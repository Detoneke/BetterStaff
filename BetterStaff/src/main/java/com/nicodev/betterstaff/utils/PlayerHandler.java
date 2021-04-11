package com.nicodev.betterstaff.utils;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class PlayerHandler {

    private ArrayList<UUID> freezedPlayers = new ArrayList<>();
    private ArrayList<UUID> vanishedPlayers = new ArrayList<>();

    public ArrayList<UUID> getFreezedPlayers() {
        return freezedPlayers;
    }

    public void addFreezed(UUID uuid) {
        this.freezedPlayers.add(uuid);
    }

    public void removeFreezed(UUID uuid) {
        this.freezedPlayers.remove(uuid);
    }

    public ArrayList<UUID> getVanishedPlayers() {
        return vanishedPlayers;
    }

    public void addVanished(UUID uuid){
        this.vanishedPlayers.add(uuid);
    }

    public void removeVanished(UUID uuid){
        this.vanishedPlayers.remove(uuid);
    }
}
