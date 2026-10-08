package com.cihv.hellomod.item.custom;

import com.cihv.hellomod.client.renderer.Test3DHelmetRenderer;
import com.cihv.hellomod.common.constant.GeoAnimationsConstant;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.animatable.client.RenderProvider;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.util.GeckoLibUtil;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Test3DHelmetItem extends ArmorItem implements GeoItem {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private final Supplier<Object> renderProvider = GeoItem.makeRenderer(this);

    public Test3DHelmetItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void createRenderer(Consumer<Object> consumer) {
        consumer.accept(new RenderProvider() {
            private Test3DHelmetRenderer renderer;

            @Override
            public BipedEntityModel<LivingEntity> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack,
                                                                        EquipmentSlot equipmentSlot,
                                                                        BipedEntityModel<LivingEntity> original) {
                if (this.renderer == null)
                    this.renderer = new Test3DHelmetRenderer();

                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

                return this.renderer;
            }
        });
    }

    @Override
    public Supplier<Object> getRenderProvider() {
        return renderProvider;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<Test3DHelmetItem>(this, "player_status", 5,
                state -> {
                    //获取当前实体，判断当前实体是否是玩家，如果是 则再判断玩家状态
                    Entity entity = state.getData(DataTickets.ENTITY);
                    if (!(entity instanceof PlayerEntity player)) {
                        return state.setAndContinue(RawAnimation.begin().thenLoop(GeoAnimationsConstant.STAY));
                    }
                    if (player.isSneaking()) {
                        return state.setAndContinue(RawAnimation.begin().thenLoop(GeoAnimationsConstant.SNEAK));
                    }
                    if (player.isSprinting()) {
                        return state.setAndContinue(RawAnimation.begin().thenLoop(GeoAnimationsConstant.RUNNING));
                    }
                    if (player.getVelocity().horizontalLengthSquared() > 0.001) {
                        return state.setAndContinue(RawAnimation.begin().thenLoop(GeoAnimationsConstant.WALK));
                    }
                    return state.setAndContinue(RawAnimation.begin().thenLoop(GeoAnimationsConstant.STAY));
                }));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return this.cache;
    }
}
