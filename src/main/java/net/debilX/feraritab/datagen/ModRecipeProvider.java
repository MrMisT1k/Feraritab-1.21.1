package net.debilX.feraritab.datagen;

import net.debilX.feraritab.block.ModBlocks;
import net.debilX.feraritab.feraritab;
import net.debilX.feraritab.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.fml.loading.moddiscovery.ModValidator;
import org.apache.logging.log4j.core.appender.rolling.action.PathSortByModificationTime;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        List<ItemLike> PISA_SMELTABLES = List.of(ModItems.RAW_PISA.get());//Сюда помещаем все предметы, которые при переплавке дают raw_pisa

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SHIT_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', Items.BROWN_DYE)
                .unlockedBy(getHasName(Items.BROWN_DYE), has(Items.BROWN_DYE)).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.PISA_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.PISA.get())
                .unlockedBy(getHasName(ModItems.PISA.get()), has(ModItems.PISA.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.SHIT_TURRET.get())
                .pattern(" A ")
                .pattern("ABA")
                .pattern(" A ")
                .define('A', Items.DISPENSER)
                .define('B', ModBlocks.SHIT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.SHIT_BLOCK.get()), has(ModBlocks.SHIT_BLOCK.get())).save(pRecipeOutput);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BLUE_BAMBOO.get(), 1)
                .requires(Items.BAMBOO)
                .requires(Items.BLUE_DYE)
                .unlockedBy(getHasName(Items.BAMBOO), has(Items.BAMBOO)).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.SHITBALL.get(), 4)
                .requires(ModBlocks.SHIT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.SHIT_BLOCK.get()), has(ModBlocks.SHIT_BLOCK.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PISA.get(), 9)
                .requires(ModBlocks.PISA_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.PISA_BLOCK.get()), has(ModBlocks.PISA_BLOCK.get())).save(pRecipeOutput, feraritab.MOD_ID);


        oreSmelting(pRecipeOutput, PISA_SMELTABLES, RecipeCategory.MISC, ModItems.PISA.get(), 0.25f, 200, "pisa");
        oreBlasting(pRecipeOutput, PISA_SMELTABLES, RecipeCategory.MISC, ModItems.PISA.get(), 0.25f, 200, "pisa");
    }


    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, feraritab.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}