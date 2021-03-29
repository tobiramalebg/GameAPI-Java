package me.gonzyui.gameapi.database;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class PlayerInfo {

    public static Map<Player, PlayerInfo> playerInfo = new HashMap<Player, PlayerInfo>();
    private Player player;
    private PlayerData playerData;

    public PlayerInfo(Player player) {
        this.player = player;
        this.playerData = new PlayerData(player.getUniqueId());
        playerInfo.put(player, this);
    }

    public static PlayerInfo getInfoPlayer(Player player) {
        return playerInfo.get(player);
    }
    public Player getPlayer() {
        return player;
    }
    public PlayerData getPlayerData() {
        return playerData;
    }
    public Float getCoinsNumber() {
        return playerData.getCoins();
    }
    public void addCoins(float amount) {
        playerData.addCoins(amount);
    }
    public void removeCoins(float amount) {
        playerData.removeCoins(amount);
    }
    public int getRank() {
        return Rank.getPlayerRank(player.getUniqueId());
    }
    public void setRank(int power) {
        playerData.setRank(power);
    }
}
