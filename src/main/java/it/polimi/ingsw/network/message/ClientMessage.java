package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.server.ClientHandler;

import java.io.Serializable;

/**
 * Class that represents a client message sent to the server
 */
public interface ClientMessage extends Serializable {
    /**
     * Handles the Client message
     * @param clientHandler that handles the client client-side
     */
    void handle(ClientHandler clientHandler);
}
