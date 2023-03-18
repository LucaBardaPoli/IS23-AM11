package it.polimi.ingsw.model;

import java.util.*;

public class Game {
    private final Integer id;
    private final Board board;
    private final List<Player> players;
    private Integer turn;
    private boolean isLastTurn;
    private final List<CommonGoal> commonGoals;
    private final List<PersonalGoal> personalGoals;
    private final List<Card> pickedCards;
    private GameStatus gameStatus;
    private final CountCards countCards;
    private Integer currentSelectedColumn;
    private static GameManagerInterface GAME_MANAGER;
    private static Integer MAX_SELECTABLE_CARDS;

    public Game(Integer id, List<String> players, List<CommonGoal> commonGoals, List<PersonalGoal> personalGoals) {
        this.id = id;
        this.board = new Board();
        this.board.fillBoard();
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
        this.pickedCards = new ArrayList<Card>();
        this.countCards = new CountCards();
        this.currentSelectedColumn = 0;
    }

    public Integer getId() {
        return this.id;
    }

    public CountCards getCountCards() {
        return this.countCards;
    }

    public Integer getNumCurrentPlayers() {
        return players.size();
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Optional<Player> getPlayer(String nickname) {
        for (Player p : this.players) {
            if (p.getNickname().equals(nickname)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public List<CommonGoal> getCommonGoals() {
        return this.commonGoals;
    }

    public Optional<PersonalGoal> getPersonalGoal(String player) {
        for (Player p : this.players) {
            if (p.getNickname().equals(nickname)) {
                return Optional.of(p.getPersonalGoal());
            }
        }
        return Optional.empty();
    }

    public Optional<Bookshelf> getBookshelf(String player) {
        for (Player p : this.players) {
            if (p.getNickname().equals(nickname)) {
                return Optional.of(p.getBookshelf());
            }
        }
        return Optional.empty();
    }

    public Board getBoard() {
        return this.board;
    }

    public GameStatus getGameStatus() {
        return this.gameStatus;
    }

    public boolean isNicknameTaken(String nickname) {
        for (Player p : this.players) {
            if (p.getNickname().equals(nickname)) {
                return true;
            }
        }
        return false;
    }

    public Player getCurrentPlayer() {
        return this.players.get(this.turn);
    }

    public Optional<Card> pickCard(Position position) {
        Optional<Card> pickedCard = Optional.empty();
        if (this.gameStatus.equals(GameStatus.PICK_CARDS)) {
            if (this.pickedCards.size() < MAX_SELECTABLE_CARDS) {
                pickedCard = this.board.getCard(position);
                if (!pickedCard.isEmpty()) {
                    this.pickedCards.add(pickedCard.get());
                }
            }
        }
        return pickedCard;
    }

    public boolean confirmChoice() {
        if (this.gameStatus.equals(GameStatus.PICK_CARDS)) {
            if (this.pickedCards.size() < MAX_SELECTABLE_CARDS && this.pickedCards.size() > 0) {
                this.gameStatus = GameStatus.SELECT_COLUMN;
                return true;
            }
        }
        return false;
    }

    public Optional<List<Card>> confirmColumn(Integer column) {
        if (this.gameStatus.equals(GameStatus.SELECT_COLUMN)) {
            if (this.players.get(this.turn).getFreeCells(column) >= this.pickedCards.size()) {
                this.gameStatus = GameStatus.SELECT_ORDER;
                this.currentSelectedColumn = column;
                return Optional.of(this.pickedCards);
            }
        }
        return Optional.empty();
    }

    // It sets the selected card as the last one in the list
    public List<Card> rearrangeCards(Integer position) {
        if (this.gameStatus.equals(GameStatus.SELECT_ORDER)) {
            Card tmp = this.pickedCards.get(position);
            this.pickedCards.set(position, this.pickedCards.get(this.pickedCards.size() - 1));
            this.pickedCards.set(this.pickedCards.size() - 1, tmp);
        }
        return this.pickedCards;
    }

    public void confirmOrderSelectedCards() {
        if (this.gameStatus.equals(GameStatus.SELECT_ORDER)) {
            this.gameStatus = GameStatus.UPDATE_POINTS;
            this.players.get(this.turn).insertCards(this.currentSelectedColumn, this.pickedCards);
            this.currentSelectedColumn = 0;
            this.pickedCards.clear();
            this.updatePoints();
        }
    }

    private void updatePoints() {
        if (this.gameStatus.equals(GameStatus.UPDATE_POINTS)) {
            if (this.players.get(this.turn).checkBookshelf(this.commonGoals)) {
                this.isLastTurn = true;
            }
        }
        this.endTurn();
    }

    private void endTurn() {
        if (this.gameStatus.equals(GameStatus.UPDATE_POINTS)) {
            if ((this.isLastTurn && this.turn < this.players.size() - 1) || !this.isLastTurn) {
                this.turn = (this.turn + 1) % this.players.size();
                this.gameStatus = GameStatus.PICK_CARDS;
            } else {
                Game.GAME_MANAGER.endGame(this);
            }
        }
    }
}