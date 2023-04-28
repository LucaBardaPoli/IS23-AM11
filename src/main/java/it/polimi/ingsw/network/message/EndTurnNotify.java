package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.network.client.ClientController;

public class EndTurnNotify implements ServerMessage {
    private final Bookshelf bookshelf;
    private final int points;
    private final String nextPlayer;

    public EndTurnNotify(Bookshelf bookshelf, int points, String nextPlayer) {
        this.bookshelf = bookshelf;
        this.points = points;
        this.nextPlayer = nextPlayer;
    }

    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    public int getPoints() {
        return points;
    }

    public String getNextPlayer() {
        return nextPlayer;
    }

    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
