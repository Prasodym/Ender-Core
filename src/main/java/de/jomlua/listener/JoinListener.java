package de.jomlua.listener;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.logging.Level;

public class JoinListener implements Listener {
    @EventHandler
    public void OnJoin(PlayerJoinEvent e) throws IOException {
        Player player = e.getPlayer();
        e.setJoinMessage(ChatUtils.Color("&8[&a+&8] " + player.getDisplayName()));
        SaveJoindata(player);
    }

    private static void SaveJoindata(Player player) throws IOException {
        File file = new File("plugins/jomlua-core/users", player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
        String location = player.getLocation().toString();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        if (!(file.exists())){
            yml.set("userdata.uuid", player.getUniqueId().toString());
            yml.set("userdata.displayname", player.getDisplayName());
            yml.set("userdata.firstjoin", time);
            yml.set("userdata.data", player.getLocale());
            yml.save(file);
            Bukkit.getLogger().log(Level.INFO, ChatOutput.PREFIX.getText() + "Added " + player.getDisplayName() + " userdata in users/" + player.getUniqueId() + ".yml");
        } else{
            Bukkit.getLogger().log(Level.INFO, ChatOutput.PREFIX.getText() + "Load " + player.getDisplayName() + " userdata in users/" + player.getUniqueId() + ".yml");
        }



    }
}
