package timeless_and_classic.client.render.tileentity;

import com.mrcrayfish.guns.tileentity.SyncedTileEntity;
import com.mrcrayfish.guns.tileentity.inventory.IStorageBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import timeless_and_classic.client.TimelessWorkbenchContainer;
import timeless_and_classic.core.registry.TileEntities;

import javax.annotation.Nullable;

public class TimelessWorkbenchTileEntity extends SyncedTileEntity implements IStorageBlock
{
    private final NonNullList<ItemStack> inventory = NonNullList.withSize(1, ItemStack.EMPTY);

    public TimelessWorkbenchTileEntity()
    {

        super(TileEntities.TIMELESS_WORKBENCH.get());
    }

    @Override
    public NonNullList<ItemStack> getInventory()
    {
        return this.inventory;
    }

    @Override
    public CompoundNBT write(CompoundNBT compound)
    {
        ItemStackHelper.saveAllItems(compound, this.inventory);
        return super.write(compound);
    }

    @Override
    public void read(BlockState state, CompoundNBT compound)
    {
        super.read(state, compound);
        ItemStackHelper.loadAllItems(compound, this.inventory);
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return index != 0 || (stack.getItem() instanceof DyeItem && this.inventory.get(index).getCount() < 1);
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player)
    {
        return this.world.getTileEntity(this.pos) == this && player.getDistanceSq(this.pos.getX() + 0.5, this.pos.getY() + 0.5, this.pos.getZ() + 0.5) <= 64.0;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TranslationTextComponent("container.timeless_and_classic.timeless_workbench");
    }

    @Nullable
    @Override
    public Container createMenu(int windowId, PlayerInventory playerInventory, PlayerEntity playerEntity)
    {
        return new TimelessWorkbenchContainer(windowId, playerInventory, this);
    }
}
