package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.network.client.ClientController;

import java.util.List;

public class SwapTilesOrderResponse implements ServerMessage {
    private final List<Tile> pickedCards;
    private final boolean successful;
    public SwapTilesOrderResponse(List<Tile> pickedCards, boolean successful) {
        this.pickedCards = pickedCards;
        this.successful = successful;
    }

    public List<Tile> getPickedCards() {
        return pickedCards;
    }

    public boolean getSuccessful() {
        return successful;
    }

    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
