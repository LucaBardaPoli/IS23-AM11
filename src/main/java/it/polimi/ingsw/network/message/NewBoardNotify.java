package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class NewBoardNotify implements ServerMessage{

    public NewBoardNotify() {
    }

    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
