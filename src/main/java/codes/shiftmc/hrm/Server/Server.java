package codes.shiftmc.hrm.Server;

import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.AsyncPlayerConfigurationEvent;
import net.minestom.server.event.player.PlayerChatEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.Instance;
import net.minestom.server.instance.LightingChunk;
import net.minestom.server.world.DimensionType;

public class Server {

    public static void main(String[] args) {
        MinecraftServer server = MinecraftServer.init();
        MojangAuth.init();

        Instance instance = MinecraftServer.getInstanceManager().createInstanceContainer(DimensionType.OVERWORLD);

        instance.setChunkSupplier(LightingChunk::new);

        var geh = MinecraftServer.getGlobalEventHandler();
        geh.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(instance);
            player.setRespawnPoint(new Pos(0, 44, 0));
        });

        geh.addListener(PlayerChatEvent.class, event -> {
            if (event.getMessage().equalsIgnoreCase("start")) {
                GameManager.createGame(event.getPlayer(), 0);
            }
        });

        server.start("0.0.0.0", 25565);
    }
}
