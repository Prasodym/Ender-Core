package de.jomlua.listener.Signs;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.PrivatPermissions;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class ColoredSigns implements Listener {
    @EventHandler
    public void ClorSign(SignChangeEvent e){
        if (e.getPlayer().hasPermission(PrivatPermissions.COLOREDSIGNS.getText())){
            for (int i = 0; i < 4; i++){
                String line = e.getLine(i);
                if(line != null && !line.equals("")){
                    e.setLine(i, ChatColor.translateAlternateColorCodes('&',line));
                }
            }
        } else {
            System.out.println(ChatOutput.PREFIXC.getText() + "§7" + e.getPlayer().getDisplayName() + " §aHat gerade versucht ein Farbschild zu setzen.");
        }

    }
}
