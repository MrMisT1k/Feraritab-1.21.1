package net.debilX.feraritab.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeightedPressurePlateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;

import java.util.List;

public class PressureBomb extends WeightedPressurePlateBlock {

    public PressureBomb(int i, BlockSetType blockSetType, Properties properties) {
        super(i, blockSetType, properties);
        this.registerDefaultState(this.defaultBlockState().setValue(WAS_ACTIVATED, false));
    }

    private final int maxWeight = 15;
    public static final BooleanProperty WAS_ACTIVATED = BooleanProperty.create("was_activated");

    @Override
    protected int getSignalStrength(Level pLevel, BlockPos pPos) {
        int i = Math.min(getEntityCount(pLevel, TOUCH_AABB.move(pPos), Entity.class), this.maxWeight);
        boolean wasActivated = pLevel.getBlockState(pPos).getValue(WAS_ACTIVATED);

        if (!pLevel.isClientSide) {
            if(wasActivated && i == 0){
                pLevel.playSound(null, pPos, SoundEvents.TNT_PRIMED, SoundSource.BLOCKS, 1.0F, 1.0F);
                pLevel.explode(
                        null,
                        pPos.getX() + 0.5,
                        pPos.getY() + 0.5,
                        pPos.getZ() + 0.5,
                        5.0f,
                        Level.ExplosionInteraction.MOB
                );
            }
            else pLevel.setBlockAndUpdate(pPos, pLevel.getBlockState(pPos).setValue(WAS_ACTIVATED, i!=0));
        }

        if (i > 0) {
            float f = (float)Math.min(this.maxWeight, i) / (float)this.maxWeight;
            return Mth.ceil(f * 15.0F);
        } else {
            return 0;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(POWER);
        pBuilder.add(WAS_ACTIVATED);
    }


    /*Баги на данный момент:
    * если быстро пробежаться по плите, она не взрывается
    * иногда после взрыва плита не уничтожается, а остается висеть в воздухе, причем если после этого кинуть на нее что-либо, она моментально взрывается*/
}
