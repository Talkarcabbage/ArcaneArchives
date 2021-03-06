package com.aranaira.arcanearchives.tileentities;

import com.aranaira.arcanearchives.data.NetworkHelper;
import com.aranaira.arcanearchives.init.BlockLibrary;
import com.aranaira.arcanearchives.util.handlers.ConfigHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

public class RadiantResonatorTileEntity extends ImmanenceTileEntity
{
	public int TicksUntilCrystalGrowth = ConfigHandler.values.iRadiantResonatorTickTime;
	public int BonusTicks; //How many bonus ticks the Resonator simulates based on supplied Immanence

	public RadiantResonatorTileEntity()
	{
		super("radiant_resonator_tile_entity");
		ImmanenceDrain = ConfigHandler.values.iRadiantResonatorDrain;
		BonusTicks = ConfigHandler.values.iRadiantResonatorBonusTicks;
	}

	@Override
	public void update()
	{
		super.update();

		// Only tick on the client side
		if(world.isRemote || NetworkID == null || NetworkID.equals(NetworkHelper.INVALID)) return;

		// This will have to be updated to hive networks TODO
		EntityPlayer player = world.getPlayerEntityByUUID(NetworkID);

		// Don't tick if the player isn't online
		if(player == null) return;

		if(world.isAirBlock(pos.up()))
		{
			if(TicksUntilCrystalGrowth > 0)
			{
				TicksUntilCrystalGrowth--;
				if(IsDrainPaid) TicksUntilCrystalGrowth -= BonusTicks;
			} else
			{

				TicksUntilCrystalGrowth = ConfigHandler.values.iRadiantResonatorTickTime;
				world.setBlockState(pos.up(), BlockLibrary.RAW_QUARTZ.getDefaultState(), 3);
			}
		}
	}

	@Override
	@ParametersAreNonnullByDefault
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate)
	{
		return (oldState.getBlock() != newSate.getBlock());
	}

}
