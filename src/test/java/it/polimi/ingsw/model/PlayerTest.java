package it.polimi.ingsw.model;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.*;

public class PlayerTest extends TestCase{

  Player player;
  List<Position> positions = new ArrayList<Position>();
  List<CardType> cardTypes = new ArrayList<CardType>();
  Map<Integer, Integer> rewards = new HashMap<Integer, Integer>();

  public static Test suite()
  {
    return new TestSuite( PlayerTest.class );
  }

  public PlayerTest( String testName ) {
    super( testName );
    String nickname = new String("Pippo");

    rewards.put(1,1);
    rewards.put(2,2);
    rewards.put(3,4);
    rewards.put(4,6);
    rewards.put(5,9);
    rewards.put(6,12);

    positions.add(new Position( 0,0 ) );
    cardTypes.add(CardType.PINK);

    positions.add(new Position( 0,2) );
    cardTypes.add(CardType.BLUE);

    positions.add(new Position( 1,4 ) );
    cardTypes.add(CardType.GREEN);

    positions.add(new Position( 2, 3) );
    cardTypes.add(CardType.WHITE);

    positions.add(new Position( 3,1 ) );
    cardTypes.add(CardType.YELLOW);

    positions.add(new Position( 5,2 ) );
    cardTypes.add(CardType.LBLUE);

    PersonalGoal personalGoal = new PersonalGoal(positions, cardTypes, rewards);
    positions.clear();
    cardTypes.clear();

  }

  //checks the addCells method
  public void testNomeCheHaSenso(){

  }

}
