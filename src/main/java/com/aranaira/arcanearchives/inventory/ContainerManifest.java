package com.aranaira.arcanearchives.inventory;

import com.aranaira.arcanearchives.data.ArcaneArchivesClientNetwork;
import com.aranaira.arcanearchives.data.ArcaneArchivesNetwork;
import com.aranaira.arcanearchives.data.NetworkHelper;
import com.aranaira.arcanearchives.inventory.handlers.ManifestItemHandler;
import com.aranaira.arcanearchives.util.handlers.AATickHandler;
import com.aranaira.arcanearchives.util.types.ManifestEntry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;
import java.util.List;

public class ContainerManifest extends Container
{

	private ArcaneArchivesClientNetwork clientNetwork = null;
	private ArcaneArchivesNetwork serverNetwork = null;
	private ManifestItemHandler handler;
	private boolean serverSide;
	private EntityPlayer player;

	public ContainerManifest(EntityPlayer playerIn, boolean ServerSide)
	{
		this.serverSide = ServerSide;
		this.player = playerIn;

		if(ServerSide) return;

		clientNetwork = NetworkHelper.getArcaneArchivesClientNetwork(this.player);
		handler = clientNetwork.getManifestHandler();

		int i = 0;
		for(int y = 0; y < 9; y++)
		{
			for(int x = 0; x < 9; x++)
			{
				this.addSlotToContainer(new SlotItemHandler(handler, i, x * 18 + 12, y * 18 + 30));
				i++;
			}
		}
	}

	@Override
	public boolean canInteractWith(@Nonnull EntityPlayer playerIn)
	{
		return true;
	}

	public ManifestEntry getEntry(int slotId)
	{
		return handler.getManifestEntryInSlot(slotId);
	}

	@Override
	@Nonnull
	public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player)
	{
		if(this.serverSide) return ItemStack.EMPTY;

		ManifestEntry entry = handler.getManifestEntryInSlot(slotId);

		if(entry == null) return ItemStack.EMPTY;

		if(entry.getDimension() != player.dimension) return ItemStack.EMPTY;

		List<Vec3d> visPositions = entry.getVecPositions();

		AATickHandler handler = AATickHandler.GetInstance();

		/*if (handler.mBlockPositions.containsAll(visPositions)) {
			handler.mBlockPositionsToRemove.addAll(visPositions);
		} else {
			List<Vec3d> discard = new ArrayList<>();
			for (Vec3d e : visPositions) {
				if (handler.mBlockPositions.contains(e)) {
					discard.add(e);
				}
			}*/
		handler.mBlockPositions.addAll(visPositions);
		//handler.mBlockPositionsToRemove.addAll(discard);
		//}

		return ItemStack.EMPTY;
	}

	public void SetSearchString(String SearchText)
	{
		handler.setSearchText(SearchText);
	}

	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
	}
}
