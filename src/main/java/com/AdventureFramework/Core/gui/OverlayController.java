package com.adventureframework.core.gui;

import com.adventureframework.core.AdventureController;
import com.adventureframework.core.gui.draggable.AdventureHealth;
import com.adventureframework.core.gui.draggable.Draggable;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Map;
import java.util.HashMap;

public class OverlayController extends AdventureController {

    private Map<String, Draggable> AdventureOverlays = new HashMap<>();

    public void init()
    {
        Minecraft mc = Minecraft.getMinecraft();
        AdventureHealth health = new AdventureHealth(mc, RenderGameOverlayEvent.ElementType.HEALTH);

        AdventureOverlays.put(RenderGameOverlayEvent.ElementType.HEALTH.name(), health);
    }

    public void loadDraggable(Draggable draggable)
    {
        AdventureOverlays.put(draggable.getElementType().name(), draggable);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void gui(RenderGameOverlayEvent event)
    {
        RenderGameOverlayEvent.ElementType overlay = event.type;

        if(event.type == RenderGameOverlayEvent.ElementType.HEALTH && !event.isCanceled())
        {
            AdventureOverlays.get(event.type.name()).render();
            event.setCanceled(true);
        }
    }
}
