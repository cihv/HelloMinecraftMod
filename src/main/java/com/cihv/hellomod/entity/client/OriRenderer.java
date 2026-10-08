package com.cihv.hellomod.entity.client;

import com.cihv.hellomod.HelloMod;
import com.cihv.hellomod.common.constant.AssetsPathConstant;
import com.cihv.hellomod.entity.custom.OriEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class OriRenderer extends MobEntityRenderer<OriEntity, OriModel<OriEntity>> {
    public static final Identifier TEXTURE = new Identifier(HelloMod.MOD_ID, AssetsPathConstant.ORI_ENTITY_TEXTURE);

    public OriRenderer(EntityRendererFactory.Context context) {
        super(context, new OriModel<>(context.getPart(ModModelLayers.ORI)), 0.6f);
    }

    @Override
    public void render(OriEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if(mobEntity.isBaby()){
            matrixStack.scale(1f, 1f, 1f);
        }else {
            matrixStack.scale(2f, 2f, 2f);
        }
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public Identifier getTexture(OriEntity entity) {
        return TEXTURE;
    }
}
