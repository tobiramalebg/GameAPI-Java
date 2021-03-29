package me.gonzyui.gameapi;

import me.gonzyui.gameapi.commands.CoinsCommand;
import me.gonzyui.gameapi.commands.RankCommand;
import me.gonzyui.gameapi.database.DatabaseManager;
import me.gonzyui.gameapi.listeners.player.PlayerJoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class GameAPI extends JavaPlugin {

    public static GameAPI INSTANCE;
    public DatabaseManager database;

    @Override
    public void onEnable() {
        INSTANCE = this;
        database = new DatabaseManager("jdbc:mysql://", "localhost", "gameapi", "root", "");
        database.connexion();

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoinListener(), this);

        this.getCommand("coins").setExecutor(new CoinsCommand());
        this.getCommand("rank").setExecutor(new RankCommand());
    }

    @Override
    public void onDisable() {
        database.deconnexion();
    }
}
