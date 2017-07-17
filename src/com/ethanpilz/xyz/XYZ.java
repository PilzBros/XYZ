package com.ethanpilz.xyz;

import com.ethanpilz.xyz.Listener.PlayerListener;
import com.ethanpilz.xyz.Manager.FreezeManager;
import com.ethanpilz.xyz.UserCommand;
import com.ethanpilz.xyz.AdminCommand;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import com.ethanpilz.xyz.MetricsLite;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XYZ extends JavaPlugin {

    public static final Logger log = Logger.getLogger("Minecraft");
    public static FreezeManager freezeManager;

    @Override
    public void onEnable(){


        //Get logger
        log.log(Level.INFO, ChatColor.GREEN + "XYZ has been enabled.");

        //Managers
         freezeManager = new FreezeManager();

        //Listeners
        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        getCommand("xyz").setExecutor(new UserCommand());
        getCommand("xyza").setExecutor(new AdminCommand());
        getCommand("xyzadmin").setExecutor(new AdminCommand());

        try {
            MetricsLite metrics = new MetricsLite(this);
            metrics.start();
        } catch (IOException e) {
            // Failed to submit the stats :-(
        }

    }

    @Override
    public void onDisable(){

        log.log(Level.INFO, ChatColor.GREEN + "XYZ has been disabled.");

    }

}
