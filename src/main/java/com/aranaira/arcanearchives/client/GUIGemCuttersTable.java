package com.aranaira.arcanearchives.client;

import com.aranaira.arcanearchives.common.ContainerGemCuttersTable;
import com.aranaira.arcanearchives.common.GemCuttersTableRecipe;
import com.aranaira.arcanearchives.common.GemCuttersTableRecipeList;
import com.aranaira.arcanearchives.common.InvisibleButton;
import com.aranaira.arcanearchives.util.ItemComparison;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GUIGemCuttersTable extends GuiContainer
{

	private static final ResourceLocation GUITextures = new ResourceLocation("arcanearchives:textures/gui/gemcutterstable.png");

	ContainerGemCuttersTable mCGCT;

	private InvisibleButton PrevPageButton;
	private InvisibleButton NextPageButton;

	public GUIGemCuttersTable(EntityPlayer player, ContainerGemCuttersTable container)
	{
		super(container);
		mCGCT = container;
		this.xSize = 206;
		this.ySize = 254;

	}

	@Override
	public void initGui()
	{
		super.initGui();

		this.buttonList.clear();

		this.PrevPageButton = new InvisibleButton(0, guiLeft + 26, guiTop + 69, 10, 18, "");
		this.NextPageButton = new InvisibleButton(1, guiLeft + 170, guiTop + 69, 10, 18, "");

		PrevPageButton.visible = false;
		NextPageButton.visible = false;

		this.buttonList.add(PrevPageButton);
		this.buttonList.add(NextPageButton);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		super.drawScreen(mouseX, mouseY, partialTicks);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableColorMaterial();
		this.mc.getTextureManager().bindTexture(GUITextures);

		GemCuttersTableRecipe recipe = mCGCT.getTile().getRecipe();

		if(recipe != null)
		{
			ItemStack output = recipe.getOutput();

			for(int i = 0; i < 8; i++)
			{
				int actualIndex = i + mCGCT.getTile().getPage() * 7;
				if(ItemComparison.AreItemsEqual(GemCuttersTableRecipeList.getOutputByIndex(actualIndex), output))
				{
					this.drawTexturedModalRect(guiLeft + i * 18 + 39, guiTop + 68, 206, 0, 20, 20);
					break;
				}
			}
			List<String> mRecipeInput = new ArrayList<>();
			if(mCGCT.getRecipeStatus())
			{
				// Valid
				mRecipeInput.add(TextFormatting.GREEN + output.getDisplayName());
			} else
			{
				// Invalid
				mRecipeInput.add(TextFormatting.RED + output.getDisplayName());
			}

			for(ItemStack item : recipe.getInput())
			{
				mRecipeInput.add(TextFormatting.GREEN + item.getDisplayName() + " : " + item.getCount());
			}

			this.drawHoveringText(mRecipeInput, guiLeft + 206, guiTop);
		}

		this.renderHoveredToolTip(mouseX, mouseY);

	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GlStateManager.enableColorMaterial();
		this.mc.getTextureManager().bindTexture(GUITextures);

		drawModalRectWithCustomSizedTexture(guiLeft, guiTop, 0, 0, 206, 256, 256, 256);
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if(button.id == 0)
		{
			mCGCT.getTile().previousPage();
		}
		if(button.id == 1)
		{
			mCGCT.getTile().nextPage();
		}
		super.actionPerformed(button);
	}
}