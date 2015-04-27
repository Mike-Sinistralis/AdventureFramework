package com.sinistralis.AdventureFramework.Core;

import com.sinistralis.AdventureFramework.Core.Enums.ConfigType;
import net.minecraftforge.common.config.Configuration;

public abstract class AdventureController {

    protected void loadConfig(ConfigType configType, IConfigurable[] configurables)
    {
        String configName = configType.getFriendlyName();
        Configuration config = AdventureFramework.configManager.getConfigByName(configName);

        config.load();

        //Setup file, go through known Attributes
    }

    protected boolean loadProperty(ConfigType configType, IConfigurable loadable)
    {
        String configName = configType.getFriendlyName();
        Configuration config = AdventureFramework.configManager.getConfigByName(configName);

        config.load();

        //Write loadable to config. If successful, return true. Else false

        return true;
    }
}
