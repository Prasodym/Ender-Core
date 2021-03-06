package de.jomlua.listener;

import de.jomlua.core;
import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.logging.Level;

public class QuitListener implements Listener {
    @EventHandler
    public void OnPlayerExitServer(PlayerQuitEvent e) throws IOException {
        Player player = e.getPlayer();
        e.setQuitMessage(ChatUtils.Color("&8[&c-&8] " + player.getDisplayName()));
        SaveQuitPosition(player);

    }
    private static void SaveQuitPosition(Player player) throws IOException {
        File file = new File(core.plugin.getDataFolder() + "/users", player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
        String location = player.getLocation().toString();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();
        double yaw = (float) player.getLocation().getYaw();
        double pitch = (float) player.getLocation().getPitch();


        yml.set("lastplayed.location.x", x);
        yml.set("lastplayed.location.y", y);
        yml.set("lastplayed.location.z", z);
        yml.set("lastplayed.location.yaw", yaw);
        yml.set("lastplayed.location.pitch", pitch);
        yml.set("lastplayed.time", time.toString());
        yml.save(file);
        Bukkit.getConsoleSender().sendMessage( ChatOutput.PREFIXC.getText() + "Save " + player.getDisplayName() + " userdata in users/" + player.getUniqueId() + ".yml");

    }
}

