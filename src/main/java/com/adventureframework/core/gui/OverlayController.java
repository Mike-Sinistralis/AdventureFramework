package com.adventureframework.core.gui;

import com.adventureframework.core.AdventureController;
import com.adventureframework.core.gui.draggable.AdventureHealth;
import com.adventureframework.core.gui.draggable.Draggable;
import com.adventureframework.core.gui.locked.AdventureMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Map;
import java.util.HashMap;

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
