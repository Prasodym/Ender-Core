package de.jomlua.commands;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.PrivatPermissions;
import de.jomlua.utils.modules.Teleport;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        String prefix = ChatOutput.PREFIX.getText();
        if (!(player.hasPermission(PrivatPermissions.SPAWN.getText()))){
            player.sendMessage(prefix + ChatOutput.NO_PERMISSIONS.getText());
            return true;
        }
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatOutput.NO_PLAYER.getText());
            return true;
        }
        if (args.length == 0){
            player.teleport(Teleport.TeleportConfig("Spawn"));
        }
        return true;
    }
}
