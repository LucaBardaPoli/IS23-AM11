package it.polimi.ingsw.network;

import it.polimi.ingsw.network.client.ClientRMIInterface;
import it.polimi.ingsw.network.server.ClientHandlerRMI;
import it.polimi.ingsw.network.server.ClientHandlerRMIInterface;
import it.polimi.ingsw.network.server.PingPongHandler;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Object exposed by the server to allow RMI-clients to get an RMI handler
 */
public class RMIListener implements RMIListenerInterface {

    /**
     * Getter method
     * @return a ClientHandlerRMI after it has been exported for the RMI procedures.
     * @throws RemoteException RMI error
     */
    public ClientHandlerRMIInterface getHandler(ClientRMIInterface clientRMIInterface) throws RemoteException {
        ClientHandlerRMI handler = new ClientHandlerRMI(clientRMIInterface, new PingPongHandler());
        System.out.println("New RMI client accepted");
        return handler;
    }
}
