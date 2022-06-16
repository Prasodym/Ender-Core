package de.jomlua.utils.modules;

import de.jomlua.core;
import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static de.jomlua.core.chat;

public class PlayerList {
    public PlayerList() {
    }


    /**
     * @ Get all onlineplayer sorted by groups;
     * @param player msg output to players
     */
    public static void getPlayerList(Player player){
        /**
         * Template
         * [Jomlua] es sind 7 von 10 Spieler online:
         * Owner: [Owner]Prasodym
         * Vip: [VIP]Columbius, [VIP]Berschof, [VIP]Vito09
         * Builder: [Builder]Maggy, [Builder]zeta, [Builder]Neodym
         */
        player.sendMessage(ChatUtils.setHexText(ChatOutput.PREFIX.getText()) + " &7es sind &c " + Bukkit.getServer().getOnlinePlayers().size() + " &7von&c " + Bukkit.getServer().getMaxPlayers() + " &7Spieler Online:");


        for (String group : groupsConfig()){
            if (chat.playerInGroup((Player) Bukkit.getOnlinePlayers(),group)){
                player.sendMessage();
            }
        }

    }
    private static List<String> groupsConfig(){
        List<String> list = new ArrayList<>();
        for ( Object s : core.getPlugin().getConfig().getList("groups")){
            list.add(s.toString());
        }
           return list;
    }

    private static List<Player> getOnlinePlayer(){
        List<Player> list = new ArrayList();
        for (Player allOnlinePlayers : Bukkit.getServer().getOnlinePlayers()){
            list.add(allOnlinePlayers);
        }
        return list;
    }
    private void GetPlayerGroups(Player player){
        HashMap<String, String> gpp = new HashMap<>();

        for (String groups : chat.getPlayerGroups(player)){
            gpp.put(player.getDisplayName(), groups);
        }
    }
}
