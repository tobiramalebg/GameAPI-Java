package me.gonzyui.gameapi.database;

import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.UUID;

public enum Rank {

    PLAYER(0, "§7Joueur"),
    VIP(1, "§6VIP"),
    HELPER(2, "§eHelper"),
    MODERATOR(3, "§9Modérateur"),
    ADMIN(10, "§CAdministrateur");

    private int power;
    private String name;
    public static HashMap<Integer, Rank> grade;

    private Rank(int power, String name) {
        this.power = power;
        this.name = name;
    }

    public static Rank getPowerRank(int power) {
        return grade.get(power);
    }

    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public static int getPlayerRank(UUID uuid) {
        try {
            PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("SELECT grade FROM players WHERE uuid_player = ?");
            preparedStatement.setString(1, uuid.toString());
            int power = 0;
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                power = rs.getInt("grade");
            }
            preparedStatement.close();

            return power;

        } catch (SQLException e) {
            System.out.println("[Rank] There was an error with " + Bukkit.getPlayer(uuid).getName() + " rank, please try to reconnect to database.");
        }
        return 0; // player rank (default)
    }
}
