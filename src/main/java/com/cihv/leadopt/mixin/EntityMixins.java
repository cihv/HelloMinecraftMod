package com.cihv.leadopt.mixin;

import com.cihv.leadopt.LeadOpt;
import com.cihv.leadopt.api.AreaLeash;
import com.cihv.leadopt.api.ILeash;
import com.cihv.leadopt.config.ModConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundSetEntityLinkPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.LeadItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(Mob.class)
abstract class MobEntityMixin extends LivingEntity {
    protected MobEntityMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    @Shadow
    public abstract boolean isLeashed();

    @Inject(method = "canBeLeashed", at = @At("RETURN"), cancellable = true)
    private void onCanBeLeashedBy(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue((cir.getReturnValue() || (!this.isLeashed())));
    }
}


// Allows trader entities (villagers and wandering traders) to be leashed
// 村民和流浪商人
@Mixin(AbstractVillager.class)
abstract class MerchantEntityMixin extends AgeableMob implements Npc, Merchant {
    protected MerchantEntityMixin(EntityType<? extends AbstractVillager> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "canBeLeashed", at = @At("RETURN"), cancellable = true)
    private void onCanBeLeashedBy(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue((cir.getReturnValue() || (!this.isLeashed())));
    }
}

// Allows water creatures (fish) to be leashed
// 水生生物
@Mixin(WaterAnimal.class)
abstract class WaterCreatureEntityMixin extends PathfinderMob {
    protected WaterCreatureEntityMixin(EntityType<? extends WaterAnimal> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "canBeLeashed", at = @At("RETURN"), cancellable = true)
    private void onCanBeLeashedBy(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue((cir.getReturnValue() || (!this.isLeashed())));
    }
}

// Allows turtles to be leashed
// 海龟
@Mixin(Turtle.class)
abstract class TurtleEntityMixin extends Animal {
    protected TurtleEntityMixin(EntityType<? extends Turtle> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "canBeLeashed", at = @At("RETURN"), cancellable = true)
    private void onCanBeLeashedBy(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue((cir.getReturnValue() || (!this.isLeashed())));
    }
}

// Allows ambient entities (bats) to be leashed
// 环境实体 —— 蝙蝠
@Mixin(AmbientCreature.class)
abstract class AmbientEntityMixin extends Mob {
    protected AmbientEntityMixin(EntityType<? extends AmbientCreature> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "canBeLeashed", at = @At("RETURN"), cancellable = true)
    private void onCanBeLeashedBy(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue((cir.getReturnValue() || (!this.isLeashed())));
    }
}

// Allows pandas to be leashed
// 熊猫
@Mixin(Panda.class)
abstract class PandaEntityMixin extends Animal {
    protected PandaEntityMixin(EntityType<? extends Panda> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "canBeLeashed", at = @At("RETURN"), cancellable = true)
    private void onCanBeLeashedBy(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue((cir.getReturnValue() || (!this.isLeashed())));
    }
}


// Deny enderman teltport
// 禁止末影人传送
@Mixin(EnderMan.class)
abstract class EndermanTeleportMixin extends Monster {
    public EndermanTeleportMixin(EntityType<? extends EnderMan> entityType, Level world) {
        super(entityType, world);
    }

    @Inject(method = "teleport()Z", at = @At("RETURN"), cancellable = true)
    private void teleportRandomly(CallbackInfoReturnable<Boolean> cir) {
        if (this.isLeashed()) {
            LeadOpt.LOGGER.info("######## 已进入if：满足末影人被拴住 #############");
            cir.setReturnValue(false);
        }
    }
}

// 让 船 可以被拴住
@Mixin(Boat.class)
abstract class BoatMixin extends Entity implements ILeash {
    @Unique
    Boat self = (Boat)(Object)this;
    @Unique
    @Nullable
    private Entity leashHolder;
    @Unique
    private int delayedLeashHolderId;
    @Unique
    @Nullable
    private CompoundTag leashInfoTag;

    public BoatMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    public void addAdditionalSaveDatas(CompoundTag compoundTag, CallbackInfo ci) {
        this.saveData(compoundTag);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    public void readAdditionalSaveData(CompoundTag compoundTag, CallbackInfo ci) {
        this.loadData(compoundTag);
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void tick(CallbackInfo ci) {
        if (!this.level().isClientSide) {
            this.tickLeash();
            if (this.getLeashHolder() != null) {
                Vec3 subtract = this.getLeashHolder().position().subtract(this.position());
                double length = subtract.length();
                double thisSpeed = this.getDeltaMovement().length();
                double masterSpeed = this.getLeashHolder().getDeltaMovement().length();
                if (length < 5.0) {
                    return;
                }

                if (!this.level().isClientSide && length > ModConfig.BOAT_LEASH_DISTANCE + 12.0 * masterSpeed) {
                    if (masterSpeed > 1.5) {
                        this.dropLeash();
                        return;
                    }

                    this.getLeashHolder().addDeltaMovement(this.getLeashHolder().getDeltaMovement().scale(-0.1));
                }

                double f = 0.06 + length / 100.0 + (masterSpeed - thisSpeed) / 4.0;
                Vec3 v = subtract.normalize().scale(f);
                if (subtract.horizontalDistance() > 4.0) {
                    float yRotNeo = (float)(Mth.atan2(subtract.z, subtract.x) * 180.0F / (float)Math.PI) - 90.0F;
                    if (this.getYRot() != yRotNeo) {
                        float yRot = this.getYRot();
                        float rot = Mth.wrapDegrees(yRotNeo - yRot) / 5.0F;
                        this.setYRot(yRot + rot);
                    }
                }

                this.setDeltaMovement(this.getDeltaMovement().add(v));
            }
        }
    }

    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    public void interact(Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (this.self.isAlive() && player.isSecondaryUseActive()) {
            List<ILeash> list = ILeash.leashableInArea(this.self, leashablex -> leashablex.getLeashHolder() == player);
            if (!list.isEmpty()) {
                for (ILeash leashable2 : list) {
                    leashable2.setLeashedTo(this.self, true);
                }

                this.self.level().gameEvent(GameEvent.ENTITY_INTERACT, this.self.blockPosition(), GameEvent.Context.of(player));
                cir.setReturnValue(InteractionResult.sidedSuccess(this.level().isClientSide));
            }
        }

        if (itemStack.is(Items.SHEARS) && this.shearOffAllLeashConnections(player)) {
            itemStack.hurtAndBreak(1, player, playerx -> playerx.broadcastBreakEvent(interactionHand));
            cir.setReturnValue(InteractionResult.SUCCESS);
        } else if (itemStack.is(Items.LEAD) && this.canBeLeashed(player)) {
            this.setLeashedTo(player, true);
            itemStack.shrink(1);
            cir.setReturnValue(InteractionResult.sidedSuccess(this.level().isClientSide));
        }
    }

    @Override
    public void removeAfterChangingDimensions() {
        this.removeLeash();
        this.getAllSlots().forEach(itemStack -> {
            if (!itemStack.isEmpty()) {
                itemStack.setCount(0);
            }
        });
    }

    @Override
    public boolean startRiding(Entity entity, boolean bl) {
        return this.startRidingV(super.startRiding(entity, bl), entity, bl);
    }

    @Override
    public void remove(RemovalReason removalReason) {
        if (!this.level().isClientSide && removalReason.shouldDestroy() && this.isLeashed()) {
            this.removeLeash();
        }

        super.remove(removalReason);
    }

    @Nullable
    @Override
    public Entity getLeashHolder() {
        if (this.leashHolder == null && this.delayedLeashHolderId != 0 && this.level().isClientSide) {
            this.leashHolder = this.level().getEntity(this.delayedLeashHolderId);
        }

        return this.leashHolder;
    }

    @Override
    public void setLeashHolder(@Nullable Entity leashHolder) {
        this.leashHolder = leashHolder;
    }

    @Nullable
    @Override
    public CompoundTag getLeashInfoTag() {
        return this.leashInfoTag;
    }

    @Override
    public void setLeashInfoTag(@Nullable CompoundTag leashInfoTag) {
        this.leashInfoTag = leashInfoTag;
    }

    @Override
    public void setDelayedLeashHolderId(int delayedLeashHolderId) {
        this.delayedLeashHolderId = delayedLeashHolderId;
        this.dropLeash(false, false);
    }
}
@Mixin(BoatRenderer.class)
abstract class BoatRendererMixin extends EntityRenderer<Boat> {
    protected BoatRendererMixin(EntityRendererProvider.Context context) {
        super(context);
    }

    @Inject(
            method = "render(Lnet/minecraft/world/entity/vehicle/Boat;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At("TAIL"),
            cancellable = true
    )
    public void render(Boat boat, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci) {
      /*if (boat instanceof ILeash leash) {
         Entity entity = leash.getLeashHolder();
         if (entity != null) {
            this.renderLeash(boat, g, poseStack, multiBufferSource, entity);
         }
      }*/
        if (ILeash.class.isInstance(boat)) {
            ILeash leash = ILeash.class.cast(boat);
            Entity entity = leash.getLeashHolder();
            if (entity != null) {
                this.renderLeash(boat, g, poseStack, multiBufferSource, entity);
            }
        }
    }

    @Unique
    private <E extends Entity> void renderLeash(Boat boat, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, E entity) {
        poseStack.pushPose();
        Vec3 vec3 = entity.getRopeHoldPosition(f);
        double d = Mth.lerp(f, boat.yRotO, boat.getYRot()) * (float) (Math.PI / 180.0) + (Math.PI / 2);
        Vec3 vec32 = boat.getLeashOffset(f);
        double e = Math.cos(d) * vec32.z + Math.sin(d) * vec32.x;
        double g = Math.sin(d) * vec32.z - Math.cos(d) * vec32.x;
        double h = Mth.lerp(f, boat.xo, boat.getX()) + e;
        double i = Mth.lerp(f, boat.yo, boat.getY()) + vec32.y;
        double j = Mth.lerp(f, boat.zo, boat.getZ()) + g;
        poseStack.translate(e, vec32.y, g);
        float k = (float)(vec3.x - h);
        float l = (float)(vec3.y - i);
        float m = (float)(vec3.z - j);
        float n = 0.025F;
        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.leash());
        Matrix4f matrix4f = poseStack.last().pose();
        float o = Mth.invSqrt(k * k + m * m) * 0.025F / 2.0F;
        float p = m * o;
        float q = k * o;
        BlockPos blockPos = BlockPos.containing(boat.getEyePosition(f));
        BlockPos blockPos2 = BlockPos.containing(entity.getEyePosition(f));
        int r = this.getBlockLightLevel(boat, blockPos);
        int s = this.entityRenderDispatcher.getRenderer(entity).getBlockLightLevel(entity, blockPos2);
        int t = boat.level().getBrightness(LightLayer.SKY, blockPos);
        int u = boat.level().getBrightness(LightLayer.SKY, blockPos2);

        for (int v = 0; v <= 24; v++) {
            addVertexPair(vertexConsumer, matrix4f, k, l, m, r, s, t, u, 0.025F, 0.025F, p, q, v, false);
        }

        for (int v = 24; v >= 0; v--) {
            addVertexPair(vertexConsumer, matrix4f, k, l, m, r, s, t, u, 0.025F, 0.0F, p, q, v, true);
        }

        poseStack.popPose();
    }

    @Unique
    private static void addVertexPair(
            VertexConsumer vertexConsumer,
            Matrix4f matrix4f,
            float f,
            float g,
            float h,
            int i,
            int j,
            int k,
            int l,
            float m,
            float n,
            float o,
            float p,
            int q,
            boolean bl
    ) {
        float r = q / 24.0F;
        int s = (int)Mth.lerp(r, i, j);
        int t = (int)Mth.lerp(r, k, l);
        int u = LightTexture.pack(s, t);
        float v = q % 2 == (bl ? 1 : 0) ? 0.7F : 1.0F;
        float w = 0.5F * v;
        float x = 0.4F * v;
        float y = 0.3F * v;
        float z = f * r;
        float aa = g > 0.0F ? g * r * r : g - g * (1.0F - r) * (1.0F - r);
        float ab = h * r;
        vertexConsumer.vertex(matrix4f, z - o, aa + n, ab + p).color(w, x, y, 1.0F).uv2(u).endVertex();
        vertexConsumer.vertex(matrix4f, z + o, aa + m - n, ab - p).color(w, x, y, 1.0F).uv2(u).endVertex();
    }
}

// 确保 船被拴的状态 能传到客户端
@Mixin(ClientPacketListener.class)
abstract class ClientPktListener {
    @Inject(method = "handleEntityLinkPacket", at = @At("TAIL"))
    public void handleEntityLinkPacket(ClientboundSetEntityLinkPacket clientboundSetEntityLinkPacket, CallbackInfo ci) {
        if (((ClientPacketListener)(Object)this).getLevel().getEntity(clientboundSetEntityLinkPacket.getSourceId()) instanceof ILeash leash) {
            leash.setDelayedLeashHolderId(clientboundSetEntityLinkPacket.getDestId());
        }
    }
}

// 让拴绳所拴的所有生物 一次性 拴到栅栏上
@Mixin(LeadItem.class)
abstract class LeadItemMixin extends Item {
    public LeadItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(
            method = "useOn",
            at = @At(
                    value = "INVOKE_ASSIGN",
                    target = "Lnet/minecraft/world/item/LeadItem;bindPlayerMobs(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/InteractionResult;"
            ),
            cancellable = true
    )
    public void useOn(UseOnContext useOnContext, CallbackInfoReturnable<InteractionResult> cir) {
        Level level = useOnContext.getLevel();
        BlockPos blockPos = useOnContext.getClickedPos();
        BlockState blockState = level.getBlockState(blockPos);
        Player player = useOnContext.getPlayer();
        LeashFenceKnotEntity leashFenceKnotEntity = null;
        boolean bl = false;
        int i = blockPos.getX();
        int j = blockPos.getY();
        int k = blockPos.getZ();

        for (ILeash mob : level.getEntitiesOfClass(
                        Entity.class, new AABB(i - 7.0, j - 7.0, k - 7.0, i + 7.0, j + 7.0, k + 7.0), entity -> entity instanceof ILeash leashable
                )
                .stream()
                .map(ILeash.class::cast)
                .toList()) {
            if (mob.getLeashHolder() == player) {
                if (leashFenceKnotEntity == null) {
                    leashFenceKnotEntity = LeashFenceKnotEntity.getOrCreateKnot(level, blockPos);
                    leashFenceKnotEntity.playPlacementSound();
                }

                mob.setLeashedTo(leashFenceKnotEntity, true);
                bl = true;
            }
        }

        if (bl) {
            level.gameEvent(GameEvent.BLOCK_ATTACH, blockPos, GameEvent.Context.of(player));
        }

        cir.setReturnValue(bl ? InteractionResult.SUCCESS : InteractionResult.PASS);
    }
}

// 用剪刀 shift+右键 一次性 解除所有所拴的生物 
@Mixin(Mob.class)
abstract class MobMixin extends LivingEntity {
    @Unique
    Mob self = (Mob)(Object)this;

    protected MobMixin(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Inject(method = "checkAndHandleImportantInteractions", at = @At("HEAD"), cancellable = true)
    public void checkAndHandleImportantInteractions(Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResult> cir) {
        ItemStack itemStack = player.getItemInHand(interactionHand);
        if (this.self.isAlive() && player.isSecondaryUseActive() && this.self.canBeLeashed(player)) {
            List<Mob> list = AreaLeash.leashableInArea(this.self, leashablex -> leashablex.getLeashHolder() == player);
            if (!list.isEmpty()) {
                for (Mob leashable2 : list) {
                    leashable2.setLeashedTo(this.self, true);
                }

                this.self.level().gameEvent(GameEvent.ENTITY_INTERACT, this.self.blockPosition(), GameEvent.Context.of(player));
                cir.setReturnValue(InteractionResult.sidedSuccess(this.level().isClientSide));
            }
        }

        if (itemStack.is(Items.SHEARS) && AreaLeash.shearOffAllLeashConnections(this.self, player)) {
            itemStack.hurtAndBreak(1, player, playerx -> playerx.broadcastBreakEvent(interactionHand));
            cir.setReturnValue(InteractionResult.SUCCESS);
        }
    }
}

@Mixin(LeashFenceKnotEntity.class)
abstract class LeashFenceKnotEntityMixin extends HangingEntity {
    protected LeashFenceKnotEntityMixin(EntityType<? extends HangingEntity> entityType, Level level) {
        super(entityType, level);
    }

    protected LeashFenceKnotEntityMixin(EntityType<? extends HangingEntity> entityType, Level level, BlockPos blockPos) {
        super(entityType, level, blockPos);
    }

    @Inject(method = "interact", at = @At(value = "RETURN", ordinal = 1))
    public void interact(Player player, InteractionHand interactionHand, CallbackInfoReturnable<InteractionResult> cir) {
        boolean bl = false;
        List<ILeash> list = this.level()
                .getEntitiesOfClass(
                        Entity.class,
                        new AABB(this.getX() - 7.0, this.getY() - 7.0, this.getZ() - 7.0, this.getX() + 7.0, this.getY() + 7.0, this.getZ() + 7.0),
                        entity -> entity instanceof ILeash leashable
                )
                .stream()
                .map(ILeash.class::cast)
                .toList();

        for (ILeash mob : list) {
            if (mob.getLeashHolder() == player) {
                mob.setLeashedTo(this, true);
                bl = true;
            }
        }

        boolean bl2 = false;
        if (!bl) {
            this.discard();
            if (player.getAbilities().instabuild) {
                for (ILeash iLeash : list) {
                    if (iLeash.isLeashed() && iLeash.getLeashHolder() == this) {
                        iLeash.removeLeash();
                        bl2 = true;
                    }
                }
            }
        }

        if (bl || bl2) {
            this.gameEvent(GameEvent.BLOCK_ATTACH, player);
        }
    }
}
