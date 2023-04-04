package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.List;

/**
 * Testing of SameKindDiagonal class (common goal)
 */
public class SameKindDiagonalGoalTest extends TestCase {
    CommonGoal goal;

    /**
     * Create the test case
     * @param testName name of the test case
     */
    public SameKindDiagonalGoalTest(String testName ) {
        super( testName );
        this.goal = new CommonGoal("", new SameKindDiagonalGoal());
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( SameKindDiagonalGoalTest.class );
    }

    /**
     * Test to detect the first of the two possible diagonals in the bookshelf
     */
    public void testDetectFirstDiagonal() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.YELLOW), 0);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.YELLOW), 1);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.YELLOW), 2);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.YELLOW), 3);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.YELLOW), 4);
        assert(this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to detect the second of the two possible diagonals in the bookshelf
     */
    public void testDetectSecondDiagonal() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.WHITE), 0);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.WHITE), 1);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.WHITE), 2);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.WHITE), 3);
        bookshelf.addCells(List.of(CardType.WHITE), 4);
        assert(this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to detect the first of the two possible anti-diagonals in the bookshelf
     */
    public void testDetectFirstAntidiagonal() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addCells(List.of(CardType.BLUE, CardType.YELLOW), 0);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.YELLOW), 1);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.YELLOW), 2);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.YELLOW), 3);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.YELLOW), 4);
        assert(this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to detect the second of the two possible anti-diagonals in the bookshelf
     */
    public void testDetectSecondAntidiagonal() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addCells(List.of(CardType.YELLOW), 0);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.YELLOW), 1);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.YELLOW), 2);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.YELLOW), 3);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.YELLOW), 4);
        assert(this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to check if detects a group of cards almost shaped as a diagonal
     */
    public void testDetectWrongShapeDiagonal() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.WHITE), 0);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.YELLOW), 1);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.WHITE), 2);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.WHITE), 3);
        bookshelf.addCells(List.of(CardType.WHITE), 4);
        assert(!this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to check if detects a group of cards almost shaped as an anti-diagonal
     */
    public void testDetectWrongShapeAntidiagonal() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addCells(List.of(CardType.YELLOW), 0);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.YELLOW), 1);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.YELLOW), 2);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.YELLOW), 3);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.PINK), 4);
        assert(!this.goal.checkGoal(bookshelf));
    }
}
