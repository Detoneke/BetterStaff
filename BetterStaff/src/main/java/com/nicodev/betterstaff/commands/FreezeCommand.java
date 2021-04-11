package com.nicodev.betterstaff.commands;

import com.nicodev.betterstaff.BetterStaff;
import com.nicodev.betterstaff.events.FreezeEvent;
import com.nicodev.betterstaff.utils.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class FreezeCommand implements CommandExecutor, Listener {

    private BetterStaff plugin;
    public FreezeCommand(BetterStaff plugin){
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
        //Config
        String noArgs = plugin.getPluginConfig().getString("Config.Freeze.Messages.no-arguments");
        String invalidPlayer = plugin.getPluginConfig().getString("Config.invalid-player");
        Player player = (Player) sender;
        if (player.hasPermission("betterstaff.freeze.use")){
            if (args.length == 0){
                player.sendMessage(noArgs);
                return false;
            }
            if (Bukkit.getPlayer(args[0]) == null){
                player.sendMessage(invalidPlayer);
                return false;
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (getPlayerHandler().getFreezedPlayers().contains(target.getUniqueId())){
                getPlayerHandler().removeFreezed(target.getUniqueId());
            }else {
                getPlayerHandler().addFreezed(target.getUniqueId());
                String freezed = plugin.getPluginConfig().getString("Config.Freeze.Messages.sender-target-freezed");
                player.sendMessage(freezed);

            }
        }else {
            String noPerms = plugin.getPluginConfig().getString("Config.no-permission");
            player.sendMessage(noPerms);
        }
        return false;
    }
}
