package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import java.util.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Testing of GameControllerInterfaceView class
 */
public class GameControllerInterfaceViewTest extends TestCase {
    GameControllerInterfaceView gameController;

    /**
     * Create the test case
     * @param testName name of the test case
     */
    public GameControllerInterfaceViewTest(String testName) {
        super(testName);
        ControllerManager controllerManager = ControllerManager.getInstance();
        controllerManager.addPlayer("Player1", 3);
        controllerManager.addPlayer("Player2", 0);
        controllerManager.addPlayer("Player3", 0);
        this.gameController = controllerManager.getControllers().get(0);
    }

    public void test() {
        List<Player> players = this.gameController.getPlayers();
        Optional<CardType> r = this.gameController.pickCard(players.get(0), new Position(0,0));
        assert(r.isPresent());
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(GameControllerInterfaceViewTest.class);
    }
}
