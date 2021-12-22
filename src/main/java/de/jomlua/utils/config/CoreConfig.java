package de.jomlua.utils.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class CoreConfig {

Player player;
String path;

CoreConfig(){

}
    public static boolean getBoolean(Player player, String path){
        File file = new File("plugins/jomlua-core/users", player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
        return yml.isSet(path);
    }
    public static String getString(Player player, String path){
        File file = new File("plugins/jomlua-core/users", player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
        return yml.getString(path);
    }
    public static Double getDouble(Player player, String path){
        File file = new File("plugins/jomlua-core/users", player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
        return yml.getDouble(path);
    }
    public static float getFloat(Player player, String path){
        File file = new File("plugins/jomlua-core/users", player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
        return (float) yml.getDouble(path);
    }

    public static void DeletePlayerData(Player player, String path)throws IOException {
        File file = new File("plugins/jomlua-core/users", player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
        yml.set(path,null);
        yml.save(file);
    }
    public static void AddPlayerData(Player player, String path, Object data)throws IOException {
        File file = new File("plugins/jomlua-core/users", player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
        yml.set(path,data);
        yml.save(file);
    }

    public static int getintConfig(String path){
        File file = new File("plugins/jomlua-core", "config.yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
        return yml.getInt(path);
    }



}
