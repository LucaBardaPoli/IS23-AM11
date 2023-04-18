package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.server.RMIClientHandler;
import it.polimi.ingsw.network.RMIListener;
import it.polimi.ingsw.network.Settings;
import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.ServerMessage;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIClient extends Client implements Remote, ClientMessageHandler {

    private RMIClientHandler clientHandler;

    public RMIClient(String serverIp) {
        super(serverIp);
    }

    public void openConnection() {
        try {
            Registry registry = LocateRegistry.getRegistry(this.serverIp);
            RMIListener rmiListener = (RMIListener) registry.lookup(Settings.RMI_REMOTE_OBJECT);
            this.clientHandler = rmiListener.getHandler();
            UnicastRemoteObject.exportObject(this, Settings.CLIENT_PORT_RMI);
            this.clientHandler.register(this);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    public void startListening() {
    }

    public void receiveMessage(ServerMessage serverMessage) throws RemoteException {
        handle(serverMessage);
    }

    public void sendMessage(ClientMessage clientMessage) {

    }

    // Handles all kind of Server messages
    public void handle(ServerMessage serverMessage) {

    }
}
