package minefantasy.mfr.itemblock;

import minefantasy.mfr.block.basic.BlockOreMF;
import minefantasy.mfr.init.ToolListMFR;
import net.minecraft.block.Block;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;

public class ItemBlockOreMF extends ItemBlockBase {
    private BlockOreMF ore;

    public ItemBlockOreMF(Block block) {
        super(block);
        ore = (BlockOreMF) block;
    }

    @Override
    public EnumRarity getRarity(ItemStack item) {
        int lvl = ore.rarity + 1;

        if (item.isItemEnchanted()) {
            if (lvl == 0) {
                lvl++;
            }
            lvl++;
        }
        if (lvl >= ToolListMFR.RARITY.length) {
            lvl = ToolListMFR.RARITY.length - 1;
        }
        return ToolListMFR.RARITY[lvl];
    }
}
