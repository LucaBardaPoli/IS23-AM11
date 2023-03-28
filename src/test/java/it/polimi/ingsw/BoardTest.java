package it.polimi.ingsw;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.CardType;
import it.polimi.ingsw.model.CountCards;
import it.polimi.ingsw.model.Position;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.HashMap;
import java.util.Optional;

public class BoardTest extends TestCase
{

    Board board = new Board(3);
    Position pos0;
    Position pos6;
    Position pos7;
    Position pos1;
    Position pos2;
    Position pos3;
    Position pos4;
    Position pos5;
    Position pos8;
    CountCards countCards = new CountCards();
    Optional<CardType> card = Optional.of(CardType.BLUE);
    Integer counterCells;


    public BoardTest( String testName )
    {
        super( testName );
        pos0 = new Position(2,3);
        pos1 = new Position(5,0);
        pos2 = new Position(4,0);
        pos3 = new Position(6,0);
        pos4 = new Position(5,1);
        pos5 = new Position(5,-1);
        pos6 = new Position(4,3);
        pos7 = new Position(4,5);
        pos8 = new Position(2,1);
        board.fillBoard(countCards);
        counterCells = 0;
    }


    public static Test suite()
    {
        return new TestSuite( BoardTest.class );
    }


    //testing the good functioning of the getCard function
    public void testApp1() {
        board.SetCard(pos0,card);
        board.getCard(pos0).equals(card);
    }

    //testing the good functioning of the fillBoard method
     //checks the card selection process happens correctly
     public void testApp2(){
         board.SetCard(pos1,card);
         board.SetCard(pos2,card);
         board.SetCard(pos3,card);
         board.SetCard(pos4,card);
         board.SetCard(pos5,card);

         assertFalse(board.validPick(pos1));
     }

     //check on the method checkValidCell
     public void testApp3(){
         assertFalse(board.checkValidCell(pos8));
     }

    //ccheck on the correct controll before picking a card from the Board
     public void testApp4(){
         board.SetCard(pos1,card);
         board.SetCard(pos2,card);
         board.SetCard(pos3,card);
         board.SetCard(pos4,card);

         assertFalse(board.validPick(pos1));
     }

}
