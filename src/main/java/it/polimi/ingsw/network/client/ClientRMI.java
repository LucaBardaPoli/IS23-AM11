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

    /**
     * Class Constructor
     * @param serverIp
     */
    public ClientRMI(String serverIp) {
        super(serverIp);
    }

    /**
     * Open RMI connection type.
     */
    public void openConnection() {
        try {
            // si collega al registro del server da remoto che è stato pubblicato
            Registry registry = LocateRegistry.getRegistry(this.serverIp);
            // dal registro tira giù l'oggetto
            RMIListenerInterface rmiListener = (RMIListenerInterface) registry.lookup(NetworkSettings.RMI_REMOTE_OBJECT);
            // esporto lui stesso
            UnicastRemoteObject.exportObject(this, NetworkSettings.CLIENT_PORT_RMI);
            // chiede un handler per lui
            this.clientHandler = rmiListener.getHandler();
            // da all handler se stesso
            this.clientHandler.registerClient(this);
        } catch (RemoteException | NotBoundException e) {
            close();
        }
    }

    /**
     * As RMI is done, there is no need to listen for messages but it is sufficient to expose objects so that methods can be called remotely.
     */
    public void start() {
    }

    /**
     * Receives a server message from the server
     * @param serverMessage is the message sent by the server
     * @throws RemoteException
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
        try {
            UnicastRemoteObject.unexportObject(this, true);
        } catch(NoSuchObjectException e) {
            ;
        }
    }
}
