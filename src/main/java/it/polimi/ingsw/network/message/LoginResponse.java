package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class LoginResponse implements ServerMessage {
    private boolean valid;

    public LoginResponse(boolean valid) {
        this.valid = valid;
    }

    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
