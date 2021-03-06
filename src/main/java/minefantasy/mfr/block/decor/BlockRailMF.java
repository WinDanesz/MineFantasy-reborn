package minefantasy.mfr.block.decor;

import minefantasy.mfr.MineFantasyReborn;
import net.minecraft.block.SoundType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.registry.GameRegistry;
import minefantasy.mfr.material.BaseMaterialMFR;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRail;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.world.World;

public class BlockRailMF extends BlockRail {
    private BaseMaterialMFR baseMat;
    private boolean isPowered = true;

    public BlockRailMF(BaseMaterialMFR material) {
        this(material, material.name.toLowerCase());
    }

    public BlockRailMF(BaseMaterialMFR material, String type) {
        super();
        String name = type + "_rail";

        setRegistryName(name);
        setUnlocalizedName(name);
        this.setHarvestLevel("pickaxe", material.harvestLevel);
        this.setSoundType(SoundType.METAL);
        this.setHardness(material.hardness + 1 / 2F);
        this.setResistance(material.hardness + 1);
        this.baseMat = material;
    }

    @Override
    public float getRailMaxSpeed(World world, EntityMinecart cart, BlockPos pos) {
        return 0.8F;
    }
}