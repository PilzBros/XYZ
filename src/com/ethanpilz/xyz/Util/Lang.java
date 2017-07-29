package com.ethanpilz.xyz.Util;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class Lang {

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

    public static String uncolor(String string) {
        return ChatColor.stripColor(string);
    }

    public void sendToSender(CommandSender commandSender, String message) {
        commandSender.sendMessage(Lang.color(message));
    }
}