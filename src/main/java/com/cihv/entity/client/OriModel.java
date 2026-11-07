package com.cihv.entity.client;

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
		ModelPartData root = modelPartData.addChild("root", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData hips = root.addChild("hips", ModelPartBuilder.create().uv(0, 0).cuboid(-1.2F, -1.9F, -1.4F, 2.4F, 2.2F, 2.8F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -11.5F, 0.0F));

		ModelPartData spine = hips.addChild("spine", ModelPartBuilder.create().uv(0, 0).cuboid(-1.4F, -2.1F, -1.5F, 2.8F, 2.1F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -1.9F, 0.0F));

		ModelPartData chest = spine.addChild("chest", ModelPartBuilder.create().uv(0, 0).cuboid(-1.8F, -2.2F, -1.8F, 3.6F, 2.8F, 3.6F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.6F, 0.0F));

		ModelPartData neck = chest.addChild("neck", ModelPartBuilder.create().uv(0, 0).cuboid(-0.9F, -1.5F, -0.9F, 1.8F, 2.0F, 1.8F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -2.6F, 0.0F));

		ModelPartData head = neck.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-3.6F, -5.6F, -3.5F, 7.2F, 5.6F, 6.8F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-2.9F, -6.6F, -2.9F, 5.8F, 1.2F, 5.6F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-2.9F, -0.4F, -3.0F, 5.8F, 0.9F, 5.9F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-3.1F, -5.4F, 3.1F, 6.2F, 5.0F, 0.7F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.4F, -2.2F, -4.0F, 1.0F, 1.0F, 0.55F, new Dilation(0.0F))
		.uv(0, 0).mirrored().cuboid(-0.6F, -2.2F, -4.0F, 1.0F, 1.0F, 0.55F, new Dilation(0.0F)).mirrored(false), ModelTransform.pivot(0.0F, -1.2F, 0.0F));

		ModelPartData eye_top_left_r1 = head.addChild("eye_top_left_r1", ModelPartBuilder.create().uv(0, 0).mirrored().cuboid(-0.9F, -1.5F, -0.14F, 1.8F, 2.0F, 0.27F, new Dilation(0.0F)).mirrored(false), ModelTransform.of(1.9F, -3.3F, -3.6F, 0.0F, 0.0F, 0.4538F));

		ModelPartData eye_top_right_r1 = head.addChild("eye_top_right_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-0.9F, -1.5F, -0.14F, 1.8F, 2.0F, 0.27F, new Dilation(0.0F)), ModelTransform.of(-1.9F, -3.3F, -3.6F, 0.0F, 0.0F, -0.4538F));

		ModelPartData ear_small_right1 = head.addChild("ear_small_right1", ModelPartBuilder.create(), ModelTransform.of(-1.4F, -6.2F, 0.6F, -0.1745F, 0.0F, -0.1047F));

		ModelPartData ear_small_right1_seg_r1 = ear_small_right1.addChild("ear_small_right1_seg_r1", ModelPartBuilder.create().uv(-1, -1).cuboid(-0.75F, -1.8F, -0.65F, 2.0F, 1.8F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

		ModelPartData ear_small_right2 = ear_small_right1.addChild("ear_small_right2", ModelPartBuilder.create(), ModelTransform.of(0.0F, -1.8F, 0.0F, -0.0698F, 0.0F, -0.0524F));

		ModelPartData ear_small_right2_seg_r1 = ear_small_right2.addChild("ear_small_right2_seg_r1", ModelPartBuilder.create().uv(-1, -1).cuboid(-0.45F, -3.0F, -1.79F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 1.0F, -0.5197F, -0.1035F, -0.2455F));

		ModelPartData ear_small_right2_seg_r2 = ear_small_right2.addChild("ear_small_right2_seg_r2", ModelPartBuilder.create().uv(-1, -1).cuboid(-0.45F, -3.0F, -1.79F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 1.0F, -0.5706F, -0.0051F, -0.0497F));

		ModelPartData ear_small_right2_seg_r3 = ear_small_right2.addChild("ear_small_right2_seg_r3", ModelPartBuilder.create().uv(-1, -1).cuboid(-0.45F, -3.0F, -1.79F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, 1.0F, -0.6545F, 0.0F, 0.0F));

		ModelPartData ear_leaf_right1 = head.addChild("ear_leaf_right1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -6.65F, -0.4F, 2.0F, 7.0F, 0.8F, new Dilation(0.0F))
		.uv(0, 0).cuboid(1.25F, -5.6F, -0.5F, 1.0F, 4.0F, 0.14F, new Dilation(0.0F))
		.uv(-1, 0).cuboid(-2.25F, -6.6F, -0.5F, 1.0F, 5.0F, 0.14F, new Dilation(0.0F))
		.uv(-1, 0).cuboid(0.75F, -6.6F, -0.5F, 1.0F, 5.0F, 0.14F, new Dilation(0.0F)), ModelTransform.of(-3.4F, -2.7F, 0.6F, -1.3579F, 0.7589F, -1.6896F));

		ModelPartData ear_leaf_rim_bot_right_r1 = ear_leaf_right1.addChild("ear_leaf_rim_bot_right_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-2.0F, -4.0F, 0.0F, 2.0F, 4.0F, 0.14F, new Dilation(0.0F)), ModelTransform.of(1.4F, -1.35F, -0.5F, 0.0F, 0.0F, -0.6545F));

		ModelPartData ear_leaf_rim_top_right_r1 = ear_leaf_right1.addChild("ear_leaf_rim_top_right_r1", ModelPartBuilder.create().uv(-2, -1).cuboid(-3.0F, -3.0F, 0.0F, 4.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(1.4F, -2.6F, -0.5F, 0.0F, 0.0F, -0.1745F));

		ModelPartData ear_leaf_mid_right_r1 = ear_leaf_right1.addChild("ear_leaf_mid_right_r1", ModelPartBuilder.create().uv(1, 0).cuboid(-2.0F, -7.0F, 0.0F, 2.0F, 7.0F, 0.8F, new Dilation(0.0F)), ModelTransform.of(0.5F, -2.3F, -0.38F, -0.0774F, -0.0403F, -0.5657F));

		ModelPartData ear_leaf_mid_right_r2 = ear_leaf_right1.addChild("ear_leaf_mid_right_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -8.0F, 0.0F, 3.0F, 8.0F, 0.8F, new Dilation(0.0F)), ModelTransform.of(1.5F, -1.3F, -0.38F, -0.0873F, 0.0F, -0.0873F));

		ModelPartData ear_leaf_right2 = ear_leaf_right1.addChild("ear_leaf_right2", ModelPartBuilder.create(), ModelTransform.of(0.0F, -2.5F, 0.0F, -0.1745F, 0.0F, -0.1047F));

		ModelPartData ear_leaf_point_right_r1 = ear_leaf_right2.addChild("ear_leaf_point_right_r1", ModelPartBuilder.create().uv(-1, 0).cuboid(-1.6F, -9.0F, 0.0F, 1.0F, 9.0F, 0.4F, new Dilation(0.0F)), ModelTransform.of(0.3F, -3.0F, -0.2F, 0.0F, 0.0F, -0.4363F));

		ModelPartData ear_leaf_point_right_r2 = ear_leaf_right2.addChild("ear_leaf_point_right_r2", ModelPartBuilder.create().uv(-1, 0).cuboid(-1.6F, -9.0F, 0.0F, 3.0F, 9.0F, 0.4F, new Dilation(0.0F)), ModelTransform.of(0.3F, -2.0F, -0.2F, 0.0F, 0.0F, -0.4363F));

		ModelPartData ear_leaf_tip_right_r1 = ear_leaf_right2.addChild("ear_leaf_tip_right_r1", ModelPartBuilder.create().uv(-1, 0).cuboid(-2.6F, -9.0F, 0.0F, 4.0F, 9.0F, 0.6F, new Dilation(0.0F)), ModelTransform.of(0.8F, -0.2F, -0.3F, 0.0F, 0.0F, -0.3491F));

		ModelPartData ear_small_left1 = head.addChild("ear_small_left1", ModelPartBuilder.create(), ModelTransform.of(1.4F, -6.2F, 0.6F, -0.1745F, 0.0F, 0.1047F));

		ModelPartData ear_small_left1_seg_r1 = ear_small_left1.addChild("ear_small_left1_seg_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.25F, -1.8F, -0.65F, 2.0F, 1.8F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, -0.1309F, 0.0F, 0.0F));

		ModelPartData ear_small_left2 = ear_small_left1.addChild("ear_small_left2", ModelPartBuilder.create(), ModelTransform.of(0.0F, -1.8F, 0.0F, -0.0698F, 0.0F, 0.0524F));

		ModelPartData ear_small_left2_seg_r1 = ear_small_left2.addChild("ear_small_left2_seg_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-0.55F, -3.0F, -1.79F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 1.0F, -0.5197F, 0.1035F, 0.2455F));

		ModelPartData ear_small_left2_seg_r2 = ear_small_left2.addChild("ear_small_left2_seg_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-0.55F, -3.0F, -1.79F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 1.0F, -0.5706F, 0.0051F, 0.0497F));

		ModelPartData ear_small_left2_seg_r3 = ear_small_left2.addChild("ear_small_left2_seg_r3", ModelPartBuilder.create().uv(0, 0).cuboid(-0.55F, -3.0F, -1.79F, 1.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 2.0F, 1.0F, -0.6545F, 0.0F, 0.0F));

		ModelPartData ear_leaf_left1 = head.addChild("ear_leaf_left1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -6.65F, -0.4F, 2.0F, 7.0F, 0.8F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-2.25F, -5.6F, -0.5F, 1.0F, 4.0F, 0.14F, new Dilation(0.0F))
		.uv(0, 0).cuboid(1.25F, -6.6F, -0.5F, 1.0F, 5.0F, 0.14F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-1.75F, -6.6F, -0.5F, 1.0F, 5.0F, 0.14F, new Dilation(0.0F)), ModelTransform.of(3.4F, -2.7F, 0.6F, -1.3579F, -0.7589F, 1.6896F));

		ModelPartData ear_leaf_mid_left_r1 = ear_leaf_left1.addChild("ear_leaf_mid_left_r1", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -7.0F, 0.0F, 2.0F, 7.0F, 0.8F, new Dilation(0.0F)), ModelTransform.of(-0.5F, -2.3F, -0.38F, -0.0774F, 0.0403F, 0.5657F));

		ModelPartData ear_leaf_rim_bot_left_r1 = ear_leaf_left1.addChild("ear_leaf_rim_bot_left_r1", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -4.0F, 0.0F, 2.0F, 4.0F, 0.14F, new Dilation(0.0F)), ModelTransform.of(-1.4F, -1.35F, -0.5F, 0.0F, 0.0F, 0.6545F));

		ModelPartData ear_leaf_rim_top_left_r1 = ear_leaf_left1.addChild("ear_leaf_rim_top_left_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -3.0F, 0.0F, 4.0F, 3.0F, 1.0F, new Dilation(0.0F)), ModelTransform.of(-1.4F, -2.6F, -0.5F, 0.0F, 0.0F, 0.1745F));

		ModelPartData ear_leaf_mid_left_r2 = ear_leaf_left1.addChild("ear_leaf_mid_left_r2", ModelPartBuilder.create().uv(0, 0).cuboid(0.0F, -8.0F, 0.0F, 3.0F, 8.0F, 0.8F, new Dilation(0.0F)), ModelTransform.of(-1.5F, -1.3F, -0.38F, -0.0873F, 0.0F, 0.0873F));

		ModelPartData ear_leaf_left2 = ear_leaf_left1.addChild("ear_leaf_left2", ModelPartBuilder.create(), ModelTransform.of(0.0F, -2.5F, 0.0F, -0.1745F, 0.0F, 0.1047F));

		ModelPartData ear_leaf_point_left_r1 = ear_leaf_left2.addChild("ear_leaf_point_left_r1", ModelPartBuilder.create().uv(0, 0).cuboid(0.6F, -9.0F, 0.0F, 1.0F, 9.0F, 0.4F, new Dilation(0.0F)), ModelTransform.of(-0.3F, -3.0F, -0.2F, 0.0F, 0.0F, 0.4363F));

		ModelPartData ear_leaf_point_left_r2 = ear_leaf_left2.addChild("ear_leaf_point_left_r2", ModelPartBuilder.create().uv(0, 0).cuboid(-1.4F, -9.0F, 0.0F, 3.0F, 9.0F, 0.4F, new Dilation(0.0F)), ModelTransform.of(-0.3F, -2.0F, -0.2F, 0.0F, 0.0F, 0.4363F));

		ModelPartData ear_leaf_tip_left_r1 = ear_leaf_left2.addChild("ear_leaf_tip_left_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.4F, -9.0F, 0.0F, 4.0F, 9.0F, 0.6F, new Dilation(0.0F)), ModelTransform.of(-0.8F, -0.2F, -0.3F, 0.0F, 0.0F, 0.3491F));

		ModelPartData arm_upper_right = chest.addChild("arm_upper_right", ModelPartBuilder.create().uv(0, 0).cuboid(-0.1F, -0.3F, -0.55F, 1.0F, 3.8F, 1.1F, new Dilation(0.0F)), ModelTransform.of(-2.6F, -1.4F, 0.0F, 0.0F, 0.0F, 0.4189F));

		ModelPartData arm_lower_right = arm_upper_right.addChild("arm_lower_right", ModelPartBuilder.create().uv(0, 0).cuboid(-0.15F, -0.3F, -0.7F, 1.0F, 3.2F, 1.4F, new Dilation(0.0F)), ModelTransform.of(0.0F, 3.6F, 0.0F, -0.2094F, 0.0F, -0.1396F));

		ModelPartData hand_right = arm_lower_right.addChild("hand_right", ModelPartBuilder.create().uv(0, 0).cuboid(-0.4F, -0.3F, -0.5F, 1.1F, 0.6F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.7F, -0.1F, -0.8F, 1.9F, 1.5F, 1.0F, new Dilation(0.08F)), ModelTransform.of(0.0F, 3.0F, 0.0F, 0.1047F, -1.5708F, 0.0F));

		ModelPartData mitten_tip_right_r1 = hand_right.addChild("mitten_tip_right_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-0.85F, 0.0F, -0.7F, 2.0F, 1.0F, 1.0F, new Dilation(0.06F)), ModelTransform.of(0.25F, 2.7F, -0.3F, -0.4887F, 0.0F, 0.0F));

		ModelPartData mitten_mass_right_r1 = hand_right.addChild("mitten_mass_right_r1", ModelPartBuilder.create().uv(-1, 0).cuboid(-1.1F, 0.0F, -0.7F, 2.0F, 2.0F, 1.4F, new Dilation(0.06F)), ModelTransform.of(0.25F, 1.3F, -0.2F, -0.1745F, 0.0F, 0.0F));

		ModelPartData thumb_right_r1 = hand_right.addChild("thumb_right_r1", ModelPartBuilder.create().uv(-2, 0).cuboid(-1.75F, 0.6F, -0.4F, 2.0F, 1.0F, 0.8F, new Dilation(0.0F)), ModelTransform.of(-0.65F, 1.1F, -0.6F, 0.0F, -0.0873F, -0.5672F));

		ModelPartData arm_upper_left = chest.addChild("arm_upper_left", ModelPartBuilder.create().uv(0, 0).cuboid(-0.9F, -0.3F, -0.55F, 1.0F, 3.8F, 1.1F, new Dilation(0.0F)), ModelTransform.of(2.6F, -1.4F, 0.0F, 0.0F, 0.0F, -0.4189F));

		ModelPartData arm_lower_left = arm_upper_left.addChild("arm_lower_left", ModelPartBuilder.create().uv(0, 0).cuboid(-0.85F, -0.3F, -0.7F, 1.0F, 3.2F, 1.4F, new Dilation(0.0F)), ModelTransform.of(0.0F, 3.6F, 0.0F, -0.2094F, 0.0F, 0.1396F));

		ModelPartData hand_left = arm_lower_left.addChild("hand_left", ModelPartBuilder.create().uv(0, 0).cuboid(-0.7F, -0.3F, -0.5F, 1.1F, 0.6F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-1.2F, -0.1F, -0.8F, 1.9F, 1.5F, 1.0F, new Dilation(0.08F)), ModelTransform.of(0.0F, 3.0F, 0.0F, 0.1047F, 1.5708F, 0.0F));

		ModelPartData thumb_left_r1 = hand_left.addChild("thumb_left_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-0.25F, 0.6F, -0.4F, 2.0F, 1.0F, 0.8F, new Dilation(0.0F)), ModelTransform.of(0.65F, 1.1F, -0.6F, 0.0F, 0.0873F, 0.5672F));

		ModelPartData mitten_tip_left_r1 = hand_left.addChild("mitten_tip_left_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-1.15F, 0.0F, -0.7F, 2.0F, 1.0F, 1.0F, new Dilation(0.06F)), ModelTransform.of(-0.25F, 2.7F, -0.3F, -0.4887F, 0.0F, 0.0F));

		ModelPartData mitten_mass_left_r1 = hand_left.addChild("mitten_mass_left_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-0.9F, 0.0F, -0.7F, 2.0F, 2.0F, 1.4F, new Dilation(0.06F)), ModelTransform.of(-0.25F, 1.3F, -0.2F, -0.1745F, 0.0F, 0.0F));

		ModelPartData tail1 = hips.addChild("tail1", ModelPartBuilder.create().uv(-1, -1).cuboid(-0.95F, -0.95F, -1.0F, 1.9F, 1.9F, 4.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.1F, 1.7F, -0.1047F, 0.0F, 0.0F));

		ModelPartData tail2 = tail1.addChild("tail2", ModelPartBuilder.create().uv(-2, -2).cuboid(-0.8788F, -0.8788F, -1.4F, 1.7575F, 1.7575F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 3.6F, 0.0698F, 0.0F, 0.0F));

		ModelPartData tail3 = tail2.addChild("tail3", ModelPartBuilder.create().uv(0, 0).cuboid(-0.8075F, -0.8075F, 0.0F, 1.615F, 1.615F, 3.6F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 3.6F, 0.1047F, 0.0F, 0.0F));

		ModelPartData tail4 = tail3.addChild("tail4", ModelPartBuilder.create().uv(0, 0).cuboid(-0.7362F, -1.2637F, 0.0F, 1.4725F, 2.0F, 3.6F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 3.6F, 0.1047F, 0.0F, 0.0F));

		ModelPartData tail5 = tail4.addChild("tail5", ModelPartBuilder.create().uv(0, 0).cuboid(-0.665F, -1.335F, 0.0F, 1.33F, 2.0F, 3.6F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.75F, -1.55F, 3.4F, 1.5F, 2.0F, 1.2F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-1.5F, -1.6F, 4.4F, 3.0F, 2.0F, 1.4F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-1.0F, -1.65F, 5.7F, 2.0F, 2.0F, 1.0F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.45F, -0.3F, 6.6F, 0.9F, 0.6F, 0.7F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 3.6F, 0.0698F, 0.0F, 0.0F));

		ModelPartData leg_upper_right = hips.addChild("leg_upper_right", ModelPartBuilder.create().uv(0, 0).cuboid(-1.2F, -0.9F, -1.1F, 2.4F, 3.8F, 2.2F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-1.0F, 2.7F, -1.0F, 2.0F, 1.8F, 2.0F, new Dilation(0.0F)), ModelTransform.of(-1.6F, 0.0F, 0.0F, -0.2094F, 0.0F, 0.0349F));

		ModelPartData thigh_upper_right_r1 = leg_upper_right.addChild("thigh_upper_right_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-2.2F, -5.4F, 0.1F, 1.0F, 2.0F, 2.2F, new Dilation(0.0F)), ModelTransform.of(1.4F, 3.5F, -1.2F, 0.0F, 0.0F, 0.0873F));

		ModelPartData leg_lower_right = leg_upper_right.addChild("leg_lower_right", ModelPartBuilder.create().uv(0, 0).cuboid(-0.75F, -1.0F, -0.75F, 1.5F, 3.0F, 1.5F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.6F, 1.8F, -0.65F, 1.2F, 1.8F, 1.3F, new Dilation(0.0F)), ModelTransform.of(0.0F, 5.3F, 0.0F, 0.4189F, 0.0F, -0.0175F));

		ModelPartData foot_right = leg_lower_right.addChild("foot_right", ModelPartBuilder.create().uv(0, 0).cuboid(-0.7F, -0.3F, -0.5F, 1.4F, 1.2F, 1.3F, new Dilation(0.06F))
		.uv(0, 0).cuboid(-0.9F, 1.5F, -1.0F, 1.8F, 0.8F, 1.0F, new Dilation(0.06F))
		.uv(0, 0).cuboid(-0.6F, 0.3F, 0.6F, 1.2F, 0.7F, 0.8F, new Dilation(0.05F)), ModelTransform.of(0.0F, 3.8F, 0.0F, -0.2094F, 0.0F, 0.0F));

		ModelPartData foot_mid_right_r1 = foot_right.addChild("foot_mid_right_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-0.8F, -0.1F, -1.6F, 1.6F, 1.2F, 2.1F, new Dilation(0.06F)), ModelTransform.of(0.0F, 0.7F, 0.0F, 0.3142F, 0.0F, 0.0F));

		ModelPartData leg_upper_left = hips.addChild("leg_upper_left", ModelPartBuilder.create().uv(0, 0).cuboid(-1.2F, -0.9F, -1.1F, 2.4F, 3.8F, 2.2F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-1.0F, 2.7F, -1.0F, 2.0F, 1.8F, 2.0F, new Dilation(0.0F)), ModelTransform.of(1.6F, 0.0F, 0.0F, -0.2094F, 0.0F, -0.0349F));

		ModelPartData thigh_upper_left_r1 = leg_upper_left.addChild("thigh_upper_left_r1", ModelPartBuilder.create().uv(0, 0).cuboid(1.2F, -5.4F, 0.1F, 1.0F, 2.0F, 2.2F, new Dilation(0.0F)), ModelTransform.of(-1.4F, 3.5F, -1.2F, 0.0F, 0.0F, -0.0873F));

		ModelPartData leg_lower_left = leg_upper_left.addChild("leg_lower_left", ModelPartBuilder.create().uv(0, 0).cuboid(-0.75F, -1.0F, -0.75F, 1.5F, 3.0F, 1.5F, new Dilation(0.0F))
		.uv(0, 0).cuboid(-0.6F, 1.8F, -0.65F, 1.2F, 1.8F, 1.3F, new Dilation(0.0F)), ModelTransform.of(0.0F, 5.3F, 0.0F, 0.4189F, 0.0F, 0.0175F));

		ModelPartData foot_left = leg_lower_left.addChild("foot_left", ModelPartBuilder.create().uv(0, 0).cuboid(-0.7F, -0.3F, -0.5F, 1.4F, 1.2F, 1.3F, new Dilation(0.06F))
		.uv(0, 0).cuboid(-0.9F, 1.5F, -1.0F, 1.8F, 0.8F, 1.0F, new Dilation(0.06F))
		.uv(0, 0).cuboid(-0.6F, 0.3F, 0.6F, 1.2F, 0.7F, 0.8F, new Dilation(0.05F)), ModelTransform.of(0.0F, 3.8F, 0.0F, -0.2094F, 0.0F, 0.0F));

		ModelPartData foot_mid_left_r1 = foot_left.addChild("foot_mid_left_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-0.8F, -0.1F, -1.6F, 1.6F, 1.2F, 2.1F, new Dilation(0.06F)), ModelTransform.of(0.0F, 0.7F, 0.0F, 0.3142F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 256);
	}
	@Override
	public void setAngles(OriEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}
	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, float red, float green, float blue, float alpha) {
		root.render(matrices, vertexConsumer, light, overlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart getPart() {
		return root;
	}
}