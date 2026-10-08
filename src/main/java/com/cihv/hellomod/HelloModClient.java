package com.cihv.hellomod;

import com.cihv.hellomod.entity.ModEntities;
import com.cihv.hellomod.entity.client.ModModelLayers;
import com.cihv.hellomod.entity.client.OriModel;
import com.cihv.hellomod.entity.client.OriRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class HelloModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ORI, OriModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.ORI, OriRenderer::new);
    }
}
