package dev.jamieisgeek.pvpcontrol.Commands;

import dev.jamieisgeek.pvpcontrol.Manager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class PvPCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player player)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        if(args.length == 0) {
            player.sendMessage("Usage: /pvp <on|off>");
            return true;
        }

        if(args[0].equalsIgnoreCase("on")) {
            if(Manager.getManager().playerPvPStatus(player.getUniqueId())) {
                player.sendMessage("PvP is already enabled!");
                return true;
            }

            Manager.getManager().setPlayerPvPStatus(player.getUniqueId(), true);
            player.sendMessage(ChatColor.GREEN + "PvP Enabled!");
            return true;
        } else if (args[0].equalsIgnoreCase("off")) {
            if(!Manager.getManager().playerPvPStatus(player.getUniqueId())) {
                player.sendMessage("PvP is already disabled!");
                return true;
            }

            Manager.getManager().setPlayerPvPStatus(player.getUniqueId(), false);
            player.sendMessage(ChatColor.RED + "PvP Disabled!");
            return true;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(command.getLabel().equalsIgnoreCase("pvp")) {
            if(args.length == 1) {
                return List.of("on", "off");
            }
        }

        return null;
    }
}
