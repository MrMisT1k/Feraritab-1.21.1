package net.debilX.feraritab.datagen;

import net.debilX.feraritab.block.ModBlocks;
import net.debilX.feraritab.block.custom.RezeBlock;
import net.debilX.feraritab.feraritab;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
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

        pressurePlateBlock(ModBlocks.SHIT_PRESSURE_PLATE.get(), blockTexture(ModBlocks.SHIT_BLOCK.get()));

        blockItem(ModBlocks.SHIT_PRESSURE_PLATE);

        rezeBlock();
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
