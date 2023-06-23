package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class NumPlayersRequest implements ServerMessage {
    /**
     * Handles the server message
     * @param clientController that handles the client server-side
     */
    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
