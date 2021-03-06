package com.aranaira.arcanearchives.client;

import com.aranaira.arcanearchives.inventory.ContainerRadiantCraftingTable;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class GUIRadiantCraftingTable extends GuiContainer
{

	private static final ResourceLocation GUITextures = new ResourceLocation("arcanearchives:textures/gui/radiantcraftingtable.png");


	public GUIRadiantCraftingTable(EntityPlayer player, ContainerRadiantCraftingTable container)
	{
		super(container);
		this.xSize = 206;
		this.ySize = 203;

	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		super.drawScreen(mouseX, mouseY, partialTicks);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableColorMaterial();
		this.mc.getTextureManager().bindTexture(GUITextures);

		drawModalRectWithCustomSizedTexture(guiLeft, guiTop, 0, 0, 256, 256, 256, 256);
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
}
