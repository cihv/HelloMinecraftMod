package com.cihv.hellomod.block;

import com.cihv.hellomod.HelloMod;
import com.cihv.hellomod.block.custom.SoundBlock;
import com.cihv.hellomod.sound.ModSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;

public class ModBlocks {
    public static final Block TEST_BLOCK = registerBlock("test_block", new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)
            .sounds(BlockSoundGroup.AMETHYST_BLOCK).strength(2f)));
    public static final Block TEST_SOUND_BLOCK = registerBlock("test_sound_block",
            new SoundBlock(FabricBlockSettings.copyOf(Blocks.GRASS_BLOCK).sounds(ModSounds.TEST_SOUND_BLOCK_SOUNDS).strength(0.5f)));

    /**
     * 辅助方法,用来获取注册后的 Block 对象
     * @param name String
     * @param block Block
     *
     * @return 注册后的Block对象
     */
    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, HelloMod.id(name), block);
    }

    /**
     * 辅助方法,用来获取注册后的 Item 对象(原生为Block)
     * @param name String
     * @param block Block
     *
     * @return 注册后的 Item 对象(原生为Block)
     */
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, HelloMod.id(name), new BlockItem(block, new FabricItemSettings()));
    }

    /**
     * 用来注册模组中的Block,后续在纯mod类中调用
     */
    public static void registerModBlocks() {
        HelloMod.LOGGER.info("正在注册 " + HelloMod.MOD_ID + " 的自定义方块..");
    }
}
