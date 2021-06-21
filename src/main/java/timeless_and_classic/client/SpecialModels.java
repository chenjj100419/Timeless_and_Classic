package timeless_and_classic.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import timeless_and_classic.core.timeless_and_classic;

/*
 * This class will be used to register special models (like the grenade launcher)
 * We can 'copy' from the SpecialModels class in the base gun mod as there
 * isn't an interface provided to implement.
 */

/**
 * Author: Mr. Pineapple
 */
@EventBusSubscriber(modid = timeless_and_classic.ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public enum SpecialModels {
    //The values in this class are stored here so we can call upon them.
    M1911("m1911"),
    M1911_SLIDE("m1911_slide"),
    M1911_STANDARD_MAG("m1911_standard_mag"),
    M1911_LONG_MAG("m1911_long_mag"),
    M1851("m1851"),
    M1851_CYLINDER("m1851_cylinder"),
    M1851_HAMMER("m1851_hammer"),
    M1928("m1928"),
    M1928_BOLT("m1928_bolt"),
    M1928_STICK_MAG("m1928_stick_mag"),
    M1928_DRUM_MAG("m1928_drum_mag"),
    MOSIN("mosin"),
    MOSIN_BOLT("mosin_bolt"),
    AK47("ak47"),
    AK47_BOLT("ak47_bolt"),
    M60("m60"),
    M60_sMAG("m60_standard_mag"),
    M60_eMAG("m60_extended_mag"),
    AK47_OPTIC_MOUNT("ak47_mount"),
    M1917("m1917"),
    M1917_CYLINDER("m1917_cylinder"),
    GLOCK_17("glock_17"),
    GLOCK_17_SLIDE("glock_17_slide"),
    GLOCK_17_STANDARD_MAG("glock_17_standard_mag"),
    GLOCK_17_EXTENDED_MAG("glock_17_extended_mag"),
    GLOCK_17_SUPPRESSOR_OVERIDE("glock_17_suppressor");

    //Variables
    private final ResourceLocation modelLocation;
    private final boolean specialModel;
    @OnlyIn(Dist.CLIENT)
    private IBakedModel cachedModel;

    SpecialModels(String modelName) {
        //Get the file path for the special modes, and set them to true (the are going to be special models)
        this(new ResourceLocation(timeless_and_classic.ID, "special/" + modelName), true);
    }

    //Second Constructor to feed variables
    SpecialModels(ResourceLocation resourceLocation, boolean specialModel) {
        this.modelLocation = resourceLocation;
        this.specialModel = specialModel;
    }

    //Get the item's model
    @OnlyIn(Dist.CLIENT)
    public IBakedModel getModel() {
        if (this.cachedModel == null) {
            IBakedModel model = Minecraft.getInstance().getModelManager().getModel(this.modelLocation);
            if (model == Minecraft.getInstance().getModelManager().getMissingModel()) {
                return model;
            }
            this.cachedModel = model;
        }
        return this.cachedModel;
    }

    //Register a new model to that item
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void register(ModelRegistryEvent event) {
        for (SpecialModels model : values()) {
            if (model.specialModel) {
                ModelLoader.addSpecialModel(model.modelLocation);
            }
        }
    }
    //TODO finish comment
}
