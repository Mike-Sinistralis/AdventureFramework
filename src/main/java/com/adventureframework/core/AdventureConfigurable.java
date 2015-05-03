package com.adventureframework.core;

import com.adventureframework.core.enums.AttributeCategory;

/**
 * A little utility class that assists with creating configuration data for Adventure Controllers.
 */
public class AdventureConfigurable {

    private String configValue;
    private String description;

    public AdventureConfigurable()
    {

    }

    public String getConfigValue() {
        return configValue;
    }

    public AdventureConfigurable setConfigValue(String value) {
        this.configValue = value;
        return this;
    }

    public AdventureConfigurable setConfigValue(Object value) {
        this.configValue = value.toString();
        return this;
    }

    public AdventureConfigurable setConfigValue(AttributeCategory value) {
        this.configValue = value.getFriendlyName();
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AdventureConfigurable setDescription(String description) {
        this.description = description;
        return this;
    }
}
