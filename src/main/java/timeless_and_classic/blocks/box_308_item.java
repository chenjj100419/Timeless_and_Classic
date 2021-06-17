package timeless_and_classic.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import static timeless_and_classic.core.registry.ItemRegistry.BULLET_308;

public class box_308_item extends BlockItem {
    public box_308_item(Block blockIn, Properties builder) {
        super(blockIn, builder);
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        playerIn.addItemStackToInventory(new ItemStack(BULLET_308.get(),50));

        playerIn.getHeldItem(handIn).damageItem(1,playerIn,playerEntity -> playerEntity.getHeldItem(handIn).shrink(1));

        return ActionResult.resultPass(playerIn.getHeldItem(handIn));
    }
}
