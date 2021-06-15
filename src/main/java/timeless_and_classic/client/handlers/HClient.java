package timeless_and_classic.client.handlers;

import com.mrcrayfish.guns.GunMod;
import com.mrcrayfish.guns.Reference;
import com.mrcrayfish.guns.client.GunButtonBindings;
import com.mrcrayfish.guns.client.KeyBinds;
import com.mrcrayfish.guns.client.handler.*;
import com.mrcrayfish.guns.client.screen.AttachmentScreen;
import com.mrcrayfish.guns.client.screen.WorkbenchScreen;
import com.mrcrayfish.guns.init.ModContainers;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import timeless_and_classic.client.screens.TimelessWorkbenchScreen;
import timeless_and_classic.core.registry.TimelessContainers;
import timeless_and_classic.core.timeless_and_classic;

import java.lang.reflect.Field;

@Mod.EventBusSubscriber(modid = timeless_and_classic.ID, value = Dist.CLIENT)
public class HClient
{
    private static Field mouseOptionsField;

    public static void setup()
    {
        registerScreenFactories();
    }
    private static void registerScreenFactories()
    {
        ScreenManager.registerFactory(TimelessContainers. WORKBENCH.get(), TimelessWorkbenchScreen::new);
    }
}
