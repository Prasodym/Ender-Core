package de.jomlua.utils.modules;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class RegionModule {
    public RegionModule() {
    }
    /**
     * Markiert eine Region (Quader) mit zwei positionen.
     * @param one Position 1
     * @param two Postition 2
     * @return Alle Blöcke in dieser Region.
     */
    public static List<Location> SelectRegion(Location one, Location two){
        List<Location> blocks = new ArrayList<>();
        int topBlockX = (one.getBlockX() < two.getBlockX() ? two.getBlockX() : one.getBlockX());
        int bottomX = (one.getBlockX() > two.getBlockX() ? two.getBlockX() : one.getBlockX());
        int topBlockY = (one.getBlockY() < two.getBlockY() ? two.getBlockY() : one.getBlockY());
        int bottomY = (one.getBlockY() > two.getBlockY() ? two.getBlockY() : one.getBlockY());
        int topBlockZ = (one.getBlockZ() < two.getBlockZ() ? two.getBlockZ() : one.getBlockZ());
        int bottomZ = (one.getBlockZ() > two.getBlockZ() ? two.getBlockZ() : one.getBlockZ());
        for(int x = bottomX;x <= topBlockX; x++){
            for (int z = bottomZ; z <= topBlockZ; z++){
                for (int y = bottomY; y <= topBlockY; y++){
                    blocks.add(new Location(one.getWorld(), x, y, z));
                }
            }
        }
        return blocks;
    }

    public static List<String> getBlocks(List<Location> QubidPositio){
        List<String> blocks = new ArrayList<>();

        for (Location e : QubidPositio)
            blocks.add(e.getBlock().getType().name().toString());
        return blocks;
    }
    public static List<String> getBlocksAt(List<Location> QubidPositio){
        List<String> blocks = new ArrayList<>();

        for (Location e : QubidPositio)
            blocks.add(e.getBlockX() + " | " + e.getBlockY() + "  | " + e.getBlockZ());
        return blocks;
    }
}
