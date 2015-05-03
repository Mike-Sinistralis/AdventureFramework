package com.adventureframework.core.gui.draggable;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

public class AdventureHealth extends Draggable {

    private static final ResourceLocation HEALTHBAR = new ResourceLocation("adventureframework","textures/gui/healthbar.png");

    public AdventureHealth(Minecraft mc, RenderGameOverlayEvent.ElementType element)
    {
        super(mc, element);
        this.dragTo(2, 2);
    }

    @Override
    public void render()
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);

        this.mc.getTextureManager().bindTexture(HEALTHBAR);

        this.drawTexturedModalRect(
                this.anchorX, this.anchorY, 0, 0, 130, 20);
    }
}
