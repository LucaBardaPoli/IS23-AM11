package it.polimi.ingsw.model;

import it.polimi.ingsw.model.goals.CheckMode;
import it.polimi.ingsw.model.goals.DifferentTypesGoal;
import it.polimi.ingsw.model.goals.LadderGoal;
import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;

import static org.junit.Assert.*;

public class GameTest {

    // Initializes the variables for every test in order to avoid mistakes
    @Deprecated
    private void InitializeGames(Game game2, Game game3, Game game4) {
        //Game ID & List of Players
        List<String> players2 = new ArrayList<>(List.of("Pluto", "Pippo"));
        List<String> players3 = new ArrayList<>(List.of("Raffaele", "Michael", "Simone"));
        List<String> players4 = new ArrayList<>(List.of("Luca", "Francesca", "Gaetano", "Mariam"));

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

        game2 = new Game(players2, commonGoals, personalGoals.subList(0, 2));
        //game2.getBoard().fillBoard(new Bag());

        game3 = new Game(players3, commonGoals, personalGoals.subList(0, 3));
        //game3.getBoard().fillBoard(new Bag());

        game4 = new Game(players4, commonGoals, personalGoals);
        //game4.getBoard().fillBoard(new Bag());

    }

    // Initializes a game with 2 players
    private Game InitializeGames2(){
        //Game ID & List of Players
        List<String> players2 = new ArrayList<>(List.of("Pluto", "Pippo"));

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

    // Initializes a game with 3 players
    private Game InitializeGames3(){
        //Game ID & List of Players
        List<String> players3 = new ArrayList<>(List.of("Raffaele", "Michael", "Simone"));

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

        return new Game(players3, commonGoals, personalGoals.subList(0, 3));
        //game2.getBoard().fillBoard(new Bag());
    }

    // Initializes a game with 4 players
    private Game InitializeGames4(){
        //Game ID & List of Players
        List<String> players4 = new ArrayList<>(List.of("Luca", "Francesca", "Gaetano", "Mariam"));

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

        return new Game(players4, commonGoals, personalGoals);
        //game2.getBoard().fillBoard(new Bag());
    }

    @Test
    public void testPickHorizontalLine(){
        Game game2;
        game2 = InitializeGames2();

        // Selectable cards in a game of 2 players
        Position positionToTest1 = new Position(1,0);
        Position positionToTest2 = new Position(1,1);

        List<Tile> cardTypesList = new ArrayList<>();

        game2.pickTile(positionToTest1);
        assert(game2.getBoard().getTile(positionToTest1) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest1));

        game2.pickTile(positionToTest2);
        assert(game2.getBoard().getTile(positionToTest2) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest2));

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game2.confirmPick());
        // Confirm the column where to insert the selected cards
        assert (game2.confirmColumn(0));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedTiles(game2.getPickedTiles());

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game2.getCurrentPlayer());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getTile(new Position(5,0)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getTile(new Position(4,0)),cardTypesList.get(1));
    }

    @Test
    public void testPickVerticalLine(){
        Game game2;
        game2 = InitializeGames2();
        // Carte Prendibili in GAME2
        Position positionToTest1 = new Position(6,2);
        Position positionToTest2 = new Position(7,2);


        List<Tile> cardTypesList = new ArrayList<>();

        game2.pickTile(positionToTest1);
        assert(game2.getBoard().getTile(positionToTest1) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest1));

        game2.pickTile(positionToTest2);
        assert(game2.getBoard().getTile(positionToTest2) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest2));

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game2.confirmPick());
        // Confirm the column where to insert the selected cards
        assert (game2.confirmColumn(1));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedTiles(game2.getPickedTiles());


        assertNotSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getTile(new Position(5,1)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getTile(new Position(4,1)),cardTypesList.get(1));

    }

    @Test
    public void testPickSingleCard(){
        Game game2;

        game2 = InitializeGames2();

        // Carte Prendibili in GAME2
        Position positionToTest1 = new Position(5,3);

        List<Tile> cardTypesList = new ArrayList<>();

        game2.pickTile(positionToTest1);
        assert(game2.getBoard().getTile(positionToTest1) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest1));

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game2.confirmPick());
        // Confirm the column where to insert the selected cards
        assert (game2.confirmColumn(2));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedTiles(game2.getPickedTiles());

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getTile(new Position(5,2)),cardTypesList.get(0));

    }

    @Test
    public void testPickNoSpaceLeftInColumns(){
        Game game2;

        game2 = InitializeGames2();

        Bookshelf bookshelf = new Bookshelf();
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE), 0);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE), 0);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE), 1);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE), 1);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE), 2);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE), 2);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE), 3);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE), 3);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE), 4);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE), 4);
        game2.getPlayer(game2.getCurrentPlayer().getNickname()).get().setBookshelf(bookshelf);

        // Carte Prendibili in GAME2
        Position positionToTest1 = new Position(1,0);
        Position positionToTest2 = new Position(1,1);

        assertNotEquals(game2.pickTile(positionToTest1), Tile.EMPTY);
        assertEquals(game2.pickTile(positionToTest2), Tile.EMPTY);
    }

    @Test
    public void testPickInvalidSequence1(){
        Game game2;
        game2 = InitializeGames2();

        // Picks a certain card and then picks other cards that are misaligned with the first one selected
        Position positionToTest1 = new Position(1,0);
        Position positionToTest2 = new Position(7,1);
        Position positionToTest3 = new Position(6,1);
        Position positionToTest4 = new Position(4,2);

        List<Tile> cardTypesList = new ArrayList<>();

        game2.pickTile(positionToTest1);
        assert(game2.getBoard().getTile(positionToTest1) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest1));

        game2.pickTile(positionToTest2);
        assert(game2.getBoard().getTile(positionToTest2) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest2));

        game2.pickTile(positionToTest3);
        assert(game2.getBoard().getTile(positionToTest3) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest3));

        game2.pickTile(positionToTest3);
        assert(game2.getBoard().getTile(positionToTest3) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest3));

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game2.confirmPick());
        // Confirm the column where to insert the selected cards
        assert (game2.confirmColumn(3));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedTiles(game2.getPickedTiles());

        // Check if the cards which are misaligned with the first are still on the board
        assertFalse(game2.getBoard().getTile(positionToTest1) != Tile.EMPTY);
        assert(game2.getBoard().getTile(positionToTest2) != Tile.EMPTY);
        assert(game2.getBoard().getTile(positionToTest3) != Tile.EMPTY);
        assert(game2.getBoard().getTile(positionToTest4) != Tile.EMPTY);

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getTile(new Position(5,3)),cardTypesList.get(0));

        assertNotSame(currentPlayerBookshelf.getTile(new Position(4,3)),cardTypesList.get(1));
        assertEquals(currentPlayerBookshelf.getTile(new Position(4,3)),Tile.EMPTY);

        assertNotSame(currentPlayerBookshelf.getTile(new Position(3,3)),cardTypesList.get(2));
        assertEquals(currentPlayerBookshelf.getTile(new Position(3,3)),Tile.EMPTY);

        assertNotSame(currentPlayerBookshelf.getTile(new Position(2,3)),cardTypesList.get(3));
        assertEquals(currentPlayerBookshelf.getTile(new Position(2,3)),Tile.EMPTY);

    }

    @Test
    public void testPickInvalidSequence2(){

        // Picks cards which cannot be selected at this moment because they don't have any free side.
        Game game2;

        game2 = InitializeGames2();

        Position positionToTest1 = new Position(3,1);
        Position positionToTest2 = new Position(4,1);
        Position positionToTest3 = new Position(2,1);

        List<Tile> cardTypesList = new ArrayList<>();

        game2.pickTile(positionToTest1);
        assert(game2.getBoard().getTile(positionToTest1) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest1));

        game2.pickTile(positionToTest2);
        assert(game2.getBoard().getTile(positionToTest2) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest2));

        game2.pickTile(positionToTest3);
        assert(game2.getBoard().getTile(positionToTest3) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest3));

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assertFalse (game2.confirmPick());
        // Confirm the column where to insert the selected cards
        assertFalse (game2.confirmColumn(4));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedTiles(game2.getPickedTiles());

        // Check if the card which is misaligned with the first is still on the board
        assert(game2.getBoard().getTile(positionToTest1) != Tile.EMPTY);
        assert(game2.getBoard().getTile(positionToTest2) != Tile.EMPTY);
        assert(game2.getBoard().getTile(positionToTest3) != Tile.EMPTY);

        // Check if the player is the same, because if he didn't select any card, he cannot end his turn
        assertSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        assertNotSame(currentPlayerBookshelf.getTile(new Position(4,4)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getTile(new Position(4,4)),Tile.EMPTY);

        assertNotSame(currentPlayerBookshelf.getTile(new Position(3,4)),cardTypesList.get(1));
        assertEquals(currentPlayerBookshelf.getTile(new Position(3,4)),Tile.EMPTY);

        assertNotSame(currentPlayerBookshelf.getTile(new Position(2,4)),cardTypesList.get(2));
        assertEquals(currentPlayerBookshelf.getTile(new Position(2,4)),Tile.EMPTY);

    }

    @Test
    public void testPickInvalidSequence3(){
        // Picks cards in Diagonal
        Game game2;
        game2 = InitializeGames2();

        Position positionToTest1 = new Position(1,0);
        Position positionToTest2 = new Position(2,1);
        Position positionToTest3 = new Position(3,2);

        List<Tile> cardTypesList = new ArrayList<>();

        game2.pickTile(positionToTest1);
        assert(game2.getBoard().getTile(positionToTest1) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest1));

        game2.pickTile(positionToTest2);
        assert(game2.getBoard().getTile(positionToTest2) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest2));

        game2.pickTile(positionToTest3);
        assert(game2.getBoard().getTile(positionToTest3) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest3));

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert(game2.confirmPick());
        // Confirm the column where to insert the selected cards
        assert(game2.confirmColumn(4));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedTiles(game2.getPickedTiles());

        // Check if the card which is misaligned with the first is still on the board
        assertFalse(game2.getBoard().getTile(positionToTest1) != Tile.EMPTY);
        assert(game2.getBoard().getTile(positionToTest2) != Tile.EMPTY);
        assert(game2.getBoard().getTile(positionToTest3) != Tile.EMPTY);

        // Checks if the Turn ended correctly
        assertNotSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        assertEquals(currentPlayerBookshelf.getTile(new Position(5,4)),cardTypesList.get(0));

        assertNotSame(currentPlayerBookshelf.getTile(new Position(4,4)),cardTypesList.get(1));
        assertEquals(currentPlayerBookshelf.getTile(new Position(4,4)),Tile.EMPTY);

        assertNotSame(currentPlayerBookshelf.getTile(new Position(3,4)),cardTypesList.get(2));
        assertEquals(currentPlayerBookshelf.getTile(new Position(3,4)),Tile.EMPTY);

    }

    @Test
    public void testPickEmptySequence0(){

        // Doesn't pick any card
        Game game2;

        game2 = InitializeGames2();

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assertFalse (game2.confirmPick());
        // Confirm the column where to insert the selected cards
        assertFalse (game2.confirmColumn(5));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedTiles(game2.getPickedTiles());

        // Check if the turn changed correctly
        assertSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        assertEquals(Optional.ofNullable(currentPlayerBookshelf.getFreeCells()),
                     Optional.of(GameSettings.ROWS * GameSettings.COLUMNS ));

    }

    @Test
    public void testPickEmptySequence1(){

        // Doesn't pick any card
        Game game2;

        game2 = InitializeGames2();

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assertFalse (game2.confirmPick());
        // Confirm the column where to insert the selected cards
        assertFalse (game2.confirmColumn(5));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedTiles(game2.getPickedTiles());

        // Check if the turn changed correctly
        assertSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        assertEquals(Optional.ofNullable(currentPlayerBookshelf.getFreeCells()),
                Optional.of(GameSettings.ROWS * GameSettings.COLUMNS ));

    }

    @Test
    public void testInvalidChoice0(){

        // Picks cards which cannot be selected in a game of 2 players.
        Game game2;
        game2 = InitializeGames2();

        Position positionToTest1 = new Position(0,0);
        Position positionToTest2 = new Position(2,-1);
        Position positionToTest3 = new Position(1,2);

        game2.pickTile(positionToTest1);
        assert(game2.getBoard().getTile(positionToTest1) == Tile.EMPTY);

        game2.pickTile(positionToTest2);
        assert(game2.getBoard().getTile(positionToTest2) == Tile.EMPTY);

        game2.pickTile(positionToTest3);
        assert(game2.getBoard().getTile(positionToTest3) == Tile.EMPTY);

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assertFalse (game2.confirmPick());
        assertEquals(Optional.of(GameSettings.ROWS * GameSettings.COLUMNS ) , Optional.ofNullable(currentPlayerBookshelf.getFreeCells()));

    }

    @Test
    public void testInvalidChoice1(){
        Game game2;
        game2 = InitializeGames2();

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assertFalse (game2.confirmPick());
        assertEquals(Optional.of(GameSettings.ROWS * GameSettings.COLUMNS ) , Optional.ofNullable(currentPlayerBookshelf.getFreeCells()));

    }

    @Test
    public void testInvalidChoice2(){

        // Picks cards which cannot be selected at this moment because they don't have any free side.
        Game game2;
        game2 = InitializeGames2();

        Position positionToTest1 = new Position(3,1);
        Position positionToTest2 = new Position(4,1);
        Position positionToTest3 = new Position(2,1);

        game2.pickTile(positionToTest1);
        assert(game2.getBoard().getTile(positionToTest1) != Tile.EMPTY);

        game2.pickTile(positionToTest2);
        assert(game2.getBoard().getTile(positionToTest2) != Tile.EMPTY);

        game2.pickTile(positionToTest3);
        assert(game2.getBoard().getTile(positionToTest3) != Tile.EMPTY);

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assertFalse (game2.confirmPick());
        assertEquals(Optional.of(GameSettings.ROWS * GameSettings.COLUMNS ) , Optional.ofNullable(currentPlayerBookshelf.getFreeCells()));

    }

    @Test
    public void testValidChoice(){

        // Picks cards which cannot be selected at this moment because they don't have any free side.
        Game game2;
        game2 = InitializeGames2();

        Position positionToTest1 = new Position(1,0);

        game2.pickTile(positionToTest1);
        assert(game2.getBoard().getTile(positionToTest1) != Tile.EMPTY);

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assertTrue(game2.confirmPick());
    }

    @Test
    public void testInvalidColumn(){
        Game game2;
        game2 = InitializeGames2();

        assertFalse(game2.confirmColumn(GameSettings.COLUMNS+1));
    }

    @Test
    public void testValidUnpickCard1(){

        Game game2;

        game2 = InitializeGames2();
        // Selectable cards in a game of 2 players
        Position positionToTest1 = new Position(6,2);
        Position positionToTest2 = new Position(7,2);


        List<Tile> cardTypesList = new ArrayList<>();

        game2.pickTile(positionToTest1);
        assert(game2.getBoard().getTile(positionToTest1) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest1));

        game2.pickTile(positionToTest2);
        assert(game2.getBoard().getTile(positionToTest2) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest2));

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Removes the card specified from the selection
        game2.unpickTile(positionToTest2);
        // Assures that the card is still on the Board
        assert(game2.getBoard().getTile(positionToTest2) != Tile.EMPTY);

        // Confirm the choice of the selected cards
        assert (game2.confirmPick());
        // Confirm the column where to insert the selected cards
        assert (game2.confirmColumn(1));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedTiles(game2.getPickedTiles());

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getTile(new Position(5,1)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getTile(new Position(4,1)),Tile.EMPTY);

    }

    @Test
    public void testValidUnpickCard2(){

        Game game2;

        game2 = InitializeGames2();
        // Selectable cards in a game with 2 players
        Position positionToTest1 = new Position(6,2);
        Position positionToTest2 = new Position(7,2);

        List<Tile> cardTypesList = new ArrayList<>();

        game2.pickTile(positionToTest1);
        assert(game2.getBoard().getTile(positionToTest1) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest1));

        game2.pickTile(positionToTest2);
        assert(game2.getBoard().getTile(positionToTest2) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest2));

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Removes the card specified from the selection
        game2.unpickTile(positionToTest1);
        // Assures that the card is still on the Board
        assert(game2.getBoard().getTile(positionToTest1) != Tile.EMPTY);

        // Confirm the choice of the selected cards
        assert (game2.confirmPick());
        // Confirm the column where to insert the selected cards
        assert (game2.confirmColumn(1));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedTiles(game2.getPickedTiles());

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getTile(new Position(5,1)),cardTypesList.get(1));
        assertEquals(currentPlayerBookshelf.getTile(new Position(4,1)),Tile.EMPTY);

    }

    @Test
    public void testCompleteRound2(){
        Game game2;
        game2 = InitializeGames2();

        // Selectable cards in a game with 2 players
        Position positionToTest1 = new Position(6,2);
        Position positionToTest2 = new Position(7,2);

        List<Tile> cardTypesList = new ArrayList<>();

        game2.pickTile(positionToTest1);
        assert(game2.getBoard().getTile(positionToTest1) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest1));

        game2.pickTile(positionToTest2);
        assert(game2.getBoard().getTile(positionToTest2) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest2));

        Player currentPlayer = game2.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game2.confirmPick());
        // Confirm the column where to insert the selected cards
        assert (game2.confirmColumn(1));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedTiles(game2.getPickedTiles());

        assertNotSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getTile(new Position(5,1)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getTile(new Position(4,1)),cardTypesList.get(1));

        // The First player ended his turn and now is the turn of the other player
        // Clears the card selected from the previous player
        cardTypesList.clear();
        positionToTest1 = new Position(2,0);
        positionToTest2 = new Position(1,0);

        game2.pickTile(positionToTest1);
        assert(game2.getBoard().getTile(positionToTest1) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest1));

        game2.pickTile(positionToTest2);
        assert(game2.getBoard().getTile(positionToTest2) != Tile.EMPTY);
        cardTypesList.add(game2.getBoard().getTile(positionToTest2));

        currentPlayer = game2.getCurrentPlayer();
        currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game2.confirmPick());
        // Confirm the column where to insert the selected cards
        assert (game2.confirmColumn(1));
        // Insert the cards in the order decided before
        game2.confirmOrderSelectedTiles(game2.getPickedTiles());

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game2.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getTile(new Position(5,1)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getTile(new Position(4,1)),cardTypesList.get(1));

    }

    @Test
    public void testCompleteRound3(){
        Game game4;
        game4 = InitializeGames4();

        // Selectable cards in a game with 3 players
        Position positionToTest1 = new Position(0,0);
        Position positionToTest2 = new Position(1,0);

        List<Tile> cardTypesList = new ArrayList<>();

        game4.pickTile(positionToTest1);
        assert(game4.getBoard().getTile(positionToTest1) != Tile.EMPTY);
        cardTypesList.add(game4.getBoard().getTile(positionToTest1));

        game4.pickTile(positionToTest2);
        assert(game4.getBoard().getTile(positionToTest2) != Tile.EMPTY);
        cardTypesList.add(game4.getBoard().getTile(positionToTest2));

        Player currentPlayer = game4.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game4.confirmPick());
        // Confirm the column where to  insert the selected cards
        assert (game4.confirmColumn(1));
        // Insert the cards in the order decided before
        game4.confirmOrderSelectedTiles(game4.getPickedTiles());

        assertNotSame(currentPlayer.getNickname(), game4.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getTile(new Position(5,1)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getTile(new Position(4,1)),cardTypesList.get(1));

        // The First player ended his turn and now is the turn of the other player
        // Clears the card selected from the previous player
        cardTypesList.clear();
        positionToTest1 = new Position(8,1);
        positionToTest2 = new Position(8,2);

        game4.pickTile(positionToTest1);
        assert(game4.getBoard().getTile(positionToTest1) != Tile.EMPTY);
        cardTypesList.add(game4.getBoard().getTile(positionToTest1));

        game4.pickTile(positionToTest2);
        assert(game4.getBoard().getTile(positionToTest2) != Tile.EMPTY);
        cardTypesList.add(game4.getBoard().getTile(positionToTest2));

        currentPlayer = game4.getCurrentPlayer();
        currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game4.confirmPick());
        // Confirm the column where to insert the selected cards
        assert (game4.confirmColumn(1));
        // Insert the cards in the order decided before
        game4.confirmOrderSelectedTiles(game4.getPickedTiles());

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game4.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getTile(new Position(5,1)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getTile(new Position(4,1)),cardTypesList.get(1));

        // The Second player ended his turn and now is the turn of the other player
        // Clears the card selected from the previous player
        cardTypesList.clear();
        positionToTest1 = new Position(5,-2);
        positionToTest2 = new Position(5,-3);

        game4.pickTile(positionToTest1);
        assert(game4.getBoard().getTile(positionToTest1) != Tile.EMPTY);
        cardTypesList.add(game4.getBoard().getTile(positionToTest1));

        game4.pickTile(positionToTest2);
        assert(game4.getBoard().getTile(positionToTest2) != Tile.EMPTY);
        cardTypesList.add(game4.getBoard().getTile(positionToTest2));

        currentPlayer = game4.getCurrentPlayer();
        currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game4.confirmPick());
        // Confirm the column where to insert the selected cards
        assert (game4.confirmColumn(1));
        // Insert the cards in the order decided before
        game4.confirmOrderSelectedTiles(game4.getPickedTiles());

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game4.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getTile(new Position(5,1)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getTile(new Position(4,1)),cardTypesList.get(1));

    }

    @Test
    public void testCompleteRound4(){
        Game game4;
        game4 = InitializeGames4();

        // Selectable cards in a game with 4 players
        Position positionToTest1 = new Position(0,0);
        Position positionToTest2 = new Position(0,1);

        List<Tile> cardTypesList = new ArrayList<>();

        game4.pickTile(positionToTest1);
        assert(game4.getBoard().getTile(positionToTest1) != Tile.EMPTY);
        cardTypesList.add(game4.getBoard().getTile(positionToTest1));

        game4.pickTile(positionToTest2);
        assert(game4.getBoard().getTile(positionToTest2) != Tile.EMPTY);
        cardTypesList.add(game4.getBoard().getTile(positionToTest2));

        Player currentPlayer = game4.getCurrentPlayer();
        Bookshelf currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game4.confirmPick());
        // Confirm the column where to insert the selected cards
        assert (game4.confirmColumn(1));
        // Insert the cards in the order decided before
        game4.confirmOrderSelectedTiles(game4.getPickedTiles());

        assertNotSame(currentPlayer.getNickname(), game4.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getTile(new Position(5,1)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getTile(new Position(4,1)),cardTypesList.get(1));

        // The First player ended his turn and now is the turn of the other player
        // Clears the card selected from the previous player
        cardTypesList.clear();
        positionToTest1 = new Position(1,0);
        positionToTest2 = new Position(1, 1);
        Position positionToTest3 = new Position(1,2);

        game4.pickTile(positionToTest1);
        assert(game4.getBoard().getTile(positionToTest1) != Tile.EMPTY);
        cardTypesList.add(game4.getBoard().getTile(positionToTest1));

        game4.pickTile(positionToTest2);
        assert(game4.getBoard().getTile(positionToTest2) != Tile.EMPTY);
        cardTypesList.add(game4.getBoard().getTile(positionToTest2));

        game4.pickTile(positionToTest3);
        assert(game4.getBoard().getTile(positionToTest3) != Tile.EMPTY);
        cardTypesList.add(game4.getBoard().getTile(positionToTest3));

        currentPlayer = game4.getCurrentPlayer();
        currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game4.confirmPick());
        // Confirm the column where to insert the selected cards
        assert(game4.confirmColumn(1));
        // Insert the cards in the order decided before
        game4.confirmOrderSelectedTiles(game4.getPickedTiles());

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game4.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getTile(new Position(5,1)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getTile(new Position(4,1)),cardTypesList.get(1));

        // The Second player ended his turn and now is the turn of the other player
        // Clears the card selected from the previous player
        cardTypesList.clear();
        positionToTest1 = new Position(4,-3);
        positionToTest2 = new Position(5,-3);

        game4.pickTile(positionToTest1);
        assert(game4.getBoard().getTile(positionToTest1) != Tile.EMPTY);
        cardTypesList.add(game4.getBoard().getTile(positionToTest1));

        game4.pickTile(positionToTest2);
        assert(game4.getBoard().getTile(positionToTest2) != Tile.EMPTY);
        cardTypesList.add(game4.getBoard().getTile(positionToTest2));

        currentPlayer = game4.getCurrentPlayer();
        currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game4.confirmPick());
        // Confirm the column where to insert the selected cards
        assert (game4.confirmColumn(1));
        // Insert the cards in the order decided before
        game4.confirmOrderSelectedTiles(game4.getPickedTiles());

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game4.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getTile(new Position(5,1)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getTile(new Position(4,1)),cardTypesList.get(1));

        // The Third player ended his turn and now is the turn of the other player
        // Clears the card selected from the previous player
        cardTypesList.clear();
        positionToTest1 = new Position(3,5);
        positionToTest2 = new Position(4,5);

        game4.pickTile(positionToTest1);
        assert(game4.getBoard().getTile(positionToTest1) != Tile.EMPTY);
        cardTypesList.add(game4.getBoard().getTile(positionToTest1));

        game4.pickTile(positionToTest2);
        assert(game4.getBoard().getTile(positionToTest2) != Tile.EMPTY);
        cardTypesList.add(game4.getBoard().getTile(positionToTest2));

        currentPlayer = game4.getCurrentPlayer();
        currentPlayerBookshelf = currentPlayer.getBookshelf();
        assertNotNull(currentPlayerBookshelf);

        // Confirm the choice of the selected cards
        assert (game4.confirmPick());
        // Confirm the column where to insert the selected cards
        assert (game4.confirmColumn(1));
        // Insert the cards in the order decided before
        game4.confirmOrderSelectedTiles(game4.getPickedTiles());

        // Check if the turn changed correctly
        assertNotSame(currentPlayer.getNickname(), game4.getCurrentPlayer().getNickname());

        // Check if the insertion of the cards in the bookshelf is correct
        assertEquals(currentPlayerBookshelf.getTile(new Position(5,1)),cardTypesList.get(0));
        assertEquals(currentPlayerBookshelf.getTile(new Position(4,1)),cardTypesList.get(1));

    }

    @Test
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

    @Test
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

    @Test
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

    @Test
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

    @Test
    public void testEndGameChairPlayer() {
        Bookshelf bookshelf = new Bookshelf();
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE), 0);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE), 1);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE), 2);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE), 3);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE), 4);

        Game game = InitializeGames2();
        game.getPlayers().get(0).setBookshelf(bookshelf);

        assert(game.pickTile(new Position(1,1)) != Tile.EMPTY);
        game.confirmPick();
        assert(!game.confirmColumn(3));
        assert(!game.confirmColumn(5));
        assert(game.confirmColumn(4));
        game.confirmOrderSelectedTiles(game.getPickedTiles());

        assert(game.getPlayers().get(0).getEndGamePoint());
        assert(!game.getPlayers().get(1).getEndGamePoint());
        assert(game.getGameStatus().equals(GameStatus.PICK_CARDS));
    }

    @Test
    public void testEndGameLastPlayer() {
        Bookshelf bookshelf = new Bookshelf();
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE), 0);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE), 1);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE), 2);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE), 3);
        bookshelf.addTiles(List.of(Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE, Tile.BLUE), 4);

        Game game = InitializeGames2();
        game.getPlayers().get(1).setBookshelf(bookshelf);

        assert(game.pickTile(new Position(1,1)) != Tile.EMPTY);
        game.confirmPick();
        assert(!game.confirmColumn(5));
        assert(game.confirmColumn(0));
        game.confirmOrderSelectedTiles(game.getPickedTiles());
        assert(!game.getPlayers().get(0).getEndGamePoint());
        assert(!game.getPlayers().get(1).getEndGamePoint());
        assert(game.getGameStatus().equals(GameStatus.PICK_CARDS));

        assert(game.pickTile(new Position(1,0)) != Tile.EMPTY);
        game.confirmPick();
        assert(!game.confirmColumn(5));
        assert(!game.confirmColumn(3));
        assert(game.confirmColumn(4));
        game.confirmOrderSelectedTiles(game.getPickedTiles());
        assert(!game.getPlayers().get(0).getEndGamePoint());
        assert(game.getPlayers().get(1).getEndGamePoint());
        assert(game.getGameStatus().equals(GameStatus.UPDATE_POINTS));
    }
}
