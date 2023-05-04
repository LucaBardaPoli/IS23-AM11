package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.ClientRMIInterface;
import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.ServerMessage;

import java.rmi.RemoteException;

public class ClientHandlerRMI extends ClientHandler implements ClientHandlerRMIInterface {
    private ClientRMIInterface client;

    /**
     * Class Constructor
     * @param pingPongHandler is a thread used to handle the PingPong
     */
    public ClientHandlerRMI(PingPongHandler pingPongHandler) {
        super(pingPongHandler);
    }

    /**
     * Registers a client
     * @param client is the client that will be registered
     * @throws RemoteException
     */
    public void registerClient(ClientRMIInterface client) throws RemoteException {
        this.client = client;
    }

    /**
     * Receives a client message
     * @param clientMessage sent by the client
     * @throws RemoteException
     */
    public void receiveMessage(ClientMessage clientMessage) throws RemoteException {
        clientMessage.handle(this);
    }

    /**
     * Sends a server message
     * @param serverMessage sent to the client
     */
    public void sendMessage(ServerMessage serverMessage) {
        new Thread(() -> {
            try {
                this.client.receiveMessage(serverMessage);
            } catch(RemoteException e) {
                this.initClose();
            }
        }).start();
    }

    /**
     * Closes the connection
     */
    public void close() {
        super.close();
    }

    /**
     * Initializes server shutdown
     */
    public void initClose() {
        super.initClose();
    }
}
