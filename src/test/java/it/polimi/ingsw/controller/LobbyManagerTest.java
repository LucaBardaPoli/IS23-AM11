package it.polimi.ingsw.controller;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.IOException;

/**
 * Testing of ControllerManager class
 */
public class LobbyManagerTest extends TestCase {
    LobbyManager lobbyManager;

    /**
     * Create the test case
     * @param testName name of the test case
     */
    public LobbyManagerTest(String testName) {
        super(testName);
        this.lobbyManager = LobbyManager.getInstance();
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(LobbyManagerTest.class);
    }

    /**
     * Tests how insertion and deletion of a player and creation of a new game are handled
     */
    public void testAddPlayer() throws IOException {
        /*assert(this.lobbyManager.addPlayer("Player1", 3));
        assert(this.lobbyManager.getCurrentGameNumPlayers() == 3);
        assert(this.lobbyManager.getLobby().contains("Player1"));
        assert(this.lobbyManager.getControllers().size() == 0);

        assert(!this.lobbyManager.addPlayer("Player1", -1));
        assert(this.lobbyManager.getCurrentGameNumPlayers() == 3);
        assert(this.lobbyManager.getLobby().contains("Player1"));
        assert(this.lobbyManager.getControllers().size() == 0);

        assert(this.lobbyManager.addPlayer("Player2", -1));
        assert(this.lobbyManager.getCurrentGameNumPlayers() == 3);
        assert(this.lobbyManager.getLobby().contains("Player1"));
        assert(this.lobbyManager.getLobby().contains("Player2"));
        assert(this.lobbyManager.getControllers().size() == 0);

        assert(!this.lobbyManager.addPlayer("Player2", -1));
        assert(this.lobbyManager.getCurrentGameNumPlayers() == 3);
        assert(this.lobbyManager.getLobby().contains("Player1"));
        assert(this.lobbyManager.getLobby().contains("Player2"));
        assert(this.lobbyManager.getControllers().size() == 0);

        this.lobbyManager.removePlayer("Player2");
        assert(this.lobbyManager.getCurrentGameNumPlayers() == 3);
        assert(this.lobbyManager.getLobby().contains("Player1"));
        assert(!this.lobbyManager.getLobby().contains("Player2"));
        assert(this.lobbyManager.getControllers().size() == 0);

        assert(this.lobbyManager.addPlayer("Player2", -1));
        assert(this.lobbyManager.getCurrentGameNumPlayers() == 3);
        assert(this.lobbyManager.getLobby().contains("Player1"));
        assert(this.lobbyManager.getLobby().contains("Player2"));
        assert(this.lobbyManager.getControllers().size() == 0);

        assert(this.lobbyManager.addPlayer("Player3", 7));
        assert(this.lobbyManager.getCurrentGameNumPlayers() == LobbyManager.MIN_NUM_PLAYERS);
        assert(!this.lobbyManager.getLobby().contains("Player1"));
        assert(!this.lobbyManager.getLobby().contains("Player2"));
        assert(!this.lobbyManager.getLobby().contains("Player3"));
        assert(this.lobbyManager.getControllers().size() == 1);
        assert(this.lobbyManager.getLobby().isEmpty());*/
    }
}
