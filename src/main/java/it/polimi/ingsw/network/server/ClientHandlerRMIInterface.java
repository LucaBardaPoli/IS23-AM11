package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.ClientRMIInterface;
import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.ServerMessage;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientHandlerRMIInterface extends Remote, Serializable {
    /**
     * Registers a client
     * @param client is the client that will be registered
     * @throws RemoteException
     */
    void registerClient(ClientRMIInterface client) throws RemoteException;

    /**
     * Receives a message from the client
     * @param clientMessage sent by the client
     * @throws RemoteException
     */
    void receiveMessage(ClientMessage clientMessage) throws RemoteException;

    /**
     * Sends a message to the client
     * @param serverMessage sent to the client
     * @throws RemoteException
     */
    void sendMessage(ServerMessage serverMessage) throws RemoteException;
}
