package net.debilX.feraritab.block.custom;

import net.debilX.feraritab.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class RezeBlock extends Block {

    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");

    public RezeBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ACTIVE, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                               Player player, BlockHitResult hit) {

        if (!level.isClientSide) {
            if (state.getValue(ACTIVE)) return InteractionResult.SUCCESS;

            level.setBlock(pos, state.setValue(ACTIVE, true), 3);

            level.playSound(
                    null,
                    pos,
                    ModSounds.ZERO_USE.get(),
                    SoundSource.BLOCKS,
                    1f,
                    1f
            );

            level.scheduleTick(pos, this, 15);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(ACTIVE)) {
            level.setBlock(pos, state.setValue(ACTIVE, false), 3);
        }
    }
}
