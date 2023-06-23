package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.ServerMessage;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote client interface given to the server to be able to exchange messages
 */
public interface ClientRMIInterface extends Remote, Serializable {
    /**
     * Receives server messages
     * @param serverMessage is the message sent by the server
     * @throws RemoteException RMI error
     */
    void receiveMessage(ServerMessage serverMessage) throws RemoteException;

    /**
     * Sends client messages to the server
     * @param clientMessage is the message that will be sent to the server
     * @throws RemoteException RMI error
     */
    void sendMessage(ClientMessage clientMessage) throws RemoteException;
}
