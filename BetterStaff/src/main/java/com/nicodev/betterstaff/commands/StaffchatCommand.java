package com.nicodev.betterstaff.commands;

import com.nicodev.betterstaff.BetterStaff;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Iterator;

public class StaffchatCommand implements CommandExecutor {

    private final BetterStaff plugin;
    public StaffchatCommand(BetterStaff plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("That command is only for players!");
            return false;
        }
        Player player = (Player) sender;
        if (args.length == 0){
            String noArgs = plugin.getPluginConfig().getString("Config.Staffchat.Messages.no-arguments");
            player.sendMessage(noArgs);
            return false;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < args.length; i++){
            stringBuilder.append(args[i]).append(" ");
        }

        Iterator var10 = Bukkit.getOnlinePlayers().iterator();
        while (var10.hasNext()){
            Player players = (Player)var10.next();
            if (players.hasPermission("betterstaff.staffchat.use")){
                String format = plugin.getPluginConfig().getString("Config.Staffchat.format");
                players.sendMessage(format.replace("{player}", player.getName()).replace("{message}", stringBuilder.toString()));
            }
        }
        return false;
    }
}
