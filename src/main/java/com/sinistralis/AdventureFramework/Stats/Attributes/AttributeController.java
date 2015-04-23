package com.sinistralis.AdventureFramework.Stats.Attributes;

import java.util.ArrayList;
import java.util.HashMap;

public final class AttributeController {

    private static HashMap<String, AttributeType> AttributeTypes = new HashMap<String, AttributeType>();

    AttributeController()
    {

    }

    public static void loadAttribute(String attributeName, AttributeType attr)
    {
        AttributeTypes.put(attributeName, attr);
    }

    public static AttributeType getAttributeType(String key)
    {
        return AttributeTypes.get(key);
    }

    public static Object[] getKnownAttributes()
    {
        return AttributeTypes.values().toArray();
    }



}
