package de.jomlua.utils.modules;

import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Random;

/**
 * Todo:
 * Update of 1.19 or newer
 * Class RED
 */
public class voidgenerator extends ChunkGenerator {
    public ChunkData generateVoid(World world, Random random, int x, int z, BiomeGrid biome){
        return createChunkData(world);
    }
}
