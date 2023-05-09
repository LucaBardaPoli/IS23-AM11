package it.polimi.ingsw.model;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BookshelfTest {
    private Bookshelf bookshelf;
    private final Tile card1,card2,card3,card4,card5;
    private final List<Tile> cardList,cardList2,cardList3;

    public BookshelfTest() {
        card1 = Tile.BLUE;
        card2 = Tile.GREEN;
        card3 = Tile.PINK;
        card4 = Tile.LBLUE;
        card5= Tile.WHITE;

        cardList = new ArrayList<>();
        cardList2 = new ArrayList<>();
        cardList3 = new ArrayList<>();
        cardList.add(card1);
        cardList.add(card2);
        cardList3.add(Tile.EMPTY);
        cardList3.add(card1);
    }

    //checks the addCells method
    @Test
    public void testApp1(){
        bookshelf = new Bookshelf();
        assertEquals(6, bookshelf.getFreeCells(2));
        bookshelf.addTiles(cardList,2);
        assertEquals(4, bookshelf.getFreeCells(2));
    }

    //test for checking the getRow method
    @Test
    public void testApp2(){
        bookshelf = new Bookshelf();
        cardList.clear();
        cardList.add(card1);
        cardList2.add(card2);
        bookshelf.addTiles(cardList,0);
        bookshelf.addTiles(cardList2,1);
        assertEquals(bookshelf.getRow(5).get(0),card1);
        assertEquals(bookshelf.getRow(5).get(1),card2);
    }

    //test for checking the getFreeCells method
    @Test
    public void testApp3(){
        bookshelf = new Bookshelf();
        assertEquals(30, bookshelf.getFreeCells());
    }

    //test for checking getFreeCells(Integer columnNumber)
    @Test
    public void testApp4(){
        bookshelf = new Bookshelf();
        bookshelf.addTiles(cardList,4);
        assertEquals(4, bookshelf.getFreeCells(4));
    }

    //another test to check on the good functioning of the class
    @Test
    public void testApp5(){
        bookshelf = new Bookshelf();

        bookshelf.addTiles(Collections.singletonList(card1),0);
        bookshelf.addTiles(Collections.singletonList(card2),1);
        bookshelf.addTiles(Collections.singletonList(card3),2);
        bookshelf.addTiles(Collections.singletonList(card4),3);
        bookshelf.addTiles(Collections.singletonList(card5),4);

        assertEquals(5, bookshelf.getRow(5).size());
    }

    //checks that the addCell method doesn't add null to the bookshelf  but only Optional of CardType
    @Test
    public void testApp6(){
        bookshelf = new Bookshelf();
        bookshelf.addTiles(cardList3,0);

        assertEquals(1, bookshelf.getColumn(0).size());
    }
}
