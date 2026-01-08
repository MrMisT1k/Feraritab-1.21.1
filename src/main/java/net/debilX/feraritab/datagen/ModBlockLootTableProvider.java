package net.debilX.feraritab.datagen;

import net.debilX.feraritab.block.ModBlocks;
import net.debilX.feraritab.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.moddiscovery.ModValidator;
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
        dropSelf(ModBlocks.PRESSURE_BOMB.get());

        this.add(ModBlocks.PISA_ORE.get(),
                block -> createOreDrop(ModBlocks.PISA_ORE.get(), ModItems.RAW_PISA.get()));

        this.add(ModBlocks.SHIT_PRESSURE_PLATE.get(),
                block -> createSingleItemTableWithSilkTouch(ModBlocks.SHIT_PRESSURE_PLATE.get(), ModItems.SHITBALL.get()));

        dropSelf(ModBlocks.PISA_STAIRS.get());
        this.add(ModBlocks.PISA_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.PISA_SLAB.get()));

        dropSelf(ModBlocks.PISA_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.PISA_BUTTON.get());

        dropSelf(ModBlocks.PISA_FENCE.get());
        dropSelf(ModBlocks.PISA_FENCE_GATE.get());
        dropSelf(ModBlocks.PISA_WALL.get());

        this.add(ModBlocks.PISA_DOOR.get(),
                block -> createDoorTable(ModBlocks.PISA_DOOR.get()));
        dropSelf(ModBlocks.PISA_TRAPDOOR.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
