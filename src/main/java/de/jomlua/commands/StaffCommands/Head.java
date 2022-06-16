package de.jomlua.commands.StaffCommands;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.PrivatPermissions;
import de.jomlua.utils.modules.ItemModule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Head implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if (!(player.hasPermission(PrivatPermissions.HEAD.getText()))) {
            ChatUtils.SendRgbMsg(player, ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            return true;
        }
        if (!(sender instanceof Player)) {
            ChatUtils.SendRgbMsg(player, ChatOutput.PREFIX.getText() + ChatOutput.NO_PLAYER.getText());
            return true;
        }

        if (command.getName().equalsIgnoreCase("head")){
            if (args.length == 1){

                player.getInventory().addItem(ItemModule.getHead(args[0]));
                ChatUtils.msg(player,"Du hast ein Kopf non den Spieler &c" + args[0] + " &fbekommen.");
            }else{
                player.sendMessage(ChatOutput.PREFIX.getText() + "Probiere doch mit /head <Spielername>");
            }
        }
        return true;
    }
}
