package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class ConfirmColumnResponse implements ServerMessage {
    private final boolean valid;

    public ConfirmColumnResponse(boolean valid) {
        this.valid = valid;
    }

    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
