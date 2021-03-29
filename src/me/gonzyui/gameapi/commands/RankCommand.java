package me.gonzyui.gameapi.commands;

import me.gonzyui.gameapi.database.PlayerInfo;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)) {
            return true;
        }
        Player player = (Player) sender;

        if(args.length == 0) {
            player.sendMessage("/rank <player> <rank>");
            return true;
        }

        if (args.length >= 1) {
            Player target = Bukkit.getPlayer(args[0]);
            int power = Integer.valueOf(args[1]);
            PlayerInfo playerInfo = PlayerInfo.getInfoPlayer(target);
            playerInfo.setRank(power);
            player.sendMessage("§cUn rôle a été ajouter à §b " + target.getName() + " §c!");
        }
        return true;
    }

}
