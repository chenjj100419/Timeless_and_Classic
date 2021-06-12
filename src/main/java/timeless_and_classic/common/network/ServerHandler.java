package timeless_and_classic.common.network;

import com.mrcrayfish.guns.common.container.WorkbenchContainer;
import com.mrcrayfish.guns.crafting.WorkbenchRecipe;
import com.mrcrayfish.guns.crafting.WorkbenchRecipes;
import com.mrcrayfish.guns.item.IColored;
import com.mrcrayfish.guns.tileentity.WorkbenchTileEntity;
import com.mrcrayfish.guns.util.InventoryUtil;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import timeless_and_classic.client.render.tileentity.TimelessWorkbenchTileEntity;
import timeless_and_classic.common.TimelessWorkbenchContainer;

import java.util.List;

public class ServerHandler
{
    public static void handleCraft(ServerPlayerEntity player, ResourceLocation id, BlockPos pos)
    {
        World world = player.world;

        if(player.openContainer instanceof WorkbenchContainer)
        {
            TimelessWorkbenchContainer workbench = (TimelessWorkbenchContainer) player.openContainer;
            if(workbench.getPos().equals(pos))
            {
                WorkbenchRecipe recipe = WorkbenchRecipes.getRecipeById(world, id);
                if(recipe == null)
                {
                    return;
                }

                List<ItemStack> materials = recipe.getMaterials();
                if(materials != null)
                {
                    for(ItemStack stack : materials)
                    {
                        if(!InventoryUtil.hasItemStack(player, stack))
                        {
                            return;
                        }
                    }

                    for(ItemStack stack : materials)
                    {
                        InventoryUtil.removeItemStack(player, stack);
                    }

                    TimelessWorkbenchTileEntity workbenchTileEntity = workbench.getWorkbench();

                    /* Gets the color based on the dye */
                    ItemStack stack = recipe.getItem();
                    ItemStack dyeStack = workbenchTileEntity.getInventory().get(0);
                    if(dyeStack.getItem() instanceof DyeItem)
                    {
                        DyeItem dyeItem = (DyeItem) dyeStack.getItem();
                        int color = dyeItem.getDyeColor().getColorValue();

                        if(stack.getItem() instanceof IColored && ((IColored) stack.getItem()).canColor(stack))
                        {
                            IColored colored = (IColored) stack.getItem();
                            colored.setColor(stack, color);
                            workbenchTileEntity.getInventory().set(0, ItemStack.EMPTY);
                        }
                    }

                    InventoryHelper.spawnItemStack(world, pos.getX() + 0.5, pos.getY() + 1.125, pos.getZ() + 0.5, stack);
                }
            }
        }
    }
}
