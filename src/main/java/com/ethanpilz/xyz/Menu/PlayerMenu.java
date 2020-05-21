package com.ethanpilz.xyz.Menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerMenu {

    public static String playerMenuTitle;

    public static void openMenu(Player player) {

        playerMenuTitle = ChatColor.YELLOW + "" + ChatColor.BOLD + "Player List";
        Inventory inv = Bukkit.createInventory(null, 9, playerMenuTitle);
        int slot = 0;
        for (Player all : Bukkit.getServer().getOnlinePlayers()) {

            ItemStack listedplayer = new ItemStack(Material.PLAYER_HEAD, 1, (short) SkullType.PLAYER.ordinal());

            SkullMeta listedplayerMeta = (SkullMeta) listedplayer.getItemMeta();

            listedplayerMeta.setOwner(all.getName());
            listedplayerMeta.setDisplayName(all.getName());
            listedplayer.setItemMeta(listedplayerMeta);

            List<String> menuItemLore = new ArrayList<String>();
            menuItemLore.add(ChatColor.GREEN + "Click to teleport");
            menuItemLore.add(ChatColor.YELLOW + "World: " + ChatColor.AQUA + all.getWorld().getName());
            menuItemLore.add(ChatColor.YELLOW + "Health: " + ChatColor.AQUA + all.getHealth() + ChatColor.WHITE + "/" + ChatColor.AQUA + "20");
            menuItemLore.add(ChatColor.YELLOW + "Food: " + ChatColor.AQUA + all.getFoodLevel() + ChatColor.WHITE + "/" + ChatColor.AQUA + "20");
            menuItemLore.add(ChatColor.YELLOW + "Level: " + ChatColor.AQUA + all.getLevel());
            listedplayerMeta.setLore(menuItemLore);
            listedplayer.setItemMeta(listedplayerMeta);

            inv.setItem(slot, listedplayer);
            slot += 1;

            player.openInventory(inv);
        }

    }
}
