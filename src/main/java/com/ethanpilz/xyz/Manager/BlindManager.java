package com.ethanpilz.xyz.Manager;

import com.ethanpilz.xyz.XYZ;
import org.bukkit.Sound;
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
     * @param player Bukkit Player
     */
    public void blindPlayer(Player player){

        blindPlayers.add(player);
        player.playSound(player.getLocation(), Sound.ENTITY_BAT_DEATH, 1, 1);
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, -1, 1));
    }

    /**
     * Unblinding a player
     * @param player Bukkit Player
     */
    public void unblindPlayer(Player player){

        blindPlayers.remove(player);
        player.playSound(player.getLocation(), Sound.ENTITY_BAT_LOOP, 1, 1);
        for (PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }

    }

    /**
     * Return if the player is blind
     * @param player Bukkit Player
     * @return
     */
    public boolean isPlayerBlind(Player player){

        return blindPlayers.contains(player);

    }

}
