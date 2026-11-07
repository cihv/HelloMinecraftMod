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
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class OriEntity extends AnimalEntity {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public final AnimationState jumpAnimationState = new AnimationState();
    private int jumpTrigger = 0;
    private int lastJumpTrigger = 0;
    // 1. 跳跃激发器
    private static final TrackedData<Integer> JUMP_ANIM_TRIGGER =
            DataTracker.registerData(OriEntity.class, TrackedDataHandlerRegistry.INTEGER);

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
        if(this.getWorld().isClient()){
            setupAniamtionStates();
        }
    }

    private void setupAniamtionStates(){
        //处于跳跃状态
        if(this.lastJumpTrigger != this.getJumpTrigger()){
            this.lastJumpTrigger = this.getJumpTrigger();
            this.jumpAnimationState.start(this.age);
        }
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(40) + 80;
            this.idleAnimationState.start(this.age);
        } else {
            this.idleAnimationTimeout--;
        }
    }
    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(JUMP_ANIM_TRIGGER, 0);
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
