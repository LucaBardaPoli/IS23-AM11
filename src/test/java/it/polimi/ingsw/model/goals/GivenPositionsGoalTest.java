package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class GivenPositionsGoalTest {

    @Test
    public void testNoPositions(){
        Bookshelf bookshelf = new Bookshelf();
        List<Position> positions = new ArrayList<>();
        CommonGoal givenPositions;
        // list is empty ==> the goal is always fulfilled for any bookshelf (even if empty)

        givenPositions = new CommonGoal(1, "given positions", new GivenPositionsGoal(positions));
        // bookshelf is empty
        assertTrue(givenPositions.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.BLUE, Tile.PINK), 0);
        // bookshelf is not empty
        assertTrue(givenPositions.checkGoal(bookshelf));
    }

    @Test
    public void testEmptyBookshelf(){
        Bookshelf bookshelf = new Bookshelf();
        List<Position> positions = new ArrayList<>();
        CommonGoal givenPositions;
        // bookshelf is empty ==> the goal is never fulfilled for any non-empty list of positions

        givenPositions = new CommonGoal(1, "given positions", new GivenPositionsGoal(positions));
        // list is empty
        assertTrue(givenPositions.checkGoal(bookshelf));

        // add bottom left corner position to the given positions list
        positions.add(new Position(GameSettings.ROWS-1, GameSettings.COLUMNS-1));
        // all goals are immutable objects, so we have to instantiate a new one
        givenPositions = new CommonGoal(1, "given positions", new GivenPositionsGoal(positions));
        assertFalse(givenPositions.checkGoal(bookshelf));
    }

    @Test
    public void test1Position(){
        Bookshelf bookshelf = new Bookshelf();
        List<Position> positions = new ArrayList<>();
        CommonGoal givenPositions;

        positions.add(new Position(1, 0));
        positions.add(new Position(1, 2));
        positions.add(new Position(3, 0));

        givenPositions = new CommonGoal(1, "given positions", new GivenPositionsGoal(positions));

        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.GREEN, Tile.WHITE, Tile.GREEN), 0);
        // adds GREEN to position (3, 0) and position (1, 0)
        assertFalse(givenPositions.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.GREEN, Tile.WHITE), 1);
        // none of the required positions belong to column 1
        assertFalse(givenPositions.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.YELLOW, Tile.WHITE, Tile.GREEN), 2);
        // adds GREEN to position (1, 2)
        assertTrue(givenPositions.checkGoal(bookshelf));
    }

    @Test
    public void test2Position(){
        Bookshelf bookshelf = new Bookshelf();
        List<Position> positions = new ArrayList<>();
        CommonGoal givenPositions;

        positions.add(new Position(1, 0));
        positions.add(new Position(1, 2));
        positions.add(new Position(3, 0));

        givenPositions = new CommonGoal(1, "given positions", new GivenPositionsGoal(positions));

        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.GREEN, Tile.WHITE, Tile.GREEN), 0);
        // adds GREEN to position (3, 0) and position (1, 0)
        assertFalse(givenPositions.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.GREEN, Tile.WHITE), 1);
        // none of the required positions belong to column 1
        assertFalse(givenPositions.checkGoal(bookshelf));

        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.YELLOW, Tile.WHITE, Tile.YELLOW, Tile.BLUE), 2);
        // adds YELLOW and not GREEN to position (1, 2)
        assertFalse(givenPositions.checkGoal(bookshelf));
    }
}
