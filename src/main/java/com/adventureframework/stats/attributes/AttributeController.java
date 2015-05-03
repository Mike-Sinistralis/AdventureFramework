package com.adventureframework.stats.attributes;

import com.adventureframework.core.AdventureController;
import com.adventureframework.core.AdventureFramework;
import com.adventureframework.core.enums.ConfigType;
import com.adventureframework.core.exceptions.AttributeAlreadyExistsException;
import net.minecraftforge.common.config.Configuration;

import java.util.HashMap;
import java.util.Map;

public class AttributeController extends AdventureController {

    private Map<String, AttributeFactory> loadedAttributeFactories = new HashMap<>();
    private Map<String, AttributeFactory> stagedAttributeFactories = new HashMap<>();

    public void stageAttributeFactory(AttributeFactory attributeFactory)
    {
        if(loadedAttributeFactories.get(attributeFactory.getName()) == null)
        {
            stagedAttributeFactories.put(attributeFactory.getName(), attributeFactory);
        }
        else
        {
            throw new AttributeAlreadyExistsException("Attributes must be uniquely named! Attribute " + attributeFactory.getName() + " already exists!");
        }
    }

    public AttributeFactory[] getKnownAttributeFactories()
    {
        return (AttributeFactory[]) loadedAttributeFactories.values().toArray();
    }

    @Override
    public void init()
    {
        Configuration config = AdventureFramework.configManager.getConfigByName(ConfigType.STATS.getFriendlyName());

        config.load();

        for(AttributeFactory factory : stagedAttributeFactories.values())
        {
            readControllerData(config, factory.getName(), factory);
            writeControllerData(config, factory.getName(), factory);
            loadedAttributeFactories.put(factory.getName(), factory);
        }

        stagedAttributeFactories.clear();

        config.save();
    }

    public void loadConfig()
    {
        String configName = ConfigType.STATS.getFriendlyName();
        Configuration config = AdventureFramework.configManager.getConfigByName(configName);

        config.load();

        for(AttributeFactory factory : stagedAttributeFactories.values())
        {
            readControllerData(config, factory.getName(), factory);
        }
    }
}
