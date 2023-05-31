package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;
import org.junit.Test;

import java.util.List;

/**
 * Testing of SameKindSquare class (common goal)
 */
public class SameKindSquareGoalTest {
    private final CommonGoal goal;

    /**
     * Create the test case
     */
    public SameKindSquareGoalTest() {
        this.goal = new CommonGoal(1, "", new SameKindSquareGoal());
    }

    /**
     * Test to detect two groups of cards shaped as a square in a corner of the bookshelf
     */
    @Test
    public void testDetectSquareInACorner() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE), 0);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE), 1);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.PINK), 0);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.PINK), 1);
        assert(this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to detect two groups of cards shaped as a square in the middle of the bookshelf
     */
    @Test
    public void testDetectSquareInTheMiddle() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.YELLOW), 1);
        bookshelf.addTiles(List.of(Tile.YELLOW), 1);
        bookshelf.addTiles(List.of(Tile.LBLUE, Tile.LBLUE, Tile.YELLOW), 2);
        bookshelf.addTiles(List.of(Tile.YELLOW), 2);
        bookshelf.addTiles(List.of(Tile.WHITE, Tile.LBLUE, Tile.PINK), 3);
        bookshelf.addTiles(List.of(Tile.WHITE, Tile.WHITE), 3);
        bookshelf.addTiles(List.of(Tile.WHITE, Tile.WHITE, Tile.WHITE), 4);
        bookshelf.addTiles(List.of(Tile.WHITE, Tile.WHITE), 4);
        assert(this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to detect two groups of cards shaped as a square in opposite corners of the bookshelf
     */
    @Test
    public void testDetectSquareOppositeCorners() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE), 0);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE), 1);
        bookshelf.addTiles(List.of(Tile.WHITE, Tile.LBLUE, Tile.PINK), 3);
        bookshelf.addTiles(List.of(Tile.WHITE, Tile.WHITE, Tile.WHITE), 3);
        bookshelf.addTiles(List.of(Tile.WHITE, Tile.WHITE, Tile.WHITE), 4);
        bookshelf.addTiles(List.of(Tile.WHITE, Tile.WHITE, Tile.WHITE), 4);
        assert(this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to detect two groups of cards shaped as a square but potentially overlapping in the bookshelf
     */
    @Test
    public void testDetectSquareOverlapping() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.LBLUE, Tile.WHITE), 2);
        bookshelf.addTiles(List.of(Tile.WHITE), 2);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.WHITE), 3);
        bookshelf.addTiles(List.of(Tile.WHITE, Tile.WHITE, Tile.WHITE), 3);
        bookshelf.addTiles(List.of(Tile.LBLUE, Tile.BLUE, Tile.WHITE), 4);
        bookshelf.addTiles(List.of(Tile.WHITE, Tile.WHITE, Tile.WHITE), 4);
        assert(this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to check if detects just one group of cards shaped as a square
     */
    @Test
    public void testDetectJustOneSquare() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE), 0);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE), 1);
        assert(!this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to check if it correctly detects a group of cards shaped as a square
     */
    @Test
    public void testAlmostOneSquare1() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE), 0);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE), 1);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE), 0);
        assert(!this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to check if it correctly detects a group of cards shaped as a square
     */
    @Test
    public void testAlmostOneSquare2() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE), 0);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE), 1);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE), 0);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.PINK, Tile.YELLOW, Tile.YELLOW), 1);
        assert(!this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to check if detects two groups of cards almost shaped as a square (except for a corner)
     */
    @Test
    public void testDetectWrongShape() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE), 0);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.WHITE), 1);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.PINK), 0);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.PINK), 1);
        assert(!this.goal.checkGoal(bookshelf));
    }
}
