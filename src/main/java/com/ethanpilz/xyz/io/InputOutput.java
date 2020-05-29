package com.ethanpilz.xyz.io;

import com.ethanpilz.xyz.XYZ;
import com.ethanpilz.xyz.components.Home;
import com.ethanpilz.xyz.exception.SaveToDatabaseException;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.sql.*;
import java.util.logging.Level;

public class InputOutput {

    public static YamlConfiguration global;
    private static Connection connection;

    public InputOutput() {
        if (!XYZ.instance.getDataFolder().exists()) {
            try {
                (XYZ.instance.getDataFolder()).mkdir();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        global = new YamlConfiguration();
    }

    private static Connection createConnection() {

        try {
            Class.forName("org.sqlite.JDBC");
            Connection ret = DriverManager.getConnection("jdbc:sqlite:" +  new File(XYZ.instance.getDataFolder().getPath(), "db.sqlite").getPath());
            ret.setAutoCommit(false);
            return ret;
        }
        catch (ClassNotFoundException e) {
            XYZ.log.log(Level.SEVERE, XYZ.consolePrefix + "ClassNotFound while attempting to create database connection");
            e.printStackTrace();
            return null;
        }
        catch (SQLException e) {
            XYZ.log.log(Level.SEVERE, XYZ.consolePrefix + "Encountered SQL exception while attempting to create database connection");
            e.printStackTrace();
            return null;
        }
    }

    public static synchronized Connection getConnection() {
        if (connection == null) connection = createConnection();

        try {
            if(connection.isClosed()) connection = createConnection();
        }

        catch (SQLException ex) {
            ex.printStackTrace();
        }

        return connection;
    }

    public static synchronized void freeConnection() {
        Connection conn = getConnection();
        if(conn != null) {
            try {
                conn.close();
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void prepareDB() {
        Connection conn = getConnection();
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate("CREATE TABLE IF NOT EXISTS \"xyz_player_homes\" (\"UUID\" VARCHAR PRIMARY KEY NOT NULL, \"X\" DOUBLE, \"Y\" DOUBLE, \"Z\" DOUBLE, \"Pitch\" FLOAT, \"Yaw\" FLOAT, \"World\" VARCHAR)");
            conn.commit();
            st.close();

        }
        catch (SQLException e) {
            XYZ.log.log(Level.SEVERE, XYZ.consolePrefix + "Encountered SQL error while attempting to prepare database: " + e.getMessage());
            e.printStackTrace();
        }
        catch (Exception e) {
            XYZ.log.log(Level.SEVERE, XYZ.consolePrefix + "Unknown error encountered while attempting to prepare database.");
        }
    }

    /**
     * Updates DB to latest version
     */
    public void updateDB() {
        //performUpdate("SELECT SecondsWaitingRoom FROM smuhc_arenas", "ALTER TABLE smuhc_arenas ADD SecondsWaitingRoom DOUBLE DEFAULT 60");
        //performUpdate("SELECT XP FROM smuhc_players", "ALTER TABLE smuhc_players ADD XP INTEGER DEFAULT 0");
    }

    /**
     * Performs update to database if check query fails
     *
     * @param check
     * @param sqlite
     */

    private void performUpdate(String check, String sqlite) {
        try {
            Statement statement = getConnection().createStatement();
            statement.executeQuery(check);
            statement.close();
        } catch (SQLException ex) {

            try {
                String[] query;

                query = sqlite.split(";");
                Connection conn = getConnection();
                Statement st = conn.createStatement();
                for (String q : query)
                    st.executeUpdate(q);
                conn.commit();
                st.close();
                XYZ.log.log(Level.INFO, XYZ.consolePrefix + "Database updated to new version!");

            } catch (SQLException e) {

                XYZ.log.log(Level.SEVERE, XYZ.consolePrefix + "Error while attempting to update database to new version!");
                e.printStackTrace();
            }
        }
    }

    public void storePlayerCheckpoint(Home home) throws SaveToDatabaseException {
        try {
            String sql;
            Connection conn = InputOutput.getConnection();

            sql = "INSERT INTO xyz_player_homes (`UUID`, `X`, `Y`, `Z`, `Pitch`, `Yaw`, `World`) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, home.getPlayer().getUniqueId().toString());
            preparedStatement.setDouble(2, home.getLocation().getX());
            preparedStatement.setDouble(3, home.getLocation().getY());
            preparedStatement.setDouble(4, home.getLocation().getZ());
            preparedStatement.setFloat(5, home.getLocation().getPitch());
            preparedStatement.setFloat(6, home.getLocation().getYaw());
            preparedStatement.setString(7, home.getLocation().getWorld().getName());

            preparedStatement.executeUpdate();
            conn.commit();
            preparedStatement.close();
        } catch (SQLException e) {
            XYZ.log.log(Level.WARNING, XYZ.consolePrefix + "Encountered an error while attempting to store a home to the database: " + e.getMessage());
            throw new SaveToDatabaseException();
        }
    }

    public Home getPlayerHome(Player player) throws SQLException {

        Connection conn = InputOutput.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT `X`, `Y`, `Z`, `Pitch`, `Yaw`, `World` FROM `xyz_player_homes` WHERE `UUID` = ?");
        ps.setString(1, player.getUniqueId().toString());
        ResultSet result = ps.executeQuery();

        Location loc = null;

        while (result.next()) {
            loc = new Location(Bukkit.getWorld(result.getString("World")), result.getDouble("X"), result.getDouble("Y"), result.getDouble("Z"), result.getFloat("Yaw"), result.getFloat("Pitch"));

        }
        ps.close();
        if (loc == null)
            return null;
        else
            return (new Home(player, loc));

    }

    public void deletePlayerHome(Player player) throws SQLException {

        Connection conn = InputOutput.getConnection();
        PreparedStatement ps = conn.prepareStatement("DELETE FROM `xyz_player_homes` WHERE `UUID` = ?");
        ps.setString(1, player.getUniqueId().toString());
        ps.executeUpdate();
        ps.close();

    }
}
