package com.cihv;

import com.cihv.entity.ModEntities;
import com.cihv.entity.client.ModModelLayers;
import com.cihv.entity.client.OriModel;
import com.cihv.entity.client.OriRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class OriClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        //注册实体和图层
        EntityRendererRegistry.register(ModEntities.ORI, OriRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.ORI, OriModel::getTexturedModelData);
    }
}
