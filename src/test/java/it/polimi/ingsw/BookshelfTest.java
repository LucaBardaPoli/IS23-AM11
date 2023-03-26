package it.polimi.ingsw;
import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.CardType;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.ArrayList;
import java.util.List;

public class BookshelfTest extends TestCase{

    Bookshelf bookshelf = new Bookshelf(6,5);
    CardType card1,card2,card3;
    List<CardType> cardList;

    public BookshelfTest( String testName )
    {
        super( testName );
        card1 = CardType.BLUE;
        card2 = CardType.GREEN;
        card3 = CardType.PINK;
        cardList = new ArrayList<CardType>();
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
