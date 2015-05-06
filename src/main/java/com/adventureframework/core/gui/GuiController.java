package com.adventureframework.core.gui;

import com.adventureframework.core.AdventureController;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * A controller that handles all menu or static gui interactions and events.
 */
public class GuiController extends AdventureController {

    @SubscribeEvent
    public void onOptionMenuShow(GuiOpenEvent event)
    {
        if(event.gui instanceof GuiIngameMenu)
        {
            event.gui = new AdventureMenu();
        }
    }

}
