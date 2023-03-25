package it.polimi.ingsw.model;

import java.util.*;

/**
 * Game class that handles the game moves and the way it evolves
 */
public class Game {
    private final Integer id;
    private final Board board;
    private final List<Player> players;
    private Integer turn;
    private boolean isLastTurn;
    private final List<PersonalGoal> personalGoals;
    private final List<CommonGoal> commonGoals;
    private final List<List<Token>> tokens;
    private final List<Position> pickedCardsPositions;
    private final List<Optional<CardType>> pickedCards;
    private GameStatus gameStatus;
    private final CountCards countCards;
    private Integer currentSelectedColumn;
    private static GameManagerInterface GAME_MANAGER;
    public static Integer MAX_SELECTABLE_CARDS;

    /**
     * Class contructor
     * @param id game id
     * @param players list of players nickname
     * @param commonGoals two common goals of the game
     * @param personalGoals players personal goals
     */
    public Game(Integer id, List<String> players, List<CommonGoal> commonGoals, List<PersonalGoal> personalGoals) {
        this.id = id;
        this.countCards = new CountCards();
        this.board = new Board(players.size());
        this.board.fillBoard(this.countCards);
        this.players = new ArrayList<Player>();
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
        this.pickedCards = new ArrayList<>();
        this.pickedCardsPositions = new ArrayList<>();
        this.currentSelectedColumn = 0;

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
        this.tokens.add(tmpTokens);
        this.tokens.add(tmpTokens);
    }

    /**
     * Getter of game id
     * @return game id
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Getter of cards counter
     * @return cards counter
     */
    public CountCards getCountCards() {
        return this.countCards;
    }

    /**
     * Getter of number of players of the game
     * @return number of players
     */
    public Integer getMaxNumPlayers() {
        return players.size();
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
     * Tries to pick a card from the game board
     * @param position position where to pick the card
     * @return the picked card only if it's a valid pick
     */
    public Optional<CardType> pickCard(Position position) {
        Optional<CardType> pickedCard = Optional.empty();

        if (this.gameStatus.equals(GameStatus.PICK_CARDS)) {

            // Checks that the number of picked cards is lower than the limit
            if (this.pickedCards.size() < MAX_SELECTABLE_CARDS) {
                if(this.board.validPick(position)) {
                    if (!this.pickedCards.isEmpty()) {
                        boolean areAlignedOnRow = true;
                        for (Position p : this.pickedCardsPositions) {
                            areAlignedOnRow = p.getRow() == position.getRow();
                            if (!areAlignedOnRow) {
                                break;
                            }
                        }
                        if (areAlignedOnRow) {
                            this.pickedCardsPositions.add(position);
                            this.pickedCards.add(Optional.of(this.board.getCard(position).get()));
                        }

                        boolean areAlignedOnColumn = true;
                        for (Position p : this.pickedCardsPositions) {
                            areAlignedOnColumn = p.getColumn() == position.getColumn();
                            if (!areAlignedOnColumn) {
                                break;
                            }
                        }
                        if (areAlignedOnColumn) {
                            this.pickedCardsPositions.add(position);
                            this.pickedCards.add(Optional.of(this.board.getCard(position).get()));
                        }
                    } else {
                        this.pickedCardsPositions.add(position);
                        this.pickedCards.add(Optional.of(this.board.getCard(position).get()));
                    }
                }
            }
        }
        return pickedCard;
    }

    /**
     * Checks if the picked cards are a valid combination
     * @return true if the pick is valid
     */
    public boolean confirmChoice() {
        if (this.gameStatus.equals(GameStatus.PICK_CARDS)) {
            if (this.pickedCards.size() < MAX_SELECTABLE_CARDS && this.pickedCards.size() > 0) {
                this.gameStatus = GameStatus.SELECT_COLUMN;
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the given column can contain the picked cards
     *
     * @param column column where to insert the picked cards
     * @return the inserted cards only if the insertion is valid
     */
    public List<Optional<CardType>> confirmColumn(Integer column) {
        if (this.gameStatus.equals(GameStatus.SELECT_COLUMN)) {
            if (this.players.get(this.turn).getFreeCells(column) >= this.pickedCards.size()) {
                this.gameStatus = GameStatus.SELECT_ORDER;
                this.currentSelectedColumn = column;
                return this.pickedCards;
            }
        }
        return null;
    }

    /**
     * Moves the selected card to the last place in the list of cards to insert in the bookshelf
     *
     * @param position position of the selected card
     * @return the new sorted list of cards
     */
    public List<Optional<CardType>> rearrangeCards(Integer position) {
        if (this.gameStatus.equals(GameStatus.SELECT_ORDER)) {
            Optional<CardType> tmp = this.pickedCards.get(position);
            this.pickedCards.set(position, this.pickedCards.get(this.pickedCards.size() - 1));
            this.pickedCards.set(this.pickedCards.size() - 1, tmp);
        }
        return this.pickedCards;
    }

    /**
     * Checks if the current cards order is valid. If so it inserts them in the bookshelf
     */
    public void confirmOrderSelectedCards() {
        if (this.gameStatus.equals(GameStatus.SELECT_ORDER)) {
            this.gameStatus = GameStatus.UPDATE_POINTS;
            this.players.get(this.turn).insertCards(this.currentSelectedColumn, this.pickedCards);
            this.currentSelectedColumn = 0;
            this.pickedCards.clear();
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
                if(this.board.validBoard()) {
                    this.board.fillBoard(this.countCards);
                }
            } else {
                Game.GAME_MANAGER.endGame(this);
            }
        }
    }

    /**
     * Assigns the score of the token at the top of the stack related to the given common goal
     * @param commonGoal index (0 or 1) that selects which common goal we are referring to
     * @return score of the token
     */
    public Integer winToken(Integer commonGoal) {
        Token wonToken = this.tokens.get(commonGoal).remove(0);
        return wonToken.getValue();
    }
}