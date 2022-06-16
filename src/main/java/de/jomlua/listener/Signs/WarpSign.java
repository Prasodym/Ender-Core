package de.jomlua.listener.Signs;

import de.jomlua.core;
import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.PrivatPermissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.File;

public class WarpSign implements Listener {
    @EventHandler
    private void SignChanged(SignChangeEvent e){
        Player player = e.getPlayer();
        String[] line = e.getLines();

        File file = new File(core.plugin.getDataFolder(), "warps.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        if (player.hasPermission(PrivatPermissions.WARPSIGNADD.getText())){
            if (line[0].equalsIgnoreCase("[Warp]")){
                if (!line[1].isEmpty()){


                    if (cfg.isSet("Warps." + line[1])){
                        player.sendMessage(ChatOutput.PREFIX.getText() + "§aDas Warp Schild wurde erfolgreich erstellt.");
                        e.setLine(0, ChatColor.DARK_BLUE + "[Warp]");
                        e.setLine(1, ChatColor.GREEN + line[1]);
                    }else{
                        player.sendMessage(ChatOutput.PREFIX.getText() + "§cDieser Warp ist noch nicht gesetzt.");
                        e.setCancelled(true);
                    }
                } else {
                    player.sendMessage(ChatOutput.PREFIX.getText() + "Du musst in der zweiten zeile dein Warp Ziel eingeben.");
                    e.setCancelled(true);

                }
            }
        }else{
            player.sendMessage(ChatOutput.PREFIX.getText() + ChatOutput.NO_PERMISSIONS.getText());
            e.setCancelled(true);
        }

    }



    @EventHandler
    public void OnPlayerClickSign(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK){
                if (event.getClickedBlock().getState() instanceof Sign){

                    Sign s = (Sign) event.getClickedBlock().getState();
                    if (s.getLine(0).equalsIgnoreCase( ChatColor.DARK_BLUE + "[Warp]")){
                        String a = s.getLine(1);
                        String b = ChatColor.stripColor(a);
                        Bukkit.getServer().dispatchCommand(player,"warp " + b);
                    }
                }
        }
    }
}
