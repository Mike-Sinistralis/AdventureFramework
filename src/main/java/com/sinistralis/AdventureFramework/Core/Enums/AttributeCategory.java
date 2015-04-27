package com.sinistralis.AdventureFramework.Core.Enums;

public enum AttributeCategory {
    PRIMARY ("Primary"),
    SECONDARY ("Secondary");

    private String friendlyName;

    private AttributeCategory(String friendlyName)
    {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName()
    {
        return this.friendlyName;
    }
}
