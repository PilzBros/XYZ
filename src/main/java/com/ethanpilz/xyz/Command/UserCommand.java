package com.ethanpilz.xyz.Command;

import com.ethanpilz.xyz.XYZ;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

import static com.ethanpilz.xyz.Strings.HelpMenu.*;
import static com.ethanpilz.xyz.XYZ.redDash;
import static com.ethanpilz.xyz.XYZ.xyzPrefix;


public class UserCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender.hasPermission("xyz.user")) {

            if (args.length < 1 || args[0].equalsIgnoreCase("help")) {
                if (sender.hasPermission("xyz.admin") || sender.isOp()) {
                    sender.sendMessage(tophelpborder);
                    sender.sendMessage(xyzPrefix + ChatColor.AQUA + "/xyza " + XYZ.redDash + ChatColor.GREEN + "Admin command list");
                } else {
                    sender.sendMessage(tophelpborder);
                }
                    sender.sendMessage(xyzPrefix + ChatColor.AQUA + "/xyz me " + XYZ.redDash + ChatColor.GREEN + "Find your current XYZ coordinates");
                    sender.sendMessage(xyzPrefix + ChatColor.AQUA + "/xyz cross" + ChatColor.WHITE + "/"
                            + ChatColor.AQUA + "c" + XYZ.redDash + ChatColor.GREEN + "XYZ of block in crosshair");
                    sender.sendMessage(xyzPrefix + ChatColor.AQUA + "/xyz chunk" + XYZ.redDash + ChatColor.GREEN + "Chunk coords you're in");
                    sender.sendMessage(xyzPrefix + ChatColor.AQUA + "/xyz share" + ChatColor.WHITE + "/" + ChatColor.AQUA + "s " + ChatColor.AQUA + "(player)" + redDash + ChatColor.GREEN + " Send your location to the player");


            } else if (sender instanceof Player) {
                    if (args[0].equalsIgnoreCase("me")) {

                        Player p = (Player) sender;

                        int x = p.getLocation().getBlockX();
                        int y = p.getLocation().getBlockY();
                        int z = p.getLocation().getBlockZ();

                        sender.sendMessage(xyzPrefix + ChatColor.YELLOW + "X" + XYZ.redDash + ChatColor.YELLOW + x);
                        sender.sendMessage(xyzPrefix + ChatColor.YELLOW + "Y" + XYZ.redDash + ChatColor.YELLOW + y);
                        sender.sendMessage(xyzPrefix + ChatColor.YELLOW + "Z" + XYZ.redDash + ChatColor.YELLOW + z);
                        sender.sendMessage(xyzPrefix + ChatColor.YELLOW + "World: " + ChatColor.LIGHT_PURPLE + p.getWorld().getName());
                        sender.sendMessage(xyzPrefix + ChatColor.YELLOW + "Biome: " + ChatColor.LIGHT_PURPLE + ((Player) sender).getWorld().getBiome(((Player) sender).getLocation().getBlockX(), ((Player) sender).getLocation().getBlockZ()).toString().toLowerCase());

                    } else if (args[0].equalsIgnoreCase("chunk")) {
                        Location playerLocation = ((Player) sender).getLocation();
                        sender.sendMessage(xyzPrefix + ChatColor.YELLOW + "Chunk" + XYZ.redDash + ChatColor.GREEN + playerLocation.getBlock().getChunk().getX()
                                + ChatColor.YELLOW + ", " + ChatColor.GREEN + playerLocation.getChunk().getZ());
                    } else if (args[0].equalsIgnoreCase("cross") || args[0].equalsIgnoreCase("c")) {

                        Player p = (Player) sender;

                        Location blockLocation = p.getTargetBlock(null, 100).getLocation();

                        int x = blockLocation.getBlockX();
                        int y = blockLocation.getBlockY();
                        int z = blockLocation.getBlockZ();

                        sender.sendMessage(xyzPrefix + ChatColor.GREEN + "Block location in crosshair:");
                        sender.sendMessage(xyzPrefix + ChatColor.AQUA + "Material: " + ChatColor.GOLD + "minecraft:" + blockLocation.getBlock().getType().name().toLowerCase());
                        sender.sendMessage(xyzPrefix + ChatColor.YELLOW + "X" + XYZ.redDash + ChatColor.GREEN + x);
                        sender.sendMessage(xyzPrefix + ChatColor.YELLOW + "Y" + XYZ.redDash + ChatColor.GREEN + y);
                        sender.sendMessage(xyzPrefix + ChatColor.YELLOW + "Z" + XYZ.redDash + ChatColor.GREEN + z);
                        sender.sendMessage(xyzPrefix + ChatColor.YELLOW + "Light" + XYZ.redDash + ChatColor.GREEN + blockLocation.getBlock().getLightFromSky());
                        sender.sendMessage(xyzPrefix + ChatColor.YELLOW + "Chunk" + XYZ.redDash +ChatColor.GREEN + blockLocation.getBlock().getChunk().getX() + ChatColor.YELLOW + ", " + ChatColor.GREEN
                                + blockLocation.getChunk().getZ());

                    } else if (args[0].equalsIgnoreCase("share") || args[0].equalsIgnoreCase("s")) {
                        if (args.length == 2 && Bukkit.getPlayer(args[1]) != null) {
                                Player player = (Player) sender;
                                Player target = Bukkit.getPlayer(args[1]);

                                int x = player.getLocation().getBlockX();
                                int y = player.getLocation().getBlockY();
                                int z = player.getLocation().getBlockZ();
                                String world = Objects.requireNonNull(player.getLocation().getWorld()).getName();

                                target.sendMessage(xyzPrefix + ChatColor.AQUA + player.getName() + ChatColor.YELLOW + " is sharing their location with you");
                                target.sendMessage( ChatColor.YELLOW + "        X" + XYZ.redDash + ChatColor.AQUA + x);
                                target.sendMessage( ChatColor.YELLOW + "        Y" + XYZ.redDash + ChatColor.AQUA + y);
                                target.sendMessage( ChatColor.YELLOW + "        Z" + XYZ.redDash + ChatColor.AQUA + z);
                                target.sendMessage( ChatColor.YELLOW + "        World" + redDash + ChatColor.AQUA + world);

                                sender.sendMessage(xyzPrefix + ChatColor.YELLOW + "Your location has successfully been sent to " + ChatColor.AQUA + target.getName());

                        }

                    } else {
                        sender.sendMessage(xyzPrefix + ChatColor.RED + "Unrecognized command " + ChatColor.AQUA + args[0]);
                    }

                } else {
                    sender.sendMessage(xyzPrefix + ChatColor.RED + "You need to be a player to do this.");
                }

            } else {
                sender.sendMessage(xyzPrefix + ChatColor.RED + "You don't have permission to use " + ChatColor.AQUA + args[0]);

            } return true;
        }
    }



