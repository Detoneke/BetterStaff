package com.nicodev.betterstaff.commands;

import com.nicodev.betterstaff.BetterStaff;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class MainCommand implements CommandExecutor {

    private BetterStaff plugin;

    public MainCommand(BetterStaff plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String configReloaded = plugin.getPluginConfig().getString("Config.config-reloaded");
        String version = plugin.getPluginConfig().getString("Config.version-cmd");
        if (sender.hasPermission("betterstaff.admin")){
            if (args.length == 0){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lBETTER&D&LSTAFF &8- &aHelp"));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- &8reload &2| &9Reload plugin"));
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- &8version &2| &9Get plugin version"));
                return false;
            }

            switch (args[0].toLowerCase()){
                case "reload":
                    sender.sendMessage(configReloaded);
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
                            "&c&l[!] &fPlayer &7{player}&f executed &8/bstaff reload&f.".replace("{player}", sender.getName())));
                    plugin.reloadConfig();
                    plugin.saveConfig();
                    break;
                case "version":
                    sender.sendMessage(version.replace("{version}", plugin.version));
                    Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
                            "&c&l[!] &fPlayer &7{player}&f executed &8/bstaff version&f.".replace("{player}", sender.getName())));
                    break;
                default:
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6&lBETTER&D&LSTAFF &8- &aHelp"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- &8reload &2| &9Reload plugin"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7- &8version &2| &9Get plugin version"));
                    break;
            }
        }
        return false;
    }
}
