package joshie.harvest.tools.command;

import joshie.harvest.api.core.ITiered;
import joshie.harvest.core.commands.AbstractHFCommand;
import joshie.harvest.core.commands.HFCommand;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;

@HFCommand
@SuppressWarnings("unused")
public class HFCommandTool extends AbstractHFCommand {
    @Override
    public String getCommandName() {
        return "tool";
    }

    @Override
    public String getUsage() {
        return "/hf tool [player] <value>";
    }

    private boolean applyLevel(ItemStack stack, double level) {
        return !(stack == null || !(stack.getItem() instanceof ITiered)) && ((ITiered) stack.getItem()).setLevel(stack, level);
    }

    @Override
    public boolean execute(MinecraftServer server, ICommandSender sender, String[] parameters) {
        if (parameters != null && (parameters.length == 1 || parameters.length == 2)) {
            try {
                double level = Double.parseDouble(parameters[parameters.length - 1]);
                EntityPlayerMP player = parameters.length == 1 ? CommandBase.getCommandSenderAsPlayer(sender) : CommandBase.getPlayer(server, sender, parameters[0]);
                return applyLevel(player.getHeldItemOffhand(), level) || applyLevel(player.getHeldItemMainhand(), level);
            } catch (NumberFormatException | CommandException ignored) {}
        }

        return false;
    }
}