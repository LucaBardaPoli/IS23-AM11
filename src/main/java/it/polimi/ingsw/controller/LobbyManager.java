package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.goals.*;
import it.polimi.ingsw.network.message.LobbyInfoMessage;
import it.polimi.ingsw.network.server.ClientHandler;
import it.polimi.ingsw.network.server.EventListener;

import java.io.Serializable;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Class that handles the creation of the games
 */
public class LobbyManager implements Serializable {
    private static LobbyManager instance;
    private final List<ClientHandler> lobby;
    private final List<Game> games;
    private Integer currentGameNumPlayers;
    private final List<CommonGoal> commonGoals;
    private final List<PersonalGoal> personalGoals;

    /**
     * Private constructor to implement the Singleton design patter
     */
    private LobbyManager() {
        this.lobby = new ArrayList<>();
        this.games = new ArrayList<>();
        this.currentGameNumPlayers = 0;
        this.commonGoals = new ArrayList<>();
        this.personalGoals = new ArrayList<>();

        this.initializePersonalGoals();
        this.initializeCommonGoals();
    }

    /**
     * Getter of ControllerManager's instance
     * @return current ControllerManager's instance if present, otherwise it creates and returns a new instance
     */
    public static LobbyManager getInstance() {
        if(LobbyManager.instance == null) {
            LobbyManager.instance = new LobbyManager();
        }
        return LobbyManager.instance;
    }

    /**
     * Getter of the lobby
     * @return the lobby
     */
    public List<ClientHandler> getLobby() {
        return this.lobby;
    }

    /**
     * Getter of number of players we're currently searching for in order to create a new game
     * @return the number of players we're currently searching for
     */
    public Integer getCurrentGameNumPlayers() {
        return this.currentGameNumPlayers;
    }

    /**
     * Getter of the list of games (used for testing)
     * @return list of games
     */
    public List<Game> getGames() {
        return this.games;
    }

    /**
     * Creates a new game and notifies the controllers of all the clients playing the game
     */
    private void addGame() {
        Collections.shuffle(this.personalGoals);
        Collections.shuffle(this.commonGoals);

        Game newGame = new Game(this.lobby.stream().map(ClientHandler::getNickname).collect(Collectors.toList()), new ArrayList<>(this.commonGoals.subList(0,2)), new ArrayList<>(this.personalGoals.subList(0, this.lobby.size())));
        this.games.add(newGame);
        EventListener eventListener = new EventListener();
        for(ClientHandler c : this.lobby) {
            c.initGame(newGame, new ArrayList<>(this.lobby));
            eventListener.addListener(c);
            c.setEventListener(eventListener);
        }
        this.lobby.clear();
        this.currentGameNumPlayers = GameSettings.MIN_NUM_PLAYERS;
    }

    /**
     * Checks if the given nickname is already used
     * @param nickname new nickname
     * @return true whether the nickname is already used
     */
    public boolean isNicknameTaken(String nickname) {
        if(this.lobby.stream().map(ClientHandler::getNickname).anyMatch((x) -> x.equals(nickname))) {
            return true;
        }
        for(Game g : this.games) {
            if(g.getPlayers().stream().map(Player::getNickname).anyMatch((x) -> x.equals(nickname))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks whether the given number of players is valid or not
     * @param numPlayers number of players
     * @return true if the number is valid
     */
    public boolean isNumPlayersValid(int numPlayers) {
        return (numPlayers >= GameSettings.MIN_NUM_PLAYERS && numPlayers <= GameSettings.MAX_NUM_PLAYERS);
    }

    /**
     * Adds a new player to the lobby
     * @param client that will participate in the game
     */
    public void addPlayer(ClientHandler client) {
        if(isNicknameTaken(client.getNickname())) {
            return;
        }
        if(this.lobby.isEmpty()) {
            if(client.getNumPlayers() >= GameSettings.MIN_NUM_PLAYERS && client.getNumPlayers() <= GameSettings.MAX_NUM_PLAYERS) {
                this.currentGameNumPlayers = client.getNumPlayers();
                this.lobby.add(client);
                for(ClientHandler c: this.lobby) {
                    c.sendMessage(new LobbyInfoMessage(this.currentGameNumPlayers, this.lobby.stream().map(ClientHandler::getNickname).collect(Collectors.toList()), true, client.getNickname()));
                }
            }
        } else {
            this.lobby.add(client);
            for(ClientHandler c: this.lobby){
                c.sendMessage(new LobbyInfoMessage(this.currentGameNumPlayers, this.lobby.stream().map(ClientHandler::getNickname).collect(Collectors.toList()), true, client.getNickname()));
            }
            if(this.lobby.size() == this.currentGameNumPlayers) {
                this.addGame();
            }
        }
    }

    /**
     * Removes a player from the lobby
     * @param client of the player which must be removed
     */
    public void removePlayer(ClientHandler client) {
        this.lobby.remove(client);
        for(ClientHandler c: this.lobby){
            c.sendMessage(new LobbyInfoMessage(this.currentGameNumPlayers, this.lobby.stream().map(ClientHandler::getNickname).collect(Collectors.toList()), false, client.getNickname()));
        }
    }

    /**
     * Creates the personal goals of the game
     */
    private void initializePersonalGoals() {

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

        this.personalGoals.add(new PersonalGoal(1, positions, tiles, rewards));
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

        this.personalGoals.add(new PersonalGoal(2, positions, tiles, rewards));
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

        this.personalGoals.add(new PersonalGoal(3, positions, tiles, rewards));
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

        this.personalGoals.add(new PersonalGoal(4, positions, tiles, rewards));
        positions.clear();
        tiles.clear();


        //Personal Goal x5
        positions.add(new Position(1 ,1 ) );
        tiles.add(Tile.LBLUE);

        positions.add(new Position( 3,1 ) );
        tiles.add(Tile.BLUE);

        positions.add(new Position( 3,2 ) );
        tiles.add(Tile.WHITE);

        positions.add(new Position( 4,4 ) );
        tiles.add(Tile.PINK);

        positions.add(new Position( 5,0 ) );
        tiles.add(Tile.YELLOW);

        positions.add(new Position( 5,3 ) );
        tiles.add(Tile.GREEN);

        this.personalGoals.add(new PersonalGoal(5, positions, tiles, rewards));
        positions.clear();
        tiles.clear();


        //Personal Goal x6
        positions.add(new Position( 0, 2) );
        tiles.add(Tile.LBLUE);

        positions.add(new Position( 0, 4) );
        tiles.add(Tile.GREEN);

        positions.add(new Position( 2, 3) );
        tiles.add(Tile.WHITE);

        positions.add(new Position( 4, 1) );
        tiles.add(Tile.YELLOW);

        positions.add(new Position( 4, 3) );
        tiles.add(Tile.BLUE);

        positions.add(new Position( 5, 0) );
        tiles.add(Tile.PINK);

        this.personalGoals.add(new PersonalGoal(6, positions, tiles, rewards));
        positions.clear();
        tiles.clear();


        //Personal Goal x7
        positions.add(new Position(0 , 0) );
        tiles.add(Tile.GREEN);

        positions.add(new Position(1 ,3 ) );
        tiles.add(Tile.BLUE);

        positions.add(new Position(2 , 1) );
        tiles.add(Tile.PINK);

        positions.add(new Position(3 ,0 ) );
        tiles.add(Tile.LBLUE);

        positions.add(new Position( 4, 4) );
        tiles.add(Tile.YELLOW);

        positions.add(new Position(5 ,2 ) );
        tiles.add(Tile.WHITE);

        this.personalGoals.add(new PersonalGoal(7, positions, tiles, rewards));
        positions.clear();
        tiles.clear();


        //Personal Goal x8
        positions.add(new Position( 0,4 ) );
        tiles.add(Tile.BLUE);

        positions.add(new Position(1 ,1 ) );
        tiles.add(Tile.GREEN);

        positions.add(new Position( 2, 2) );
        tiles.add(Tile.LBLUE);

        positions.add(new Position(3 ,0 ) );
        tiles.add(Tile.PINK);

        positions.add(new Position( 4,3 ) );
        tiles.add(Tile.WHITE);

        positions.add(new Position(5 , 3) );
        tiles.add(Tile.YELLOW);

        this.personalGoals.add(new PersonalGoal(8, positions, tiles, rewards));
        positions.clear();
        tiles.clear();


        //Personal Goal x9
        positions.add(new Position( 0, 2) );
        tiles.add(Tile.YELLOW);

        positions.add(new Position( 2,2 ) );
        tiles.add(Tile.GREEN);

        positions.add(new Position(3 , 4) );
        tiles.add(Tile.WHITE);

        positions.add(new Position( 4, 1) );
        tiles.add(Tile.LBLUE);

        positions.add(new Position(4 ,4 ) );
        tiles.add(Tile.PINK);

        positions.add(new Position(5 , 0) );
        tiles.add(Tile.BLUE);

        this.personalGoals.add(new PersonalGoal(9, positions, tiles, rewards));
        positions.clear();
        tiles.clear();


        //Personal Goal x10
        positions.add(new Position( 0,4 ) );
        tiles.add(Tile.LBLUE);

        positions.add(new Position( 1, 1) );
        tiles.add(Tile.YELLOW);

        positions.add(new Position(2 , 0) );
        tiles.add(Tile.WHITE);

        positions.add(new Position( 3,3 ) );
        tiles.add(Tile.GREEN);

        positions.add(new Position(4 ,1 ) );
        tiles.add(Tile.BLUE);

        positions.add(new Position(5 , 3) );
        tiles.add(Tile.PINK);

        this.personalGoals.add(new PersonalGoal(10, positions, tiles, rewards));
        positions.clear();
        tiles.clear();


        //Personal Goal x11
        positions.add(new Position(0 ,2 ) );
        tiles.add(Tile.PINK);

        positions.add(new Position( 1,1 ) );
        tiles.add(Tile.WHITE);

        positions.add(new Position( 2,0 ) );
        tiles.add(Tile.YELLOW);

        positions.add(new Position( 3, 2) );
        tiles.add(Tile.BLUE);

        positions.add(new Position( 4,4 ) );
        tiles.add(Tile.GREEN);

        positions.add(new Position(5 ,3 ) );
        tiles.add(Tile.LBLUE);

        this.personalGoals.add(new PersonalGoal(11, positions, tiles, rewards));
        positions.clear();
        tiles.clear();


        //Personal Goal x12
        positions.add(new Position(0 ,2 ) );
        tiles.add(Tile.WHITE);

        positions.add(new Position( 1,1 ) );
        tiles.add(Tile.PINK);

        positions.add(new Position( 2,2 ) );
        tiles.add(Tile.BLUE);

        positions.add(new Position( 3, 3) );
        tiles.add(Tile.LBLUE);

        positions.add(new Position( 4,4 ) );
        tiles.add(Tile.YELLOW);

        positions.add(new Position( 5, 0) );
        tiles.add(Tile.GREEN);

        this.personalGoals.add(new PersonalGoal(12, positions, tiles, rewards));
        positions.clear();
        tiles.clear();
    }

    /**
     * Creates the common goals of the game
     */
    private void initializeCommonGoals(){

        Predicate<Bookshelf> bookshelfPredicate;

        // Common Goal: Ladder Goal
        bookshelfPredicate = new LadderGoal();
        this.commonGoals.add(new CommonGoal(12, "Ladder goal", bookshelfPredicate));

        // Common Goal : Lots Different Columns Goal
        bookshelfPredicate = new DifferentTypesGoal(6, GameSettings.ROWS, 2, CheckMode.VERTICAL);
        this.commonGoals.add(new CommonGoal(2, "2 columns formed by 6 different types of tiles",  bookshelfPredicate));

        // Common Goal: Lots Different Rows Goal
        bookshelfPredicate = new DifferentTypesGoal(5, GameSettings.COLUMNS, 2, CheckMode.HORIZONTAL);
        this.commonGoals.add(new CommonGoal(6, "2 rows formed by 5 different types of tiles",  bookshelfPredicate));

        // Common Goal : Few Different Columns Goal
        bookshelfPredicate = new DifferentTypesGoal(1, 3, 3, CheckMode.VERTICAL);
        this.commonGoals.add(new CommonGoal(5, "3 full columns with max 3 different tile types",  bookshelfPredicate));

        // Common Goal: Few Different Rows Goal
        bookshelfPredicate = new DifferentTypesGoal(1, 3, 4, CheckMode.HORIZONTAL);
        this.commonGoals.add(new CommonGoal(7, "4 full rows with max 3 different tile types",  bookshelfPredicate));

        // Common Goal: Same Kind Diagonal Goal
        bookshelfPredicate = new SameKindDiagonalGoal();
        this.commonGoals.add(new CommonGoal(11, "5 tiles of the same type forming a diagonal",  bookshelfPredicate));

        // Common Goal: Same Kind Groups Goal
        bookshelfPredicate = new SameKindGroupsGoal(6, 2);
        this.commonGoals.add(new CommonGoal(4, "6 groups each containing at least 2 tiles of the same type",  bookshelfPredicate));

        // Common Goal: Same Kind Groups Goal
        bookshelfPredicate = new SameKindGroupsGoal(4, 4);
        this.commonGoals.add(new CommonGoal(3, "4 groups each containing at least 4 tiles of the same type",  bookshelfPredicate));

        // Common Goal: Same Kind N Goal
        bookshelfPredicate = new SameKindNGoal(8);
        this.commonGoals.add(new CommonGoal(9, "8 tiles of the same type",  bookshelfPredicate));

        // Common Goal: Same Kind Square Goal
        bookshelfPredicate = new SameKindSquareGoal();
        this.commonGoals.add(new CommonGoal(1, "2 groups (2x2 squares) each containing 4 tiles of the same type",  bookshelfPredicate));

        // Common Goal: Same Kind X Goal
        bookshelfPredicate = new SameKindXGoal();
        this.commonGoals.add(new CommonGoal(10, "5 tiles of the same type forming an X",  bookshelfPredicate));

        // Common Goal: Given Positions Goal
        List<Position> positionList = new ArrayList<>();
        positionList.add(new Position(0,0));
        positionList.add(new Position(0,GameSettings.COLUMNS-1));
        positionList.add(new Position(GameSettings.ROWS-1,0));
        positionList.add(new Position(GameSettings.ROWS-1,GameSettings.COLUMNS-1));
        bookshelfPredicate = new GivenPositionsGoal(positionList);
        this.commonGoals.add(new CommonGoal(8, "4 tiles of the same type in the 4 corners of the bookshelf",  bookshelfPredicate));
    }

    /**
     * Ends the game for the players
     * @param game to end
     */
    public void endGame(Game game) {
        this.games.remove(game);
    }
}
