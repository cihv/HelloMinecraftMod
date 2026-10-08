package com.cihv.hellomod.mixin;

import com.cihv.hellomod.HelloMod;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Map;

@Mixin(ModelLoader.class)
public abstract class ModelLoaderMixin {

    @Shadow
    protected abstract void addModel(ModelIdentifier modelId);

    @Inject(method = "<init>", at = @At("TAIL"))
    void addTestStaff(BlockColors blockColors, Profiler profiler,
                      Map<Identifier, JsonUnbakedModel> jsonUnbakedModels,
                      Map<Identifier, List<ModelLoader.SourceTrackedData>> blockStates,
                      CallbackInfo callbackInfo) {
        this.addModel(new ModelIdentifier(HelloMod.MOD_ID, "test_staff_3d", "inventory"));
    }
}
