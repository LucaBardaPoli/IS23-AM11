package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.Client;

import java.io.Serializable;

public interface ServerMessage extends Serializable {
    public void handle(Client client);
}
