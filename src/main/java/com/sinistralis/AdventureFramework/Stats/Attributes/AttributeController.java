package com.sinistralis.AdventureFramework.Stats.Attributes;

import com.sinistralis.AdventureFramework.Core.AdventureController;
import com.sinistralis.AdventureFramework.Core.AdventureFramework;
import com.sinistralis.AdventureFramework.Core.Enums.ConfigType;
import com.sinistralis.AdventureFramework.Core.IConfigurable;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.AttributeType;
import net.minecraftforge.common.config.Configuration;

import java.util.HashMap;

public final class AttributeController extends AdventureController{

    private static HashMap<String, Attribute> Attributes = new HashMap<String, Attribute>();

    public static void loadAttribute(Attribute attr)
    {
        Attributes.put(attr.getName(), attr);
    }

    public static Attribute getNewAttributeByName(String key)
    {
        return Attribute.cloneStructure(Attributes.get(key));
    }

    public static Attribute[] getKnownAttributes()
    {
        return (Attribute[]) Attributes.values().toArray();
    }

    public static void loadConfig()
    {
        loadConfig(ConfigType.ATTRIBUTES.name(), getKnownAttributes());
    }



}
