package me.gonzyui.gameapi.database;

import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public enum Rank {

    PLAYER(0, "§7§5Admin", "§7Joueur", "§7"),
    VIP(1, "§6§4Admin","§6VIP", "§6VIP "),
    HELPER(2, "§e§3Admin","§eHelper", "§eHelper "),
    MODERATOR(3, "§9§2Admin", "§9Modérateur", "§9Modérateur "),
    ADMIN(10, "§c§1Admin", "§CAdmin","§cAdmin ");

    private int power;
    private String name, orderRank, displayName;
    public static Map<Integer, Rank> ranks = new HashMap<>();
    //public static HashMap<Integer, Rank> grade;

    private Rank(int power, String orderRank, String name, String displayName) {
        this.power = power;
        this.name = name;
        this.orderRank = orderRank;
        this.displayName = displayName;
    }

    static {
        for (Rank rank : Rank.values()) {
            ranks.put(rank.getPower(), rank);
        }
    }

    /* static Rank getPowerRank(int power) {
        return grade.get(power);
    }*/

    public String getName() {
        return name;
    }

    public static Rank powerToRank(int power) {
        return ranks.get(power);
    }

    public int getPower() {
        return power;
    }

    public String getOrderRank() {
        return orderRank;
    }

    public String getDisplayName() {
        return displayName;
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
