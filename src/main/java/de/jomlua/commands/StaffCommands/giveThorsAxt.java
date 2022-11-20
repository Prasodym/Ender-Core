package de.jomlua.commands.StaffCommands;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.PrivatPermissions;
import de.jomlua.utils.modules.ItemBuilder;
import de.jomlua.utils.modules.ItemModule;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class giveThorsAxt implements CommandExecutor {
    // Uper 1.19.2 Thor werzeug miscellaneous
    String Permissions = "jomlua.admin.tools";
    String CommandName = "thor";


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] arg) {
        Player player = (Player) sender;

        if (!(player.hasPermission(PrivatPermissions.ITEMUNNAME.getText()))) {
            ChatUtils.SendRgbMsg(player, ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            return true;
        }
        if (!(sender instanceof Player)) {
            ChatUtils.SendRgbMsg(player, ChatOutput.PREFIX.getText() + ChatOutput.NO_PLAYER.getText());
            return true;
        }
        if (arg.length == 0){
            player.getInventory().addItem(ItemModule.getThor());
            player.sendMessage(ChatOutput.PREFIX.getText() + "Gebe Thors Axt!");
            return true;
        }else{
            player.sendMessage(ChatOutput.PREFIX.getText() + "§6Benutze /thor");
            // TODO Help messages
        }
        return false;
    }
}
