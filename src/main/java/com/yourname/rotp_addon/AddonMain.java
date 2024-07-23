package com.yourname.rotp_addon;

import com.yourname.rotp_addon.init.InitPowers;
import com.yourname.rotp_addon.power.impl.nonstand.type.tutorial.TutorialCustomRegistries;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(AddonMain.MOD_ID)
public class AddonMain {
    public static final String MOD_ID = "rotp_addon";
    public static final Logger LOGGER = LogManager.getLogger();

    public AddonMain() {
        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        InitPowers.ACTIONS.register(modEventBus);
        TutorialCustomRegistries.initCustomRegistries(modEventBus);
//        InitEntities.ENTITIES.register(modEventBus);
    }

    public static Logger getLogger() {
        return LOGGER;
    }
}
