package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

/**
 * Testing of SameKindSquare class (common goal)
 */
public class SameKindSquareTest extends TestCase {
    CommonGoal goal;

    /**
     * Create the test case
     * @param testName name of the test case
     */
    public SameKindSquareTest( String testName ) {
        super( testName );
        this.goal = new CommonGoal("", new SameKindSquare());
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( SameKindSquareTest.class );
    }

    /**
     * Test to detect two groups of cards shaped as a square in a corner of the bookshelf
     */
    public void testDetectSquareInACorner() {
        Bookshelf bookshelf  = new Bookshelf(6, 5);
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE), 0);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE), 1);
        bookshelf.addCells(List.of(CardType.PINK, CardType.PINK), 0);
        bookshelf.addCells(List.of(CardType.PINK, CardType.PINK), 1);
        assert(this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to detect two groups of cards shaped as a square in the middle of the bookshelf
     */
    public void testDetectSquareInTheMiddle() {
        Bookshelf bookshelf  = new Bookshelf(6, 5);
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.YELLOW), 1);
        bookshelf.addCells(List.of(CardType.YELLOW), 1);
        bookshelf.addCells(List.of(CardType.LBLUE, CardType.LBLUE, CardType.YELLOW), 2);
        bookshelf.addCells(List.of(CardType.YELLOW), 2);
        bookshelf.addCells(List.of(CardType.WHITE, CardType.LBLUE, CardType.PINK), 3);
        bookshelf.addCells(List.of(CardType.WHITE, CardType.WHITE), 3);
        bookshelf.addCells(List.of(CardType.WHITE, CardType.WHITE, CardType.WHITE), 4);
        bookshelf.addCells(List.of(CardType.WHITE, CardType.WHITE), 4);
        assert(this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to detect two groups of cards shaped as a square in opposite corners of the bookshelf
     */
    public void testDetectSquareOppositeCorners() {
        Bookshelf bookshelf  = new Bookshelf(6, 5);
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE), 0);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE), 1);
        bookshelf.addCells(List.of(CardType.WHITE, CardType.LBLUE, CardType.PINK), 3);
        bookshelf.addCells(List.of(CardType.WHITE, CardType.WHITE, CardType.WHITE), 3);
        bookshelf.addCells(List.of(CardType.WHITE, CardType.WHITE, CardType.WHITE), 4);
        bookshelf.addCells(List.of(CardType.WHITE, CardType.WHITE, CardType.WHITE), 4);
        assert(this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to detect two groups of cards shaped as a square but potentially overlapping in the bookshelf
     */
    public void testDetectSquareOverlapping() {
        Bookshelf bookshelf  = new Bookshelf(6, 5);
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addCells(List.of(CardType.BLUE, CardType.LBLUE, CardType.WHITE), 2);
        bookshelf.addCells(List.of(CardType.WHITE), 2);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.WHITE), 3);
        bookshelf.addCells(List.of(CardType.WHITE, CardType.WHITE, CardType.WHITE), 3);
        bookshelf.addCells(List.of(CardType.LBLUE, CardType.BLUE, CardType.WHITE), 4);
        bookshelf.addCells(List.of(CardType.WHITE, CardType.WHITE, CardType.WHITE), 4);
        assert(this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to check if detects just one group of cards shaped as a square
     */
    public void testDetectJustOneSquare() {
        Bookshelf bookshelf  = new Bookshelf(6, 5);
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE), 0);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE), 1);
        assert(!this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to check if detects two groups of cards almost shaped as a square (except for a corner)
     */
    public void testDetectWrongShape() {
        Bookshelf bookshelf  = new Bookshelf(6, 5);
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE), 0);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.WHITE), 1);
        bookshelf.addCells(List.of(CardType.PINK, CardType.PINK), 0);
        bookshelf.addCells(List.of(CardType.PINK, CardType.PINK), 1);
        assert(!this.goal.checkGoal(bookshelf));
    }
}
