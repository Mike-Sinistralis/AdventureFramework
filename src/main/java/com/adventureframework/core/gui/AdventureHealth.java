package com.adventureframework.core.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

class AdventureHealth extends Draggable {

    private static final ResourceLocation HEALTHBAR = new ResourceLocation("adventureframework","textures/gui/resourceBar.png");

    public AdventureHealth(Minecraft mc, RenderGameOverlayEvent.ElementType element)
    {
        super(mc, element);
    }

    @Override
    public void render(RenderGameOverlayEvent.Pre event)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);

        this.mc.getTextureManager().bindTexture(HEALTHBAR);

        //this.drawTexturedModalRect(
          //      event.resolution.getScaledWidth()/2 - 53, event.resolution.getScaledHeight()/2 - 5, 0, 0, 92, 8);

        Gui.drawRect(event.resolution.getScaledWidth()/2 - 46, event.resolution.getScaledHeight()/2 - 4, event.resolution.getScaledWidth()/2 + 46, event.resolution.getScaledHeight()/2 - 4, 0xFFFF0000);

        Gui.drawRect(0,0, 100, 10, 0xFFFF0000);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
