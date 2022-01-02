package de.jomlua.commands;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.PrivatPermissions;
import de.jomlua.utils.modules.Teleport;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Warp implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        String prefix = ChatOutput.PREFIX.getText();

        if (!(player.hasPermission(PrivatPermissions.WARP.getText()))){
            player.sendMessage(prefix + ChatOutput.NO_PERMISSIONS.getText());
            return true;
        }
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatOutput.NO_PLAYER.getText());
            return true;
        }

        if (command.getName().equalsIgnoreCase("warp")) {
            if (args.length == 1) {
                if (Teleport.getBooleanWarp(args[0])) {

                   player.teleport(Teleport.TeleportWarp(args[0]));

                    player.sendMessage("§aWarpe zu §c" + args[0]);

                }else{
                    player.sendMessage(ChatOutput.PREFIX.getText() + "§cDieser Warp ist noch nicht gesetzt.");
                }
            }else{
                player.sendMessage(ChatOutput.PREFIX.getText() + "§7Befehle die dir helfen könnten.");
                player.sendMessage("§7- §e/§bwarp §c[name]");
            }
        }
        if (command.getName().equalsIgnoreCase("setwarp")) {
            if (!(player.hasPermission(PrivatPermissions.SETWARP.getText()))){
                player.sendMessage(prefix + ChatOutput.NO_PERMISSIONS.getText());
                return true;
            }
            if (args.length == 1) {
                if (!(Teleport.getBooleanWarp(args[0]))){
                    try {
                        Teleport.SetWarp(player,args[0]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                    return true;
                } else{
                    ChatUtils.msg(player, "Dieser warp &7(&c" + args[0] + "&7) &fexistiert bereits.");
                }

                return true;
            }else{
                player.sendMessage(ChatOutput.PREFIX.getText() + "§7Befehle die dir helfen könnten.");
                player.sendMessage("§7- §e/§bsetwarp §c<name>");
            }
        }
        if (command.getName().equalsIgnoreCase("delwarp")){
            if (!(player.hasPermission(PrivatPermissions.DELWARP.getText()))){
                player.sendMessage(prefix + ChatOutput.NO_PERMISSIONS.getText());
                return true;
            }
            if (args.length == 1){
                if (Teleport.getBooleanWarp(args[0])){
                    try {
                        Teleport.DeleteWarp(player, args[0]);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    ChatUtils.msg(player, "Dieser Warp (" + args[0] + ") existiert nicht");
                }

            }else{
                player.sendMessage(ChatOutput.COMMAND_HELP.getText());
                ChatUtils.msg(player, "&7 - &e/&bdelwarp &c<warpname>");
            }

        }
        if (command.getName().equalsIgnoreCase("warps")){
            File fIle = new File("plugins/jomlua-core/warps.yml");
            YamlConfiguration cnf = YamlConfiguration.loadConfiguration(fIle);
            if (args.length == 0){
                int i = 1;

                Iterator<String> WhileHome = cnf.getConfigurationSection("Warps.").getKeys(true).iterator();

                while (WhileHome.hasNext()){
                    String list = (String) WhileHome.next();
                    if (!list.contains(".")){
                        TextComponent liste = null;
                        TextComponent delete;
                        TextComponent edit;
                        TextComponent teleport;
                        /**
                         * TODO var edit später einbinden, konzept hierfür muss noch überlegt werden.
                         */
                        //edit = ChatUtils.TcCommand("&7[&2Ändern&7] ", "/setwarp " + list, "&3Änder die position von " + list);

                        if (player.hasPermission(PrivatPermissions.DELWARP.getText())){


                            teleport = ChatUtils.TcCommand("&7[&3Teleport&7] ", "/warp " + list, "&3Teleportiere zu " + list);
                            delete = ChatUtils.TcCommand("&7[&cDelete&7]", "/delwarp " + list, "&cLösche diesen Warp.");
                            liste = ChatUtils.TcText("&e" + i++ +". §7" + list + " &e-> ");
                            liste.addExtra(teleport);
                            teleport.addExtra(delete);
                        } else{
                            teleport = ChatUtils.TcCommand("&7[&3Teleport&7] ", "/warp " + list, "&3Teleportiere zu " + list);
                            liste = ChatUtils.TcText("&e" + i++ +". §7" + list + " &e-> ");
                            liste.addExtra(teleport);

                        }





                        player.spigot().sendMessage(liste);
                    }
                }


            }else{
                ChatUtils.msg(player, ChatOutput.COMMAND_HELP.getText());
                ChatUtils.msg(player, "&7 - &e/&bwarps");
            }
        }

        return true;
    }

    @Nullable
    @Override
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        String input = args[0].toLowerCase();

        List<String> completion = null;

        for (String stra: Teleport.getWarp()){
            if (stra.startsWith(input)){
                if (completion == null){
                    completion = new ArrayList<>();
                }
                completion.add(stra);
            }
        }

        if (completion != null){
            Collections.sort(completion);
        }

        return completion;
    }
}
