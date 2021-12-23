package de.jomlua.commands;

import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.PrivatPermissions;
import de.jomlua.utils.config.CoreConfig;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.logging.Level;

public class SetSpawn implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        String prefix = ChatOutput.PREFIX.getText();

        if (!(player.hasPermission(PrivatPermissions.SETSPAWN.getText()))){
            player.sendMessage(prefix + ChatOutput.NO_PERMISSIONS.getText());
            return true;
        }
        if (!(sender instanceof Player)){
            sender.sendMessage(ChatOutput.NO_PLAYER.getText());
            return true;
        }

        try{
            CoreConfig.setConfig("Spawn.world", player.getWorld().getName());
            CoreConfig.setConfig("Spawn.x", player.getLocation().getX());
            CoreConfig.setConfig("Spawn.y", player.getLocation().getBlockY());
            CoreConfig.setConfig("Spawn.z", player.getLocation().getZ());
            CoreConfig.setConfig("Spawn.pitch", player.getLocation().getPitch());
            CoreConfig.setConfig("Spawn.yaw", player.getLocation().getYaw());
        }catch(IOException exze){
            exze.printStackTrace();
            Bukkit.getLogger().log(Level.WARNING, "There is an error reading the spawn location, check the configuration");
        }

        System.out.println(ChatOutput.PREFIX.getText() + "Es wurde von" + player.getDisplayName() + "ein neuer TP Punkt gesetzt.");
        player.sendMessage(ChatOutput.PREFIX.getText() + "§aDu hast in §3§l" + player.getWorld().getName() + "§a den Spawn gesetzt.");
        return true;
    }
}
