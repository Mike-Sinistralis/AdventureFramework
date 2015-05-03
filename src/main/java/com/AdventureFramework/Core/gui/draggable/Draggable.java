package com.adventureframework.core.gui.draggable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public abstract class Draggable extends Gui {

    protected Minecraft mc;
    private RenderGameOverlayEvent.ElementType elementType;
    private boolean isDragged = false;
    private int anchorX;
    private int anchorY;

    public Draggable(Minecraft mc, RenderGameOverlayEvent.ElementType element)
    {
        super();

        this.mc = mc;
        this.elementType = element;
    }

    public RenderGameOverlayEvent.ElementType getElementType() {
        return elementType;
    }

    public void render()
    {

    }

    public boolean containsCoordinate(int x, int y)
    {
        return false;
    }

    public void dragTo(int newX, int newY)
    {
        this.anchorX = newX;
        this.anchorY = newY;
    }

    //TODO: Write Client Config
    //TODO: Save Client Config
}
