package it.polimi.ingsw.network.chat;

import it.polimi.ingsw.network.chat.ChatClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChatServer extends Remote {
    void login(ChatClient cc) throws RemoteException;
    void send (String message) throws RemoteException;
}
