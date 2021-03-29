package me.gonzyui.gameapi.commands;

import me.gonzyui.gameapi.database.PlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CoinsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        Player player = (Player) sender;
        PlayerInfo playerInfo = new PlayerInfo(player);

        if (args.length == 0) {
            player.sendMessage("§a Vous possèdez actuellement §6" + playerInfo.getCoinsNumber() + " §6coins.");
            return true;
        }

        if(args.length >= 1) {
            if(args[0].equalsIgnoreCase("add")) {
                if(args.length == 3) {
                    Player target = Bukkit.getPlayer(args[2]);
                    if(target != null) {
                        float amount = Float.valueOf(args[1]);
                        PlayerInfo targetInfo = new PlayerInfo(player);
                        targetInfo.addCoins(amount);
                        player.sendMessage("§aVous avez reçu §6" + amount + " §6coins.");
                    }
                }
            } else if (args[0].equalsIgnoreCase("remove")) {
                if(args.length == 3) {
                    Player target = Bukkit.getPlayer(args[2]);
                    if (target != null) {
                        float amount = Float.valueOf(args[1]);
                        PlayerInfo targetInfo = new PlayerInfo(player);
                        targetInfo.removeCoins(amount);
                        player.sendMessage("§6" + amount + " §6coins, §cvous ont été retirés.");
                    }
                }
            }
        }
        return true;
    }
}
