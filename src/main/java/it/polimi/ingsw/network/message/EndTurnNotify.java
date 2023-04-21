package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.network.client.ClientController;

public class EndTurnNotify implements ServerMessage {
    private final Bookshelf bookshelf;
    private final int points;
    private final boolean isYourTurn;

    public EndTurnNotify(Bookshelf bookshelf, int points, boolean isYourTurn) {
        this.bookshelf = bookshelf;
        this.points = points;
        this.isYourTurn = isYourTurn;
    }

    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
