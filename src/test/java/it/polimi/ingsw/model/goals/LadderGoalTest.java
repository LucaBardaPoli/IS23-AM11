package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.CardType;
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
        CommonGoal ladderGoal = new CommonGoal("ladder goal", new LadderGoal());

        assertFalse(ladderGoal.checkGoal(bookshelf));
    }

    @Test
    public void testRandFullBookshelf(){
        Bookshelf bookshelf = BookshelfBuilder.randomFullBookshelf();
        CommonGoal ladderGoal = new CommonGoal("ladder goal", new LadderGoal());

        assertFalse(ladderGoal.checkGoal(bookshelf));
    }

    @Test
    public void testLadderDescending1(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal ladderGoal = new CommonGoal("ladder goal", new LadderGoal());

        bookshelf.addCells(List.of(CardType.PINK, CardType.BLUE, CardType.LBLUE, CardType.YELLOW, CardType.BLUE, CardType.YELLOW), 0);
        bookshelf.addCells(List.of(CardType.PINK, CardType.BLUE, CardType.LBLUE, CardType.YELLOW, CardType.BLUE), 1);
        bookshelf.addCells(List.of(CardType.PINK, CardType.BLUE, CardType.LBLUE, CardType.YELLOW), 2);
        bookshelf.addCells(List.of(CardType.PINK, CardType.BLUE, CardType.LBLUE), 3);
        bookshelf.addCells(List.of(CardType.PINK, CardType.BLUE), 4);

        assertTrue(ladderGoal.checkGoal(bookshelf));
    }

    @Test
    public void testLadderDescending2(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal ladderGoal = new CommonGoal("ladder goal", new LadderGoal());

        bookshelf.addCells(List.of(CardType.PINK, CardType.BLUE, CardType.LBLUE, CardType.YELLOW, CardType.BLUE), 0);
        bookshelf.addCells(List.of(CardType.PINK, CardType.BLUE, CardType.LBLUE, CardType.YELLOW), 1);
        bookshelf.addCells(List.of(CardType.PINK, CardType.BLUE, CardType.LBLUE), 2);
        bookshelf.addCells(List.of(CardType.PINK, CardType.BLUE), 3);
        bookshelf.addCells(List.of(CardType.PINK), 4);

        assertTrue(ladderGoal.checkGoal(bookshelf));
    }

    @Test
    public void testLadderAscending1(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal ladderGoal = new CommonGoal("ladder goal", new LadderGoal());

        bookshelf.addCells(List.of(CardType.PINK, CardType.BLUE), 0);
        bookshelf.addCells(List.of(CardType.PINK, CardType.BLUE, CardType.LBLUE), 1);
        bookshelf.addCells(List.of(CardType.PINK, CardType.BLUE, CardType.LBLUE, CardType.YELLOW), 2);
        bookshelf.addCells(List.of(CardType.PINK, CardType.BLUE, CardType.LBLUE, CardType.YELLOW, CardType.BLUE), 3);
        bookshelf.addCells(List.of(CardType.PINK, CardType.BLUE, CardType.LBLUE, CardType.YELLOW, CardType.BLUE, CardType.YELLOW), 4);

        assertTrue(ladderGoal.checkGoal(bookshelf));
    }

    @Test
    public void testLadderAscending2(){
        Bookshelf bookshelf = new Bookshelf();
        CommonGoal ladderGoal = new CommonGoal("ladder goal", new LadderGoal());

        bookshelf.addCells(List.of(CardType.PINK), 0);
        bookshelf.addCells(List.of(CardType.PINK, CardType.BLUE), 1);
        bookshelf.addCells(List.of(CardType.PINK, CardType.BLUE, CardType.LBLUE), 2);
        bookshelf.addCells(List.of(CardType.PINK, CardType.BLUE, CardType.LBLUE, CardType.YELLOW), 3);
        bookshelf.addCells(List.of(CardType.PINK, CardType.BLUE, CardType.LBLUE, CardType.YELLOW, CardType.BLUE), 4);

        assertTrue(ladderGoal.checkGoal(bookshelf));
    }
}
