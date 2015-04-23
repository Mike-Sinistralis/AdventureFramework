package com.sinistralis.AdventureFramework.Stats.Attributes;

public class AttributeType {

    private String attributeName;
    private String attributeDescription;

    AttributeType(String name)
    {
        attributeName = name;
    }

    public String getAttributeName()
    {
        return attributeName;
    }

}
