package net.debilX.feraritab.item;

import net.debilX.feraritab.feraritab;
import net.debilX.feraritab.item.custom.ChiselItem;
import net.debilX.feraritab.item.custom.ShitballItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, feraritab.MOD_ID);

    public static final RegistryObject<Item> PISA = ITEMS.register("pisa",
        () -> new Item(new Item.Properties()));
    //Регистрация предмета
    public static final RegistryObject<Item> RAW_PISA = ITEMS.register("raw_pisa",
        () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BLUE_BAMBOO = ITEMS.register("blue_bamboo",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(3)));

    public static final RegistryObject<Item> SHITBALL = ITEMS.register("shitball",
            () -> new ShitballItem(new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
