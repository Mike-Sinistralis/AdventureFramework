package com.sinistralis.AdventureFramework.Core;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

public interface IConfigurable {

    public JsonObjectBuilder writeConfigJson();

    public void loadConfigJson(JsonObject config);
}
