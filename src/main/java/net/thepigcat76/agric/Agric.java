package net.thepigcat76.agric;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.thepigcat76.agric.block.ModBlocks;
import net.thepigcat76.agric.block.entity.ModBlockEntities;
import net.thepigcat76.agric.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.thepigcat76.agric.recipe.ModRecipes;
import net.thepigcat76.agric.screen.ModMenuTypes;
import net.thepigcat76.agric.screen.centrifuge.CentrifugeScreen;
import net.thepigcat76.agric.screen.drying_rack.DryingRackScreen;
import org.slf4j.Logger;

@Mod(Agric.MODID)
public class Agric {

    public static final String MODID = "agric";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Agric() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.DRYING_RACK_MENU.get(), DryingRackScreen::new);
            MenuScreens.register(ModMenuTypes.CENTRIFUGE_MENU.get(), CentrifugeScreen::new);
        }
    }
}
