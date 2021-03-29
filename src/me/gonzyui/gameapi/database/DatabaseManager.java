package me.gonzyui.gameapi.database;

import org.bukkit.Bukkit;

import java.sql.*;
import java.util.UUID;

public class DatabaseManager {

    private String urlBase;
    private String host;
    private String database;
    private String userName;
    private String password;
    private static Connection connection;

    public DatabaseManager(String urlBase, String host, String database, String userName, String password) {
        this.urlBase = urlBase;
        this.host = host;
        this.database = database;
        this.userName = userName;
        this.password = password;
    }
    public static Connection getConnexion() {
        return connection;
    }

    /**
     * We creating player account on joining server
     */
    public void createAccount(UUID uuid) {
        if(!hasAccount(uuid)) {
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO players (uuid_player, player_name, coins, grade) VALUES (?, ?, ?, ?)");
                preparedStatement.setString(1, uuid.toString());
                preparedStatement.setString(2, Bukkit.getPlayer(uuid).getName());
                preparedStatement.setFloat(3, 100.0F);
                preparedStatement.setInt(4, 0);
                preparedStatement.execute();
                preparedStatement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * If player has account return
     */
    public boolean hasAccount(UUID uuid) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT coins FROM players WHERE uuid_player = ?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();
            float coins = 0;

            while(rs.next()) {
                return true;
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
    /**
     * Here we try to connect to our database
     */
    public void connexion() {
        if(!isOnline()) {
            try {
                connection = DriverManager.getConnection(this.urlBase + this.host + "/" + this.database, this.userName, this.password);
                System.out.println("§aDatabase successfully connected!");
                return;
            } catch  (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Here we disconnect from our database
     */
    public void deconnexion() {
        if(isOnline()) {
            try {
                connection.close();
                System.out.println("§cDatabase successfully disconnected!");
                return;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    /**
     * Here we will verify if our database is online
     */
    public boolean isOnline() {
        try {
            if((connection == null) || (connection.isClosed())) {
                return false;
            }
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
