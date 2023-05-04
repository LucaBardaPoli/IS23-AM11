package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.server.ClientHandler;

import java.io.Serializable;

public interface ClientMessage extends Serializable {
    /**
     * Handles the Client message
     * @param clientHandler handles the client
     */
    void handle(ClientHandler clientHandler);
}
