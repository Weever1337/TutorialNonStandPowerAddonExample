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

    public static void loadRegistryObjects() {}

    public static final RegistryObject<TutorialAction> BARRAGE = ACTIONS.register("barrage",
            () -> new TutorialBarrage(
                    new TutorialAction.Builder().heldWalkSpeed(0.5F)
            )
    );

    public static final RegistryObject<TutorialAction> SENDO_OVERDRIVE = ACTIONS.register("sendo_overdrive",
            () -> new TutorialSendoOverdrive(
                    new TutorialAction.Builder().cooldown(25).energyCost(200).needsFreeMainHand().swingHand()
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

    public static final RegistryObject<TutorialAction> WALL_CLIMB = ACTIONS.register("wall_climb",
            () -> new TutorialWallClimb(new TutorialAction.Builder())
    );

    public static final RegistryObject<TutorialAction> TELEPORT = ACTIONS.register("teleport",
            () -> new TutorialTeleport(new TutorialAction.Builder().cooldown(100).energyCost(125))
    );

    public static final RegistryObject<TutorialPowerType> TUTORIAL = NON_STAND_POWERS.register("tutorial",
            ()-> new TutorialPowerType(
                    new TutorialAction[] { // attacks
                            BARRAGE.get(),
                            SENDO_OVERDRIVE.get(),
                    },
                    new TutorialAction[] { // abilities
                            INVISIBLE.get(),
                            WALL_CLIMB.get(),
                            TELEPORT.get()
                    },
                    TELEPORT.get() // default MMB
            ));
}
