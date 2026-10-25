package com.cihv.leadopt.api;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.game.ClientboundSetEntityLinkPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.decoration.HangingEntity;
import net.minecraft.world.entity.decoration.LeashFenceKnotEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public interface ILeash {
   static List<ILeash> leashableLeashedTo(Entity entity) {
      return leashableInArea(entity, leashable -> leashable.getLeashHolder() == entity);
   }

   static List<ILeash> leashableInArea(Entity entity, Predicate<ILeash> predicate) {
      return leashableInArea(entity.level(), entity.getBoundingBox().getCenter(), predicate);
   }

   static List<ILeash> leashableInArea(Level level, Vec3 vec3, Predicate<ILeash> predicate) {
      AABB aABB = AABB.ofSize(vec3, 32.0, 32.0, 32.0);
      return level.getEntitiesOfClass(Entity.class, aABB, entity -> entity instanceof ILeash leashable && predicate.test(leashable))
         .stream()
         .map(ILeash.class::cast)
         .toList();
   }

   default boolean shearOffAllLeashConnections(@Nullable Player player) {
      boolean bl = this.dropAllLeashConnections(player);
      if (bl && ((Entity)this).level() instanceof ServerLevel serverLevel) {
         serverLevel.playSound(
            null, ((Entity)this).blockPosition(), SoundEvents.SHEEP_SHEAR, player != null ? player.getSoundSource() : ((Entity)this).getSoundSource()
         );
      }

      return bl;
   }

   private boolean dropAllLeashConnections(@Nullable Player player) {
      List<ILeash> list = leashableLeashedTo((Entity)this);
      boolean bl = !list.isEmpty();
      if (this.isLeashed()) {
         this.dropLeash();
         bl = true;
      }

      for (ILeash leashable2 : list) {
         leashable2.dropLeash();
      }

      if (bl) {
         ((Entity)this).gameEvent(GameEvent.SHEAR, player);
         return true;
      } else {
         return false;
      }
   }

   default boolean startRidingV(boolean bl2, Entity entity, boolean bl) {
      if (bl2 && this.isLeashed()) {
         this.dropLeash();
      }

      return bl2;
   }

   @Nullable
   Entity getLeashHolder();

   CompoundTag getLeashInfoTag();

   void setLeashHolder(Entity var1);

   void setLeashInfoTag(CompoundTag var1);

   void setDelayedLeashHolderId(int var1);

   default boolean canBeLeashed(Player player) {
      return !this.isLeashed();
   }

   default boolean isLeashed() {
      return this.getLeashHolder() != null;
   }

   default void setLeashedTo(Entity entity, boolean bl) {
      this.setLeashHolder(entity);
      this.setLeashInfoTag(null);
      if (!((Entity)this).level().isClientSide && bl && ((Entity)this).level() instanceof ServerLevel) {
         ((ServerLevel)((Entity)this).level())
            .getChunkSource()
            .broadcast((Entity)this, new ClientboundSetEntityLinkPacket((Entity)this, this.getLeashHolder()));
      }

      if (((Entity)this).isPassenger()) {
         ((Entity)this).stopRiding();
      }
   }

   default void dropLeash() {
      this.dropLeash(true, true);
   }

   default void removeLeash() {
      this.dropLeash(true, false);
   }

   default void dropLeash(boolean bl, boolean bl2) {
      if (this.getLeashHolder() != null) {
         this.setLeashHolder(null);
         this.setLeashInfoTag(null);
         if (!((Entity)this).level().isClientSide && bl2) {
            ((Entity)this).spawnAtLocation(Items.LEAD);
         }

         if (!((Entity)this).level().isClientSide && bl && ((Entity)this).level() instanceof ServerLevel) {
            ((ServerLevel)((Entity)this).level()).getChunkSource().broadcast((Entity)this, new ClientboundSetEntityLinkPacket((Entity)this, null));
         }
      }
   }

   default void tickLeash() {
      if (this.getLeashInfoTag() != null) {
         this.restoreLeashFromSave();
      }

      if (this.getLeashHolder() != null && (!((Entity)this).isAlive() || !this.getLeashHolder().isAlive())) {
         this.dropLeash();
      }
   }

   private void restoreLeashFromSave() {
      if (this.getLeashInfoTag() != null && ((Entity)this).level() instanceof ServerLevel) {
         if (this.getLeashInfoTag().hasUUID("UUID")) {
            UUID uUID = this.getLeashInfoTag().getUUID("UUID");
            Entity entity = ((ServerLevel)((Entity)this).level()).getEntity(uUID);
            if (entity != null) {
               this.setLeashedTo(entity, true);
               return;
            }
         } else if (this.getLeashInfoTag().contains("X", 99)
            && this.getLeashInfoTag().contains("Y", 99)
            && this.getLeashInfoTag().contains("Z", 99)) {
            BlockPos blockPos = NbtUtils.readBlockPos(this.getLeashInfoTag());
            this.setLeashedTo(LeashFenceKnotEntity.getOrCreateKnot(((Entity)this).level(), blockPos), true);
            return;
         }

         if (((Entity)this).tickCount > 100) {
            ((Entity)this).spawnAtLocation(Items.LEAD);
            this.setLeashInfoTag(null);
         }
      }
   }

   default void saveData(CompoundTag compoundTag) {
      if (this.getLeashHolder() != null) {
         CompoundTag compoundTag3 = new CompoundTag();
         if (this.getLeashHolder() instanceof LivingEntity) {
            UUID uUID = this.getLeashHolder().getUUID();
            compoundTag3.putUUID("UUID", uUID);
         } else if (this.getLeashHolder() instanceof HangingEntity) {
            BlockPos blockPos = ((HangingEntity)this.getLeashHolder()).getPos();
            compoundTag3.putInt("X", blockPos.getX());
            compoundTag3.putInt("Y", blockPos.getY());
            compoundTag3.putInt("Z", blockPos.getZ());
         }

         compoundTag.put("ILeash", compoundTag3);
      } else if (this.getLeashInfoTag() != null) {
         compoundTag.put("ILeash", this.getLeashInfoTag().copy());
      }
   }

   default void loadData(CompoundTag compoundTag) {
      if (compoundTag.contains("ILeash", 10)) {
         this.setLeashInfoTag(compoundTag.getCompound("ILeash"));
      }
   }
}
