package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.network.client.ClientController;

import java.util.List;

public class EndTurnNotify implements ServerMessage {
    private final Board board;
    private final Bookshelf bookshelf;
    private final List<Integer> commonGoalsTokens;
    private final int points;
    private final boolean endGame;
    private final String player;
    private final String nextPlayer;

    /**
     * Class constructor
     * @param board new board
     * @param bookshelf new bookshelf
     * @param commonGoalsTokens new common goals tokens
     * @param points new player's points
     * @param endGame whether it's the end of the game
     * @param player whose turn is over
     * @param nextPlayer who's now playing
     */
    public EndTurnNotify(Board board, Bookshelf bookshelf, List<Integer> commonGoalsTokens, int points, boolean endGame, String player, String nextPlayer) {
        this.board = board;
        this.bookshelf = bookshelf;
        this.commonGoalsTokens = commonGoalsTokens;
        this.points = points;
        this.endGame = endGame;
        this.player = player;
        this.nextPlayer = nextPlayer;
    }

    /**
     * Board getter
     * @return board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Bookshelf getter
     * @return bookshelf
     */
    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    /**
     * Common goals token getter
     * @return common goals token
     */
    public List<Integer> getCommonGoalsTokens() {
        return commonGoalsTokens;
    }

    /**
     * Points getter
     * @return points
     */
    public int getPoints() {
        return points;
    }

    /**
     * End game getter
     * @return end game
     */
    public boolean getEndGame() {
        return this.endGame;
    }

    /**
     * Player whose turn is over
     * @return player
     */
    public String getPlayer() {
        return player;
    }

    /**
     * Player who's now playing
     * @return player
     */
    public String getNextPlayer() {
        return nextPlayer;
    }

    /**
     * Handles the server message
     * @param clientController that handles the client server-side
     */
    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
