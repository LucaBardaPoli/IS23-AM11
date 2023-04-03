package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.CardType;
import it.polimi.ingsw.model.CommonGoal;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

public class SameKindGroupsGoalTest extends TestCase {
    public SameKindGroupsGoalTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite( SameKindGroupsGoalTest.class );
    }

    public void testEmptyBookshelf(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal sameKindGroups;

        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(0, 0));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(0, 1));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(1, 0));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(1, 1));
        assertFalse(sameKindGroups.checkGoal(bookshelf));
    }

    /**
     * tests detection of small groups (1-3 tiles)
     * */
    public void testSmallGroups(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal sameKindGroups;

        bookshelf.addCells(List.of(CardType.BLUE), 0);
        bookshelf.addCells(List.of(CardType.YELLOW, CardType.LBLUE), 1);
        bookshelf.addCells(List.of(CardType.PINK, CardType.PINK), 2);

        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(0, 0));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(0, 1));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(1, 0));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(1, 2));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(2, 2));
        assertFalse(sameKindGroups.checkGoal(bookshelf));
        bookshelf.addCells(List.of(CardType.BLUE), 0);
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(5, 1));
        assertFalse(sameKindGroups.checkGoal(bookshelf));
        bookshelf.addCells(List.of(CardType.BLUE), 2);
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(6, 1));
        assertFalse(sameKindGroups.checkGoal(bookshelf));
        bookshelf.addCells(List.of(CardType.BLUE), 1);
        assertFalse(sameKindGroups.checkGoal(bookshelf));
        bookshelf.addCells(List.of(CardType.PINK, CardType.BLUE), 0);
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(4, 2));
        assertFalse(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(3, 2));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        bookshelf.addCells(List.of(CardType.WHITE, CardType.WHITE), 0);
        bookshelf.addCells(List.of(CardType.YELLOW, CardType.GREEN, CardType.GREEN), 3);
        bookshelf.addCells(List.of(CardType.YELLOW, CardType.YELLOW), 4);
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(5, 2));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(6, 2));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(7, 2));
        assertFalse(sameKindGroups.checkGoal(bookshelf));
    }


}
