package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class GameResultNotify implements ServerMessage{

    public GameResultNotify() {
    }

    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}