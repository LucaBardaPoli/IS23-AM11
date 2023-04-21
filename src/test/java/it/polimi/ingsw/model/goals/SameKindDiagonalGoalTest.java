package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;
import org.junit.Test;

import java.util.List;

/**
 * Testing of SameKindDiagonal class (common goal)
 */
public class SameKindDiagonalGoalTest {
    private CommonGoal goal;

    /**
     * Create the test case
     */
    public SameKindDiagonalGoalTest() {
        this.goal = new CommonGoal("", new SameKindDiagonalGoal());
    }

    @Test
    /**
     * Test to detect the first of the two possible diagonals in the bookshelf
     */
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

    @Test
    /**
     * Test to detect the second of the two possible diagonals in the bookshelf
     */
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

    @Test
    /**
     * Test to detect the first of the two possible anti-diagonals in the bookshelf
     */
    public void testDetectFirstAntidiagonal() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.YELLOW), 0);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.YELLOW), 1);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.YELLOW), 2);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.YELLOW), 3);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.YELLOW), 4);
        assert(this.goal.checkGoal(bookshelf));
    }

    @Test
    /**
     * Test to detect the second of the two possible anti-diagonals in the bookshelf
     */
    public void testDetectSecondAntidiagonal() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.YELLOW), 0);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.YELLOW), 1);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.YELLOW), 2);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.YELLOW), 3);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.YELLOW), 4);
        assert(this.goal.checkGoal(bookshelf));
    }

    @Test
    /**
     * Test to check if detects a group of cards almost shaped as a diagonal
     */
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

    @Test
    /**
     * Test to check if detects a group of cards almost shaped as an anti-diagonal
     */
    public void testDetectWrongShapeAntidiagonal() {
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
