package net.debilX.feraritab.datagen;

import net.debilX.feraritab.block.ModBlocks;
import net.debilX.feraritab.feraritab;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
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
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
