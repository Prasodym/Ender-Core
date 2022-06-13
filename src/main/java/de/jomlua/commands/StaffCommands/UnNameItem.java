package de.jomlua.commands.StaffCommands;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.PrivatPermissions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class UnNameItem implements CommandExecutor {
    String Permissions = "jomlua.admin.unnameitem";
    String CommandName = "iname";
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if (!(player.hasPermission(PrivatPermissions.ITEMUNNAME.getText()))){
            ChatUtils.SendRgbMsg(player, ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            return true;
        }
        if (!(sender instanceof Player)){
            ChatUtils.SendRgbMsg(player, ChatOutput.PREFIX.getText() + ChatOutput.NO_PLAYER.getText());
            return true;
        }

        if (command.getName().equalsIgnoreCase(CommandName)){
            if (args.length >= 1){
                String arguments = "";
                for (int i = 0; i < args.length; i++) {
                    arguments += args[i] + " ";
                }
                String item = player.getInventory().getItemInMainHand().getType().toString();

                ChatUtils.RenameItemInHand(player,arguments);
                ChatUtils.SendRgbMsg(player, "&fDas Item &c" + item + " &fwurde umgenannt zu " + arguments+ "&f.");
            }else{
                ChatUtils.SendRgbMsg(player, ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            }
        }
        return true;
    }
}
