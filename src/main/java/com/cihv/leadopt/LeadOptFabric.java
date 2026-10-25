package com.cihv.leadopt;

import net.fabricmc.api.ModInitializer;

public final class LeadOptFabric implements ModInitializer {
   @Override
   public void onInitialize() {
      LeadOpt.init();
   }
}
