package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.server.ClientHandler;

import java.rmi.RemoteException;

public class NumPlayersResponse implements ClientMessage{

    Integer numPlayers;

    public NumPlayersResponse(Integer numPlayers) {
        this.numPlayers = numPlayers;
    }

    public Integer getNumPlayers() {
        return numPlayers;
    }

    @Override
    public void handle(ClientHandler clientHandler) throws RemoteException {
        clientHandler.handle(this);
    }
}
