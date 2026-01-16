package net.debilX.feraritab.datagen;

import net.debilX.feraritab.block.ModBlocks;
import net.debilX.feraritab.block.custom.RezeBlock;
import net.debilX.feraritab.block.custom.PatyBlock;
import net.debilX.feraritab.feraritab;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.WeightedPressurePlateBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, feraritab.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.PISA_BLOCK);
        blockWithItem(ModBlocks.PISA_ORE);
        blockWithItem(ModBlocks.SHIT_BLOCK);
        blockWithItem(ModBlocks.SHIT_TURRET);

        patyBlock();

        pressurePlateBlock(ModBlocks.SHIT_PRESSURE_PLATE.get(), blockTexture(ModBlocks.SHIT_BLOCK.get()));

        blockItem(ModBlocks.SHIT_PRESSURE_PLATE);

        rezeBlock();
        pressureBomb();

        stairsBlock(ModBlocks.PISA_STAIRS.get(), blockTexture(ModBlocks.PISA_BLOCK.get()));
        slabBlock(ModBlocks.PISA_SLAB.get(), blockTexture(ModBlocks.PISA_BLOCK.get()), blockTexture(ModBlocks.PISA_BLOCK.get()));

        buttonBlock(ModBlocks.PISA_BUTTON.get(), blockTexture(ModBlocks.PISA_BLOCK.get()));
        pressurePlateBlock(ModBlocks.PISA_PRESSURE_PLATE.get(), blockTexture(ModBlocks.PISA_BLOCK.get()));

        fenceBlock(ModBlocks.PISA_FENCE.get(), blockTexture(ModBlocks.PISA_BLOCK.get()));
        fenceGateBlock(ModBlocks.PISA_FENCE_GATE.get(), blockTexture(ModBlocks.PISA_BLOCK.get()));
        wallBlock(ModBlocks.PISA_WALL.get(), blockTexture(ModBlocks.PISA_BLOCK.get()));

        doorBlockWithRenderType(ModBlocks.PISA_DOOR.get(), 
                ResourceLocation.fromNamespaceAndPath(feraritab.MOD_ID, "block/pisa_door_bottom"),
                ResourceLocation.fromNamespaceAndPath(feraritab.MOD_ID, "block/pisa_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.PISA_TRAPDOOR.get(), 
                ResourceLocation.fromNamespaceAndPath(feraritab.MOD_ID, "block/pisa_trapdoor"), true, "cutout");

        blockItem(ModBlocks.PISA_STAIRS);
        blockItem(ModBlocks.PISA_SLAB);
        blockItem(ModBlocks.PISA_PRESSURE_PLATE);
        blockItem(ModBlocks.PISA_FENCE_GATE);
        blockItem(ModBlocks.PISA_TRAPDOOR);
    }


    private void patyBlock() {
        getVariantBuilder(ModBlocks.PATY_BLOCK.get())
                .forAllStates(state -> {

                    Direction facing = state.getValue(PatyBlock.FACING);
                    boolean active = state.getValue(PatyBlock.ACTIVE);

                    String front = active ? "paty_block_on" : "paty_block_off";

                    int yRot = switch (facing) {
                        case NORTH -> 180;
                        case SOUTH -> 0;
                        case WEST -> 90;
                        case EAST -> 270;
                        default -> 0;
                    };

                    return new ConfiguredModel[]{
                            new ConfiguredModel(
                                    models().cube(
                                            "paty_block",
                                            modLoc("block/paty_block_all"), // down
                                            modLoc("block/paty_block_all"), // up
                                            modLoc("block/" + front),       // north (перед)
                                            modLoc("block/paty_block_all"), // south
                                            modLoc("block/paty_block_all"), // west
                                            modLoc("block/paty_block_all")  // east
                                    ),
                                    0,
                                    yRot,
                                    false
                            )
                    };
                });

        simpleBlockItem(ModBlocks.PATY_BLOCK.get(),
                models().cubeAll("paty_block_item", modLoc("block/paty_block_all")));
    }

    private void rezeBlock() {
        getVariantBuilder(ModBlocks.REZE_BLOCK.get()).forAllStates(state -> {
            if(state.getValue(RezeBlock.ACTIVE)) {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("reze_block_2",
                        ResourceLocation.fromNamespaceAndPath(feraritab.MOD_ID, "block/" + "reze_block_2")))};
            } else {
                return new ConfiguredModel[]{new ConfiguredModel(models().cubeAll("reze_block",
                        ResourceLocation.fromNamespaceAndPath(feraritab.MOD_ID, "block/" + "reze_block")))};
            }
        });
        simpleBlockItem(ModBlocks.REZE_BLOCK.get(), models().cubeAll("reze_block",
                ResourceLocation.fromNamespaceAndPath(feraritab.MOD_ID, "block/" + "reze_block")));
    }

    public void pressureBomb() {
        ModelFile pressurePlate = models().pressurePlate("pressure_bomb", mcLoc("block/tnt_top"));
        ModelFile pressurePlateDown = models().pressurePlateDown("pressure_bomb" + "_down", mcLoc("block/tnt_top"));
        getVariantBuilder(ModBlocks.PRESSURE_BOMB.get())
                .forAllStates(state -> {
                    int power = state.getValue(WeightedPressurePlateBlock.POWER);
                    return ConfiguredModel.builder()
                            .modelFile(models().pressurePlate("pressure_bomb", mcLoc("block/tnt_top")))
                            .build();
                });
        blockItem(ModBlocks.PRESSURE_BOMB);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("feraritab:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject, String appendix) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("feraritab:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix));
    }
}
