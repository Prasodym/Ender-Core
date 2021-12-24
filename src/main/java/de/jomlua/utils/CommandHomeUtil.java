package de.jomlua.utils;

import de.jomlua.utils.config.CoreConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

public class CommandHomeUtil {

    public static void TeleportHome(Player player, String home){
        World world = Bukkit.getWorld(CoreConfig.getString(player, "homes." + home + ".world"));
        double x = CoreConfig.getDouble(player, "homes." + home + ".x");
        double y = CoreConfig.getDouble(player, "homes." + home + ".y");
        double z = CoreConfig.getDouble(player, "homes." + home + ".z");
        float yaw = CoreConfig.getFloat(player,"homes." + home + ".yaw");
        float pitch = CoreConfig.getFloat(player, "homes." + home + ".pitch");

        Location location = new Location(world,x,y,z,yaw,pitch);
        player.teleport(location);
    }
    public static void DeleteHome(Player player, String home) throws IOException {
        CoreConfig.DeletePlayerData(player, "homes." + home);
        CoreConfig.DeletePlayerData(player,"homes." + home + ".world");
        CoreConfig.DeletePlayerData(player,"homes." + home + ".x");
        CoreConfig.DeletePlayerData(player,"homes." + home + ".y");
        CoreConfig.DeletePlayerData(player,"homes." + home + ".z");
        CoreConfig.DeletePlayerData(player,"homes." + home + ".yaw");
        CoreConfig.DeletePlayerData(player,"homes." + home + ".pitch");
        Bukkit.getLogger().log(Level.INFO, ChatOutput.PREFIX.getText() + "Player §c" + player.getDisplayName() + "§f deletet home: §c" + home);
    }
    public static void CreateHome(Player player, Location location, String home) throws IOException {
        String world = location.getWorld().getName();
        double x = location.getX();
        double y = location.getY();
        double z = location.getZ();
        float yaw = location.getYaw();
        float pitch = location.getPitch();

        CoreConfig.AddPlayerData(player, "homes." + home + ".world", world);
        CoreConfig.AddPlayerData(player, "homes." + home + ".x", x);
        CoreConfig.AddPlayerData(player, "homes." + home + ".y", y);
        CoreConfig.AddPlayerData(player, "homes." + home + ".z", z);
        CoreConfig.AddPlayerData(player,"homes." + home + ".yaw", yaw);
        CoreConfig.AddPlayerData(player, "homes." + home + ".pitch", pitch);

        Bukkit.getLogger().log(Level.INFO, ChatOutput.PREFIX.getText() + "Player §c" + player.getDisplayName() + "§f added home: §c" + home);
    }

    public static Set<String> getHomePoints(Player player){
        File file = new File("plugins/jomlua-core/users", player.getUniqueId() + ".yml");
        YamlConfiguration yml = YamlConfiguration.loadConfiguration(file);
        if (yml.isSet("homes.")){

            return yml.getConfigurationSection("homes.").getKeys(false);
        }else{
            return new HashSet<String>();
        }
    }
}
