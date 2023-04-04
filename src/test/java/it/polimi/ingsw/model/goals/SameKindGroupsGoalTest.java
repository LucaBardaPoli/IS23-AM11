package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.CardType;
import it.polimi.ingsw.model.CommonGoal;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class SameKindGroupsGoalTest extends TestCase {
    public SameKindGroupsGoalTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite( SameKindGroupsGoalTest.class );
    }

    /**
     * tests an empty bookshelf
     */
    public void testEmptyBookshelf(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal sameKindGroups;

        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(0, 0));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(0, 1));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(1, 0));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(-1, 3));
        // when min_groups <= 0 the goal is fulfilled for any bookshelf
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        // every empty cell counts as a group of size 0
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(Bookshelf.getRows() * Bookshelf.getColumns(), 0));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(Bookshelf.getRows() * Bookshelf.getColumns(), -1));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(Bookshelf.getRows() * Bookshelf.getColumns() + 1, 0));
        assertFalse(sameKindGroups.checkGoal(bookshelf));

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

    /**
     * tests detection of big groups (4-7 tiles)
     */
    public void testBigGroups(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal sameKindGroups;

        bookshelf.addCells(List.of(CardType.WHITE, CardType.WHITE, CardType.GREEN), 0);
        bookshelf.addCells(List.of(CardType.YELLOW, CardType.WHITE), 1);
        bookshelf.addCells(List.of(CardType.YELLOW, CardType.WHITE), 2);

        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(1, 4));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(2, 4));
        assertFalse(sameKindGroups.checkGoal(bookshelf));

        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.BLUE), 3);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE), 2);

        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(2, 4));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(1, 5));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(2, 5));
        assertFalse(sameKindGroups.checkGoal(bookshelf));

        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE), 2);
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(1, 6));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(2, 6));
        assertFalse(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(1, 7));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(2, 7));
        assertFalse(sameKindGroups.checkGoal(bookshelf));

        bookshelf.addCells(List.of(CardType.WHITE), 0);
        bookshelf.addCells(List.of(CardType.WHITE, CardType.WHITE), 1);
        assertTrue(sameKindGroups.checkGoal(bookshelf));
    }

    /**
     * tests a full bookshelf
     */
    public void testFullBookshelf(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal sameKindGroups;

        bookshelf.addCells(List.of(CardType.LBLUE, CardType.BLUE, CardType.BLUE, CardType.LBLUE, CardType.PINK, CardType.BLUE), 0);
        bookshelf.addCells(List.of(CardType.YELLOW, CardType.WHITE, CardType.LBLUE, CardType.LBLUE, CardType.WHITE, CardType.WHITE), 1);
        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.BLUE, CardType.WHITE, CardType.WHITE, CardType.BLUE), 2);
        bookshelf.addCells(List.of(CardType.WHITE, CardType.LBLUE, CardType.BLUE, CardType.BLUE, CardType.GREEN, CardType.BLUE), 3);
        bookshelf.addCells(List.of(CardType.PINK, CardType.PINK, CardType.PINK, CardType.BLUE, CardType.WHITE, CardType.YELLOW), 4);

        // there are 16 groups of size >= 1
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(16, 1));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(17, 1));
        assertFalse(sameKindGroups.checkGoal(bookshelf));

        // there are 6 groups of size >= 2
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(6, 2));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(7, 2));
        assertFalse(sameKindGroups.checkGoal(bookshelf));

        // there are 4 groups of size >= 3
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(4, 3));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(5, 3));
        assertFalse(sameKindGroups.checkGoal(bookshelf));

        // there are 2 groups of size >= 4
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(2, 4));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(3, 4));
        assertFalse(sameKindGroups.checkGoal(bookshelf));

        // there is 1 group of size >= 5
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(1, 5));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(2, 5));
        assertFalse(sameKindGroups.checkGoal(bookshelf));

        // there is 1 group of size >= 6
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(1, 6));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(2, 6));
        assertFalse(sameKindGroups.checkGoal(bookshelf));

        // there are 0 group of size >= 7
        sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(1, 7));
        assertFalse(sameKindGroups.checkGoal(bookshelf));
    }

    /**
     * tests the following properties:
     * - if the goal is not fulfilled for a certain number of minimum groups it should not be fulfilled for any other greater number of minimun groups
     * - if the goal is fulfilled for a certain number of minimum groups it should be fulfilled for any other lower number of minimum groups
     */
    public void testMinGroups(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal sameKindGroups;
        Random rand = new Random();
        int minGroups, minSize, i , j;

        for(i = 0; i < Bookshelf.getColumns(); i++){
            for(j = 0; j < Bookshelf.getRows(); j++){
                bookshelf.addCells(List.of(CardType.values()[Math.abs(rand.nextInt()) % CardType.values().length]), i);
            }
        }

        for(i = 0; i < 100; i++){
            minGroups = Math.abs(rand.nextInt()) % 11;
            minSize = Math.abs(rand.nextInt()) % 8;
            sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(minGroups, minSize));

            if(sameKindGroups.checkGoal(bookshelf)){
                while(minGroups > 0){
                    minGroups--;
                    assertTrue(sameKindGroups.checkGoal(bookshelf));
                }
            } else {
                while(minGroups < 10){
                    minGroups++;
                    assertFalse(sameKindGroups.checkGoal(bookshelf));
                }
            }
        }
    }

    /**
     * tests the following properties:
     * - if the goal is not fulfilled for a certain number of minimum size it should not be fulfilled for any other greater number of minimun size
     * - if the goal is fulfilled for a certain number of minimum size it should be fulfilled for any other lower number of minimum size
     */
    public void testMinSize(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal sameKindGroups;
        Random rand = new Random();
        int minGroups, minSize, i, j;

        for(i = 0; i < Bookshelf.getColumns(); i++){
            for(j = 0; j < Bookshelf.getRows(); j++){
                bookshelf.addCells(List.of(CardType.values()[Math.abs(rand.nextInt()) % CardType.values().length]), i);
            }
        }

        for(i = 0; i < 100; i++) {
            minGroups = Math.abs(rand.nextInt()) % 11;
            minSize = Math.abs(rand.nextInt()) % 8;
            sameKindGroups = new CommonGoal("same kind groups", new SameKindGroupsGoal(minGroups, minSize));

            if (sameKindGroups.checkGoal(bookshelf)) {
                while (minSize > 0) {
                    minSize--;
                    assertTrue(sameKindGroups.checkGoal(bookshelf));
                }
            } else {
                while (minSize < 10) {
                    minSize++;
                    assertFalse(sameKindGroups.checkGoal(bookshelf));
                }
            }
        }
    }

}
