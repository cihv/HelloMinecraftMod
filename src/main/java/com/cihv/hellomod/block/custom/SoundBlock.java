package com.cihv.hellomod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SoundBlock extends Block {
    public SoundBlock(Settings settings) {
        super(settings);
    }

    /**
     * 实现右键 SoundBlock 时所发生的行为
     * @param blockState BlockState
     * @param world World
     * @param blockPos BlockPos
     * @param player PlayerEntity
     * @param hand Hand
     * @param hit BlockHitResult
     *
     * @return ActionResult.SUCCESS
     */
    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        //实现右键方块产生的行为——发出声音
        world.playSound(player, blockPos, SoundEvents.BLOCK_NOTE_BLOCK_XYLOPHONE.value(), SoundCategory.BLOCKS, 1.0F, 1.0F);
        return ActionResult.SUCCESS;
    }

    /**
     * 为 SoundBlock 添加提示信息
     * @param stack ItemStack
     * @param world BlockView
     * @param tooltip List<'Text'>
     * @param options TooltipContext
     */
    @Override
    public void appendTooltip(ItemStack stack, @Nullable BlockView world, List<Text> tooltip, TooltipContext options) {
        //通过Text.literal来添加自定义提示信息
        tooltip.add(Text.literal("右键时会发出甜美响声 !"));
        super.appendTooltip(stack, world, tooltip, options);
    }
}
