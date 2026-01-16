package net.debilX.feraritab.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeightedPressurePlateBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.unsafe.UnsafeFieldAccess;
import org.lwjgl.util.freetype.FT_Prop_GlyphToScriptMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PressureBomb extends WeightedPressurePlateBlock {

    public PressureBomb(int i, BlockSetType blockSetType, Properties properties) {
        super(i, blockSetType, properties);
        this.registerDefaultState(this.defaultBlockState().setValue(WAS_ACTIVATED, false));
        this.registerDefaultState(this.defaultBlockState().setValue(CAMOUFLAGE, 0));
    }

    public static final Map<Integer, Block> CAMOUFLAGE_MAP =
            Map.of(
                    0, Blocks.TNT,
                    1, Blocks.STONE,
                    2, Blocks.DIRT,
                    3, Blocks.GRASS_BLOCK,
                    4, Blocks.SAND,
                    5, Blocks.COBBLESTONE
            );

    private final int maxWeight = 15;
    public static final BooleanProperty WAS_ACTIVATED = BooleanProperty.create("was_activated");
    public static final IntegerProperty CAMOUFLAGE = IntegerProperty.create("camouflage", 0, 5);

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
                this.destroy(pLevel, pPos, pLevel.getBlockState(pPos));
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
    protected ItemInteractionResult useItemOn(
            ItemStack pStack, BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHitResult
    ) {
        Item item = pStack.getItem();
        int k = -1;
        for(Integer e : CAMOUFLAGE_MAP.keySet()){
            if(CAMOUFLAGE_MAP.get(e).asItem() == item)k=e;
        }
        if(k!=-1){
            if(!pLevel.isClientSide){
                pLevel.setBlockAndUpdate(pPos, pState.setValue(CAMOUFLAGE, k));
            }
            return ItemInteractionResult.SUCCESS;
        }else{
            return super.useItemOn(pStack,pState,pLevel,pPos,pPlayer,pHand,pHitResult);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(POWER);
        pBuilder.add(WAS_ACTIVATED);
        pBuilder.add(CAMOUFLAGE);
    }


    /*Баги на данный момент:
    * если быстро пробежаться по плите, она не взрывается
    * иногда после взрыва плита не уничтожается, а остается висеть в воздухе, причем если после этого кинуть на нее что-либо, она моментально взрывается*/
}
