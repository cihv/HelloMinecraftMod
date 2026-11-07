package com.cihv;

import com.cihv.entity.ModEntities;
import com.cihv.entity.custom.OriEntity;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ori implements ModInitializer {
	public static final String MOD_ID = "ori";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Hello Ori!");
		//注册生物Ori
		FabricDefaultAttributeRegistry.register(ModEntities.ORI, OriEntity.createOriAttributes());
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
