package de.jomlua.utils.modules;

import de.jomlua.core;
import de.jomlua.utils.ChatOutput;
import de.jomlua.utils.ChatUtils;
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
import java.util.Objects;
import java.util.logging.Level;

public class Teleport {
    public Teleport() {
    }

    private static File Cwarp = new File(core.plugin.getDataFolder(),"warps.yml");


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
    public static Location DefaultHome(Player player){
        File file = new File(core.plugin.getDataFolder(), "/users" + player.getUniqueId() + ".yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(file);

        World world = Bukkit.getWorld(config.getString("homes.home.world"));
        double x = config.getDouble("homes.home.x");
        double y = config.getDouble("homes.home.y");
        double z = config.getDouble("homes.home.z");
        float yaw = (float) config.getDouble("homes.home.yaw");
        float pitch= (float) config.getDouble("homes.home.pitch");

        return new Location(world,x,y,z,yaw,pitch);
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
        ChatUtils.ConsolenMsg("&cEs wurde von&f " + player.getDisplayName() + " &cder neuer Warp §f" + warpname + " &cgesetzt.");

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
        ChatUtils.ConsolenMsg("&cEs wurde das Warp &f" + warpname + " &cwurde gelöscht von &f" + player.getDisplayName() + "&c.");
    }

    public static void DeleteHome(Player player, String warpname) throws IOException {
        File file = new File(core.plugin.getDataFolder(), player.getUniqueId() + ".yml");
        YamlConfiguration conf = YamlConfiguration.loadConfiguration(file);
        if (conf.isSet("homes." + warpname)){
            conf.set("homes." + warpname, null);
            conf.set("homes." + warpname + ".world", null);
            conf.set("homes." + warpname + ".x", null);
            conf.set("homes." + warpname + ".y", null);
            conf.set("homes." + warpname + ".z", null);
            conf.set("homes." + warpname + ".yaw", null);
            conf.set("homes." + warpname + ".pitch", null);
            conf.save(file);
            Chatinterfaces.ListetHomeInterface(player);
            player.sendMessage(ChatOutput.PREFIX.getText() + "§aDein Home §c" + warpname + " §awurde gelöscht.");
        }else{
            player.sendMessage(ChatOutput.PREFIX.getText() + "§cDiser Home §f" + warpname + " §cexistiert nicht!");
        }

    }

    public static List<String> getWarp(){
        File file = new File(core.plugin.getDataFolder(),"warps.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        List<String> warpse = new ArrayList<>();

        //Iterator<String> WhileHome = cnf.getConfigurationSection("Warps.").getKeys(true).iterator();
       try{
           Iterator var10 = cfg.getConfigurationSection("Warps.").getKeys(false).iterator();
           while (var10.hasNext()) {
               String arg = (String) var10.next();
               if (!arg.contains(".")) {
                   warpse.add(arg);
               }
           }
       }catch (NullPointerException e){

       }
        return warpse;
    }
    public static boolean getBooleanWarp(String path){
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(Cwarp);
        return yml.isSet("Warps." + path);
    }
}
