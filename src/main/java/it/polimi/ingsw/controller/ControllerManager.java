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
    private static Integer MIN_NUM_PLAYERS;
    private static Integer MAX_NUM_PLAYERS;

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
     * Creates a new controller that handles the evolution of the new game
     * @param players list containing the names of the players who will play the game
     */
    private void addGame(List<String> players){
        Collections.shuffle(personalGoals);
        Collections.shuffle(commonGoals);

        GameController newController = new GameController();
        Game newGame = new Game(counterGames, this.lobby, commonGoals.subList(0,2), personalGoals.subList(0,players.size()), newController);
        this.controllers.add(newController);
        this.counterGames++;
        this.lobby.clear();
        this.currentGameNumPlayers = 0;
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

        // Common Goal: DifferentGroupColumn Goal

        // Common Goal: DifferentGroupRow Goal

        // Common Goal: Ladder Goal
        bookshelfPredicate = new LadderGoal();
        this.commonGoals.add(new CommonGoal("Ladder Goal", bookshelfPredicate));

        // Common Goal : MaxDifferentColumn Goal
        bookshelfPredicate = new MaxDifferentColumn();
        this.commonGoals.add(new CommonGoal("Max Different Column",  bookshelfPredicate));

        // Common Goal: MaxDifferentRow Goal
        bookshelfPredicate = new MaxDifferentRow();
        this.commonGoals.add(new CommonGoal("Max Different Row",  bookshelfPredicate));

        // Common Goal: SameKindDiagonal Goal
        bookshelfPredicate = new SameKindDiagonal();
        this.commonGoals.add(new CommonGoal("Same Kind Diagonal",  bookshelfPredicate));

        // Common Goal: SameKindGroups Goal
        bookshelfPredicate = new SameKindGroups(6, 2);
        this.commonGoals.add(new CommonGoal("Same Kind Groups (6 groups of 2 tails each)",  bookshelfPredicate));

        // Common Goal: SameKindGroups Goal
        bookshelfPredicate = new SameKindGroups(4, 4);
        this.commonGoals.add(new CommonGoal("Same Kind Groups (4 groups of 4 tails each)",  bookshelfPredicate));

        // Common Goal: SameKindN Goal
        bookshelfPredicate = new SameKindN(8);
        this.commonGoals.add(new CommonGoal("Same Kind N",  bookshelfPredicate));

        // Common Goal: SameKindSquare Goal
        bookshelfPredicate = new SameKindSquare();
        this.commonGoals.add(new CommonGoal("Same Kind Square",  bookshelfPredicate));

        // Common Goal: SameKindX Goal
        bookshelfPredicate = new SameKindX();
        this.commonGoals.add(new CommonGoal("Same Kind X",  bookshelfPredicate));

        // Common Goal: GivenPositions Goal
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

