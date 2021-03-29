package me.gonzyui.gameapi.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerData {

    private UUID uuid;

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
    }
    public void setRank(int power) {
        try {
            PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("UPDATE players SET grade = ? WHERE uuid_player = ?");
            preparedStatement.setInt(1, power);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCoins(float amount) {
        try {
            PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("UPDATE players SET coins = coins + ? WHERE uuid_player = ?");
            preparedStatement.setFloat(1, amount);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeCoins(float amount) {
        try {
            PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("UPDATE players SET coins = coins - ? WHERE uuid_player = ?");
            preparedStatement.setFloat(1, amount);
            preparedStatement.setString(2, uuid.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Float getCoins() {
        try {
            PreparedStatement preparedStatement = DatabaseManager.getConnexion().prepareStatement("SELECT coins FROM players WHERE uuid_player = ?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();
            float coins = 0f;

            while(rs.next()) {
                coins = rs.getFloat("coins");
            }
            preparedStatement.close();

            return coins;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0f;

    }
}
