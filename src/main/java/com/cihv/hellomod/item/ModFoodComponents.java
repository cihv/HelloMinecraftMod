package com.cihv.hellomod.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;

public class ModFoodComponents {
    //创建自定义食物组件
    public static final FoodComponent TEST_FOOD = new FoodComponent.Builder()
            .hunger(2)//饥饿值回复 1 格
            .saturationModifier(1)//饱和度回复 0.5 格
            .statusEffect(new StatusEffectInstance(StatusEffects.LUCK, 100), 0.5f)//持续时间100/20=5秒
            .build();
}
