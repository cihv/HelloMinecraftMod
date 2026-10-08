package com.cihv.hellomod.entity;

import com.cihv.hellomod.HelloMod;
import com.cihv.hellomod.entity.custom.OriEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEntities {
    public static final EntityType<OriEntity> ORI = registerEntity("ori", SpawnGroup.CREATURE,
            OriEntity::new, 1f, 2f);


    private static <T extends Entity> EntityType<T> registerEntity(String name, SpawnGroup spawnGroup,
                                                                   EntityType.EntityFactory<T> factory,
                                                                   float width, float height) {
        return Registry.register(Registries.ENTITY_TYPE, HelloMod.id(name),
                FabricEntityTypeBuilder.create(spawnGroup, factory)
                        .dimensions(EntityDimensions.fixed(width, height)).build());
    }
    public static void registerModEntities() {
        HelloMod.LOGGER.info("正在注册 " + HelloMod.MOD_ID + " 模组的自定义生物..");
        FabricDefaultAttributeRegistry.register(ORI, OriEntity.createOriAttributes());
    }
}
