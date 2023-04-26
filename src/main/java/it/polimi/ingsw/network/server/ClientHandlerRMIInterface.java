package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.ClientRMIInterface;
import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.ServerMessage;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientHandlerRMIInterface extends Remote, Serializable {
    void registerClient(ClientRMIInterface client) throws RemoteException;
    void receiveMessage(ClientMessage clientMessage) throws RemoteException;
    void sendMessage(ServerMessage serverMessage) throws RemoteException;
}
