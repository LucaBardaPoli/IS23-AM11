package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.server.ClientHandler;

public class ConfirmOrderNotify implements ClientMessage {
    @Override
    public void handle(ClientHandler clientHandler) {
        clientHandler.handle(this);
    }
}
