package com.sinistralis.AdventureFramework.Stats.Attributes;

import java.util.HashMap;

public final class AttributeController {

    private static HashMap<String, Attribute> Attributes = new HashMap<String, Attribute>();

    AttributeController()
    {

    }

    public static void loadAttribute(String attributeName, Attribute attr)
    {
        Attributes.put(attributeName, attr);
    }

    public static Attribute getAttributeType(String key)
    {
        return Attributes.get(key);
    }

    public static Object[] getKnownAttributes()
    {
        return Attributes.values().toArray();
    }



}
