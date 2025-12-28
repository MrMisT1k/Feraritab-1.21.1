package net.debilX.feraritab.item;

import net.debilX.feraritab.block.ModBlocks;
import net.debilX.feraritab.feraritab;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MOD_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, feraritab.MOD_ID);

    public static final RegistryObject<CreativeModeTab> DEBIL1 = CREATIVE_MOD_TABS.register("debil1",
            ()-> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.PISA.get()))
                    .title(Component.translatable("creativetab.feraritab.debil1"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept((ModItems.PISA.get()));
                        pOutput.accept(ModItems.RAW_PISA.get());
                        pOutput.accept(ModBlocks.PISA_BLOCK.get());
                        pOutput.accept(ModBlocks.PISA_ORE.get());
                        pOutput.accept((ModItems.CHISEL.get()));
                        pOutput.accept((ModBlocks.REZE_BLOCK.get()));


                    }).build());

    public static final RegistryObject<CreativeModeTab> DEBIL2 = CREATIVE_MOD_TABS.register("debil2",
            ()-> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.SHIT_BLOCK.get()))
                    .withTabsBefore(DEBIL1.getId())
                    .title(Component.translatable("creativetab.feraritab.debil2"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept((ModItems.BLUE_BAMBOO.get()));
                        pOutput.accept((ModItems.SHITBALL.get()));
                        pOutput.accept(ModBlocks.SHIT_BLOCK.get());

                    }).build());



    public static void register(IEventBus eventBus){
        CREATIVE_MOD_TABS.register(eventBus);
    }
}
