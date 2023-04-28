package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.ClientRMIInterface;
import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.PongMessage;
import it.polimi.ingsw.network.message.ServerMessage;

import java.rmi.RemoteException;

public class ClientHandlerRMI extends ClientHandler implements ClientHandlerRMIInterface {
    private ClientRMIInterface client;

    public ClientHandlerRMI(PingPongHandler pingPongHandler) {
        super(pingPongHandler);
    }

    public void registerClient(ClientRMIInterface client) throws RemoteException {
        this.client = client;
    }

    // Receive a Client message (corresponds to the TCP while trying to read something)
    public void receiveMessage(ClientMessage clientMessage) throws RemoteException {
        clientMessage.handle(this);
    }

    // Send all kind of Server messages
    public void sendMessage(ServerMessage serverMessage) {
        try {
            this.client.receiveMessage(serverMessage);
        } catch(RemoteException e) {
            e.printStackTrace();
        }
    }

    public void close() {
    }

    @Override
    public void handle(PongMessage message) {
    }
}
