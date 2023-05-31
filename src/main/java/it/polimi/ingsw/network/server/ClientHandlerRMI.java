package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.ClientRMIInterface;
import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.ServerMessage;

import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Server-side handler for an RMI client
 */
public class ClientHandlerRMI extends ClientHandler implements ClientHandlerRMIInterface {
    private ClientRMIInterface client;
    private final ExecutorService executors;

    /**
     * Class Constructor
     * @param pingPongHandler is a thread used to handle the PingPong
     */
    public ClientHandlerRMI(PingPongHandler pingPongHandler) {
        super(pingPongHandler);
        this.executors = Executors.newCachedThreadPool();
    }

    /**
     * Registers a client
     * @param client is the client that will be registered
     * @throws RemoteException RMI error
     */
    public void registerClient(ClientRMIInterface client) throws RemoteException {
        this.client = client;
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
        this.executors.submit(() -> {
            try {
                client.receiveMessage(serverMessage);
            } catch (RemoteException e) {
                if (!this.stopConnection) {
                    this.initClose();
                }
            }
        });
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
