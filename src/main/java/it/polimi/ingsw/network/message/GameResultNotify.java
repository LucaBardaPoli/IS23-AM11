package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class GameResultNotify implements ServerMessage {

    /**
     * Handles the server message
     * @param clientController that handles the client server-side
     */
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
