package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

import java.io.Serializable;

/**
 * Class that represents a server message sent to a client
 */
public interface ServerMessage extends Serializable {
    /**
     * Handles the server message
     * @param clientController that handles the client server-side
     */
    void handle(ClientController clientController);
}
