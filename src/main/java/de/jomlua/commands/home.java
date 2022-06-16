package de.jomlua.commands;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.CommandHomeUtil;
import de.jomlua.utils.PrivatPermissions;
import de.jomlua.utils.config.CoreConfig;
import de.jomlua.utils.modules.Chatinterfaces;
import de.jomlua.utils.modules.Teleport;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;


public class home implements CommandExecutor {




    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String lable, @NotNull String[] args) {
        Player player = (Player) sender;
        String prefix = ChatOutput.PREFIX.getText();
        HashMap<String,String> replace = new HashMap<>();



        if (!(player.hasPermission(PrivatPermissions.HOME.getText()))){
            player.sendMessage(ChatOutput.NO_PERMISSIONS.getText());
            return true;
        }
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatOutput.NO_PLAYER.getText());
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("home")){
            if (args.length == 0){
                if (CoreConfig.getBoolean(player, "homes.home")){
                    CommandHomeUtil.TeleportHome(player,"home");
                    player.sendMessage(prefix + ChatOutput.COMMAND_HOME_TELEPORT.getText());

                }else{

                    player.sendMessage(prefix + "Dein Home wurde noch nicht gesetzt");
                }

            }else{
                if (args.length == 1){
                    //teleport to
                    if (CoreConfig.getBoolean(player, "homes."+args[0])){
                        CommandHomeUtil.TeleportHome(player, args[0]);
                        player.sendMessage(prefix + ChatOutput.COMMAND_HOME_TELEPORT.getText());
                    }else{
                        replace.put("%home%", args[0]);
                        player.sendMessage(prefix + ChatOutput.COMANND_HOME_DEFAULTEXIST.getText(replace));
                    }
                }
            }
        }

        if (cmd.getName().equalsIgnoreCase("delhome")){
         if (args.length == 1){
             try {
                 Teleport.DeleteHome(player, args[0]);
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }else{
             player.sendMessage(ChatOutput.COMMAND_HELP.getText());
             player.sendMessage("");
             ChatUtils.msg(player, "&7 - &e/&bdelhome &c<home>");
         }
        }

        if (cmd.getName().equalsIgnoreCase("homes")){
            if (args.length == 0){
                Chatinterfaces.ListetHomeInterface(player);

            }else{
                player.sendMessage(prefix + ChatOutput.COMMAND_HELP.getText());
                player.sendMessage("§e/§chomes");
            }
        }

        return false;
    }
}
