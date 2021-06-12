package timeless_and_classic.core.registry;

import com.mrcrayfish.guns.GunMod;
import net.minecraft.block.SoundType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
// import com.mrcrayfish.guns.Reference;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import timeless_and_classic.blocks.*;
import timeless_and_classic.core.timeless_and_classic;

import javax.annotation.Nullable;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Author: ClumsyAlien, codebase and design based off Mr.Pineapple's original addon
 */

public class ModBlocks {
    // Just like our ItemRegistry class, we start off with creating a Register object using forge and our ModId
    public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, timeless_and_classic.ID);
    /*
        Here I am doing something quite hacky, each box are pretty much completely, I have much plans that require each box to be separate at it's final stage,
        If you are creating your own "simple" then do look away as this is slightly more advanced!
    */
    public static final RegistryObject<Block> MAGNUMBOX = registerMagnum("magnumbox", () -> new magnum_box(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0).notSolid().setOpaque((bs, br, bp) -> false)));
    public static final RegistryObject<Block> BOX_45 = register45("box45", () -> new box_45(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0).notSolid().setOpaque((bs, br, bp) -> false)));
    public static final RegistryObject<Block> BOX_WIN_30 = registerwin30("win30-30box", () -> new win_30_box(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0).notSolid().setOpaque((bs, br, bp) -> false)));
    public static final RegistryObject<Block> BOX_308 = register308("box_308-block", () -> new box_308(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0).notSolid().setOpaque((bs, br, bp) -> false)));
    public static final RegistryObject<Block> TIMELESS_WORKBENCH = register("timeless_workbench", () -> new Timeless_Workbench(Block.Properties.create(Material.IRON).sound(SoundType.METAL).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0).notSolid().setOpaque((bs, br, bp) -> false)));

    /*
        Here I am doing something quite hacky, each box_item has completely different reactions on a rightclick event, I decided to just quickly create 3 separate new classes...
        Each class (magnum_box_item , box_45_item , win_30_box_item) all has a rightClick event, and give different bullet types according to the block type!
    */

    private static <T extends Block> RegistryObject<T> register(String id, Supplier<T> blockSupplier) // Basic Registry system, used for creating a basic BlockItem!!!
    {
        return register(id, blockSupplier, block1 -> new BlockItem(block1, new Item.Properties().group(GunMod.GROUP)));
    }

    private static <T extends Block> RegistryObject<T> registerMagnum(String id, Supplier<T> blockSupplier)
    {
        return register(id, blockSupplier, block1 -> new magnum_box_item(block1, new Item.Properties().maxStackSize(8).group(timeless_and_classic.GROUP)));
    }

    private static <T extends Block> RegistryObject<T> register308(String id, Supplier<T> blockSupplier)
    {
        return register(id, blockSupplier, block1 -> new box_308_item(block1, new Item.Properties().maxStackSize(1).maxDamage(7).group(timeless_and_classic.GROUP)));
    }

    private static <T extends Block> RegistryObject<T> register45(String id, Supplier<T> blockSupplier)
    {
        return register(id, blockSupplier, block1 -> new box_45_item(block1, new Item.Properties().maxStackSize(8).group(timeless_and_classic.GROUP)));
    }

    private static <T extends Block> RegistryObject<T> registerwin30(String id, Supplier<T> blockSupplier)
    {
        return register(id, blockSupplier, block1 -> new win_30_box_item(block1, new Item.Properties().maxStackSize(8).group(timeless_and_classic.GROUP)));
    }

    private static <T extends Block> RegistryObject<T> register(String id, Supplier<T> blockSupplier, @Nullable Function<T, BlockItem> supplier)
    {
        T block = blockSupplier.get();
        if(supplier != null)
        {
            ItemRegistry.ITEM_REGISTRY.register(id, () -> supplier.apply(block));
        }
        return ModBlocks.REGISTER.register(id, () -> block);
    }

}
