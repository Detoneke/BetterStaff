package com.nicodev.betterstaff.commands;

import com.nicodev.betterstaff.BetterStaff;
import com.nicodev.betterstaff.utils.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    private BetterStaff plugin;
    public VanishCommand(BetterStaff plugin){
        this.plugin = plugin;
    }
    private PlayerHandler getPlayerHandler(){
        return plugin.getPlayerHandler();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("That command is only for players!");
            return false;
        }
        Player player = (Player) sender;
        String unvanished = plugin.getPluginConfig().getString("Config.Vanish.Messages.unvanished");
        String vanished = plugin.getPluginConfig().getString("Config.Vanish.Messages.vanished");
        if (player.hasPermission("betterstaff.vanish.use")){
            if (args.length == 0){
                if (getPlayerHandler().getVanishedPlayers().contains(player.getUniqueId())){
                    getPlayerHandler().removeVanished(player.getUniqueId());
                    for (Player people : Bukkit.getOnlinePlayers()){
                        player.sendMessage(unvanished);
                        people.showPlayer(player);
                        return false;
                    }
                }else {
                    getPlayerHandler().addVanished(player.getUniqueId());
                    for (Player people : Bukkit.getOnlinePlayers()){
                        player.sendMessage(vanished);
                        people.hidePlayer(player);
                        return false;
                    }
                }
            }
            if (Bukkit.getPlayer(args[0]) == null){
                String invalidPlayer = plugin.getPluginConfig().getString("Config.invalid-player");
                player.sendMessage(invalidPlayer);
                return false;
            }else{
                Player target = Bukkit.getPlayer(args[0]);
                if (getPlayerHandler().getVanishedPlayers().contains(target.getUniqueId())){
                    getPlayerHandler().removeVanished(target.getUniqueId());
                    for (Player people : Bukkit.getOnlinePlayers()){
                        String targetUnvanished = plugin.getPluginConfig().getString("Config.Vanish.Messages.target-unvanished");
                        people.showPlayer(target);
                        player.sendMessage(targetUnvanished.replace("{target}", target.getName()));
                        target.sendMessage(unvanished);
                        return false;
                    }
                }else{
                    getPlayerHandler().addVanished(target.getUniqueId());
                    for (Player people : Bukkit.getOnlinePlayers()){
                        String targetVanished = plugin.getPluginConfig().getString("Config.Vanish.Messages.target-vanished");
                        people.hidePlayer(target);
                        player.sendMessage(targetVanished.replace("{target}", target.getName()));
                        target.sendMessage(vanished);
                    }
                }
            }
        }
        return false;
    }
}