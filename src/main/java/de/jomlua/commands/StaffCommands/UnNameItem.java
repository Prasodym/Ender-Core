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

import java.util.Arrays;

public class UnNameItem implements CommandExecutor {
    String Permissions = "jomlua.admin.unnameitem";
    String CommandName = "rename";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;

        if (!(player.hasPermission(PrivatPermissions.ITEMUNNAME.getText()))) {
            ChatUtils.SendRgbMsg(player, ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            return true;
        }
        if (!(sender instanceof Player)) {
            ChatUtils.SendRgbMsg(player, ChatOutput.PREFIX.getText() + ChatOutput.NO_PLAYER.getText());
            return true;
        }

        if (command.getName().equalsIgnoreCase(CommandName)) {
            if (args.length >= 1) {
                String arguments = " ";
                for (int i = 1; i < args.length; i++) {
                    arguments += args[i] + " ";
                }

                String lore = " ";
                for (int i = 2; i < args.length; i++) {
                    lore += args[i] + " ";
                }
                if (args[0].equalsIgnoreCase("name")) {

                    String item = player.getInventory().getItemInMainHand().getType().toString();

                    ItemModule.setName(player,arguments);
                    ChatUtils.SendRgbMsg(player, "&fDas Item &c" + item + " &fwurde umgenannt zu " + arguments+ "&f.");
                }
                if (args[0].equalsIgnoreCase("lore")) {
//                    int value;
//
//                    try{
//                        value = Integer.valueOf(args[1]);
//                    }catch (NumberFormatException e){
//                        sender.sendMessage(ChatOutput.PREFIX.getText() + "§c<Line> muss eine ganze Zahl sein.");
//                        return true;
//                    }


                    ItemModule.lore(player, lore.split(" "));
                    player.sendMessage("Line: True");

                }
            }
        }
        return true;
    }
}


