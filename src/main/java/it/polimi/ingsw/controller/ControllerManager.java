package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.goals.*;
import java.util.*;
import java.util.function.Predicate;

/**
 * Class that handles the games creation
 */
public class ControllerManager {
    private static ControllerManager instance = null;
    private final List<GameController> controllers;
    private final List<String> lobby;
    private Integer counterGames;
    private Integer currentGameNumPlayers;
    private final List<CommonGoal> commonGoals;
    private final List<PersonalGoal> personalGoals;
    public static Integer MIN_NUM_PLAYERS;
    public static Integer MAX_NUM_PLAYERS;

    /**
     * Private constructor to implement the Singleton design patter
     */
    private ControllerManager() {
        this.controllers = new ArrayList<GameController>();
        this.lobby = new ArrayList<String>();
        this.counterGames = 0;
        this.currentGameNumPlayers = 0;
        this.commonGoals = new ArrayList<CommonGoal>();
        this.personalGoals = new ArrayList<PersonalGoal>();
        ControllerManager.MIN_NUM_PLAYERS = 2;
        ControllerManager.MAX_NUM_PLAYERS = 4;

        this.initializePersonalGoals();
        this.initializeCommonGoals();
    }

    /**
     * Getter of ControllerManager's instance
     * @return current ControllerManager's instance if present, otherwise it creates and returns a new instance
     */
    public static ControllerManager getInstance() {
        if (instance == null) {
            instance = new ControllerManager();
        }
        return instance;
    }

    /**
     * Getter of the lobby
     * @return the lobby
     */
    public List<String> getLobby() {
        return this.lobby;
    }

    /**
     * Getter of the controllers
     * @return list of controllers
     */
    public List<GameController> getControllers() {
        return this.controllers;
    }

    /**
     * Getter of number of players we're currently searching for in order to create a new game
     * @return the number of players we're currently searching for
     */
    public Integer getCurrentGameNumPlayers() {
        return this.currentGameNumPlayers;
    }

    /**
     * Creates a new controller that handles the evolution of the new game
     * @param players list containing the names of the players who will play the game
     */
    private void addGame(List<String> players){
        Collections.shuffle(personalGoals);
        Collections.shuffle(commonGoals);

        Game newGame = new Game(counterGames, this.lobby, commonGoals.subList(0,2), personalGoals.subList(0,players.size()));
        // newView = new View();
        this.controllers.add(new GameController(newGame)); // newView
        this.counterGames++;
        this.lobby.clear();
        this.currentGameNumPlayers = MIN_NUM_PLAYERS;
    }

    /**
     * Adds a new player to the lobby
     * @param nickname of the player
     * @param numPlayers number of the players who will participate in a game (only the first player joining the lobby can specify that number)
     */
    public boolean addPlayer(String nickname, Integer numPlayers) {
        // Checks for possible nickname duplicates
        for(GameController gc : this.controllers) {
            if(gc.getModel().isNicknameTaken(nickname)) {
                return false;
            }
        }
        if(this.lobby.contains(nickname)) {
            return false;
        }
        if(this.lobby.isEmpty()) {
            if(numPlayers >= MIN_NUM_PLAYERS && numPlayers <= MAX_NUM_PLAYERS) {
                this.currentGameNumPlayers = numPlayers;
                this.lobby.add(nickname);
            } else {
                return false;
            }
        } else {
            this.lobby.add(nickname);
            if(this.lobby.size() == this.currentGameNumPlayers) {
                this.addGame(this.lobby);
            }
        }
        return true;
    }

    /**
     * Removes a player from the lobby
     * @param nickname of the player which must be removed
     */
    public void removePlayer(String nickname) {
        this.lobby.remove(nickname);
    }

    /**
     * Creates the personal goals of the game
     */
    private void initializePersonalGoals() {

        List<Position> positions = new ArrayList<Position>();
        List<CardType> cardTypes = new ArrayList<CardType>();
        Map<Integer, Integer> rewards = new HashMap<Integer, Integer>();

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

        this.personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
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

        this.personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
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

        this.personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
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

        this.personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();


        //Personal Goal x5
        positions.add(new Position(1 ,1 ) );
        cardTypes.add(CardType.LBLUE);

        positions.add(new Position( 3,1 ) );
        cardTypes.add(CardType.BLUE);

        positions.add(new Position( 3,2 ) );
        cardTypes.add(CardType.WHITE);

        positions.add(new Position( 4,4 ) );
        cardTypes.add(CardType.PINK);

        positions.add(new Position( 5,0 ) );
        cardTypes.add(CardType.YELLOW);

        positions.add(new Position( 5,3 ) );
        cardTypes.add(CardType.GREEN);

        this.personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();


        //Personal Goal x6
        positions.add(new Position( 0, 2) );
        cardTypes.add(CardType.LBLUE);

        positions.add(new Position( 0, 4) );
        cardTypes.add(CardType.GREEN);

        positions.add(new Position( 2, 3) );
        cardTypes.add(CardType.WHITE);

        positions.add(new Position( 4, 1) );
        cardTypes.add(CardType.YELLOW);

        positions.add(new Position( 4, 3) );
        cardTypes.add(CardType.BLUE);

        positions.add(new Position( 5, 0) );
        cardTypes.add(CardType.PINK);

        this.personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();


        //Personal Goal x7
        positions.add(new Position(0 , 0) );
        cardTypes.add(CardType.GREEN);

        positions.add(new Position(1 ,3 ) );
        cardTypes.add(CardType.BLUE);

        positions.add(new Position(2 , 1) );
        cardTypes.add(CardType.PINK);

        positions.add(new Position(3 ,0 ) );
        cardTypes.add(CardType.LBLUE);

        positions.add(new Position( 4, 4) );
        cardTypes.add(CardType.YELLOW);

        positions.add(new Position(5 ,2 ) );
        cardTypes.add(CardType.WHITE);

        this.personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();

        //Personal Goal x8
        positions.add(new Position( 0,4 ) );
        cardTypes.add(CardType.BLUE);

        positions.add(new Position(1 ,1 ) );
        cardTypes.add(CardType.GREEN);

        positions.add(new Position( 2, 2) );
        cardTypes.add(CardType.LBLUE);

        positions.add(new Position(3 ,0 ) );
        cardTypes.add(CardType.PINK);

        positions.add(new Position( 4,3 ) );
        cardTypes.add(CardType.WHITE);

        positions.add(new Position(5 , 3) );
        cardTypes.add(CardType.YELLOW);

        this.personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();

        //Personal Goal x9
        positions.add(new Position( 0, 2) );
        cardTypes.add(CardType.YELLOW);

        positions.add(new Position( 2,2 ) );
        cardTypes.add(CardType.GREEN);

        positions.add(new Position(3 , 4) );
        cardTypes.add(CardType.WHITE);

        positions.add(new Position( 4, 1) );
        cardTypes.add(CardType.LBLUE);

        positions.add(new Position(4 ,4 ) );
        cardTypes.add(CardType.PINK);

        positions.add(new Position(5 , 0) );
        cardTypes.add(CardType.BLUE);

        this.personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();

        //Personal Goal x10
        positions.add(new Position( 0,4 ) );
        cardTypes.add(CardType.LBLUE);

        positions.add(new Position( 1, 1) );
        cardTypes.add(CardType.YELLOW);

        positions.add(new Position(2 , 0) );
        cardTypes.add(CardType.WHITE);

        positions.add(new Position( 3,3 ) );
        cardTypes.add(CardType.GREEN);

        positions.add(new Position(4 ,1 ) );
        cardTypes.add(CardType.BLUE);

        positions.add(new Position(5 , 3) );
        cardTypes.add(CardType.PINK);

        this.personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();

        //Personal Goal x11
        positions.add(new Position(0 ,2 ) );
        cardTypes.add(CardType.PINK);

        positions.add(new Position( 1,1 ) );
        cardTypes.add(CardType.WHITE);

        positions.add(new Position( 2,0 ) );
        cardTypes.add(CardType.YELLOW);

        positions.add(new Position( 3, 2) );
        cardTypes.add(CardType.BLUE);

        positions.add(new Position( 4,4 ) );
        cardTypes.add(CardType.GREEN);

        positions.add(new Position(5 ,3 ) );
        cardTypes.add(CardType.LBLUE);

        this.personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();

        //Personal Goal x12
        positions.add(new Position(0 ,2 ) );
        cardTypes.add(CardType.WHITE);

        positions.add(new Position( 1,1 ) );
        cardTypes.add(CardType.PINK);

        positions.add(new Position( 2,2 ) );
        cardTypes.add(CardType.BLUE);

        positions.add(new Position( 3, 3) );
        cardTypes.add(CardType.LBLUE);

        positions.add(new Position( 4,4 ) );
        cardTypes.add(CardType.YELLOW);

        positions.add(new Position( 5, 0) );
        cardTypes.add(CardType.GREEN);

        this.personalGoals.add(new PersonalGoal(positions, cardTypes, rewards));
        positions.clear();
        cardTypes.clear();

    }

    /**
     * Creates the common goals of the game
     */
    private void initializeCommonGoals(){

        Predicate<Bookshelf> bookshelfPredicate;

        // Common Goal: Ladder Goal
        bookshelfPredicate = new LadderGoal();
        this.commonGoals.add(new CommonGoal("Ladder Goal", bookshelfPredicate));

        // Common Goal : Lots Different Columns Goal
        bookshelfPredicate = new DifferentTypesGoal(6, Bookshelf.getRows(), 2, CheckMode.VERTICAL);
        this.commonGoals.add(new CommonGoal("Lots Different Columns",  bookshelfPredicate));

        // Common Goal: Lots Different Rows Goal
        bookshelfPredicate = new DifferentTypesGoal(5, Bookshelf.getColumns(), 2, CheckMode.HORIZONTAL);
        this.commonGoals.add(new CommonGoal("Lots Different Rows",  bookshelfPredicate));

        // Common Goal : Few Different Columns Goal
        bookshelfPredicate = new DifferentTypesGoal(1, 3, 3, CheckMode.VERTICAL);
        this.commonGoals.add(new CommonGoal("Few Different Columns",  bookshelfPredicate));

        // Common Goal: Few Different Rows Goal
        bookshelfPredicate = new DifferentTypesGoal(1, 3, 4, CheckMode.HORIZONTAL);
        this.commonGoals.add(new CommonGoal("Few Different Rows",  bookshelfPredicate));

        // Common Goal: Same Kind Diagonal Goal
        bookshelfPredicate = new SameKindDiagonalGoal();
        this.commonGoals.add(new CommonGoal("Same Kind Diagonal",  bookshelfPredicate));

        // Common Goal: Same Kind Groups Goal
        bookshelfPredicate = new SameKindGroupsGoal(6, 2);
        this.commonGoals.add(new CommonGoal("Same Kind Groups (6 groups of 2 tails each)",  bookshelfPredicate));

        // Common Goal: Same Kind Groups Goal
        bookshelfPredicate = new SameKindGroupsGoal(4, 4);
        this.commonGoals.add(new CommonGoal("Same Kind Groups (4 groups of 4 tails each)",  bookshelfPredicate));

        // Common Goal: Same Kind N Goal
        bookshelfPredicate = new SameKindNGoal(8);
        this.commonGoals.add(new CommonGoal("Same Kind N",  bookshelfPredicate));

        // Common Goal: Same Kind Square Goal
        bookshelfPredicate = new SameKindSquareGoal();
        this.commonGoals.add(new CommonGoal("Same Kind Square",  bookshelfPredicate));

        // Common Goal: Same Kind X Goal
        bookshelfPredicate = new SameKindXGoal();
        this.commonGoals.add(new CommonGoal("Same Kind X",  bookshelfPredicate));

        // Common Goal: Given Positions Goal
        List<Position> positionList = new ArrayList<>();
        positionList.add(new Position(0,0));
        positionList.add(new Position(0,4));
        positionList.add(new Position(5,0));
        positionList.add(new Position(5,4));
        bookshelfPredicate = new GivenPositionsGoal(positionList);
        this.commonGoals.add(new CommonGoal("Given Positions",  bookshelfPredicate));
    }

    /**
     * Ends the game for the players
     * @param controller controller that handles the game to end
     */
    public void endGame(GameController controller) {
        controller.endGame();
        this.controllers.remove(controller);
    }
}

