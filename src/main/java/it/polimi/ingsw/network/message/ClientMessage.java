package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.server.ClientHandler;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface ClientMessage extends Serializable {
    public void handle(ClientHandler clientHandler) throws RemoteException;
}
