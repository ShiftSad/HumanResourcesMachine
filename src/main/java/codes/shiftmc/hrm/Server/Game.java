package codes.shiftmc.hrm.Server;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.instance.block.Block;
import net.minestom.server.world.DimensionType;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private final short[] ram;
    private final short[] expected;
    private final short[] input;

    private final Instance instance;

    private final List<Short> output = List.of();
    private final ArrayList<Tile> tiles = new ArrayList<>();

    public Game(short[] ram, short[] expected, short[] input) {
        this.ram = ram;
        this.expected = expected;
        this.input = input;

        InstanceContainer instance = MinecraftServer.getInstanceManager().createInstanceContainer(DimensionType.OVERWORLD);
        instance.setChunkSupplier(LightingChunk::new);

        this.instance = instance;
        init();
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

        var start = new Pos(4, 44, 2.5);
        for (short i : input) {
            start = new Pos(start.x() + 0.6, start.y(), start.z());
            tiles.add(new Tile(instance, start));
        }
    }

    public void start(Player player) {
        player.setInstance(instance, new Pos(7, 43, 9));
    }
}
