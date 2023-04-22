package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class LoginResponse implements ServerMessage {
    private final boolean valid;

    public LoginResponse(boolean valid) {
        this.valid = valid;
    }

    public boolean getValid() {
        return valid;
    }

    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
