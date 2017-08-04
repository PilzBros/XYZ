package com.ethanpilz.xyz;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@SuppressWarnings("unused")

public class AdminCommand implements CommandExecutor {

    private final XYZ xyz;

    private static final String xyzAprefix = ChatColor.GOLD + "[XYZ] ";
    private static final String xyzprefix = ChatColor.GOLD + "[XYZ] ";
    
    public AdminCommand(XYZ xyz) {
        this.xyz = xyz;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender.hasPermission("xyz.admin")) {

            if (args.length < 1 || args[0].equalsIgnoreCase("help")) {

                sender.sendMessage(xyzAprefix + "Command list:");
                sender.sendMessage(xyzAprefix + ChatColor.AQUA + "/XYZA other (player)");
                sender.sendMessage(xyzAprefix + ChatColor.AQUA + "/XYZA relocate (player)");
                sender.sendMessage(xyzAprefix + ChatColor.AQUA + "/XYZA version");
                sender.sendMessage(xyzAprefix + ChatColor.AQUA + "/XYZA tp (player) (player)");
                sender.sendMessage(xyzAprefix + ChatColor.AQUA + "/XYZA swap (player) (player)");
                sender.sendMessage(xyzAprefix + ChatColor.AQUA + "/XYZA freeze (player) " + ChatColor.GREEN + "<- toggle");



            } else if (args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("v")){

                sender.sendMessage(xyzAprefix + ChatColor.GREEN + "XYZ Version " + ChatColor.LIGHT_PURPLE + "2.5"); //VERSION CHANGE HERE

            }
              else if (args[0].equalsIgnoreCase("other")){
                if(args.length == 2){
                    if(Bukkit.getPlayer(args[1]).isOnline()){
                        Player target = Bukkit.getServer().getPlayer(args[1]);

                        int x = target.getLocation().getBlockX();
                        int y = target.getLocation().getBlockY();
                        int z = target.getLocation().getBlockZ();
                        String name = target.getName();

                        sender.sendMessage(xyzAprefix + ChatColor.AQUA + name + ChatColor.AQUA + "'s" + ChatColor.GREEN +" location is");
                        sender.sendMessage(xyzAprefix + ChatColor.BLUE + "X" + ChatColor.WHITE + " = " + ChatColor.GREEN + x);
                        sender.sendMessage(xyzAprefix + ChatColor.BLUE + "Y" + ChatColor.WHITE + " = " + ChatColor.GREEN + y);
                        sender.sendMessage(xyzAprefix + ChatColor.BLUE + "Z" + ChatColor.WHITE + " = " + ChatColor.GREEN + z);

                    } else {
                        sender.sendMessage(xyzAprefix + ChatColor.DARK_RED + "Invalid player");
                    }

                } else {
                    sender.sendMessage(xyzAprefix + ChatColor.DARK_RED + "You must provide a player name.");
                }

            } else if (args[0].equalsIgnoreCase("relocate")){
                if (args.length == 2){
                    if(Bukkit.getPlayer(args[1]).isOnline()){
                        Player receiver = Bukkit.getPlayer(args[1]);
                        Location originalLocation = receiver.getLocation();
                        Random random = new Random();
                        String name = receiver.getName();
                        int x = random.nextInt(10000);
                        int y = 100;
                        int z = random.nextInt(10000);
                        Location teleportLocation = new Location(receiver.getWorld(), x, y, z);
                        receiver.teleport(teleportLocation);

                        sender.sendMessage(xyzAprefix + ChatColor.AQUA + name + ChatColor.YELLOW + " has been moved " + (int) teleportLocation.distance(originalLocation) + " blocks away");

                        receiver.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 1000000)); //invulnerability for 5 seconds after cast

                    } else {
                        sender.sendMessage(xyzAprefix + ChatColor.RED + "Invalid player");
                    }

                } else {
                    sender.sendMessage(xyzAprefix + ChatColor.RED + "You have to provide a player.");
                }

            } else if (args[0].equalsIgnoreCase("tp")) {
                if (args.length == 3){
                    if(Bukkit.getPlayer(args[1]).isOnline()){
                        if(Bukkit.getPlayer(args[2]).isOnline()){

                            Player p1 = Bukkit.getPlayer(args[1]);
                            Player p2 = Bukkit.getPlayer(args[2]);

                            String p1name = p1.getName();
                            String p2name = p2.getName();

                            p1.teleport(p2.getLocation());

                            sender.sendMessage(xyzAprefix + ChatColor.GREEN + "Successfully tp'd " + ChatColor.AQUA + p1name + ChatColor.GREEN + " to " + ChatColor.AQUA + p2name);

                        } else {
                            sender.sendMessage(xyzAprefix + ChatColor.RED + "Second player is invalid.");
                        }
                    } else {
                        sender.sendMessage(xyzAprefix + ChatColor.RED + "First player is invalid.");
                    }
                } else {
                    sender.sendMessage(xyzAprefix + ChatColor.RED + "Not enough players provided. (2 needed)");
                }


            } else if (args[0].equalsIgnoreCase("swap")) {
                if (args.length == 3) {
                     if (Bukkit.getPlayer(args[1]).isOnline()) {
                        if (Bukkit.getPlayer(args[2]).isOnline()) {

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
                            p2.sendMessage(xyzprefix + "You've been swapped with " + ChatColor.AQUA + name1);

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
                if (args.length == 2){
                    if(Bukkit.getPlayer(args[1]).isOnline()) {
                        Player p = Bukkit.getPlayer(args[1]);

                        if(this.xyz.getFreezeManager().isPlayerFrozen(p)){
                            sender.sendMessage(xyzAprefix + ChatColor.AQUA + p.getName() + ChatColor.GREEN + " unfrozen.");
                            p.sendMessage(xyzprefix + ChatColor.AQUA + sender.getName() + ChatColor.GREEN + " has unfrozen you.");
                            this.xyz.getFreezeManager().unfreezePlayer(p);


                        } else {
                            sender.sendMessage(xyzAprefix + ChatColor.AQUA + p.getName() + ChatColor.GREEN + " frozen.");
                            p.sendMessage(xyzprefix + ChatColor.AQUA + sender.getName() + ChatColor.GREEN + " has frozen you.");
                            this.xyz.getFreezeManager().freezePlayer(p);
                        }

                    } else {
                        sender.sendMessage(xyzAprefix + ChatColor.RED + "Invalid player " + ChatColor.AQUA + args[1]);
                    }

                } else {
                    sender.sendMessage(xyzAprefix + ChatColor.RED + "Please specify a player.");
                }

            } else {
                String unrecognized = args[0];
                sender.sendMessage(xyzAprefix + ChatColor.RED + "Unrecognized command " + ChatColor.AQUA + unrecognized);
            }

        } else {
            sender.sendMessage(xyzAprefix + ChatColor.RED + "You don't have permission to use " + ChatColor.AQUA + args[0]);
        }
        return true;
    }
}
