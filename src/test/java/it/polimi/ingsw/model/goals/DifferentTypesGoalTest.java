package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.utility.BookshelfBuilder;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DifferentTypesGoalTest {

    @Test
    public void testEmptyBookshelf(){
        Bookshelf bookshelf = new Bookshelf();

        CommonGoal fewDifferentColumns = new CommonGoal(1, "3 full columns with max 3 different types", new DifferentTypesGoal(1, 3, 3, CheckMode.VERTICAL));
        CommonGoal fewDifferentRows = new CommonGoal(1, "3 full rows with max 3 different types", new DifferentTypesGoal(1, 3, 3, CheckMode.HORIZONTAL));
        CommonGoal lotsDifferentColumns = new CommonGoal(1, "2 full columns with min 6 different types", new DifferentTypesGoal(6, GameSettings.ROWS, 2, CheckMode.VERTICAL));
        CommonGoal lotsDifferentRows = new CommonGoal(1, "2 full rows with min 5 different types", new DifferentTypesGoal(5, GameSettings.COLUMNS, 2, CheckMode.HORIZONTAL));

        assertFalse(fewDifferentColumns.checkGoal(bookshelf));
        assertFalse(fewDifferentRows.checkGoal(bookshelf));
        assertFalse(lotsDifferentColumns.checkGoal(bookshelf));
        assertFalse(lotsDifferentRows.checkGoal(bookshelf));
    }

    @Test
    public void testFewDifferentColumns(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal fewDifferentColumns = new CommonGoal(1, "3 full columns with max 3 different types", new DifferentTypesGoal(1, 3, 3, CheckMode.VERTICAL));

        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.PINK, Tile.PINK, Tile.YELLOW, Tile.YELLOW), 0);
        // 1 full columns with max 3 different types (1 full column total)
        assertFalse(fewDifferentColumns.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE, Tile.PINK, Tile.PINK, Tile.YELLOW, Tile.BLUE), 1);
        // 2 full columns with max 3 different types (2 full columns total)
        assertFalse(fewDifferentColumns.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.WHITE, Tile.WHITE, Tile.PINK, Tile.BLUE, Tile.LBLUE, Tile.YELLOW), 2);
        // still 2 full columns with max 3 different types (3 full columns total)
        assertFalse(fewDifferentColumns.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE, Tile.PINK, Tile.BLUE, Tile.YELLOW, Tile.BLUE), 3);
        // 3 full columns with max 3 different types (4 full columns total)
        assertTrue(fewDifferentColumns.checkGoal(bookshelf));
    }

    @Test
    public void testFewDifferentRows(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal fewDifferentRows = new CommonGoal(1, "3 full rows with max 3 different types", new DifferentTypesGoal(1, 3, 3, CheckMode.HORIZONTAL));

        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.PINK, Tile.PINK), 0);
        assertFalse(fewDifferentRows.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE, Tile.PINK, Tile.PINK, Tile.YELLOW, Tile.BLUE), 1);
        assertFalse(fewDifferentRows.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.WHITE, Tile.WHITE, Tile.PINK, Tile.BLUE, Tile.LBLUE, Tile.YELLOW), 2);
        assertFalse(fewDifferentRows.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.WHITE, Tile.LBLUE, Tile.PINK, Tile.LBLUE, Tile.WHITE, Tile.WHITE), 3);
        // so far no row is full since the last column is empty
        assertFalse(fewDifferentRows.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.PINK, Tile.BLUE, Tile.LBLUE, Tile.YELLOW), 4);
        // now the last column is added and there are 3 rows with at most 3 different types
        assertTrue(fewDifferentRows.checkGoal(bookshelf));
    }

    @Test
    public void testLotsDifferentColumns(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal lotsDifferentColumns = new CommonGoal(1, "2 columns with min 6 different types", new DifferentTypesGoal(6, GameSettings.ROWS, 2, CheckMode.VERTICAL));

        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.PINK, Tile.PINK), 0);
        // 0 full columns with min 6 different types (1 column total)
        assertFalse(lotsDifferentColumns.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE, Tile.LBLUE, Tile.WHITE, Tile.GREEN, Tile.YELLOW), 1);
        // 1 full column with min 6 different types (2 columns total)
        assertFalse(lotsDifferentColumns.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.WHITE, Tile.LBLUE, Tile.PINK, Tile.LBLUE, Tile.WHITE, Tile.WHITE), 3);
        // 1 full column with min 6 different types (3 columns total)
        assertFalse(lotsDifferentColumns.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.WHITE, Tile.PINK, Tile.GREEN, Tile.BLUE, Tile.LBLUE, Tile.YELLOW), 2);
        // 2 full columns with min 6 different types (4 columns total)
        assertTrue(lotsDifferentColumns.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.BLUE, Tile.WHITE, Tile.PINK, Tile.GREEN, Tile.LBLUE, Tile.YELLOW), 4);
        // 3 full columns with min 6 different types (5 columns total)
        assertTrue(lotsDifferentColumns.checkGoal(bookshelf));
    }

    @Test
    public void testLotsDifferentRows(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal lotsDifferentRows = new CommonGoal(1, "2 rows with min 5 different types", new DifferentTypesGoal(5, GameSettings.COLUMNS, 2, CheckMode.HORIZONTAL));

        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.PINK, Tile.PINK), 0);
        assertFalse(lotsDifferentRows.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE, Tile.LBLUE, Tile.WHITE, Tile.GREEN, Tile.YELLOW), 1);
        assertFalse(lotsDifferentRows.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.WHITE, Tile.LBLUE, Tile.WHITE, Tile.LBLUE, Tile.WHITE, Tile.WHITE), 2);
        assertFalse(lotsDifferentRows.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.YELLOW, Tile.PINK, Tile.GREEN, Tile.BLUE, Tile.LBLUE, Tile.YELLOW), 3);
        // so far no row is full since the last column is empty
        assertFalse(lotsDifferentRows.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.LBLUE, Tile.WHITE, Tile.YELLOW, Tile.GREEN, Tile.LBLUE, Tile.YELLOW), 4);
        // now the last column is added and there are 3 full rows with at least 5 different types
        assertTrue(lotsDifferentRows.checkGoal(bookshelf));
    }

    /**
     * tests the following properties:
     * - if the goal is not fulfilled for a certain number of minimum types it should not be fulfilled for any other greater number of minimum types
     * - if the goal is fulfilled for a certain number of minimum types it should be fulfilled for any other lower number of minimum types
     */
    @Test
    public void testMinTypes(){
        Bookshelf bookshelf;
        CommonGoal differentTypes;
        Random rand = new Random();
        int minTypes, maxTypes, minNum;
        int nrows = GameSettings.ROWS;
        int ncolumns = GameSettings.COLUMNS;
        CheckMode mode;

        // for 1000 times generates a random full bookshelf and random minGroups and minSize in and tests if the properties hold
        for(int k = 0; k < 1000; k++) {
            bookshelf = BookshelfBuilder.randomFullBookshelf();
            maxTypes = Tile.values().length;

            if(k % 2 == 0){
                mode = CheckMode.HORIZONTAL;
                minNum = Math.abs(rand.nextInt()) % (nrows + 1);
                minTypes = Math.abs(rand.nextInt()) % Math.min(Tile.values().length + 1, ncolumns + 1);
            } else {
                mode = CheckMode.VERTICAL;
                minNum = Math.abs(rand.nextInt()) % (ncolumns + 1);
                minTypes = Math.abs(rand.nextInt()) % Math.min(Tile.values().length + 1, nrows + 1);
            }
            differentTypes = new CommonGoal(1, "same kind groups", new DifferentTypesGoal(minTypes, maxTypes, minNum, mode));

            if (differentTypes.checkGoal(bookshelf)) {
                while (minTypes > 0) {
                    minTypes--;
                    differentTypes = new CommonGoal(1, "same kind groups", new DifferentTypesGoal(minTypes, maxTypes, minNum, mode));
                    assertTrue(differentTypes.checkGoal(bookshelf));
                }
            } else {
                while (minTypes <= Tile.values().length) {
                    minTypes++;
                    differentTypes = new CommonGoal(1, "same kind groups", new DifferentTypesGoal(minTypes, maxTypes, minNum, mode));
                    assertFalse(differentTypes.checkGoal(bookshelf));
                }
            }
        }
    }

    /**
     * tests the following properties:
     * - if the goal is not fulfilled for a certain number of maximum types it should not be fulfilled for any other lower number of maximum types
     * - if the goal is fulfilled for a certain number of maximum types it should be fulfilled for any other greater number of maximum types
     */
    @Test
    public void testMaxTypes(){
        Bookshelf bookshelf;
        CommonGoal differentTypes;
        Random rand = new Random();
        int minTypes, maxTypes, minNum;
        int nrows = GameSettings.ROWS;
        int ncolumns = GameSettings.COLUMNS;
        CheckMode mode;

        // for 1000 times generates a random full bookshelf and random minGroups and minSize in and tests if the properties hold
        for(int k = 0; k < 1000; k++) {
            bookshelf = BookshelfBuilder.randomFullBookshelf();
            minTypes = 0;

            if(k % 2 == 0){
                mode = CheckMode.HORIZONTAL;
                minNum = Math.abs(rand.nextInt()) % (nrows + 1);
                maxTypes = Math.abs(rand.nextInt()) % Math.min(Tile.values().length + 1, ncolumns + 1);
            } else {
                mode = CheckMode.VERTICAL;
                minNum = Math.abs(rand.nextInt()) % (ncolumns + 1);
                maxTypes = Math.abs(rand.nextInt()) % Math.min(Tile.values().length + 1, nrows + 1);
            }
            differentTypes = new CommonGoal(1, "same kind groups", new DifferentTypesGoal(minTypes, maxTypes, minNum, mode));

            if (differentTypes.checkGoal(bookshelf)) {
                while (maxTypes <= Tile.values().length) {
                    maxTypes++;
                    differentTypes = new CommonGoal(1, "same kind groups", new DifferentTypesGoal(minTypes, maxTypes, minNum, mode));
                    assertTrue(differentTypes.checkGoal(bookshelf));
                }
            } else {
                while (maxTypes > 0) {
                    maxTypes--;
                    differentTypes = new CommonGoal(1, "same kind groups", new DifferentTypesGoal(minTypes, maxTypes, minNum, mode));
                    assertFalse(differentTypes.checkGoal(bookshelf));
                }
            }
        }
    }


    /**
     * tests the following properties:
     * - if the goal is not fulfilled for a certain number of minimum rows/columns it should not be fulfilled for any other greater number of minimum rows/columns
     * - if the goal is fulfilled for a certain number of minimum rows/columns it should be fulfilled for any other lower number of minimum rows/columns
     */
    @Test
    public void testMinNum(){
        Bookshelf bookshelf;
        CommonGoal differentTypes;
        Random rand = new Random();
        int minTypes, maxTypes, minNum;
        int nrows = GameSettings.ROWS;
        int ncolumns = GameSettings.COLUMNS;
        CheckMode mode;

        // for 1000 times generates a random full bookshelf and random minGroups and minSize in and tests if the properties hold
        for(int k = 0; k < 1000; k++) {
            bookshelf = BookshelfBuilder.randomFullBookshelf();

            if(k % 2 == 0){
                mode = CheckMode.HORIZONTAL;
                minNum = Math.abs(rand.nextInt()) % (nrows + 1);
                maxTypes = Math.abs(rand.nextInt()) % Math.min(Tile.values().length + 1, nrows + 1);
                minTypes = Math.abs(rand.nextInt()) % (maxTypes + 1);
            } else {
                mode = CheckMode.VERTICAL;
                minNum = Math.abs(rand.nextInt()) % (ncolumns + 1);
                maxTypes = Math.abs(rand.nextInt()) % Math.min(Tile.values().length + 1, nrows + 1);
                minTypes = Math.abs(rand.nextInt()) % (maxTypes + 1);
            }
            differentTypes = new CommonGoal(1, "same kind groups", new DifferentTypesGoal(minTypes, maxTypes, minNum, mode));

            if (differentTypes.checkGoal(bookshelf)) {
                while (minNum > 0) {
                    minNum--;
                    differentTypes = new CommonGoal(1, "same kind groups", new DifferentTypesGoal(minTypes, maxTypes, minNum, mode));
                    assertTrue(differentTypes.checkGoal(bookshelf));
                }
            } else {
                while (minNum <= Math.max(nrows, ncolumns)) {
                    minNum++;
                    differentTypes = new CommonGoal(1, "same kind groups", new DifferentTypesGoal(minTypes, maxTypes, minNum, mode));
                    assertFalse(differentTypes.checkGoal(bookshelf));
                }
            }
        }
    }
}
