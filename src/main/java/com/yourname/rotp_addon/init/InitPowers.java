package com.yourname.rotp_addon.init;

import com.github.standobyte.jojo.action.Action;
import com.github.standobyte.jojo.power.impl.nonstand.type.NonStandPowerType;
import com.yourname.rotp_addon.AddonMain;
import com.yourname.rotp_addon.action.non_stand.*;
import com.yourname.rotp_addon.power.impl.nonstand.type.tutorial.TutorialPowerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;


public class InitPowers {
    public static final DeferredRegister<NonStandPowerType<?>> NON_STAND_POWERS = DeferredRegister.create(
            (Class<NonStandPowerType<?>>) ((Class<?>) NonStandPowerType.class), AddonMain.MOD_ID);
    public static final DeferredRegister<Action<?>> ACTIONS = DeferredRegister.create(
            (Class<Action<?>>) ((Class<?>) Action.class), AddonMain.MOD_ID);

    public static final RegistryObject<TutorialAction> BARRAGE = ACTIONS.register("barrage",
            () -> new TutorialBarrage(
                    new TutorialAction.Builder().heldWalkSpeed(0.5F)
            )
    );

    public static final RegistryObject<TutorialAction> INVISIBLE = ACTIONS.register("invisible",
            () -> new TutorialChangeEffects(
                    new TutorialAction.Builder().energyCost(15)
            )
    );

    public static final RegistryObject<TutorialAction> NIGHT_VISION = ACTIONS.register("night_vision",
            () -> new TutorialChangeEffects(
                    new TutorialAction.Builder().energyCost(15).shiftVariationOf(INVISIBLE)
            )
    );

    public static final RegistryObject<TutorialAction> REGENERATION = ACTIONS.register("regeneration",
            () -> new TutorialRegeneration(new TutorialAction.Builder().holdEnergyCost(10))
    );

    public static final RegistryObject<TutorialAction> TELEPORT = ACTIONS.register("teleport",
            () -> new TutorialTeleport(new TutorialAction.Builder().cooldown(100).energyCost(125))
    );

    public static final RegistryObject<TutorialPowerType> TUTORIAL = NON_STAND_POWERS.register("tutorial",
            () -> new TutorialPowerType(
                    new TutorialAction[]{ // attacks
                            BARRAGE.get(),
                    },
                    new TutorialAction[]{ // abilities
                            INVISIBLE.get(),
                            REGENERATION.get(),
                            TELEPORT.get()
                    },
                    REGENERATION.get() // default MMB
            ).withColor(0xb9faca));
}
