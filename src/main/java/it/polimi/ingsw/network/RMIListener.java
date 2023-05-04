package it.polimi.ingsw.network;

import it.polimi.ingsw.network.server.ClientHandlerRMI;
import it.polimi.ingsw.network.server.ClientHandlerRMIInterface;
import it.polimi.ingsw.network.server.PingPongHandler;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RMIListener implements RMIListenerInterface {
    /**
     * RMI listener class
     */
    private final List<ClientHandlerRMI> handlers;

    /**
     * Class Constructor
     * @throws RemoteException
     */
    public RMIListener() throws RemoteException {
        this.handlers = new ArrayList<>();
    }

    /**
     * Getter method
     * @return a ClientHandlerRMI after it has been exported for the RMI procedures.
     * @throws RemoteException
     */
    public ClientHandlerRMIInterface getHandler() throws RemoteException {
        ClientHandlerRMI handler = new ClientHandlerRMI(new PingPongHandler());
        this.handlers.add(handler);
        System.out.println("New RMI client accepted");
        return (ClientHandlerRMIInterface) UnicastRemoteObject.exportObject(handler, NetworkSettings.SERVER_PORT_RMI);
    }
}
