package com.aranaira.arcanearchives.inventory.handlers;

import com.aranaira.arcanearchives.data.ArcaneArchivesNetwork;
import com.aranaira.arcanearchives.data.NetworkHelper;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandlerModifiable;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;
import java.util.UUID;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class NetworkItemHandler implements IItemHandlerModifiable
{

	private UUID playerUUID;
	private String searchText = "";
	@Nullable
	private ArcaneArchivesNetwork network;

	public NetworkItemHandler(UUID uuid)
	{
		playerUUID = uuid;
		// TODO: This is server-side only
		network = NetworkHelper.getArcaneArchivesNetwork(uuid);
	}

	public String getSearchString()
	{
		return searchText;
	}

	public void setSearchString(String s)
	{
		searchText = s;
	}

	@Override
	public int getSlots()
	{
		return 27;
	}

	@Override
	public ItemStack getStackInSlot(int slot)
	{
		if(network == null) // TODO: ERROR
			return ItemStack.EMPTY;

		List<ItemStack> listofItems;
		if(searchText.compareTo("") != 0) listofItems = network.GetFilteredItems(searchText);
		else listofItems = network.GetAllItemsOnNetwork();

		if(listofItems.size() > slot) return listofItems.get(slot);
		else return ItemStack.EMPTY;
	}

	@Override
	public ItemStack insertItem(int slot, ItemStack stack, boolean simulate)
	{
		if(network == null) // TODO: Error
			return stack;
		// TODO: This shouldn't be a copy?
		return network.InsertItem(stack.copy(), simulate);
	}

	@Override
	public ItemStack extractItem(int slot, int amount, boolean simulate)
	{
		if(network == null) // TODO: Error
			return ItemStack.EMPTY;
		return network.ExtractItem(getStackInSlot(slot), amount, simulate);
	}

	@Override
	public int getSlotLimit(int slot)
	{
		//Should this be something ridiculously high?
		return 100000;
	}

	@Override
	public void setStackInSlot(int slot, ItemStack stack)
	{
		//if (stack.isEmpty())
		//	return;

		//ArcaneArchives.logger.info(NetworkHelper.getArcaneArchivesNetwork(playerUUID).GetTotalItems());

		//NetworkHelper.getArcaneArchivesNetwork(playerUUID).InsertItem(stack, false);

		//NetworkHelper.getArcaneArchivesNetwork(playerUUID).InsertItem(stack, false);
		//ArcaneArchives.logger.info(slot);
		//NetworkHelper.getArcaneArchivesNetwork(playerUUID).InsertItem(stack);
	}
}
