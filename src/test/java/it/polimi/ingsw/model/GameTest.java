package it.polimi.ingsw.model;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.model.goals.CheckMode;
import it.polimi.ingsw.model.goals.DifferentTypesGoal;
import it.polimi.ingsw.model.goals.LadderGoal;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.*;
import java.util.function.Predicate;

public class GameTest extends TestCase{
    public static Test suite()
    {
        return new TestSuite( GameTest.class );
    }

    // Initializes the variables for every test in order to avoid mistakes

    @Deprecated
    private void InitializeGames(Game game2, Game game3, Game game4){
        //Game ID & List of Players
        Integer gameId = 0;
        List<String> players2 = new ArrayList<>(List.of("Pluto", "Pippo"));
        List<String> players3 = new ArrayList<>(List.of("Raffaele", "Michael", "Simone"));
        List<String> players4 = new ArrayList<>(List.of("Luca", "Francesca", "Gaetano", "Mariam"));

        // ## Personal Goals ##
        List<PersonalGoal> personalGoals = new ArrayList<>();

        List<Position> positions = new ArrayList<>();
        List<CardType> cardTypes = new ArrayList<>();
        Map<Integer, Integer> rewards = new HashMap<>();

        rewards.put(1,1);
        rewards.put(2,2);
        rewards.put(3,4);
        rewards.put(4,6);
        rewards.put(5,9);
        rewards.put(6,12);

        //Personal Goal x1
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

        personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();

        //Personal Goal x2
        positions.add(new Position(1 ,1 ) );
        cardTypes.add(CardType.PINK);

        positions.add(new Position(2 ,0 ) );
        cardTypes.add(CardType.GREEN);

        positions.add(new Position( 2,2 ) );
        cardTypes.add(CardType.YELLOW);

        positions.add(new Position( 3,4 ) );
        cardTypes.add(CardType.WHITE);

        positions.add(new Position( 4, 3) );
        cardTypes.add(CardType.LBLUE);

        positions.add(new Position( 5, 4) );
        cardTypes.add(CardType.BLUE);

        personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();


        //Personal Goal x3
        positions.add(new Position(1 ,0) );
        cardTypes.add(CardType.BLUE);

        positions.add(new Position(1 , 3) );
        cardTypes.add(CardType.YELLOW);

        positions.add(new Position(2 ,2 ) );
        cardTypes.add(CardType.PINK);

        positions.add(new Position(3 , 1) );
        cardTypes.add(CardType.GREEN);

        positions.add(new Position(3 , 4) );
        cardTypes.add(CardType.LBLUE);

        positions.add(new Position(5 ,0 ) );
        cardTypes.add(CardType.WHITE);

        personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();


        //Personal Goal x4
        positions.add(new Position( 0,4 ) );
        cardTypes.add(CardType.YELLOW);

        positions.add(new Position( 2, 0) );
        cardTypes.add(CardType.LBLUE);

        positions.add(new Position(2 , 2) );
        cardTypes.add(CardType.BLUE);

        positions.add(new Position( 3,3) );
        cardTypes.add(CardType.PINK);

        positions.add(new Position(4 , 1) );
        cardTypes.add(CardType.WHITE);

        positions.add(new Position(4, 2) );
        cardTypes.add(CardType.GREEN);

        personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();

        // ## Common Goals ##
        List<CommonGoal> commonGoals = new ArrayList<>();
        Predicate<Bookshelf> bookshelfPredicate;

        // Common Goal: DifferentGroupColumn Goal
        bookshelfPredicate = new DifferentTypesGoal(6,6,2, CheckMode.VERTICAL);
        commonGoals.add(new CommonGoal("Different Group Column",  bookshelfPredicate));

        // Common Goal: Ladder Goal
        bookshelfPredicate = new LadderGoal();
        commonGoals.add(new CommonGoal("Ladder Goal", bookshelfPredicate));

        game2 = new Game(gameId, players2, commonGoals, personalGoals.subList(0, 2));
        //game2.getBoard().fillBoard(new CountCards());

        game3 = new Game(gameId++, players3, commonGoals, personalGoals.subList(0, 3));
        //game3.getBoard().fillBoard(new CountCards());

        game4 = new Game(gameId++, players4, commonGoals, personalGoals);
        //game4.getBoard().fillBoard(new CountCards());

    }

    // Initializes a game with 2 players
    private Game InitializeGames2(){
        //Game ID & List of Players
        Integer gameId = 0;
        List<String> players2 = new ArrayList<>(List.of("Pluto", "Pippo"));

        // ## Personal Goals ##
        List<PersonalGoal> personalGoals = new ArrayList<>();

        List<Position> positions = new ArrayList<>();
        List<CardType> cardTypes = new ArrayList<>();
        Map<Integer, Integer> rewards = new HashMap<>();

        rewards.put(1,1);
        rewards.put(2,2);
        rewards.put(3,4);
        rewards.put(4,6);
        rewards.put(5,9);
        rewards.put(6,12);

        //Personal Goal x1
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

        personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();

        //Personal Goal x2
        positions.add(new Position(1 ,1 ) );
        cardTypes.add(CardType.PINK);

        positions.add(new Position(2 ,0 ) );
        cardTypes.add(CardType.GREEN);

        positions.add(new Position( 2,2 ) );
        cardTypes.add(CardType.YELLOW);

        positions.add(new Position( 3,4 ) );
        cardTypes.add(CardType.WHITE);

        positions.add(new Position( 4, 3) );
        cardTypes.add(CardType.LBLUE);

        positions.add(new Position( 5, 4) );
        cardTypes.add(CardType.BLUE);

        personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();


        //Personal Goal x3
        positions.add(new Position(1 ,0) );
        cardTypes.add(CardType.BLUE);

        positions.add(new Position(1 , 3) );
        cardTypes.add(CardType.YELLOW);

        positions.add(new Position(2 ,2 ) );
        cardTypes.add(CardType.PINK);

        positions.add(new Position(3 , 1) );
        cardTypes.add(CardType.GREEN);

        positions.add(new Position(3 , 4) );
        cardTypes.add(CardType.LBLUE);

        positions.add(new Position(5 ,0 ) );
        cardTypes.add(CardType.WHITE);

        personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();


        //Personal Goal x4
        positions.add(new Position( 0,4 ) );
        cardTypes.add(CardType.YELLOW);

        positions.add(new Position( 2, 0) );
        cardTypes.add(CardType.LBLUE);

        positions.add(new Position(2 , 2) );
        cardTypes.add(CardType.BLUE);

        positions.add(new Position( 3,3) );
        cardTypes.add(CardType.PINK);

        positions.add(new Position(4 , 1) );
        cardTypes.add(CardType.WHITE);

        positions.add(new Position(4, 2) );
        cardTypes.add(CardType.GREEN);

        personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();

        // ## Common Goals ##
        List<CommonGoal> commonGoals = new ArrayList<>();
        Predicate<Bookshelf> bookshelfPredicate;

        // Common Goal: DifferentGroupColumn Goal
        bookshelfPredicate = new DifferentTypesGoal(6,6,2, CheckMode.VERTICAL);
        commonGoals.add(new CommonGoal("Different Group Column",  bookshelfPredicate));

        // Common Goal: Ladder Goal
        bookshelfPredicate = new LadderGoal();
        commonGoals.add(new CommonGoal("Ladder Goal", bookshelfPredicate));

        return new Game(gameId, players2, commonGoals, personalGoals.subList(0, 2));
        //game2.getBoard().fillBoard(new CountCards());
    }

    // Initializes a game with 3 players
    private Game InitializeGames3(){
        //Game ID & List of Players
        Integer gameId = 0;
        List<String> players3 = new ArrayList<>(List.of("Raffaele", "Michael", "Simone"));

        // ## Personal Goals ##
        List<PersonalGoal> personalGoals = new ArrayList<>();

        List<Position> positions = new ArrayList<>();
        List<CardType> cardTypes = new ArrayList<>();
        Map<Integer, Integer> rewards = new HashMap<>();

        rewards.put(1,1);
        rewards.put(2,2);
        rewards.put(3,4);
        rewards.put(4,6);
        rewards.put(5,9);
        rewards.put(6,12);

        //Personal Goal x1
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

        personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();

        //Personal Goal x2
        positions.add(new Position(1 ,1 ) );
        cardTypes.add(CardType.PINK);

        positions.add(new Position(2 ,0 ) );
        cardTypes.add(CardType.GREEN);

        positions.add(new Position( 2,2 ) );
        cardTypes.add(CardType.YELLOW);

        positions.add(new Position( 3,4 ) );
        cardTypes.add(CardType.WHITE);

        positions.add(new Position( 4, 3) );
        cardTypes.add(CardType.LBLUE);

        positions.add(new Position( 5, 4) );
        cardTypes.add(CardType.BLUE);

        personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();


        //Personal Goal x3
        positions.add(new Position(1 ,0) );
        cardTypes.add(CardType.BLUE);

        positions.add(new Position(1 , 3) );
        cardTypes.add(CardType.YELLOW);

        positions.add(new Position(2 ,2 ) );
        cardTypes.add(CardType.PINK);

        positions.add(new Position(3 , 1) );
        cardTypes.add(CardType.GREEN);

        positions.add(new Position(3 , 4) );
        cardTypes.add(CardType.LBLUE);

        positions.add(new Position(5 ,0 ) );
        cardTypes.add(CardType.WHITE);

        personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();


        //Personal Goal x4
        positions.add(new Position( 0,4 ) );
        cardTypes.add(CardType.YELLOW);

        positions.add(new Position( 2, 0) );
        cardTypes.add(CardType.LBLUE);

        positions.add(new Position(2 , 2) );
        cardTypes.add(CardType.BLUE);

        positions.add(new Position( 3,3) );
        cardTypes.add(CardType.PINK);

        positions.add(new Position(4 , 1) );
        cardTypes.add(CardType.WHITE);

        positions.add(new Position(4, 2) );
        cardTypes.add(CardType.GREEN);

        personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();

        // ## Common Goals ##
        List<CommonGoal> commonGoals = new ArrayList<>();
        Predicate<Bookshelf> bookshelfPredicate;

        // Common Goal: DifferentGroupColumn Goal
        bookshelfPredicate = new DifferentTypesGoal(6,6,2, CheckMode.VERTICAL);
        commonGoals.add(new CommonGoal("Different Group Column",  bookshelfPredicate));

        // Common Goal: Ladder Goal
        bookshelfPredicate = new LadderGoal();
        commonGoals.add(new CommonGoal("Ladder Goal", bookshelfPredicate));

        return new Game(gameId, players3, commonGoals, personalGoals.subList(0, 3));
        //game2.getBoard().fillBoard(new CountCards());
    }

    // Initializes a game with 4 players
    private Game InitializeGames4(){
        //Game ID & List of Players
        Integer gameId = 0;
        List<String> players4 = new ArrayList<>(List.of("Luca", "Francesca", "Gaetano", "Mariam"));

        // ## Personal Goals ##
        List<PersonalGoal> personalGoals = new ArrayList<>();

        List<Position> positions = new ArrayList<>();
        List<CardType> cardTypes = new ArrayList<>();
        Map<Integer, Integer> rewards = new HashMap<>();

        rewards.put(1,1);
        rewards.put(2,2);
        rewards.put(3,4);
        rewards.put(4,6);
        rewards.put(5,9);
        rewards.put(6,12);

        //Personal Goal x1
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

        personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();

        //Personal Goal x2
        positions.add(new Position(1 ,1 ) );
        cardTypes.add(CardType.PINK);

        positions.add(new Position(2 ,0 ) );
        cardTypes.add(CardType.GREEN);

        positions.add(new Position( 2,2 ) );
        cardTypes.add(CardType.YELLOW);

        positions.add(new Position( 3,4 ) );
        cardTypes.add(CardType.WHITE);

        positions.add(new Position( 4, 3) );
        cardTypes.add(CardType.LBLUE);

        positions.add(new Position( 5, 4) );
        cardTypes.add(CardType.BLUE);

        personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();


        //Personal Goal x3
        positions.add(new Position(1 ,0) );
        cardTypes.add(CardType.BLUE);

        positions.add(new Position(1 , 3) );
        cardTypes.add(CardType.YELLOW);

        positions.add(new Position(2 ,2 ) );
        cardTypes.add(CardType.PINK);

        positions.add(new Position(3 , 1) );
        cardTypes.add(CardType.GREEN);

        positions.add(new Position(3 , 4) );
        cardTypes.add(CardType.LBLUE);

        positions.add(new Position(5 ,0 ) );
        cardTypes.add(CardType.WHITE);

        personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();


        //Personal Goal x4
        positions.add(new Position( 0,4 ) );
        cardTypes.add(CardType.YELLOW);

        positions.add(new Position( 2, 0) );
        cardTypes.add(CardType.LBLUE);

        positions.add(new Position(2 , 2) );
        cardTypes.add(CardType.BLUE);

        positions.add(new Position( 3,3) );
        cardTypes.add(CardType.PINK);

        positions.add(new Position(4 , 1) );
        cardTypes.add(CardType.WHITE);

        positions.add(new Position(4, 2) );
        cardTypes.add(CardType.GREEN);

        personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();

        // ## Common Goals ##
        List<CommonGoal> commonGoals = new ArrayList<>();
        Predicate<Bookshelf> bookshelfPredicate;

        // Common Goal: DifferentGroupColumn Goal
        bookshelfPredicate = new DifferentTypesGoal(6,6,2, CheckMode.VERTICAL);
        commonGoals.add(new CommonGoal("Different Group Column",  bookshelfPredicate));

        // Common Goal: Ladder Goal
        bookshelfPredicate = new LadderGoal();
        commonGoals.add(new CommonGoal("Ladder Goal", bookshelfPredicate));

        return new Game(gameId, players4, commonGoals, personalGoals);
        //game2.getBoard().fillBoard(new CountCards());
    }

    // Game(Integer id, List<String> players, List<CommonGoal> commonGoals, List<PersonalGoal> personalGoals)
    public GameTest( String testName ) {
        super( testName );

    }

    public void testPickHorizontalLine(){
        Game game2;
        game2 = InitializeGames2();

        // Selectable cards in a game of 2 players
        Position positionToTest1 = new Position(1,0);
        Position positionToTest2 = new Position(1,1);

        List<Optional<CardType>> cardTypesList = new ArrayList<>();

        game2.pickCard(positionToTest1);
        assert(game2.getBoard().getCard(positionToTest1).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest1));

        game2.pickCard(positionToTest2);
        assert(game2.getBoard().getCard(positionToTest2).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest2));

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game2.confirmChoice());
        // Confirm the column where to insert the selected cards
        assert (game2.confirmColumn(0));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedCards();

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game2.getCurrentPlayer());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getCell(new Position(5,0)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getCell(new Position(4,0)),cardTypesList.get(1));


    }

    public void testPickVerticalLine(){
        Game game2;
        game2 = InitializeGames2();
        // Carte Prendibili in GAME2
        Position positionToTest1 = new Position(6,2);
        Position positionToTest2 = new Position(7,2);


        List<Optional<CardType>> cardTypesList = new ArrayList<>();

        game2.pickCard(positionToTest1);
        assert(game2.getBoard().getCard(positionToTest1).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest1));

        game2.pickCard(positionToTest2);
        assert(game2.getBoard().getCard(positionToTest2).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest2));

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game2.confirmChoice());
        // Confirm the column where to insert the selected cards
        assert (game2.confirmColumn(1));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedCards();


        assertNotSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getCell(new Position(5,1)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getCell(new Position(4,1)),cardTypesList.get(1));

    }

    public void testPickSingleCard(){
        Game game2;

        game2 = InitializeGames2();

        // Carte Prendibili in GAME2
        Position positionToTest1 = new Position(5,3);

        List<Optional<CardType>> cardTypesList = new ArrayList<>();

        game2.pickCard(positionToTest1);
        assert(game2.getBoard().getCard(positionToTest1).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest1));

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game2.confirmChoice());
        // Confirm the column where to insert the selected cards
        assert (game2.confirmColumn(2));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedCards();

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getCell(new Position(5,2)),cardTypesList.get(0));

    }

    public void testPickInvalidSequence1(){
        Game game2;
        game2 = InitializeGames2();

        // Picks a certain card and then picks other cards that are misaligned with the first one selected
        Position positionToTest1 = new Position(1,0);
        Position positionToTest2 = new Position(7,1);
        Position positionToTest3 = new Position(6,1);
        Position positionToTest4 = new Position(4,2);

        List<Optional<CardType>> cardTypesList = new ArrayList<>();

        game2.pickCard(positionToTest1);
        assert(game2.getBoard().getCard(positionToTest1).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest1));

        game2.pickCard(positionToTest2);
        assert(game2.getBoard().getCard(positionToTest2).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest2));

        game2.pickCard(positionToTest3);
        assert(game2.getBoard().getCard(positionToTest3).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest3));

        game2.pickCard(positionToTest3);
        assert(game2.getBoard().getCard(positionToTest3).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest3));

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game2.confirmChoice());
        // Confirm the column where to insert the selected cards
        assert (game2.confirmColumn(3));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedCards();

        // Check if the cards which are misaligned with the first are still on the board
        assertFalse(game2.getBoard().getCard(positionToTest1).isPresent());
        assert(game2.getBoard().getCard(positionToTest2).isPresent());
        assert(game2.getBoard().getCard(positionToTest3).isPresent());
        assert(game2.getBoard().getCard(positionToTest4).isPresent());

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getCell(new Position(5,3)),cardTypesList.get(0));

        assertNotSame(currentPlayerBookshelf.getCell(new Position(4,3)),cardTypesList.get(1));
        assertEquals(currentPlayerBookshelf.getCell(new Position(4,3)),Optional.empty());

        assertNotSame(currentPlayerBookshelf.getCell(new Position(3,3)),cardTypesList.get(2));
        assertEquals(currentPlayerBookshelf.getCell(new Position(3,3)),Optional.empty());

        assertNotSame(currentPlayerBookshelf.getCell(new Position(2,3)),cardTypesList.get(3));
        assertEquals(currentPlayerBookshelf.getCell(new Position(2,3)),Optional.empty());

    }

    public void testPickInvalidSequence2(){

        // Picks cards which cannot be selected at this moment because they don't have any free side.
        Game game2;

        game2 = InitializeGames2();

        Position positionToTest1 = new Position(3,1);
        Position positionToTest2 = new Position(4,1);
        Position positionToTest3 = new Position(2,1);

        List<Optional<CardType>> cardTypesList = new ArrayList<>();

        game2.pickCard(positionToTest1);
        assert(game2.getBoard().getCard(positionToTest1).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest1));

        game2.pickCard(positionToTest2);
        assert(game2.getBoard().getCard(positionToTest2).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest2));

        game2.pickCard(positionToTest3);
        assert(game2.getBoard().getCard(positionToTest3).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest3));

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assertFalse (game2.confirmChoice());
        // Confirm the column where to insert the selected cards
        assertFalse (game2.confirmColumn(4));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedCards();

        // Check if the card which is misaligned with the first is still on the board
        assert(game2.getBoard().getCard(positionToTest1).isPresent());
        assert(game2.getBoard().getCard(positionToTest2).isPresent());
        assert(game2.getBoard().getCard(positionToTest3).isPresent());

        // Check if the player is the same, because if he didn't select any card, he cannot end his turn
        assertSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        assertNotSame(currentPlayerBookshelf.getCell(new Position(4,4)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getCell(new Position(4,4)),Optional.empty());

        assertNotSame(currentPlayerBookshelf.getCell(new Position(3,4)),cardTypesList.get(1));
        assertEquals(currentPlayerBookshelf.getCell(new Position(3,4)),Optional.empty());

        assertNotSame(currentPlayerBookshelf.getCell(new Position(2,4)),cardTypesList.get(2));
        assertEquals(currentPlayerBookshelf.getCell(new Position(2,4)),Optional.empty());

    }

    public void testPickInvalidSequence3(){
        // Picks cards in Diagonal
        Game game2;
        game2 = InitializeGames2();

        Position positionToTest1 = new Position(1,0);
        Position positionToTest2 = new Position(2,1);
        Position positionToTest3 = new Position(3,2);

        List<Optional<CardType>> cardTypesList = new ArrayList<>();

        game2.pickCard(positionToTest1);
        assert(game2.getBoard().getCard(positionToTest1).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest1));

        game2.pickCard(positionToTest2);
        assert(game2.getBoard().getCard(positionToTest2).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest2));

        game2.pickCard(positionToTest3);
        assert(game2.getBoard().getCard(positionToTest3).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest3));

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert(game2.confirmChoice());
        // Confirm the column where to insert the selected cards
        assert(game2.confirmColumn(4));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedCards();

        // Check if the card which is misaligned with the first is still on the board
        assertFalse(game2.getBoard().getCard(positionToTest1).isPresent());
        assert(game2.getBoard().getCard(positionToTest2).isPresent());
        assert(game2.getBoard().getCard(positionToTest3).isPresent());

        // Checks if the Turn ended correctly
        assertNotSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        assertEquals(currentPlayerBookshelf.getCell(new Position(5,4)),cardTypesList.get(0));

        assertNotSame(currentPlayerBookshelf.getCell(new Position(4,4)),cardTypesList.get(1));
        assertEquals(currentPlayerBookshelf.getCell(new Position(4,4)),Optional.empty());

        assertNotSame(currentPlayerBookshelf.getCell(new Position(3,4)),cardTypesList.get(2));
        assertEquals(currentPlayerBookshelf.getCell(new Position(3,4)),Optional.empty());

    }


    public void testPickEmptySequence0(){

        // Doesn't pick any card
        Game game2;

        game2 = InitializeGames2();

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assertFalse (game2.confirmChoice());
        // Confirm the column where to insert the selected cards
        assertFalse (game2.confirmColumn(5));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedCards();

        // Check if the turn changed correctly
        assertSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        assertEquals(Optional.ofNullable(currentPlayerBookshelf.getFreeCells()),
                     Optional.of(Bookshelf.getRows() * Bookshelf.getColumns() ));

    }

    public void testPickEmptySequence1(){

        // Doesn't pick any card
        Game game2;

        game2 = InitializeGames2();

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assertFalse (game2.confirmChoice());
        // Confirm the column where to insert the selected cards
        assertFalse (game2.confirmColumn(5));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedCards();

        // Check if the turn changed correctly
        assertSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        assertEquals(Optional.ofNullable(currentPlayerBookshelf.getFreeCells()),
                Optional.of(Bookshelf.getRows() * Bookshelf.getColumns() ));

    }

    public void testInvalidChoice0(){

        // Picks cards which cannot be selected in a game of 2 players.
        Game game2;
        game2 = InitializeGames2();

        Position positionToTest1 = new Position(0,0);
        Position positionToTest2 = new Position(2,-1);
        Position positionToTest3 = new Position(1,2);

        game2.pickCard(positionToTest1);
        assert(game2.getBoard().getCard(positionToTest1).isEmpty());

        game2.pickCard(positionToTest2);
        assert(game2.getBoard().getCard(positionToTest2).isEmpty());

        game2.pickCard(positionToTest3);
        assert(game2.getBoard().getCard(positionToTest3).isEmpty());

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assertFalse (game2.confirmChoice());
        assertEquals(Optional.of(Bookshelf.getRows() * Bookshelf.getColumns() ) , Optional.ofNullable(currentPlayerBookshelf.getFreeCells()));

    }


    public void testInvalidChoice1(){
        Game game2;
        game2 = InitializeGames2();

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assertFalse (game2.confirmChoice());
        assertEquals(Optional.of(Bookshelf.getRows() * Bookshelf.getColumns() ) , Optional.ofNullable(currentPlayerBookshelf.getFreeCells()));

    }

    public void testInvalidChoice2(){

        // Picks cards which cannot be selected at this moment because they don't have any free side.
        Game game2;
        game2 = InitializeGames2();

        Position positionToTest1 = new Position(3,1);
        Position positionToTest2 = new Position(4,1);
        Position positionToTest3 = new Position(2,1);

        game2.pickCard(positionToTest1);
        assert(game2.getBoard().getCard(positionToTest1).isPresent());

        game2.pickCard(positionToTest2);
        assert(game2.getBoard().getCard(positionToTest2).isPresent());

        game2.pickCard(positionToTest3);
        assert(game2.getBoard().getCard(positionToTest3).isPresent());

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assertFalse (game2.confirmChoice());
        assertEquals(Optional.of(Bookshelf.getRows() * Bookshelf.getColumns() ) , Optional.ofNullable(currentPlayerBookshelf.getFreeCells()));

    }

    public void testValidChoice(){

        // Picks cards which cannot be selected at this moment because they don't have any free side.
        Game game2;
        game2 = InitializeGames2();

        Position positionToTest1 = new Position(1,0);

        game2.pickCard(positionToTest1);
        assert(game2.getBoard().getCard(positionToTest1).isPresent());

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assertTrue(game2.confirmChoice());
    }

    public void testInvalidColumn(){
        Game game2;
        game2 = InitializeGames2();

        assertFalse(game2.confirmColumn(Bookshelf.getColumns()+1));
    }

    public void testValidRearrangeCards(){
        Game game2;

        game2 = InitializeGames2();
        // Carte Prendibili in GAME2
        Position positionToTest1 = new Position(1,0);
        Position positionToTest2 = new Position(1,1);

        List<Optional<CardType>> cardTypesList = new ArrayList<>();

        game2.pickCard(positionToTest1);
        assert(game2.getBoard().getCard(positionToTest1).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest1));

        game2.pickCard(positionToTest2);
        assert(game2.getBoard().getCard(positionToTest2).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest2));

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game2.confirmChoice());
        // Confirm the column where to insert the selected cards
        assert (game2.confirmColumn(0));

        // Puts the first card selected in the last position
        List<CardType> rearrangedCards = game2.rearrangeCards(0);
        assertEquals(rearrangedCards.get(0),cardTypesList.get(1).get());
        assertEquals(rearrangedCards.get(1),cardTypesList.get(0).get());

        // Insert the cards in the order decided before
        game2.confirmOrderSelectedCards();

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getCell(new Position(5,0)),cardTypesList.get(1));
        assertEquals(currentPlayerBookshelf.getCell(new Position(4,0)),cardTypesList.get(0));

    }

    public void testDoubleRearrangeCards(){
        Game game2;

        game2 = InitializeGames2();
        // Carte Prendibili in GAME2
        Position positionToTest1 = new Position(1,0);
        Position positionToTest2 = new Position(1,1);

        List<Optional<CardType>> cardTypesList = new ArrayList<>();

        game2.pickCard(positionToTest1);
        assert(game2.getBoard().getCard(positionToTest1).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest1));

        game2.pickCard(positionToTest2);
        assert(game2.getBoard().getCard(positionToTest2).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest2));

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game2.confirmChoice());
        // Confirm the column where to insert the selected cards
        assert (game2.confirmColumn(0));

        // Puts the first card selected in the last position
        List<CardType> rearrangedCards = game2.rearrangeCards(0);
        assertEquals(rearrangedCards.get(0),cardTypesList.get(1).get());
        assertEquals(rearrangedCards.get(1),cardTypesList.get(0).get());

        rearrangedCards = game2.rearrangeCards(0);
        assertEquals(rearrangedCards.get(0),cardTypesList.get(0).get());
        assertEquals(rearrangedCards.get(1),cardTypesList.get(1).get());

        // Insert the cards in the order decided before
        game2.confirmOrderSelectedCards();

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game2.getCurrentPlayer());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getCell(new Position(5,0)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getCell(new Position(4,0)),cardTypesList.get(1));

    }

    public void testValidRemoveCard1(){

        Game game2;

        game2 = InitializeGames2();
        // Selectable cards in a game of 2 players
        Position positionToTest1 = new Position(6,2);
        Position positionToTest2 = new Position(7,2);


        List<Optional<CardType>> cardTypesList = new ArrayList<>();

        game2.pickCard(positionToTest1);
        assert(game2.getBoard().getCard(positionToTest1).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest1));

        game2.pickCard(positionToTest2);
        assert(game2.getBoard().getCard(positionToTest2).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest2));

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Removes the card specified from the selection
        game2.removeCard(positionToTest2);
        // Assures that the card is still on the Board
        assert(game2.getBoard().getCard(positionToTest2).isPresent());

        // Confirm the choice of the selected cards
        assert (game2.confirmChoice());
        // Confirm the column where to insert the selected cards
        assert (game2.confirmColumn(1));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedCards();

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getCell(new Position(5,1)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getCell(new Position(4,1)),Optional.empty());

    }

    public void testValidRemoveCard2(){

        Game game2;

        game2 = InitializeGames2();
        // Selectable cards in a game with 2 players
        Position positionToTest1 = new Position(6,2);
        Position positionToTest2 = new Position(7,2);

        List<Optional<CardType>> cardTypesList = new ArrayList<>();

        game2.pickCard(positionToTest1);
        assert(game2.getBoard().getCard(positionToTest1).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest1));

        game2.pickCard(positionToTest2);
        assert(game2.getBoard().getCard(positionToTest2).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest2));

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Removes the card specified from the selection
        game2.removeCard(positionToTest1);
        // Assures that the card is still on the Board
        assert(game2.getBoard().getCard(positionToTest1).isPresent());

        // Confirm the choice of the selected cards
        assert (game2.confirmChoice());
        // Confirm the column where to insert the selected cards
        assert (game2.confirmColumn(1));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedCards();

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getCell(new Position(5,1)),cardTypesList.get(1));
        assertEquals(currentPlayerBookshelf.getCell(new Position(4,1)),Optional.empty());

    }

    public void testCompleteRound2(){
        Game game2;
        game2 = InitializeGames2();

        // Initializes the game controller
        GameController gameController = new GameController(game2);
        game2.setGameController(gameController);

        // Selectable cards in a game with 2 players
        Position positionToTest1 = new Position(6,2);
        Position positionToTest2 = new Position(7,2);

        List<Optional<CardType>> cardTypesList = new ArrayList<>();

        game2.pickCard(positionToTest1);
        assert(game2.getBoard().getCard(positionToTest1).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest1));

        game2.pickCard(positionToTest2);
        assert(game2.getBoard().getCard(positionToTest2).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest2));

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game2.confirmChoice());
        // Confirm the column where to insert the selected cards
        assert (game2.confirmColumn(1));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedCards();

        assertNotSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getCell(new Position(5,1)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getCell(new Position(4,1)),cardTypesList.get(1));

        // The First player ended his turn and now is the turn of the other player
        // Clears the card selected from the previous player
        cardTypesList.clear();
        positionToTest1 = new Position(2,0);
        positionToTest2 = new Position(1,0);

        game2.pickCard(positionToTest1);
        assert(game2.getBoard().getCard(positionToTest1).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest1));

        game2.pickCard(positionToTest2);
        assert(game2.getBoard().getCard(positionToTest2).isPresent());
        cardTypesList.add(game2.getBoard().getCard(positionToTest2));

        currentPlayer = game2.getCurrentPlayer();
        currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game2.confirmChoice());
        // Confirm the column where to insert the selected cards
        assert (game2.confirmColumn(1));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedCards();

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getCell(new Position(5,1)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getCell(new Position(4,1)),cardTypesList.get(1));

    }

    public void testCompleteRound3(){
        Game game4;
        game4 = InitializeGames4();

        // Initializes the game controller
        GameController gameController = new GameController(game4);
        game4.setGameController(gameController);

        // Selectable cards in a game with 3 players
        Position positionToTest1 = new Position(0,0);
        Position positionToTest2 = new Position(1,0);

        List<Optional<CardType>> cardTypesList = new ArrayList<>();

        game4.pickCard(positionToTest1);
        assert(game4.getBoard().getCard(positionToTest1).isPresent());
        cardTypesList.add(game4.getBoard().getCard(positionToTest1));

        game4.pickCard(positionToTest2);
        assert(game4.getBoard().getCard(positionToTest2).isPresent());
        cardTypesList.add(game4.getBoard().getCard(positionToTest2));

        Player currentPlayer = game4.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game4.confirmChoice());
        // Confirm the column where to  insert the selected cards
        assert (game4.confirmColumn(1));
        // Insert the cards in the order decided before
        game4.confirmOrderSelectedCards();

        assertNotSame(currentPlayer.getNickname(), game4.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getCell(new Position(5,1)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getCell(new Position(4,1)),cardTypesList.get(1));

        // The First player ended his turn and now is the turn of the other player
        // Clears the card selected from the previous player
        cardTypesList.clear();
        positionToTest1 = new Position(8,1);
        positionToTest2 = new Position(8,2);

        game4.pickCard(positionToTest1);
        assert(game4.getBoard().getCard(positionToTest1).isPresent());
        cardTypesList.add(game4.getBoard().getCard(positionToTest1));

        game4.pickCard(positionToTest2);
        assert(game4.getBoard().getCard(positionToTest2).isPresent());
        cardTypesList.add(game4.getBoard().getCard(positionToTest2));

        currentPlayer = game4.getCurrentPlayer();
        currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game4.confirmChoice());
        // Confirm the column where to insert the selected cards
        assert (game4.confirmColumn(1));
        // Insert the cards in the order decided before
        game4.confirmOrderSelectedCards();

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game4.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getCell(new Position(5,1)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getCell(new Position(4,1)),cardTypesList.get(1));

        // The Second player ended his turn and now is the turn of the other player
        // Clears the card selected from the previous player
        cardTypesList.clear();
        positionToTest1 = new Position(5,-2);
        positionToTest2 = new Position(5,-3);

        game4.pickCard(positionToTest1);
        assert(game4.getBoard().getCard(positionToTest1).isPresent());
        cardTypesList.add(game4.getBoard().getCard(positionToTest1));

        game4.pickCard(positionToTest2);
        assert(game4.getBoard().getCard(positionToTest2).isPresent());
        cardTypesList.add(game4.getBoard().getCard(positionToTest2));

        currentPlayer = game4.getCurrentPlayer();
        currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game4.confirmChoice());
        // Confirm the column where to insert the selected cards
        assert (game4.confirmColumn(1));
        // Insert the cards in the order decided before
        game4.confirmOrderSelectedCards();

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game4.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getCell(new Position(5,1)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getCell(new Position(4,1)),cardTypesList.get(1));

    }

    public void testCompleteRound4(){
        Game game4;
        game4 = InitializeGames4();

        // Initializes the game controller
        GameController gameController = new GameController(game4);
        game4.setGameController(gameController);

        // Selectable cards in a game with 4 players
        Position positionToTest1 = new Position(0,0);
        Position positionToTest2 = new Position(0,1);

        List<Optional<CardType>> cardTypesList = new ArrayList<>();

        game4.pickCard(positionToTest1);
        assert(game4.getBoard().getCard(positionToTest1).isPresent());
        cardTypesList.add(game4.getBoard().getCard(positionToTest1));

        game4.pickCard(positionToTest2);
        assert(game4.getBoard().getCard(positionToTest2).isPresent());
        cardTypesList.add(game4.getBoard().getCard(positionToTest2));

        Player currentPlayer = game4.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game4.confirmChoice());
        // Confirm the column where to insert the selected cards
        assert (game4.confirmColumn(1));
        // Insert the cards in the order decided before
        game4.confirmOrderSelectedCards();

        assertNotSame(currentPlayer.getNickname(), game4.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getCell(new Position(5,1)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getCell(new Position(4,1)),cardTypesList.get(1));

        // The First player ended his turn and now is the turn of the other player
        // Clears the card selected from the previous player
        cardTypesList.clear();
        positionToTest1 = new Position(8,1);
        positionToTest2 = new Position(8,2);

        game4.pickCard(positionToTest1);
        assert(game4.getBoard().getCard(positionToTest1).isPresent());
        cardTypesList.add(game4.getBoard().getCard(positionToTest1));

        game4.pickCard(positionToTest2);
        assert(game4.getBoard().getCard(positionToTest2).isPresent());
        cardTypesList.add(game4.getBoard().getCard(positionToTest2));

        currentPlayer = game4.getCurrentPlayer();
        currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game4.confirmChoice());
        // Confirm the column where to insert the selected cards
        assert (game4.confirmColumn(1));
        // Insert the cards in the order decided before
        game4.confirmOrderSelectedCards();

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game4.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getCell(new Position(5,1)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getCell(new Position(4,1)),cardTypesList.get(1));

        // The Second player ended his turn and now is the turn of the other player
        // Clears the card selected from the previous player
        cardTypesList.clear();
        positionToTest1 = new Position(4,-3);
        positionToTest2 = new Position(5,-3);

        game4.pickCard(positionToTest1);
        assert(game4.getBoard().getCard(positionToTest1).isPresent());
        cardTypesList.add(game4.getBoard().getCard(positionToTest1));

        game4.pickCard(positionToTest2);
        assert(game4.getBoard().getCard(positionToTest2).isPresent());
        cardTypesList.add(game4.getBoard().getCard(positionToTest2));

        currentPlayer = game4.getCurrentPlayer();
        currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game4.confirmChoice());
        // Confirm the column where to insert the selected cards
        assert (game4.confirmColumn(1));
        // Insert the cards in the order decided before
        game4.confirmOrderSelectedCards();

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game4.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getCell(new Position(5,1)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getCell(new Position(4,1)),cardTypesList.get(1));

        // The Third player ended his turn and now is the turn of the other player
        // Clears the card selected from the previous player
        cardTypesList.clear();
        positionToTest1 = new Position(3,5);
        positionToTest2 = new Position(4,5);

        game4.pickCard(positionToTest1);
        assert(game4.getBoard().getCard(positionToTest1).isPresent());
        cardTypesList.add(game4.getBoard().getCard(positionToTest1));

        game4.pickCard(positionToTest2);
        assert(game4.getBoard().getCard(positionToTest2).isPresent());
        cardTypesList.add(game4.getBoard().getCard(positionToTest2));

        currentPlayer = game4.getCurrentPlayer();
        currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game4.confirmChoice());
        // Confirm the column where to insert the selected cards
        assert (game4.confirmColumn(1));
        // Insert the cards in the order decided before
        game4.confirmOrderSelectedCards();

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game4.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getCell(new Position(5,1)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getCell(new Position(4,1)),cardTypesList.get(1));

    }

    public void testUntakenNickname1(){
    Game game2, game3, game4;
    game2 = InitializeGames2();
    game3 = InitializeGames3();
    game4 = InitializeGames4();

    // nameToCheck is the name that should be checked
    String nameToCheck = "Margara";

    assertFalse(game2.isNicknameTaken(nameToCheck));
    assertFalse(game3.isNicknameTaken(nameToCheck));
    assertFalse(game4.isNicknameTaken(nameToCheck));
    }

    public void testUntakenNickname2(){
        Game game2, game3, game4;
        game2 = InitializeGames2();
        game3 = InitializeGames3();
        game4 = InitializeGames4();

        // nameToCheck is the name that should be checked
        String nameToCheck = "Conti";

        assertFalse(game2.isNicknameTaken(nameToCheck));
        assertFalse(game3.isNicknameTaken(nameToCheck));
        assertFalse(game4.isNicknameTaken(nameToCheck));
    }

    public void testTakenNickname1(){
        Game game2, game3, game4;
        game2 = InitializeGames2();
        game3 = InitializeGames3();
        game4 = InitializeGames4();

        // nameToCheck is the name that should be checked
        String nameToCheck = "Pluto";

        assert(game2.isNicknameTaken(nameToCheck));
        assertFalse(game3.isNicknameTaken(nameToCheck));
        assertFalse(game4.isNicknameTaken(nameToCheck));
    }

    public void testTakenNickname2(){
        Game game2, game3, game4;
        game2 = InitializeGames2();
        game3 = InitializeGames3();
        game4 = InitializeGames4();

        // nameToCheck is the name that should be checked
        String nameToCheck = "Francesca";

        assertFalse(game2.isNicknameTaken(nameToCheck));
        assertFalse(game3.isNicknameTaken(nameToCheck));
        assert(game4.isNicknameTaken(nameToCheck));
    }


}
