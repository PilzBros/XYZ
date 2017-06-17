package com.ethanpilz.xyz;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UserCommand implements CommandExecutor {

    private static final String xyzprefix = ChatColor.GOLD + "[XYZ] ";

        @Override
        public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
        {

            if (sender.hasPermission("xyz.user")){

                if(args.length < 1 || args[0].equalsIgnoreCase("help")){

                    sender.sendMessage(xyzprefix + "Your commands ↓");
                    sender.sendMessage(xyzprefix + ChatColor.AQUA + "/XYZ me " + ChatColor.GREEN + "Find your current XYZ coordinates.");
                    sender.sendMessage(xyzprefix + ChatColor.LIGHT_PURPLE + "More coming soon");

                } else if (args[0].equalsIgnoreCase("me")){

                    Player p = (Player) sender;

                    int x = p.getLocation().getBlockX();
                    int y = p.getLocation().getBlockY();
                    int z = p.getLocation().getBlockZ();

                    sender.sendMessage(xyzprefix + ChatColor.YELLOW + "X = " + ChatColor.GREEN + x);
                    sender.sendMessage(xyzprefix + ChatColor.YELLOW + "Y = " + ChatColor.GREEN + y);
                    sender.sendMessage(xyzprefix + ChatColor.YELLOW + "Z = " + ChatColor.GREEN + z);

                } else {
                    sender.sendMessage(xyzprefix + ChatColor.DARK_RED + "Unrecognized command");
                }


            } else {
                sender.sendMessage(xyzprefix + ChatColor.DARK_RED + "You don't have permission to use this command");
            }
        return true;
    }
}

