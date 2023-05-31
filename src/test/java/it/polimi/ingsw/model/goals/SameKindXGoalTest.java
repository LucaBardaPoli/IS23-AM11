package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;
import org.junit.Test;
import java.util.List;

/**
 * Testing of SameKindX class (common goal)
 */
public class SameKindXGoalTest {
    private final CommonGoal goal;

    /**
     * Create the test case
     */
    public SameKindXGoalTest() {
        this.goal = new CommonGoal(1, "", new SameKindXGoal());
    }

    /**
     * Test to detect a group of cards shaped as an X in a corner of the bookshelf
     */
    @Test
    public void testDetectXInACorner() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.WHITE, Tile.BLUE), 0);
        bookshelf.addTiles(List.of(Tile.WHITE, Tile.BLUE, Tile.WHITE), 1);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.WHITE, Tile.BLUE), 2);
        assert(this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to detect a group of cards shaped as an X in the middle of the bookshelf
     */
    @Test
    public void testDetectXInTheMiddle() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.WHITE, Tile.WHITE, Tile.BLUE), 1);
        bookshelf.addTiles(List.of(Tile.WHITE, Tile.BLUE), 1);
        bookshelf.addTiles(List.of(Tile.WHITE, Tile.WHITE, Tile.WHITE), 2);
        bookshelf.addTiles(List.of(Tile.BLUE), 2);
        bookshelf.addTiles(List.of(Tile.WHITE, Tile.WHITE, Tile.BLUE), 3);
        bookshelf.addTiles(List.of(Tile.WHITE, Tile.BLUE), 3);
        assert(this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to check if detects a group of cards almost shaped as an X (except for a corner)
     */
    @Test
    public void testDetectWrongShape() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.WHITE, Tile.BLUE), 0);
        bookshelf.addTiles(List.of(Tile.WHITE, Tile.BLUE, Tile.WHITE), 1);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.WHITE, Tile.GREEN), 2);
        assert(!this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to check if detects a group of cards almost shaped as an X (except for a corner)
     */
    @Test
    public void testAlmostAnX1() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.GREEN, Tile.WHITE, Tile.BLUE), 0);
        bookshelf.addTiles(List.of(Tile.WHITE, Tile.BLUE, Tile.WHITE), 1);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.WHITE, Tile.BLUE), 2);
        assert(!this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to check if detects a group of cards almost shaped as an X (except for a corner)
     */
    @Test
    public void testAlmostAnX2() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.WHITE, Tile.GREEN), 0);
        bookshelf.addTiles(List.of(Tile.WHITE, Tile.BLUE, Tile.WHITE), 1);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.WHITE, Tile.BLUE), 2);
        assert(!this.goal.checkGoal(bookshelf));
    }
}
