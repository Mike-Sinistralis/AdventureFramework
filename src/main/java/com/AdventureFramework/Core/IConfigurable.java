package com.adventureframework.Core;

import java.util.Map;

public interface IConfigurable {

    public Map<String, String> writeConfig();

    public void loadConfig(Map<String, String> config);
}
