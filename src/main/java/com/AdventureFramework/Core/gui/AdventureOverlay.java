package com.adventureframework.core.gui;

import com.adventureframework.core.gui.draggable.Draggable;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.MouseEvent;

import java.util.Map;
import java.util.HashMap;

public class AdventureOverlay {

    private Map<String, Draggable> AdventureOverlays = new HashMap<>();

    @SubscribeEvent
    public void gui(RenderGameOverlayEvent event)
    {
        RenderGameOverlayEvent.ElementType overlay = event.type;


    }
}
