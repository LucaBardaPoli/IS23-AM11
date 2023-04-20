package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class PickTaleResponse implements ServerMessage{

    boolean response;

    public PickTaleResponse(boolean response) {
        this.response = response;
    }

    @Override
    public void handle(ClientController clientController) {

    }
}
