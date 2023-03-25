package it.polimi.ingsw;
import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.CardType;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookshelfTest extends TestCase{

    Bookshelf bookshelf;
    Optional<CardType> card1,card2,card3;
    List<Optional<CardType>> cardList;

    public BookshelfTest( String testName )
    {
        super( testName );
        card1 = Optional.of(CardType.BLUE);
        card2 = Optional.of(CardType.GREEN);
        card3 = Optional.of(CardType.PINK);
        cardList = new ArrayList<Optional<CardType>>();
        cardList.add(card1);
        cardList.add(card2);

    }

    //checks the addCells method
    public void testApp1(){
        assert(bookshelf.getFreeCells(2) == 0);
        bookshelf.addCells(cardList,2);
        assert(bookshelf.getFreeCells(2) == 2);
    }


    public static Test suite()
    {
        return new TestSuite( BoardTest.class );
    }

}
