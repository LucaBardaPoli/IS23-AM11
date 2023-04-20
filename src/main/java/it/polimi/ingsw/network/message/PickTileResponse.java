package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class PickTileResponse implements ServerMessage{
    private boolean response;

    public PickTileResponse(boolean response) {
        this.response = response;
    }

    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
