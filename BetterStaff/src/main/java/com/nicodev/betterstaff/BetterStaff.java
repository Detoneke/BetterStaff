package com.nicodev.betterstaff;

import com.nicodev.betterstaff.commands.FreezeCommand;
import com.nicodev.betterstaff.commands.MainCommand;
import com.nicodev.betterstaff.commands.StaffchatCommand;
import com.nicodev.betterstaff.commands.VanishCommand;
import com.nicodev.betterstaff.events.FreezeEvent;
import com.nicodev.betterstaff.utils.File;
import com.nicodev.betterstaff.utils.PlayerHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetterStaff extends JavaPlugin {

    private PlayerHandler playerHandler;
    PluginDescriptionFile pluginyml = getDescription();
    public String version = pluginyml.getVersion();
    private final File config = new File(this,  "config");

    @Override
    public void onEnable() {
        // Mensajes de inicio
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&6&lBETTER&e&lSTAFF &8| &fLoaded &asuccessful!"));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&6&lBETTER&e&lSTAFF &8| &fPlugin version is &a" + version + "&f."));
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&',
                "&6&lBETTER&e&lSTAFF &8| &fPlease, leave a good review on Spigot."));

        // Cargar comandos
        getCommand("freeze").setExecutor(new FreezeCommand(this));
        getCommand("vanish").setExecutor(new VanishCommand(this));
        getCommand("staffchat").setExecutor(new StaffchatCommand(this));
        getCommand("betterstaff").setExecutor(new MainCommand(this));

        //Cargar métodos - eventos
        Bukkit.getPluginManager().registerEvents(new FreezeEvent(this), this);
        playerHandler = new PlayerHandler();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public File getPluginConfig(){
        return config;
    }

    public PlayerHandler getPlayerHandler() {
        return playerHandler;
    }

}
