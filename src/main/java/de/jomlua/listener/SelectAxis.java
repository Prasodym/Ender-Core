package de.jomlua.listener;

import de.jomlua.core;
import de.jomlua.utils.ChatOutput;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import java.text.DecimalFormat;
import java.util.HashMap;

public class SelectAxis implements Listener {
    private NamespacedKey namespacedKey = new NamespacedKey(core.getPlugin(), "loc"); //key = new NamespacedKey(BlockCompare.getPlugin(), "sel"), PersistentDataType.STRING);

    public static HashMap<Player, Location> position1 = new HashMap<Player, Location>();
    public static HashMap<Player, Location> position2 = new HashMap<Player, Location>();
    public Location pos1;
    public Location pos2;
    @EventHandler
    public void SelectWithAxt(PlayerInteractEvent event){

        Player player = event.getPlayer();
        DecimalFormat format = new DecimalFormat("#0.00");
        Block block = event.getClickedBlock();

        if (block == null){
            return;
        }

        if (player.getInventory().getItemInMainHand().getType().equals(Material.WOODEN_AXE)){
            if (event.getAction() == Action.LEFT_CLICK_BLOCK){
                if (event.getHand() == EquipmentSlot.HAND){
                    double x = event.getClickedBlock().getX();
                    double y = event.getClickedBlock().getY();
                    double z = event.getClickedBlock().getZ();

                    pos1 = new Location(player.getWorld(), x, y, z);
                    position1.put(player, pos1);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatOutput.PREFIX.getText()) + "Position: §eX §a" + format.format(x) + " §7| §eY §a" +format.format(y) + " §7| §eZ §a" + format.format(z));
                    event.setCancelled(true);
                }

            }


            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (event.getHand() == EquipmentSlot.HAND) {
                    double x = event.getClickedBlock().getX();

                    double y = event.getClickedBlock().getY();
                    double z = event.getClickedBlock().getZ();

                    pos2 = new Location(player.getWorld(), x, y, z);
                    position2.put(player, pos2);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatOutput.PREFIX.getText()) + "§cPosition: §eX §a" + format.format(x) + " §7| §eY §a" + format.format(y) + " §7| §eZ §a" + format.format(z));
                    event.setCancelled(true);
                }
            }

        }
    }
}
