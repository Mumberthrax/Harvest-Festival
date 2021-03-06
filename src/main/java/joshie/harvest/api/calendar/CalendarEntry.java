package joshie.harvest.api.calendar;

import net.minecraft.item.ItemStack;

import java.util.List;

public interface CalendarEntry {
    /** Returns this item as a stack, to be used for displaying in the calendar **/
    ItemStack getStackRepresentation();

    /** Add tooltip for displaying in the calender
     *  @param tooltip  add to this list **/
    void addTooltipForCalendarEntry(List<String> tooltip);
}
