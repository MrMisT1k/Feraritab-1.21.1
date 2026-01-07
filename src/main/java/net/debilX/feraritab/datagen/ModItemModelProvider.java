package net.debilX.feraritab.datagen;

import net.minecraft.world.level.block.Block;
import net.debilX.feraritab.block.ModBlocks;
import net.debilX.feraritab.feraritab;
import net.debilX.feraritab.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, feraritab.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.RAW_PISA.get());
        basicItem(ModItems.SHITBALL.get());
        basicItem(ModItems.BLUE_BAMBOO.get());
        withExistingParent(ModItems.CHISEL.getId().toString(), mcLoc("item/generated"))
                .texture("layer0", modLoc("item/boom"));
        basicItem(ModItems.PISA.get());
        basicItem(ModItems.Z_BURGER.get());

        buttonItem(ModBlocks.PISA_BUTTON, ModBlocks.PISA_BLOCK);
        fenceItem(ModBlocks.PISA_FENCE, ModBlocks.PISA_BLOCK);
        wallItem(ModBlocks.PISA_WALL, ModBlocks.PISA_BLOCK);

        simpleBlockItem(ModBlocks.PISA_DOOR);
        trapdoorItem(ModBlocks.PISA_TRAPDOOR);

    }
    public void buttonItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(feraritab.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void fenceItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(feraritab.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void wallItem(RegistryObject<? extends Block> block, RegistryObject<Block> baseBlock) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("block/wall_inventory"))
                .texture("wall",  ResourceLocation.fromNamespaceAndPath(feraritab.MOD_ID,
                        "   block/" + ForgeRegistries.BLOCKS.getKey(baseBlock.get()).getPath()));
    }

    public void trapdoorItem(RegistryObject<? extends Block> block) {
        this.withExistingParent(ForgeRegistries.BLOCKS.getKey(block.get()).getPath(), mcLoc("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(feraritab.MOD_ID,
                        "block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<? extends Block> item) {
        return withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(feraritab.MOD_ID,"item/" + item.getId().getPath()));
    }
}
