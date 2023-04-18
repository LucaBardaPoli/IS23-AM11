package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.message.ClientMessage;

public interface ServerMessageHandler {
    public void handle(ClientMessage clientMessage);
}
