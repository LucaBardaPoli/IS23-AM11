package it.polimi.ingsw.network.chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatClient extends Remote {
    void receive(String message) throws RemoteException;
}
