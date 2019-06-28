package com.ethanpilz.xyz;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@SuppressWarnings("unused")

public class AdminCommand implements CommandExecutor {

    private final XYZ xyz;
    public boolean portalTravel = true;

    private static final String xyzAprefix = ChatColor.GOLD + "[XYZ] ";
    private static final String xyzprefix = ChatColor.GOLD + "[XYZ] ";
    public static final String xyzVersion = "1.1.8"; //<----- VERSION CHANGE HERE FOR EVERY UPDATE!!!
    
    public AdminCommand(XYZ xyz) {
        this.xyz = xyz;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender.hasPermission("xyz.admin")) {

            if (args.length < 1 || args[0].equalsIgnoreCase("help")) {

                sender.sendMessage(ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "--------------------" + ChatColor.GOLD + ChatColor.BOLD + " XYZ " + ChatColor.GRAY + "" + ChatColor.STRIKETHROUGH + "--------------------");
                sender.sendMessage(ChatColor.AQUA + "/XYZA version");
                sender.sendMessage(ChatColor.AQUA + "/XYZA other" + ChatColor.GREEN + "/" + ChatColor.AQUA + "o (player)");
                sender.sendMessage(ChatColor.AQUA + "/XYZA relocate" + ChatColor.GREEN + "/" + ChatColor.AQUA + "r (player)");
                sender.sendMessage(ChatColor.AQUA + "/XYZA tp (player) (player)");
                sender.sendMessage(ChatColor.AQUA + "/XYZA swap" + ChatColor.GREEN + "/" + ChatColor.AQUA + "s (player) (player)");
                sender.sendMessage(ChatColor.AQUA + "/XYZA freeze (player) " + ChatColor.GREEN + "<- toggle");
                sender.sendMessage(ChatColor.AQUA + "/XYZA distance (player)");
                sender.sendMessage(ChatColor.AQUA + "/XYZA spawn (player)");


            } else if (args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("v")) {

                sender.sendMessage(xyzAprefix + ChatColor.GREEN + "XYZ Version " + ChatColor.LIGHT_PURPLE + xyzVersion);

            } else if (args[0].equalsIgnoreCase("other") || args[0].equalsIgnoreCase("o")) {
                if (args.length == 2) {
                    if (Bukkit.getPlayer(args[1]) != null) {
                        Player target = Bukkit.getServer().getPlayer(args[1]);

                        int x = target.getLocation().getBlockX();
                        int y = target.getLocation().getBlockY();
                        int z = target.getLocation().getBlockZ();

                        sender.sendMessage(xyzAprefix + ChatColor.AQUA + target.getName() + ChatColor.AQUA + "'s" + ChatColor.GREEN + " location is");
                        sender.sendMessage(xyzAprefix + ChatColor.BLUE + "X" + ChatColor.WHITE + " = " + ChatColor.GREEN + x);
                        sender.sendMessage(xyzAprefix + ChatColor.BLUE + "Y" + ChatColor.WHITE + " = " + ChatColor.GREEN + y);
                        sender.sendMessage(xyzAprefix + ChatColor.BLUE + "Z" + ChatColor.WHITE + " = " + ChatColor.GREEN + z);
                        sender.sendMessage(xyzprefix + ChatColor.BLUE + "World: " + ChatColor.LIGHT_PURPLE + target.getWorld().getName());
                        sender.sendMessage(xyzprefix + ChatColor.BLUE + "Biome: " + ChatColor.LIGHT_PURPLE + target.getWorld().getBiome(target.getLocation().getBlockX(), target.getLocation().getBlockZ()));

                    } else {
                        sender.sendMessage(xyzAprefix + ChatColor.DARK_RED + "Invalid player");
                    }

                } else {
                    sender.sendMessage(xyzAprefix + ChatColor.DARK_RED + "You must provide a player");
                }

            } else if (args[0].equalsIgnoreCase("relocate") || (args[0].equalsIgnoreCase("r"))) {
                if (args.length == 2) {
                    if (Bukkit.getPlayer(args[1]) != null) {
                        Player receiver = Bukkit.getPlayer(args[1]);
                        Location originalLocation = receiver.getLocation();
                        Random random = new Random();
                        String name = receiver.getName();
                        int x = random.nextInt(10000);
                        int y = 100;
                        int z = random.nextInt(10000);
                        Location teleportLocation = new Location(receiver.getWorld(), x, y, z);
                        receiver.teleport(teleportLocation);
                        receiver.playSound(receiver.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);

                        sender.sendMessage(xyzAprefix + ChatColor.AQUA + name + ChatColor.YELLOW + " has been moved " + (int) teleportLocation.distance(originalLocation) + " blocks away.");
                        receiver.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 1000000)); //invulnerability for 5 seconds after cast
                        receiver.sendMessage(xyzprefix + ChatColor.YELLOW + "You've been relocated " + ChatColor.AQUA + (int) teleportLocation.distance(originalLocation) + ChatColor.YELLOW + " blocks away.");

                    } else if (Bukkit.getPlayer(args[1]) == null) {
                        sender.sendMessage(xyzAprefix + ChatColor.RED + "Invalid player");
                    } else {
                        sender.sendMessage(xyzAprefix + ChatColor.RED + "Invalid player");
                    }


                } else {
                    sender.sendMessage(xyzAprefix + ChatColor.RED + "You must provide a player.");
                }

            } else if (args[0].equalsIgnoreCase("tp")) {
                if (args.length == 3) {
                    if (Bukkit.getPlayer(args[1]) != null) {
                        if (Bukkit.getPlayer(args[2]) != null) {

                            Player p1 = Bukkit.getPlayer(args[1]);
                            Player p2 = Bukkit.getPlayer(args[2]);

                            String p1name = p1.getName();
                            String p2name = p2.getName();

                            p1.teleport(p2.getLocation());

                            sender.sendMessage(xyzAprefix + ChatColor.GREEN + "Successfully tp'd " + ChatColor.AQUA + p1name + ChatColor.GREEN + " to " + ChatColor.AQUA + p2name);
                            p2.playSound(p2.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);

                        } else {
                            sender.sendMessage(xyzAprefix + ChatColor.RED + "Second player is invalid.");
                        }
                    } else {
                        sender.sendMessage(xyzAprefix + ChatColor.RED + "First and/or second player is invalid.");
                    }
                } else {
                    sender.sendMessage(xyzAprefix + ChatColor.RED + "Not enough players provided. (2 needed)");
                }


            } else if (args[0].equalsIgnoreCase("swap") || (args[0].equalsIgnoreCase("s"))) {
                if (args.length == 3) {
                    if (Bukkit.getPlayer(args[1]) != null) {
                        if (Bukkit.getPlayer(args[2]) != null) {

                            Player p1 = Bukkit.getPlayer(args[1]);
                            Player p2 = Bukkit.getPlayer(args[2]);

                            String p1name = p1.getName();
                            String p2name = p2.getName();

                            Location loc1 = p1.getLocation();
                            Location loc2 = p2.getLocation();

                            p1.teleport(loc2);
                            p2.teleport(loc1);

                            String name1 = p1.getName();
                            String name2 = p2.getName();

                            p1.sendMessage(xyzprefix + "You've been swapped with " + ChatColor.AQUA + name2);
                            p1.playSound(p1.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                            p2.sendMessage(xyzprefix + "You've been swapped with " + ChatColor.AQUA + name1);
                            p2.playSound(p2.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);

                            sender.sendMessage(xyzAprefix + ChatColor.GREEN + "Swapped " + ChatColor.AQUA + p1name + ChatColor.GREEN + " with " + ChatColor.AQUA + p2name);

                        } else {
                            sender.sendMessage(xyzAprefix + ChatColor.RED + "Invalid players");
                        }

                    } else {
                        sender.sendMessage(xyzAprefix + ChatColor.RED + "Invalid players");
                    }

                } else {
                    sender.sendMessage(xyzAprefix + ChatColor.RED + "Not enough players provided. (2 needed)");
                }

            } else if (args[0].equalsIgnoreCase("freeze")) {
                if (args.length == 2) {
                    if (Bukkit.getPlayer(args[1]) != null) {
                        Player p = Bukkit.getPlayer(args[1]);

                        if (this.xyz.getFreezeManager().isPlayerFrozen(p)) {
                            sender.sendMessage(xyzAprefix + ChatColor.AQUA + p.getName() + ChatColor.GREEN + " unfrozen.");
                            p.sendMessage(xyzprefix + ChatColor.AQUA + sender.getName() + ChatColor.GREEN + " has unfrozen you.");
                            this.xyz.getFreezeManager().unfreezePlayer(p);
                            p.playSound(p.getLocation(), Sound.ENTITY_SILVERFISH_AMBIENT, 1, 1);


                        } else {
                            sender.sendMessage(xyzAprefix + ChatColor.AQUA + p.getName() + ChatColor.GREEN + " frozen.");
                            p.sendMessage(xyzprefix + ChatColor.AQUA + sender.getName() + ChatColor.GREEN + " has frozen you.");
                            this.xyz.getFreezeManager().freezePlayer(p);
                            p.playSound(p.getLocation(), Sound.ENTITY_SILVERFISH_AMBIENT, 1, 1);
                        }

                    } else {
                        sender.sendMessage(xyzAprefix + ChatColor.RED + "Invalid player");
                    }

                } else {
                    sender.sendMessage(xyzAprefix + ChatColor.RED + "Please specify a player.");
                }

            } else if (args[0].equalsIgnoreCase("distance")) {
                if (args.length == 2) {
                    if (Bukkit.getPlayer(args[1]) != null) {
                        if (sender instanceof Player) {
                            Player target = Bukkit.getServer().getPlayer(args[1]);

                            Location targetLocation = target.getLocation();
                            Location senderLocation = ((Player) sender).getLocation();
                            sender.sendMessage(xyzAprefix + ChatColor.AQUA + target.getName() + ChatColor.YELLOW + " is " + targetLocation.distance(senderLocation) + " blocks away");
                        } else {
                            sender.sendMessage(xyzAprefix + ChatColor.RED + "This can't be executed by the console, because you don't have coordinates.");
                        }
                    } else {
                        sender.sendMessage(xyzAprefix + ChatColor.RED + "Invalid player");
                    }
                } else {
                    sender.sendMessage(xyzAprefix + ChatColor.RED + "You have to include a player to compare distances with!");
                }
            } else if (args[0].equalsIgnoreCase("spawn")) {
                if (args.length == 2) {
                    if (Bukkit.getPlayer(args[1]) != null) {
                        Player target = Bukkit.getServer().getPlayer(args[1]);
                        Location spawn = Bukkit.getServer().getPlayer(args[1]).getWorld().getSpawnLocation();
                        target.teleport(spawn);
                        target.playSound(target.getLocation(), Sound.ENTITY_DROWNED_SHOOT, 1, 1);
                    } else {
                        sender.sendMessage(xyzAprefix + ChatColor.RED + "Invalid player");
                    }
                } else {
                    sender.sendMessage(xyzAprefix + ChatColor.RED + "Please specify a player.");
                }
            } else if (args[0].equalsIgnoreCase("portal") || args[0].equalsIgnoreCase("p")) {
                if (args.length == 2){
                    if(args[2] == "allow" || args[2] == "unlock"){
                        boolean portalTravel = true;
                    }
                    else if (args[2] == "deny" || args[2] == "lock"){
                        boolean portalTravel = false;
                    }
                } else {
                    sender.sendMessage(xyzAprefix + ChatColor.RED + "Not enough arguments." +ChatColor.AQUA + "/xyza portal (lock/unlock)");
                }
            } else {
                String command = args[0];
                sender.sendMessage(xyzAprefix + ChatColor.RED + "Command " + ChatColor.AQUA + command + ChatColor.RED + " unrecognized.");
            }
        } else if (!sender.hasPermission("xyz.admin")) {
            sender.sendMessage(xyzAprefix + ChatColor.RED + "Insufficient permission to use "
                    + ChatColor.AQUA + args[0] + ChatColor.RED + ". You'll need" + ChatColor.AQUA + " xyz.admin" + ChatColor.RED + " to do that.");
        } else {
            sender.sendMessage(xyzprefix + ChatColor.RED + "Something went wrong...");
        }
        return true;
    }
}
