package de.jomlua.listener;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.PrivatPermissions;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandEvent implements Listener {
    @EventHandler
    public void CommandExecute(PlayerCommandPreprocessEvent e){
        if (e.getPlayer().isOp() || e.getPlayer().hasPermission(PrivatPermissions.ADMIN.getText())) return;
        String cmd = e.getMessage().replaceFirst("/", "");

        if (cmd.contains(" ")){
            cmd = cmd.split(" ")[0];
        }
        if (cmd.contains(":"))
            cmd = cmd.split(":")[1];

        if (cmd.equalsIgnoreCase("plugins") || cmd.equalsIgnoreCase("pl") || cmd.equalsIgnoreCase("icanhasbukkit")) {
            e.setCancelled(true); // <------------- This cancels the event and actually prevents the message from getting displayed.
            e.getPlayer().sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
        }
    }
}
