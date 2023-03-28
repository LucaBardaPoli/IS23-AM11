package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * Testing of SameKindX class (common goal)
 */
public class SameKindXTest extends TestCase {
    CommonGoal goal;

    /**
     * Create the test case
     * @param testName name of the test case
     */
    public SameKindXTest( String testName ) {
        super( testName );
        this.goal = new CommonGoal("", new SameKindX());
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SameKindXTest.class );
    }

    /**
     * Test to detect a group of cards shaped as an X in a corner of the bookshelf
     */
    public void testDetectXInACorner() {
        Bookshelf bookshelf  = new Bookshelf(6, 5);
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addCells(List.of(CardType.BLUE, CardType.WHITE, CardType.BLUE), 0);
        bookshelf.addCells(List.of(CardType.WHITE, CardType.BLUE, CardType.WHITE), 1);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.WHITE, CardType.BLUE), 2);
        assert(this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to detect a group of cards shaped as an X in the middle of the bookshelf
     */
    public void testDetectXInTheMiddle() {
        Bookshelf bookshelf  = new Bookshelf(6, 5);
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addCells(List.of(CardType.WHITE, CardType.WHITE, CardType.BLUE), 1);
        bookshelf.addCells(List.of(CardType.WHITE, CardType.BLUE), 1);
        bookshelf.addCells(List.of(CardType.WHITE, CardType.WHITE, CardType.WHITE), 2);
        bookshelf.addCells(List.of(CardType.BLUE), 2);
        bookshelf.addCells(List.of(CardType.WHITE, CardType.WHITE, CardType.BLUE), 3);
        bookshelf.addCells(List.of(CardType.WHITE, CardType.BLUE), 3);
        assert(this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to check if detects a group of cards almost shaped as an X (except for a corner)
     */
    public void testDetectWrongShape() {
        Bookshelf bookshelf  = new Bookshelf(6, 5);
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addCells(List.of(CardType.BLUE, CardType.WHITE, CardType.BLUE), 0);
        bookshelf.addCells(List.of(CardType.WHITE, CardType.BLUE, CardType.WHITE), 1);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.WHITE, CardType.GREEN), 2);
        assert(!this.goal.checkGoal(bookshelf));
    }
}
