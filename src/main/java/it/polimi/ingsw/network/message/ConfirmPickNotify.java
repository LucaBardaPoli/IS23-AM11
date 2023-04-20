package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.server.ClientHandler;

import java.rmi.RemoteException;

public class ConfirmPickNotify implements ClientMessage {
    @Override
    public void handle(ClientHandler clientHandler) throws RemoteException {
        clientHandler.handle(this);
    }
}
