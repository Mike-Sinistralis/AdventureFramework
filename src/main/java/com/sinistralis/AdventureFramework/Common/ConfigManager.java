package com.sinistralis.AdventureFramework.Common;

import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager
{
    private Map<String, Configuration> configFiles = new HashMap<>();
    private String rootDirectory;

    public ConfigManager(String rootDirectory)
    {
        this.rootDirectory = rootDirectory;
    }

    public Configuration getConfig(String configName)
    {
        return configFiles.get(configName);
    }

    public void loadConfig(String configName)
    {
        Configuration configToLoad = new Configuration(new File(), false);
    }

    public void saveConfig(String configName)
    {

    }

}

FEDIR = new File(FunctionHelper.getBaseDir(), "/ForgeEssentials");

