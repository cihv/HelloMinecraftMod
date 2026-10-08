package com.cihv.hellomod.client.renderer;

import com.cihv.hellomod.common.constant.GeoBoneConstant;
import com.cihv.hellomod.item.custom.Test3DHelmetItem;
import com.cihv.hellomod.model.Test3DHelmetModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class Test3DHelmetRenderer extends GeoArmorRenderer<Test3DHelmetItem> {
    public Test3DHelmetRenderer() {
        super(new Test3DHelmetModel());
    }

    //重写父类的getHeadBone，返回父级头骨
    @Override
    public GeoBone getHeadBone() {
        return this.model.getBone(GeoBoneConstant.TEST_3D_HELMET_GEO_HEADBONE).orElse(null);
    }
}
