package codes.shiftmc.hrm.Server;

import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.instance.block.Block;
import net.minestom.server.world.DimensionType;

import java.util.List;

public class Game {

    private final short[] ram;
    private final short[] expected;
    private final short[] input;

    private final Instance instance;

    private final List<Short> output = List.of();

    public Game(short[] ram, short[] expected, short[] input) {
        this.ram = ram;
        this.expected = expected;
        this.input = input;

        InstanceContainer instance = MinecraftServer.getInstanceManager().createInstanceContainer(DimensionType.OVERWORLD);
        instance.setChunkSupplier(LightingChunk::new);

        // chess board platform 14 x 18
        List<Block> blocks = List.of(Block.STRIPPED_SPRUCE_WOOD, Block.STRIPPED_OAK_WOOD);
        for (int x = 0; x < 14; x++) {
            for (int z = 0; z < 18; z++) {
                instance.setBlock(x, 0, z, blocks.get((x + z) % 2));
            }
        }

        this.instance = instance;
    }

    public Game(short[] expected, short[] input) {
        this(new short[100], expected, input);
    }

    public Game clone() {
        return new Game(ram, expected, input);
    }

    public void init() {
        // chess board platform 14 x 18
        List<Block> blocks = List.of(Block.STRIPPED_SPRUCE_WOOD, Block.STRIPPED_OAK_WOOD);
        for (int x = 0; x < 14; x++) {
            for (int z = 0; z < 18; z++) {
                instance.setBlock(x, 42, z, blocks.get((x + z) % 2));
            }
        }

    }

    public void start(Player player) {
    }
}
