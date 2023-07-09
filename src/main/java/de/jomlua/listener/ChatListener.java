package de.jomlua.listener;

import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.PrivatPermissions;
//import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import static de.jomlua.core.*;
public class ChatListener implements Listener {

    @EventHandler @Deprecated //TODO 1.19.3 AsyncChatDecorateEvent and AsyncChatCommandDecorateEvent is new
    public void onPlayerWriteChat(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        World getworld = player.getWorld();
        String group = chat.getPrimaryGroup(player);
        String suffix = chat.getPlayerSuffix(player);
        String prefix = chat.getGroupPrefix(getworld,group);
        String world = getworld.getName();
        String message = e.getMessage();

        String Fmsg = ChatUtils.setHexText(prefix + player.getDisplayName()+":&a ") + ChatUtils.setHexText(e.getMessage());
        String nOPmsg = ChatUtils.setHexText( prefix + player.getDisplayName()+":&a ") + e.getMessage();
        String OPmsg = ChatUtils.setHexText("&7[&4Operator&7]" + prefix + player.getDisplayName()+":&a ") + e.getMessage();

        if (player.hasPermission(PrivatPermissions.CHATLITENER.getText())){
            e.setFormat(ChatUtils.setHexText(prefix + player.getDisplayName()+":&a ") + ChatUtils.setHexText(e.getMessage()));
            //Bukkit.getLogger().info(Fmsg);
            //Bukkit.broadcastMessage(Fmsg);
        } else {
            if (!(player.isOp())){
                e.setFormat(ChatUtils.setHexText( prefix + player.getDisplayName()+":&a ") + e.getMessage());
                //Bukkit.getLogger().info(nOPmsg);
               // Bukkit.broadcastMessage(nOPmsg);
            }else{
                //Bukkit.getLogger().info(OPmsg);
                //Bukkit.broadcastMessage(OPmsg);
                e.setFormat(ChatUtils.setHexText("&7[&4Operator&7]" + prefix + player.getDisplayName()+":&a ") + e.getMessage());
            }
        }

    }

    /*public void onPlayerChat(AsyncChatEvent e){
        Player player = e.getPlayer();
        World getWorld = player.getWorld();
        String group = chat.getPrimaryGroup(player);

        String suffix = chat.getGroupSuffix(getWorld, group);
        String prefix = chat.getGroupPrefix(getWorld, group);


        if (player.hasPermission(PrivatPermissions.CHATLITENER.getText())){

            e.setFormat(ChatUtils.setHexText(prefix + player.getDisplayName()+":&a ") + ChatUtils.setHexText(e.getMessage()));
        } else {
            if (!(player.isOp())){
                e.setFormat(ChatUtils.setHexText( prefix + player.getDisplayName()+":&a ") + e.getMessage());
            }else{
                e.setFormat(ChatUtils.setHexText("&7[&4Operator&7]" + prefix + player.getDisplayName()+":&a ") + e.getMessage());
            }
        }
    }*/

}
