package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class NewBoardNotify implements ServerMessage {

    // Nuova Board
    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
