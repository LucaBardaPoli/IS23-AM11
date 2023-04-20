package it.polimi.ingsw.network;

import it.polimi.ingsw.network.server.ClientHandlerRMIInterface;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIListenerInterface extends Remote {
    public ClientHandlerRMIInterface getHandler() throws RemoteException;
}
