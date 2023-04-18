package it.polimi.ingsw.network;

import it.polimi.ingsw.network.server.RMIClientHandler;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class RMIListener implements Remote {
    private List<RMIClientHandler> handlers;

    public RMIListener() {
        this.handlers = new ArrayList<>();
    }

    public RMIClientHandler getHandler() throws RemoteException {
        RMIClientHandler handler = new RMIClientHandler();
        this.handlers.add(handler);
        return handler;
    }
}
