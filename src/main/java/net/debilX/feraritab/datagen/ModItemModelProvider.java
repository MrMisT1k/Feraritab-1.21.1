package net.debilX.feraritab.datagen;

import net.debilX.feraritab.feraritab;
import net.debilX.feraritab.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

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
    }
}
