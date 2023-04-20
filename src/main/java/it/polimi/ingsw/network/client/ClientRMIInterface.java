package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.ServerMessage;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientRMIInterface extends Remote, Serializable {
    public void receiveMessage(ServerMessage serverMessage) throws RemoteException;
    public void sendMessage(ClientMessage clientMessage) throws RemoteException;
}
