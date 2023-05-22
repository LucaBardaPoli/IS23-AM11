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
    private final String player;
    private final String nextPlayer;

    public EndTurnNotify(Board board, Bookshelf bookshelf, List<Integer> commonGoalsTokens, int points, String player, String nextPlayer) {
        this.board = board;
        this.bookshelf = bookshelf;
        this.commonGoalsTokens = commonGoalsTokens;
        this.points = points;
        this.player = player;
        this.nextPlayer = nextPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    public List<Integer> getCommonGoalsTokens() {
        return commonGoalsTokens;
    }

    public int getPoints() {
        return points;
    }

    public String getPlayer() {
        return player;
    }

    public String getNextPlayer() {
        return nextPlayer;
    }

    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
