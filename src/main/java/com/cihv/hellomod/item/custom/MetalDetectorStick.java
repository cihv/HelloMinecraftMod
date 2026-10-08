package com.cihv.hellomod.item.custom;

import com.cihv.hellomod.sound.ModSounds;
import com.cihv.hellomod.util.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetalDetectorStick extends Item {
    public MetalDetectorStick(Settings settings) {
        super(settings);
    }


    /**
     * 实现侦测棒作用于方块时的行为
     *
     * @param context ItemUsageContext
     *
     * @return ActionResult.SUCCESS
     */
    public ActionResult useOnBlock(ItemUsageContext context) {
        //首先需要判断当前环境是不是客户端
        if (!context.getWorld().isClient()) {
            //如果不是客户端，开始定义 1.所点击方块的坐标，2.玩家，3.是否发现目标方块
            BlockPos positionClicked = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            Boolean foundBlock = false;
            //遍历当前点击所点击方块的往下64格位置
            for (int i = 0; i < positionClicked.getY() + 64; i++) {
                //获取当前方块状态
                BlockState blockState = context.getWorld().getBlockState(positionClicked.down(i));
                //判断当前方块状态是否是有价值的
                if (isValuableBlock(blockState)) {
                    foundBlock = true;
                    outputValuableCoordinates(positionClicked.down(i), player, blockState.getBlock());
                    context.getWorld().playSound(null, positionClicked,
                            ModSounds.METAL_DETECTOR_FOUND_ORE,
                            SoundCategory.BLOCKS, 0.1f, 1f);
                    break;
                }
            }
            if (!foundBlock) {
                player.sendMessage(Text.literal("未发现'煤矿'或'铁矿' !"), true);
            }
            //不管发没发现，都需要降低工具的耐久
            context.getStack().damage(1, player,
                    playerEntity -> playerEntity.sendToolBreakStatus(playerEntity.getActiveHand()));
        }
        return ActionResult.SUCCESS;
    }

    /**
     * 辅助方法,检测到有价值方块时 在物品栏上方输出方块信息
     *
     * @param blockPos BlockPos
     * @param player   PlayerEntity
     * @param block    Block
     */
    private void outputValuableCoordinates(BlockPos blockPos, PlayerEntity player, Block block) {
        player.sendMessage(Text.literal("已发现'" + block.asItem().getName().getString() + "'" +
                        "位于(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ") !"),
                true);
    }

    /**
     * 辅助方法,判断是否为有价值方块
     *
     * @param blockState BlockState
     *
     * @return true/false
     */
    private boolean isValuableBlock(BlockState blockState) {
        //如果此方块的tag在自定义tags中 就返回true
        return blockState.isIn(ModTags.Blocks.METAL_DETECTOR_DETECTABLE_BLOCKS);
    }

    /**
     * 为 MetalDetectorStick 添加提示信息
     *
     * @param stack   ItemStack
     * @param world   World
     * @param tooltip List<'Text'>
     * @param context TooltipContext
     */
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        //通过translatable来设置提示信息
        tooltip.add(Text.translatable("tooltip.hellomod.metal_detector_stick.tooltip"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}
