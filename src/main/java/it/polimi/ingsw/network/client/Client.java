package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.ClientMessage;

import java.rmi.RemoteException;

public abstract class Client {
    protected String serverIp;
    protected ClientController controller;
    protected boolean stopConnection;

    public Client(String serverIp) {
        this.serverIp = serverIp;
        this.stopConnection = false;
    }

    public void setController(ClientController clientController) {
        this.controller = clientController;
    }

    public abstract void openConnection();

    public abstract void startListening();

    public abstract void sendMessage(ClientMessage clientMessage) throws RemoteException;
}
