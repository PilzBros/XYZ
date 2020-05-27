package com.ethanpilz.xyz.Listener;

import com.ethanpilz.xyz.XYZ;
import com.ethanpilz.xyz.Menu.PlayerMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.ItemStack;

import static com.ethanpilz.xyz.Menu.PlayerMenu.inv;

public class InventoryListener implements Listener {

    private final XYZ xyz;

    public InventoryListener(XYZ xyz) {
        this.xyz = xyz;
    }

    // Check for clicks on items
    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (!e.getView().getTitle().equals(PlayerMenu.playerMenuTitle)) {
            return;
        }

        e.setCancelled(true);

        final ItemStack clickedItem = e.getCurrentItem();

        // verify current item is not null
        if (clickedItem == null || clickedItem.getType() == Material.AIR) return;

        // Using slots click is a best option for your inventory click's
        Player player = Bukkit.getPlayer(clickedItem.getItemMeta().getDisplayName());
        e.getWhoClicked().teleport(player);
    }

    // Cancel dragging in our inventory
    @EventHandler
    public void onInventoryClick(final InventoryDragEvent e) {
        if (e.getInventory() == inv) {
            e.setCancelled(true);
        }
    }
}
