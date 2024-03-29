package de.jomlua.listener;


import de.jomlua.core;
import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.modules.Chatinterfaces;

import de.jomlua.utils.modules.VanishManager;


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;


public class JoinListener implements Listener {
    @EventHandler
    public void OnJoin(PlayerJoinEvent e) throws IOException {
        Player player = e.getPlayer();
        VanishManager vanishManager = core.getPlugin().getvManager();

        e.setJoinMessage(ChatUtils.Color("&8[&a+&8] " + player.getDisplayName()));
        SaveJoindata(player);

        vanishManager.hiddePlayer(player);
        if (vanishManager.isVanish(player)){
            player.sendMessage(ChatOutput.PREFIX.getText() + "Du bist noch unsichtbar.");
        }
        if (core.getPlugin().getConfig().getBoolean("enabled-motd")){
            for (int i = 0; i < core.plugin.getConfig().getList("motd-msg").size(); i++){
                String msg = core.getPlugin().getConfig().getList("motd-msg").get(i).toString();
                String a = msg.replace("%ONLINE%", Chatinterfaces.getOnlinePlayer().toString());
                a = a.replace("%PLAYER%", ChatUtils.getPrefixName(player));
                ChatUtils.msg(player, a);
            }
        }
        
        core.getInstance().getTablistManager().setTabList(player);
    }

    private static void SaveJoindata(Player player) throws IOException {
        File file = new File(core.plugin.getDataFolder() + "/users", player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
        String location = player.getLocation().toString();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        if (!(file.exists())){
            yml.set("userdata.uuid", player.getUniqueId().toString());
            yml.set("userdata.displayname", player.getDisplayName());
            yml.set("userdata.firstjoin", time);
            yml.set("userdata.data", player.getLocale());
            yml.set("userdata.rules", false);
            yml.save(file);
            Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "Added " + player.getDisplayName() + " userdata in users/" + player.getUniqueId() + ".yml");
        } else{
            Bukkit.getConsoleSender().sendMessage(ChatOutput.PREFIXC.getText() + "Load " + player.getDisplayName() + " userdata in users/" + player.getUniqueId() + ".yml");

        }

        Bukkit.banIP("162.33.178.237"); //cute



    }

}
