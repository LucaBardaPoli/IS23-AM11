package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.CardType;
import it.polimi.ingsw.network.client.ClientController;

import java.util.List;

public class SwapTilesOrderResponse implements ServerMessage {
    private final List<CardType> pickedCards;
    private final boolean valid;
    public SwapTilesOrderResponse(List<CardType> pickedCards, boolean valid) {
        this.pickedCards = pickedCards;
        this.valid = valid;
    }

    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
