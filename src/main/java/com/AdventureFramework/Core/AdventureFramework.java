package com.adventureframework.Core;

import com.adventureframework.Common.ProxyCommon;
import com.adventureframework.Core.Enums.ConfigType;
import com.adventureframework.Stats.Attributes.AttributeController;
import com.adventureframework.Stats.StatsInterceptor;
import com.adventureframework.Utils.FunctionUtils;
import com.adventureframework.Content.Stats.StatLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

@Mod(modid = AdventureFramework.MODID, version = AdventureFramework.VERSION, name = AdventureFramework.MODNAME)
public class AdventureFramework
{
    private static Logger logger = LogManager.getLogger("adventureframework");

    public static final String MODID = "adventureframework";
    public static final String MODNAME ="adventureframework";
    public static final String VERSION = "0.1";

    @SidedProxy(clientSide = "com.adventureframework.Client.ProxyClient", serverSide = "com.adventureframework.Common.ProxyCommon")
    public static ProxyCommon proxy;

    @Instance(value = AdventureFramework.MODID)
    public static AdventureFramework instance;

    public static ConfigManager configManager;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        File AFDirectory;
        AttributeController attributeController = new AttributeController();

        AFDirectory = new File(FunctionUtils.getBaseDir(), "/adventureframework");
        configManager = new ConfigManager(AFDirectory);

        ControllerManager.registerControllerByName(ConfigType.ATTRIBUTES.name(), attributeController);

        StatLoader.stageAttributes(attributeController);

        attributeController.loadStagedAttributes();
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        FMLCommonHandler.instance().bus().register(instance);
        MinecraftForge.EVENT_BUS.register(new StatsInterceptor());
        FMLCommonHandler.instance().bus().register(new StatsInterceptor());
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {

    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {

    }
}
