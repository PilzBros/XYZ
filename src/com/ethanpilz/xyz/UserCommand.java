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
                if (sender.hasPermission("xyz.admin") || sender.isOp()) {
                    sender.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "--------------------" + ChatColor.GOLD + ChatColor.BOLD + " XYZ " + ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "--------------------");
                    sender.sendMessage(xyzprefix + ChatColor.AQUA + "/xyza " + ChatColor.RED + "- " + ChatColor.GREEN + "Admin command list");
                    sender.sendMessage(xyzprefix + ChatColor.AQUA + "/xyz me " + ChatColor.RED + "- " + ChatColor.GREEN + "Find your current XYZ coordinates");
                    sender.sendMessage(xyzprefix + ChatColor.AQUA + "/xyz cross" + ChatColor.GREEN + "/"
                            + ChatColor.AQUA + "c" + ChatColor.RED + " - " + ChatColor.GREEN + "XYZ of block in crosshair");
                    sender.sendMessage(xyzprefix + ChatColor.AQUA + "/xyz chunk" + ChatColor.RED + " - " + ChatColor.GREEN + "Chunk coords you're in");
                } else {

                    sender.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "--------------------" + ChatColor.GOLD + ChatColor.BOLD + " XYZ " + ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "--------------------");
                    sender.sendMessage(xyzprefix + ChatColor.AQUA + "/xyz me " + ChatColor.RED + "- " + ChatColor.GREEN + "Find your current XYZ coordinates");
                    sender.sendMessage(xyzprefix + ChatColor.AQUA + "/xyz cross" + ChatColor.GREEN + "/"
                            + ChatColor.AQUA + "c" + ChatColor.RED + " - " + ChatColor.GREEN + "XYZ of block in crosshair");
                    sender.sendMessage(xyzprefix + ChatColor.AQUA + "/xyz chunk" + ChatColor.RED + " - " + ChatColor.GREEN + "Chunk coords you're in");

                }
            } else if (sender instanceof Player) {
                    if (args[0].equalsIgnoreCase("me")) {

                        Player p = (Player) sender;

                        int x = p.getLocation().getBlockX();
                        int y = p.getLocation().getBlockY();
                        int z = p.getLocation().getBlockZ();

                        sender.sendMessage(xyzprefix + ChatColor.YELLOW + "X" + ChatColor.WHITE + " = " + ChatColor.GREEN + x);
                        sender.sendMessage(xyzprefix + ChatColor.YELLOW + "Y" + ChatColor.WHITE + " = " + ChatColor.GREEN + y);
                        sender.sendMessage(xyzprefix + ChatColor.YELLOW + "Z" + ChatColor.WHITE + " = " + ChatColor.GREEN + z);
                        sender.sendMessage(xyzprefix + ChatColor.YELLOW + "World: " + ChatColor.LIGHT_PURPLE + p.getWorld().getName());
                        sender.sendMessage(xyzprefix + ChatColor.YELLOW + "Biome: " + ChatColor.LIGHT_PURPLE + ((Player) sender).getWorld().getBiome(((Player) sender).getLocation().getBlockX(), ((Player) sender).getLocation().getBlockZ()).toString().toLowerCase());

                    } else if (args[0].equalsIgnoreCase("chunk")) {
                        if (sender instanceof Player) {
                            Location playerLocation = ((Player) sender).getLocation();
                            sender.sendMessage(xyzprefix + ChatColor.YELLOW + "Chunk = " + ChatColor.GREEN + playerLocation.getBlock().getChunk().getX()
                                    + ChatColor.YELLOW + ", " + ChatColor.GREEN + playerLocation.getChunk().getZ());
                        }
                    } else if (args[0].equalsIgnoreCase("cross") || args[0].equalsIgnoreCase("c")) {

                        Player p = (Player) sender;

                        Location blockLocation = p.getTargetBlock(null, 100).getLocation();

                        int x = blockLocation.getBlockX();
                        int y = blockLocation.getBlockY();
                        int z = blockLocation.getBlockZ();

                        sender.sendMessage(xyzprefix + ChatColor.GREEN + "Block location in crosshair:");
                        sender.sendMessage(xyzprefix + ChatColor.AQUA + "Type: " + ChatColor.GOLD + blockLocation.getBlock().getType().toString().toLowerCase());
                        sender.sendMessage(xyzprefix + ChatColor.YELLOW + "X = " + ChatColor.GREEN + x);
                        sender.sendMessage(xyzprefix + ChatColor.YELLOW + "Y = " + ChatColor.GREEN + y);
                        sender.sendMessage(xyzprefix + ChatColor.YELLOW + "Z = " + ChatColor.GREEN + z);
                        sender.sendMessage(xyzprefix + ChatColor.YELLOW + "Light = " + ChatColor.GREEN + blockLocation.getBlock().getLightFromSky());
                        sender.sendMessage(xyzprefix + ChatColor.YELLOW + "Chunk = " + ChatColor.GREEN + blockLocation.getBlock().getChunk().getX() + ChatColor.YELLOW + ", " + ChatColor.GREEN
                                + blockLocation.getChunk().getZ());

                    } else {
                        sender.sendMessage(xyzprefix + ChatColor.RED + "Unrecognized command " + ChatColor.AQUA + args[0]);
                    }

                } else {
                    sender.sendMessage(xyzprefix + ChatColor.RED + "You need to be a player to do this.");
                }

            } else {
                sender.sendMessage(xyzAprefix + ChatColor.RED + "You don't have permission to use " + ChatColor.AQUA + args[0]);

            } return true;
        }
    }



