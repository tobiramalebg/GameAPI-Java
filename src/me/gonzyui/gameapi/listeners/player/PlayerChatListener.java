package me.gonzyui.gameapi.listeners.player;

import me.gonzyui.gameapi.database.PlayerInfo;
import me.gonzyui.gameapi.database.Rank;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChatListener implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();

        PlayerInfo playerInfo = new PlayerInfo(player);

        event.setCancelled(true);

        if(event.getMessage() != null) {
            event.setFormat(Rank.powerToRank(playerInfo.getRank()).getDisplayName() + player.getName() + "§7:§f " + event.getMessage());

            for(Player players : Bukkit.getOnlinePlayers()) {
                players.sendMessage(event.getFormat());
            }
        }

    }
}
