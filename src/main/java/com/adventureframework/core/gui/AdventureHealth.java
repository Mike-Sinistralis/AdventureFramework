package com.adventureframework.core.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

class AdventureHealth extends Draggable {

    private static final ResourceLocation HEALTHBAR = new ResourceLocation("adventureframework","textures/gui/healthbar.png");

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

        this.drawTexturedModalRect(
                event.resolution.getScaledWidth()/2 - 52, event.resolution.getScaledHeight()/2 - 5, 0, 0, 106, 10);
    }
}
