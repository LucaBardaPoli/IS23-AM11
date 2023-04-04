package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;
import org.junit.Test;
import java.util.List;

/**
 * Testing of SameKindX class (common goal)
 */
public class SameKindXGoalTest {
    private CommonGoal goal;

    /**
     * Create the test case
     */
    public SameKindXGoalTest() {
        this.goal = new CommonGoal("", new SameKindXGoal());
    }

    /**
     * Test to detect a group of cards shaped as an X in a corner of the bookshelf
     */
    @Test
    public void testDetectXInACorner() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addCells(List.of(CardType.BLUE, CardType.WHITE, CardType.BLUE), 0);
        bookshelf.addCells(List.of(CardType.WHITE, CardType.BLUE, CardType.WHITE), 1);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.WHITE, CardType.BLUE), 2);
        assert(this.goal.checkGoal(bookshelf));
    }

    /**
     * Test to detect a group of cards shaped as an X in the middle of the bookshelf
     */
    @Test
    public void testDetectXInTheMiddle() {
        Bookshelf bookshelf  = new Bookshelf();
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
    @Test
    public void testDetectWrongShape() {
        Bookshelf bookshelf  = new Bookshelf();
        assert(!this.goal.checkGoal(bookshelf));
        bookshelf.addCells(List.of(CardType.BLUE, CardType.WHITE, CardType.BLUE), 0);
        bookshelf.addCells(List.of(CardType.WHITE, CardType.BLUE, CardType.WHITE), 1);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.WHITE, CardType.GREEN), 2);
        assert(!this.goal.checkGoal(bookshelf));
    }
}
