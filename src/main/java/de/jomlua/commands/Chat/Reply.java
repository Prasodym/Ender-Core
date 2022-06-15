package de.jomlua.commands.Chat;

import de.jomlua.core;
import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.PrivatPermissions;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Reply implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String s, @NotNull String[] args) {


        if (!(sender.hasPermission(PrivatPermissions.MSG.getText()))){
            sender.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            return true;
        }
        if (args.length >= 1){
            String arguments = "";
            for (int i = 0; i < args.length; i++) {
                arguments += args[i] + " ";
            }


        }

        Player player = (Player) sender;
        if (sender instanceof Player){
            if (cmd.getName().equalsIgnoreCase("m")){
                World world = player.getWorld();
                String group = core.chat.getPrimaryGroup(player);
                String prefix = core.chat.getGroupPrefix(world,group);
                if (args.length == 0){
                    player.sendMessage(ChatOutput.PREFIX.getText() + "Du musst ein spieler wählen.");
                }else{
                    if (args.length == 2){
                        Player target = Bukkit.getPlayer(args[0]);

                        ChatUtils.setReply(player,target);
                        args[0] = "";
                        String msg = "";
                        for (int i = 0; i < args.length; i++){
                            msg += " " + args[i];
                        }
                        /* TODO Prüfe ob target afk ist, wenn ja dann Title senden.*/
                        target.sendTitle(ChatUtils.setHexText("&e&lAchtung"),ChatUtils.setHexText("&5Du hast eine neue nachricht von " + player.getDisplayName()),1,20,1);
                        target.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a" + prefix + player.getName() + "&7 -> &cDu:&b" + msg));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cDu &7-> &a" + prefix + target.getName() + "&8:&b" + msg));
                    }
                }
            }
            if (cmd.getName().equalsIgnoreCase("r")){
                World world = player.getWorld();
                String group = core.chat.getPrimaryGroup(player);
                String prefix = core.chat.getGroupPrefix(world,group);
                if (args.length == 0){
                    player.sendMessage("§e Du hast keine nachricht erhalten.");
                }else{
                    if (ChatUtils.getReply(player) == null){
                        player.sendMessage("Hast keine msg");
                        return true;
                    }
                    if (args.length == 1){
                        Player reciver = ChatUtils.getReply(player);
                        String mass = "";
                        for (int i = 0; i < args.length; i++){
                            mass += " " + args[i];
                        }

                        reciver.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a" + prefix + player.getName() + "&7 -> &cdu:&b" + mass));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cdu &7-> &a" + prefix + reciver.getName() + "&8:&b" + mass));
                    }
                }
            }
        }else{
            sender.sendMessage(ChatOutput.NO_PLAYER.getText());
        }

        return false;
    }


}
