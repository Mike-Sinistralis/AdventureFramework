package com.adventureframework.Core.Enums;

public enum ConfigType {
    ATTRIBUTES ("Attributes"),
    EFFECTS ("Effects"),
    STATS ("Stats");

    private String friendlyName;

    private ConfigType(String friendlyName)
    {
        this.friendlyName = friendlyName;
    }

    public String getFriendlyName()
    {
        return this.friendlyName;
    }

    public String getConfigName()
    {
        return this.friendlyName.toLowerCase();
    }

}
