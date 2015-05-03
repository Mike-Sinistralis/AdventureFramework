package com.adventureframework.core.enums;

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
