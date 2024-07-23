package com.yourname.rotp_addon.power.impl.nonstand.type.tutorial;

import com.yourname.rotp_addon.init.InitPowers;
import net.minecraftforge.eventbus.api.IEventBus;

public class TutorialCustomRegistries {
    public static void initCustomRegistries(IEventBus modEventBus) {
        InitPowers.loadRegistryObjects();
        InitPowers.NON_STAND_POWERS.register(modEventBus);
    }
}
