package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class ConfirmColumnResponse implements ServerMessage {
    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
