package com.plouklapenguin.FoxyJumpscare;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class CommonProxy {

    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        Config.synchronizeConfiguration(event.getSuggestedConfigurationFile());

        FoxyJumpscare.LOG.info("I am FoxyJumpscare at version " + Tags.VERSION);
    }
}
