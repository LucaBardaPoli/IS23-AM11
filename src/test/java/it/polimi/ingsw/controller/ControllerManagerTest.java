package it.polimi.ingsw.controller;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Testing of ControllerManager class
 */
public class ControllerManagerTest extends TestCase {
    ControllerManager controllerManager;

    /**
     * Create the test case
     * @param testName name of the test case
     */
    public ControllerManagerTest(String testName) {
        super(testName);
        this.controllerManager = ControllerManager.getInstance();
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(ControllerManagerTest.class);
    }

    /**
     * Tests how insertion and deletion of a player and creation of a new game are handled
     */
    public void testAddPlayer() {
        assert(this.controllerManager.addPlayer("Player1", 3));
        assert(this.controllerManager.getCurrentGameNumPlayers() == 3);
        assert(this.controllerManager.getLobby().contains("Player1"));
        assert(this.controllerManager.getControllers().size() == 0);

        assert(!this.controllerManager.addPlayer("Player1", -1));
        assert(this.controllerManager.getCurrentGameNumPlayers() == 3);
        assert(this.controllerManager.getLobby().contains("Player1"));
        assert(this.controllerManager.getControllers().size() == 0);

        assert(this.controllerManager.addPlayer("Player2", -1));
        assert(this.controllerManager.getCurrentGameNumPlayers() == 3);
        assert(this.controllerManager.getLobby().contains("Player1"));
        assert(this.controllerManager.getLobby().contains("Player2"));
        assert(this.controllerManager.getControllers().size() == 0);

        assert(!this.controllerManager.addPlayer("Player2", -1));
        assert(this.controllerManager.getCurrentGameNumPlayers() == 3);
        assert(this.controllerManager.getLobby().contains("Player1"));
        assert(this.controllerManager.getLobby().contains("Player2"));
        assert(this.controllerManager.getControllers().size() == 0);

        this.controllerManager.removePlayer("Player2");
        assert(this.controllerManager.getCurrentGameNumPlayers() == 3);
        assert(this.controllerManager.getLobby().contains("Player1"));
        assert(!this.controllerManager.getLobby().contains("Player2"));
        assert(this.controllerManager.getControllers().size() == 0);

        assert(this.controllerManager.addPlayer("Player2", -1));
        assert(this.controllerManager.getCurrentGameNumPlayers() == 3);
        assert(this.controllerManager.getLobby().contains("Player1"));
        assert(this.controllerManager.getLobby().contains("Player2"));
        assert(this.controllerManager.getControllers().size() == 0);

        assert(this.controllerManager.addPlayer("Player3", 7));
        assert(this.controllerManager.getCurrentGameNumPlayers() == ControllerManager.MIN_NUM_PLAYERS);
        assert(!this.controllerManager.getLobby().contains("Player1"));
        assert(!this.controllerManager.getLobby().contains("Player2"));
        assert(!this.controllerManager.getLobby().contains("Player3"));
        assert(this.controllerManager.getControllers().size() == 1);
        assert(this.controllerManager.getLobby().isEmpty());
    }
}
