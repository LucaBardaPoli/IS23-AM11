package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.CardType;
import it.polimi.ingsw.model.CommonGoal;
import it.polimi.ingsw.model.Position;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.List;

public class GivenPositionsGoalTest extends TestCase {
    public GivenPositionsGoalTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite( GivenPositionsGoalTest.class );
    }

    public void testNoPositions(){
        Bookshelf bookshelf = new Bookshelf();
        List<Position> positions = new ArrayList<>();
        CommonGoal givenPositions;
        // list is empty ==> the goal is always fulfilled for any bookshelf (even if empty)

        givenPositions = new CommonGoal("given positions", new GivenPositionsGoal(positions));
        // bookshelf is empty
        assertTrue(givenPositions.checkGoal(bookshelf));

        bookshelf.addCells(List.of(CardType.BLUE, CardType.PINK), 0);
        // bookshelf is not empty
        assertTrue(givenPositions.checkGoal(bookshelf));
    }

    public void testEmptyBookshelf(){
        Bookshelf bookshelf = new Bookshelf();
        List<Position> positions = new ArrayList<>();
        CommonGoal givenPositions;
        // bookshelf is empty ==> the goal is never fulfilled for any non-empty list of positions

        givenPositions = new CommonGoal("given positions", new GivenPositionsGoal(positions));
        // list is empty
        assertTrue(givenPositions.checkGoal(bookshelf));

        // add bottom left corner position to the given positions list
        positions.add(new Position(Bookshelf.getRows()-1, Bookshelf.getColumns()-1));
        // all goals are immutable objects, so we have to instantiate a new one
        givenPositions = new CommonGoal("given positions", new GivenPositionsGoal(positions));
        assertFalse(givenPositions.checkGoal(bookshelf));
    }

    public void test1Position(){
        Bookshelf bookshelf = new Bookshelf();
        List<Position> positions = new ArrayList<>();
        CommonGoal givenPositions;

        positions.add(new Position(1, 0));
        positions.add(new Position(1, 2));
        positions.add(new Position(3, 0));

        givenPositions = new CommonGoal("given positions", new GivenPositionsGoal(positions));

        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.GREEN, CardType.WHITE, CardType.GREEN), 0);
        // adds GREEN to position (3, 0) and position (1, 0)
        assertFalse(givenPositions.checkGoal(bookshelf));

        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.GREEN, CardType.WHITE), 1);
        // none of the required positions belong to column 1
        assertFalse(givenPositions.checkGoal(bookshelf));

        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.YELLOW, CardType.WHITE, CardType.GREEN), 2);
        // adds GREEN to position (1, 2)
        assertTrue(givenPositions.checkGoal(bookshelf));
    }

    public void test2Position(){
        Bookshelf bookshelf = new Bookshelf();
        List<Position> positions = new ArrayList<>();
        CommonGoal givenPositions;

        positions.add(new Position(1, 0));
        positions.add(new Position(1, 2));
        positions.add(new Position(3, 0));

        givenPositions = new CommonGoal("given positions", new GivenPositionsGoal(positions));

        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.GREEN, CardType.WHITE, CardType.GREEN), 0);
        // adds GREEN to position (3, 0) and position (1, 0)
        assertFalse(givenPositions.checkGoal(bookshelf));

        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.GREEN, CardType.WHITE), 1);
        // none of the required positions belong to column 1
        assertFalse(givenPositions.checkGoal(bookshelf));

        bookshelf.addCells(List.of(CardType.BLUE, CardType.BLUE, CardType.YELLOW, CardType.WHITE, CardType.YELLOW, CardType.BLUE), 2);
        // adds YELLOW and not GREEN to position (1, 2)
        assertFalse(givenPositions.checkGoal(bookshelf));
    }

}
