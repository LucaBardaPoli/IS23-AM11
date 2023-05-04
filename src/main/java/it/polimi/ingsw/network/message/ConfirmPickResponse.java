package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class ConfirmPickResponse implements ServerMessage {

    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
