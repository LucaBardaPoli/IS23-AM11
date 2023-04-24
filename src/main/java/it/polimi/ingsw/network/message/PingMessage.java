package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

import java.rmi.RemoteException;

public class PingMessage implements ServerMessage{

    @Override
    public void handle(ClientController clientController) throws RemoteException {
        clientController.handle(this);
    }
}
