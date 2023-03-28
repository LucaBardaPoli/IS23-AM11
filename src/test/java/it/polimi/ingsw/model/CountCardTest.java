package it.polimi.ingsw.model;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.LinkedHashMap;
import java.util.Optional;

public class CountCardTest extends TestCase{

    CountCards count = new CountCards();
    Board board = new Board(4);
    CardType carta;
    private LinkedHashMap<CardType, Integer> cardsCounter = new LinkedHashMap<CardType, Integer>();
    public CountCardTest( String testName )
    {
        super( testName );
        carta = count.pickCard();
        cardsCounter = count.getCountCardType();
    }

    //test to check the correct creation of cards
    public void testApp1(){
        assertEquals(Optional.of(cardsCounter.get(carta).intValue()), Optional.of(21));

    }

    public static Test suite()
    {
        return new TestSuite( BoardTest.class );
    }
}

