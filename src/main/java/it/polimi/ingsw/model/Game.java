package it.polimi.ingsw.model;

import java.util.*;

/**
 * Game class that handles the game moves and the way it evolves
 */
public class Game {
    private final int id;
    private final Board board;
    private final List<Player> players;
    private int turn;
    private boolean isLastTurn;
    private final List<PersonalGoal> personalGoals;
    private final List<CommonGoal> commonGoals;
    private final List<List<Token>> tokens;
    private final List<Position> pickedTilesPositions;
    private final List<Tile> pickedTiles;
    private GameStatus gameStatus;
    private final Bag bag;
    private int currentSelectedColumn;
    private boolean endGame;

    /**
     * Class contructor
     * @param id game id
     * @param players list of players nickname
     * @param commonGoals two common goals of the game
     * @param personalGoals players personal goals
     */
    public Game(int id, List<String> players, List<CommonGoal> commonGoals, List<PersonalGoal> personalGoals) {
        this.id = id;
        this.bag = new Bag();
        this.board = new Board(players.size(), bag);
        this.board.fillBoard();
        this.players = new ArrayList<>();
        this.personalGoals = personalGoals;
        Collections.shuffle(this.personalGoals);
        for (int i = 0; i < players.size(); i++) {
            this.players.add(new Player(players.get(i), this.personalGoals.get(i), this));
        }
        Collections.shuffle(this.players);
        this.turn = 0;
        this.isLastTurn = false;
        this.commonGoals = commonGoals;
        this.gameStatus = GameStatus.PICK_CARDS;
        this.pickedTiles = new ArrayList<>();
        this.pickedTilesPositions = new ArrayList<>();
        this.currentSelectedColumn = 0;
        this.endGame = false;

        // Assigns tokens based on the number of players
        this.tokens = new ArrayList<>();
        List<Token> tmpTokens = new ArrayList<>();
        if(this.players.size() == 2) {
            tmpTokens.add(new Token(8));
            tmpTokens.add(new Token(4));
        } else if(this.players.size() == 3) {
            tmpTokens.add(new Token(8));
            tmpTokens.add(new Token(6));
            tmpTokens.add(new Token(4));
        } else if(this.players.size() == 4) {
            tmpTokens.add(new Token(8));
            tmpTokens.add(new Token(6));
            tmpTokens.add(new Token(4));
            tmpTokens.add(new Token(2));
        }
        this.tokens.add(new ArrayList<>(tmpTokens));
        this.tokens.add(new ArrayList<>(tmpTokens));
    }

    /**
     * Getter of game id
     * @return game id
     */
    public int getId() {
        return this.id;
    }

    /**
     * Getter of cards counter
     * @return cards counter
     */
    public Bag getBag() {
        return this.bag;
    }

    /**
     * Getter of the players
     * @return list of players
     */
    public List<Player> getPlayers() {
        return players;
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

    public List<Tile> getPickedTiles() {
        return this.pickedTiles;
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
        return this.players.get((this.turn - 1) % this.players.size());
    }

    /**
     * Tries to pick a tile from the game board
     * @param position position where to pick the tile
     * @return the picked tile only if it's a valid pick
     */
    public Optional<Tile> pickTile(Position position) {
        Optional<Tile> pickedTile = Optional.empty();

        if (this.gameStatus.equals(GameStatus.PICK_CARDS)) {
            // Checks that the number of picked cards is lower than the limit
            if (this.pickedTiles.size() < GameSettings.MAX_SELECTABLE_CARDS) {
                if(this.board.validPick(position)) {
                    if (!this.pickedTiles.isEmpty()) {
                        boolean areAlignedOnRow = true;
                        for (Position p : this.pickedTilesPositions) {
                            areAlignedOnRow = p.getRow() == position.getRow();
                            if (!areAlignedOnRow) {
                                break;
                            }
                        }
                        if (areAlignedOnRow) {
                            this.pickedTilesPositions.add(position);
                            pickedTile = this.board.getTile(position);
                            this.pickedTiles.add(pickedTile.get());
                        }

                        boolean areAlignedOnColumn = true;
                        for (Position p : this.pickedTilesPositions) {
                            areAlignedOnColumn = p.getColumn() == position.getColumn();
                            if (!areAlignedOnColumn) {
                                break;
                            }
                        }
                        if (areAlignedOnColumn) {
                            this.pickedTilesPositions.add(position);
                            pickedTile = this.board.getTile(position);
                            this.pickedTiles.add(pickedTile.get());
                        }
                    } else {
                        this.pickedTilesPositions.add(position);
                        pickedTile = this.board.getTile(position);
                        this.pickedTiles.add(pickedTile.get());
                    }
                }
            }
        }
        return pickedTile;
    }

    /**
     * Removes the tile from the chosen ones
     * @param position position of the tile
     * @return new list of picked tiles
     */
    public Optional<List<Tile>> removeTile(Position position) {
        if (this.gameStatus.equals(GameStatus.PICK_CARDS)) {
            // Checks that al least one card has been already chosen
            if (!this.pickedTiles.isEmpty()) {
                for(int i = 0; i < this.pickedTilesPositions.size(); i++) {
                    if(this.pickedTilesPositions.get(i).equals(position)) {
                        this.pickedTilesPositions.remove(i);
                        this.pickedTiles.remove(i);
                        return Optional.of(this.pickedTiles);
                    }
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Checks if the picked tiles are a valid combination
     * @return true if the pick is valid
     */
    public boolean confirmPick() {
        if (this.gameStatus.equals(GameStatus.PICK_CARDS)) {
            if (this.pickedTiles.size() < GameSettings.MAX_SELECTABLE_CARDS && !this.pickedTiles.isEmpty()) {
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
     * Moves the selected tile to the last place in the list of cards to insert in the bookshelf
     * @param index position of the selected tile
     * @return the new sorted list of tiles
     */
    public Optional<List<Tile>> rearrangeTiles(int index) {
        if(this.gameStatus.equals(GameStatus.SELECT_ORDER) && index >= 0 && index <= 2) {
            Tile tmp = this.pickedTiles.get(index);
            this.pickedTiles.set(index, this.pickedTiles.get(this.pickedTiles.size() - 1));
            this.pickedTiles.set(this.pickedTiles.size() - 1, tmp);
            return Optional.of(this.pickedTiles);
        }
        return Optional.empty();
    }

    /**
     * Checks if the current tiles order is valid. If so it inserts them in the bookshelf
     */
    public void confirmOrderSelectedTiles() {
        if (this.gameStatus.equals(GameStatus.SELECT_ORDER)) {
            this.gameStatus = GameStatus.UPDATE_POINTS;
            this.players.get(this.turn).insertCards(this.currentSelectedColumn, this.pickedTiles);
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
        if (this.gameStatus.equals(GameStatus.UPDATE_POINTS)) {
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
            if ((this.isLastTurn && this.turn < this.players.size() - 1) || !this.isLastTurn) {
                this.turn = (this.turn + 1) % this.players.size();
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
     * @param commonGoal index (0 or 1) that selects which common goal we are referring to
     * @return score of the token
     */
    public int winToken(int commonGoal) {
        List<Token> wonTokenList = this.tokens.get(commonGoal);
        Token wonToken = wonTokenList.remove(0);
        return wonToken.getValue();
    }

    /**
     * Checks if the given player is the one who's currently playing
     * @param player player who played the move
     * @return true whether the given player is the one who's currently playing
     */
    public boolean checkPlayer(String player) {
        return getCurrentPlayer().getNickname().equals(player);
    }
}