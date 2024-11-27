package com.KillCommand;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class KillCommandPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Register the /kill command
        PluginCommand killCommand = getCommand("kill");
        if (killCommand != null) {
            killCommand.setExecutor(new KillCommandExecutor());
        }
        getLogger().info(ChatColor.GREEN + "KillCommandPlugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info(ChatColor.RED + "KillCommandPlugin disabled!");
    }

    private static class KillCommandExecutor implements TabExecutor {

        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
                return true;
            }

            Player player = (Player) sender;

            // Check if the player is not already dead
            if (!player.isDead()) {
                player.setHealth(0.0);  // Set player's health to 0 (kills the player)
            } else {
                player.sendMessage(ChatColor.DARK_RED + "You are already dead!");
            }

            return true;
        }

        @Override
        public java.util.List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
            return java.util.Collections.emptyList();
        }
    }
}
