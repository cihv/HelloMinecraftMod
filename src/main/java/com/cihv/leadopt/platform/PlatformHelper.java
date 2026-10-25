package com.cihv.leadopt.platform;

import com.cihv.leadopt.platform.fabric.PlatformHelperImpl;
import dev.architectury.injectables.annotations.ExpectPlatform;
import dev.architectury.injectables.annotations.ExpectPlatform.Transformed;

import java.nio.file.Path;

public interface PlatformHelper {
   @ExpectPlatform
   @Transformed
   static Path getConfigPath() {
      return PlatformHelperImpl.getConfigPath();
   }
}
