package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SameKindNGoalTest {

    @Test
    public void testEmptyBookshelf(){
        Bookshelf bookshelf = new Bookshelf();

        CommonGoal sameKindGoal;

        for(int i = 0; i < GameSettings.ROWS * GameSettings.COLUMNS; i++){
            sameKindGoal = new CommonGoal(1, "same kind goal", new SameKindNGoal(i));
            assertFalse(sameKindGoal.checkGoal(bookshelf));
        }
    }

    @Test
    public void testFullBookshelf(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal sameKindNGoal;
        int i;

        bookshelf.addTiles(List.of(Tile.WHITE, Tile.GREEN, Tile.PINK, Tile.PINK, Tile.YELLOW, Tile.LBLUE), 0);
        bookshelf.addTiles(List.of(Tile.LBLUE, Tile.GREEN, Tile.PINK, Tile.WHITE, Tile.LBLUE, Tile.GREEN), 1);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE, Tile.LBLUE, Tile.YELLOW, Tile.BLUE, Tile.YELLOW), 2);
        bookshelf.addTiles(List.of(Tile.WHITE, Tile.YELLOW, Tile.BLUE, Tile.WHITE, Tile.LBLUE, Tile.WHITE), 3);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.LBLUE, Tile.BLUE, Tile.GREEN, Tile.WHITE, Tile.GREEN), 4);

        // there are 4 PINK tiles, 4 YELLOW tiles, 5 WHITE tiles, 5 GREEN tiles, 5 BLUE tiles, 6 LBLUE tiles
        for(i = 0; i <= 6; i++){
            sameKindNGoal = new CommonGoal(1, "same kind n goal", new SameKindNGoal(i));
            assertTrue(sameKindNGoal.checkGoal(bookshelf));
        }

        for(i = 7; i <= 20; i++){
            sameKindNGoal = new CommonGoal(1, "same kind n goal", new SameKindNGoal(i));
            assertFalse(sameKindNGoal.checkGoal(bookshelf));
        }
    }

    @Test
    public void testHalfFullBookshelf(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal sameKindNGoal;
        int i;

        bookshelf.addTiles(List.of(Tile.WHITE, Tile.GREEN, Tile.PINK, Tile.PINK), 0);
        bookshelf.addTiles(List.of(Tile.LBLUE, Tile.GREEN, Tile.PINK, Tile.WHITE, Tile.LBLUE), 1);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE, Tile.LBLUE, Tile.YELLOW, Tile.BLUE, Tile.YELLOW), 2);
        bookshelf.addTiles(List.of(Tile.WHITE), 3);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.LBLUE), 4);

        // there are 2 YELLOW tiles, 2 GREEN tiles, 3 WHITE tiles, 3 BLUE tiles, 3 LBLUE tiles, 4 PINK tiles
        for(i = 0; i <= 4; i++){
            sameKindNGoal = new CommonGoal(1, "same kind n goal", new SameKindNGoal(i));
            assertTrue(sameKindNGoal.checkGoal(bookshelf));
        }

        for(i = 5; i <= 20; i++){
            sameKindNGoal = new CommonGoal(1, "same kind n goal", new SameKindNGoal(i));
            assertFalse(sameKindNGoal.checkGoal(bookshelf));
        }
    }
}
