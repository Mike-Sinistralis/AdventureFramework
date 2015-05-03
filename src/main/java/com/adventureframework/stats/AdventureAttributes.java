package com.adventureframework.stats;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

class AdventureAttributes implements IExtendedEntityProperties
{
    public final static String extendedPropertiesName = "AdventureAttributes";
    protected Entity theEntity;
    protected World theWorld;

    AdventureAttributes()
    {

    }

    @Override
    public void saveNBTData(NBTTagCompound parCompound)
    {

    }

    @Override
    public void loadNBTData(NBTTagCompound parCompound)
    {

    }

    @Override
    public void init(Entity entity, World world)
    {
        theEntity = entity;
        theWorld = world;
    }
}
