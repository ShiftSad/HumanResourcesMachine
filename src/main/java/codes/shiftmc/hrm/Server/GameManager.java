package codes.shiftmc.hrm.Server;

import net.minestom.server.entity.Player;

import java.util.ArrayList;
import java.util.Map;

public final class GameManager {

    private GameManager() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    private final static ArrayList<Game> runningGames = new ArrayList<>();
    private final static Map<Integer, Game> games = Map.of(
            0, new Game(new short[]{2, 1, 7}, new short[]{2, 1, 7})
    );

    public static void createGame(Player player, int gameID) {
        Game game = games.get(gameID);
        if (game == null) throw new IllegalArgumentException("Game with ID " + gameID + " does not exist");
        Game newGame = game.clone();

        runningGames.add(newGame);
        newGame.start(player);
    }
}
