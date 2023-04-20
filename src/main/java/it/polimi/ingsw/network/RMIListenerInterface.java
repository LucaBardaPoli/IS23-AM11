package it.polimi.ingsw.network;

import it.polimi.ingsw.network.server.ClientHandlerRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIListenerInterface extends Remote {
    public ClientHandlerRMI getHandler() throws RemoteException;
}
