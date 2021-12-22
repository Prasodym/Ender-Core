package de.jomlua.commands;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.CommandHomeUtil;
import de.jomlua.utils.PrivatPermissions;
import de.jomlua.utils.config.CoreConfig;
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
            return false;
        }
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatOutput.NO_PLAYER.getText());
            return false;
        }

        if (cmd.getName().equalsIgnoreCase("home")){
            if (args.length == 0){
                if (CoreConfig.getBoolean(player, "homes.home")){
                    CommandHomeUtil.TeleportHome(player,"home");
                    player.sendMessage(prefix + ChatOutput.COMMAND_HOME_TELEPORT.getText());

                    TextComponent msg = new TextComponent();
                    msg.setText("Teleport zum Home §a->");

                    TextComponent msga = new TextComponent();
                    msga.setText(" §c[Teleport]");
                    msga.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/home"));
                    msga.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("[Teleport]").create()));
                    msg.addExtra(msga);
                    player.spigot().sendMessage(msg);
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

        if (cmd.getName().equalsIgnoreCase("homes")){
            if (args.length == 0){
                File fIle = new File("plugins/jomlua-core/users", player.getUniqueId() + ".yml");
                YamlConfiguration cnf = YamlConfiguration.loadConfiguration(fIle);
                int number = CoreConfig.getintConfig("homecount");
                int i = 1;
                if (player.hasPermission(PrivatPermissions.SETHOMEMULTI.getText())){
                    player.sendMessage(ChatOutput.PREFIX.getText() + "§aAlle gesetzte Homes §7(§2" + CommandHomeUtil.getHomePoints(player).size() + " §avon§c Infiniti§7)§a.");
                } else{
                    if (CommandHomeUtil.getHomePoints(player).size() == number){
                        player.sendMessage(ChatOutput.PREFIX.getText() + "§aAlle gesetzte Homes §7(§c" + CommandHomeUtil.getHomePoints(player).size() + " §avon§c "+ number +"§7)§a.");
                    }else {
                        player.sendMessage(ChatOutput.PREFIX.getText() + "§aAlle gesetzte Homes §7(§2" + CommandHomeUtil.getHomePoints(player).size() + " §avon§c "+ number +"§7)§a.");
                    }
                }

                player.sendMessage(prefix + "§aAlle verf");
                Iterator<String> WhileHome = cnf.getConfigurationSection("homes.").getKeys(true).iterator();

                while (WhileHome.hasNext()){
                    String list = (String) WhileHome.next();
                    if (!list.contains(".")){
                       TextComponent liste;
                       TextComponent delete;
                       TextComponent teleport;

                       teleport = ChatUtils.TcCommand("&7[&3TP&7] ", "/home " + list, "&3Teleportiere zu " + list);
                       delete = ChatUtils.TcCommand("&7[&cDelete&7]", "/delhome " + list, "&cLösche diesen Home.");
                       liste = ChatUtils.TcText("&e" + i++ +". §7" + list + " &e-> ");
                       liste.addExtra(teleport);
                       teleport.addExtra(delete);

                       player.spigot().sendMessage(liste);
                    }
                }

            }else{
                player.sendMessage(prefix + ChatOutput.COMMAND_HELP.getText());
                player.sendMessage("§e/§chomes");
            }
        }

        return false;
    }
}
