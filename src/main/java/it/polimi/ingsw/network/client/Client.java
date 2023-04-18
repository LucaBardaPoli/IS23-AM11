package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.ServerMessage;

import java.rmi.RemoteException;

public abstract class Client {
    protected String serverIp;
    protected boolean stopConnection;

    public Client(String serverIp) {
        this.serverIp = serverIp;
        this.stopConnection = false;
    }

    public abstract void openConnection();

    public abstract void startListening();

    public abstract void receiveMessage(ServerMessage serverMessage) throws RemoteException;

    public abstract void sendMessage(ClientMessage clientMessage);
}
