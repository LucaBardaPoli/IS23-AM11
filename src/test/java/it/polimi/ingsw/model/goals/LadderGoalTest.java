package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.model.CommonGoal;
import it.polimi.ingsw.utility.BookshelfBuilder;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LadderGoalTest {
    @Test
    public void testEmptyBookshelf(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal ladderGoal = new CommonGoal(1, "ladder goal", new LadderGoal());

        assertFalse(ladderGoal.checkGoal(bookshelf));
    }

    @Test
    public void testRandFullBookshelf(){
        Bookshelf bookshelf = BookshelfBuilder.randomFullBookshelf();
        CommonGoal ladderGoal = new CommonGoal(1, "ladder goal", new LadderGoal());

        assertFalse(ladderGoal.checkGoal(bookshelf));
    }

    @Test
    public void testLadderDescending1(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal ladderGoal = new CommonGoal(1, "ladder goal", new LadderGoal());

        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE, Tile.LBLUE, Tile.YELLOW, Tile.BLUE, Tile.YELLOW), 0);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE, Tile.LBLUE, Tile.YELLOW, Tile.BLUE), 1);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE, Tile.LBLUE, Tile.YELLOW), 2);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE, Tile.LBLUE), 3);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE), 4);

        assertTrue(ladderGoal.checkGoal(bookshelf));
    }

    @Test
    public void testLadderDescending2(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal ladderGoal = new CommonGoal(1, "ladder goal", new LadderGoal());

        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE, Tile.LBLUE, Tile.YELLOW, Tile.BLUE), 0);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE, Tile.LBLUE, Tile.YELLOW), 1);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE, Tile.LBLUE), 2);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE), 3);
        bookshelf.addTiles(List.of(Tile.PINK), 4);

        assertTrue(ladderGoal.checkGoal(bookshelf));
    }

    @Test
    public void testLadderAscending1(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal ladderGoal = new CommonGoal(1, "ladder goal", new LadderGoal());

        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE), 0);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE, Tile.LBLUE), 1);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE, Tile.LBLUE, Tile.YELLOW), 2);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE, Tile.LBLUE, Tile.YELLOW, Tile.BLUE), 3);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE, Tile.LBLUE, Tile.YELLOW, Tile.BLUE, Tile.YELLOW), 4);

        assertTrue(ladderGoal.checkGoal(bookshelf));
    }

    @Test
    public void testLadderAscending2(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal ladderGoal = new CommonGoal(1, "ladder goal", new LadderGoal());

        bookshelf.addTiles(List.of(Tile.PINK), 0);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE), 1);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE, Tile.LBLUE), 2);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE, Tile.LBLUE, Tile.YELLOW), 3);
        bookshelf.addTiles(List.of(Tile.PINK, Tile.BLUE, Tile.LBLUE, Tile.YELLOW, Tile.BLUE), 4);

        assertTrue(ladderGoal.checkGoal(bookshelf));
    }
}
