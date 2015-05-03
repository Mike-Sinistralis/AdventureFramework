package com.adventureframework.core.gui.draggable;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import org.lwjgl.opengl.GL11;

public class AdventureHealth extends Draggable {

    private ResourceLocation texture;

    public AdventureHealth(Minecraft mc, RenderGameOverlayEvent.ElementType element)
    {
        super(mc, element);
        texture = new ResourceLocation("adventureframework","textures/gui/healthbar.png");
        this.dragTo(2, 2);
    }

    @Override
    public void render()
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);

        this.mc.renderEngine.bindTexture(texture);
    }

}
