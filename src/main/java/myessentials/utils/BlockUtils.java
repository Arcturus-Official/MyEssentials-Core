package myessentials.utils;

import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class BlockUtils {

    public static String getBlockName(Block block) {
        return GameRegistry.findUniqueIdentifierFor(block).toString().replace(":", ".");
    }
}
