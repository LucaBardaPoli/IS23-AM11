package it.polimi.ingsw.model;

import org.junit.Test;

/**
 * Testing of Bag class
 */
public class BagTest {
    private final Bag bag;

    public BagTest() {
        this.bag = new Bag();
    }

    /**
     * Tests the correct pick (randomly) of a card from the bag
     */
    @Test
    public void testCardCreation() {
        for(int i = 0 ; i < 100; i++) {
            bag.pickTile();
        }

        System.out.println("Count BLUE: " + bag.getBag().get(Tile.BLUE));
        System.out.println("Count LBLUE: " + bag.getBag().get(Tile.LBLUE));
        System.out.println("Count GREEN: " + bag.getBag().get(Tile.GREEN));
        System.out.println("Count YELLOW: " + bag.getBag().get(Tile.YELLOW));
        System.out.println("Count PINK: " + bag.getBag().get(Tile.PINK));
        System.out.println("Count WHITE: " + bag.getBag().get(Tile.WHITE));
    }
}

