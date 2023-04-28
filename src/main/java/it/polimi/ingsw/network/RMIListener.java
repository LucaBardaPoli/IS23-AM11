package it.polimi.ingsw.network;

import it.polimi.ingsw.network.server.ClientHandlerRMI;
import it.polimi.ingsw.network.server.ClientHandlerRMIInterface;
import it.polimi.ingsw.network.server.PingPongHandler;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class RMIListener implements RMIListenerInterface {
    private final List<ClientHandlerRMI> handlers;

    public RMIListener() throws RemoteException {
        this.handlers = new ArrayList<>();
    }

    public ClientHandlerRMIInterface getHandler() throws RemoteException {
        PingPongHandler pingPongHandler = new PingPongHandler();
        ClientHandlerRMI handler = new ClientHandlerRMI(pingPongHandler);
        this.handlers.add(handler);
        System.out.println("New RMI client accepted");
        return (ClientHandlerRMIInterface) UnicastRemoteObject.exportObject(handler, NetworkSettings.SERVER_PORT_RMI);
    }
}
