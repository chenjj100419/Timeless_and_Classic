package timeless_and_classic.client.handlers;

import com.mrcrayfish.controllable.Controllable;
import com.mrcrayfish.controllable.client.Action;
import com.mrcrayfish.controllable.client.Controller;
import com.mrcrayfish.controllable.client.gui.navigation.BasicNavigationPoint;
import com.mrcrayfish.controllable.event.ControllerEvent;
import com.mrcrayfish.controllable.event.GatherActionsEvent;
import com.mrcrayfish.controllable.event.GatherNavigationPointsEvent;
import com.mrcrayfish.guns.Config;
import com.mrcrayfish.guns.client.GunButtonBindings;
import com.mrcrayfish.guns.client.handler.AimingHandler;
import com.mrcrayfish.guns.client.handler.ReloadHandler;
import com.mrcrayfish.guns.client.handler.ShootingHandler;
import com.mrcrayfish.guns.client.screen.WorkbenchScreen;
import com.mrcrayfish.guns.common.Gun;
import com.mrcrayfish.guns.init.ModSyncedDataKeys;
import com.mrcrayfish.guns.item.GunItem;
import com.mrcrayfish.guns.item.attachment.IScope;
import com.mrcrayfish.guns.item.attachment.impl.Scope;
import com.mrcrayfish.guns.network.PacketHandler;
import com.mrcrayfish.guns.network.message.MessageAttachments;
import com.mrcrayfish.guns.network.message.MessageUnload;
import com.mrcrayfish.guns.util.GunEnchantmentHelper;
import com.mrcrayfish.obfuscate.common.data.SyncedPlayerData;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import timeless_and_classic.client.screens.TimelessWorkbenchScreen;

public class HController {
    @SubscribeEvent
    public void onGatherNavigationPoints(GatherNavigationPointsEvent event)
    {
        Minecraft mc = Minecraft.getInstance();
        if(mc.currentScreen instanceof WorkbenchScreen)
        {
            TimelessWorkbenchScreen workbench = (TimelessWorkbenchScreen) mc.currentScreen;
            int startX = workbench.getGuiLeft();
            int startY = workbench.getGuiTop();

            for(int i = 0; i < workbench.getTabs().size(); i++)
            {
                int tabX = startX + 28 * i + (28 / 2);
                int tabY = startY - (28 / 2);
                event.addPoint(new BasicNavigationPoint(tabX, tabY));
            }

            for(int i = 0; i < 6; i++)
            {
                int itemX = startX + 172 + (80 / 2);
                int itemY = startY + i * 19 + 63 + (19 / 2);
                event.addPoint(new BasicNavigationPoint(itemX, itemY));
            }
        }
    }
}
