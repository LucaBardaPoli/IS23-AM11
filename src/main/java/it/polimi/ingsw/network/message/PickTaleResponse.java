package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.server.ClientHandler;

public class PickTaleResponse implements ServerMessage{

    boolean response;

    public PickTaleResponse(boolean response) {
        this.response = response;
    }

    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}