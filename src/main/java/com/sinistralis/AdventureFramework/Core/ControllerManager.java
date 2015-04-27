package com.sinistralis.AdventureFramework.Core;

import com.sinistralis.AdventureFramework.Core.Enums.ConfigType;

import java.util.HashMap;
import java.util.Map;

public class ControllerManager {

    private static Map<String, AdventureController> controllers= new HashMap<>();

    public static void registerControllerByName(String name, AdventureController controller)
    {
        controllers.put(name, controller);
    }

    public static AdventureController[] getControllers()
    {
        return (AdventureController[])controllers.values().toArray();
    }

    public static AdventureController getControllerByName(ConfigType controllerName)
    {
        return controllers.get(controllerName.name());
    }

}
