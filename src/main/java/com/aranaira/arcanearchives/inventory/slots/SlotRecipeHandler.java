package com.aranaira.arcanearchives.inventory.slots;

import com.aranaira.arcanearchives.registry.crafting.GemCuttersTableRecipeList;
import com.aranaira.arcanearchives.tileentities.GemCuttersTableTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class SlotRecipeHandler extends Slot
{
	private static IInventory emptyInventory = new InventoryBasic("[Null]", true, 0);
	private final int index;
	private final int x;
	private final int y;
	private final GemCuttersTableTileEntity entity;
	private boolean dimmed = false;

	public SlotRecipeHandler(int index, int xPosition, int yPosition, GemCuttersTableTileEntity entity)
	{
		super(emptyInventory, index, xPosition, yPosition);
		this.index = index;
		this.x = xPosition;
		this.y = yPosition;
		this.entity = entity;
	}

	public boolean isDimmed()
	{
		return dimmed;
	}

	public void setDimmed(boolean dimmed)
	{
		this.dimmed = dimmed;
	}

	public void setDimmed()
	{
		setDimmed(true);
	}

	public int getPage()
	{
		return entity.getPage();
	}

	public int getRelativeIndex()
	{
		return index + getPage() * 7;
	}

	@Override
	public ItemStack onTake(EntityPlayer thePlayer, ItemStack stack)
	{
		onSlotChanged();
		return ItemStack.EMPTY;
	}

	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return false;
	}

	@Override
	public ItemStack getStack()
	{
		int slot = getRelativeIndex();
		if(slot < GemCuttersTableRecipeList.getSize())
		{
			return GemCuttersTableRecipeList.getOutputByIndex(slot);
		}

		return ItemStack.EMPTY;
	}

	@Override
	public boolean getHasStack()
	{
		return !getStack().isEmpty();
	}

	@Override
	public void putStack(ItemStack stack)
	{
	}

	@Override
	public void onSlotChanged()
	{
	}

	@Override
	public int getSlotStackLimit()
	{
		return 1;
	}

	@Override
	public int getItemStackLimit(ItemStack stack)
	{
		return 1;
	}

	// Handle this TODO
	@Nullable
	@Override
	public String getSlotTexture()
	{
		return super.getSlotTexture();
	}

	@Override
	public ItemStack decrStackSize(int amount)
	{
		return ItemStack.EMPTY;
	}

	@Override
	public boolean isHere(IInventory inv, int slotIn)
	{
		return false;
	}

	@Override
	public boolean canTakeStack(EntityPlayer playerIn)
	{
		return false;
	}

	@Override
	public int getSlotIndex()
	{
		return super.getSlotIndex();
	}

	@Override
	public boolean isSameInventory(Slot other)
	{
		return false;
	}
}
