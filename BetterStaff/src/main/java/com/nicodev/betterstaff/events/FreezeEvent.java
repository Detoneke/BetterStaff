package com.nicodev.betterstaff.events;

import com.nicodev.betterstaff.BetterStaff;
import com.nicodev.betterstaff.utils.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class FreezeEvent implements Listener {

    private final BetterStaff plugin;

    public FreezeEvent(BetterStaff plugin) {
        this.plugin = plugin;
    }

    private PlayerHandler getPlayerHandler() {
        return plugin.getPlayerHandler();
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent e) {
        String freezed = plugin.getPluginConfig().getString("Config.Freeze.Messages.target-freezed");
        Player player = e.getPlayer();
        if (getPlayerHandler().getFreezedPlayers().contains(player.getUniqueId())) {
            e.setCancelled(true);
            int delay = plugin.getPluginConfig().getInt("Config.Freeze.freezed-message-cooldown");
            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                @Override
                public void run() {
                    player.sendMessage(freezed);
                }
            }, delay * 20L, 20);
        }
    }
}