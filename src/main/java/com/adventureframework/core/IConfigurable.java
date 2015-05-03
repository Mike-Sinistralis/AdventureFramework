package com.adventureframework.core;

import java.util.Map;

/**
 * An interface that configurable content needs to implement.
 */
public interface IConfigurable {

    public Map<String, AdventureConfigurable> writeConfig();

    public void loadConfig(Map<String, String> config);
}
