package it.polimi.ingsw.network;

import it.polimi.ingsw.network.server.ClientHandlerRMI;
import it.polimi.ingsw.network.server.ClientHandlerRMIInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class RMIListener implements RMIListenerInterface {
    private List<ClientHandlerRMIInterface> handlers;

    public RMIListener() {
        this.handlers = new ArrayList<>();
    }

    public ClientHandlerRMIInterface getHandler() throws RemoteException {
        ClientHandlerRMIInterface handler = new ClientHandlerRMI();
        this.handlers.add(handler);
        return handler;
    }
}
