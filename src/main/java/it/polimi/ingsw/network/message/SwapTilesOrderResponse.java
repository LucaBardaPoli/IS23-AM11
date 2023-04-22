package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.network.client.ClientController;

import java.util.List;

public class SwapTilesOrderResponse implements ServerMessage {
    private final List<Tile> pickedCards;
    private final boolean valid;
    public SwapTilesOrderResponse(List<Tile> pickedCards, boolean valid) {
        this.pickedCards = pickedCards;
        this.valid = valid;
    }

    public List<Tile> getPickedCards() {
        return pickedCards;
    }

    public boolean getValid() {
        return valid;
    }

    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
