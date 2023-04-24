package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.server.ClientHandler;

import java.io.Serializable;

public interface ClientMessage extends Serializable {
    void handle(ClientHandler clientHandler);
}
