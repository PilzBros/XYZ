package com.ethanpilz.xyz.Listener;

import com.ethanpilz.xyz.XYZ;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import com.ethanpilz.xyz.AdminCommand;

public class PlayerListener implements Listener {
    
    private final XYZ xyz;
    
    public PlayerListener(XYZ xyz) {
        this.xyz = xyz;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerMove(PlayerMoveEvent event) {
     if(this.xyz.getFreezeManager().isPlayerFrozen(event.getPlayer())){
         Location from = event.getFrom();
         Location to = event.getTo();
         event.setCancelled(!(from.getX() == to.getX() && from.getY() == to.getY() && from.getZ() == to.getZ()));
     }
    }
    @EventHandler(priority = EventPriority.NORMAL)
    public void onRealmTravel(PlayerPortalEvent event) {
        if(this.xyz.
    }
}
