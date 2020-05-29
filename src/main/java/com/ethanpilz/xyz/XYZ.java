package com.ethanpilz.xyz;

import com.ethanpilz.xyz.command.AdminCommand;
import com.ethanpilz.xyz.command.UserCommand;
import com.ethanpilz.xyz.listener.PlayerListener;
import com.ethanpilz.xyz.manager.FreezeManager;
import com.ethanpilz.xyz.manager.BlindManager;
import com.ethanpilz.xyz.manager.LockdownManager;
import com.ethanpilz.xyz.manager.RealmManager;
import com.ethanpilz.xyz.statistics.UpdateChecker;
import com.ethanpilz.xyz.tabcompletors.XYZATab;
import com.ethanpilz.xyz.tabcompletors.XYZTab;
import com.ethanpilz.xyz.controller.HomeController;
import com.ethanpilz.xyz.io.InputOutput;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public class XYZ extends JavaPlugin {

    // Global Managers
    public static InputOutput inputOutput;
    private FreezeManager freezeManager;
    private BlindManager blindManager;
    private RealmManager realmManager;
    private LockdownManager lockdownManager;
    public static XYZ instance;

    // Controllers
    public static HomeController homeController;

    // Prefixes/Strings
    public static final String xyzaPrefix = ChatColor.GOLD + "[XYZ] ";
    public static final String xyzPrefix = ChatColor.GOLD + "[XYZ] ";
    public static final String infoPrefix = ChatColor.RED + "Info" + ChatColor.DARK_GRAY + " >> ";
    public static final String redDash = ChatColor.RED + " - " + ChatColor.RESET;
    public static final String consolePrefix = "[XYZ] ";

    // Versions and dates
    public static final String pluginVersion = "1.2.3";      // Change with each build
    public static final String spigotVersion = "1.15.2";
    public static final String buildDate = "May 29, 2020";   // Change with each build

    // Logger
    public static final Logger log = Logger.getLogger("Minecraft");



    @Override
    public void onEnable(){

        //Instance
        XYZ.instance = this;

        // Get logger
        Logger logger = this.getLogger();

        new UpdateChecker(this, 30713).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                logger.info("XYZ is up to date.");
            } else {
                logger.info("There is a new update available for XYZ! Link: " + "https://www.spigotmc.org/resources/xyz.30713/");
            }
        });

        if (Bukkit.getVersion().contains("1.7") || Bukkit.getVersion().contains("1.8")) {
            Bukkit.getLogger().log(Level.INFO, consolePrefix + "This version of spigot may not work properly with XYZ " + pluginVersion);
        }

        // Managers
        this.freezeManager = new FreezeManager(this);
        this.blindManager = new BlindManager(this);
        this.realmManager = new RealmManager(this);
        this.lockdownManager = new LockdownManager(this);

        // Controllers
        homeController = new HomeController();

        // Database
        inputOutput = new InputOutput();
        inputOutput.prepareDB();

        // Listeners
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
        //getServer().getPluginManager().registerEvent(new InventoryListener(this), this);

        getCommand("xyz").setExecutor(new UserCommand());
        getCommand("xyza").setExecutor(new AdminCommand(this));
        getCommand("xyzadmin").setExecutor(new AdminCommand(this));

        // Tab Completors
        getCommand("xyz").setTabCompleter(new XYZTab());
        getCommand("xyza").setTabCompleter(new XYZATab());
        getCommand("xyzadmin").setTabCompleter(new XYZATab());


    }

    @Override
    public void onDisable(){
        Bukkit.getLogger().log(Level.INFO, "XYZ has been disabled.");
    }

    public FreezeManager getFreezeManager() { return this.freezeManager; }
    public BlindManager getBlindManager() { return this.blindManager; }
    public RealmManager getRealmManager() { return this.realmManager; }
    public LockdownManager getLockdownManager() { return this.lockdownManager; }
   // public PlayerMenu getPlayerMenu() { return this.}
}
