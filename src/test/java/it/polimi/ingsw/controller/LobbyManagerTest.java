package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.GameSettings;
import it.polimi.ingsw.network.server.ClientHandler;
import it.polimi.ingsw.network.server.ClientHandlerStub;
import org.junit.Test;

/**
 * Testing of ControllerManager class
 */
public class LobbyManagerTest {
    private final LobbyManager lobbyManager;

    public LobbyManagerTest() {
        this.lobbyManager = LobbyManager.getInstance();
    }

    /**
     * Tests how insertion and deletion of a player and creation of a new game are handled
     */
    @Test
    public void testAddPlayer() {
        ClientHandler clientHandler1 = new ClientHandlerStub("Player1", 3);
        this.lobbyManager.addPlayer(clientHandler1);
        assert(this.lobbyManager.getCurrentGameNumPlayers() == 3);
        assert(this.lobbyManager.getLobby().contains(clientHandler1));
        assert(this.lobbyManager.isNicknameTaken("Player1"));
        assert(this.lobbyManager.getGames().size() == 0);

        this.lobbyManager.addPlayer(clientHandler1);
        assert(this.lobbyManager.getCurrentGameNumPlayers() == 3);
        assert(this.lobbyManager.getLobby().contains(clientHandler1));
        assert(this.lobbyManager.getGames().size() == 0);

        ClientHandler clientHandler2 = new ClientHandlerStub("Player2", -1);
        this.lobbyManager.addPlayer(clientHandler2);
        assert(this.lobbyManager.getCurrentGameNumPlayers() == 3);
        assert(this.lobbyManager.getLobby().contains(clientHandler1));
        assert(this.lobbyManager.getLobby().contains(clientHandler2));
        assert(this.lobbyManager.isNicknameTaken("Player1"));
        assert(this.lobbyManager.isNicknameTaken("Player2"));
        assert(this.lobbyManager.getGames().size() == 0);

        this.lobbyManager.addPlayer(clientHandler2);
        assert(this.lobbyManager.getCurrentGameNumPlayers() == 3);
        assert(this.lobbyManager.getLobby().contains(clientHandler1));
        assert(this.lobbyManager.getLobby().contains(clientHandler2));
        assert(this.lobbyManager.getGames().size() == 0);

        this.lobbyManager.removePlayer(clientHandler2);
        assert(this.lobbyManager.getCurrentGameNumPlayers() == 3);
        assert(this.lobbyManager.getLobby().contains(clientHandler1));
        assert(!this.lobbyManager.getLobby().contains(clientHandler2));
        assert(this.lobbyManager.isNicknameTaken("Player1"));
        assert(!this.lobbyManager.isNicknameTaken("Player2"));
        assert(this.lobbyManager.getGames().size() == 0);

        this.lobbyManager.addPlayer(clientHandler2);
        assert(this.lobbyManager.getCurrentGameNumPlayers() == 3);
        assert(this.lobbyManager.getLobby().contains(clientHandler1));
        assert(this.lobbyManager.getLobby().contains(clientHandler2));
        assert(this.lobbyManager.isNicknameTaken("Player1"));
        assert(this.lobbyManager.isNicknameTaken("Player2"));
        assert(this.lobbyManager.getGames().size() == 0);

        ClientHandler clientHandler3 = new ClientHandlerStub("Player3", 7);
        this.lobbyManager.addPlayer(clientHandler3);
        assert(this.lobbyManager.getCurrentGameNumPlayers() == GameSettings.MIN_NUM_PLAYERS);
        assert(!this.lobbyManager.getLobby().contains(clientHandler1));
        assert(!this.lobbyManager.getLobby().contains(clientHandler2));
        assert(!this.lobbyManager.getLobby().contains(clientHandler3));
        assert(this.lobbyManager.isNicknameTaken("Player1"));
        assert(this.lobbyManager.isNicknameTaken("Player2"));
        assert(this.lobbyManager.isNicknameTaken("Player3"));
        assert(this.lobbyManager.getGames().size() == 1);
        assert(this.lobbyManager.getLobby().isEmpty());

        this.lobbyManager.endGame(this.lobbyManager.getGames().get(0));
        assert(this.lobbyManager.getCurrentGameNumPlayers() == GameSettings.MIN_NUM_PLAYERS);
        assert(this.lobbyManager.getGames().size() == 0);
        assert(!this.lobbyManager.isNicknameTaken("Player1"));
        assert(!this.lobbyManager.isNicknameTaken("Player2"));
        assert(!this.lobbyManager.isNicknameTaken("Player3"));
        assert(this.lobbyManager.getLobby().isEmpty());
    }

    @Test
    public void testValues() {
        assert(!this.lobbyManager.isNumPlayersValid(1));
        assert(this.lobbyManager.isNumPlayersValid(2));
        assert(this.lobbyManager.isNumPlayersValid(3));
        assert(this.lobbyManager.isNumPlayersValid(4));
        assert(!this.lobbyManager.isNumPlayersValid(5));
    }
}
