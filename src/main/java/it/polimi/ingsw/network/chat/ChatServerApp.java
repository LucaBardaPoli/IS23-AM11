package it.polimi.ingsw.network.chat;

import it.polimi.ingsw.network.NetworkSettings;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ChatServerApp extends UnicastRemoteObject implements ChatServer {
    private final List<ChatClient> chatClients;

    public ChatServerApp() throws RemoteException {
        this.chatClients = new ArrayList<>();
    }

    public void login(ChatClient cc) throws RemoteException {
        this.chatClients.add(cc);
    }

    public void send(String message) throws RemoteException {
        System.out.println("server received: " + message);
        for (ChatClient cc : chatClients) {
            cc.receive(message);
        }
    }

    private void startServer() throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(NetworkSettings.SERVER_PORT_RMI);
        try {
            registry.bind("ChatService", this);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Server ready");
    }

    public static void main(String[] args) {
        try {
            new ChatServerApp().startServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}