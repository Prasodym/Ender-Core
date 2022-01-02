package de.jomlua.commands;

import de.jomlua.core;
import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
import de.jomlua.utils.modules.RandomTeleport;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

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
//                setBorderVisual();
            }else{
                ChatUtils.msg(player, ChatOutput.PREFIX.getText() + ChatOutput.COMMAND_HELP.getText());
                ChatUtils.msg(player, "");
                ChatUtils.msg(player, "&7 - &e/&bworldborder &c<radius>");
            }
        }
        return true;
    }

    private void setBorder(String world, String size, Player player) {
        File file = new File("plugins/jomlua-core", "config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
        Location spawn = player.getLocation();

        try {
            config.load(file);
        }catch (InvalidConfigurationException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
            core.plugin.getLogger().log(Level.WARNING, "Die datei" + file + "Konnte nicht geldaden werden.");
        }

//        if (config.isSet("worldborder.worlds." + world)){
            //config.set("worldborder.worlds", world);
            config.set("worldborder.worlds." + world + ".size", Integer.valueOf(size));
            System.out.println("true");
//        }else{
//            //config.addDefault("worldborder.worlds", world);
//            config.addDefault("worldborder.worlds." + world + ".size", size);
//            System.out.println("false");
//        }


        try {
            config.save(file);

            WorldBorder border = Bukkit.getWorld(world).getWorldBorder();
            border.setCenter(spawn);
            border.setSize(Double.parseDouble(size));
            border.setWarningDistance(2);
            border.setDamageAmount(-10);
            player.sendMessage("Worldborder in " + world + " wurde auf " + size + " gesetzt.");
        }catch (IOException e){
            e.printStackTrace();
        }

//        String getworld = core.plugin.getConfig().getString("worldborder.worlds");
//        double getSize = core.plugin.getConfig().getDouble("worldborder.worlds." + getworld + ".size");

    }

}
