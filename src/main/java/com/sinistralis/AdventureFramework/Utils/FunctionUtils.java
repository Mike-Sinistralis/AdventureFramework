package com.sinistralis.AdventureFramework.Utils;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.io.File;

public final class FunctionUtils {

    public static File getBaseDir()
    {
        if (FMLCommonHandler.instance().getSide().isClient())
        {
            return Minecraft.getMinecraft().mcDataDir;
        }
        else
        {
            return new File(".");
        }
    }
}
