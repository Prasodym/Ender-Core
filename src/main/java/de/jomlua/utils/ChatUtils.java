package de.jomlua.utils;

import de.jomlua.core;
import de.jomlua.utils.modules.VanishManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static de.jomlua.core.chat;

public class ChatUtils {
    public ChatUtils(){

    }

    /**
     *
     * @param text Text or word which is displayed in the chat
     * @param command Command that will be executed when clicked
     * @param hoverText Infotext when hovering the mouse over it
     * @return Chat output sin clickable
     */
    @Deprecated
    public static TextComponent TcCommand(String text, String command, String hoverText){
//        TextComponent msga = new TextComponent();
//        msga.setText(" §c[Teleport]");
//        msga.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,"/home"));
//        msga.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("[Teleport]").create()));

        TextComponent tc = new TextComponent();
        tc.setText(Color(text));
        tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Color(hoverText)).create()));
        tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND,command));
        return tc;
    }
    @Deprecated
    public static TextComponent TcLink(String text, String linkadress, String Hovertext){
        TextComponent tc = new TextComponent(text);
        tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Color(Hovertext)).create() ));
        tc.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, ChatUtils.Color(linkadress)));
        return tc;
    }
    @Deprecated
    public static TextComponent TcHover(String text, String Hovertext){
        TextComponent tc = new TextComponent(text);
        tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Color(Hovertext)).create() ));
        return tc;
    }
    public static TextComponent TcText(String text){
        TextComponent tc =new TextComponent();
        tc.setText(Color(text));
        return tc;
    }
    public static String Color(String text){
        return ChatColor.translateAlternateColorCodes('&',text);
    }

    public static void msg(Player player, String messages){
        player.sendMessage(ChatUtils.Color(messages));
    }

    public static String getPrefixName(Player player){
        return chat.getPlayerPrefix(player);
    }

    public static String setHexText(String text){
        Pattern pattern = Pattern.compile("#[a-fa-f0-9]{6}");
        Matcher match = pattern.matcher(text);

        while (match.find()){
            String color = text.substring(match.start(), match.end());
            text = text.replace(color, net.md_5.bungee.api.ChatColor.of(color)+"");
            match = pattern.matcher(text);
        }
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    /**
     *@param Text Text to console
     **/
    public static void ConsolenMsg(String Text){
        Bukkit.getConsoleSender().sendMessage(Color(ChatOutput.PREFIXC.getText() + Text));
    }
    public static void SendRgbMsg(Player player, String msg){
        player.sendMessage(setHexText(msg));
    }

    public static String getOnlinePlayer(){
        int a = Bukkit.getOnlinePlayers().size();
        int b = Bukkit.getMaxPlayers();

        return "&7" + a + " &fvon&8 " + b + " &fSpieler";
    }


    private static HashMap<Player, Player> reply = new HashMap<Player, Player>();
    public static void setReply(Player player1, Player player2){
        reply.put(player1, player2);
        reply.put(player2, player1);
    }

    public static Player getReply(Player player1){
        return reply.get(player1);
    }

    public static void NoPerm(Player player){
        ChatUtils.msg(player, ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
    }


}
