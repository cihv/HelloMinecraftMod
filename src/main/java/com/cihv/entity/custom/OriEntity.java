package com.cihv.entity.custom;

import com.cihv.entity.ModEntities;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
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
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class OriEntity extends AnimalEntity {
    //动画：空闲
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    //动画：跳跃
    public final AnimationState jumpAnimationState = new AnimationState();
    private int jumpTrigger = 0;
    private int lastJumpTrigger = 0;
    private static final int DOUBLE_JUMP_DURATION_TICKS = 48;
    private static final int JUMP_1_DURATION_TICKS = 32;
    private int doubleJumpTicksLeft = 0;
    private int jump1TicksLeft = 0;
    // 1. 跳跃激发器
    private static final TrackedData<Integer> JUMP_ANIM_TRIGGER =
            DataTracker.registerData(OriEntity.class, TrackedDataHandlerRegistry.INTEGER);
    //动画：二段跳（前空翻）
    public final AnimationState jump2AnimationStatement = new AnimationState();
    private static final TrackedData<Boolean> isJump2Anim =
            DataTracker.registerData(OriEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    //动画：二段跳（旋转跳）
    public final AnimationState jump2SpinAnimationStatement = new AnimationState();
    private static final TrackedData<Boolean> isJump2SpinAnim =
            DataTracker.registerData(OriEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(JUMP_ANIM_TRIGGER, 0);
        this.dataTracker.startTracking(isJump2Anim, false);
        this.dataTracker.startTracking(isJump2SpinAnim, false);
    }

    public boolean isJumping2() {
        return this.dataTracker.get(isJump2Anim);
    }

    public boolean isJumping2Spin() {
        return this.dataTracker.get(isJump2SpinAnim);
    }

    private void setJump2(boolean jump2) {
        this.dataTracker.set(isJump2Anim, jump2);
        if(jump2){
            this.setJump2Spin(false);
            this.doubleJumpTicksLeft = DOUBLE_JUMP_DURATION_TICKS;
            this.getNavigation().stop();
            this.setTarget(null);
        }
    }

    private void setJump2Spin(boolean jump2Spin) {
        this.dataTracker.set(isJump2SpinAnim, jump2Spin);
        if(jump2Spin){
            this.setJump2(false);
            this.doubleJumpTicksLeft = DOUBLE_JUMP_DURATION_TICKS;
            this.getNavigation().stop();
            this.setTarget(null);
        }
    }

    public boolean isMoving(Double speed) {
        return speed > 1.0e-6;
    }

    public OriEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.ORI.create(world);
    }

    /**
     * 设置生物属性
     *
     * @return
     */
    public static DefaultAttributeContainer createOriAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 14)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1f)
                .add(EntityAttributes.GENERIC_ARMOR, 0.5f)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2f)
                .build();
    }

    /**
     * 设置生物行为
     */
    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new TemptGoal(this, 0.3f,
                Ingredient.ofItems(Items.POPPY), false));
        this.goalSelector.add(2, new WanderAroundFarGoal(this, 0.2d, 0.5f));
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient()) {
            setupAniamtionStates();
        } else if (this.isJumping2() || this.isJumping2Spin()) {
            if (--this.doubleJumpTicksLeft <= 0) {
                this.doubleJumpTicksLeft = 0;
                this.dataTracker.set(isJump2Anim, false);
                this.dataTracker.set(isJump2SpinAnim, false);
            }
        }
    }

    private void setupAniamtionStates() {
        boolean doubleJumping = this.isJumping2() || this.isJumping2Spin();

        //处于一段跳状态
        if (this.lastJumpTrigger != this.getJumpTrigger()) {
            this.lastJumpTrigger = this.getJumpTrigger();
            if (!doubleJumping) {
                this.jumpAnimationState.start(this.age);
                this.jump1TicksLeft = JUMP_1_DURATION_TICKS;
            }
        }
        if (this.jump1TicksLeft > 0 && --this.jump1TicksLeft == 0) {
            this.jumpAnimationState.stop();
        }

        //处于二段跳状态
        if(this.isJumping2()){
            this.jump2SpinAnimationStatement.stop();
            this.jump2AnimationStatement.startIfNotRunning(this.age);
            return;
        }
        this.jump2AnimationStatement.stop();

        if(this.isJumping2Spin()){
            this.jump2SpinAnimationStatement.startIfNotRunning(this.age);
            return;
        }
        this.jump2SpinAnimationStatement.stop();

        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            this.idleAnimationTimeout--;
        }
    }

    private void jump2Logic(PlayerEntity player, ItemStack stackInHand, Hand hand) {
        if (stackInHand.isEmpty() && hand == Hand.MAIN_HAND) {
            if (player.isSneaking()) {
                //潜行空手右键：二段旋转跳
                this.setJump2Spin(!this.isJumping2Spin());
            } else {
                //非潜行空手右键：二段前空翻
                this.setJump2(!this.isJumping2());
            }
        }
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack stackInHand = player.getStackInHand(hand);
        if (!player.getWorld().isClient()) {
            jump2Logic(player, stackInHand, hand);
            return ActionResult.SUCCESS;
        }
        return super.interactMob(player, hand);
    }

    // 2. 重写 jump
    @Override
    public void jump() {
        // 自定义跳跃物理（例如固定高度）
        this.setVelocity(this.getVelocity().x, 0.42D, this.getVelocity().z);

        // 跳跃动画开始
        this.dataTracker.set(JUMP_ANIM_TRIGGER, ++this.jumpTrigger);
    }

    // 3. 返回跳跃激发器数值
    public int getJumpTrigger() {
        return this.dataTracker.get(JUMP_ANIM_TRIGGER);
    }
}
