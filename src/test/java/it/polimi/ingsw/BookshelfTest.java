package it.polimi.ingsw;
import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.CardType;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.ArrayList;
import java.util.List;

public class BookshelfTest extends TestCase{

    Bookshelf bookshelf;
    CardType card1,card2,card3;
    List<CardType> cardList;
    List<CardType> cardList2;

    public BookshelfTest( String testName )
    {
        super( testName );
        bookshelf = new Bookshelf(6,5);
        card1 = CardType.BLUE;
        card2 = CardType.GREEN;
        card3 = CardType.PINK;
        cardList = new ArrayList<CardType>();
        cardList2 = new ArrayList<CardType>();
        cardList.add(card1);
        cardList.add(card2);

    }

    //checks the addCells method
    public void testApp1(){
        assertEquals(0, 6 - (int) bookshelf.getFreeCells(2));
        bookshelf.addCells(cardList,2);
        assertEquals(2, 6 - (int) bookshelf.getFreeCells(2));
    }

    //test per controllare il funzionamento di getRow
    public void testApp2(){
        cardList.clear();
        cardList.add(card1);
        cardList2.add(card2);
        bookshelf.addCells(cardList,0);
        bookshelf.addCells(cardList2,1);
        assertEquals(bookshelf.getRow(5).get(0).get(),card1);
        assertEquals(bookshelf.getRow(5).get(1).get(),card2);
    }


    public static Test suite()
    {
        return new TestSuite( BoardTest.class );
    }

}
