package it.polimi.ingsw.model;
import it.polimi.ingsw.model.goals.*;
import java.util.*;
import java.util.function.Predicate;

public class GameManager implements GameManagerInterface{

    private static GameManager instance = null;

    private final List<Game> gameList;
    private final List<String> lobby;
    private Integer counterGames;
    private Integer currentGameNumPlayers;
    private final List<CommonGoal> commonGoals;
    private final List<PersonalGoal> personalGoals;

    // Costruttore invisibile

    /**
     * &#064;SingletonPattern
     * Class contructor
     */
    private GameManager() {
        this.gameList = new ArrayList<Game>();
        this.lobby = new ArrayList<String>();
        this.counterGames = 0;
        this.currentGameNumPlayers = 0;
        this.commonGoals = new ArrayList<CommonGoal>();
        this.personalGoals = new ArrayList<PersonalGoal>();

        this.initializePersonalGoals();
        this.initializeCommonGoals();
    }

    /**
     * Getter of GameManager's instance
     * @return GameManager's instance if present, otherwise it return @null
     */
    public static GameManager getInstance() {
        // Crea l'oggetto solo se NON esiste:
        if (instance == null) {
            instance = new GameManager();
        }
        return instance;
    }

    /**
     * Creates a new game
     * @param players is a list containing the names of the players who will play the new game
     */
    private void addGame(List<String> players){
        // Funzione rand
        Collections.shuffle(personalGoals);
        Collections.shuffle(commonGoals);

        this.gameList.add(new Game(counterGames, this.lobby, commonGoals.subList(0,2), personalGoals.subList(0,players.size())));
        this.counterGames++;
    }

    /**
     * Add a player to the lobby if one already exists, otherwise it creates another one
     * @param nickname of the player
     * @param numPlayers number of the players who will participate in a game (only the first player joining the lobby has the privilege to set that number)
     */
    public void addPlayer(String nickname, Integer numPlayers) {
        if (this.lobby.size() == 0) {
            this.currentGameNumPlayers = numPlayers;
            this.lobby.add(nickname);
        } else {
            this.lobby.add(nickname);
            if(this.lobby.size() == this.currentGameNumPlayers) {
                this.addGame(this.lobby);
                this.lobby.clear();
                this.currentGameNumPlayers = 0;
            }
        }
    }

    /**
     * Removes a player from the lobby
     * @param nickname of the player which must be removed
     */
    public void removePlayer(String nickname){
        this.lobby.remove(nickname);
    }

    private void initializePersonalGoals(){

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
    // instanziare staticamente

    private void initializeCommonGoals(){

        Predicate<Bookshelf> bookshelfPredicate;

        // Common Goal: Ladder Goal
        bookshelfPredicate = new LadderGoal();
        this.commonGoals.add(new CommonGoal("Ladder Goal", bookshelfPredicate));

        // Common Goal : MaxDifferentColumn Goal
        bookshelfPredicate = new MaxDifferentColumn();
        this.commonGoals.add(new CommonGoal("Max Different Column ",  bookshelfPredicate));

        // Common Goal: MaxDifferentRow Goal
        bookshelfPredicate = new MaxDifferentRow();
        this.commonGoals.add(new CommonGoal("Max Different Row",  bookshelfPredicate));

        // Common Goal: SameKindDiagonal Goal
        bookshelfPredicate = new SameKindDiagonal();
        this.commonGoals.add(new CommonGoal("Same Kind Diagonal",  bookshelfPredicate));

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
     *  Ends the game for the players
     * @param game that should be ended
     */
    @Override
    public void endGame(Game game) {
        this.gameList.remove(game);
    }
}

