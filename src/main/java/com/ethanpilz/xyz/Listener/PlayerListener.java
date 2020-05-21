package com.ethanpilz.xyz.Listener;

import com.ethanpilz.xyz.XYZ;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

import java.util.Objects;

public class PlayerListener implements Listener {
    
    private final XYZ xyz;
    
    public PlayerListener(XYZ xyz) {
        this.xyz = xyz;
    }

    // Cancel move event if player is frozen
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerMove(PlayerMoveEvent event) {
        if (this.xyz.getFreezeManager().isPlayerFrozen(event.getPlayer())){
         Location from = event.getFrom();
         Location to = event.getTo();
         event.setCancelled(!(from.getX() == to.getX() && from.getY() == to.getY() && from.getZ() == to.getZ()));
         event.setCancelled(!(from.getX() == to.getX() && from.getY() == to.getY() && from.getZ() == to.getZ()));
        }
    }

    // Check portal travel for those without xyz.admin
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerPortal(PlayerPortalEvent event) {
        if (this.xyz.getRealmManager().areRealmsLocked() && (!event.getPlayer().hasPermission("xyz.admin"))) {
            event.setCancelled(true);
            event.getPlayer().sendMessage( XYZ.infoPrefix + ChatColor.YELLOW + "You can't go to " + ChatColor.AQUA +
                    Objects.requireNonNull(Objects.requireNonNull(event.getTo()).getWorld()).getName() + ChatColor.YELLOW + "because portals are locked.");
        }
    }

    // Unfreeze players upon leaving the server
    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerLogout(PlayerQuitEvent event) {
        if (this.xyz.getFreezeManager().isPlayerFrozen(event.getPlayer())){
            this.xyz.getFreezeManager().unfreezePlayer(event.getPlayer());
        }
        if (this.xyz.getBlindManager().isPlayerBlind(event.getPlayer())){
            this.xyz.getBlindManager().unblindPlayer(event.getPlayer());
        }
    }

    // Refuse to let players join when server is in lockdown.
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerPreJoin(AsyncPlayerPreLoginEvent event) {
        if (this.xyz.getLockdownManager().isServerInLockdown()) {
            event.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, ChatColor.RED + "This server currently in lockdown."
                    + ChatColor.YELLOW + "Nobody can join this server.");
        }
    }

    // Cancel teleport for those without xyz.admin if server is in lockdown
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if (this.xyz.getLockdownManager().isServerInLockdown()) {
            if (!event.getPlayer().hasPermission("xyz.admin")) {
                event.setCancelled(true);
            }
        }
    }
}
