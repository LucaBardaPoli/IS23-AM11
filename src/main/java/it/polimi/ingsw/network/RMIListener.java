package it.polimi.ingsw.network;

import it.polimi.ingsw.network.server.ClientHandlerRMI;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class RMIListener implements RMIListenerInterface {
    private List<ClientHandlerRMI> handlers;

    public RMIListener() {
        this.handlers = new ArrayList<>();
    }

    public ClientHandlerRMI getHandler() throws RemoteException {
        ClientHandlerRMI handler = new ClientHandlerRMI();
        this.handlers.add(handler);
        return handler;
    }
}
