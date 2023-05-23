package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.ClientRMIInterface;
import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.ServerMessage;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Server-side handler for an RMI client
 */
public class ClientHandlerRMI extends ClientHandler implements ClientHandlerRMIInterface {
    private ClientRMIInterface client;

    /**
     * Class Constructor
     * @param pingPongHandler is a thread used to handle the PingPong
     */
    public ClientHandlerRMI(ClientRMIInterface clientRMIInterface, PingPongHandler pingPongHandler) throws RemoteException {
        super(pingPongHandler);
        this.client = clientRMIInterface;
    }

    /**
     * Method used from remote RMI-clients to test whether the connection is still open
     * @throws RemoteException RMI error
     */
    public void testConnection() throws RemoteException {
    }

    /**
     * Receives a client message
     * @param clientMessage sent by the client
     * @throws RemoteException RMI error
     */
    public void receiveMessage(ClientMessage clientMessage) throws RemoteException {
        clientMessage.handle(this);
    }

    /**
     * Sends a server message
     * @param serverMessage sent to the client
     */
    public void sendMessage(ServerMessage serverMessage) {
        // Fare coda
        new Thread(() -> {
            try {
                this.client.receiveMessage(serverMessage);
            } catch(RemoteException e) {
                if (!this.stopConnection) {
                    this.initClose();
                }
                e.printStackTrace();
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
