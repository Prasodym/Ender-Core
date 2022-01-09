package de.jomlua.utils.modules;

import de.jomlua.core;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class VanishManager {
    private final core core;
    private final List<Player> vanished;

    public VanishManager(core core) {
        this.core = core;
        this.vanished = new ArrayList<>();
    }

    public List<Player> getVanished(){
        return vanished;
    }

    public boolean isVanish(Player player){
        return vanished.contains(player);
    }
    public void setVanished(Player player, boolean var){
        if (var){
            vanished.add(player);
        }else {
            vanished.remove(player);
        }

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (player.equals(onlinePlayer)) continue;
            if (var){
                onlinePlayer.hidePlayer(core, player);
            }else{
                onlinePlayer.showPlayer(core, player);
            }
        }
    }

    public void hiddePlayer(Player player){
        vanished.forEach(player1 -> player.hidePlayer(core, player1));
    }
}
