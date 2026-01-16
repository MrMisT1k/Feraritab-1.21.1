package net.debilX.feraritab.block.custom;

import net.debilX.feraritab.item.ModItems;
import net.debilX.feraritab.sound.ModSounds;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.BlockHitResult;
import java.util.Map;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class PatyBlock extends Block {

    public static final BooleanProperty ACTIVE = BooleanProperty.create("active");
    public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
    
    // Счетчик для чередования звуков при установке блока
    private static final AtomicInteger soundCounter = new AtomicInteger(0);

    public PatyBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(ACTIVE, false).setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(ACTIVE, FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getPlayer().getDirection())
                .setValue(ACTIVE, false);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        super.setPlacedBy(level, pos, state, placer, stack);
        
        if (!level.isClientSide) {
            // Определяем какой звук воспроизвести по очереди
            int soundIndex = soundCounter.getAndIncrement() % 2;
            
            if (soundIndex == 0) {
                level.playSound(
                        null,
                        pos,
                        ModSounds.PATY_1.get(),
                        SoundSource.BLOCKS,
                        1.0f,
                        1.0f
                );
            } else {
                level.playSound(
                        null,
                        pos,
                        ModSounds.PATY_2.get(),
                        SoundSource.BLOCKS,
                        1.0f,
                        1.0f
                );
            }
        }
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        // Проверяем, является ли сущность игроком (чтобы блок не реагировал на мобов и предметы)
        if (entity instanceof Player player) {
            // Проверяем, еблан ли я и что музыка НЕ играет
            if (!state.getValue(ACTIVE)) {
                // Проверяем, нажал ли игрок Shift
                if (player.isShiftKeyDown()) {
                    if (!level.isClientSide) {
                        // Активируем блок - устанавливаем свойство ACTIVE в true
                        level.setBlock(pos, state.setValue(ACTIVE, Boolean.TRUE), 3);

                        // Воспроизводим звук активации блока
                        level.playSound(
                                null,
                                pos,
                                ModSounds.PATY_BLOCK.get(),
                                SoundSource.BLOCKS,
                                1f,
                                1f
                        );

                        // Планируем деактивацию через 100 тиков (5 секунд)
                        // 20 тиков = 1 секунда в Minecraft
                        level.scheduleTick(pos, this, 100);

                    }
                }
            }
        }

        // Вызываем родительский метод для стандартной обработки
        super.stepOn(level, pos, state, entity);
    }


    @Override
    public void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        // Если блок активен, деактивируем его
        if (state.getValue(ACTIVE)) {
            // Устанавливаем свойство ACTIVE в false, возвращая блок в неактивное состояние
            level.setBlock(pos, state.setValue(ACTIVE, Boolean.FALSE), 3);
        }
    }
}
