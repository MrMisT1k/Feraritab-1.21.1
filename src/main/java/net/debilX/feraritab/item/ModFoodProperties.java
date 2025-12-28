package net.debilX.feraritab.item;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties Z_BURGER = new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(1.2F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 400, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 6000, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 6000, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400, 3), 1.0F)
            .alwaysEdible()
            .build();

}
