package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class ConfirmColumnResponse implements ServerMessage {
    private final boolean valid;
    private final boolean needForSwap;

    public ConfirmColumnResponse(boolean valid, boolean needForSwap) {
        this.valid = valid;
        this.needForSwap = needForSwap;
    }

    public boolean getValid() {
        return valid;
    }

    public boolean getNeedForSwap() {
        return this.needForSwap;
    }

    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
