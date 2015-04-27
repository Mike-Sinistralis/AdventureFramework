package com.sinistralis.AdventureFramework.Core.Enums;

public enum ConfigType {
    ATTRIBUTES ("Attributes"),
    EFFECTS ("Effects");

    private String friendlyName;

    private ConfigType(String friendlyName)
    {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName()
    {
        return this.friendlyName;
    }
}
