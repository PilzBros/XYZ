package com.ethanpilz.xyz.Listener;

import com.ethanpilz.xyz.XYZ;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerMove(PlayerMoveEvent event) {
     if(XYZ.freezeManager.isPlayerFrozen(event.getPlayer())){
         Location from = event.getFrom();
         Location to = event.getTo();
         event.setCancelled(!(from.getX() == to.getX() && from.getY() == to.getY() && from.getZ() == to.getZ()));
     }
    }
}
