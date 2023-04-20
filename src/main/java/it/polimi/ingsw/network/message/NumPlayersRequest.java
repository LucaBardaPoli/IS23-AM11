package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class NumPlayersRequest implements ServerMessage{

    public NumPlayersRequest() {
    }

    @Override

    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
