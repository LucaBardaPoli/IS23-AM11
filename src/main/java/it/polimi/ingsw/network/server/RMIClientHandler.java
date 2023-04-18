package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.RMIClient;
import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.LoginRequest;
import it.polimi.ingsw.network.message.ServerMessage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public class RMIClientHandler extends ClientHandler implements Remote, ServerMessageHandler {
    private RMIClient client;

    public RMIClientHandler() {
        super();
    }

    public void register(RMIClient client) throws RemoteException {
        this.client = client;
    }

    // Receive a Client message (corresponds to the TCP while trying to read something)
    public void receiveMessage(ClientMessage clientMessage) throws RemoteException {
        clientMessage.handle(this);
    }

    // Handles all kind of Client messages
    public void handle(ClientMessage clientMessage) {

    }

    public void handle(LoginRequest clientMessage) {
        clientMessage.handle(this);
    }

    // Send all kind of Server messages
    public void sendMessage(ServerMessage serverMessage) {

    }
}
