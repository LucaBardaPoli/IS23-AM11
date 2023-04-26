package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.server.ClientHandlerRMIInterface;
import it.polimi.ingsw.network.NetworkSettings;
import it.polimi.ingsw.network.RMIListenerInterface;
import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.ServerMessage;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ClientRMI extends Client implements ClientRMIInterface {
    private ClientHandlerRMIInterface clientHandler;

    public ClientRMI(String serverIp) {
        super(serverIp);
    }

    public void openConnection() {
        try {
            Registry registry = LocateRegistry.getRegistry(this.serverIp);
            RMIListenerInterface rmiListener = (RMIListenerInterface) registry.lookup(NetworkSettings.RMI_REMOTE_OBJECT);
            UnicastRemoteObject.exportObject(this, NetworkSettings.CLIENT_PORT_RMI);
            this.clientHandler = rmiListener.getHandler();
            this.clientHandler.registerClient(this);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    public void startListening() {
    }

    public void receiveMessage(ServerMessage serverMessage) throws RemoteException {
        serverMessage.handle(this.controller);
    }

    public void sendMessage(ClientMessage clientMessage) {
        try {
            this.clientHandler.receiveMessage(clientMessage);
        } catch(RemoteException e) {
            e.printStackTrace();
        }
    }
}
