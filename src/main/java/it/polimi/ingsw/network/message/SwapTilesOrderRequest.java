package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.server.ClientHandler;

import java.rmi.RemoteException;

public class SwapTilesOrderRequest implements ClientMessage {

    Integer position;

    public SwapTilesOrderRequest(Integer position) {
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }

    @Override
    public void handle(ClientHandler clientHandler) throws RemoteException {
        clientHandler.handle(this);
    }
}
