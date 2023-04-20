package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class ConfirmColumnResponse implements ServerMessage{
    public ConfirmColumnResponse() {
    }

    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
