package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.ClientRMIInterface;
import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.ServerMessage;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public interface ClientHandlerRMIInterface extends Remote, Serializable {

    /**
     * Method used from remote RMI-clients to test whether the connection is still open
     * @throws RemoteException RMI error
     */
    void testConnection() throws RemoteException;

    /**
     * Receives a message from the client
     * @param clientMessage sent by the client
     * @throws RemoteException RMI error
     */
    void receiveMessage(ClientMessage clientMessage) throws RemoteException;

    /**
     * Sends a message to the client
     * @param serverMessage sent to the client
     * @throws RemoteException RMI error
     */
    void sendMessage(ServerMessage serverMessage) throws RemoteException;
}
