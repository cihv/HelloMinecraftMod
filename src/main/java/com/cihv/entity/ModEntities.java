package com.cihv.entity;

import com.cihv.Ori;
import com.cihv.entity.custom.OriEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<OriEntity> ORI =
            Registry.register(Registries.ENTITY_TYPE, new Identifier(Ori.MOD_ID, "ori"), FabricEntityTypeBuilder
                    .create(SpawnGroup.CREATURE, OriEntity::new)
                    .dimensions(EntityDimensions.fixed(1f, 1f))
                    .build());
}
