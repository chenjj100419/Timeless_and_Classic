
package timeless_and_classic.core.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import timeless_and_classic.core.timeless_and_classic;

/**
 * Author: ClumsyAlien, codebase and design based off Mr.Pineapple's original addon
 */

public class SoundRegistry {
    // A Deferred Register which will be called in our main class to register all of the sounds, the very same
    public static final DeferredRegister<SoundEvent> SOUND_REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, timeless_and_classic.ID);

    // Register the name of the sound via the Object name for each block in our sounds.json
    public static final RegistryObject<SoundEvent> M1911_FIRE = register("item.fire_m1911");
    public static final RegistryObject<SoundEvent> M1911_FIRES = register("item.fire_m1911s");
    public static final RegistryObject<SoundEvent> MOSIN_NAGANT_FIRE = register("item.fire_mosin-nagant");


    private static RegistryObject<SoundEvent> register(String key) {
        return SOUND_REGISTRY.register(key, () -> new SoundEvent(new ResourceLocation(timeless_and_classic.ID, key)));
    }
}
