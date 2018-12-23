package com.aranaira.arcanearchives.util.handlers;

import com.aranaira.arcanearchives.ArcaneArchives;
import com.aranaira.arcanearchives.blocks.RadiantChest;
import com.aranaira.arcanearchives.common.AAItemStackHandler;
import com.aranaira.arcanearchives.items.TomeOfArcanaItem;
import com.aranaira.arcanearchives.tileentities.RadiantChestTileEntity;
import com.typesafe.config.Config;

import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDestroyBlockEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent.BreakEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.items.CapabilityItemHandler;
import scala.Int;

@Mod.EventBusSubscriber
public class ForgeEventHandler
{
	@SubscribeEvent
	public static void onBlockBreakEvent(BreakEvent event)
	{
		World w = event.getWorld();
		
		if(!w.isRemote && event.getState().getBlock() instanceof RadiantChest)
		{
			RadiantChestTileEntity rcte = (RadiantChestTileEntity)w.getTileEntity(event.getPos());
			
			AAItemStackHandler handler = (AAItemStackHandler) rcte.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, EnumFacing.UP);
			
			boolean allSlotsEmpty = true;
			
			for(int i=0; i<handler.getSlots(); i++)
			{
				if(!handler.getStackInSlot(i).isEmpty())
				{
					allSlotsEmpty = false;
					break;
				}
			}
			
			if(!allSlotsEmpty)
				event.setCanceled(true);
		}
	}

	@SubscribeEvent
	public static void onLivingDestroyBlockEvent(LivingDestroyBlockEvent event)
	{
		if(event.getState().getBlock() instanceof RadiantChest)
		{
			event.setCanceled(true);
		}
	}
}