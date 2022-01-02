package de.jomlua.commands.TpaCommand;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.modules.TeleportTyp;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tpaacept implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Player target = Bukkit.getPlayer(Tpa.tpa.get(player.getUniqueId()));


        if (args.length==0){
            if (!(sender instanceof Player)){
                sender.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PLAYER.getText());
                return true;
            }
            if (!(player.hasPermission("jomlua.tpa"))){
                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
                return true;
            }
            if (!(Tpa.tpa.containsKey(player.getUniqueId()))){
                // Keine anfrage msg
                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.TPA_DENY_D.getText());
                return true;
            }
            if (target == null){
                //msg Spieler bereits offline
                player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.TPA_DENY_H.getText());
                Tpa.tpa.remove(player.getUniqueId());
                Tpa.tpType.remove(player.getUniqueId());
            }

            TeleportTyp tpType = Tpa.tpType.get(player.getUniqueId());
            if (tpType == TeleportTyp.NORMAL){
                target.teleport(player);
            }else{
                player.teleport(target);
            }
            Tpa.tpa.remove(player.getUniqueId());
            Tpa.tpType.remove(player.getUniqueId());

        }else{
            player.sendMessage("§a- §e/tpaccept");
        }
        return false;
    }
}
