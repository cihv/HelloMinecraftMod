package com.cihv.entity.client;

import com.cihv.entity.animation.ModAnimations;
import com.cihv.entity.custom.OriEntity;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;

public class OriModel<T extends OriEntity> extends SinglePartEntityModel<T> {
	private final ModelPart root;
	private final ModelPart hips;
	private final ModelPart spine;
	private final ModelPart chest;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart ear_small_right1;
	private final ModelPart ear_small_right2;
	private final ModelPart ear_leaf_right1;
	private final ModelPart ear_leaf_right2;
	private final ModelPart ear_small_left1;
	private final ModelPart ear_small_left2;
	private final ModelPart ear_leaf_left1;
	private final ModelPart ear_leaf_left2;
	private final ModelPart arm_upper_right;
	private final ModelPart arm_lower_right;
	private final ModelPart hand_right;
	private final ModelPart arm_upper_left;
	private final ModelPart arm_lower_left;
	private final ModelPart hand_left;
	private final ModelPart tail1;
	private final ModelPart tail2;
	private final ModelPart tail3;
	private final ModelPart tail4;
	private final ModelPart tail5;
	private final ModelPart leg_upper_right;
	private final ModelPart leg_lower_right;
	private final ModelPart foot_right;
	private final ModelPart leg_upper_left;
	private final ModelPart leg_lower_left;
	private final ModelPart foot_left;
	public OriModel(ModelPart root) {
		this.root = root.getChild("root");
		this.hips = this.root.getChild("hips");
		this.spine = this.hips.getChild("spine");
		this.chest = this.spine.getChild("chest");
		this.neck = this.chest.getChild("neck");
		this.head = this.neck.getChild("head");
		this.ear_small_right1 = this.head.getChild("ear_small_right1");
		this.ear_small_right2 = this.ear_small_right1.getChild("ear_small_right2");
		this.ear_leaf_right1 = this.head.getChild("ear_leaf_right1");
		this.ear_leaf_right2 = this.ear_leaf_right1.getChild("ear_leaf_right2");
		this.ear_small_left1 = this.head.getChild("ear_small_left1");
		this.ear_small_left2 = this.ear_small_left1.getChild("ear_small_left2");
		this.ear_leaf_left1 = this.head.getChild("ear_leaf_left1");
		this.ear_leaf_left2 = this.ear_leaf_left1.getChild("ear_leaf_left2");
		this.arm_upper_right = this.chest.getChild("arm_upper_right");
		this.arm_lower_right = this.arm_upper_right.getChild("arm_lower_right");
		this.hand_right = this.arm_lower_right.getChild("hand_right");
		this.arm_upper_left = this.chest.getChild("arm_upper_left");
		this.arm_lower_left = this.arm_upper_left.getChild("arm_lower_left");
		this.hand_left = this.arm_lower_left.getChild("hand_left");
		this.tail1 = this.hips.getChild("tail1");
		this.tail2 = this.tail1.getChild("tail2");
		this.tail3 = this.tail2.getChild("tail3");
		this.tail4 = this.tail3.getChild("tail4");
		this.tail5 = this.tail4.getChild("tail5");
		this.leg_upper_right = this.hips.getChild("leg_upper_right");
		this.leg_lower_right = this.leg_upper_right.getChild("leg_lower_right");
		this.foot_right = this.leg_lower_right.getChild("foot_right");
		this.leg_upper_left = this.hips.getChild("leg_upper_left");
		this.leg_lower_left = this.leg_upper_left.getChild("leg_lower_left");
		this.foot_left = this.leg_lower_left.getChild("foot_left");
	}
	public static TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 192.0F, 0.0F));

		ModelPartData hips = root.addChild("hips", ModelPartBuilder.create().uv(0, 334).cuboid(-9.6F, -15.2F, -11.2F, 19.2F, 17.6F, 22.4F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -92.0F, 0.0F));

		ModelPartData spine = hips.addChild("spine", ModelPartBuilder.create().uv(333, 288).cuboid(-11.2F, -16.8F, -12.0F, 22.4F, 16.8F, 24.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -15.2F, 0.0F));

		ModelPartData chest = spine.addChild("chest", ModelPartBuilder.create().uv(0, 235).cuboid(-14.4F, -17.6F, -14.4F, 28.8F, 22.4F, 28.8F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -20.8F, 0.0F));

		ModelPartData neck = chest.addChild("neck", ModelPartBuilder.create().uv(413, 376).cuboid(-7.2F, -12.0F, -7.2F, 14.4F, 16.0F, 14.4F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -20.8F, 0.0F));

		ModelPartData head = neck.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-28.8F, -44.8F, -28.0F, 57.6F, 44.8F, 54.4F, new Dilation(0.0F))
		.uv(191, 178).cuboid(-23.2F, -52.8F, -23.2F, 46.4F, 9.6F, 44.8F, new Dilation(0.0F))
		.uv(0, 178).cuboid(-23.2F, -3.2F, -24.0F, 46.4F, 7.2F, 47.2F, new Dilation(0.0F))
		.uv(368, 235).cuboid(-24.8F, -43.2F, 24.8F, 49.6F, 40.0F, 5.6F, new Dilation(0.0F))
		.uv(286, 470).cuboid(-3.2F, -17.6F, -32.0F, 8.0F, 8.0F, 4.4F, new Dilation(0.0F))
		.uv(453, 470).mirrored().cuboid(-4.8F, -17.6F, -32.0F, 8.0F, 8.0F, 4.4F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, -9.6F, 0.0F));

		ModelPartData eye_top_left_r1 = head.addChild("eye_top_left_r1", ModelPartBuilder.create().uv(462, 443).mirrored().cuboid(-7.2F, -12.0F, -1.12F, 14.4F, 16.0F, 2.16F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(15.2F, -26.4F, -28.8F, 0.0F, 0.0F, 0.4538F));

		ModelPartData eye_top_right_r1 = head.addChild("eye_top_right_r1", ModelPartBuilder.create().uv(425, 443).cuboid(-7.2F, -12.0F, -1.12F, 14.4F, 16.0F, 2.16F, new Dilation(0.0F)), ModelTransform.of(-15.2F, -26.4F, -28.8F, 0.0F, 0.0F, -0.4538F));

		ModelPartData ear_small_right1 = head.addChild("ear_small_right1", ModelPartBuilder.create(), ModelTransform.of(-11.2F, -49.6F, 4.8F, -0.1745F, 0.0F, -0.1047F));

		ModelPartData ear_small_right1_seg_r1 = ear_small_right1.addChild("ear_small_right1_seg_r1", ModelPartBuilder.create().uv(135, 443).cuboid(-6.0F, -14.4F, -5.2F, 16.0F, 14.4F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

		ModelPartData ear_small_right2 = ear_small_right1.addChild("ear_small_right2", ModelPartBuilder.create(), ModelTransform.of(0.0F, -14.4F, 0.0F, -0.0698F, 0.0F, -0.0524F));

		ModelPartData ear_small_right2_seg_r1 = ear_small_right2.addChild("ear_small_right2_seg_r1", ModelPartBuilder.create().uv(200, 376).cuboid(-3.6F, -24.0F, -14.32F, 8.0F, 24.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 8.0F, 8.0F, -0.5197F, -0.1035F, -0.2455F));

		ModelPartData ear_small_right2_seg_r2 = ear_small_right2.addChild("ear_small_right2_seg_r2", ModelPartBuilder.create().uv(167, 376).cuboid(-3.6F, -24.0F, -14.32F, 8.0F, 24.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 8.0F, 8.0F, -0.5706F, -0.0051F, -0.0497F));

		ModelPartData ear_small_right2_seg_r3 = ear_small_right2.addChild("ear_small_right2_seg_r3", ModelPartBuilder.create().uv(53, 376).cuboid(-3.6F, -24.0F, -14.32F, 8.0F, 24.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 16.0F, 8.0F, -0.6545F, 0.0F, 0.0F));

		ModelPartData ear_leaf_right1 = head.addChild("ear_leaf_right1", ModelPartBuilder.create().uv(212, 101).cuboid(-8.0F, -53.2F, -3.2F, 16.0F, 56.0F, 6.4F, new Dilation(0.0F))
		.uv(378, 334).cuboid(10.0F, -44.8F, -4.0F, 8.0F, 32.0F, 1.12F, new Dilation(0.0F))
		.uv(249, 288).cuboid(-18.0F, -52.8F, -4.0F, 8.0F, 40.0F, 1.12F, new Dilation(0.0F))
		.uv(270, 288).cuboid(6.0F, -52.8F, -4.0F, 8.0F, 40.0F, 1.12F, new Dilation(0.0F)), ModelTransform.of(-27.2F, -21.6F, 4.8F, -1.3579F, 0.7589F, -1.6896F));

		ModelPartData ear_leaf_rim_bot_right_r1 = ear_leaf_right1.addChild("ear_leaf_rim_bot_right_r1", ModelPartBuilder.create().uv(341, 334).cuboid(-16.0F, -32.0F, 0.0F, 16.0F, 32.0F, 1.12F, new Dilation(0.0F)), ModelTransform.of(11.2F, -10.8F, -4.0F, 0.0F, 0.0F, -0.6545F));

		ModelPartData ear_leaf_rim_top_right_r1 = ear_leaf_right1.addChild("ear_leaf_rim_top_right_r1", ModelPartBuilder.create().uv(86, 376).cuboid(-24.0F, -24.0F, 0.0F, 32.0F, 24.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(11.2F, -20.8F, -4.0F, 0.0F, 0.0F, -0.1745F));

		ModelPartData ear_leaf_mid_right_r1 = ear_leaf_right1.addChild("ear_leaf_mid_right_r1", ModelPartBuilder.create().uv(261, 101).cuboid(-16.0F, -56.0F, 0.0F, 16.0F, 56.0F, 6.4F, new Dilation(0.0F)), ModelTransform.of(4.0F, -18.4F, -3.04F, -0.0774F, -0.0403F, -0.5657F));

		ModelPartData ear_leaf_mid_right_r2 = ear_leaf_right1.addChild("ear_leaf_mid_right_r2", ModelPartBuilder.create().uv(82, 101).cuboid(-24.0F, -64.0F, 0.0F, 24.0F, 64.0F, 6.4F, new Dilation(0.0F)), ModelTransform.of(12.0F, -10.4F, -3.04F, -0.0873F, 0.0F, -0.0873F));

		ModelPartData ear_leaf_right2 = ear_leaf_right1.addChild("ear_leaf_right2", ModelPartBuilder.create(), ModelTransform.of(0.0F, -20.0F, 0.0F, -0.1745F, 0.0F, -0.1047F));

		ModelPartData ear_leaf_point_right_r1 = ear_leaf_right2.addChild("ear_leaf_point_right_r1", ModelPartBuilder.create().uv(434, 0).cuboid(-12.8F, -72.0F, 0.0F, 8.0F, 72.0F, 3.2F, new Dilation(0.0F)), ModelTransform.of(2.4F, -24.0F, -1.6F, 0.0F, 0.0F, -0.4363F));

		ModelPartData ear_leaf_point_right_r2 = ear_leaf_right2.addChild("ear_leaf_point_right_r2", ModelPartBuilder.create().uv(377, 0).cuboid(-12.8F, -72.0F, 0.0F, 24.0F, 72.0F, 3.2F, new Dilation(0.0F)), ModelTransform.of(2.4F, -16.0F, -1.6F, 0.0F, 0.0F, -0.4363F));

		ModelPartData ear_leaf_tip_right_r1 = ear_leaf_right2.addChild("ear_leaf_tip_right_r1", ModelPartBuilder.create().uv(227, 0).cuboid(-20.8F, -72.0F, 0.0F, 32.0F, 72.0F, 4.8F, new Dilation(0.0F)), ModelTransform.of(6.4F, -1.6F, -2.4F, 0.0F, 0.0F, -0.3491F));

		ModelPartData ear_small_left1 = head.addChild("ear_small_left1", ModelPartBuilder.create(), ModelTransform.of(11.2F, -49.6F, 4.8F, -0.1745F, 0.0F, 0.1047F));

		ModelPartData ear_small_left1_seg_r1 = ear_small_left1.addChild("ear_small_left1_seg_r1", ModelPartBuilder.create().uv(184, 443).cuboid(-10.0F, -14.4F, -5.2F, 16.0F, 14.4F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

		ModelPartData ear_small_left2 = ear_small_left1.addChild("ear_small_left2", ModelPartBuilder.create(), ModelTransform.of(0.0F, -14.4F, 0.0F, -0.0698F, 0.0F, 0.0524F));

		ModelPartData ear_small_left2_seg_r1 = ear_small_left2.addChild("ear_small_left2_seg_r1", ModelPartBuilder.create().uv(299, 376).cuboid(-4.4F, -24.0F, -14.32F, 8.0F, 24.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 8.0F, 8.0F, -0.5197F, 0.1035F, 0.2455F));

		ModelPartData ear_small_left2_seg_r2 = ear_small_left2.addChild("ear_small_left2_seg_r2", ModelPartBuilder.create().uv(266, 376).cuboid(-4.4F, -24.0F, -14.32F, 8.0F, 24.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 8.0F, 8.0F, -0.5706F, 0.0051F, 0.0497F));

		ModelPartData ear_small_left2_seg_r3 = ear_small_left2.addChild("ear_small_left2_seg_r3", ModelPartBuilder.create().uv(233, 376).cuboid(-4.4F, -24.0F, -14.32F, 8.0F, 24.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 16.0F, 8.0F, -0.6545F, 0.0F, 0.0F));

		ModelPartData ear_leaf_left1 = head.addChild("ear_leaf_left1", ModelPartBuilder.create().uv(310, 101).cuboid(-8.0F, -53.2F, -3.2F, 16.0F, 56.0F, 6.4F, new Dilation(0.0F))
		.uv(489, 334).cuboid(-18.0F, -44.8F, -4.0F, 8.0F, 32.0F, 1.12F, new Dilation(0.0F))
		.uv(291, 288).cuboid(10.0F, -52.8F, -4.0F, 8.0F, 40.0F, 1.12F, new Dilation(0.0F))
		.uv(312, 288).cuboid(-14.0F, -52.8F, -4.0F, 8.0F, 40.0F, 1.12F, new Dilation(0.0F)), ModelTransform.of(27.2F, -21.6F, 4.8F, -1.3579F, -0.7589F, 1.6896F));

		ModelPartData ear_leaf_mid_left_r1 = ear_leaf_left1.addChild("ear_leaf_mid_left_r1", ModelPartBuilder.create().uv(359, 101).cuboid(0.0F, -56.0F, 0.0F, 16.0F, 56.0F, 6.4F, new Dilation(0.0F)), ModelTransform.of(-4.0F, -18.4F, -3.04F, -0.0774F, 0.0403F, 0.5657F));

		ModelPartData ear_leaf_rim_bot_left_r1 = ear_leaf_left1.addChild("ear_leaf_rim_bot_left_r1", ModelPartBuilder.create().uv(452, 334).cuboid(0.0F, -32.0F, 0.0F, 16.0F, 32.0F, 1.12F, new Dilation(0.0F)), ModelTransform.of(-11.2F, -10.8F, -4.0F, 0.0F, 0.0F, 0.6545F));

		ModelPartData ear_leaf_rim_top_left_r1 = ear_leaf_left1.addChild("ear_leaf_rim_top_left_r1", ModelPartBuilder.create().uv(332, 376).cuboid(-8.0F, -24.0F, 0.0F, 32.0F, 24.0F, 8.0F, new Dilation(0.0F)), ModelTransform.of(-11.2F, -20.8F, -4.0F, 0.0F, 0.0F, 0.1745F));

		ModelPartData ear_leaf_mid_left_r2 = ear_leaf_left1.addChild("ear_leaf_mid_left_r2", ModelPartBuilder.create().uv(147, 101).cuboid(0.0F, -64.0F, 0.0F, 24.0F, 64.0F, 6.4F, new Dilation(0.0F)), ModelTransform.of(-12.0F, -10.4F, -3.04F, -0.0873F, 0.0F, 0.0873F));

		ModelPartData ear_leaf_left2 = ear_leaf_left1.addChild("ear_leaf_left2", ModelPartBuilder.create(), ModelTransform.of(0.0F, -20.0F, 0.0F, -0.1745F, 0.0F, 0.1047F));

		ModelPartData ear_leaf_point_left_r1 = ear_leaf_left2.addChild("ear_leaf_point_left_r1", ModelPartBuilder.create().uv(57, 101).cuboid(4.8F, -72.0F, 0.0F, 8.0F, 72.0F, 3.2F, new Dilation(0.0F)), ModelTransform.of(-2.4F, -24.0F, -1.6F, 0.0F, 0.0F, 0.4363F));

		ModelPartData ear_leaf_point_left_r2 = ear_leaf_left2.addChild("ear_leaf_point_left_r2", ModelPartBuilder.create().uv(0, 101).cuboid(-11.2F, -72.0F, 0.0F, 24.0F, 72.0F, 3.2F, new Dilation(0.0F)), ModelTransform.of(-2.4F, -16.0F, -1.6F, 0.0F, 0.0F, 0.4363F));

		ModelPartData ear_leaf_tip_left_r1 = ear_leaf_left2.addChild("ear_leaf_tip_left_r1", ModelPartBuilder.create().uv(302, 0).cuboid(-11.2F, -72.0F, 0.0F, 32.0F, 72.0F, 4.8F, new Dilation(0.0F)), ModelTransform.of(-6.4F, -1.6F, -2.4F, 0.0F, 0.0F, 0.3491F));

		ModelPartData arm_upper_right = chest.addChild("arm_upper_right", ModelPartBuilder.create().uv(87, 334).cuboid(-0.8F, -2.4F, -4.4F, 8.0F, 30.4F, 8.8F, new Dilation(0.0F)), ModelTransform.of(-20.8F, -11.2F, 0.0F, 0.0F, 0.0F, 0.4189F));

		ModelPartData arm_lower_right = arm_upper_right.addChild("arm_lower_right", ModelPartBuilder.create().uv(161, 334).cuboid(-1.2F, -2.4F, -5.6F, 8.0F, 25.6F, 11.2F, new Dilation(0.0F)), ModelTransform.of(0.0F, 28.8F, 0.0F, -0.2094F, 0.0F, -0.1396F));

		ModelPartData hand_right = arm_lower_right.addChild("hand_right", ModelPartBuilder.create().uv(313, 470).cuboid(-3.2F, -2.4F, -4.0F, 8.8F, 4.8F, 8.0F, new Dilation(0.0F))
		.uv(327, 443).cuboid(-5.6F, -0.8F, -6.4F, 15.2F, 12.0F, 8.0F, new Dilation(0.64F)), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.1047F, -1.5708F, 0.0F));

		ModelPartData mitten_tip_right_r1 = hand_right.addChild("mitten_tip_right_r1", ModelPartBuilder.create().uv(0, 470).cuboid(-6.8F, 0.0F, -5.6F, 16.0F, 8.0F, 8.0F, new Dilation(0.48F)), ModelTransform.of(2.0F, 21.6F, -2.4F, -0.4887F, 0.0F, 0.0F));

		ModelPartData mitten_mass_right_r1 = hand_right.addChild("mitten_mass_right_r1", ModelPartBuilder.create().uv(203, 411).cuboid(-8.8F, 0.0F, -5.6F, 16.0F, 16.0F, 11.2F, new Dilation(0.48F)), ModelTransform.of(2.0F, 10.4F, -1.6F, -0.1745F, 0.0F, 0.0F));

		ModelPartData thumb_right_r1 = hand_right.addChild("thumb_right_r1", ModelPartBuilder.create().uv(192, 470).cuboid(-14.0F, 4.8F, -3.2F, 16.0F, 8.0F, 6.4F, new Dilation(0.0F)), ModelTransform.of(-5.2F, 8.8F, -4.8F, 0.0F, -0.0873F, -0.5672F));

		ModelPartData arm_upper_left = chest.addChild("arm_upper_left", ModelPartBuilder.create().uv(124, 334).cuboid(-7.2F, -2.4F, -4.4F, 8.0F, 30.4F, 8.8F, new Dilation(0.0F)), ModelTransform.of(20.8F, -11.2F, 0.0F, 0.0F, 0.0F, -0.4189F));

		ModelPartData arm_lower_left = arm_upper_left.addChild("arm_lower_left", ModelPartBuilder.create().uv(202, 334).cuboid(-6.8F, -2.4F, -5.6F, 8.0F, 25.6F, 11.2F, new Dilation(0.0F)), ModelTransform.of(0.0F, 28.8F, 0.0F, -0.2094F, 0.0F, 0.1396F));

		ModelPartData hand_left = arm_lower_left.addChild("hand_left", ModelPartBuilder.create().uv(418, 470).cuboid(-5.6F, -2.4F, -4.0F, 8.8F, 4.8F, 8.0F, new Dilation(0.0F))
		.uv(376, 443).cuboid(-9.6F, -0.8F, -6.4F, 15.2F, 12.0F, 8.0F, new Dilation(0.64F)), ModelTransform.of(0.0F, 24.0F, 0.0F, 0.1047F, 1.5708F, 0.0F));

		ModelPartData thumb_left_r1 = hand_left.addChild("thumb_left_r1", ModelPartBuilder.create().uv(239, 470).cuboid(-2.0F, 4.8F, -3.2F, 16.0F, 8.0F, 6.4F, new Dilation(0.0F)), ModelTransform.of(5.2F, 8.8F, -4.8F, 0.0F, 0.0873F, 0.5672F));

		ModelPartData mitten_tip_left_r1 = hand_left.addChild("mitten_tip_left_r1", ModelPartBuilder.create().uv(49, 470).cuboid(-9.2F, 0.0F, -5.6F, 16.0F, 8.0F, 8.0F, new Dilation(0.48F)), ModelTransform.of(-2.0F, 21.6F, -2.4F, -0.4887F, 0.0F, 0.0F));

		ModelPartData mitten_mass_left_r1 = hand_left.addChild("mitten_mass_left_r1", ModelPartBuilder.create().uv(260, 411).cuboid(-7.2F, 0.0F, -5.6F, 16.0F, 16.0F, 11.2F, new Dilation(0.48F)), ModelTransform.of(-2.0F, 10.4F, -1.6F, -0.1745F, 0.0F, 0.0F));

		ModelPartData tail1 = hips.addChild("tail1", ModelPartBuilder.create().uv(271, 235).cuboid(-7.6F, -7.6F, -8.0F, 15.2F, 15.2F, 32.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.8F, 13.6F, -0.1047F, 0.0F, 0.0F));

		ModelPartData tail2 = tail1.addChild("tail2", ModelPartBuilder.create().uv(376, 178).cuboid(-7.03F, -7.03F, -11.2F, 14.06F, 14.06F, 40.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 28.8F, 0.0698F, 0.0F, 0.0F));

		ModelPartData tail3 = tail2.addChild("tail3", ModelPartBuilder.create().uv(164, 288).cuboid(-6.46F, -6.46F, 0.0F, 12.92F, 12.92F, 28.8F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 28.8F, 0.1047F, 0.0F, 0.0F));

		ModelPartData tail4 = tail3.addChild("tail4", ModelPartBuilder.create().uv(0, 288).cuboid(-5.89F, -10.11F, 0.0F, 11.78F, 16.0F, 28.8F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 28.8F, 0.1047F, 0.0F, 0.0F));

		ModelPartData tail5 = tail4.addChild("tail5", ModelPartBuilder.create().uv(83, 288).cuboid(-5.32F, -10.68F, 0.0F, 10.64F, 16.0F, 28.8F, new Dilation(0.0F))
		.uv(439, 411).cuboid(-6.0F, -12.4F, 27.2F, 12.0F, 16.0F, 9.6F, new Dilation(0.0F))
		.uv(130, 411).cuboid(-12.0F, -12.8F, 35.2F, 24.0F, 16.0F, 11.2F, new Dilation(0.0F))
		.uv(86, 443).cuboid(-8.0F, -13.2F, 45.6F, 16.0F, 16.0F, 8.0F, new Dilation(0.0F))
		.uv(480, 470).cuboid(-3.6F, -2.4F, 52.8F, 7.2F, 4.8F, 5.6F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 28.8F, 0.0698F, 0.0F, 0.0F));

		ModelPartData leg_upper_right = hips.addChild("leg_upper_right", ModelPartBuilder.create().uv(117, 235).cuboid(-9.6F, -7.2F, -8.8F, 19.2F, 30.4F, 17.6F, new Dilation(0.0F))
		.uv(0, 411).cuboid(-8.0F, 21.6F, -8.0F, 16.0F, 14.4F, 16.0F, new Dilation(0.0F)), ModelTransform.of(-12.8F, 0.0F, 0.0F, -0.2094F, 0.0F, 0.0349F));

		ModelPartData thigh_upper_right_r1 = leg_upper_right.addChild("thigh_upper_right_r1", ModelPartBuilder.create().uv(399, 334).cuboid(-17.6F, -43.2F, 0.8F, 8.0F, 16.0F, 17.6F, new Dilation(0.0F)), ModelTransform.of(11.2F, 28.0F, -9.6F, 0.0F, 0.0F, 0.0873F));

		ModelPartData leg_lower_right = leg_upper_right.addChild("leg_lower_right", ModelPartBuilder.create().uv(243, 334).cuboid(-6.0F, -8.0F, -6.0F, 12.0F, 24.0F, 12.0F, new Dilation(0.0F))
		.uv(0, 443).cuboid(-4.8F, 14.4F, -5.2F, 9.6F, 14.4F, 10.4F, new Dilation(0.0F)), ModelTransform.of(0.0F, 42.4F, 0.0F, 0.4189F, 0.0F, -0.0175F));

		ModelPartData foot_right = leg_lower_right.addChild("foot_right", ModelPartBuilder.create().uv(233, 443).cuboid(-5.6F, -2.4F, -4.0F, 11.2F, 9.6F, 10.4F, new Dilation(0.48F))
		.uv(98, 470).cuboid(-7.2F, 12.0F, -8.0F, 14.4F, 6.4F, 8.0F, new Dilation(0.48F))
		.uv(348, 470).cuboid(-4.8F, 2.4F, 4.8F, 9.6F, 5.6F, 6.4F, new Dilation(0.4F)), ModelTransform.of(0.0F, 30.4F, 0.0F, -0.2094F, 0.0F, 0.0F));

		ModelPartData foot_mid_right_r1 = foot_right.addChild("foot_mid_right_r1", ModelPartBuilder.create().uv(317, 411).cuboid(-6.4F, -0.8F, -12.8F, 12.8F, 9.6F, 16.8F, new Dilation(0.48F)), ModelTransform.of(0.0F, 5.6F, 0.0F, 0.3142F, 0.0F, 0.0F));

		ModelPartData leg_upper_left = hips.addChild("leg_upper_left", ModelPartBuilder.create().uv(194, 235).cuboid(-9.6F, -7.2F, -8.8F, 19.2F, 30.4F, 17.6F, new Dilation(0.0F))
		.uv(65, 411).cuboid(-8.0F, 21.6F, -8.0F, 16.0F, 14.4F, 16.0F, new Dilation(0.0F)), ModelTransform.of(12.8F, 0.0F, 0.0F, -0.2094F, 0.0F, -0.0349F));

		ModelPartData thigh_upper_left_r1 = leg_upper_left.addChild("thigh_upper_left_r1", ModelPartBuilder.create().uv(0, 376).cuboid(9.6F, -43.2F, 0.8F, 8.0F, 16.0F, 17.6F, new Dilation(0.0F)), ModelTransform.of(-11.2F, 28.0F, -9.6F, 0.0F, 0.0F, -0.0873F));

		ModelPartData leg_lower_left = leg_upper_left.addChild("leg_lower_left", ModelPartBuilder.create().uv(292, 334).cuboid(-6.0F, -8.0F, -6.0F, 12.0F, 24.0F, 12.0F, new Dilation(0.0F))
		.uv(43, 443).cuboid(-4.8F, 14.4F, -5.2F, 9.6F, 14.4F, 10.4F, new Dilation(0.0F)), ModelTransform.of(0.0F, 42.4F, 0.0F, 0.4189F, 0.0F, 0.0175F));

		ModelPartData foot_left = leg_lower_left.addChild("foot_left", ModelPartBuilder.create().uv(280, 443).cuboid(-5.6F, -2.4F, -4.0F, 11.2F, 9.6F, 10.4F, new Dilation(0.48F))
		.uv(145, 470).cuboid(-7.2F, 12.0F, -8.0F, 14.4F, 6.4F, 8.0F, new Dilation(0.48F))
		.uv(383, 470).cuboid(-4.8F, 2.4F, 4.8F, 9.6F, 5.6F, 6.4F, new Dilation(0.4F)), ModelTransform.of(0.0F, 30.4F, 0.0F, -0.2094F, 0.0F, 0.0F));

		ModelPartData foot_mid_left_r1 = foot_left.addChild("foot_mid_left_r1", ModelPartBuilder.create().uv(378, 411).cuboid(-6.4F, -0.8F, -12.8F, 12.8F, 9.6F, 16.8F, new Dilation(0.48F)), ModelTransform.of(0.0F, 5.6F, 0.0F, 0.3142F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 512, 2048);
	}
	@Override
	public void setAngles(OriEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
        //两种二段跳
		if (entity.isJumping2()) {
			this.updateAnimation(entity.jump2AnimationStatement, ModAnimations.ORI_JUMP_2, ageInTicks, 1.0f);
			return;
		}
		if (entity.isJumping2Spin()) {
			this.updateAnimation(entity.jump2SpinAnimationStatement, ModAnimations.ORI_JUMP_2_SPIN, ageInTicks, 1.0f);
			return;
		}
        //行走
		if(entity.isMoving(entity.getVelocity().horizontalLengthSquared())){
			this.animateMovement(ModAnimations.ORI_WALK, limbSwing, limbSwingAmount, 2f, 2f);
		}else {
			this.updateAnimation(entity.idleAnimationState, ModAnimations.ORI_IDLE, ageInTicks, 1.0f);
		}
		//一段跳
		this.updateAnimation(entity.jumpAnimationState, ModAnimations.ORI_JUMP_1, ageInTicks, 1.0f);
    }
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		matrices.push();
		matrices.scale(0.125f, 0.125f, 0.125f);
		root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
		matrices.pop();
	}

	@Override
	public ModelPart getPart() {
		return root;
	}
}