package com.cihv.leadopt;

import com.cihv.leadopt.api.ConfigFile;
import com.cihv.leadopt.config.ModConfig;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LeadOpt implements ModInitializer {
    public static final String MOD_ID = "leadopt";
    public static final String MOD_NAME = "LeadOpt";
    public static final Logger LOGGER = LoggerFactory.getLogger("LeadOpt");

    public static void init() {
        ConfigFile.sync(ModConfig.class);
    }

    @Override
    public void onInitialize() {

    }
}
