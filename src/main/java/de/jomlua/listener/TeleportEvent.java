package de.jomlua.listener;

import de.jomlua.commands.Back;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class TeleportEvent implements Listener {
    @EventHandler
    public void onTeleport(PlayerTeleportEvent e){
        Player player = e.getPlayer();
        Back.back.put(player.getUniqueId(),player.getLocation());
    }
}
