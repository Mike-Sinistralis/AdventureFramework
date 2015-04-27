package com.sinistralis.AdventureFramework.Stats.Attributes;

import com.sinistralis.AdventureFramework.Core.AdventureController;
import com.sinistralis.AdventureFramework.Core.Enums.ConfigType;
import com.sinistralis.AdventureFramework.Core.Exceptions.AttributeAlreadyExistsException;

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
        for(Attribute attribute : stagedAttributes.values())
        {
            if(loadProperty(ConfigType.ATTRIBUTES, attribute))
            {
                loadedAttributes.remove(attribute.getName());
                stagedAttributes.put(attribute.getName(), attribute);
            }
        }
    }

    public void loadConfig()
    {
        loadConfig(ConfigType.ATTRIBUTES, getKnownAttributes());
    }
}
