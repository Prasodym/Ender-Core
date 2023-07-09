package de.jomlua.listener;

import de.jomlua.core;
import de.jomlua.utils.CommandHomeUtil;
import de.jomlua.utils.modules.Teleport;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;


public class DeathListener implements Listener {
    private float getXP;

    /**
     * TODO Diesen Handler Fixen, Teleportiert nicht zum home.
     * @param e Event
     */
    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        Player player = e.getPlayer();
        Location location = player.getLocation();

        CommandHomeUtil.TeleportHome(player, "home");

        player.setFlySpeed((float) 0.1);
        player.setWalkSpeed((float) 0.2);
        player.setAllowFlight(false);
        player.setGameMode(GameMode.SURVIVAL);
        if (core.getPlugin().CoreConfig().getBoolean("give-back-xp-on-death")){
            player.setExp(getXP);
        }
        player.playSound(location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP,4f,2f);
    }
    @EventHandler
    public void DeathOn(PlayerDeathEvent e){
        Player player = e.getEntity();
        getXP = player.getExp();
    }
    @EventHandler
    public void doSpawnMobEvent(CreatureSpawnEvent e){

        if (e.getEntity().getType().equals(EntityType.SLIME)){
            e.setCancelled(true);
        }
    }
}
