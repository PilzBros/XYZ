package com.ethanpilz.xyz;

import com.ethanpilz.xyz.UserCommand;
import com.ethanpilz.xyz.AdminCommand;
import org.bukkit.plugin.java.JavaPlugin;

public class XYZ extends JavaPlugin {

    @Override
    public void onEnable(){

        getCommand("xyz").setExecutor(new UserCommand());
      //  getCommand("alc").setExecutor(new UserCommand());
        getCommand("xyzadmin").setExecutor(new AdminCommand());
       // getCommand("alca").setExecutor(new AdminCommand());

    }

    @Override
    public void onDisable(){

    }

}
