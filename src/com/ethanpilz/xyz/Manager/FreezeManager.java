package com.ethanpilz.xyz.Manager;

import com.ethanpilz.xyz.XYZ;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class FreezeManager {

    private Set<Player> frozenPlayers;

    public FreezeManager(XYZ xyz){
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
