package com.plouklapenguin.FoxyJumpscare;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class Config {

    public static int chance = 10000;

    public static void synchronizeConfiguration(File configFile) {
        Configuration configuration = new Configuration(configFile);

        chance = configuration.getInt(
            "Chance",
            Configuration.CATEGORY_GENERAL,
            chance,
            0,
            1000000,
            "The chance of foxy appearing, 1/{chance}.");

        if (configuration.hasChanged()) {
            configuration.save();
        }
    }
}
