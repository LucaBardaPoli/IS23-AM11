package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.network.client.ClientController;

public class EndTurnNotify implements ServerMessage {

    private final Board board;
    private final Bookshelf bookshelf;
    private final int points;
    private final String player;
    private final String nextPlayer;

    public EndTurnNotify(Board board, Bookshelf bookshelf, int points, String player, String nextPlayer) {
        this.board = board;
        this.bookshelf = bookshelf;
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
