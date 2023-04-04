package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.CardType;
import it.polimi.ingsw.model.CommonGoal;
import it.polimi.ingsw.utility.BookshelfBuilder;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;

public class SameKindNGoalTest extends TestCase {
    public SameKindNGoalTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite( SameKindNGoalTest.class );
    }

    public void testEmptyBookshelf(){
        Bookshelf bookshelf = new Bookshelf();

        CommonGoal sameKindGoal;

        for(int i = 0; i < Bookshelf.getRows() * Bookshelf.getColumns(); i++){
            sameKindGoal = new CommonGoal("same kind goal", new SameKindNGoal(i));
            assertFalse(sameKindGoal.checkGoal(bookshelf));
        }
    }

    public void testFullBookshelf(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal sameKindNGoal;
        int i;

        bookshelf.addCells(List.of(CardType.WHITE, CardType.GREEN, CardType.PINK, CardType.PINK, CardType.YELLOW, CardType.LBLUE), 0);
        bookshelf.addCells(List.of(CardType.LBLUE, CardType.GREEN, CardType.PINK, CardType.WHITE, CardType.LBLUE, CardType.GREEN), 1);
        bookshelf.addCells(List.of(CardType.PINK, CardType.BLUE, CardType.LBLUE, CardType.YELLOW, CardType.BLUE, CardType.YELLOW), 2);
        bookshelf.addCells(List.of(CardType.WHITE, CardType.YELLOW, CardType.BLUE, CardType.WHITE, CardType.LBLUE, CardType.WHITE), 3);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.LBLUE, CardType.BLUE, CardType.GREEN, CardType.WHITE, CardType.GREEN), 4);

        // there are 4 PINK tiles, 4 YELLOW tiles, 5 WHITE tiles, 5 GREEN tiles, 5 BLUE tiles, 6 LBLUE tiles
        for(i = 0; i <= 6; i++){
            sameKindNGoal = new CommonGoal("same kind n goal", new SameKindNGoal(i));
            assertTrue(sameKindNGoal.checkGoal(bookshelf));
        }

        for(i = 7; i <= 20; i++){
            sameKindNGoal = new CommonGoal("same kind n goal", new SameKindNGoal(i));
            assertFalse(sameKindNGoal.checkGoal(bookshelf));
        }
    }

    public void testHalfFullBookshelf(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal sameKindNGoal;
        int i;

        bookshelf.addCells(List.of(CardType.WHITE, CardType.GREEN, CardType.PINK, CardType.PINK), 0);
        bookshelf.addCells(List.of(CardType.LBLUE, CardType.GREEN, CardType.PINK, CardType.WHITE, CardType.LBLUE), 1);
        bookshelf.addCells(List.of(CardType.PINK, CardType.BLUE, CardType.LBLUE, CardType.YELLOW, CardType.BLUE, CardType.YELLOW), 2);
        bookshelf.addCells(List.of(CardType.WHITE), 3);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.LBLUE), 4);

        // there are 2 YELLOW tiles, 2 GREEN tiles, 3 WHITE tiles, 3 BLUE tiles, 3 LBLUE tiles, 4 PINK tiles
        for(i = 0; i <= 4; i++){
            sameKindNGoal = new CommonGoal("same kind n goal", new SameKindNGoal(i));
            assertTrue(sameKindNGoal.checkGoal(bookshelf));
        }

        for(i = 5; i <= 20; i++){
            sameKindNGoal = new CommonGoal("same kind n goal", new SameKindNGoal(i));
            assertFalse(sameKindNGoal.checkGoal(bookshelf));
        }
    }
}
