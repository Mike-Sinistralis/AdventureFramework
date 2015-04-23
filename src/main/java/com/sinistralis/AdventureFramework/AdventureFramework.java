package com.sinistralis.AdventureFramework;

import com.sinistralis.AdventureFramework.Common.ProxyCommon;
import com.sinistralis.AdventureFramework.Content.AdventureContentLoader;
import com.sinistralis.AdventureFramework.Stats.StatConfigurationHandler;
import com.sinistralis.AdventureFramework.Stats.StatsInterceptor;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.common.config.Configuration;
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


@Mod(modid = AdventureFramework.MODID, version = AdventureFramework.VERSION, name = AdventureFramework.MODNAME)
public class AdventureFramework
{
    private static Logger logger = LogManager.getLogger("AdventureFramework");

    public static final String MODID = "AdventureFramework";
    public static final String MODNAME ="AdventureFramework";
    public static final String VERSION = "0.1";

    @SidedProxy(clientSide = "com.ForgeEssentials.ProxyClient", serverSide = "com.ForgeEssentials.ProxyCommon")
    public static ProxyCommon proxy;
    
    @Instance(value = AdventureFramework.MODID)
    public static AdventureFramework instance;

    public static StatConfigurationHandler statConfig;

    private void setupAdventureStatsConfiguration(FMLPreInitializationEvent event)
    {
        AdventureContentLoader.loadCoreContent();

        statConfig = new StatConfigurationHandler(new Configuration(event.getSuggestedConfigurationFile()));
        statConfig.loadConfiguration();
    }


    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        setupAdventureStatsConfiguration(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        FMLCommonHandler.instance().bus().register(instance);
        MinecraftForge.EVENT_BUS.register(new StatsInterceptor());
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
