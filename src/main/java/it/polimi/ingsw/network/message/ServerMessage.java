package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

import java.io.Serializable;
import java.rmi.RemoteException;

public interface ServerMessage extends Serializable {
    public void handle(ClientController clientController) throws RemoteException;
}
