package it.polimi.ingsw.network;

import it.polimi.ingsw.network.server.ClientHandlerRMIInterface;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIListenerInterface extends Serializable, Remote {
    /**
     * Getter method
     * @return a ClientHandlerRMI
     * @throws RemoteException RMI error
     */
    ClientHandlerRMIInterface getHandler() throws RemoteException;
}
