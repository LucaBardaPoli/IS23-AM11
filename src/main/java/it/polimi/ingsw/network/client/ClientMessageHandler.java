package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.ServerMessage;

public interface ClientMessageHandler {
    public void handle(ServerMessage clientMessage);
}
