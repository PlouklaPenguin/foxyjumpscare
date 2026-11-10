package com.plouklapenguin.FoxyJumpscare;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(
    modid = FoxyJumpscare.MODID,
    version = Tags.VERSION,
    name = "FoxyJumpscare",
    acceptedMinecraftVersions = "[1.7.10]",
    dependencies = "required-after:gtnhlib@[0.9.41,);")
public class FoxyJumpscare {

    public static final String MODID = "WitheredFoxyGTNH";
    public static final Logger LOG = LogManager.getLogger(MODID);

    @SidedProxy(
        clientSide = "com.plouklapenguin.FoxyJumpscare.ClientProxy",
        serverSide = "com.plouklapenguin.FoxyJumpscare.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    // preInit "Run before anything else. Read your config, create blocks, items, etc, and register them with the
    // GameRegistry." (Remove if not needed)
    public void preInit(FMLPreInitializationEvent event) {
        proxy.preInit(event);
    }
}
