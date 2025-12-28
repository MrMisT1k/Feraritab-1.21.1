package net.debilX.feraritab.datagen;

import net.debilX.feraritab.block.ModBlocks;
import net.debilX.feraritab.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.PISA_BLOCK.get());
        dropSelf(ModBlocks.REZE_BLOCK.get());
        dropSelf(ModBlocks.SHIT_BLOCK.get());
        dropSelf(ModBlocks.SHIT_TURRET.get());

        this.add(ModBlocks.PISA_ORE.get(),
                block -> createOreDrop(ModBlocks.PISA_ORE.get(), ModItems.RAW_PISA.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
