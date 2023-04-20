package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class SwapTilesOrderResponse implements ServerMessage {
    // Nuovo ordine
    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
