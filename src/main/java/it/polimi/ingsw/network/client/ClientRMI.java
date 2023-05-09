package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.server.ClientHandlerRMIInterface;
import it.polimi.ingsw.network.NetworkSettings;
import it.polimi.ingsw.network.RMIListenerInterface;
import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.ServerMessage;

import java.rmi.NoSuchObjectException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Client Class which implements the RMI connection type
 */
public class ClientRMI extends Client implements ClientRMIInterface {
    private ClientHandlerRMIInterface clientHandler;
    private Thread connectionTester;

    /**
     * Class Constructor
     * @param serverIp server's IP
     */
    public ClientRMI(String serverIp) {
        super(serverIp);
    }

    /**
     * Open RMI connection type.
     */
    public boolean openConnection() {
        try {
            Registry registry = LocateRegistry.getRegistry(this.serverIp);
            RMIListenerInterface rmiListener = (RMIListenerInterface) registry.lookup(NetworkSettings.RMI_REMOTE_OBJECT);
            UnicastRemoteObject.exportObject(this, NetworkSettings.CLIENT_PORT_RMI);
            this.clientHandler = rmiListener.getHandler();
            this.clientHandler.registerClient(this);
            return true;
        } catch (RemoteException | NotBoundException e) {
            this.close();
            return false;
        }
    }

    /**
     * As RMI is done, there is no need to listen for messages but it is sufficient to expose objects so that methods can be called remotely.
     */
    public void start() {
        this.connectionTester = new Thread(() -> {
            do {
                try {
                    Thread.sleep(NetworkSettings.MAX_PONG_WAIT);
                } catch (InterruptedException e) {
                    this.close();
                }
                try {
                    this.clientHandler.testConnection();
                } catch(RemoteException e) {
                    this.close();
                }
            } while(!this.stopConnection);
        });
        this.connectionTester.start();
    }

    /**
     * Receives a server message from the server
     * @param serverMessage is the message sent by the server
     * @throws RemoteException network error
     */
    public void receiveMessage(ServerMessage serverMessage) throws RemoteException {
        serverMessage.handle(this.controller);
    }

    /**
     * Sends a client message to the server
     * @param clientMessage is the message that will be sent to the server
     */
    public void sendMessage(ClientMessage clientMessage) {
        try {
            this.clientHandler.receiveMessage(clientMessage);
        } catch(RemoteException e) {
            close();
        }
    }

    /**
     * Ends the exportation on the remote-object.
     */
    public void close() {
        if(this.stopConnection) {
            return;
        }
        this.stopConnection = true;
        try {
            System.out.println("Closing connection with the server...");
            if(this.connectionTester != null) {
                this.connectionTester.interrupt();
            }
            if (this.controller != null) {
                this.controller.getView().setEndGame(true);
                this.controller.getView().showPlayerDisconnected("Server");
            }
            UnicastRemoteObject.unexportObject(this, true);
        } catch (NoSuchObjectException e) {
            ;
        }
    }
}
