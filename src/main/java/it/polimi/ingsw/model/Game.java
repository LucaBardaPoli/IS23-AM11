package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Game class that handles the game moves and the way it evolves
 */
public class Game implements Serializable {
    private final Board board;
    private final List<Player> players;
    private int turn;
    private boolean isLastTurn;
    private final List<CommonGoal> commonGoals;
    private final List<List<Token>> tokens;
    private final List<Position> pickedTilesPositions;
    private List<Tile> pickedTiles;
    private GameStatus gameStatus;
    private int currentSelectedColumn;
    private boolean endGame;

    /**
     * Class constructor
     * @param players list of nickname players
     * @param commonGoals two common goals of the game
     * @param personalGoals players personal goals
     */
    public Game(List<String> players, List<CommonGoal> commonGoals, List<PersonalGoal> personalGoals) {
        // Board creation
        this.board = new Board(players.size(), new Bag());
        this.board.fillBoard();

        // Goals creation and assignment
        this.players = new ArrayList<>();
        Collections.shuffle(personalGoals);
        for (int i = 0; i < players.size(); i++) {
            this.players.add(new Player(players.get(i), personalGoals.get(i), this));
        }
        Collections.shuffle(this.players);
        this.commonGoals = commonGoals;

        this.turn = 0;
        this.isLastTurn = false;
        this.gameStatus = GameStatus.PICK_CARDS;
        this.pickedTiles = new ArrayList<>();
        this.pickedTilesPositions = new ArrayList<>();
        this.currentSelectedColumn = 0;
        this.endGame = false;

        // Assigns tokens based on the number of players
        this.tokens = new ArrayList<>();
        List<Token> tmpTokens = new ArrayList<>();
        if (this.players.size() == 2) {
            tmpTokens.add(new Token(8));
            tmpTokens.add(new Token(4));
        } else if (this.players.size() == 3) {
            tmpTokens.add(new Token(8));
            tmpTokens.add(new Token(6));
            tmpTokens.add(new Token(4));
        } else if (this.players.size() == 4) {
            tmpTokens.add(new Token(8));
            tmpTokens.add(new Token(6));
            tmpTokens.add(new Token(4));
            tmpTokens.add(new Token(2));
        }
        for (int i = 0; i < this.commonGoals.size(); i++) {
            this.tokens.add(new ArrayList<>(tmpTokens));
        }
    }

    /**
     * Getter of the players
     * @return list of players
     */
    public List<Player> getPlayers() {
        return this.players;
    }

    /**
     * Getter of a single player
     * @param nickname nickname of the player
     * @return player
     */
    public Optional<Player> getPlayer(String nickname) {
        for (Player p : this.players) {
            if (p.getNickname().equals(nickname)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    /**
     * Getter of the common goals
     * @return common goals
     */
    public List<CommonGoal> getCommonGoals() {
        return this.commonGoals;
    }

    /**
     * Getter of the token on top af the given common goal
     * @param commonGoal common goal
     * @return token's value
     */
    public Optional<Integer> getTopToken(CommonGoal commonGoal) {
        if(this.commonGoals.contains(commonGoal)) {
            try {
                return Optional.of(this.tokens.get(this.commonGoals.indexOf(commonGoal)).get(0).getValue());
            } catch(IndexOutOfBoundsException e) {
                return Optional.of(0);
            }
        }
        return Optional.empty();
    }

    /**
     * Getter of a player's personal goal
     * @param player nickname of the player
     * @return personal goal
     */
    public Optional<PersonalGoal> getPersonalGoal(String player) {
        for (Player p : this.players) {
            if (p.getNickname().equals(player)) {
                return Optional.of(p.getPersonalGoal());
            }
        }
        return Optional.empty();
    }

    /**
     * Getter of a player's bookshelf
     * @param player nickname of the player
     * @return bookshelf
     */
    public Optional<Bookshelf> getBookshelf(String player) {
        for (Player p : this.players) {
            if (p.getNickname().equals(player)) {
                return Optional.of(p.getBookshelf());
            }
        }
        return Optional.empty();
    }

    /**
     * Getter of the game board
     * @return game board
     */
    public Board getBoard() {
        return this.board;
    }

    /**
     * Getter of the game status
     * @return game status
     */
    public GameStatus getGameStatus() {
        return this.gameStatus;
    }

    /**
     * Getter of the tiles currently picked
     * @return picked tiles
     */
    public List<Tile> getPickedTiles() {
        return this.pickedTiles;
    }

    /**
     * End game getter
     * @return end game
     */
    public boolean getEndGame() {
        return endGame;
    }

    /**
     * Last turn getter
     * @return whether it's the last turn
     */
    public boolean getIsLastTurn() {
        return isLastTurn;
    }

    /**
     * Checks if a given nickname is already taken
     * @param nickname nickname
     * @return true if exists a player with the given nickname
     */
    public boolean isNicknameTaken(String nickname) {
        for (Player p : this.players) {
            if (p.getNickname().equals(nickname)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Getter of the player who's currently playing
     * @return player
     */
    public Player getCurrentPlayer() {
        return this.players.get(this.turn);
    }

    /**
     * Getter of the last player who played
     * @return player
     */
    public Player getLastPlayer() {
        return this.players.get(Math.floorMod((this.turn - 1), this.players.size()));
    }

    /**
     * Checks if exists at least a column where to insert the picked tiles
     * @return true if exists at least one column
     */
    private boolean checkPickOnBookshelf() {
        for(int i = 0; i < GameSettings.COLUMNS; i++) {
            if(this.players.get(this.turn).getFreeCells(i) >= (this.pickedTiles.size() + 1)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tries to pick a tile from the game board
     * @param position position where to pick the tile
     * @return the picked tile only if it's a valid pick
     */
    public Tile pickTile(Position position) {
        Tile pickedTile = Tile.EMPTY;

        if (this.gameStatus.equals(GameStatus.PICK_CARDS)) {
            // Checks that the number of picked cards is lower than the limit
            if ((this.pickedTiles.size() + 1) <= GameSettings.MAX_SELECTABLE_CARDS && checkPickOnBookshelf()) {
                if(this.board.validPick(position) && !this.pickedTilesPositions.contains(position)) {
                    if (!this.pickedTiles.isEmpty()) {
                        boolean areAlignedOnRow = true;
                        for (Position p : this.pickedTilesPositions) {
                            areAlignedOnRow = (p.getRow() == position.getRow());
                            if (!areAlignedOnRow) {
                                break;
                            }
                        }
                        if (areAlignedOnRow) {
                            List<Integer> positionsColumn = this.pickedTilesPositions.stream().map(Position::getColumn).collect(Collectors.toList());
                            if((Math.max(Collections.max(positionsColumn), position.getColumn()) - Math.min(Collections.min(positionsColumn), position.getColumn())) == this.pickedTiles.size()) {
                                this.pickedTilesPositions.add(position);
                                pickedTile = this.board.getTile(position);
                                this.pickedTiles.add(pickedTile);
                            }
                        }

                        boolean areAlignedOnColumn = true;
                        for (Position p : this.pickedTilesPositions) {
                            areAlignedOnColumn = p.getColumn() == position.getColumn();
                            if (!areAlignedOnColumn) {
                                break;
                            }
                        }
                        if (areAlignedOnColumn) {
                            List<Integer> positionsRows = this.pickedTilesPositions.stream().map(Position::getRow).collect(Collectors.toList());
                            if((Math.max(Collections.max(positionsRows), position.getRow()) - Math.min(Collections.min(positionsRows), position.getRow())) == this.pickedTiles.size()) {
                                this.pickedTilesPositions.add(position);
                                pickedTile = this.board.getTile(position);
                                this.pickedTiles.add(pickedTile);
                            }
                        }
                    } else {
                        this.pickedTilesPositions.add(position);
                        pickedTile = this.board.getTile(position);
                        this.pickedTiles.add(pickedTile);
                    }
                }
            }
        }
        return pickedTile;
    }

    /**
     * Removes the tile from the picked ones
     * @param position position of the tile
     * @return true if the removal happened successfully, false otherwise
     */
    public boolean unpickTile(Position position) {
        if (this.gameStatus.equals(GameStatus.PICK_CARDS)) {
            // Checks that al least one card has been already chosen
            if (!this.pickedTiles.isEmpty()) {
                for(int i = 0; i < this.pickedTilesPositions.size(); i++) {
                    if(this.pickedTilesPositions.get(i).equals(position)) {
                        this.pickedTilesPositions.remove(i);
                        this.pickedTiles.remove(i);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks if the picked tiles are a valid combination
     * @return true if the pick is valid
     */
    public boolean confirmPick() {
        if (this.gameStatus.equals(GameStatus.PICK_CARDS)) {
            if (this.pickedTiles.size() <= GameSettings.MAX_SELECTABLE_CARDS && !this.pickedTiles.isEmpty()) {
                for(Position p : this.pickedTilesPositions) {
                    this.board.pickTile(p);
                }
                this.gameStatus = GameStatus.SELECT_COLUMN;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given column can contain the picked tiles
     * @param column column where to insert the picked tiles
     * @return the inserted tiles only if the insertion is valid
     */
    public boolean confirmColumn(int column) {
        if (this.gameStatus.equals(GameStatus.SELECT_COLUMN)) {
            if (column >= 0 && column < GameSettings.COLUMNS && this.players.get(this.turn).getFreeCells(column) >= this.pickedTiles.size()) {
                this.gameStatus = GameStatus.SELECT_ORDER;
                this.currentSelectedColumn = column;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if it would have any sense to swap the picked tiles
     * @return false when the picked tiles are of the same type or when the player picked just one tile
     */
    public boolean checkNeedForSwap() {
        return !this.pickedTiles.stream().allMatch(x -> x.equals(this.pickedTiles.get(0)));
    }

    /**
     * Checks if the current tiles order is valid. If so it inserts them in the bookshelf+
     * @param pickedTiles list of picked tiles
     */
    public void confirmOrderSelectedTiles(List<Tile> pickedTiles) {
        if (this.gameStatus.equals(GameStatus.SELECT_ORDER)) {
            this.pickedTiles = pickedTiles;
            this.gameStatus = GameStatus.UPDATE_POINTS;
            this.players.get(this.turn).insertTiles(this.currentSelectedColumn, this.pickedTiles);
            this.currentSelectedColumn = 0;
            this.pickedTiles.clear();
            this.pickedTilesPositions.clear();
            this.updatePoints();
        }
    }

    /**
     * Checks whether the current player as either
     * fulfilled a common goal,
     * fulfilled a personal goal or
     * has filled the board
     */
    private void updatePoints() {
        if(this.gameStatus.equals(GameStatus.UPDATE_POINTS)) {
            if (this.players.get(this.turn).checkBookshelf(this.commonGoals)) {
                this.isLastTurn = true;
            }
        }
        this.endTurn();
    }

    /**
     * Handles a turn change
     */
    private void endTurn() {
        if (this.gameStatus.equals(GameStatus.UPDATE_POINTS)) {
            if (!this.isLastTurn || this.turn < this.players.size() - 1) {
                this.turn = Math.floorMod((this.turn + 1), this.players.size());
                this.gameStatus = GameStatus.PICK_CARDS;
                if(this.board.hasToBeRefilled()) {
                    this.board.fillBoard();
                }
            } else {
                this.endGame = true;
            }
        }
    }

    /**
     * Returns the current points of a given player
     * @param player player's nickname
     * @return player's points if he exists
     */
    public Optional<Integer> getPlayerPoints(String player) {
        for(Player p : this.players) {
            if(p.getNickname().equals(player)) {
                return Optional.of(p.getPoints());
            }
        }
        return Optional.empty();
    }

    /**
     * Assigns the score of the token at the top of the stack related to the given common goal
     * @param commonGoal index (0 or 1) that selects which common goal we're referring to
     * @return score of the token
     */
    public int winToken(int commonGoal) {
        List<Token> wonTokenList = this.tokens.get(commonGoal);
        Token wonToken = wonTokenList.remove(0);
        return wonToken.getValue();
    }
}