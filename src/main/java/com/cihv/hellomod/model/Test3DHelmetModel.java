package com.cihv.hellomod.model;

import com.cihv.hellomod.HelloMod;
import com.cihv.hellomod.common.constant.AssetsPathConstant;
import com.cihv.hellomod.item.custom.Test3DHelmetItem;
import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;

public class Test3DHelmetModel extends GeoModel<Test3DHelmetItem> {
    @Override
    public Identifier getModelResource(Test3DHelmetItem animatable) {
        return new Identifier(HelloMod.MOD_ID, AssetsPathConstant.TEST_3D_HELMET_GEO_MODEL);
    }

    @Override
    public Identifier getTextureResource(Test3DHelmetItem animatable) {
        return new Identifier(HelloMod.MOD_ID, AssetsPathConstant.TEST_3D_HELMET_GEO_TEXTURE);
    }

    @Override
    public Identifier getAnimationResource(Test3DHelmetItem animatable) {
        return new Identifier(HelloMod.MOD_ID, AssetsPathConstant.TEST_3D_HELMET_GEO_ANIMATION);
    }
}
