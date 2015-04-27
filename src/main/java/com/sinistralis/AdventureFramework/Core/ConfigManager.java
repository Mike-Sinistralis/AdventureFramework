package com.sinistralis.AdventureFramework.Core;

import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ConfigManager
{
    private Map<String, Configuration> configFiles = new HashMap<>();
    private File rootDirectory;

    public ConfigManager(File rootDirectory)
    {
        if(!rootDirectory.exists())
        {
            rootDirectory.mkdirs();
        }

        this.rootDirectory = rootDirectory;
    }

    public Configuration getConfigByName(String configName)
    {
        Configuration config = configFiles.get(configName);
        if(config == null)
        {
            config = new Configuration(new File(this.rootDirectory, configName + ".cfg"), false);
            configFiles.put(configName, config);
        }
        return config;
    }

    public void load()
    {
        for(Configuration config : configFiles.values())
        {
            config.load();
        }
    }

    public void saveConfig(String configName)
    {
        configFiles.get(configName).save();
    }

    public void saveAll()
    {
        for(Configuration config : configFiles.values())
        {
            config.save();
        }
    }

}
