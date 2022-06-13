package de.jomlua.listener;

import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.PrivatPermissions;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static de.jomlua.core.*;
public class ChatListener implements Listener {

    @EventHandler
    public void onPlayerWriteChat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        World getworld = player.getWorld();
        String group = chat.getPrimaryGroup(player);
        String suffix = chat.getPlayerSuffix(player);
        String prefix = chat.getGroupPrefix(getworld,group);
        String world = getworld.getName();


        if (player.hasPermission(PrivatPermissions.CHATLITENER.getText())){
            e.setFormat(ChatUtils.setHexText(prefix + player.getDisplayName()+":&a ") + ChatUtils.setHexText(e.getMessage()));
        } else {
            if (!(player.isOp())){
                e.setFormat(ChatUtils.setHexText( prefix + player.getDisplayName()+":&a ") + e.getMessage());
            }else{
                e.setFormat(ChatUtils.setHexText("&7[&4Operator&7]" + prefix + player.getDisplayName()+":&a ") + e.getMessage());
            }
        }

    }

}
