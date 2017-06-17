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

public class AdminCommand implements CommandExecutor {

    private static final String xyzAprefix = ChatColor.GOLD + "[XYZ Admin] ";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender.hasPermission("xyz.admin")) {

            if (args.length < 1 || args[0].equalsIgnoreCase("help")) {

                sender.sendMessage(xyzAprefix + "Your commands:");
                sender.sendMessage(xyzAprefix + ChatColor.AQUA + "/XYZAdmin other (player)" + ChatColor.GREEN + " Find another player's XYZ coordinates.");
                sender.sendMessage(xyzAprefix + ChatColor.AQUA + "/XYZAdmin relocate (player)" + ChatColor.GREEN + " Randomize a player's location");



            } else if (args[0].equalsIgnoreCase("other")){
                if(args.length == 2){
                    if(Bukkit.getOfflinePlayer(args[1]).isOnline()){
                        Player target = Bukkit.getServer().getPlayer(args[1]);

                        int x = target.getLocation().getBlockX();
                        int y = target.getLocation().getBlockY();
                        int z = target.getLocation().getBlockZ();
                        String name = target.getName();

                        sender.sendMessage(xyzAprefix + ChatColor.AQUA + name + ChatColor.AQUA + "'s" + ChatColor.GREEN +" location is");
                        sender.sendMessage(xyzAprefix + ChatColor.BLUE + "X = " + ChatColor.GREEN + x);
                        sender.sendMessage(xyzAprefix + ChatColor.BLUE + "Y = " + ChatColor.GREEN + y);
                        sender.sendMessage(xyzAprefix + ChatColor.BLUE + "Z = " + ChatColor.GREEN + z);

                    } else {
                        sender.sendMessage(xyzAprefix + ChatColor.DARK_RED + "Invalid player");
                    }

                } else {
                    sender.sendMessage(xyzAprefix + ChatColor.DARK_RED + "You must provide a player name.");
                }

            } else if (args[0].equalsIgnoreCase("relocate")){
                if (args.length == 2){
                    if(Bukkit.getOfflinePlayer(args[1]).isOnline()){
                        Player player = (Player) sender;
                        Location originalLocation = player.getLocation();
                        Random random = new Random();
                        String name = player.getName();
                        int x = random.nextInt(10000);
                        int y = 150;
                        int z = random.nextInt(10000);
                        Location teleportLocation = new Location(player.getWorld(), x, y, z);
                        player.teleport(teleportLocation);

                        sender.sendMessage(xyzAprefix + ChatColor.AQUA + name + ChatColor.YELLOW + " has been moved " + (int) teleportLocation.distance(originalLocation) + " blocks away");

                        player.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 1000000)); //invulnerability for 10 seconds after cast

                    } else {
                        sender.sendMessage(xyzAprefix + ChatColor.DARK_RED + "Invalid player");
                    }

                } else {
                    sender.sendMessage(xyzAprefix + ChatColor.DARK_RED + "You have to provide a player.");
                }

            } else {
                sender.sendMessage(xyzAprefix + ChatColor.DARK_RED + "Unrecognized command.");
            }

        } else {

            sender.sendMessage(xyzAprefix + ChatColor.DARK_RED + "You don't have permission to use this command");

        }
        return true;
    }
}