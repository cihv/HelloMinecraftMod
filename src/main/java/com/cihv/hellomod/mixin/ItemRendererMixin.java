package com.cihv.hellomod.mixin;

import com.cihv.hellomod.HelloMod;
import com.cihv.hellomod.item.ModItems;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {

    @ModifyVariable(method = "renderItem(Lnet/minecraft/item/ItemStack;" +
            "Lnet/minecraft/client/render/model/json/ModelTransformationMode;" +
            "ZLnet/minecraft/client/util/math/MatrixStack;" +
            "Lnet/minecraft/client/render/VertexConsumerProvider;" +
            "IILnet/minecraft/client/render/model/BakedModel;)V", at = @At(value = "HEAD"), argsOnly = true)
    BakedModel useTestStaffModel(BakedModel value, ItemStack stack, ModelTransformationMode renderMode,
                                 boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers,
                                 int light, int layer) {
        if (stack.isOf(ModItems.TEST_STAFF) && renderMode != ModelTransformationMode.GUI) {
            return ((ItemRendererAccessor) this)
                    .hellomod$getModels()
                    .getModelManager()
                    .getModel(new ModelIdentifier(HelloMod.MOD_ID, "test_staff_3d", "inventory")
                    );
        }
        return value;
    }
}
