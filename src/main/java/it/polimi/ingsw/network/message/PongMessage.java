package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.server.ClientHandler;

public class PongMessage implements ClientMessage {
    /**
     * Handles the Client message
     * @param clientHandler that handles the client client-side
     */
    @Override
    public void handle(ClientHandler clientHandler) {
        clientHandler.handle(this);
    }
}
