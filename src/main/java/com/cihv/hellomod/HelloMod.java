package com.cihv.hellomod;

import com.cihv.hellomod.block.ModBlocks;
import com.cihv.hellomod.entity.ModEntities;
import com.cihv.hellomod.item.ModItems;
import com.cihv.hellomod.item.ModItemGroups;
import com.cihv.hellomod.sound.ModSounds;
import com.cihv.hellomod.util.ModLootTableModifiers;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloMod implements ModInitializer {
	public static final String MOD_ID = "hellomod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	/**
	 * 初始化方法,用以注册模组中所自定义的 物品组,物品,方块
	 */
	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
		ModLootTableModifiers.modifyLootTables();
		ModSounds.registerSounds();
		ModEntities.registerModEntities();
	}

	/**
	 * 返回一个 Identifier 对象
	 * @param path String
	 *
	 * @return Identifier
	 */
	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}
