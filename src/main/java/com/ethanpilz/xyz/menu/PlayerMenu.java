package com.ethanpilz.xyz.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerMenu {

    public static String playerMenuTitle = ChatColor.YELLOW + "" + ChatColor.BOLD + "Player List";
    public static Inventory inv;

    public PlayerMenu() {
        inv = Bukkit.createInventory(null, 9, playerMenuTitle);
        initializeItems();
    }

    public void initializeItems() {
        int slot = 0;
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {

            ItemStack listedplayer = new ItemStack(Material.PLAYER_HEAD, 1, (short) SkullType.PLAYER.ordinal());

            SkullMeta listedplayerMeta = (SkullMeta) listedplayer.getItemMeta();

            listedplayerMeta.setOwningPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()));
            listedplayerMeta.setDisplayName(player.getName());
            listedplayer.setItemMeta(listedplayerMeta);

            List<String> menuItemLore = new ArrayList<>();
            menuItemLore.add(ChatColor.GREEN + "" + ChatColor.UNDERLINE + "Click to teleport");
            menuItemLore.add(ChatColor.YELLOW + "World: " + ChatColor.AQUA + player.getWorld().getName());
            menuItemLore.add(ChatColor.YELLOW + "Health: " + ChatColor.AQUA + player.getHealth() + ChatColor.WHITE + "/" + ChatColor.AQUA + "20");
            menuItemLore.add(ChatColor.YELLOW + "Food: " + ChatColor.AQUA + player.getFoodLevel() + ChatColor.WHITE + "/" + ChatColor.AQUA + "20");
            menuItemLore.add(ChatColor.YELLOW + "Armor: " + ChatColor.AQUA + player.getAttribute(Attribute.GENERIC_ARMOR).getValue() + ChatColor.WHITE + "/" + ChatColor.AQUA + "20");
            menuItemLore.add(ChatColor.YELLOW + "Level: " + ChatColor.AQUA + player.getLevel());
            listedplayerMeta.setLore(menuItemLore);
            listedplayer.setItemMeta(listedplayerMeta);

            inv.setItem(slot, listedplayer);
            slot += 1;

        }
    }

    // You can open the inventory with this
    public void openPlayerlistInventory(Player player) {
        player.openInventory(inv);
    }
}
