package com.cihv.hellomod.item;

import com.cihv.hellomod.HelloMod;
import com.cihv.hellomod.entity.ModEntities;
import com.cihv.hellomod.item.custom.MetalDetectorStick;
import com.cihv.hellomod.item.custom.ModArmorItem;
import com.cihv.hellomod.item.custom.Test3DHelmetItem;
import com.cihv.hellomod.sound.ModSounds;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModItems {
    public static final Item AWESOMELEAD = registerItem("awesome_lead", new Item(new FabricItemSettings()));
    public static final Item TEST_ITEM = registerItem("test_item", new Item(new FabricItemSettings()));
    public static final Item METAL_DETECTOR_STICK = registerItem("metal_detector_stick",
            new MetalDetectorStick(new FabricItemSettings().maxDamage(1000)));
    public static final Item TEST_FOOD = registerItem("test_food", new Item(new FabricItemSettings().food(ModFoodComponents.TEST_FOOD)));
    public static final Item TEST_FUEL = registerItem("test_fuel", new Item(new FabricItemSettings()));
    public static final Item TEST_STAFF = registerItem("test_staff", new Item(new FabricItemSettings().maxCount(1)));
    public static final Item TEST_SWORD = registerItem("test_sword",
            new SwordItem(ModToolMaterial.TEST_ITEM, 18, -1.9f, new FabricItemSettings()));
    public static final Item TEST_HELMET = registerItem("test_helmet",
            new ModArmorItem(ModArmorMaterial.TEST_ITEM, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item TEST_3D_HELMET = registerItem("test_3d_helmet",
            new Test3DHelmetItem(ModArmorMaterial.TEST_ITEM, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item WA_WA_LIAN_MUSIC_DISC = registerItem("wa_wa_lian_music_disc",
            new MusicDiscItem(7, ModSounds.WA_WA_LIAN_MUSIC, new FabricItemSettings().maxCount(1), 217));
    public static final Item ORI_SPAWN_EGG = registerItem("ori_spawn_egg",
            new SpawnEggItem(ModEntities.ORI, 0x00000, 0xfffff, new FabricItemSettings()));

    /**
     * 辅助方法,将物品添加进"原材料"物品组
     * @param entries FabricItemGroupEntries
     */
    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries){
        entries.add(AWESOMELEAD);
        entries.add(TEST_ITEM);//测试——添加自定义item
    }

    /**
     * 辅助方法,用来获取注册后的 Item 对象
     * @param name String
     * @param item Item
     *
     * @return 注册后的 Item 对象
     */
    private static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, HelloMod.id(name), item);
    }

    /**
     * 用来注册模组中的Item,后续在纯mod类中调用
     */
    public static void registerModItems(){
        HelloMod.LOGGER.info("正在注册 "+ HelloMod.MOD_ID+" 模组的自定义物品..");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
        //添加一条注册燃料item的语句
        FuelRegistry.INSTANCE.add(TEST_FUEL, 100);
    }

}
