package com.ethanpilz.xyz;

import com.ethanpilz.xyz.Command.AdminCommand;
import com.ethanpilz.xyz.Command.UserCommand;
import com.ethanpilz.xyz.Listener.PlayerListener;
import com.ethanpilz.xyz.Manager.FreezeManager;
import com.ethanpilz.xyz.Manager.BlindManager;
import com.ethanpilz.xyz.Manager.LockdownManager;
import com.ethanpilz.xyz.Manager.RealmManager;
import com.ethanpilz.xyz.Statistic.MetricsLite;
import com.ethanpilz.xyz.Statistic.UpdateChecker;
import com.ethanpilz.xyz.TabCompletors.XYZATab;
import com.ethanpilz.xyz.TabCompletors.XYZTab;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XYZ extends JavaPlugin {

    private FreezeManager freezeManager;
    private BlindManager blindManager;
    private RealmManager realmManager;
    private LockdownManager lockdownManager;
    public static final String xyzaPrefix = ChatColor.GOLD + "[XYZ] ";
    public static final String xyzPrefix = ChatColor.GOLD + "[XYZ] ";
    public static final String infoPrefix = ChatColor.RED + "Info" + ChatColor.DARK_GRAY + " >> ";
    private static final String consolePrefix = "[XYZ] ";
    public static final String pluginVersion = "1.2.1";      // Change with each build... :)
    public static final String spigotVersion = "1.15.2";

    @Override
    public void onEnable(){


        // Get logger
        Logger logger = this.getLogger();

        new UpdateChecker(this, 30713).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                logger.info("XYZ up to date");
            } else {
                logger.info("There is a new update available for XYZ!");
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

        // Listeners
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

        getCommand("xyz").setExecutor(new UserCommand());
        getCommand("xyza").setExecutor(new AdminCommand(this));
        getCommand("xyzadmin").setExecutor(new AdminCommand(this));

        // Tab Completors
        getCommand("xyz").setTabCompleter(new XYZTab());
        getCommand("xyza").setTabCompleter(new XYZATab());
        getCommand("xyzadmin").setTabCompleter(new XYZATab());

        try {
            MetricsLite metrics = new MetricsLite(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-( omegalul
        }

    }

    @Override
    public void onDisable(){
        Bukkit.getLogger().log(Level.INFO, "XYZ has been disabled.");
    }
    public FreezeManager getFreezeManager() { return this.freezeManager; }
    public BlindManager getBlindManager() { return this.blindManager; }
    public RealmManager getRealmManager() { return this.realmManager; }
    public LockdownManager getLockdownManager() { return this.lockdownManager; }
}
