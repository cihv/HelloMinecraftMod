package com.cihv.leadopt.api;

import com.cihv.leadopt.platform.PlatformHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ConfigFile {
   public static final Logger LOGGER = LogManager.getLogger(ConfigFile.class);
   private static Map<Class<?>, Function<Object, String>> encoders = new HashMap<>();
   private static Map<Class<?>, Function<String, Object>> decoders = new HashMap<>();

   public static void write(Class<?> cls) {
      ConfigInstance ci = new ConfigInstance(cls);
      ci.write();
   }

   public static void sync(Class<?> cls) {
      ConfigInstance ci = new ConfigInstance(cls);
      ci.read();
      ci.write();
   }

   public static <T> void addMapper(Class<T> cls, Function<T, String> encoder, Function<String, T> decoder) {
      encoders.put(cls, x -> encoder.apply((T)x));
      decoders.put(cls, decoder::apply);
   }

   private static String getPrefix(Class<?> cls) {
      return cls.isEnum() ? "S" : cls.getSimpleName().substring(0, 1).toUpperCase(Locale.ROOT);
   }

   private static String encode(Class<?> cls, Object o) {
      return cls.isEnum() ? ((Enum)o).name() : encoders.get(cls).apply(o);
   }

   private static Object decode(Class<?> cls, String s) {
      return cls.isEnum() ? Enum.valueOf((Class)cls, s) : decoders.get(cls).apply(s);
   }

   static {
      addMapper(int.class, Object::toString, Integer::parseInt);
      addMapper(Integer.class, i -> i == null ? "" : i.toString(), Integer::parseInt);
      addMapper(long.class, Object::toString, Long::parseLong);
      addMapper(Long.class, i -> i == null ? "" : i.toString(), Long::parseLong);
      addMapper(float.class, Object::toString, Float::parseFloat);
      addMapper(Float.class, i -> i == null ? "" : i.toString(), Float::parseFloat);
      addMapper(double.class, Object::toString, Double::parseDouble);
      addMapper(Double.class, i -> i == null ? "" : i.toString(), Double::parseDouble);
      addMapper(boolean.class, Object::toString, Boolean::parseBoolean);
      addMapper(Boolean.class, i -> i == null ? "" : i.toString(), Boolean::parseBoolean);
      addMapper(String.class, i -> i == null ? "" : i, l -> l);
   }

   @Retention(RetentionPolicy.RUNTIME)
   public @interface Comment {
      String value();
   }

   static class ConfigInstance {
      final PropertyClass pc;
      final Path path;

      public ConfigInstance(Class<?> cls) {
         this.pc = new PropertyClass(cls);
         File file = this.pc.getAnnotation(File.class);
         this.path = Paths.get(PlatformHelper.getConfigPath().toString(), file.value());
      }

      public void read() {
         if (Files.exists(this.path)) {
            List<String> lines;
            try {
               lines = Files.readAllLines(this.path);
            } catch (Exception e) {
               throw new RuntimeException("Unable to read config file " + this.path, e);
            }

            lines = lines.stream().filter(x -> !x.matches("\\s*#.*")).filter(x -> !x.matches("\\s*")).map(String::trim).collect(Collectors.toList());
            if (lines.size() > 2) {
               this.pc.read(lines);
            }
         }
      }

      public void write() {
         try {
            Files.write(this.path, String.join(System.lineSeparator(), this.pc.write()).getBytes());
         } catch (Exception e) {
            throw new RuntimeException("Unable to write config file " + this.path, e);
         }
      }
   }

   @Retention(RetentionPolicy.RUNTIME)
   public @interface File {
      String value();
   }

   @Retention(RetentionPolicy.RUNTIME)
   public @interface Name {
      String value();
   }

   abstract static class Property {
      protected abstract <A extends Annotation> A getAnnotation(Class<A> var1);

      protected abstract void read(List<String> var1);

      protected abstract List<String> write();

      protected abstract String getName();

      protected String getName(String def) {
         Name n = this.getAnnotation(Name.class);
         return n == null ? def : n.value();
      }

      protected String getComment() {
         Comment n = this.getAnnotation(Comment.class);
         return n == null ? "" : n.value();
      }

      public Range getRange() {
         return this.getAnnotation(Range.class);
      }

      protected List<String> getFormattedComment() {
         if (this.getComment().length() == 0) {
            return new ArrayList<>();
         }

         List<String> result = new ArrayList<>();
         String[] parts = this.getComment().split("\n");
         if (parts.length == 1) {
            result.add("# " + parts[0]);
         } else {
            int max = Arrays.stream(parts).map(String::length).sorted(Comparator.reverseOrder()).findFirst().get();
            max = Math.max(max, this.getName().length());
            result.add(StringUtils.repeat("#", max + 4));
            result.add("# " + this.getName() + StringUtils.repeat(" ", max - this.getName().length()) + " #");
            result.add("# " + StringUtils.repeat("-", max) + " #");

            for (String part : parts) {
               result.add("# " + part + StringUtils.repeat(" ", max - part.length()) + " #");
            }

            result.add(StringUtils.repeat("#", max + 4));
         }

         return result;
      }
   }

   static class PropertyClass extends Property {
      private final Class<?> cls;
      final List<Property> properties;

      public PropertyClass(Class<?> cls) {
         this.cls = cls;
         this.properties = new ArrayList<>();

         for (Field field : cls.getDeclaredFields()) {
            field.setAccessible(true);
            if (this.canAccess(field.getModifiers()) && field.isAccessible()) {
               this.properties.add(new PropertyField(field));
            }
         }

         for (Class<?> scls : cls.getDeclaredClasses()) {
            if (Modifier.isPublic(cls.getModifiers())) {
               this.properties.add(new PropertyClass(scls));
            }
         }
      }

      @Override
      protected <A extends Annotation> A getAnnotation(Class<A> cls) {
         return this.cls.getAnnotation(cls);
      }

      @Override
      protected void read(List<String> lines) {
         lines.remove(0);

         while (!lines.isEmpty()) {
            String line = lines.get(0);
            if (line.equals("}")) {
               lines.remove(0);
               return;
            }

            String[] parts = line.split("[{=<]");
            Property prop = this.properties
               .stream()
               .filter(x -> x.getName().equals(parts[0].trim()) || x.getName().equals(parts[0].substring(2)))
               .findFirst()
               .orElse(null);
            if (prop != null) {
               prop.read(lines);
            } else {
               lines.remove(0);
            }
         }
      }

      @Override
      protected List<String> write() {
         List<String> lines = new ArrayList<>();
         lines.addAll(this.getFormattedComment());
         lines.add(this.getName() + " {");

         for (Property p : this.properties) {
            p.write().forEach(line -> lines.add("    " + line));
         }

         lines.add("}");
         lines.add("");
         return lines;
      }

      @Override
      protected String getName() {
         return this.getName(this.cls.getSimpleName());
      }

      private boolean canAccess(int modifiers) {
         return Modifier.isStatic(modifiers) && Modifier.isPublic(modifiers) && !Modifier.isFinal(modifiers);
      }
   }

   static class PropertyField extends Property {
      final Field field;

      private PropertyField(Field f) {
         this.field = f;
      }

      @Override
      protected <A extends Annotation> A getAnnotation(Class<A> cls) {
         return this.field.getAnnotation(cls);
      }

      @Override
      protected void read(List<String> lines) {
         if (this.field.getType().isArray()) {
            lines.remove(0);
            List<String> found = new ArrayList<>();

            while (!lines.isEmpty()) {
               String line = lines.remove(0);
               if (line.equals(">")) {
                  try {
                     Object[] array = (Object[])Array.newInstance(this.field.getType().getComponentType(), found.size());

                     for (int i = 0; i < found.size(); i++) {
                        array[i] = ConfigFile.decode(this.field.getType().getComponentType(), found.get(i));
                     }

                     this.field.set(null, array);
                  } catch (IllegalAccessException | NullPointerException e) {
                     ConfigFile.LOGGER.error("Error reading field " + this.field);
                     e.printStackTrace();
                  }

                  return;
               }

               found.add(line);
            }
         }

         if (Map.class.isAssignableFrom(this.field.getType())) {
            lines.remove(0);
            List<String> found = new ArrayList<>();

            while (!lines.isEmpty()) {
               String line = lines.remove(0);
               if (line.equals("}")) {
                  try {
                     Map<Object, Object> data = (Map<Object, Object>)this.field.get(null);
                     data.clear();
                     Type[] types = ((ParameterizedType)this.field.getGenericType()).getActualTypeArguments();
                     Class<?> kt = (Class<?>)types[0];
                     Class<?> vt = (Class<?>)types[1];

                     for (String s : found) {
                        String[] sp = s.split("=", 2);
                        Object key = ConfigFile.decode(kt, sp[0].substring(2));
                        Object val = ConfigFile.decode(vt, sp[1]);
                        data.put(key, val);
                     }
                  } catch (IllegalAccessException | NullPointerException e) {
                     ConfigFile.LOGGER.error("Error reading field " + this.field);
                     e.printStackTrace();
                  }

                  return;
               }

               found.add(line);
            }
         }

         String line = lines.remove(0);

         try {
            this.field.set(null, ConfigFile.decode(this.field.getType(), line.split("=", 2)[1]));
         } catch (IllegalAccessException | NullPointerException e) {
            ConfigFile.LOGGER.error("Error reading field " + this.field);
            e.printStackTrace();
         }
      }

      @Override
      protected List<String> write() {
         List<String> lines = new ArrayList<>();
         lines.addAll(this.getFormattedComment());
         if (this.field.getType().isArray()) {
            Class aType = this.field.getType().getComponentType();
            lines.add(this.getName() + " <");

            try {
               Object[] data = (Object[])this.field.get(null);

               for (Object elem : data) {
                  lines.add("    " + ConfigFile.encode(aType, elem));
               }
            } catch (IllegalAccessException | NullPointerException e) {
               throw new RuntimeException("Error writing field " + this.field, e);
            }

            lines.add(">");
            lines.add("");
            return lines;
         } else if (Map.class.isAssignableFrom(this.field.getType())) {
            lines.add(this.getName() + " {");

            try {
               Map<Object, Object> data = (Map<Object, Object>)this.field.get(null);

               for (Object key : data.keySet()) {
                  Object value = data.get(key);
                  lines.add(
                     "    "
                        + ConfigFile.getPrefix(value.getClass())
                        + ":"
                        + ConfigFile.encode(key.getClass(), key)
                        + "="
                        + ConfigFile.encode(value.getClass(), value)
                  );
               }
            } catch (IllegalAccessException | NullPointerException e) {
               throw new RuntimeException("Error writing field " + this.field, e);
            }

            lines.add("}");
            lines.add("");
            return lines;
         } else {
            try {
               lines.add(
                  ConfigFile.getPrefix(this.field.getType()) + ":" + this.getName() + "=" + ConfigFile.encode(this.field.getType(), this.field.get(null))
               );
            } catch (IllegalAccessException | NullPointerException e) {
               throw new RuntimeException("Error writing field " + this.field, e);
            }

            lines.add("");
            return lines;
         }
      }

      @Override
      protected String getName() {
         return this.getName(this.field.getName());
      }
   }

   @Retention(RetentionPolicy.RUNTIME)
   public @interface Range {
      double min();

      double max();
   }
}
