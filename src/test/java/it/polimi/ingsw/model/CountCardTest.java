package it.polimi.ingsw.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Testing of CountCards class
 */
public class CountCardTest extends TestCase {
    CountCards count;
    Board board;

    public CountCardTest( String testName ) {
        super( testName );
        this.count = new CountCards();
        this.board = new Board(4, count);
    }

    /**
     * Tests the correct pick (randomly) of a card from the bag
     */
    public void testCardCreation() {
        for(int i = 0 ; i < 100; i++) {
            count.pickCard();
        }

        System.out.println("Count BLUE: " + count.getCountCardType().get(CardType.BLUE));
        System.out.println("Count LBLUE: " + count.getCountCardType().get(CardType.LBLUE));
        System.out.println("Count GREEN: " + count.getCountCardType().get(CardType.GREEN));
        System.out.println("Count YELLOW: " + count.getCountCardType().get(CardType.YELLOW));
        System.out.println("Count PINK: " + count.getCountCardType().get(CardType.PINK));
        System.out.println("Count WHITE: " + count.getCountCardType().get(CardType.WHITE));
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( CountCardTest.class );
    }
}

