package net.debilX.feraritab.block;

import net.debilX.feraritab.block.custom.ShitTurretBlock;
import net.debilX.feraritab.block.custom.RezeBlock;
import net.debilX.feraritab.block.custom.PatyBlock;
import net.debilX.feraritab.feraritab;
import net.debilX.feraritab.item.ModItems;
import net.debilX.feraritab.sound.ModSounds;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.block.TrapDoorBlock;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, feraritab.MOD_ID);

    public static final RegistryObject<Block> PISA_BLOCK = registerBlock("pisa_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static final RegistryObject<Block> SHIT_BLOCK = registerBlock("shit_block",
            ()-> new Block(BlockBehaviour.Properties.of()
                    .requiresCorrectToolForDrops()
                    .strength(2f).sound(SoundType.MUD)));

    public static final RegistryObject<Block> PISA_ORE = registerBlock("pisa_ore",
            () -> new DropExperienceBlock(UniformInt.of(2,4), BlockBehaviour.Properties.of()
                    .strength(1f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> SHIT_TURRET = registerBlock("shit_turret",
            () -> new ShitTurretBlock(BlockBehaviour.Properties.of().strength(2f)));

    public static final RegistryObject<Block> REZE_BLOCK = registerBlock("reze_block",
            () -> new RezeBlock(BlockBehaviour.Properties.of()
                    .strength(1f).sound(ModSounds.ZERO_BLOCK_SOUNDS)
                    .lightLevel(state -> state.getValue(RezeBlock.ACTIVE) ? 15 : 0)));

    public static final RegistryObject<PressurePlateBlock> SHIT_PRESSURE_PLATE = registerBlock("shit_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.ACACIA, BlockBehaviour.Properties.of().sound(SoundType.MUD).strength(1)));

    public static final RegistryObject<Block> PATY_BLOCK = registerBlock("paty_block",
            () -> new PatyBlock(BlockBehaviour.Properties.of()
                    .strength(1f).sound(SoundType.AMETHYST)));

    public static final RegistryObject<StairBlock> PISA_STAIRS = registerBlock("pisa_stairs",
            () -> new StairBlock(ModBlocks.PISA_BLOCK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));
    public static final RegistryObject<SlabBlock> PISA_SLAB = registerBlock("pisa_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));

    public static final RegistryObject<PressurePlateBlock> PISA_PRESSURE_PLATE = registerBlock("pisa_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.IRON, BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));
    public static final RegistryObject<ButtonBlock> PISA_BUTTON = registerBlock("pisa_button",
            () -> new ButtonBlock(BlockSetType.IRON,1, BlockBehaviour.Properties.of().strength(3f)
                    .requiresCorrectToolForDrops().noCollission()));

    public static final RegistryObject<FenceBlock> PISA_FENCE = registerBlock("pisa_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));
    public static final RegistryObject<FenceGateBlock> PISA_FENCE_GATE = registerBlock("pisa_fence_gate",
            () -> new FenceGateBlock(WoodType.ACACIA, BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));
    public static final RegistryObject<WallBlock> PISA_WALL = registerBlock("pisa_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()));

    public static final RegistryObject<DoorBlock> PISA_DOOR = registerBlock("pisa_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().noOcclusion()));
    public static final RegistryObject<TrapDoorBlock> PISA_TRAPDOOR = registerBlock("pisa_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops().noOcclusion()));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
