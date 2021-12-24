package de.jomlua.commands;

import de.jomlua.core;
import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.modules.RandomTeleport;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;

public class randomTeleport implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        Player player = (Player) sender;
        if (command.getName().equalsIgnoreCase("randomtp")){
            if (args.length == 0){
                if (player.getWorld().getEnvironment() == World.Environment.NETHER || player.getWorld().getEnvironment() == World.Environment.NETHER){
                    ChatUtils.msg(player, ChatOutput.PREFIX.getText() + "Du kannst dich in &c" + player.getWorld().getName() + "nicht Random teleportieren.");
                }else{
                    player.teleport(RandomTeleport.SerchValidLoction(player));
                    ChatUtils.msg(player, "Teleportiere.");
                }
            }else{
                ChatUtils.msg(player, ChatOutput.PREFIX.getText() + ChatOutput.COMMAND_HELP.getText());
                ChatUtils.msg(player, "");
                ChatUtils.msg(player, "&7 - &e/&brtp &foder &e/&brandomtp");
            }
        }
        if (command.getName().equalsIgnoreCase("worldborder")){
            if (args.length == 1){

                setBorder(player.getWorld().getName(), args[0], player);
            }else{
                ChatUtils.msg(player, ChatOutput.PREFIX.getText() + ChatOutput.COMMAND_HELP.getText());
                ChatUtils.msg(player, "");
                ChatUtils.msg(player, "&7 - &e/&bworldborder &c<radius>");
            }
        }
        return true;
    }

    private void setBorder(String world, String size, Player player){
        core.plugin.getConfig().set("worldborder.worlds" + world + ". radius", size);

        String getworld = core.plugin.getConfig().getString("worldborder.worlds");
        double getSize = core.plugin.getConfig().getDouble("worldborder.worlds." + getworld + ".size");

        WorldBorder border = Bukkit.getWorld(getworld).getWorldBorder();
        border.setCenter(Bukkit.getWorld(getworld).getSpawnLocation());
        border.setSize(getSize);
        border.setWarningDistance(2);


    }
}
