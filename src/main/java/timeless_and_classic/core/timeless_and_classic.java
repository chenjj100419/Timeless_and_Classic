package timeless_and_classic.core;

import com.mrcrayfish.guns.client.render.gun.ModelOverrides;
import com.mrcrayfish.guns.common.GripType;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.registries.ObjectHolder;
import timeless_and_classic.client.SpecialModels;
import timeless_and_classic.client.render.gun.model.*;
import timeless_and_classic.client.render.pose.*;
import timeless_and_classic.common.CustomGripType;
import timeless_and_classic.core.registry.ItemRegistry;
import timeless_and_classic.core.registry.ModBlocks;
import timeless_and_classic.core.registry.SoundRegistry;
import timeless_and_classic.common.CustomGripType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;



/**
 * Author: ClumsyAlien, codebase and design based off Mr.Pineapple's original addon
 */
@Mod(timeless_and_classic.ID)
public class timeless_and_classic {
    //This variable is our mods ID - this must be coherent across the project
    public static final String ID = "timeless_and_classic";

    /*
     * This is our creative tab that we will add our items to.
     * If you wanted, you could just add them to the Gun Mods tab.
     * We pass in our ID to this so we can name it in the lang file.
     */
    public static final ItemGroup GROUP = new ItemGroup(ID) {
        //Here we create the icon for the tab
        //If you wanted a normal item here then you can just return an ItemStack
        @Override
        public ItemStack createIcon() {
            //Get the Item in a new ItemStack
            ItemStack stack = new ItemStack(ItemRegistry.M1911.get());
            //Here we add ammunition to the gun so it doesn't have the re-fill bar under the item
            stack.getOrCreateTag().putInt("AmmoCount", ItemRegistry.M1911.get().getGun().getGeneral().getMaxAmmo());
            //We now return the stack which has added ammunition
            return stack;
        }
    };

    //What needs to be called the the event bus
    public timeless_and_classic() {
        //Here we add the config to the mod - remember to do this for the server and client if you have them

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.commonSpec);
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.register(this);
        //Register the Deferred Register from our Registry classes
        //EntityRegistry.ENTITY_REGISTRY.register(bus);
        ItemRegistry.ITEM_REGISTRY.register(bus);
        ModBlocks.REGISTER.register(bus);
        SoundRegistry.SOUND_REGISTRY.register(bus);
        //Call the setup methods from below and add them to the bus
        bus.addListener(this::commonSetup);
        bus.addListener(this::clientSetup);
        // bus.addListener(this::clientLoad);
    }

    void commonSetup(FMLCommonSetupEvent event) {
        // ProjectileManager.getInstance().registerFactory(ItemRegistry.DART.get(), ((world, livingEntity, itemStack, gunItem, gun) -> new DartEntity(EntityRegistry.DART.get(), world, livingEntity, itemStack, gunItem, gun)));
        GripType.registerType(new CustomGripType(new ResourceLocation("timeless_and_classic", "one_handed_m1911"), new OneHandedPoseHighRes_m1911()));
        GripType.registerType(new CustomGripType(new ResourceLocation("timeless_and_classic", "one_handed_m1851"), new OneHandedPoseHighRes_m1851()));
        GripType.registerType(new CustomGripType(new ResourceLocation("timeless_and_classic", "two_handed_m1894"), new TwoHandedPoseHighRes_m1894()));
        GripType.registerType(new CustomGripType(new ResourceLocation("timeless_and_classic", "two_handed_m1928"), new TwoHandedPoseHighRes_m1928()));
        GripType.registerType(new CustomGripType(new ResourceLocation("timeless_and_classic", "two_handed_mosin"), new TwoHandedPoseHighRes_mosin()));
    }

    void clientSetup(FMLClientSetupEvent event) {
        // RenderingRegistry.registerEntityRenderingHandler(EntityRegistry.DART.get(), DartEntityRenderer::new);
        ModelOverrides.register(ItemRegistry.M1911.get(), new m1911_animation());
        ModelOverrides.register(ItemRegistry.M1851.get(), new m1851_animation());
        ModelOverrides.register(ItemRegistry.M1928.get(), new m1928_animation());
        ModelOverrides.register(ItemRegistry.MOSIN.get(), new mosin_animation());
        ModelOverrides.register(ItemRegistry.AK47.get(), new ak47_animation());

        RenderTypeLookup.setRenderLayer(ModBlocks.MAGNUMBOX.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.BOX_45.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.BOX_WIN_30.get(), RenderType.getCutout());
    }


}