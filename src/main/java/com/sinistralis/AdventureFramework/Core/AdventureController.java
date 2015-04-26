package com.sinistralis.AdventureFramework.Core;

import net.minecraftforge.common.config.Configuration;

public abstract class AdventureController {

    protected static void loadConfig(String configName, IConfigurable[] configurables)
    {
        Configuration config = AdventureFramework.configManager.getConfig(configName);

        //Setup file, go through known Attributes
    }
}
