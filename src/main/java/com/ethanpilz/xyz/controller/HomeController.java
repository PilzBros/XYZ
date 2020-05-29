package com.ethanpilz.xyz.controller;

import com.ethanpilz.xyz.XYZ;
import com.ethanpilz.xyz.components.Home;
import com.ethanpilz.xyz.exception.SaveToDatabaseException;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Optional;

public class HomeController {
    private HashMap<String, Location> homes;

    public HomeController(){
        homes = new HashMap<>();

    }

    public void saveHome(Home home) throws SQLException, SaveToDatabaseException {
        homes.put(home.getUUID(), home.getLocation());

        if (XYZ.inputOutput.getPlayerHome(home.getPlayer()) != null) {
            XYZ.inputOutput.deletePlayerHome(home.getPlayer());
        }

        XYZ.inputOutput.storePlayerCheckpoint(home);
        home.getPlayer().sendMessage(XYZ.xyzPrefix + ChatColor.YELLOW + "Home set.");
        home.getPlayer().playSound(home.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 50);
    }

    public Optional<Location> getHome(Player player) throws SQLException {

        if (homes.containsKey(player.getUniqueId().toString())) {

            player.playSound(player.getLocation(), Sound.ENTITY_PARROT_IMITATE_ENDERMITE, 1, 1);
            return Optional.of(homes.get(player.getUniqueId().toString()));


        } else if (XYZ.inputOutput.getPlayerHome(player) != null) {
            Home home = XYZ.inputOutput.getPlayerHome(player);
            player.playSound(player.getLocation(), Sound.ENTITY_PARROT_IMITATE_ENDERMITE, 1, 1);
            return Optional.of(home.getLocation());
        }
        else {
            return Optional.empty();
        }
    }
}
