package de.jomlua.utils.modules;



import de.jomlua.core;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

public class WorldManagerModule {

    public static List<String> getExistWorld(){
        File file = new File(core.plugin.getDataFolder(),"worlds.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        List<String> warpse = new ArrayList<>();

        //Iterator<String> WhileHome = cnf.getConfigurationSection("Warps.").getKeys(true).iterator();
        Iterator var10 = cfg.getConfigurationSection("Worlds.").getKeys(false).iterator();
        while (var10.hasNext()) {
            String arg = (String) var10.next();
            if (!arg.contains(".")) {
                warpse.add(arg);
            }
        }
        return warpse;
    }

    public static boolean getBooleanWorld(String path){
        File file = new File(core.plugin.getDataFolder(),"worlds.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        return cfg.isList("Worlds." + path);
    }

    public static void deleteWorld(String path, File url) {
        File challenge = url;
        if (challenge.exists()) {

            Bukkit.unloadWorld(path, true);

            File data = new File(path +"\\data");
            if (data.exists()) {
                for (File files1 : data.listFiles()) {
                    files1.delete();
                }
                data.delete();
            }

            File playerdata = new File(path +"\\playerdata");
            if (playerdata.exists()) {
                for (File files2 : playerdata.listFiles()) {
                    files2.delete();
                }
                playerdata.delete();
            }

            File poi = new File(path +"\\poi");
            if (poi.exists()) {
                for (File files3 : poi.listFiles()) {
                    files3.delete();
                }
                poi.delete();
            }

            File region = new File(path +"\\region");
            if (region.exists()) {
                for (File files4 : region.listFiles()) {
                    files4.delete();
                }
                region.delete();
            }

            for (File files5 : challenge.listFiles()) {
                files5.delete();
            }
            challenge.delete();
        }
    }

    public static void platform(Location location, int xa, int ya) {

        for (int x = (int)location.getX()-xa; x < (int)location.getX()+xa; x++) {
            for (int z = (int)location.getZ()-ya; z < (int)location.getZ()+ya; z++) {
                Block block = location.getWorld().getBlockAt(x, (int)location.getY(), z);
                // set block info
                block.setType(Material.COBBLESTONE);
            }
        }
    }
//    public static void setIsland(Location location){
//        Plugin plugin = core.getPlugin();
//        Path path = Paths.get(plugin.getDataFolder().getPath() + File.separator + "utils/skyinsel.nbt");
//        StructureBlockLibApi.INSTANCE
//                .loadStructure(plugin)
//                .at(location)
//                .loadFromFile(path)
//                .onException(e -> plugin.getLogger().log(Level.SEVERE, "Failed to load structure.", e))
//                .onResult(e -> plugin.getLogger().log(Level.INFO, ChatColor.GREEN + "Loaded structure 'mystructure'."));
//
//    }

}
