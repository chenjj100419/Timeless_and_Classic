package timeless_and_classic.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

/*
 * This is our config class. In here we can specify things that can be changed by the player.
 * The config allows users to take more control of the mod. For instance should a biome spawn?
 *
 * In this case, currently, I am only letting the player decide the chance of a dart being lost.
 */

/**
 * Author: Mr.Pineapple (ClumsyAlien, I am currently keeping the file before stashing in a note file, to potentially poke around with later)
 */
public class Config {
    /*
     * This is an inner class of the config class.
     * This is the common config.
     * In the future we might have a client and server config.
     */
    public static class Common {
        //Initialise our value
        public final ForgeConfigSpec.IntValue dartLossChance;

        //Constructor for the Common Config
        Common(ForgeConfigSpec.Builder builder) {
            //What we will be pushing to the config, this has a title of common
            builder.push("common");
            {
                /*
                 * We can then add a comment (highly recommended) to inform the user what they are changing.
                 * Notice here that we are connecting it the value from above.
                 * Then in the case of an integer we need to set the range.
                 * This takes in the path (what it will be called). The default value. The minimum value. Then the max value.
                 */
                this.dartLossChance = builder.comment("The chance of a dart being lost when shot is 1/this number").defineInRange("dartLossMinimum", 20, 0, Integer.MAX_VALUE);
            }
            //Remember to pop this section
            builder.pop();
        }
    }

    /*
     * Now we need to be able to access these values across our project.
     * We create a static variable of COMMON (same goes for server/client when added) so we can call the values in the mod.
     * Then we initialise them.
     */
    static final ForgeConfigSpec commonSpec;
    public static final Config.Common COMMON;

    static {
        final Pair<Common, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(Common::new);
        commonSpec = commonSpecPair.getRight();
        COMMON = commonSpecPair.getLeft();
    }
}