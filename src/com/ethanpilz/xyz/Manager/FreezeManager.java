package com.ethanpilz.xyz.Manager;

import org.bukkit.entity.Player;

import java.util.HashSet;

public class FreezeManager {

    private HashSet<Player> frozenPlayers;

    public FreezeManager(){

        frozenPlayers = new HashSet<>();

    }

    /**
     * Freezing a player
     * @param player
     */
    public void freezePlayer(Player player){

        frozenPlayers.add(player);
        player.setWalkSpeed(0);
    }

    /**
     * Unfreezing a player
     * @param player
     */
    public void unfreezePlayer(Player player){

        frozenPlayers.remove(player);
        player.setWalkSpeed(0.2f);

    }

    /**
     * Return if the player is frozen
     * @param player
     * @return
     */
    public boolean isPlayerFrozen(Player player){

        return frozenPlayers.contains(player);

    }

}
