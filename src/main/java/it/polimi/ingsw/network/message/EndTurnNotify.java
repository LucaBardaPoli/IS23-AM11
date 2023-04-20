package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class EndTurnNotify implements ServerMessage{

    public EndTurnNotify() {
    }

    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
