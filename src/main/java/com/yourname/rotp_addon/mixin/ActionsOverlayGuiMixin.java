package com.yourname.rotp_addon.mixin;

import com.github.standobyte.jojo.client.ui.actionshud.ActionsOverlayGui;
import com.github.standobyte.jojo.power.IPower;
import com.yourname.rotp_addon.init.AddonPowers;
import com.yourname.rotp_addon.power.impl.nonstand.type.tutorial.TutorialPowerType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ActionsOverlayGui.class)
// Remove it with new Ripples of the Past update
public abstract class ActionsOverlayGuiMixin {
    @Inject (method = "getPowerUiColor", at = @At("RETURN"), cancellable = true)
    private static void injected(IPower<?, ?> power, CallbackInfoReturnable<Integer> cir){
        if (power.getType() == AddonPowers.TUTORIAL.get()){
            cir.setReturnValue(TutorialPowerType.COLOR);
        }
    }
}