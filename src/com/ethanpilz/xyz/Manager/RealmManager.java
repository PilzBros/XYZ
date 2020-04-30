
package com.ethanpilz.xyz.Manager;

import com.ethanpilz.xyz.XYZ;
import org.bukkit.entity.Player;
import java.util.HashSet;
import java.util.Set;

public class RealmManager {

    private Set<Player> frozenPlayers;

    public RealmManager(XYZ xyz){
        frozenPlayers = new HashSet<>();
    }

    /**
     * Locking portals
     * @param player
     */
    public void lockRealms(Player player){

        frozenPlayers.add(player);
        player.setWalkSpeed(0);
    }

    /**
     * Unlocking portals
     * @param player
     */
    public void unlockRealms(Player player){

        frozenPlayers.remove(player);
        player.setWalkSpeed(0.2f);

    }

    /**
     * Return if the portals are locked
     * @param player
     * @return
     */
    public boolean areRealmsLocked(Player player){

        return frozenPlayers.contains(player);

    }

}
