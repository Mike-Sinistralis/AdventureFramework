package com.sinistralis.AdventureFramework.Stats;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class StatsInterceptor
{
    @SubscribeEvent
    public void interceptHurtEvent(LivingHurtEvent event)
    {
        if(!(event.entityLiving instanceof EntityPlayer))
            return;

        EntityPlayer player = (EntityPlayer) event.entityLiving;
    }

    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event)
    {

    }
}
