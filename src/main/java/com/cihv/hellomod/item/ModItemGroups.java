package com.cihv.hellomod.item;

import com.cihv.hellomod.HelloMod;
import com.cihv.hellomod.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

public class ModItemGroups {
    public static final ItemGroup HELLOMOD_GROUP = Registry.register(
            Registries.ITEM_GROUP,
            HelloMod.id("hellomod_group"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("itemgroup.hellomod_group"))
                    .icon(() -> new ItemStack(ModItems.AWESOMELEAD))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.AWESOMELEAD);
                        entries.add(ModBlocks.TEST_BLOCK);//测试——添加自定义block
                        entries.add(ModItems.TEST_ITEM);//测试——添加自定义item
                        entries.add(ModItems.METAL_DETECTOR_STICK);//测试——添加自定义item(advanced-edition)
                        entries.add(ModBlocks.TEST_SOUND_BLOCK);//测试——添加自定义block(advanced-edition)
                        entries.add(ModItems.TEST_FOOD);//测试——添加自定义食物
                        entries.add(ModItems.TEST_FUEL);//测试——添加自定义燃料
                        entries.add(ModItems.TEST_STAFF);//测试——添加自定义3d物品
                        entries.add(ModItems.TEST_SWORD);//测试——添加自定义工具：剑
                        entries.add(ModItems.TEST_HELMET);//测试——添加自定义装备：头盔
                        entries.add(ModItems.TEST_3D_HELMET);//测试——添加自定义3d装备：3d头盔
                        entries.add(ModItems.WA_WA_LIAN_MUSIC_DISC);//测试——添加自定义唱片(歌名为《娃娃脸》)
                        entries.add(ModItems.ORI_SPAWN_EGG);//测试——添加自定义生物Ori的刷怪蛋
                    })
                    .build()
    );

    /**
     * 用来注册模组中的ItemGroup,后续在纯mod类调用
     */
    public static void registerItemGroups() {
        HelloMod.LOGGER.info("正在注册 " + HelloMod.MOD_ID + " 的自定义物品组..");
    }
}
