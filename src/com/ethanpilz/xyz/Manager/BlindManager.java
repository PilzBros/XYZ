package com.ethanpilz.xyz.Manager;

import com.ethanpilz.xyz.XYZ;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.HashSet;
import java.util.Set;

public class BlindManager {

    private Set<Player> blindPlayers;

    public BlindManager(XYZ xyz){
        blindPlayers = new HashSet<>();
    }

    /**
     * Blinding a player
     * @param player
     */
    public void blindPlayer(Player player){

        blindPlayers.add(player);
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 100000000, 1));
    }

    /**
     * Unblinding a player
     * @param player
     */
    public void unblindPlayer(Player player){

        blindPlayers.remove(player);
        for (PotionEffect effect : player.getActivePotionEffects())
        {
            player.removePotionEffect(effect.getType());
        }

    }

    /**
     * Return if the player is blind
     * @param player
     * @return
     */
    public boolean isPlayerBlind(Player player){

        return blindPlayers.contains(player);

    }

}
