package com.cihv.leadopt.utils;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DynamicOps;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.MapLike;
import com.cihv.leadopt.LeadOpt;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.nbt.Tag;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class NbtUtils {
   public static <T> void store(CompoundTag tag, String string, Codec<T> codec, T object) {
      store(tag, string, codec, NbtOps.INSTANCE, object);
   }

   public static <T> void storeNullable(CompoundTag tag, String string, Codec<T> codec, @Nullable T object) {
      if (object != null) {
         store(tag, string, codec, object);
      }
   }

   public static <T> void store(CompoundTag tag, String string, Codec<T> codec, DynamicOps<Tag> dynamicOps, T object) {
      tag.put(
         string,
         (Tag)codec.encodeStart(dynamicOps, object)
            .getOrThrow(true, string2 -> LeadOpt.LOGGER.error("Failed to write field ({}={}): {}", new Object[]{string, object, string2}))
      );
   }

   public static <T> void storeNullable(CompoundTag tag, String string, Codec<T> codec, DynamicOps<Tag> dynamicOps, @Nullable T object) {
      if (object != null) {
         store(tag, string, codec, dynamicOps, object);
      }
   }

   public static <T> void store(CompoundTag tag, MapCodec<T> mapCodec, T object) {
      store(tag, mapCodec, NbtOps.INSTANCE, object);
   }

   public static <T> void store(CompoundTag tag, MapCodec<T> mapCodec, DynamicOps<Tag> dynamicOps, T object) {
      tag.merge(
         (CompoundTag)mapCodec.encoder()
            .encodeStart(dynamicOps, object)
            .getOrThrow(true, string2 -> LeadOpt.LOGGER.error("Failed to write field ({}): {}", string2, object))
      );
   }

   public static <T> Optional<T> read(CompoundTag tag, String string, Codec<T> codec) {
      return read(tag, string, codec, NbtOps.INSTANCE);
   }

   public static <T> Optional<T> read(CompoundTag tag, String string, Codec<T> codec, DynamicOps<Tag> dynamicOps) {
      Tag tag1 = tag.get(string);
      return tag1 == null
         ? Optional.empty()
         : codec.parse(dynamicOps, tag)
            .resultOrPartial(string2 -> LeadOpt.LOGGER.error("Failed to read field ({}={}): {}", new Object[]{string, tag, string2}));
   }

   public static <T> Optional<T> read(CompoundTag tag, MapCodec<T> mapCodec) {
      return read(tag, mapCodec, NbtOps.INSTANCE);
   }

   public static <T> Optional<T> read(CompoundTag tag, MapCodec<T> mapCodec, DynamicOps<Tag> dynamicOps) {
      return mapCodec.decode(
            dynamicOps, (MapLike)dynamicOps.getMap(tag).getOrThrow(true, string -> LeadOpt.LOGGER.error("Failed to read value ({}): {}", tag, string))
         )
         .result();
   }
}
