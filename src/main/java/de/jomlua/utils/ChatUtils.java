package de.jomlua.utils;

import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

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
    public static TextComponent TcText(String text){
        TextComponent tc =new TextComponent();
        tc.setText(Color(text));
        return tc;
    }
    public static String Color(String text){
        return ChatColor.translateAlternateColorCodes('&',text);
    }

    public static void msg(Player player, String messages){
        TextComponent text = new TextComponent();
        text.setText(ChatUtils.Color(messages));
        player.spigot().sendMessage(text);
    }
}
