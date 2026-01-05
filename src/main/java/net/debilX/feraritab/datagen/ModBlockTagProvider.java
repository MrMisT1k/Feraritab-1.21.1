package net.debilX.feraritab.datagen;

import net.debilX.feraritab.block.ModBlocks;
import net.debilX.feraritab.feraritab;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {

    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, feraritab.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.PISA_ORE.get())
                .add(ModBlocks.PISA_BLOCK.get())
                .add(ModBlocks.SHIT_TURRET.get());

        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlocks.SHIT_BLOCK.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.PISA_BLOCK.get())
                .add(ModBlocks.SHIT_TURRET.get())
                .add(ModBlocks.PISA_ORE.get())
                .add(ModBlocks.SHIT_BLOCK.get());

        tag(BlockTags.FENCES).add(ModBlocks.PISA_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(ModBlocks.PISA_FENCE_GATE.get());
        tag(BlockTags.WALLS).add(ModBlocks.PISA_WALL.get());

    }
}
