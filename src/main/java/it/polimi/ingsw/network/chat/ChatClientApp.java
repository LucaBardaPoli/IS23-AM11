package it.polimi.ingsw.network.chat;

import it.polimi.ingsw.network.NetworkSettings;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ChatClientApp extends UnicastRemoteObject implements ChatClient {
    private ChatServer cs;

    protected ChatClientApp() throws RemoteException {
    }

    public void receive (String message) throws RemoteException {
        System.out.println(message);
    }

    private void startClient() throws Exception {
        Registry registry;
        registry = LocateRegistry.getRegistry(NetworkSettings.SERVER_NAME, NetworkSettings.SERVER_PORT_RMI);
        // Looking up the registry for the remote object
        this.cs = (ChatServer) registry.lookup("ChatService");
        this.cs.login(this);
        inputLoop();
    }

    private void inputLoop() throws IOException {
        BufferedReader br = new BufferedReader (new InputStreamReader(System.in)); String message;
        while ( (message = br.readLine ()) != null) {
            cs.send(message);
        }
    }

    public static void main(String[] args) {
        try {
            new ChatClientApp().startClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}