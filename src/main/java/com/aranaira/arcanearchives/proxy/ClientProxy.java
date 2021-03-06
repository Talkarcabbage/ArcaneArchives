package com.aranaira.arcanearchives.proxy;

import com.aranaira.arcanearchives.ArcaneArchives;
import com.aranaira.arcanearchives.data.NetworkHelper;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import javax.annotation.Nonnull;

@SuppressWarnings("unused")
@Mod.EventBusSubscriber(modid = ArcaneArchives.MODID)
public class ClientProxy extends CommonProxy
{
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
		// TODO: This was never actually being called from what I can tell?
		OBJLoader.INSTANCE.addDomain(ArcaneArchives.MODID);
	}

	@Override
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
		//ClientCommandHandler.instance.registerCommand(new ArcaneArchivesCommand());
	}

	@Override
	public void registerItemRenderer(@Nonnull Item item, int meta, String id)
	{
		if(item.getRegistryName() != null) // TODO: error message when there's no registry name
		{
			ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
		}
	}

	@SubscribeEvent
	public void playerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
	{
		NetworkHelper.clearClientCache();
	}
}
