package com.adventureframework.core;

import com.adventureframework.core.enums.ConfigType;

import java.util.HashMap;
import java.util.Map;

/**
 * A utility class that manages all controllers, and creates a simple place to reference them as needed.
 */
public class ControllerManager {

    private static Map<String, AdventureController> controllers= new HashMap<>();

    public static void registerControllerByName(ConfigType controllerName, AdventureController controller)
    {
        controllers.put(controllerName.getFriendlyName(), controller);
    }

    public static AdventureController[] getControllers()
    {
        return (AdventureController[])controllers.values().toArray();
    }

    public static AdventureController getControllerByName(ConfigType controllerName)
    {
        return controllers.get(controllerName.getFriendlyName());
    }

    public static void init()
    {
        for(AdventureController controller : controllers.values())
        {
            controller.init();
        }
    }

}
