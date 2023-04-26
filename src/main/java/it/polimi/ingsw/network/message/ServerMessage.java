package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

import java.io.Serializable;

public interface ServerMessage extends Serializable {
    void handle(ClientController clientController);
}
