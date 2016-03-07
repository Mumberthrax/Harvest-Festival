package joshie.harvest.buildings.placeable.blocks;

import java.util.UUID;

import joshie.harvest.core.network.PacketHandler;
import joshie.harvest.core.network.PacketSyncOrientation;
import joshie.harvest.core.util.generic.IFaceable;
import net.minecraft.block.Block;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import net.minecraftforge.common.ChestGenHooks;

public class PlaceableIFaceable extends PlaceableBlock {
    private ForgeDirection dir;
    private String chestType;

    public PlaceableIFaceable(Block block, int meta, int offsetX, int offsetY, int offsetZ, ForgeDirection dir) {
        super(block, meta, offsetX, offsetY, offsetZ);
        this.dir = dir;
    }

    public PlaceableIFaceable(Block block, int meta, int offsetX, int offsetY, int offsetZ, ForgeDirection dir, String chestType) {
        this(block, meta, offsetX, offsetY, offsetZ, dir);
        this.chestType = chestType;
    }

    @Override
    public boolean canPlace(PlacementStage stage) {
        return stage == PlacementStage.TORCHES;
    }

    private ForgeDirection getFacing(boolean n1, boolean n2, boolean swap) {
        if (dir == NORTH) {
            if (n2) {
                return swap ? EAST : SOUTH;
            } else if (swap) {
                return WEST;
            }
        } else if (dir == SOUTH) {
            if (n2) {
                return swap ? WEST : NORTH;
            } else if (swap) {
                return EAST;
            }
        } else if (dir == WEST) {
            if (n1) {
                return swap ? SOUTH : EAST;
            } else if (swap) {
                return NORTH;
            }
        } else if (dir == EAST) {
            if (n1) {
                return swap ? NORTH : WEST;
            } else if (swap) {
                return SOUTH;
            }
        }

        return dir;
    }

    @Override
    public boolean place(UUID uuid, World world, int x, int y, int z, boolean n1, boolean n2, boolean swap) {
        if (!super.place(uuid, world, x, y, z, n1, n2, swap)) return false;
        TileEntity tile = world.getTileEntity(x, y, z);
        if (tile instanceof IFaceable) {
            ForgeDirection orientation = getFacing(n1, n2, swap);
            ((IFaceable) tile).setFacing(orientation);
            PacketHandler.sendAround(new PacketSyncOrientation(world.provider.dimensionId, x, y, z, orientation), tile);
            return true;
        }

        if (chestType != null && tile instanceof IInventory) {
            WeightedRandomChestContent.generateChestContents(world.rand, ChestGenHooks.getItems(chestType, world.rand), (IInventory) tile, 10);
            return true;
        }

        return false;
    }
}
