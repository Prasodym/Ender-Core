package de.jomlua.utils.modules;

import de.jomlua.core;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Random;

public class RandomTeleport {

    public static core plugin;
    public RandomTeleport(core plugin) {
        this.plugin = plugin;
    }

    public static HashSet<Material> invalidBlocks = new HashSet<>();
    static {
        invalidBlocks.add(Material.LAVA);
        invalidBlocks.add(Material.WATER);
        invalidBlocks.add(Material.CACTUS);
        invalidBlocks.add(Material.FIRE);
    }

    public static Location randomLocation(Player player){
        Random random = new Random();

        int x = 0;
        int y = 0;
        int z = 0;

        if (plugin.getConfig().getBoolean("worldborder.enabled")){
            x = random.nextInt(plugin.getConfig().getInt("worldborder.radius"));
            z = random.nextInt(plugin.getConfig().getInt("worldborder.radius"));
            y = 150;
        }else{
            x = random.nextInt(25000);
            z = random.nextInt(25000);
            y = 150;
        }
        Location Rlocation = new Location(player.getWorld(), x,y,z);
        y = Rlocation.getWorld().getHighestBlockYAt(Rlocation);

        Rlocation.setY(y);
        return Rlocation;
    }
    public static boolean randomLocationValid(Location location){
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();

        Block block = location.getWorld().getBlockAt(x,y,z);
        Block below = location.getWorld().getBlockAt(x,y-1,z);
        Block above = location.getWorld().getBlockAt(x,y+1,z);
        return !(invalidBlocks.contains(below.getType())) || (block.getType().isSolid()) || (above.getType().isSolid());
    }
    public static Location SerchValidLoction(Player player){
        Location RLocation = randomLocation(player);

        while (!randomLocationValid(RLocation)){
            RLocation = randomLocation(player);
        }
        return RLocation;
    }
}
