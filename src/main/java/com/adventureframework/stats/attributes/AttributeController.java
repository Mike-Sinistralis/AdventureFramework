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

    /**
     * Stages an Attribute Factory to be loaded into the game system. Factories are loaded during preInit for default properties, but
     * can be called again at any point to load new attributes.
     *
     * @param attributeFactory The factory to stage.
     */
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

    /**
     * @return An array of all loaded Attribute Factories
     */
    public AttributeFactory[] getKnownAttributeFactories()
    {
        return (AttributeFactory[]) loadedAttributeFactories.values().toArray();
    }

    /**
     * Initializes all stages Attribute Factories and generates config files for them. If config files exists, loads in new values.
     * This can be called at any time to load custom attributes, but preferably do it during postInit.
     */
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

    /**
     * Checks each Attribute Factory for new configurations to load.
     */
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
