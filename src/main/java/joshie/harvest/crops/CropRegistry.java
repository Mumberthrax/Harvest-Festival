package joshie.harvest.crops;

import joshie.harvest.api.calendar.Season;
import joshie.harvest.api.crops.ICrop;
import joshie.harvest.api.crops.ICropData;
import joshie.harvest.api.crops.ICropHandler;
import joshie.harvest.api.crops.IStateHandler.PlantSection;
import joshie.harvest.blocks.BlockCrop;
import joshie.harvest.core.config.HFConfig;
import joshie.harvest.core.handlers.HFTrackers;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Collection;

public class CropRegistry implements ICropHandler {
    @Override
    public ICrop getCrop(String unlocalized) {
        return HFConfig.mappings.getCrop(unlocalized);
    }

    @Override
    public ICropData getCropAtLocation(World world, BlockPos pos) {
        PlantSection section = BlockCrop.getSection(world, pos);
        return section == PlantSection.BOTTOM ? HFTrackers.getCropTracker().getCropDataForLocation(world, pos) : HFTrackers.getCropTracker().getCropDataForLocation(world, pos.down());
    }

    @Override
    public ICrop registerCrop(String unlocalized, int cost, int sell, int stages, int regrow, int year, int color, Season... seasons) {
        return new Crop(unlocalized, seasons, cost, sell, stages, regrow, year, color);
    }

    @Override
    public ICrop registerCrop(ICrop crop) {
        return crop;
    }

    @Override
    public Collection<ICrop> getCrops() {
        return Crop.crops;
    }
}