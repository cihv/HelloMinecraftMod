package com.cihv.hellomod.util;

import com.cihv.hellomod.HelloMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class ModTags {

    //内部类Blocks，存放方块类型的tag
    public static class Blocks {

        //自定义tag——on-block
        public static final TagKey<Block> METAL_DETECTOR_DETECTABLE_BLOCKS =
                createTag("metal_detector_detectable_blocks");
        /**
         * 创建Block标签
         * @param name
         * @return 生成的TagKey对象
         */
        public static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, HelloMod.id(name));
        }
    }

    //内部类Items，存放物品类型的tag
    public static class Items {

        /**
         * 创建Item标签
         * @param name
         * @return 生成的TagKey对象
         */
        public static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, HelloMod.id(name));
        }
    }
}
