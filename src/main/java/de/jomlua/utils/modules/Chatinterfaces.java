package de.jomlua.utils.modules;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.CommandHomeUtil;
import de.jomlua.utils.PrivatPermissions;
import de.jomlua.utils.config.CoreConfig;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.Iterator;

public class Chatinterfaces {

    public Chatinterfaces() {
    }
    public static void ListetHomeInterface(Player player){
        String prefix = ChatOutput.PREFIX.getText();
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


        Iterator<String> WhileHome = cnf.getConfigurationSection("homes.").getKeys(true).iterator();

        while (WhileHome.hasNext()){
            String list = (String) WhileHome.next();
            if (!list.contains(".")){
                TextComponent liste;
                TextComponent delete;
                TextComponent teleport;

                teleport = ChatUtils.TcCommand("&7[&3TP&7] ", "/home " + list, "&3Teleportiere zum " + list);
                delete = ChatUtils.TcCommand("&7[&cDelete&7]", "/delhome " + list, "&cLösche diesen Home.");
                liste = ChatUtils.TcText("&e" + i++ +". §7" + list + " &e-> ");
                liste.addExtra(teleport);
                teleport.addExtra(delete);

                player.spigot().sendMessage(liste);
            }
        }
    }

    public static void ListetHomeTargetInterface(Player player, OfflinePlayer target){
        String prefix = ChatOutput.PREFIX.getText();
        File fIle = new File("plugins/jomlua-core/users", target.getUniqueId() + ".yml");
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

        if (cnf.isSet("homes")){
            Iterator<String> WhileHome = cnf.getConfigurationSection("homes.").getKeys(true).iterator();

            while (WhileHome.hasNext()){
                String list = (String) WhileHome.next();
                if (!list.contains(".")){
                    TextComponent liste;
                    TextComponent delete;
                    TextComponent teleport;

                    teleport = ChatUtils.TcCommand("&7[&3TP&7] ", "/atphome " + target.getName() + " " + list, "&3Teleportiere zum " + list);
                    delete = ChatUtils.TcCommand("&7[&cDelete&7]Soon", "/delhome " + list, "&cLösche diesen Home.");
                    liste = ChatUtils.TcText("&e" + i++ +". §7" + list + " &e-> ");
                    liste.addExtra(teleport);
                    teleport.addExtra(delete);

                    player.spigot().sendMessage(liste);
                }
            }
        }else{
            player.sendMessage(ChatOutput.PREFIX.getText() + "Es wurden noch keine Homes gesetzt!.");
        }

    }
    public static String getOnlinePlayer(){
        int a = Bukkit.getOnlinePlayers().size();
        int b = Bukkit.getMaxPlayers();

        return "§a" + a + " §7von§c " + b;
    }
}
