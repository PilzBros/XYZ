package com.ethanpilz.xyz;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.Material;
import org.bukkit.SkullType;

import static com.ethanpilz.xyz.Strings.HelpMenu.*;
import static com.ethanpilz.xyz.XYZ.SpigotVersion;

@SuppressWarnings("unused")

public class AdminCommand implements CommandExecutor {

    private final XYZ xyz;
    public boolean portalTravel = true;
    public Inventory inv;

    private static final String xyzAprefix = ChatColor.GOLD + "[XYZ] ";
    private static final String xyzprefix = ChatColor.GOLD + "[XYZ] ";
    public static final String xyzVersion = "1.1.9"; //<----- VERSION CHANGE HERE FOR EVERY UPDATE!!!

    public AdminCommand(XYZ xyz) {
        this.xyz = xyz;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (sender.hasPermission("xyz.admin")) {

            if (args.length < 1) {

                sender.sendMessage(tophelpborder);
                sender.sendMessage(ChatColor.AQUA + "/xyza version" + ChatColor.GREEN + "/" + ChatColor.AQUA + "v");
                sender.sendMessage(ChatColor.AQUA + "/xyza other" + ChatColor.GREEN + "/" + ChatColor.AQUA + "o (player)");
                sender.sendMessage(ChatColor.AQUA + "/xyza relocate" + ChatColor.GREEN + "/" + ChatColor.AQUA + "r (player)");
                sender.sendMessage(ChatColor.AQUA + "/xyza tp (player) (player)");
                sender.sendMessage(ChatColor.AQUA + "/xyza swap" + ChatColor.GREEN + "/" + ChatColor.AQUA + "s (player) (player)");
                sender.sendMessage(ChatColor.AQUA + "/xyza freeze (player) " + ChatColor.GREEN + "<- toggle");
                sender.sendMessage(ChatColor.AQUA + "/xyza distance (player)");
                sender.sendMessage(ChatColor.AQUA + "/xyza serverinfo");
                sender.sendMessage(bottomhelpborder1);

            } else if (args[0].equalsIgnoreCase("help")) {
                if (args.length == 1 || args[1].equalsIgnoreCase("1")) {
                    sender.sendMessage(tophelpborder);
                    sender.sendMessage(ChatColor.AQUA + "/xyza version" + ChatColor.GREEN + "/" + ChatColor.AQUA + "v");
                    sender.sendMessage(ChatColor.AQUA + "/xyza other" + ChatColor.GREEN + "/" + ChatColor.AQUA + "o (player)");
                    sender.sendMessage(ChatColor.AQUA + "/xyza relocate" + ChatColor.GREEN + "/" + ChatColor.AQUA + "r (player)");
                    sender.sendMessage(ChatColor.AQUA + "/xyza tp (player) (player)");
                    sender.sendMessage(ChatColor.AQUA + "/xyza swap" + ChatColor.GREEN + "/" + ChatColor.AQUA + "s (player) (player)");
                    sender.sendMessage(ChatColor.AQUA + "/xyza freeze (player) " + ChatColor.GREEN + "<- toggle");
                    sender.sendMessage(ChatColor.AQUA + "/xyza distance (player)");
                    sender.sendMessage(ChatColor.AQUA + "/xyza serverinfo");
                    sender.sendMessage(bottomhelpborder1);

                } else if (args[1].equalsIgnoreCase("2")) {
                        sender.sendMessage(tophelpborder);
                        sender.sendMessage(ChatColor.AQUA + "/xyza stalk (player)");
                        sender.sendMessage(ChatColor.AQUA + "/xyza clear (player)" + ChatColor.RED + " - " + ChatColor.GREEN + "Clear player potion effects");
                        sender.sendMessage(ChatColor.AQUA + "/xyza blind (player) " + ChatColor.GREEN + "<- toggle");
                        sender.sendMessage(ChatColor.AQUA + "/xyza spawn (player)");
                        sender.sendMessage(ChatColor.AQUA + "/xyza players");
                        sender.sendMessage(bottomhelpborder2);
                        
                } else if (Integer.parseInt(args[1]) > 2) {

                    sender.sendMessage(xyzAprefix + ChatColor.RED + "Invalid page.");
                }
            } else if (args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("v")) {

                sender.sendMessage(xyzAprefix + ChatColor.YELLOW + ">>Spigot Official " + ChatColor.AQUA + xyzVersion);
                sender.sendMessage(xyzAprefix + ChatColor.YELLOW + "Last built for " + ChatColor.GREEN + SpigotVersion + ChatColor.YELLOW + " on" + ChatColor.AQUA + "April 29, 2020");

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

                            sender.sendMessage(xyzAprefix + ChatColor.GREEN + "Teleported " + ChatColor.AQUA + p1name + ChatColor.GREEN + " to " + ChatColor.AQUA + p2name);
                            p2.playSound(p2.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);

                        } else {
                            sender.sendMessage(xyzAprefix + ChatColor.RED + "Second player is invalid.");
                        }
                    } else {
                        sender.sendMessage(xyzAprefix + ChatColor.RED + "First and/or second player is invalid.");
                    }
                } else if (args.length == 2) {
                    if (Bukkit.getPlayer(args[1]) != null) {
                        Player p1 = (Player) sender;
                        Player p2 = Bukkit.getPlayer(args[1]);
                        String p2name = p2.getName();

                        p1.teleport(p2.getLocation());
                        sender.sendMessage(xyzAprefix + ChatColor.GREEN + "Teleported to " + ChatColor.AQUA + p2name);
                        p2.playSound(p2.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
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
                            if (((Player) sender).getWorld().equals(target.getWorld())) {
                                sender.sendMessage(xyzAprefix + ChatColor.AQUA + target.getName() + ChatColor.YELLOW + " is " + ((int) targetLocation.distance(senderLocation)) + " blocks away");
                            } else {
                                sender.sendMessage(xyzAprefix + ChatColor.RED + "You're in " + ChatColor.GREEN + ((Player) sender).getWorld().getName()
                                        + ChatColor.RED + " and " + ChatColor.AQUA + target.getName() + ChatColor.RED + " is in " + ChatColor.GREEN + target.getWorld().getName());
                            }
                        } else {
                            sender.sendMessage(xyzAprefix + ChatColor.RED + "This can't be executed by the console, because you don't have coordinates.");
                        }
                    } else {
                        sender.sendMessage(xyzAprefix + ChatColor.RED + "Invalid player");
                    }
                } else if (args.length > 2) {
                    sender.sendMessage(xyzAprefix + ChatColor.RED + "Too many arguments. Correct usage: " + ChatColor.AQUA + "/xyza distance (player)");
                } else {
                    sender.sendMessage(xyzAprefix + ChatColor.RED + "You have to include a player to compare distances with.");
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
                if (args.length == 2) {
                    if (args[2] == "allow" || args[2] == "unlock") {
                        boolean portalTravel = true;
                        sender.sendMessage(xyzAprefix + "Portals have been" + ChatColor.GREEN + "unlocked.");
                    } else if (args[2] == "deny" || args[2] == "lock") {
                        boolean portalTravel = false;
                        sender.sendMessage(xyzAprefix + "Portals have been" + ChatColor.RED + "locked.");

                    } else if (args[2] != "lock" || args[2] != "unlock") {
                        sender.sendMessage(xyzAprefix + ChatColor.RED + "Unknown request " + ChatColor.AQUA + args[2] + ChatColor.RED + ". " + ChatColor.RED + "Use " +
                                ChatColor.GREEN + "/xyza portal (lock/unlock)");
                    }
                } else {
                    sender.sendMessage(xyzAprefix + ChatColor.RED + "Not enough arguments. " + ChatColor.AQUA + "/xyza portal (lock/unlock)");
                }
            } else if (args[0].equalsIgnoreCase("stalk")) {
                if (args.length == 2) {
                    if (Bukkit.getPlayer(args[1]) != null) {
                        Player target = Bukkit.getServer().getPlayer(args[1]);
                        Player sender1 = (Player) sender;
                        sender1.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000000, 1)); //invisibilty forever
                        sender1.teleport(target.getLocation());
                    } else {
                        sender.sendMessage(xyzAprefix + ChatColor.RED + "Invalid player " + ChatColor.AQUA + args[1]);
                    }
                } else {
                    sender.sendMessage(xyzAprefix + ChatColor.RED + "You have to include a player to stalk.");
                }
            } else if (args[0].equalsIgnoreCase("clear")) {
                if (args.length == 2) {
                    if (Bukkit.getPlayer(args[1]) != null) {
                        sender.sendMessage(xyzAprefix + ChatColor.YELLOW + "Potion effects cleared from " + ChatColor.AQUA + Bukkit.getPlayer(args[1]).getName());
                        Player player = Bukkit.getServer().getPlayer(args[1]);
                        assert player != null;
                        for (PotionEffect effect : player.getActivePotionEffects()) {
                            player.removePotionEffect(effect.getType());
                        }
                    }
                } else if (args.length == 1) {
                    if (sender instanceof Player) {
                        sender.sendMessage(xyzAprefix + ChatColor.YELLOW + "Cleared your potion effects.");
                        Player sender1 = (Player) sender;
                        for (PotionEffect effect : sender1.getActivePotionEffects()) {
                            sender1.removePotionEffect(effect.getType());
                        }
                    }
                }
            } else if (args[0].equalsIgnoreCase("blind")) {
                if (args.length == 2) {
                    if (Bukkit.getPlayer(args[1]) != null) {
                        Player p = Bukkit.getPlayer(args[1]);

                        if (this.xyz.getBlindManager().isPlayerBlind(p)) {
                            sender.sendMessage(xyzAprefix + ChatColor.AQUA + p.getName() + ChatColor.GREEN + " is no longer blinded.");
                            this.xyz.getBlindManager().unblindPlayer(p);
                            p.playSound(p.getLocation(), Sound.ENTITY_BAT_LOOP, 1, 1);

                        } else {
                            sender.sendMessage(xyzAprefix + ChatColor.AQUA + p.getName() + ChatColor.GREEN + " blinded.");
                            this.xyz.getBlindManager().blindPlayer(p);
                            p.playSound(p.getLocation(), Sound.ENTITY_BAT_DEATH, 1, 1);
                        }

                    } else {
                        sender.sendMessage(xyzAprefix + ChatColor.RED + "Invalid player " + ChatColor.AQUA);
                    }

                } else {
                    sender.sendMessage(xyzAprefix + ChatColor.RED + "You have to include a player to blind.");
                }

            } else if (args[0].equalsIgnoreCase("serverinfo")){

                sender.sendMessage(xyzAprefix + ChatColor.YELLOW + "Server version: " + ChatColor.AQUA + Bukkit.getServer().getBukkitVersion());
                sender.sendMessage(xyzAprefix + ChatColor.YELLOW + "IP: " + ChatColor.AQUA + Bukkit.getServer().getIp());
                sender.sendMessage(xyzAprefix + ChatColor.YELLOW + "Total memory - " + ChatColor.AQUA + (Runtime.getRuntime().totalMemory()/1024/1024) + " MB");
                sender.sendMessage(xyzAprefix + ChatColor.YELLOW + "Free memory - " + ChatColor.AQUA + (Runtime.getRuntime().freeMemory()/1024/1024) + " MB");
                sender.sendMessage(xyzAprefix + ChatColor.YELLOW + "Online Mode: " + ChatColor.AQUA + Bukkit.getServer().getOnlineMode());
                sender.sendMessage(xyzAprefix + ChatColor.YELLOW + "Banned: " + ChatColor.AQUA + Bukkit.getServer().getBannedPlayers().size());
                for(World world : Bukkit.getWorlds()) {
                    sender.sendMessage(xyzAprefix + ChatColor.YELLOW + "World: " + ChatColor.AQUA + world.getName());
                }
            } else if (args[0].equalsIgnoreCase("players")) {


                Inventory inv = Bukkit.createInventory(null, 9, ChatColor.YELLOW + "" + ChatColor.BOLD + "Player List");
                int slot = 0;
                for (Player all : Bukkit.getServer().getOnlinePlayers()) {

                    ItemStack listedplayer = new ItemStack(Material.PLAYER_HEAD, 1, (short) SkullType.PLAYER.ordinal());

                    SkullMeta listedplayerMeta = (SkullMeta) listedplayer.getItemMeta();

                    listedplayerMeta.setOwner(all.getName());
                    listedplayerMeta.setDisplayName(all.getName());
                    listedplayer.setItemMeta(listedplayerMeta);

                    inv.setItem(slot, listedplayer);
                    slot += 1;

                    Player player = (Player) sender;
                    player.openInventory(inv);
                    return true;

                }
            }
                    else {
                    String command = args[0];
                    sender.sendMessage(xyzAprefix + ChatColor.RED + "Command " + ChatColor.AQUA + command + ChatColor.RED + " unrecognized.");
                }
            } else {
            sender.sendMessage(xyzAprefix + ChatColor.RED + "Sorry, you don't have permissions to use " + ChatColor.AQUA + args[0] + ChatColor.RED + ". You'll need "
                    + ChatColor.GREEN + "xyz.admin");
        }
        return true;
        }

    }

