package de.jomlua.utils.modules;

import de.jomlua.core;
import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class TablistModule {

    public void setTabList(Player player){
        DateTimeFormatter Time = DateTimeFormatter.ofPattern("HH:mm:ss");

        HashMap<String, String> head = new HashMap<String, String>();
        head.put("%playercount%", ChatUtils.setHexText(ChatUtils.getOnlinePlayer()));

        HashMap<String, String> foot = new HashMap<String, String>();
        foot.put("%clock%", Time.format(LocalDateTime.now()));

        player.setPlayerListHeaderFooter(ChatUtils.setHexText(ChatOutput.TABHEAD.getText(head)), ChatUtils.setHexText(ChatOutput.TABFOOTER.getText(foot)));
        player.setPlayerListName(ChatUtils.setHexText(core.chat.getPlayerPrefix(player) + player.getDisplayName()));

    }
}
