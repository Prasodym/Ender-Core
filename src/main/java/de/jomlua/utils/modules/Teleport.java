package de.jomlua.utils.modules;

import de.jomlua.core;
import de.jomlua.utils.ChatOutput;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

public class Teleport {
    public Teleport() {
    }

    private static File Cwarp = new File(core.plugin.getDataFolder().getPath() + "warps.yml");


    public static Location TeleportConfig(String path){

            FileConfiguration config = core.getPlugin().getConfig();
            World world = Bukkit.getWorld(config.getString(path + ".world"))   ;
            double x = config.getDouble(path + ".x");
            double y = config.getDouble(path + ".y");
            double z = config.getDouble(path + ".z");
            float yaw = (float) config.getDouble(path + ".yaw");
            float pitch= (float) config.getDouble(path + ".pitch");

        Location location = new Location(world,x,y,z,yaw,pitch);
        return location;
    }

    public static Location TeleportWarp(String WarpName){
        YamlConfiguration yaml = YamlConfiguration.loadConfiguration(Cwarp);
        World world = Bukkit.getWorld(yaml.getString("Warps." + WarpName + ".world"))   ;
        double x = yaml.getDouble("Warps." + WarpName + ".x");
        double y = yaml.getDouble("Warps." + WarpName + ".y");
        double z = yaml.getDouble("Warps." + WarpName + ".z");
        float yaw = (float) yaml.getDouble("Warps." + WarpName + ".yaw");
        float pitch= (float) yaml.getDouble("Warps." + WarpName + ".pitch");

        Location location = new Location(world,x,y,z,yaw,pitch);
        return location;
    }
    public static void SetWarp(Player player, String warpname) throws IOException {
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(Cwarp);
        String world = player.getWorld().getName();
        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();
        double yaw = player.getLocation().getYaw();
        double pitch = player.getLocation().getPitch();

        conf.set("Warps." + warpname + ".world", world);
        conf.set("Warps." + warpname + ".x", x);
        conf.set("Warps." + warpname + ".y", y);
        conf.set("Warps." + warpname + ".z", z);
        conf.set("Warps." + warpname + ".yaw", yaw);
        conf.set("Warps." + warpname + ".pitch", pitch);
        conf.save(Cwarp);
        player.sendMessage(ChatOutput.PREFIX.getText() + "§a Warp §c" + warpname + " §awurde gesetzt.");
        Bukkit.getLogger().log(Level.INFO, ChatOutput.PREFIX.getText() + "Es wurde von§c" + player.getDisplayName() + "§fder neuer Warp §c" + warpname + " §fgesetzt.");
    }
    public static void DeleteWarp(Player player, String warpname) throws IOException {
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(Cwarp);
        conf.set("Warps." + warpname, null);
        conf.set("Warps." + warpname + ".world", null);
        conf.set("Warps." + warpname + ".x", null);
        conf.set("Warps." + warpname + ".y", null);
        conf.set("Warps." + warpname + ".z", null);
        conf.set("Warps." + warpname + ".yaw", null);
        conf.set("Warps." + warpname + ".pitch", null);
        conf.save(Cwarp);
        player.sendMessage(ChatOutput.PREFIX.getText() + "§a Warp §c" + warpname + " §awurde gelöscht.");
        Bukkit.getLogger().log(Level.INFO, ChatOutput.PREFIX.getText() + "Es wurde von§c" + player.getDisplayName() + "§fder Warp §c" + warpname + " §fentfernt.");
    }

    private static List<String> getWarp(){
        File file = new File("plugins/jomlua-core", "warps.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        List<String> warps = new ArrayList<>();


        Iterator var10 = cfg.getKeys(false).iterator();
        while (var10.hasNext()) {
            String arg = (String) var10.next();
            if (!arg.contains(".")) {
                warps.add(arg);
            }
        }
        return warps;
    }
    public static boolean getBooleanWarp(String path){
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(Cwarp);
        return yml.isSet("Warps." + path);
    }
}
