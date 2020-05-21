package com.ethanpilz.xyz.Listener;

import com.ethanpilz.xyz.Menu.PlayerMenu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onClick(InventoryClickEvent event) {
        String title = event.getView().getTitle();
        if(title.equals(PlayerMenu.playerMenuTitle)) {
            event.setCancelled(true);
            if(event.getCurrentItem() == null) {
                return;
                }
            }
            if(title.equals(PlayerMenu.playerMenuTitle)){

        }

    }

}
