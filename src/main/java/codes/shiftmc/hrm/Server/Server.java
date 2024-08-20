package codes.shiftmc.hrm.Server;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.block.Block;

import java.util.List;

public class Server {

    public static void main(String[] args) {
        MinecraftServer server = MinecraftServer.init();
        MojangAuth.init();

        Instance instance = MinecraftServer.getInstanceManager().createInstanceContainer();
        // chess board platform 14 x 18
        List<Block> blocks = List.of(Block.STRIPPED_SPRUCE_WOOD, Block.STRIPPED_OAK_WOOD);
        for (int x = 0; x < 14; x++) {
            for (int z = 0; z < 18; z++) {
                instance.setBlock(x, 0, z, blocks.get((x + z) % 2));
            }
        }

        var geh = MinecraftServer.getGlobalEventHandler();
        geh.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(instance);
            player.setRespawnPoint(new Pos(0, 2, 0));
        });

        server.start("0.0.0.0", 25565);
    }
}
