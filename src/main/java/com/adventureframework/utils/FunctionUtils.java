package com.adventureframework.utils;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;

import java.io.File;

/**
   Just a tiny library of functions I have collected to make certain tasks easier.
 */
public final class FunctionUtils {

    /**
     * Gets the base directory depending on which side calls the method.
     *
     * @return A file containing the base directory based on side.
     */
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
