package com.sinistralis.AdventureFramework.Core;

import com.sinistralis.AdventureFramework.Common.ConfigManager;
import com.sinistralis.AdventureFramework.Common.ProxyCommon;
import com.sinistralis.AdventureFramework.Stats.StatsInterceptor;
import com.sinistralis.AdventureFramework.Utils.FunctionUtils;
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
    private static Logger logger = LogManager.getLogger("AdventureFramework");
    private File AFDirectory;

    public static final String MODID = "AdventureFramework";
    public static final String MODNAME ="AdventureFramework";
    public static final String VERSION = "0.1";

    @SidedProxy(clientSide = "com.sinistralis.AdventureFramework.Client.ProxyClient", serverSide = "com.sinistralis.AdventureFramework.Common.ProxyCommon")
    public static ProxyCommon proxy;

    @Instance(value = AdventureFramework.MODID)
    public static AdventureFramework instance;

    public static ConfigManager configManager;


    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        AFDirectory = new File(FunctionUtils.getBaseDir(), "/AdventureFramework");

        configManager = new ConfigManager(AFDirectory);

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
