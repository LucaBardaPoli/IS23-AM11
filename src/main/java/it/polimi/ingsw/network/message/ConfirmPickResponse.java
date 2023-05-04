package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class ConfirmPickResponse implements ServerMessage {

    private final boolean valid;

    public ConfirmPickResponse(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }

    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
