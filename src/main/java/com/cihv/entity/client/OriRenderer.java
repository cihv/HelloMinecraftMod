package com.cihv.entity.client;

import com.cihv.Ori;
import com.cihv.constant.AssertsConstant;
import com.cihv.entity.custom.OriEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class OriRenderer extends MobEntityRenderer<OriEntity, OriModel<OriEntity>> {
    public static final Identifier TEXTURE = Ori.id(AssertsConstant.ORI_TEXTURE_PATH);

    public OriRenderer(EntityRendererFactory.Context context) {
        super(context, new OriModel<>(context.getPart(ModModelLayers.ORI)), 0.5f);
    }

    @Override
    public Identifier getTexture(OriEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(OriEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (mobEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
