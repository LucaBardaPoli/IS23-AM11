package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;
import org.junit.Test;

import java.util.List;

/**
 * Testing of SameKindDiagonal class (common goal)
 */
public class SameKindDiagonalGoalTest {
    private final CommonGoal goal;

    /**
     * Create the test case
     */
    public SameKindDiagonalGoalTest() {
        this.goal = new CommonGoal(1,"", new SameKindDiagonalGoal());
    }


    /**
     * Test to detect the first of the two possible diagonals in the bookshelf
     */
    @Test
    public void testDetectFirstDiagonal() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.YELLOW), 0);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.YELLOW), 1);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.YELLOW), 2);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.YELLOW), 3);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.YELLOW), 4);
        assert(this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to detect the second of the two possible diagonals in the bookshelf
     */
    @Test
    public void testDetectSecondDiagonal() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.WHITE), 0);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.WHITE), 1);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.WHITE), 2);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.WHITE), 3);
        bookshelf.addTiles(List.of(Tile.WHITE), 4);
        assert(this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to detect the first of the two possible anti-diagonals in the bookshelf
     */
    @Test
    public void testDetectFirstAntiDiagonal() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.YELLOW), 0);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.YELLOW), 1);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.YELLOW), 2);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.YELLOW), 3);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.YELLOW), 4);
        assert(this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to detect the second of the two possible anti-diagonals in the bookshelf
     */
    @Test
    public void testDetectSecondAntiDiagonal() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.YELLOW), 0);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.YELLOW), 1);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.YELLOW), 2);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.YELLOW), 3);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.YELLOW), 4);
        assert(this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to check if detects a group of cards almost shaped as a diagonal
     */
    @Test
    public void testDetectWrongShapeDiagonal() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.WHITE), 0);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.YELLOW), 1);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.WHITE), 2);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.WHITE), 3);
        bookshelf.addTiles(List.of(Tile.WHITE), 4);
        assert(!this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to check if detects a group of cards almost shaped as an anti-diagonal
     */
    @Test
    public void testDetectWrongShapeAntiDiagonal() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.YELLOW), 0);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.YELLOW), 1);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.YELLOW), 2);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.YELLOW), 3);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.PINK), 4);
        assert(!this.goal.checkGoal(bookshelf));
    }
}
