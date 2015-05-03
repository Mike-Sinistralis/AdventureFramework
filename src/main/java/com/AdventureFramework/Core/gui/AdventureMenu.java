package com.adventureframework.core.gui;

import com.adventureframework.core.gui.locked.UIPositioner;
import net.minecraft.client.gui.*;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.io.IOException;
import java.util.Iterator;

@SideOnly(Side.CLIENT)
public class AdventureMenu extends GuiIngameMenu {

    private int buttonId = 20;

    @Override
    public void initGui()
    {
        super.initGui();
        int currentRow = this.height / 4 + 24 + -16;

        //Remove exit button, iterate through all buttons, then add ours and re-add exit.
        Iterator<GuiButton> it = buttonList.iterator();
        while (it.hasNext()) {
            GuiButton button = it.next();
            if (button.id == 1) {
                it.remove();
            }
            else if (button.yPosition > currentRow)
            {
                currentRow = button.yPosition;
            }

            if(button.id == buttonId)
            {
                buttonId += 1;
            }
        }

        this.buttonList.add(new GuiButton(20, this.width / 2 - 100, currentRow + 24, "Configure UI"));
        this.buttonList.add(new GuiButton(1, this.width / 2 - 100, currentRow + 48, I18n.format("menu.returnToMenu", new Object[0])));
    }

    protected void actionPerformed(GuiButton button) throws IOException
    {
        super.actionPerformed(button);
        if(button.id == buttonId)
        {
            this.mc.displayGuiScreen(new UIPositioner());
        }
    }

}
