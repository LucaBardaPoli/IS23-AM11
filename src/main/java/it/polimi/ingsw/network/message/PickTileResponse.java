package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class PickTileResponse implements ServerMessage {
    private final boolean valid;

    public PickTileResponse(boolean response) {
        this.valid = response;
    }

    public boolean getValid() {
        return valid;
    }

    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
