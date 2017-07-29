package com.ethanpilz.xyz;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashSet;


public class UserCommand implements CommandExecutor {

    private static final String xyzprefix = ChatColor.GOLD + "[XYZ] ";
    private static final String xyzAprefix = ChatColor.GOLD + "[XYZ] ";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender.hasPermission("xyz.user")) {

            if (args.length < 1 || args[0].equalsIgnoreCase("help")) {

                sender.sendMessage(xyzprefix + "Command list");
                sender.sendMessage(xyzprefix + ChatColor.AQUA + "/XYZAdmin " + ChatColor.GREEN + "Admin command list");
                sender.sendMessage(xyzprefix + ChatColor.AQUA + "/XYZ me " + ChatColor.GREEN + "Find your current XYZ coordinates");
                sender.sendMessage(xyzprefix + ChatColor.AQUA + "/XYZ cross " + ChatColor.GREEN + "Coordinates of block in crosshair");

            } else if (args[0].equalsIgnoreCase("me")) {

                Player p = (Player) sender;

                int x = p.getLocation().getBlockX();
                int y = p.getLocation().getBlockY();
                int z = p.getLocation().getBlockZ();

                sender.sendMessage(xyzprefix + ChatColor.BLUE + "X" + ChatColor.WHITE + " = " + ChatColor.GREEN + x);
                sender.sendMessage(xyzprefix + ChatColor.BLUE + "Y" + ChatColor.WHITE + " = " + ChatColor.GREEN + y);
                sender.sendMessage(xyzprefix + ChatColor.BLUE + "Z" + ChatColor.WHITE + " = " + ChatColor.GREEN + z);

            } else if (args[0].equalsIgnoreCase("cross") || args[0].equalsIgnoreCase("c")) {

                Player p = (Player) sender;

                Location blockLocation = p.getTargetBlock((HashSet<Material>) null, 10).getLocation();

                int x = blockLocation.getBlockX();
                int y = blockLocation.getBlockY();
                int z = blockLocation.getBlockZ();

                sender.sendMessage(xyzprefix + ChatColor.GREEN + "Block location in crosshair:");
                sender.sendMessage(xyzprefix + ChatColor.AQUA + "Type: " + ChatColor.GOLD + blockLocation.getBlock().getType().toString());
                sender.sendMessage(xyzprefix + ChatColor.YELLOW + "X = " + ChatColor.GREEN + x);
                sender.sendMessage(xyzprefix + ChatColor.YELLOW + "Y = " + ChatColor.GREEN + y);
                sender.sendMessage(xyzprefix + ChatColor.YELLOW + "Z = " + ChatColor.GREEN + z);

            } else {
                sender.sendMessage(xyzprefix + ChatColor.DARK_RED + "Unrecognized command " + ChatColor.AQUA + args[0]);
            }

            } else {
                sender.sendMessage(xyzAprefix + ChatColor.RED + "You don't have permission to use " + ChatColor.AQUA + args[0]);

        }
            return true;
    }

}


