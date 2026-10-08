package com.cihv.hellomod.entity.client;

import com.cihv.hellomod.entity.animation.ModAnimations;
import com.cihv.hellomod.entity.custom.OriEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.MathHelper;

public class OriModel<T extends OriEntity> extends SinglePartEntityModel<T> {
	private final ModelPart ori;
	private final ModelPart body;
	private final ModelPart torso;
	private final ModelPart head;
	private final ModelPart ear;
	private final ModelPart left_ear;
	private final ModelPart right_ear;
	private final ModelPart tail;
	private final ModelPart right_arm;
	private final ModelPart right_leg;
	private final ModelPart left_arm;
	private final ModelPart left_leg;
	public OriModel(ModelPart root) {
		this.ori = root.getChild("ori");
		this.body = this.ori.getChild("body");
		this.torso = this.body.getChild("torso");
		this.head = this.torso.getChild("head");
		this.ear = this.head.getChild("ear");
		this.left_ear = this.ear.getChild("left_ear");
		this.right_ear = this.ear.getChild("right_ear");
		this.tail = this.torso.getChild("tail");
		this.right_arm = this.body.getChild("right_arm");
		this.right_leg = this.body.getChild("right_leg");
		this.left_arm = this.body.getChild("left_arm");
		this.left_leg = this.body.getChild("left_leg");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData ori = modelPartData.addChild("ori", ModelPartBuilder.create(), ModelTransform.pivot(-15.0F, 9.0F, 0.0F));

		ModelPartData body = ori.addChild("body", ModelPartBuilder.create(), ModelTransform.of(15.0F, -1.0F, 1.0F, 0.0F, 1.5708F, 0.0F));

		ModelPartData torso = body.addChild("torso", ModelPartBuilder.create().uv(15, 23).cuboid(14.0F, -6.0F, -1.0F, 2.0F, 7.0F, 3.0F, new Dilation(0.0F))
		.uv(26, 23).cuboid(14.0F, 1.0F, -2.0F, 2.0F, 1.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-15.0F, 1.0F, -1.0F));

		ModelPartData head = torso.addChild("head", ModelPartBuilder.create().uv(0, 3).cuboid(-2.0F, -5.0F, -2.25F, 4.0F, 5.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(15.0F, -6.0F, 0.25F));

		ModelPartData ear = head.addChild("ear", ModelPartBuilder.create(), ModelTransform.pivot(-15.0F, 6.0F, -0.25F));

		ModelPartData left_ear = ear.addChild("left_ear", ModelPartBuilder.create(), ModelTransform.pivot(15.0F, -9.0F, 2.0F));

		ModelPartData cube_r1 = left_ear.addChild("cube_r1", ModelPartBuilder.create().uv(41, 21).cuboid(0.0F, -4.0F, -1.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -4.0F, 5.0F, 0.1704F, -0.0326F, -3.1014F));

		ModelPartData cube_r2 = left_ear.addChild("cube_r2", ModelPartBuilder.create().uv(14, 34).cuboid(0.0F, -6.0F, -1.0F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 1.0F, 1.0F, -2.5307F, 0.0F, 3.1416F));

		ModelPartData cube_r3 = left_ear.addChild("cube_r3", ModelPartBuilder.create().uv(39, 0).cuboid(0.0F, -5.0F, -1.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 2.0F, 0.0F, -2.2253F, 0.0F, 3.1416F));

		ModelPartData cube_r4 = left_ear.addChild("cube_r4", ModelPartBuilder.create().uv(9, 41).cuboid(-1.9981F, -1.9469F, 0.0692F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -2.0F, 1.0F, -2.4871F, 0.0436F, 3.1416F));

		ModelPartData cube_r5 = left_ear.addChild("cube_r5", ModelPartBuilder.create().uv(19, 41).cuboid(-1.9981F, -2.9924F, 0.0869F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -3.0F, 2.0F, -3.0543F, 0.0436F, 3.1416F));

		ModelPartData cube_r6 = left_ear.addChild("cube_r6", ModelPartBuilder.create().uv(35, 30).cuboid(-1.9981F, -2.9702F, 0.082F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -3.0F, 1.0F, -2.7925F, 0.0436F, 3.1416F));

		ModelPartData cube_r7 = left_ear.addChild("cube_r7", ModelPartBuilder.create().uv(45, 29).cuboid(1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, -5.0F, 2.0F, 0.5236F, 0.0F, 0.0F));

		ModelPartData right_ear = ear.addChild("right_ear", ModelPartBuilder.create(), ModelTransform.pivot(15.0F, -9.0F, -1.0F));

		ModelPartData cube_r8 = right_ear.addChild("cube_r8", ModelPartBuilder.create().uv(34, 17).cuboid(0.0F, -4.0F, -1.0F, 1.0F, 4.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.0F, -5.0F, -2.9234F, 0.0F, 0.0F));

		ModelPartData cube_r9 = right_ear.addChild("cube_r9", ModelPartBuilder.create().uv(34, 9).cuboid(0.0F, -6.0F, -1.0F, 1.0F, 6.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, -1.0F, 0.5672F, 0.0F, 0.0F));

		ModelPartData cube_r10 = right_ear.addChild("cube_r10", ModelPartBuilder.create().uv(35, 37).cuboid(0.0F, -5.0F, -1.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, 0.0F, 0.8727F, 0.0F, 0.0F));

		ModelPartData cube_r11 = right_ear.addChild("cube_r11", ModelPartBuilder.create().uv(19, 34).cuboid(0.0F, -3.0F, 0.0F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.0F, -1.0F, 0.3491F, 0.0F, 0.0F));

		ModelPartData cube_r12 = right_ear.addChild("cube_r12", ModelPartBuilder.create().uv(40, 40).cuboid(0.0F, -3.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -3.0F, -2.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData cube_r13 = right_ear.addChild("cube_r13", ModelPartBuilder.create().uv(40, 35).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, -1.0F, 0.6545F, 0.0F, 0.0F));

		ModelPartData cube_r14 = right_ear.addChild("cube_r14", ModelPartBuilder.create().uv(24, 45).cuboid(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, -6.0F, -2.0F, -2.9234F, 0.0F, 0.0F));

		ModelPartData tail = torso.addChild("tail", ModelPartBuilder.create().uv(39, 13).cuboid(-14.0F, 4.0F, 0.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(14.0F, 1.0F, 0.0F));

		ModelPartData cube_r15 = tail.addChild("cube_r15", ModelPartBuilder.create().uv(24, 41).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-14.0F, 5.0F, 0.0F, 0.0F, 0.0F, -0.9599F));

		ModelPartData cube_r16 = tail.addChild("cube_r16", ModelPartBuilder.create().uv(0, 0).cuboid(-12.0F, -1.0F, -1.0F, 14.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 1.0F, 1.0F, 0.0F, 0.0F, -0.3491F));

		ModelPartData right_arm = body.addChild("right_arm", ModelPartBuilder.create(), ModelTransform.pivot(1.0F, -3.0F, -2.0F));

		ModelPartData cube_r17 = right_arm.addChild("cube_r17", ModelPartBuilder.create().uv(0, 43).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 9.0F, -4.0F, 2.4837F, -0.1897F, -0.0225F));

		ModelPartData cube_r18 = right_arm.addChild("cube_r18", ModelPartBuilder.create().uv(14, 42).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 11.0F, -5.0F, 0.8235F, 1.3555F, 0.8804F));

		ModelPartData cube_r19 = right_arm.addChild("cube_r19", ModelPartBuilder.create().uv(29, 41).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 11.0F, -6.0F, -0.5373F, -0.1816F, 0.0255F));

		ModelPartData cube_r20 = right_arm.addChild("cube_r20", ModelPartBuilder.create().uv(44, 0).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 10.0F, -5.0F, 1.4564F, -0.6661F, 0.3127F));

		ModelPartData cube_r21 = right_arm.addChild("cube_r21", ModelPartBuilder.create().uv(41, 26).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(3.0F, 11.0F, -5.0F, -0.0345F, -0.2673F, -0.0846F));

		ModelPartData cube_r22 = right_arm.addChild("cube_r22", ModelPartBuilder.create().uv(39, 16).cuboid(0.0F, -4.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 5.0F, -7.0F, -0.1992F, -0.417F, -0.4842F));

		ModelPartData cube_r23 = right_arm.addChild("cube_r23", ModelPartBuilder.create().uv(19, 3).cuboid(0.0F, -3.0F, -5.0F, 1.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 5.0F, -4.0F, 1.7101F, -0.2214F, -0.3525F));

		ModelPartData cube_r24 = right_arm.addChild("cube_r24", ModelPartBuilder.create().uv(0, 14).cuboid(0.0F, -2.0F, -6.0F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 2.0F, 0.0F, 0.2231F, -0.1639F, -0.0602F));

		ModelPartData right_leg = body.addChild("right_leg", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 2.0F, -2.0F));

		ModelPartData cube_r25 = right_leg.addChild("cube_r25", ModelPartBuilder.create().uv(39, 7).cuboid(-1.0F, -2.0F, 0.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 15.0F, -2.0F, 0.0F, 0.0F, 0.3491F));

		ModelPartData cube_r26 = right_leg.addChild("cube_r26", ModelPartBuilder.create().uv(9, 32).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 9.0F, -2.0F, 0.0F, 0.0F, 0.3491F));

		ModelPartData cube_r27 = right_leg.addChild("cube_r27", ModelPartBuilder.create().uv(26, 30).cuboid(-2.0F, -3.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 3.0F, -1.0F, 0.0F, 0.0F, -0.3491F));

		ModelPartData left_arm = body.addChild("left_arm", ModelPartBuilder.create(), ModelTransform.of(1.0F, -3.0F, 1.0F, 0.0F, 0.2182F, 0.0F));

		ModelPartData cube_r28 = left_arm.addChild("cube_r28", ModelPartBuilder.create().uv(44, 3).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 11.0F, 5.0F, -0.4343F, 0.0265F, 0.0258F));

		ModelPartData cube_r29 = left_arm.addChild("cube_r29", ModelPartBuilder.create().uv(44, 16).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 10.0F, 6.0F, 1.1914F, -0.2334F, 0.4924F));

		ModelPartData cube_r30 = left_arm.addChild("cube_r30", ModelPartBuilder.create().uv(29, 44).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 8.0F, 5.0F, -2.4666F, 0.0649F, 0.1966F));

		ModelPartData cube_r31 = left_arm.addChild("cube_r31", ModelPartBuilder.create().uv(34, 44).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 10.0F, 6.0F, 1.7895F, 0.5445F, 1.7843F));

		ModelPartData cube_r32 = left_arm.addChild("cube_r32", ModelPartBuilder.create().uv(14, 45).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 9.0F, 6.0F, 2.1F, 0.1213F, 0.0522F));

		ModelPartData cube_r33 = left_arm.addChild("cube_r33", ModelPartBuilder.create().uv(40, 30).cuboid(0.0F, -4.0F, 0.0F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 6.0F, 6.0F, 0.24F, -0.3989F, -0.5191F));

		ModelPartData cube_r34 = left_arm.addChild("cube_r34", ModelPartBuilder.create().uv(0, 23).cuboid(0.0F, -3.0F, -5.0F, 1.0F, 2.0F, 6.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 6.0F, 8.0F, 1.3673F, -0.1967F, -0.2812F));

		ModelPartData cube_r35 = left_arm.addChild("cube_r35", ModelPartBuilder.create().uv(17, 14).cuboid(0.0F, -2.0F, -8.0F, 1.0F, 1.0F, 7.0F, new Dilation(0.0F)), ModelTransform.of(-2.0F, 4.0F, 7.0F, -0.2569F, -0.1639F, -0.0602F));

		ModelPartData left_leg = body.addChild("left_leg", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 2.0F, 1.0F));

		ModelPartData cube_r36 = left_leg.addChild("cube_r36", ModelPartBuilder.create().uv(39, 10).cuboid(-1.0F, -2.0F, 0.0F, 2.0F, 1.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 15.0F, 1.0F, 0.0F, 0.0F, 0.3491F));

		ModelPartData cube_r37 = left_leg.addChild("cube_r37", ModelPartBuilder.create().uv(34, 0).cuboid(0.0F, -2.0F, 0.0F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 9.0F, 1.0F, 0.0F, 0.0F, 0.3491F));

		ModelPartData cube_r38 = left_leg.addChild("cube_r38", ModelPartBuilder.create().uv(0, 32).cuboid(-2.0F, -3.0F, -1.0F, 2.0F, 8.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(2.0F, 3.0F, 1.0F, 0.0F, 0.0F, -0.3491F));
		return TexturedModelData.of(modelData, 64, 64);
	}
	@Override
	public void setAngles(OriEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		setHeadAngles(netHeadYaw, headPitch);
		if (entity.isSitting()) {
			this.updateAnimation(entity.sitAnimationState, ModAnimations.ORI_SIT, ageInTicks, 1f);
			return;
		}
		if(entity.isDancing()){
			this.updateAnimation(entity.danceAnimationState, ModAnimations.ORI_DANCE, ageInTicks, 1f);
			return;
		}
		if (entity.isMoving(entity.getVelocity().horizontalLengthSquared())) {
			this.animateMovement(ModAnimations.ORI_WALK, limbSwing, limbSwingAmount, 2f, 2f);
		} else {
			this.updateAnimation(entity.idleAnimationState, ModAnimations.ORI_IDLE, ageInTicks, 1f);
		}
	}
	private void setHeadAngles(float headYaw, float headPitch){
		headYaw = MathHelper.clamp(headYaw, -10.0f, 10.0f);
		headPitch = MathHelper.clamp(headPitch, -10.0f, 10.0f);
		this.head.yaw = headYaw * 0.01745f;
		this.head.pitch = headPitch * 0.01745f;
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		ori.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return ori;
	}
}