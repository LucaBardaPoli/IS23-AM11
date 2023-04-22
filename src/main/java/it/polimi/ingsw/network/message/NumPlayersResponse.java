package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.server.ClientHandler;

public class NumPlayersResponse implements ClientMessage {
    private final int numPlayers;

    public NumPlayersResponse(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    @Override
    public void handle(ClientHandler clientHandler) {
        clientHandler.handle(this);
    }
}
