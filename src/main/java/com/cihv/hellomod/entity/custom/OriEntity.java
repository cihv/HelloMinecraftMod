package com.cihv.hellomod.entity.custom;

import com.cihv.hellomod.entity.ModEntities;
import com.cihv.hellomod.item.ModItems;
import com.cihv.hellomod.sound.ModSounds;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class OriEntity extends AnimalEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState sitAnimationState = new AnimationState();
    public final AnimationState danceAnimationState = new AnimationState();

    private int idleAnimationTimeout = 0;
    private static final TrackedData<Boolean> SITTING = DataTracker.registerData(OriEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> DANCING = DataTracker.registerData(OriEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public OriEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    //设置生物Ori的属性
    public static DefaultAttributeContainer.Builder createOriAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2f)
                .add(EntityAttributes.GENERIC_ARMOR, 0.5f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2);
    }

    //设置生物Ori的行为
    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(0, new TemptGoal(this, 1f, Ingredient.ofItems(ModItems.TEST_ITEM), false));
        this.goalSelector.add(1, new AnimalMateGoal(this, 1f));
        this.goalSelector.add(3, new FollowParentGoal(this, 1f));
        this.goalSelector.add(4, new WanderAroundFarGoal(this, 1d));
        this.goalSelector.add(5, new LookAtEntityGoal(this, PlayerEntity.class, 1f));
        this.goalSelector.add(6, new LookAroundGoal(this));

    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SITTING, false);
        this.dataTracker.startTracking(DANCING, false);
    }

    public boolean isSitting() {
        return this.dataTracker.get(SITTING);
    }

    public void setSitting(boolean sitting) {
        this.dataTracker.set(SITTING, sitting);
        if (sitting) {
            this.getNavigation().stop();
            this.setTarget(null);
            this.setVelocity(0, 0, 0);
        }
    }

    public boolean isMoving(Double speed) {
        return speed > 1.0e-6;
    }

    public boolean isDancing() {
        return this.dataTracker.get(DANCING);
    }

    public void setDancing(boolean dancing) {
        this.dataTracker.set(DANCING, dancing);
        if (dancing) {
            this.setSitting(false);
            this.getNavigation().stop();
            this.setTarget(null);
            this.setVelocity(0, 0, 0);
        }
    }

    //右键交互
    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        //空手右键坐下
        if (!this.getWorld().isClient() && hand == Hand.MAIN_HAND && stack.isEmpty()) {
            if (player.isSneaking()) {
                this.setDancing(!this.isDancing());
                this.getWorld().playSound(null, this.getBlockPos(), ModSounds.ORI_DANCE_SOUND, SoundCategory.MUSIC, 5f, 1f);
            } else {
                this.setDancing(false);
                this.setSitting(!this.isSitting());
            }
            return ActionResult.SUCCESS;
        }
        return super.interactMob(player, hand);
    }

    private void setupAnimationStates() {
        if (this.isSitting()) {
            this.idleAnimationState.stop();
            this.sitAnimationState.startIfNotRunning(this.age);
            return;
        }
        this.sitAnimationState.stop();

        if (this.isDancing()) {
            this.idleAnimationState.stop();
            this.danceAnimationState.startIfNotRunning(this.age);
            return;
        }
        this.danceAnimationState.stop();

        if (this.isMoving(this.getVelocity().horizontalLengthSquared())) {
            this.idleAnimationState.stop();
        } else {
            if (this.idleAnimationTimeout <= 0) {
                this.idleAnimationTimeout = this.random.nextInt(40) + 80;
                this.idleAnimationState.start(this.age);
            } else {
                this.idleAnimationTimeout--;
            }
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getWorld().isClient()) {
            if (this.isSitting() || this.isDancing()) {
                this.getNavigation().stop();
                this.setVelocity(0, 0, 0);
            }
        }
        if (this.getWorld().isClient()) {
            setupAnimationStates();
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(ModItems.TEST_ITEM);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.ORI.create(world);
    }

    //设置生物Ori的声音
    @Override
    protected @Nullable SoundEvent getAmbientSound() {
        return ModSounds.ORI_AMBIENT_SOUND;
    }

    @Override
    protected @Nullable SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ORI_HURT_SOUND;
    }

    @Override
    protected @Nullable SoundEvent getDeathSound() {
        return ModSounds.ORI_DEATH_SOUND;
    }
}
