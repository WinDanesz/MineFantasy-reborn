package minefantasy.mfr.mechanics.worldGen.structure;

import minefantasy.mfr.init.BlockListMFR;
import minefantasy.mfr.init.ComponentListMFR;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.LootTableList;

public class StructureGenAncientAlter extends StructureModuleMFR {
    private ResourceLocation lootType = LootTableList.CHESTS_SIMPLE_DUNGEON;

    public StructureGenAncientAlter(World world, BlockPos pos, int d) {
        super(world,pos, d);
    }

    StructureGenAncientAlter(World world, StructureCoordinates position) {
        super(world, position);
    }

    @Override
    public void generate() {
        int radius = 2;
        // FLOOR
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                placeBlock(Blocks.STONEBRICK, new BlockPos(x, 0, z));

                Object[] array = getFoundation(radius, x, z);
                if (array != null) {
                    buildFoundation((Block) array[0], new BlockPos(x, -1, z), 32, 3, false);
                }
            }
        }
        for (int p = -1; p <= 1; p++) {
            placeBlock(Blocks.STONE_BRICK_STAIRS, new BlockPos(-radius, 0, p), 0);
            placeBlock(Blocks.STONE_BRICK_STAIRS, new BlockPos(radius, 0, p), 0);
            placeBlock(Blocks.STONE_BRICK_STAIRS, new BlockPos(p, 0, -radius), 0);
            placeBlock(Blocks.STONE_BRICK_STAIRS, new BlockPos(p, 0, radius), 0);
        }
        placeBlock(BlockListMFR.MYTHIC_DECOR, new BlockPos(-radius, 0, -radius));
        placeBlock(BlockListMFR.MYTHIC_DECOR, new BlockPos(radius, 0, -radius));
        placeBlock(BlockListMFR.MYTHIC_DECOR, new BlockPos(-radius, 0, radius));
        placeBlock(BlockListMFR.MYTHIC_DECOR, new BlockPos(radius, 0, radius));

        placeBlock(BlockListMFR.MYTHIC_DECOR, new BlockPos(-radius, 1, -radius));
        placeBlock(BlockListMFR.MYTHIC_DECOR, new BlockPos(radius, 1, -radius));
        placeBlock(BlockListMFR.MYTHIC_DECOR, new BlockPos(-radius, 1, radius));
        placeBlock(BlockListMFR.MYTHIC_DECOR, new BlockPos(radius, 1, radius));

        placeBlock(BlockListMFR.MYTHIC_DECOR, new BlockPos(-radius, 2, -radius));
        placeBlock(BlockListMFR.MYTHIC_DECOR, new BlockPos(radius, 2, -radius));
        placeBlock(BlockListMFR.MYTHIC_DECOR, new BlockPos(-radius, 2, radius));
        placeBlock(BlockListMFR.MYTHIC_DECOR, new BlockPos(radius, 2, radius));

        // CHEST
        {
            int x = 0;
            int y = 0;
            int z = 0;

            placeBlock(Blocks.STONEBRICK, new BlockPos(x, y + 1, z));
            placeChest(new BlockPos(0, y + 2, z), lootType);
            placeSpawner(new BlockPos(0, y, z), EntityList.getKey(EntityEnderman.class));
        }

    }

    private Object[] getFoundation(int radius, int x, int z) {
        if (x == -radius && z == -radius)
            return null;
        if (x == radius && z == -radius)
            return null;
        if (x == -radius && z == radius)
            return null;
        if (x == radius && z == radius)
            return null;

        if (x == 0 && z == -radius)
            return new Object[]{Blocks.OBSIDIAN, false};
        if (x == 0 && z == radius)
            return new Object[]{Blocks.OBSIDIAN, false};
        if (x == -radius && z == 0)
            return new Object[]{Blocks.OBSIDIAN, false};
        if (x == radius && z == 0)
            return new Object[]{Blocks.OBSIDIAN, false};

        return new Object[]{Blocks.STONEBRICK, true};
    }

    private void placeChest(BlockPos pos, ResourceLocation loot) {
        BlockPos coords = this.offsetPos(pos, direction);
        world.setBlockState(coords, (IBlockState) Blocks.CHEST, direction);
        TileEntityChest tileentitychest = (TileEntityChest) world.getTileEntity(coords);

        if (tileentitychest != null) {
            tileentitychest.setLootTable(loot, 2 + rand.nextInt(3));

            int artId = rand.nextInt(tileentitychest.getSizeInventory());
            tileentitychest.setInventorySlotContents(artId, new ItemStack(ComponentListMFR.ARTEFACTS, 1, 2));
        }
    }

    public StructureModuleMFR setLoot(ResourceLocation loot) {
        this.lootType = loot;
        return this;
    }
}
