package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.server.ClientHandler;

import java.rmi.RemoteException;

public class SwapTilesOrderRequest implements ClientMessage {
    private int position;

    public SwapTilesOrderRequest(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    @Override
    public void handle(ClientHandler clientHandler) throws RemoteException {
        clientHandler.handle(this);
    }
}
