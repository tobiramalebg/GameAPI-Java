package me.gonzyui.gameapi.listeners.player;

import me.gonzyui.gameapi.GameAPI;
import me.gonzyui.gameapi.database.PlayerInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        GameAPI.INSTANCE.database.createAccount(player.getUniqueId());

        new PlayerInfo(player);
    }
}
