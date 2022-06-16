package de.jomlua.commands.StaffCommands;

import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.PrivatPermissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class Invsee implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if (!(player.hasPermission(PrivatPermissions.INVSEE.getText()))){
            ChatUtils.NoPerm(player);
            return true;
        }

        if (command.getName().equalsIgnoreCase("invsee")){
            if (args.length == 1){
                Player target = Bukkit.getServer().getPlayer(args[0]);

                Inventory targetcontainers = Bukkit.createInventory(player,9, ChatColor.translateAlternateColorCodes('&', "Inventar von &c" + target.getDisplayName()));

                if (Bukkit.getServer().getPlayer(args[0]) == null){
                    //no player msg
                    return true;
                }

                if (target.getInventory().getHelmet() != null) {
                    targetcontainers.setItem(0, target.getInventory().getHelmet());
                }

                if (target.getInventory().getChestplate() != null) {
                    targetcontainers.setItem(1, target.getInventory().getChestplate());
                }

                if (target.getInventory().getLeggings() != null) {
                    targetcontainers.setItem(2, target.getInventory().getLeggings());
                }

                if (target.getInventory().getBoots() != null) {
                    targetcontainers.setItem(3, target.getInventory().getBoots());
                }
                player.openInventory(targetcontainers);
            }else{
                // Help message
            }
        }
        return true;
    }
}
