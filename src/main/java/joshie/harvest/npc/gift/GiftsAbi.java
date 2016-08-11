package joshie.harvest.npc.gift;

import joshie.harvest.api.npc.gift.GiftCategory;
import joshie.harvest.api.npc.gift.IGiftHandler;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class GiftsAbi implements IGiftHandler {
    @Override
    public Quality getQuality(ItemStack stack) {
        if (stack.getItem() == Items.SUGAR) {
            return Quality.AWESOME;
        }

        if (GiftRegistry.is(stack, GiftCategory.CUTE)) {
            return Quality.GOOD;
        }

        if (GiftRegistry.is(stack, GiftCategory.SCARY)) {
            return Quality.BAD;
        }

        return Quality.DECENT;
    }
}