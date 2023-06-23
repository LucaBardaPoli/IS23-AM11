package it.polimi.ingsw.model;

import it.polimi.ingsw.model.goals.*;
import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;
import static org.junit.Assert.*;

public class PlayerTest {
  private Game game2;

  private Game InitializeGames2(){
    //Game ID & List of Players
    List<String> players2 = new ArrayList<>(List.of("Brancaleone", "Arnaldo"));

    // List of the personal Goals that wil be present in this game
    List<PersonalGoal> personalGoals = new ArrayList<>();

    List<Position> positions = new ArrayList<>();
    List<Tile> tiles = new ArrayList<>();
    Map<Integer, Integer> rewards = new HashMap<>();

    rewards.put(1,1);
    rewards.put(2,2);
    rewards.put(3,4);
    rewards.put(4,6);
    rewards.put(5,9);
    rewards.put(6,12);

    //Personal Goal x1
    positions.add(new Position( 0,0 ) );
    tiles.add(Tile.PINK);

    positions.add(new Position( 0,2) );
    tiles.add(Tile.BLUE);

    positions.add(new Position( 1,4 ) );
    tiles.add(Tile.GREEN);

    positions.add(new Position( 2, 3) );
    tiles.add(Tile.WHITE);

    positions.add(new Position( 3,1 ) );
    tiles.add(Tile.YELLOW);

    positions.add(new Position( 5,2 ) );
    tiles.add(Tile.LBLUE);

    personalGoals.add(new PersonalGoal(1, positions, tiles, rewards));
    positions.clear();
    tiles.clear();

    //Personal Goal x2
    positions.add(new Position(1 ,1 ) );
    tiles.add(Tile.PINK);

    positions.add(new Position(2 ,0 ) );
    tiles.add(Tile.GREEN);

    positions.add(new Position( 2,2 ) );
    tiles.add(Tile.YELLOW);

    positions.add(new Position( 3,4 ) );
    tiles.add(Tile.WHITE);

    positions.add(new Position( 4, 3) );
    tiles.add(Tile.LBLUE);

    positions.add(new Position( 5, 4) );
    tiles.add(Tile.BLUE);

    personalGoals.add(new PersonalGoal(1, positions, tiles, rewards));
    positions.clear();
    tiles.clear();


    //Personal Goal x3
    positions.add(new Position(1 ,0) );
    tiles.add(Tile.BLUE);

    positions.add(new Position(1 , 3) );
    tiles.add(Tile.YELLOW);

    positions.add(new Position(2 ,2 ) );
    tiles.add(Tile.PINK);

    positions.add(new Position(3 , 1) );
    tiles.add(Tile.GREEN);

    positions.add(new Position(3 , 4) );
    tiles.add(Tile.LBLUE);

    positions.add(new Position(5 ,0 ) );
    tiles.add(Tile.WHITE);

    personalGoals.add(new PersonalGoal(1, positions, tiles, rewards));
    positions.clear();
    tiles.clear();


    //Personal Goal x4
    positions.add(new Position( 0,4 ) );
    tiles.add(Tile.YELLOW);

    positions.add(new Position( 2, 0) );
    tiles.add(Tile.LBLUE);

    positions.add(new Position(2 , 2) );
    tiles.add(Tile.BLUE);

    positions.add(new Position( 3,3) );
    tiles.add(Tile.PINK);

    positions.add(new Position(4 , 1) );
    tiles.add(Tile.WHITE);

    positions.add(new Position(4, 2) );
    tiles.add(Tile.GREEN);

    personalGoals.add(new PersonalGoal(1, positions, tiles, rewards));
    positions.clear();
    tiles.clear();

    // ## Common Goals ##
    List<CommonGoal> commonGoals = new ArrayList<>();
    Predicate<Bookshelf> bookshelfPredicate;

    // Common Goal: DifferentGroupColumn Goal
    bookshelfPredicate = new DifferentTypesGoal(6,6,2, CheckMode.VERTICAL);
    commonGoals.add(new CommonGoal(1, "Different Group Column",  bookshelfPredicate));

    // Common Goal: Ladder Goal
    bookshelfPredicate = new LadderGoal();
    commonGoals.add(new CommonGoal(1, "Ladder Goal", bookshelfPredicate));

    return new Game(players2, commonGoals, personalGoals.subList(0, 2));
    //game2.getBoard().fillBoard(new Bag());
  }

  private Game InitializeGames2DiffGoals(){
    //Game ID & List of Players
    List<String> players2 = new ArrayList<>(List.of("Brancaleone", "Arnaldo"));

    // ## Personal Goals ##
    List<PersonalGoal> personalGoals = new ArrayList<>();

    List<Position> positions = new ArrayList<>();
    List<Tile> tiles = new ArrayList<>();
    Map<Integer, Integer> rewards = new HashMap<>();

    rewards.put(1,1);
    rewards.put(2,2);
    rewards.put(3,4);
    rewards.put(4,6);
    rewards.put(5,9);
    rewards.put(6,12);

    //Personal Goal x1
    positions.add(new Position( 0,0 ) );
    tiles.add(Tile.PINK);

    positions.add(new Position( 0,2) );
    tiles.add(Tile.BLUE);

    positions.add(new Position( 1,4 ) );
    tiles.add(Tile.GREEN);

    positions.add(new Position( 2, 3) );
    tiles.add(Tile.WHITE);

    positions.add(new Position( 3,1 ) );
    tiles.add(Tile.YELLOW);

    positions.add(new Position( 5,2 ) );
    tiles.add(Tile.LBLUE);

    personalGoals.add(new PersonalGoal(1, positions, tiles, rewards));
    positions.clear();
    tiles.clear();

    //Personal Goal x1
    positions.add(new Position( 0,0 ) );
    tiles.add(Tile.PINK);

    positions.add(new Position( 0,2) );
    tiles.add(Tile.BLUE);

    positions.add(new Position( 1,4 ) );
    tiles.add(Tile.GREEN);

    positions.add(new Position( 2, 3) );
    tiles.add(Tile.WHITE);

    positions.add(new Position( 3,1 ) );
    tiles.add(Tile.YELLOW);

    positions.add(new Position( 5,2 ) );
    tiles.add(Tile.LBLUE);

    personalGoals.add(new PersonalGoal(1, positions, tiles, rewards));
    positions.clear();
    tiles.clear();

    //Personal Goal x2
    positions.add(new Position(1 ,1 ) );
    tiles.add(Tile.PINK);

    positions.add(new Position(2 ,0 ) );
    tiles.add(Tile.GREEN);

    positions.add(new Position( 2,2 ) );
    tiles.add(Tile.YELLOW);

    positions.add(new Position( 3,4 ) );
    tiles.add(Tile.WHITE);

    positions.add(new Position( 4, 3) );
    tiles.add(Tile.LBLUE);

    positions.add(new Position( 5, 4) );
    tiles.add(Tile.BLUE);

    personalGoals.add(new PersonalGoal(1, positions, tiles, rewards));
    positions.clear();
    tiles.clear();


    //Personal Goal x3
    positions.add(new Position(1 ,0) );
    tiles.add(Tile.BLUE);

    positions.add(new Position(1 , 3) );
    tiles.add(Tile.YELLOW);

    positions.add(new Position(2 ,2 ) );
    tiles.add(Tile.PINK);

    positions.add(new Position(3 , 1) );
    tiles.add(Tile.GREEN);

    positions.add(new Position(3 , 4) );
    tiles.add(Tile.LBLUE);

    positions.add(new Position(5 ,0 ) );
    tiles.add(Tile.WHITE);

    personalGoals.add(new PersonalGoal(1, positions, tiles, rewards));
    positions.clear();
    tiles.clear();


    //Personal Goal x4
    positions.add(new Position( 0,4 ) );
    tiles.add(Tile.YELLOW);

    positions.add(new Position( 2, 0) );
    tiles.add(Tile.LBLUE);

    positions.add(new Position(2 , 2) );
    tiles.add(Tile.BLUE);

    positions.add(new Position( 3,3) );
    tiles.add(Tile.PINK);

    positions.add(new Position(4 , 1) );
    tiles.add(Tile.WHITE);

    positions.add(new Position(4, 2) );
    tiles.add(Tile.GREEN);

    personalGoals.add(new PersonalGoal(1, positions, tiles, rewards));
    positions.clear();
    tiles.clear();

    // ## Common Goals ##
    List<CommonGoal> commonGoals = new ArrayList<>();
    Predicate<Bookshelf> bookshelfPredicate;

// Common Goal: Given Positions Goal
    List<Position> positionList = new ArrayList<>();
    positionList.add(new Position(0,0));
    positionList.add(new Position(0,4));
    positionList.add(new Position(5,0));
    positionList.add(new Position(5,4));
    bookshelfPredicate = new GivenPositionsGoal(positionList);
    commonGoals.add(new CommonGoal(1, "Given Positions",  bookshelfPredicate));

    // Common Goal: Ladder Goal
    bookshelfPredicate = new LadderGoal();
    commonGoals.add(new CommonGoal(1, "Ladder Goal", bookshelfPredicate));

    return new Game(players2, commonGoals, personalGoals.subList(0, 2));
    //game2.getBoard().fillBoard(new Bag());
  }

  private Game InitializeGames2DiffGoals2(){
    //Game ID & List of Players
    List<String> players2 = new ArrayList<>(List.of("Brancaleone", "Arnaldo"));

    // ## Personal Goals ##
    List<PersonalGoal> personalGoals = new ArrayList<>();

    List<Position> positions = new ArrayList<>();
    List<Tile> tiles = new ArrayList<>();
    Map<Integer, Integer> rewards = new HashMap<>();

    rewards.put(1,1);
    rewards.put(2,2);
    rewards.put(3,4);
    rewards.put(4,6);
    rewards.put(5,9);
    rewards.put(6,12);

    //Personal Goal x1
    positions.add(new Position( 0,0 ) );
    tiles.add(Tile.PINK);

    positions.add(new Position( 0,2) );
    tiles.add(Tile.BLUE);

    positions.add(new Position( 1,4 ) );
    tiles.add(Tile.GREEN);

    positions.add(new Position( 2, 3) );
    tiles.add(Tile.WHITE);

    positions.add(new Position( 3,1 ) );
    tiles.add(Tile.YELLOW);

    positions.add(new Position( 5,2 ) );
    tiles.add(Tile.LBLUE);

    personalGoals.add(new PersonalGoal(1, positions, tiles, rewards));
    positions.clear();
    tiles.clear();

    //Personal Goal x1
    positions.add(new Position( 0,0 ) );
    tiles.add(Tile.PINK);

    positions.add(new Position( 0,2) );
    tiles.add(Tile.BLUE);

    positions.add(new Position( 1,4 ) );
    tiles.add(Tile.GREEN);

    positions.add(new Position( 2, 3) );
    tiles.add(Tile.WHITE);

    positions.add(new Position( 3,1 ) );
    tiles.add(Tile.YELLOW);

    positions.add(new Position( 5,2 ) );
    tiles.add(Tile.LBLUE);

    personalGoals.add(new PersonalGoal(1, positions, tiles, rewards));
    positions.clear();
    tiles.clear();

    //Personal Goal x2
    positions.add(new Position(1 ,1 ) );
    tiles.add(Tile.PINK);

    positions.add(new Position(2 ,0 ) );
    tiles.add(Tile.GREEN);

    positions.add(new Position( 2,2 ) );
    tiles.add(Tile.YELLOW);

    positions.add(new Position( 3,4 ) );
    tiles.add(Tile.WHITE);

    positions.add(new Position( 4, 3) );
    tiles.add(Tile.LBLUE);

    positions.add(new Position( 5, 4) );
    tiles.add(Tile.BLUE);

    personalGoals.add(new PersonalGoal(1, positions, tiles, rewards));
    positions.clear();
    tiles.clear();


    //Personal Goal x3
    positions.add(new Position(1 ,0) );
    tiles.add(Tile.BLUE);

    positions.add(new Position(1 , 3) );
    tiles.add(Tile.YELLOW);

    positions.add(new Position(2 ,2 ) );
    tiles.add(Tile.PINK);

    positions.add(new Position(3 , 1) );
    tiles.add(Tile.GREEN);

    positions.add(new Position(3 , 4) );
    tiles.add(Tile.LBLUE);

    positions.add(new Position(5 ,0 ) );
    tiles.add(Tile.WHITE);

    personalGoals.add(new PersonalGoal(1, positions, tiles, rewards));
    positions.clear();
    tiles.clear();


    //Personal Goal x4
    positions.add(new Position( 0,4 ) );
    tiles.add(Tile.YELLOW);

    positions.add(new Position( 2, 0) );
    tiles.add(Tile.LBLUE);

    positions.add(new Position(2 , 2) );
    tiles.add(Tile.BLUE);

    positions.add(new Position( 3,3) );
    tiles.add(Tile.PINK);

    positions.add(new Position(4 , 1) );
    tiles.add(Tile.WHITE);

    positions.add(new Position(4, 2) );
    tiles.add(Tile.GREEN);

    personalGoals.add(new PersonalGoal(1, positions, tiles, rewards));
    positions.clear();
    tiles.clear();

    // ## Common Goals ##
    List<CommonGoal> commonGoals = new ArrayList<>();
    Predicate<Bookshelf> bookshelfPredicate;

    // Common Goal: Same Kind Square Goal
    bookshelfPredicate = new SameKindSquareGoal();
    commonGoals.add(new CommonGoal(1, "Same Kind Square",  bookshelfPredicate));

    // Common Goal: Same Kind X Goal
    bookshelfPredicate = new SameKindXGoal();
    commonGoals.add(new CommonGoal(1, "Same Kind X",  bookshelfPredicate));

    return new Game(players2, commonGoals, personalGoals.subList(0, 2));
    //game2.getBoard().fillBoard(new Bag());
  }

  //checks the addCells method
  @Test
  public void testValidAdjacency1(){
    this.game2 = InitializeGames2();

    // Creates the Bookshelf that will be tested. This one is taken from the first example in the Rulebook
    Bookshelf bookshelfCase = new Bookshelf();
    assertNotNull(bookshelfCase);

    // Local array that contains the cards that will be inserted in the Bookshelf
    List<Tile> cardToInsert = new ArrayList<>();

    // 1 Column
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.BLUE);
    cardToInsert.add(Tile.BLUE);
    cardToInsert.add(Tile.BLUE);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    bookshelfCase.addTiles(cardToInsert.subList(0,3),0);
    bookshelfCase.addTiles(cardToInsert.subList(3,6),0);

    // 2 Column
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.BLUE);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    bookshelfCase.addTiles(cardToInsert.subList(6,9),1);
    bookshelfCase.addTiles(cardToInsert.subList(9,12),1);

    // 3 Column
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    bookshelfCase.addTiles(cardToInsert.subList(12,15),2);
    bookshelfCase.addTiles(cardToInsert.subList(15,17),2);

    // 4 Column
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    bookshelfCase.addTiles(cardToInsert.subList(17,20),3);
    bookshelfCase.addTiles(cardToInsert.subList(20,22),3);

    // 5 Column
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.WHITE);
    cardToInsert.add(Tile.WHITE);
    bookshelfCase.addTiles(cardToInsert.subList(22,25),4);
    bookshelfCase.addTiles(cardToInsert.subList(25,26),4);

    assertEquals(bookshelfCase.getFreeCells(),  4);

    // Assigning the test Bookshelf to the Current Player
    Player currentPlayer = game2.getCurrentPlayer();
    currentPlayer.setBookshelf(bookshelfCase);

    assertEquals(currentPlayer.getBookshelf(),bookshelfCase);

    Integer adjacencyPoints = currentPlayer.checkAdjacency(currentPlayer.getBookshelf());
    assertEquals(Integer.valueOf(21), adjacencyPoints);

  }

  @Test
  public void testValidAdjacency2(){
    this.game2 = InitializeGames2();

    // Creates the Bookshelf that will be tested. This one is taken from the first example in the Rulebook
    Bookshelf bookshelfCase = new Bookshelf();
    assertNotNull(bookshelfCase);

    // Local array that contains the cards that will be inserted in the Bookshelf
    List<Tile> cardToInsert = new ArrayList<>();

    // 1 Column
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.BLUE);
    cardToInsert.add(Tile.WHITE);
    cardToInsert.add(Tile.PINK);
    bookshelfCase.addTiles(cardToInsert.subList(0,3),0);
    bookshelfCase.addTiles(cardToInsert.subList(3,6),0);

    // 2 Column
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.WHITE);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    bookshelfCase.addTiles(cardToInsert.subList(6,9),1);
    bookshelfCase.addTiles(cardToInsert.subList(9,12),1);

    // 3 Column
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.BLUE);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    bookshelfCase.addTiles(cardToInsert.subList(12,15),2);
    bookshelfCase.addTiles(cardToInsert.subList(15,18),2);

    // 4 Column
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.WHITE);
    cardToInsert.add(Tile.GREEN);
    bookshelfCase.addTiles(cardToInsert.subList(18,21),3);
    bookshelfCase.addTiles(cardToInsert.subList(21,23),3);

    // 5 Column
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.GREEN);
    bookshelfCase.addTiles(cardToInsert.subList(23,25),4);

    assertEquals( 5, bookshelfCase.getFreeCells());

    // Assigning the test Bookshelf to the Current Player
    Player currentPlayer = game2.getCurrentPlayer();
    currentPlayer.setBookshelf(bookshelfCase);

    assertEquals(currentPlayer.getBookshelf(),bookshelfCase);

    Integer adjacencyPoints = currentPlayer.checkAdjacency(currentPlayer.getBookshelf());
    assertEquals(Integer.valueOf(18), adjacencyPoints);

  }

  @Test
  public void testValidAdjacency3(){

    this.game2 = InitializeGames2();

    // Creates the Bookshelf that will be tested.
    Bookshelf bookshelfCase = new Bookshelf();
    assertNotNull(bookshelfCase);

    // Local array that contains the cards that will be inserted in the Bookshelf
    List<Tile> cardToInsert = new ArrayList<>();

    // Bookshelf which contains a same card type per column
    // 1 Column
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    bookshelfCase.addTiles(cardToInsert.subList(0,3),0);
    bookshelfCase.addTiles(cardToInsert.subList(3,6),0);

    // 2 Column
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    bookshelfCase.addTiles(cardToInsert.subList(6,9),1);
    bookshelfCase.addTiles(cardToInsert.subList(9,12),1);

    // 3 Column
    cardToInsert.add(Tile.WHITE);
    cardToInsert.add(Tile.WHITE);
    cardToInsert.add(Tile.WHITE);
    cardToInsert.add(Tile.WHITE);
    cardToInsert.add(Tile.WHITE);
    cardToInsert.add(Tile.WHITE);
    bookshelfCase.addTiles(cardToInsert.subList(12,15),2);
    bookshelfCase.addTiles(cardToInsert.subList(15,18),2);

    // 4 Column
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.YELLOW);
    bookshelfCase.addTiles(cardToInsert.subList(18,21),3);
    bookshelfCase.addTiles(cardToInsert.subList(21,24),3);

    // 5 Column
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.GREEN);
    bookshelfCase.addTiles(cardToInsert.subList(24,27),4);
    bookshelfCase.addTiles(cardToInsert.subList(27, 30),4);

    assertEquals(0 , bookshelfCase.getFreeCells());

    // Assigning the test Bookshelf to the Current Player
    Player currentPlayer = game2.getCurrentPlayer();
    currentPlayer.setBookshelf(bookshelfCase);

    assertEquals(currentPlayer.getBookshelf(),bookshelfCase);

    Integer adjacencyPoints = currentPlayer.checkAdjacency(currentPlayer.getBookshelf());
    assertEquals(Integer.valueOf(5*8), adjacencyPoints);

  }

  @Test
  public void testValidAdjacency4(){

    this.game2 = InitializeGames2();

    // Creates the Bookshelf that will be tested.
    Bookshelf bookshelfCase = new Bookshelf();
    assertNotNull(bookshelfCase);

    // Local array that contains the cards that will be inserted in the Bookshelf
    List<Tile> cardToInsert = new ArrayList<>();

    // Bookshelf which contains a same card type per row
    // 1 Column
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.WHITE);
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.BLUE);
    bookshelfCase.addTiles(cardToInsert.subList(0,3),0);
    bookshelfCase.addTiles(cardToInsert.subList(3,6),0);

    // 2 Column
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.WHITE);
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.BLUE);
    bookshelfCase.addTiles(cardToInsert.subList(6,9),1);
    bookshelfCase.addTiles(cardToInsert.subList(9,12),1);

    // 3 Column
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.WHITE);
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.BLUE);
    bookshelfCase.addTiles(cardToInsert.subList(12,15),2);
    bookshelfCase.addTiles(cardToInsert.subList(15,18),2);

    // 4 Column
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.WHITE);
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.BLUE);
    bookshelfCase.addTiles(cardToInsert.subList(18,21),3);
    bookshelfCase.addTiles(cardToInsert.subList(21,24),3);

    // 5 Column
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.WHITE);
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.BLUE);
    bookshelfCase.addTiles(cardToInsert.subList(24,27),4);
    bookshelfCase.addTiles(cardToInsert.subList(27, 30),4);

    assertEquals(0, bookshelfCase.getFreeCells());

    // Assigning the test Bookshelf to the Current Player
    Player currentPlayer = game2.getCurrentPlayer();
    currentPlayer.setBookshelf(bookshelfCase);

    assertEquals(currentPlayer.getBookshelf(),bookshelfCase);

    Integer adjacencyPoints = currentPlayer.checkAdjacency(currentPlayer.getBookshelf());
    assertEquals(Integer.valueOf(6 * 5), adjacencyPoints);

  }

  @Test
  public void testValidAdjacency5(){

    this.game2 = InitializeGames2();

    // Creates the Bookshelf that will be tested.
    Bookshelf bookshelfCase = new Bookshelf();
    assertNotNull(bookshelfCase);

    // Local array that contains the cards that will be inserted in the Bookshelf
    List<Tile> cardToInsert = new ArrayList<>();

    // Bookshelf which contains a same card type per row (only 2 column filled)
    // 1 Column
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    bookshelfCase.addTiles(cardToInsert.subList(0,3),0);
    bookshelfCase.addTiles(cardToInsert.subList(3,6),0);

    // 5 Column
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    bookshelfCase.addTiles(cardToInsert.subList(6,9),4);
    bookshelfCase.addTiles(cardToInsert.subList(9,12),4);

    assertEquals(18, bookshelfCase.getFreeCells());

    // Assigning the test Bookshelf to the Current Player
    Player currentPlayer = game2.getCurrentPlayer();
    currentPlayer.setBookshelf(bookshelfCase);

    assertEquals(currentPlayer.getBookshelf(),bookshelfCase);

    Integer adjacencyPoints = currentPlayer.checkAdjacency(currentPlayer.getBookshelf());
    assertEquals(Integer.valueOf(2 * 8), adjacencyPoints);

  }

  @Test
  public void testValidAdjacency6(){

    this.game2 = InitializeGames2();

    // Creates the Bookshelf that will be tested.
    Bookshelf bookshelfCase = new Bookshelf();
    assertNotNull(bookshelfCase);

    // Local array that contains the cards that will be inserted in the Bookshelf
    List<Tile> cardToInsert = new ArrayList<>();

    // Bookshelf which contains a same card type per row(only 2 adjacent column filled)
    // 1 Column
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    bookshelfCase.addTiles(cardToInsert.subList(0,3),0);
    bookshelfCase.addTiles(cardToInsert.subList(3,6),0);

    // 5 Column
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    bookshelfCase.addTiles(cardToInsert.subList(6,9),1);
    bookshelfCase.addTiles(cardToInsert.subList(9,12),1);

    assertEquals(18, bookshelfCase.getFreeCells());

    // Assigning the test Bookshelf to the Current Player
    Player currentPlayer = game2.getCurrentPlayer();
    currentPlayer.setBookshelf(bookshelfCase);

    assertEquals(currentPlayer.getBookshelf(),bookshelfCase);

    Integer adjacencyPoints = currentPlayer.checkAdjacency(currentPlayer.getBookshelf());
    assertEquals(Integer.valueOf(8), adjacencyPoints);

  }

  @Test
  public void testValidAdjacency7(){

    this.game2 = InitializeGames2();

    // Creates the Bookshelf that will be tested.
    Bookshelf bookshelfCase = new Bookshelf();
    assertNotNull(bookshelfCase);

    // Local array that contains the cards that will be inserted in the Bookshelf
    List<Tile> cardToInsert = new ArrayList<>();

    // Bookshelf which contains an L shape
    // 1 Column
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    bookshelfCase.addTiles(cardToInsert.subList(0,3),0);
    bookshelfCase.addTiles(cardToInsert.subList(3,6),0);

    // Other columns
    cardToInsert.add(Tile.PINK);
    bookshelfCase.addTiles(cardToInsert.subList(6,7),1);

    cardToInsert.add(Tile.PINK);
    bookshelfCase.addTiles(cardToInsert.subList(7,8),2);

    cardToInsert.add(Tile.PINK);
    bookshelfCase.addTiles(cardToInsert.subList(8,9),3);

    cardToInsert.add(Tile.PINK);
    bookshelfCase.addTiles(cardToInsert.subList(9,10),4);


    assertEquals(20, bookshelfCase.getFreeCells());

    // Assigning the test Bookshelf to the Current Player
    Player currentPlayer = game2.getCurrentPlayer();
    currentPlayer.setBookshelf(bookshelfCase);

    assertEquals(currentPlayer.getBookshelf(),bookshelfCase);

    Integer adjacencyPoints = currentPlayer.checkAdjacency(currentPlayer.getBookshelf());
    assertEquals(Integer.valueOf(8), adjacencyPoints);

  }

  @Test
  public void testValidAdjacency8(){

    this.game2 = InitializeGames2();

    // Creates the Bookshelf that will be tested.
    Bookshelf bookshelfCase = new Bookshelf();
    assertNotNull(bookshelfCase);

    // Local array that contains the cards that will be inserted in the Bookshelf
    List<Tile> cardToInsert = new ArrayList<>();

    // Bookshelf which contains an L shape
    // 1 Column
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.YELLOW);
    bookshelfCase.addTiles(cardToInsert.subList(0,3),4);
    bookshelfCase.addTiles(cardToInsert.subList(3,6),4);

    // Other columns
    cardToInsert.add(Tile.YELLOW);
    bookshelfCase.addTiles(cardToInsert.subList(6,7),0);

    cardToInsert.add(Tile.YELLOW);
    bookshelfCase.addTiles(cardToInsert.subList(7,8),1);

    cardToInsert.add(Tile.YELLOW);
    bookshelfCase.addTiles(cardToInsert.subList(8,9),2);

    cardToInsert.add(Tile.YELLOW);
    bookshelfCase.addTiles(cardToInsert.subList(9,10),3);


    assertEquals(20, bookshelfCase.getFreeCells());

    // Assigning the test Bookshelf to the Current Player
    Player currentPlayer = game2.getCurrentPlayer();
    currentPlayer.setBookshelf(bookshelfCase);

    assertEquals(currentPlayer.getBookshelf(),bookshelfCase);

    Integer adjacencyPoints = currentPlayer.checkAdjacency(currentPlayer.getBookshelf());
    assertEquals(Integer.valueOf(8), adjacencyPoints);

  }

  @Test
  public void testValidAdjacency9(){

    this.game2 = InitializeGames2();

    // Creates the Bookshelf that will be tested.
    Bookshelf bookshelfCase = new Bookshelf();
    assertNotNull(bookshelfCase);

    // Local array that contains the cards that will be inserted in the Bookshelf
    List<Tile> cardToInsert = new ArrayList<>();

    // Bookshelf which contains only 1 card and then add more cards to check all the scores
    // 2 Column
    cardToInsert.add(Tile.YELLOW);
    bookshelfCase.addTiles(cardToInsert.subList(0,1),1);


    assertEquals(29, bookshelfCase.getFreeCells());

    // Assigning the test Bookshelf to the Current Player
    Player currentPlayer = game2.getCurrentPlayer();
    currentPlayer.setBookshelf(bookshelfCase);

    assertEquals(currentPlayer.getBookshelf(),bookshelfCase);

    Integer adjacencyPoints = currentPlayer.checkAdjacency(currentPlayer.getBookshelf());
    assertEquals(Integer.valueOf(0 ), adjacencyPoints);

    // Adding the second card in the same column as before
    cardToInsert.add(Tile.YELLOW);
    bookshelfCase.addTiles(cardToInsert.subList(1,2),1);
    assertEquals(28, bookshelfCase.getFreeCells());
    adjacencyPoints = currentPlayer.checkAdjacency(currentPlayer.getBookshelf());
    assertEquals(Integer.valueOf(0 ), adjacencyPoints);

    // Adding the third card in the same column as before
    cardToInsert.add(Tile.YELLOW);
    bookshelfCase.addTiles(cardToInsert.subList(2,3),1);
    assertEquals(27, bookshelfCase.getFreeCells());
    adjacencyPoints = currentPlayer.checkAdjacency(currentPlayer.getBookshelf());
    assertEquals(Integer.valueOf(2 ), adjacencyPoints);

    // Adding the fourth card in the same column as before
    cardToInsert.add(Tile.YELLOW);
    bookshelfCase.addTiles(cardToInsert.subList(3,4),1);
    assertEquals( 26, bookshelfCase.getFreeCells());
    adjacencyPoints = currentPlayer.checkAdjacency(currentPlayer.getBookshelf());
    assertEquals(Integer.valueOf(3 ), adjacencyPoints);

    // Adding the fifth card in the same column as before
    cardToInsert.add(Tile.YELLOW);
    bookshelfCase.addTiles(cardToInsert.subList(3,4),1);
    assertEquals(25, bookshelfCase.getFreeCells());
    adjacencyPoints = currentPlayer.checkAdjacency(currentPlayer.getBookshelf());
    assertEquals(Integer.valueOf(5 ), adjacencyPoints);

    // Adding the last card in the same column as before
    cardToInsert.add(Tile.YELLOW);
    bookshelfCase.addTiles(cardToInsert.subList(3,4),1);
    assertEquals(24, bookshelfCase.getFreeCells());
    adjacencyPoints = currentPlayer.checkAdjacency(currentPlayer.getBookshelf());
    assertEquals(Integer.valueOf(8 ), adjacencyPoints);

  }

  @Test
  public void testValidAdjacency10(){

    this.game2 = InitializeGames2();

    // Creates the Bookshelf that will be tested.
    Bookshelf bookshelfCase = new Bookshelf();
    assertNotNull(bookshelfCase);

    // Local array that contains the cards that will be inserted in the Bookshelf
    List<Tile> cardToInsert = new ArrayList<>();

    // Bookshelf that contains a single card.
    // The latter will be filled with one card at a time, thus having a full column filled
    // 1 Column
    cardToInsert.add(Tile.YELLOW);
    bookshelfCase.addTiles(cardToInsert.subList(0,1),0);


    assertEquals(29, bookshelfCase.getFreeCells());

    // Assigning the test Bookshelf to the Current Player
    Player currentPlayer = game2.getCurrentPlayer();
    currentPlayer.setBookshelf(bookshelfCase);

    assertEquals(currentPlayer.getBookshelf(),bookshelfCase);

    Integer adjacencyPoints = currentPlayer.checkAdjacency(currentPlayer.getBookshelf());
    assertEquals(Integer.valueOf(0 ), adjacencyPoints);

    // Adding the second card in the next row
    cardToInsert.add(Tile.YELLOW);
    bookshelfCase.addTiles(cardToInsert.subList(1,2),1);
    assertEquals(28, bookshelfCase.getFreeCells());
    adjacencyPoints = currentPlayer.checkAdjacency(currentPlayer.getBookshelf());
    assertEquals(Integer.valueOf(0 ), adjacencyPoints);

    // Adding the third card in the next row
    cardToInsert.add(Tile.YELLOW);
    bookshelfCase.addTiles(cardToInsert.subList(2,3),2);
    assertEquals(27, bookshelfCase.getFreeCells());
    adjacencyPoints = currentPlayer.checkAdjacency(currentPlayer.getBookshelf());
    assertEquals(Integer.valueOf(2 ), adjacencyPoints);

    // Adding the fourth card in the next row
    cardToInsert.add(Tile.YELLOW);
    bookshelfCase.addTiles(cardToInsert.subList(3,4),3);
    assertEquals(26, bookshelfCase.getFreeCells());
    adjacencyPoints = currentPlayer.checkAdjacency(currentPlayer.getBookshelf());
    assertEquals(Integer.valueOf(3 ), adjacencyPoints);

    // Adding the last card in the next row
    cardToInsert.add(Tile.YELLOW);
    bookshelfCase.addTiles(cardToInsert.subList(3,4),4);
    assertEquals(25, bookshelfCase.getFreeCells());
    adjacencyPoints = currentPlayer.checkAdjacency(currentPlayer.getBookshelf());
    assertEquals(Integer.valueOf(5 ), adjacencyPoints);

  }

  @Test
  public void testEmptyAdjacency(){

    this.game2 = InitializeGames2();

    // Creates the Bookshelf that will be tested.
    Bookshelf bookshelfCase = new Bookshelf();
    assertNotNull(bookshelfCase);

    assertEquals(GameSettings.ROWS * GameSettings.COLUMNS, bookshelfCase.getFreeCells());

    // Assigning the test Bookshelf to the Current Player
    Player currentPlayer = game2.getCurrentPlayer();
    currentPlayer.setBookshelf(bookshelfCase);

    assertEquals(currentPlayer.getBookshelf(),bookshelfCase);

    Integer adjacencyPoints = currentPlayer.checkAdjacency(currentPlayer.getBookshelf());
    assertEquals(Integer.valueOf(0), adjacencyPoints);

  }

  @Test
  public void testCheckBookshelf1(){
    this.game2 = InitializeGames2DiffGoals();

    // Creates the Bookshelf that will be tested.
    Bookshelf bookshelfCase = new Bookshelf();
    assertNotNull(bookshelfCase);

    // Local array that contains the cards that will be inserted in the Bookshelf
    List<Tile> cardToInsert = new ArrayList<>();

    // Bookshelf which contains a same card type per column
    // 1 Column
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.LBLUE);
    bookshelfCase.addTiles(cardToInsert.subList(0,3),0);
    bookshelfCase.addTiles(cardToInsert.subList(3,6),0);

    // 2 Column
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    bookshelfCase.addTiles(cardToInsert.subList(6,9),1);
    bookshelfCase.addTiles(cardToInsert.subList(9,12),1);

    // 3 Column
    cardToInsert.add(Tile.WHITE);
    cardToInsert.add(Tile.WHITE);
    cardToInsert.add(Tile.WHITE);
    cardToInsert.add(Tile.WHITE);
    cardToInsert.add(Tile.WHITE);
    cardToInsert.add(Tile.WHITE);
    bookshelfCase.addTiles(cardToInsert.subList(12,15),2);
    bookshelfCase.addTiles(cardToInsert.subList(15,18),2);

    // 4 Column
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.YELLOW);
    bookshelfCase.addTiles(cardToInsert.subList(18,21),3);
    bookshelfCase.addTiles(cardToInsert.subList(21,24),3);

    // 5 Column
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.LBLUE);
    bookshelfCase.addTiles(cardToInsert.subList(24,27),4);
    bookshelfCase.addTiles(cardToInsert.subList(27, 30),4);

    assertEquals(0, bookshelfCase.getFreeCells());

    // Assigning the test Bookshelf to the Current Player
    Player currentPlayer = game2.getCurrentPlayer();
    currentPlayer.setBookshelf(bookshelfCase);
    assertEquals(currentPlayer.getBookshelf(),bookshelfCase);

    // Checking the Bookshelf for: Personal Goals, Common Goals,
    currentPlayer.checkBookshelf(game2.getCommonGoals());
    int scoreCheckBookshelf = currentPlayer.getPoints();

    assertEquals(8, currentPlayer.getCommonGoalPoints());
    assertEquals(1, currentPlayer.getPersonalGoalPoints());
    assertEquals(35, currentPlayer.getAdjacentPoints());
    assertEquals(currentPlayer.getPoints(), scoreCheckBookshelf);

  }

  @Test
  public void testCheckBookshelf2(){
    this.game2 = InitializeGames2DiffGoals2();

    // Creates the Bookshelf that will be tested.
    Bookshelf bookshelfCase = new Bookshelf();
    assertNotNull(bookshelfCase);

    // Local array that contains the cards that will be inserted in the Bookshelf
    List<Tile> cardToInsert = new ArrayList<>();

    // Bookshelf which contains the pattern for SameKindX common goal
    // 1 Column
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.WHITE);
    cardToInsert.add(Tile.LBLUE);
    bookshelfCase.addTiles(cardToInsert.subList(0,3),0);

    // 2 Column
    cardToInsert.add(Tile.YELLOW);
    cardToInsert.add(Tile.LBLUE);
    bookshelfCase.addTiles(cardToInsert.subList(3,5),1);

    // 3 Column
    cardToInsert.add(Tile.LBLUE);
    cardToInsert.add(Tile.GREEN);
    cardToInsert.add(Tile.LBLUE);
    bookshelfCase.addTiles(cardToInsert.subList(5,8),2);

    // 4 Column
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.WHITE);
    bookshelfCase.addTiles(cardToInsert.subList(8,11),3);

    cardToInsert.add(Tile.WHITE);
    bookshelfCase.addTiles(cardToInsert.subList(11,12),3);

    // 5 Column
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.PINK);
    cardToInsert.add(Tile.WHITE);
    bookshelfCase.addTiles(cardToInsert.subList(12,15),4);

    cardToInsert.add(Tile.WHITE);
    bookshelfCase.addTiles(cardToInsert.subList(15,16),4);

    assertEquals(14, bookshelfCase.getFreeCells());

    // Assigning the test Bookshelf to the Current Player
    Player currentPlayer = game2.getCurrentPlayer();
    currentPlayer.setBookshelf(bookshelfCase);
    assertEquals(currentPlayer.getBookshelf(),bookshelfCase);

    // Checking the Bookshelf for: Personal Goals, Common Goals,
    currentPlayer.checkBookshelf(game2.getCommonGoals());
    int scoreCheckBookshelf = currentPlayer.getPoints();

    assertEquals(2*8, currentPlayer.getCommonGoalPoints());
    assertEquals(2, currentPlayer.getPersonalGoalPoints());
    assertEquals(2*3, currentPlayer.getAdjacentPoints());
    assertEquals(currentPlayer.getPoints() , scoreCheckBookshelf);
  }
}
