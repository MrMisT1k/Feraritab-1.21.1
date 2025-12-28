package net.debilX.feraritab.block.custom;

import net.debilX.feraritab.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.internal.TextComponentMessageFormatHandler;

import java.awt.*;

import static java.lang.Math.clamp;
import static java.lang.Math.min;

public class ShitTurretBlock extends Block{
    public static int amountOfShit = 0;
    public static final int MAX_AMOUNT_OF_SHIT=100;

    public ShitTurretBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult) {
        if(pLevel.isClientSide()){
            if (pStack.is(ModItems.SHITBALL.get())) {
                if (amountOfShit + pStack.getCount() < MAX_AMOUNT_OF_SHIT) {
                    pLevel.playSound(pPlayer, pPos, SoundEvents.MUD_BREAK, SoundSource.BLOCKS, 1, 1);
                } else if (amountOfShit < MAX_AMOUNT_OF_SHIT) {
                    pLevel.playSound(pPlayer, pPos, SoundEvents.MUD_BREAK, SoundSource.BLOCKS, 1, 1);
                }
                return ItemInteractionResult.SUCCESS;
            } else if (pStack.is(Items.BONE_MEAL)) {
                pLevel.playSound(pPlayer, pPos, SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.BLOCKS, 1, 1);
                return ItemInteractionResult.SUCCESS;
            }
            return ItemInteractionResult.SUCCESS;
        }
        else {
            if (pStack.is(ModItems.SHITBALL.get())) {
                if (amountOfShit + pStack.getCount() < MAX_AMOUNT_OF_SHIT) {
                    amountOfShit += pStack.getCount();
                    pStack.consume(pStack.getCount(), pPlayer);
                    pLevel.playSound(pPlayer, pPos, SoundEvents.MUD_BREAK, SoundSource.BLOCKS, 1, 1);
                } else if (amountOfShit < MAX_AMOUNT_OF_SHIT) {
                    pStack.consume(MAX_AMOUNT_OF_SHIT - amountOfShit, pPlayer);
                    amountOfShit = MAX_AMOUNT_OF_SHIT;
                    pLevel.playSound(pPlayer, pPos, SoundEvents.MUD_BREAK, SoundSource.BLOCKS, 1, 1);
                } else {
                    pPlayer.sendSystemMessage(Component.literal("Больше говна не влезет..."));
                }
                return ItemInteractionResult.SUCCESS;
            } else if (pStack.is(Items.BONE_MEAL)) {
                double phi = Math.PI * (Math.sqrt(5) - 1);
                for (int i = 0; i < amountOfShit; ++i) {
                    double y = 1 - (i / (double) (amountOfShit - 1)) * 2;
                    double radius = Math.sqrt(1 - y * y);
                    double theta = phi * i;
                    double x = Math.cos(theta) * radius;
                    double z = Math.sin(theta) * radius;

                    Snowball snowball = new Snowball(pLevel, pPlayer);
                    snowball.setItem(new ItemStack(ModItems.SHITBALL.get()));
                    snowball.setPos(pPos.getX() + x * 1.5, pPos.getY() + y * 1.5, pPos.getZ() + z * 1.5);
                    snowball.shoot(x, y, z, 1, 1);
                    pLevel.addFreshEntity(snowball);
                }
                amountOfShit = 0;
                pStack.consume(1, pPlayer);
                pLevel.playSound(pPlayer, pPos, SoundEvents.DRAGON_FIREBALL_EXPLODE, SoundSource.BLOCKS, 1, 1);
                return ItemInteractionResult.SUCCESS;
            } else {
                pPlayer.sendSystemMessage(Component.literal("Сейчас в турели " + String.valueOf(amountOfShit) + " говна"));
                return super.useItemOn(pStack, pState, pLevel, pPos, pPlayer, pHand, pHitResult);
            }
        }
    }
}