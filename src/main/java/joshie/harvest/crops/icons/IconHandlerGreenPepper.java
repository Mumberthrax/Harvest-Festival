package joshie.harvest.crops.icons;

import joshie.harvest.core.lib.HFModInfo;
import joshie.harvest.crops.HFCrops;
import net.minecraftforge.fml.relauncher.SideOnly;

public class IconHandlerGreenPepper extends AbstractIconHandler {
    @SideOnly(Side.CLIENT)
    public IIcon getIconForStage(PlantSection section, int stage) {
        if (stage <= 2) return stageIcons[0];
        else if (stage <= 3) return stageIcons[1];
        else if (stage <= 5) return stageIcons[2];
        else if (stage <= 7) return stageIcons[3];
        else return stageIcons[4];
    }

    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister register) {
        stageIcons = new IIcon[5];
        for (int i = 0; i < stageIcons.length; i++) {
            stageIcons[i] = register.registerIcon(HFModInfo.CROPPATH + HFCrops.green_pepper.getUnlocalizedName() + "_" + (i + 1));
        }
    }
}
