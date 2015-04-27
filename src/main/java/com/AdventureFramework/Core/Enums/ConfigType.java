package com.adventureframework.core.enums;

public enum ConfigType {
    ATTRIBUTES ("attributes"),
    EFFECTS ("Effects"),
    STATS ("stats");

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
