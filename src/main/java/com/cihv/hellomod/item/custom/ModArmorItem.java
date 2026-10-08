package com.cihv.hellomod.item.custom;

import com.cihv.hellomod.item.ModArmorMaterial;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

public class ModArmorItem extends ArmorItem {
    /*private static final Map<ArmorMaterial, StatusEffectInstance> ARMOR_MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<ArmorMaterial, StatusEffectInstance>())
                    .put(ModArmorMaterial.TEST_ITEM, new StatusEffectInstance(
                                    StatusEffects.GLOWING, -1, 99, true, false, true
                            ) //发光 Lv100
                    ).put(ModArmorMaterial.XXX, new StatusEffectInstance(
                                    StatusEffects.REGENERATION, -1, 99, true, false, true
                            ) //生命恢复 Lv100
                    ).put(ModArmorMaterial.YYY, new StatusEffectInstance(
                                    StatusEffects.SPEED, -1, 3, true, false, true
                            ) //速度 Lv3
                    ).put(ModArmorMaterial.ZZZ, new StatusEffectInstance(
                                    StatusEffects.NIGHT_VISION, -1, 99, true, false, true
                            ) //夜视 Lv100
                    ).put(ModArmorMaterial.QQQ, new StatusEffectInstance(
                                    StatusEffects.RESISTANCE, -1, 99, true, false, true
                            ) //抗性提升 Lv100
                    ).build();*/
    private static final Multimap<ArmorMaterial, StatusEffectInstance> ARMOR_MATERIAL_TO_EFFECTS_MAP =
            ImmutableMultimap.<ArmorMaterial, StatusEffectInstance>builder()
                    .put(ModArmorMaterial.TEST_ITEM, new StatusEffectInstance(
                                    StatusEffects.GLOWING, 600, 99, true, false, true
                            ) //发光 Lv100
                    ).put(ModArmorMaterial.TEST_ITEM, new StatusEffectInstance(
                                    StatusEffects.REGENERATION, 600, 99, true, false, true
                            ) //生命恢复 Lv100
                    ).put(ModArmorMaterial.TEST_ITEM, new StatusEffectInstance(
                                    StatusEffects.SPEED, 600, 3, true, false, true
                            ) //速度 Lv3
                    ).put(ModArmorMaterial.TEST_ITEM, new StatusEffectInstance(
                                    StatusEffects.NIGHT_VISION, 600, 0, true, false, true
                            ) //夜视 Lv1
                    ).put(ModArmorMaterial.TEST_ITEM, new StatusEffectInstance(
                                    StatusEffects.RESISTANCE, 600, 99, true, false, true
                            ) //抗性提升 Lv100
                    ).build();

    public ModArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            /*if (entity instanceof PlayerEntity player && hasFullSuitOfArmorOn(player)) {
                *//*evaluateArmorEffects(player);*//* //原本意思是全套才生效，只是这里我只加了一个头盔
            }*/
            if(entity instanceof PlayerEntity player && !(player.getInventory().getArmorStack(3)).isEmpty()){
                evaluateArmorEffects2(player); //只要戴了这种头盔 就生效
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
        ItemStack helmetStack = player.getInventory().getArmorStack(3);
        return !helmetStack.isEmpty();
    }

    /*private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<ArmorMaterial, StatusEffectInstance> entry : ARMOR_MATERIAL_TO_EFFECT_MAP.entrySet()) {
            ArmorMaterial mapArmorMaterial = entry.getKey();
            StatusEffectInstance mapArmorEffect = entry.getValue();
            if (hasCorrectArmorOn(mapArmorMaterial, player)) { //是否穿戴了正确的装备
                addStatusEffectForMaterial(player, mapArmorMaterial, mapArmorEffect); //赋予玩家对应装备材料的效果
            }
        }
    }*/
    private void evaluateArmorEffects2(PlayerEntity player) {
        Set<ArmorMaterial> wornMaterials = new HashSet<>();
        for (ItemStack itemStack : player.getArmorItems()) {
            if(itemStack.getItem() instanceof ArmorItem armorItem){
                wornMaterials.add(armorItem.getMaterial());
            }
        }
        for (ArmorMaterial armorMaterial : wornMaterials) {
            for (StatusEffectInstance effect : ARMOR_MATERIAL_TO_EFFECTS_MAP.get(armorMaterial)) {
                player.addStatusEffect(new StatusEffectInstance(effect));
            }
        }
    }

    private boolean hasCorrectArmorOn(ArmorMaterial mapArmorMaterial, PlayerEntity player) {
        for (ItemStack armorStack : player.getInventory().armor) {
            if (!(armorStack.getItem() instanceof ArmorItem)) {
                return false;
            }
        }
        ArmorItem helmet = (ArmorItem) player.getInventory().getArmorStack(3).getItem();
        return helmet.getMaterial() == mapArmorMaterial;
    }

    private void addStatusEffectForMaterial(PlayerEntity player, ArmorMaterial mapArmorMaterial, StatusEffectInstance mapArmorEffect) {
        boolean hassedStatusEffect = player.hasStatusEffect(mapArmorEffect.getEffectType());

        if (hasCorrectArmorOn(mapArmorMaterial, player) && !hassedStatusEffect) {
            player.addStatusEffect(new StatusEffectInstance(mapArmorEffect));
        }
    }
}
