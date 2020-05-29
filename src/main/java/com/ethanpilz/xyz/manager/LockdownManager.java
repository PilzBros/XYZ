package com.ethanpilz.xyz.manager;

import com.ethanpilz.xyz.XYZ;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LockdownManager {

    private XYZ xyz;

    boolean isServerInLockdown;

    public LockdownManager(XYZ xyz) {
        isServerInLockdown = false;
    }

    public void lockdownServer(XYZ xyz) {
        this.xyz = xyz;
        for(Player player : Bukkit.getOnlinePlayers()) {
            if (!(player.hasPermission("xyz.admin"))) {
                this.xyz.getFreezeManager().freezePlayer(player);
            }
        }
        Bukkit.broadcastMessage(XYZ.infoPrefix + ChatColor.RED + "The server is now in lockdown. Joining disabled and movement disabled.");
        this.isServerInLockdown = true;
    }

    public void liftLockdown(XYZ xyz) {
        this.xyz = xyz;
        for(Player player : Bukkit.getOnlinePlayers()) {
            if (!(player.hasPermission("xyz.admin"))) {
                this.xyz.getFreezeManager().unfreezePlayer(player);
            }
        }
        Bukkit.broadcastMessage(XYZ.infoPrefix + ChatColor.RED + "The server lockdown has been lifted. Joining now allowed and movement enabled.");
        this.isServerInLockdown = false;
    }

    public boolean isServerInLockdown() {
        return isServerInLockdown;
    }

}
