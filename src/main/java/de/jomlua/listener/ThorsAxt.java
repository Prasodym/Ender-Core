package de.jomlua.listener;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Set;

public class ThorsAxt implements Listener {
    @EventHandler
    public void GoToFire(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if (e.getItem() != null && e.getItem().hasItemMeta() && e.getAction() == Action.RIGHT_CLICK_AIR | e.getAction() == Action.RIGHT_CLICK_BLOCK && player.getInventory().getItemInMainHand().getItemMeta().getLocalizedName().equalsIgnoreCase("thor1")) {
            if (player.hasPermission("jomlua.admin.tools")) {
                Block targetblock = player.getTargetBlock((Set)null, 200);
                if (targetblock != null) {
                    Location loc = targetblock.getLocation();

                    player.getWorld().strikeLightning(loc);
                }
            } else {
                player.sendMessage("Du bist nicht berechtigt.");
            }
        }
    }
}
