package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class GameStartNotify implements ServerMessage{

    public GameStartNotify() {
    }

    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}