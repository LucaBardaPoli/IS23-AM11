package it.polimi.ingsw.model;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BookshelfTest extends TestCase{

    Bookshelf bookshelf;
    CardType card1,card2,card3,card4,card5;
    List<CardType> cardList,cardList2,cardList3;

    public BookshelfTest( String testName )
    {
        super( testName );

        card1 = CardType.BLUE;
        card2 = CardType.GREEN;
        card3 = CardType.PINK;
        card4 = CardType.LBLUE;
        card5= CardType.WHITE;

        cardList = new ArrayList<CardType>();
        cardList2 = new ArrayList<CardType>();
        cardList3 = new ArrayList<CardType>();
        cardList.add(card1);
        cardList.add(card2);
        cardList3.add(null);
        cardList3.add(card1);
    }

    //checks the addCells method
    public void testApp1(){
        bookshelf = new Bookshelf();
        assertEquals(6, (int) bookshelf.getFreeCells(2));
        bookshelf.addCells(cardList,2);
        assertEquals(4, (int) bookshelf.getFreeCells(2));
    }

    //test for checking the getRow method
    public void testApp2(){
        bookshelf = new Bookshelf();
        cardList.clear();
        cardList.add(card1);
        cardList2.add(card2);
        bookshelf.addCells(cardList,0);
        bookshelf.addCells(cardList2,1);
        assertEquals(bookshelf.getRow(5).get(0).get(),card1);
        assertEquals(bookshelf.getRow(5).get(1).get(),card2);
    }

    //test for checking the getFreeCells method
    public void testApp3(){
        bookshelf = new Bookshelf();
        assertEquals(30, (int) bookshelf.getFreeCells());
    }

    //test for checking getFreeCells(Integer columnNumber)
    public void testApp4(){
        bookshelf = new Bookshelf();
        bookshelf.addCells(cardList,4);
        assertEquals(4,(int) bookshelf.getFreeCells(4));
    }

    //another test to check on the good functioning of the class
    public void testApp5(){
        bookshelf = new Bookshelf();

        bookshelf.addCells(Collections.singletonList(card1),0);
        bookshelf.addCells(Collections.singletonList(card2),1);
        bookshelf.addCells(Collections.singletonList(card3),2);
        bookshelf.addCells(Collections.singletonList(card4),3);
        bookshelf.addCells(Collections.singletonList(card5),4);

        assertEquals(5, bookshelf.getRow(5).size());
    }

    //checks that the addCell method doesn't add null to the bookshelf  but only Optional of CardType
    public void testApp6(){
        bookshelf = new Bookshelf();
        bookshelf.addCells(cardList3,0);

        assertEquals(1, bookshelf.getColumn(0).size());
    }

    //checks the behavior of getCell when it gets a negative position
    public void testApp7(){
        bookshelf = new Bookshelf();
        Position negativePosition = new Position(-1,-1);
        assertEquals(bookshelf.getCell(negativePosition), Optional.empty());
    }

    public static Test suite()
    {
        return new TestSuite( BookshelfTest.class );
    }

}