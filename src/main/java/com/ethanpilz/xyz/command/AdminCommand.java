package com.ethanpilz.xyz.command;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import com.ethanpilz.xyz.XYZ;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import sun.jvm.hotspot.gc.shared.Generation;

import static com.ethanpilz.xyz.strings.HelpMenu.*;
import static com.ethanpilz.xyz.XYZ.*;

@SuppressWarnings("unused")

public class AdminCommand implements CommandExecutor {

    private final XYZ xyz;

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
                        sender.sendMessage(ChatColor.AQUA + "/xyza clear (player)" + XYZ.redDash + ChatColor.GREEN + "Clear player potion effects");
                        sender.sendMessage(ChatColor.AQUA + "/xyza blind (player) " + ChatColor.GREEN + "<- toggle");
                        sender.sendMessage(ChatColor.AQUA + "/xyza spawn (player)");
                        sender.sendMessage(ChatColor.AQUA + "/xyza players" + redDash + ChatColor.YELLOW + "view online players in GUI");
                        sender.sendMessage(ChatColor.AQUA + "/xyza portals (lock/unlock)" + ChatColor.RED + " - " + ChatColor.YELLOW + "disable portal travel");
                        sender.sendMessage(ChatColor.AQUA + "/xyza lockdown " + ChatColor.GREEN + "<- toggle");
                        sender.sendMessage(ChatColor.AQUA + "/xyza home (player)" + redDash + ChatColor.GREEN + "tp to player home");
                        sender.sendMessage(bottomhelpborder2);

                    } else if (Integer.parseInt(args[1]) > 2) {

                        sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "Invalid page.");
                    }
                } else if (args[0].equalsIgnoreCase("version") || args[0].equalsIgnoreCase("v")) {

                    sender.sendMessage(XYZ.xyzaPrefix + ChatColor.YELLOW + ">> Spigot Official " + ChatColor.AQUA + pluginVersion);
                    sender.sendMessage(XYZ.xyzaPrefix + ChatColor.YELLOW + "Last built for " + ChatColor.GREEN + spigotVersion + ChatColor.YELLOW + " on" + buildDate);

                } else if (args[0].equalsIgnoreCase("other") || args[0].equalsIgnoreCase("o")) {
                    if (args.length == 2) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            Player target = Bukkit.getServer().getPlayer(args[1]);

                            int x = target.getLocation().getBlockX();
                            int y = target.getLocation().getBlockY();
                            int z = target.getLocation().getBlockZ();

                            sender.sendMessage(XYZ.xyzaPrefix + ChatColor.AQUA + target.getName() + ChatColor.AQUA + "'s " + ChatColor.YELLOW + "location is");
                            sender.sendMessage(ChatColor.YELLOW + "X" + redDash + ChatColor.AQUA + x);
                            sender.sendMessage(ChatColor.YELLOW + "Y" + redDash + ChatColor.AQUA + y);
                            sender.sendMessage(ChatColor.YELLOW + "Z" + redDash + ChatColor.AQUA + z);
                            sender.sendMessage(ChatColor.YELLOW + "World" + redDash + ChatColor.LIGHT_PURPLE + target.getWorld().getName());
                            sender.sendMessage(ChatColor.YELLOW + "Biome" + redDash + ChatColor.LIGHT_PURPLE + target.getWorld().getBiome(target.getLocation().getBlockX(), target.getLocation().getBlockZ()));

                        } else {
                            sender.sendMessage(XYZ.xyzaPrefix + ChatColor.DARK_RED + "Invalid player");
                        }

                    } else {
                        sender.sendMessage(XYZ.xyzaPrefix + ChatColor.DARK_RED + "You must provide a player");
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

                            sender.sendMessage(XYZ.xyzaPrefix + ChatColor.AQUA + name + ChatColor.YELLOW + " has been moved " + (int) teleportLocation.distance(originalLocation) + " blocks away.");
                            receiver.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 1000000)); //invulnerability for 5 seconds after cast
                            receiver.sendMessage(XYZ.xyzPrefix + ChatColor.YELLOW + "You've been relocated " + ChatColor.AQUA + (int) teleportLocation.distance(originalLocation) + ChatColor.YELLOW + " blocks away.");

                        } else if (Bukkit.getPlayer(args[1]) == null) {
                            sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "Invalid player");
                        } else {
                            sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "Invalid player");
                        }


                    } else {
                        sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "You must provide a player.");
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

                                sender.sendMessage(XYZ.xyzaPrefix + ChatColor.GREEN + "Teleported " + ChatColor.AQUA + p1name + ChatColor.GREEN + " to " + ChatColor.AQUA + p2name);
                                p2.playSound(p2.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);

                            } else {
                                sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "Second player is invalid.");
                            }
                        } else {
                            sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "First and/or second player is invalid.");
                        }
                    } else if (args.length == 2) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            Player p1 = (Player) sender;
                            Player p2 = Bukkit.getPlayer(args[1]);
                            String p2name = p2.getName();

                            p1.teleport(p2.getLocation());
                            sender.sendMessage(XYZ.xyzaPrefix + ChatColor.GREEN + "Teleported to " + ChatColor.AQUA + p2name);
                            p2.playSound(p2.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                        }
                    } else {
                        sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "Not enough players provided. (2 needed)");
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

                                p1.sendMessage(XYZ.xyzPrefix + "You've been swapped with " + ChatColor.AQUA + name2);
                                p1.playSound(p1.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
                                p2.sendMessage(XYZ.xyzPrefix + "You've been swapped with " + ChatColor.AQUA + name1);
                                p2.playSound(p2.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);

                                sender.sendMessage(XYZ.xyzaPrefix + ChatColor.GREEN + "Swapped " + ChatColor.AQUA + p1name + ChatColor.GREEN + " with " + ChatColor.AQUA + p2name);

                            } else {
                                sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "Invalid players");
                            }

                        } else {
                            sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "Invalid players");
                        }

                    } else {
                        sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "Not enough players provided. (2 needed)");
                    }

                } else if (args[0].equalsIgnoreCase("freeze")) {
                    if (args.length == 2) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            Player p = Bukkit.getPlayer(args[1]);

                            if (this.xyz.getFreezeManager().isPlayerFrozen(p)) {
                                sender.sendMessage(XYZ.xyzaPrefix + ChatColor.AQUA + p.getName() + ChatColor.GREEN + " unfrozen.");
                                p.sendMessage(XYZ.xyzPrefix + ChatColor.AQUA + sender.getName() + ChatColor.GREEN + " has unfrozen you.");
                                this.xyz.getFreezeManager().unfreezePlayer(p);
                                p.playSound(p.getLocation(), Sound.ENTITY_SILVERFISH_AMBIENT, 1, 1);


                            } else {
                                sender.sendMessage(XYZ.xyzaPrefix + ChatColor.AQUA + p.getName() + ChatColor.GREEN + " frozen.");
                                p.sendMessage(XYZ.xyzPrefix + ChatColor.AQUA + sender.getName() + ChatColor.GREEN + " has frozen you.");
                                this.xyz.getFreezeManager().freezePlayer(p);
                                p.playSound(p.getLocation(), Sound.ENTITY_SILVERFISH_AMBIENT, 1, 1);
                            }

                        } else {
                            sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "Invalid player");
                        }

                    } else {
                        sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "Please specify a player.");
                    }

                } else if (args[0].equalsIgnoreCase("distance")) {
                    if (args.length == 2) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            if (sender instanceof Player) {
                                Player target = Bukkit.getServer().getPlayer(args[1]);

                                Location targetLocation = target.getLocation();
                                Location senderLocation = ((Player) sender).getLocation();
                                if (((Player) sender).getWorld().equals(target.getWorld())) {
                                    sender.sendMessage(XYZ.xyzaPrefix + ChatColor.AQUA + target.getName() + ChatColor.YELLOW + " is " + ((int) targetLocation.distance(senderLocation)) + " blocks away");
                                } else {
                                    sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "You're in " + ChatColor.GREEN + ((Player) sender).getWorld().getName()
                                            + ChatColor.RED + " and " + ChatColor.AQUA + target.getName() + ChatColor.RED + " is in " + ChatColor.GREEN + target.getWorld().getName());
                                }
                            } else {
                                sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "This can't be executed by the console, because you don't have coordinates.");
                            }
                        } else {
                            sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "Invalid player");
                        }
                    } else if (args.length > 2) {
                        sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "Too many arguments. Correct usage: " + ChatColor.AQUA + "/xyza distance (player)");
                    } else {
                        sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "You have to include a player to compare distances with.");
                    }
                } else if (args[0].equalsIgnoreCase("spawn")) {
                    if (args.length == 2) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            Player target = Bukkit.getServer().getPlayer(args[1]);
                            Location spawn = Bukkit.getServer().getPlayer(args[1]).getWorld().getSpawnLocation();
                            target.teleport(spawn);
                            target.playSound(target.getLocation(), Sound.ENTITY_DROWNED_SHOOT, 1, 1);
                            sender.sendMessage(xyzaPrefix + ChatColor.AQUA + target.getName() + ChatColor.YELLOW + " successfully sent to spawn in world " + ChatColor.AQUA + target.getWorld().getName());
                        } else {
                            sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "Invalid player");
                        }
                    } else {
                        sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "Please specify a player.");
                    }
                } else if (args[0].equalsIgnoreCase("portals") || args[0].equalsIgnoreCase("p")) {
                    if (args.length == 2) {
                        if (args[1].equals("allow") || args[1].equals("unlock")) {
                            xyz.getRealmManager().unlockRealms();
                            sender.sendMessage(XYZ.xyzaPrefix + ChatColor.YELLOW + "Portals have been " + ChatColor.GREEN + "unlocked.");
                        } else if (args[1].equalsIgnoreCase("deny") || args[1].equalsIgnoreCase("lock")) {
                            xyz.getRealmManager().lockRealms();
                            sender.sendMessage(XYZ.xyzaPrefix + ChatColor.YELLOW + "Portals have been " + ChatColor.RED + "locked.");

                        } else {
                            sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "Unrecognized argument. Try" + ChatColor.AQUA + "/xyza portals (lock/unlock)");

                        }
                    } else {
                        if (xyz.getRealmManager().areRealmsLocked()) {
                            sender.sendMessage(XYZ.xyzaPrefix + ChatColor.YELLOW + "Currently, portals are " + ChatColor.RED + "locked" + ChatColor.YELLOW + ". " + ChatColor.YELLOW + "Change that with " + ChatColor.AQUA + "/xyza portals (lock/unlock)");
                        } else if (!xyz.getRealmManager().areRealmsLocked()) {
                            sender.sendMessage(XYZ.xyzaPrefix + ChatColor.YELLOW + "Currently, portals are " + ChatColor.GREEN + "unlocked" + ChatColor.YELLOW + ". " + ChatColor.YELLOW + "Change that with " + ChatColor.AQUA + "/xyza portals (lock/unlock)");
                        }
                    }

                } else if (args[0].equalsIgnoreCase("stalk")) {
                    if (args.length == 2) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            Player target = Bukkit.getServer().getPlayer(args[1]);
                            Player sender1 = (Player) sender;
                            sender1.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, -1, 1)); //invisibilty forever
                            sender1.teleport(target.getLocation());
                        } else {
                            sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "Invalid player " + ChatColor.AQUA + args[1]);
                        }
                    } else {
                        sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "You have to include a player to stalk.");
                    }
                } else if (args[0].equalsIgnoreCase("clear")) {
                    if (args.length == 2) {
                        if (Bukkit.getPlayer(args[1]) != null) {
                            sender.sendMessage(XYZ.xyzaPrefix + ChatColor.YELLOW + "Potion effects cleared from " + ChatColor.AQUA + Bukkit.getPlayer(args[1]).getName());
                            Player player = Bukkit.getServer().getPlayer(args[1]);
                            assert player != null;
                            for (PotionEffect effect : player.getActivePotionEffects()) {
                                player.removePotionEffect(effect.getType());
                            }
                        }
                    } else if (args.length == 1) {
                        if (sender instanceof Player) {
                            sender.sendMessage(XYZ.xyzaPrefix + ChatColor.YELLOW + "Cleared your potion effects.");
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
                                sender.sendMessage(XYZ.xyzaPrefix + ChatColor.AQUA + p.getName() + ChatColor.GREEN + " is no longer blinded.");
                                this.xyz.getBlindManager().unblindPlayer(p);
                            } else {
                                sender.sendMessage(XYZ.xyzaPrefix + ChatColor.AQUA + p.getName() + ChatColor.GREEN + " blinded.");
                                this.xyz.getBlindManager().blindPlayer(p);
                            }

                        } else {
                            sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "Invalid player " + ChatColor.AQUA);
                        }

                    } else {
                        sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "You have to include a player to blind.");
                    }

                } else if (args[0].equalsIgnoreCase("serverinfo")) {

                    sender.sendMessage(XYZ.xyzaPrefix + ChatColor.YELLOW + "Server version: " + ChatColor.AQUA + Bukkit.getServer().getBukkitVersion());
                    sender.sendMessage(XYZ.xyzaPrefix + ChatColor.YELLOW + "IP" + redDash + ChatColor.AQUA + Bukkit.getServer().getIp());
                    sender.sendMessage(XYZ.xyzaPrefix + ChatColor.YELLOW + "Total memory" + redDash + ChatColor.AQUA + (Runtime.getRuntime().totalMemory() / 1024 / 1024) + " MB");
                    sender.sendMessage(XYZ.xyzaPrefix + ChatColor.YELLOW + "Free memory" + redDash +ChatColor.AQUA + (Runtime.getRuntime().freeMemory() / 1024 / 1024) + " MB");
                    sender.sendMessage(XYZ.xyzaPrefix + ChatColor.YELLOW + "Portals locked" + redDash + ChatColor.AQUA + xyz.getRealmManager().areRealmsLocked());
                    sender.sendMessage(XYZ.xyzaPrefix + ChatColor.YELLOW + "Online Mode" + redDash + ChatColor.AQUA + Bukkit.getServer().getOnlineMode());
                    sender.sendMessage(XYZ.xyzaPrefix + ChatColor.YELLOW + "Banned" + redDash + ChatColor.AQUA + Bukkit.getServer().getBannedPlayers().size());
                    sender.sendMessage(xyzaPrefix + ChatColor.YELLOW + "Worlds" + redDash + ChatColor.AQUA + Bukkit.getWorlds().stream().map(World::getName).collect(Collectors.joining(ChatColor.WHITE + ", " + ChatColor.AQUA)));

                } else if (args[0].equalsIgnoreCase("players")) {
                    if (sender instanceof Player) {
                        Player player = (Player) sender;
                        player.sendMessage(xyzaPrefix + ChatColor.RED + "This command is disabled for this build of XYZ. Sorry");

                    } else {
                        sender.sendMessage(xyzaPrefix + ChatColor.RED + "You need to be a player to do this.");
                    }

                } else if (args[0].equalsIgnoreCase("lockdown")) {
                    if (this.xyz.getLockdownManager().isServerInLockdown()) {
                        this.xyz.getLockdownManager().liftLockdown(xyz);
                        sender.sendMessage(XYZ.xyzaPrefix + ChatColor.GREEN + "Lockdown lifted.");
                    } else if (!this.xyz.getLockdownManager().isServerInLockdown()) {
                        this.xyz.getLockdownManager().lockdownServer(xyz);
                        sender.sendMessage(XYZ.xyzaPrefix + ChatColor.GREEN + "Lockdown imposed.");
                    }

                } else if (args[0].equalsIgnoreCase("world")) {
                    if (args.length == 2) {
                        if (Bukkit.getWorld(args[1]) != null) {
                            World world = Bukkit.getWorld(args[1]);
                            sender.sendMessage(xyzaPrefix + ChatColor.AQUA + world.getName() + ChatColor.YELLOW + " information:");
                            sender.sendMessage(xyzaPrefix + ChatColor.AQUA + "Environment" + redDash + world.getEnvironment().toString().toLowerCase());
                            sender.sendMessage(xyzaPrefix + ChatColor.AQUA + "PVP" + redDash + world.getPVP());
                            sender.sendMessage(xyzaPrefix + ChatColor.AQUA + "Time" + redDash + world.getTime());
                            sender.sendMessage(xyzaPrefix + ChatColor.AQUA + "Difficulty" + redDash + world.getDifficulty().toString().toLowerCase());
                            sender.sendMessage(xyzaPrefix + ChatColor.AQUA + "Players" + redDash + ChatColor.WHITE + Bukkit.getWorld(args[1]).getPlayers().stream().map(Player::getName).collect(Collectors.joining(ChatColor.AQUA + ", " + ChatColor.WHITE)));
                        } else {
                            sender.sendMessage(xyzaPrefix + ChatColor.RED + "Invalid world. These look like worlds to me:\n" + ChatColor.AQUA + Bukkit.getWorlds().stream().map(World::getName).collect(Collectors.joining(ChatColor.WHITE + ", " + ChatColor.AQUA)));
                        }

                    } else {
                        sender.sendMessage(xyzaPrefix + ChatColor.RED + "You must include a world name.");
                    }

                } else if (args[0].equalsIgnoreCase("home")) {
                    if (args.length == 2) {
                        if (Bukkit.getPlayer(args[1]) != null) {

                            Player player = (Player) sender;
                            Optional<Location> playerLocation = Optional.empty();
                            Player target = Bukkit.getPlayer(args[1]);

                            try {
                                playerLocation = XYZ.homeController.getHome(target);
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            if (playerLocation.isPresent()) {
                                player.teleport(playerLocation.get());
                                sender.sendMessage(xyzPrefix + ChatColor.YELLOW + "Teleported to " + ChatColor.AQUA + target.getName() + "'s " + ChatColor.YELLOW + "home.");

                            } else {
                                sender.sendMessage(xyzPrefix + ChatColor.AQUA + target.getName() + ChatColor.RED + " has no home set.");
                            }

                        } else {
                            sender.sendMessage(xyzaPrefix + ChatColor.RED + "Invalid player " + ChatColor.AQUA + args[1]);
                        }
                    } else {
                        sender.sendMessage(xyzaPrefix + ChatColor.RED + "Invalid syntax. Use " + ChatColor.AQUA + "/xyza home (player)");
                    }
                }

                else {
                    String command = args[0];
                    sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "Command " + ChatColor.AQUA + command + ChatColor.RED + " unrecognized. " + ChatColor.RED +
                            "These looks like commands to me: \n" + ChatColor.AQUA + "version, other, relocate, tp, swap, freeze, distance, serverinfo, spawn," +
                            "\n stalk, clear, blind, portals, lockdown, players, world, home");
                }
            } else {
                sender.sendMessage(XYZ.xyzaPrefix + ChatColor.RED + "Sorry, you don't have permission. You'll need " + ChatColor.GREEN + "xyz.admin");
            }
        return false;
        }

}

