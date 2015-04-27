package com.sinistralis.AdventureFramework.Stats.Attributes;

import com.sinistralis.AdventureFramework.Core.AdventureController;
import com.sinistralis.AdventureFramework.Core.AdventureFramework;
import com.sinistralis.AdventureFramework.Core.Enums.ConfigType;
import com.sinistralis.AdventureFramework.Core.Exceptions.AttributeAlreadyExistsException;
import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.Configuration;

import java.util.HashMap;
import java.util.Map;

public class AttributeController extends AdventureController{

    private Map<String, Attribute> loadedAttributes = new HashMap<>();
    private Map<String, Attribute> stagedAttributes = new HashMap<>();

    public void stageAttribute(Attribute attr)
    {
        if(loadedAttributes.get(attr.getName()) == null)
        {
            stagedAttributes.put(attr.getName(), attr);
        }
        else
        {
            throw new AttributeAlreadyExistsException();
        }
    }

    public Attribute getNewAttributeByName(String key)
    {
        Attribute returnAttribute = null;

        if(loadedAttributes.get(key) != null)
        {
            returnAttribute = Attribute.cloneStructure(loadedAttributes.get(key));
        }
        return returnAttribute;
    }

    public Attribute[] getKnownAttributes()
    {
        return (Attribute[]) loadedAttributes.values().toArray();
    }

    public void loadStagedAttributes()
    {
        Configuration config = AdventureFramework.configManager.getConfigByName(ConfigType.STATS.getFriendlyName());

        config.load();

        for(Attribute attribute : stagedAttributes.values())
        {
            readControllerData(config, attribute.getName(), attribute);
            writeControllerData(config, attribute.getName(), attribute);

            loadedAttributes.put(attribute.getName(), attribute);
        }

        stagedAttributes.clear();

        config.addCustomCategoryComment("armor", "Explanation about each field goes here");

        config.save();
    }

    public void loadConfig()
    {
        String configName = ConfigType.STATS.getFriendlyName();
        Configuration config = AdventureFramework.configManager.getConfigByName(configName);
        ConfigCategory masterCategory = config.getCategory(ConfigType.ATTRIBUTES.getConfigName());

        config.load();

        for(Attribute attribute : stagedAttributes.values())
        {
            readControllerData(config, attribute.getName(), attribute);
        }
    }
}
