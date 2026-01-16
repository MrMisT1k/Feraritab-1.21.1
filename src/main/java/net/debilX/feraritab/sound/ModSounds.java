package net.debilX.feraritab.sound;

import net.debilX.feraritab.feraritab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENT =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, feraritab.MOD_ID);

    public static final RegistryObject<SoundEvent> ZERO_USE = registerSoundEvent("zero_use");
    public static final RegistryObject<SoundEvent> PATY_BLOCK = registerSoundEvent("paty_block");
    public static final RegistryObject<SoundEvent> PATY_1 = registerSoundEvent("paty_1");
    public static final RegistryObject<SoundEvent> PATY_2 = registerSoundEvent("paty_2");

    public static final RegistryObject<SoundEvent> ZERO_BLOCK_BREAK = registerSoundEvent("zero_block_break");
    public static final RegistryObject<SoundEvent> ZERO_BLOCK_STEP = registerSoundEvent("zero_block_step");
    public static final RegistryObject<SoundEvent> ZERO_BLOCK_PLACE = registerSoundEvent("zero_block_place");
    public static final RegistryObject<SoundEvent> ZERO_BLOCK_HIT = registerSoundEvent("zero_block_hit");
    public static final RegistryObject<SoundEvent> ZERO_BLOCK_FALL = registerSoundEvent("zero_block_fall");

    public static final ForgeSoundType ZERO_BLOCK_SOUNDS = new ForgeSoundType(1f, 1f,
            ZERO_BLOCK_BREAK, ZERO_BLOCK_STEP,
            ZERO_BLOCK_PLACE, ZERO_BLOCK_HIT, ZERO_BLOCK_FALL);


    private static RegistryObject<SoundEvent> registerSoundEvent(String name){
        return SOUND_EVENT.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(feraritab.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENT.register(eventBus);
    }
}
