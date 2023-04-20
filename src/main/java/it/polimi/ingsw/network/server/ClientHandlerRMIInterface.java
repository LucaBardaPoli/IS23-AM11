package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.client.ClientRMIInterface;
import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.ServerMessage;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientHandlerRMIInterface extends Remote, Serializable {
    public void registerClient(ClientRMIInterface client) throws RemoteException;

    public void receiveMessage(ClientMessage clientMessage) throws RemoteException;

    public void sendMessage(ServerMessage serverMessage) throws RemoteException;
}
