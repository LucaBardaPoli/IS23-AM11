package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.GameSettings;
import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.model.CommonGoal;
import it.polimi.ingsw.utility.BookshelfBuilder;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SameKindGroupsGoalTest {

    /**
     * tests an empty bookshelf
     */
    @Test
    public void testEmptyBookshelf(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal sameKindGroups;

        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(0, 0));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(0, 1));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(1, 0));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(-1, 3));
        // when min_groups <= 0 the goal is fulfilled for any bookshelf
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        // every empty cell counts as a group of size 0
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(GameSettings.ROWS * GameSettings.COLUMNS, 0));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(GameSettings.ROWS * GameSettings.COLUMNS, -1));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(GameSettings.ROWS * GameSettings.COLUMNS + 1, 0));
        assertFalse(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(1, 1));
        assertFalse(sameKindGroups.checkGoal(bookshelf));
    }

    /**
     * tests detection of small groups (1-3 tiles)
     * */
    @Test
    public void testSmallGroups(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal sameKindGroups;

        bookshelf.addTiles(List.of(Tile.BLUE), 0);
        bookshelf.addTiles(List.of(Tile.YELLOW, Tile.LBLUE), 1);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.PINK), 2);

        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(0, 0));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(0, 1));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(1, 0));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(1, 2));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(2, 2));
        assertFalse(sameKindGroups.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.BLUE), 0);
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(5, 1));
        assertFalse(sameKindGroups.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.BLUE), 2);
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(6, 1));
        assertFalse(sameKindGroups.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.BLUE), 1);
        assertFalse(sameKindGroups.checkGoal(bookshelf));
        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE), 0);
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(4, 2));
        assertFalse(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(3, 2));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.WHITE, Tile.WHITE), 0);
        bookshelf.addTiles(List.of(Tile.YELLOW, Tile.GREEN, Tile.GREEN), 3);
        bookshelf.addTiles(List.of(Tile.YELLOW, Tile.YELLOW), 4);
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(5, 2));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(6, 2));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(7, 2));
        assertFalse(sameKindGroups.checkGoal(bookshelf));
    }

    /**
     * tests detection of big groups (4-7 tiles)
     */
    @Test
    public void testBigGroups(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal sameKindGroups;

        bookshelf.addTiles(List.of(Tile.WHITE, Tile.WHITE, Tile.GREEN), 0);
        bookshelf.addTiles(List.of(Tile.YELLOW, Tile.WHITE), 1);
        bookshelf.addTiles(List.of(Tile.YELLOW, Tile.WHITE), 2);

        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(1, 4));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(2, 4));
        assertFalse(sameKindGroups.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE), 3);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE), 2);

        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(2, 4));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(1, 5));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(2, 5));
        assertFalse(sameKindGroups.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE), 2);
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(1, 6));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(2, 6));
        assertFalse(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(1, 7));
        assertTrue(sameKindGroups.checkGoal(bookshelf));

        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(2, 7));
        assertFalse(sameKindGroups.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.WHITE), 0);
        bookshelf.addTiles(List.of(Tile.WHITE, Tile.WHITE), 1);
        assertTrue(sameKindGroups.checkGoal(bookshelf));
    }

    /**
     * tests a full bookshelf
     */
    @Test
    public void testFullBookshelf(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal sameKindGroups;

        bookshelf.addTiles(List.of(Tile.LBLUE, Tile.BLUE, Tile.BLUE, Tile.LBLUE, Tile.PINK, Tile.BLUE), 0);
        bookshelf.addTiles(List.of(Tile.YELLOW, Tile.WHITE, Tile.LBLUE, Tile.LBLUE, Tile.WHITE, Tile.WHITE), 1);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.WHITE, Tile.WHITE, Tile.BLUE), 2);
        bookshelf.addTiles(List.of(Tile.WHITE, Tile.LBLUE, Tile.BLUE, Tile.BLUE, Tile.GREEN, Tile.BLUE), 3);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.PINK, Tile.PINK, Tile.BLUE, Tile.WHITE, Tile.YELLOW), 4);

        // there are 16 groups of size >= 1
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(16, 1));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(17, 1));
        assertFalse(sameKindGroups.checkGoal(bookshelf));

        // there are 6 groups of size >= 2
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(6, 2));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(7, 2));
        assertFalse(sameKindGroups.checkGoal(bookshelf));

        // there are 4 groups of size >= 3
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(4, 3));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(5, 3));
        assertFalse(sameKindGroups.checkGoal(bookshelf));

        // there are 2 groups of size >= 4
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(2, 4));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(3, 4));
        assertFalse(sameKindGroups.checkGoal(bookshelf));

        // there is 1 group of size >= 5
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(1, 5));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(2, 5));
        assertFalse(sameKindGroups.checkGoal(bookshelf));

        // there is 1 group of size >= 6
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(1, 6));
        assertTrue(sameKindGroups.checkGoal(bookshelf));
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(2, 6));
        assertFalse(sameKindGroups.checkGoal(bookshelf));

        // there are 0 group of size >= 7
        sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(1, 7));
        assertFalse(sameKindGroups.checkGoal(bookshelf));
    }

    /**
     * tests the following properties:
     * - if the goal is not fulfilled for a certain number of minimum groups it should not be fulfilled for any other greater number of minimum groups
     * - if the goal is fulfilled for a certain number of minimum groups it should be fulfilled for any other lower number of minimum groups
     */
    @Test
    public void testMinGroups(){
        Bookshelf bookshelf;
        CommonGoal sameKindGroups;
        Random rand = new Random();
        int minGroups, minSize;

        // for 1000 times generates a random full bookshelf and random minGroups and minSize in and tests if the properties hold
        for(int k = 0; k < 1000; k++){
            bookshelf = BookshelfBuilder.randomFullBookshelf();
            minGroups = Math.abs(rand.nextInt()) % (GameSettings.COLUMNS * GameSettings.ROWS + 1);
            minSize = Math.abs(rand.nextInt()) % (GameSettings.COLUMNS * GameSettings.ROWS + 1);
            sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(minGroups, minSize));

            if(sameKindGroups.checkGoal(bookshelf)){
                while(minGroups > 0){
                    minGroups--;
                    sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(minGroups, minSize));
                    assertTrue(sameKindGroups.checkGoal(bookshelf));
                }
            } else {
                while(minGroups < GameSettings.COLUMNS * GameSettings.ROWS + 1){
                    minGroups++;
                    sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(minGroups, minSize));
                    assertFalse(sameKindGroups.checkGoal(bookshelf));
                }
            }
        }
    }

    /**
     * tests the following properties:
     * - if the goal is not fulfilled for a certain value of minimum size it should not be fulfilled for any other greater value of minimum size
     * - if the goal is fulfilled for a certain value of minimum size it should be fulfilled for any other lower value of minimum size
     */
    @Test
    public void testMinSize(){
        Bookshelf bookshelf;
        CommonGoal sameKindGroups;
        Random rand = new Random();
        int minGroups, minSize;

        // for 1000 times generates a random full bookshelf and random minGroups and minSize in and tests if the properties hold
        for(int k = 0; k < 1000; k++) {
            bookshelf = BookshelfBuilder.randomFullBookshelf();
            minGroups = Math.abs(rand.nextInt()) % (GameSettings.COLUMNS * GameSettings.ROWS + 1);
            minSize = Math.abs(rand.nextInt()) % (GameSettings.COLUMNS * GameSettings.ROWS + 1);
            sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(minGroups, minSize));

            if (sameKindGroups.checkGoal(bookshelf)) {
                while (minSize > 0) {
                    minSize--;
                    sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(minGroups, minSize));
                    assertTrue(sameKindGroups.checkGoal(bookshelf));
                }
            } else {
                while (minSize <= GameSettings.COLUMNS * GameSettings.ROWS + 1) {
                    minSize++;
                    sameKindGroups = new CommonGoal(1, "same kind groups", new SameKindGroupsGoal(minGroups, minSize));
                    assertFalse(sameKindGroups.checkGoal(bookshelf));
                }
            }
        }
    }
}
