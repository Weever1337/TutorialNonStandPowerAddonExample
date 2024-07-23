package com.yourname.rotp_addon.action.non_stand;

import com.github.standobyte.jojo.action.ActionTarget;
import com.github.standobyte.jojo.init.ModStatusEffects;
import com.github.standobyte.jojo.power.impl.nonstand.INonStandPower;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class TutorialChangeEffects extends TutorialAction {
    public TutorialChangeEffects(Builder builder) {
        super(builder);
    }

    @Override
    protected void perform(World world, LivingEntity user, INonStandPower power, ActionTarget target) {
        if (!isShiftVariation()) {
            if (user.hasEffect(ModStatusEffects.FULL_INVISIBILITY.get())) {
                user.removeEffect(ModStatusEffects.FULL_INVISIBILITY.get());
            } else {
                user.addEffect(new EffectInstance(ModStatusEffects.FULL_INVISIBILITY.get(), 999999, 999999, false, false, true));
            }
        } else {
            if (user.hasEffect(Effects.NIGHT_VISION)) {
                user.removeEffect(Effects.NIGHT_VISION);
            } else {
                user.addEffect(new EffectInstance(Effects.NIGHT_VISION, 999999, 999999, false, false, true));
            }
        }
    }
}
