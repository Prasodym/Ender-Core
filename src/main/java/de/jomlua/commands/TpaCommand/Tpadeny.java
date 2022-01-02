package de.jomlua.commands.TpaCommand;

import de.jomlua.utils.ChatOutput;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Tpadeny implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (args.length==0){
            if (player.hasPermission("jomlua.tpa")){
                UUID target = Tpa.tpa.get(player.getUniqueId());

                Player target1 = Bukkit.getPlayer(target);

                HashMap<String, String> replacements = new HashMap<String, String>();
                replacements.put("%target%", player.getDisplayName());
                if (Tpa.tpa.get(player.getUniqueId()) != null){
                    Tpa.tpa.remove(player.getUniqueId());
                    Tpa.tpType.remove(player.getUniqueId());
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.TPA_DENY_A.getText());
                    target1.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.TPA_DENY_B.getText(replacements));

                } else{
                    player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.TPA_DENY_I.getText());
                }



            }

        }else{
            player.sendMessage("§a- §e/tpadeny");
        }
        return false;
    }
}
