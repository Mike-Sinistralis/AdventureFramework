package com.adventureframework.core.gui;

import com.adventureframework.core.AdventureController;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;

public class OverlayController extends AdventureController {

    private Map<String, Draggable> AdventureOverlays = new HashMap<>();

    private Minecraft mc;

    public void init()
    {
        mc = Minecraft.getMinecraft();
        AdventureHealth health = new AdventureHealth(mc, RenderGameOverlayEvent.ElementType.HEALTH);

        AdventureOverlays.put(RenderGameOverlayEvent.ElementType.HEALTH.name(), health);
    }

    public void loadDraggable(RenderGameOverlayEvent.ElementType overlayToOverwrite, Draggable draggable)
    {
        if(AdventureOverlays.containsKey(overlayToOverwrite.name()))
        {
            AdventureOverlays.remove(overlayToOverwrite.name());
        }

        AdventureOverlays.put(overlayToOverwrite.name(), draggable);
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void beforeOverlayRender(RenderGameOverlayEvent.Pre event)
    {
        RenderGameOverlayEvent.ElementType overlay = event.type;

        if(AdventureOverlays.containsKey(event.type.name()) && !event.isCanceled())
        {
            AdventureOverlays.get(event.type.name()).render();
            event.setCanceled(true);
            mc.getTextureManager().bindTexture(Gui.icons);
        }
    }
}
