package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.server.ClientHandler;

import java.rmi.RemoteException;

public class NumPlayersResponse implements ClientMessage {
    private int numPlayers;

    public NumPlayersResponse(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public int getNumPlayers() {
        return numPlayers;
    }

    @Override
    public void handle(ClientHandler clientHandler) throws RemoteException {
        clientHandler.handle(this);
    }
}
