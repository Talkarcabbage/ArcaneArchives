package com.aranaira.arcanearchives.inventory;

import com.aranaira.arcanearchives.tileentities.MatrixStorageTileEntity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

import javax.annotation.Nonnull;

public class ContainerMatrixStorage extends Container
{
	MatrixStorageTileEntity mTileEntity;

	public ContainerMatrixStorage(MatrixStorageTileEntity MSTE, IInventory playerInventory)
	{
		//ArcaneArchivesNetwork aanetwork = NetworkHelper.getArcaneArchivesNetwork(playerIn.getUniqueID());
		mTileEntity = MSTE;

		// Againn, nonnull by default
		/*ArcaneArchives.logger.info("^^^^NULL CHECKS");
		ArcaneArchives.logger.info("inv null? " + playerInventory.equals(null));
		ArcaneArchives.logger.info("te null? " + MSTE.equals(null));*/
	}

	@Override
	public boolean canInteractWith(@Nonnull EntityPlayer playerIn)
	{
		return true;
	}
}
