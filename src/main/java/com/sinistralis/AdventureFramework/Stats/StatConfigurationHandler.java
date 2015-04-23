package com.sinistralis.AdventureFramework.Stats;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;


public class StatConfigurationHandler
{

    private Configuration config;

    public StatConfigurationHandler(Configuration statConfig)
    {
        config = statConfig;
    }

    public void loadConfiguration()
    {
        ConfigCategory attributes = config.getCategory("attributeConfig");
        Property test = config.get(attributes.getName(), "test", "nothing");



        config.load();


        config.save();
    }

    public void saveConfiguration()
    {

    }

}
