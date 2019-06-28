package com.ethanpilz.xyz;

import com.ethanpilz.xyz.Listener.PlayerListener;
import com.ethanpilz.xyz.Manager.FreezeManager;
import com.ethanpilz.xyz.Manager.RealmManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import com.ethanpilz.xyz.MetricsLite;
import java.io.IOException;
import java.util.logging.Level;

import static com.ethanpilz.xyz.AdminCommand.xyzVersion;

public class XYZ extends JavaPlugin {

    private FreezeManager freezeManager;
    private static final String xyzprefix = ChatColor.GOLD + "[XYZ] ";
    private static final String SpigotVersion = "1.14.2";
    @Override
    public void onEnable(){


        //Get logger
        if (Bukkit.getVersion().contains("1.14")) {
            Bukkit.getLogger().log(Level.INFO, xyzprefix + ChatColor.GRAY + "Your version " + ChatColor.AQUA + Bukkit.getVersion() + " is supported!");
        } else {
            Bukkit.getLogger().log(Level.INFO, xyzprefix + ChatColor.RED +
                    "Your version " + ChatColor.AQUA + Bukkit.getVersion() + ChatColor.RED + " isn't optimal.");
            Bukkit.getLogger().log(Level.INFO, xyzprefix + ChatColor.GRAY +
                    "Version " + ChatColor.LIGHT_PURPLE + xyzVersion + ChatColor.RED + " is made for Spigot " + ChatColor.GREEN + SpigotVersion);
        }
        //Managers
        this.freezeManager = new FreezeManager(this);

        //Listeners
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

        getCommand("xyz").setExecutor(new UserCommand());
        getCommand("xyza").setExecutor(new AdminCommand(this));

        try {
            MetricsLite metrics = new MetricsLite(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
        }

    }

    @Override
    public void onDisable(){

        Bukkit.getLogger().log(Level.INFO, ChatColor.GREEN + "XYZ has been disabled.");

    }
    
    public FreezeManager getFreezeManager() {
        return this.freezeManager;
    }

    public RealmManager getRealmManager() {
        return this.realmManager;
    }
}
