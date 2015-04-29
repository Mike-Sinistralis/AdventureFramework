package com.adventureframework.stats.attributes;

import com.adventureframework.core.AdventureController;
import com.adventureframework.core.AdventureFramework;
import com.adventureframework.core.enums.ConfigType;
import com.adventureframework.core.exceptions.AttributeAlreadyExistsException;
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
            throw new AttributeAlreadyExistsException("Attributes must be uniquely named! Attribute " + attr.getName() + " already exists!");
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

        config.save();
    }

    public void loadConfig()
    {
        String configName = ConfigType.STATS.getFriendlyName();
        Configuration config = AdventureFramework.configManager.getConfigByName(configName);

        config.load();

        for(Attribute attribute : stagedAttributes.values())
        {
            readControllerData(config, attribute.getName(), attribute);
        }
    }
}
