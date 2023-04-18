package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.RMIListener;
import it.polimi.ingsw.network.Settings;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerController {
    private ServerSocket serverSocket;
    private RMIListener rmiListener;
    private final ExecutorService executors;
    private boolean closeConnection;

    public ServerController() {
        this.executors = Executors.newCachedThreadPool();
        this.closeConnection = false;
    }

    public void startServer(int port) {
        // TCP
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("Listening...");
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }

        // RMI
        try {
            this.rmiListener = (RMIListener) UnicastRemoteObject.exportObject(new RMIListener(), Settings.SERVER_PORT_RMI);
            Registry registry = LocateRegistry.createRegistry(Settings.SERVER_PORT_RMI);
            registry.rebind(Settings.RMI_REMOTE_OBJECT, this.rmiListener);
            System.out.println("Exposed remote obj...");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        System.out.println("Server ready...");
    }

    public void run() {
        // We could save the handlers
        while(!this.closeConnection) {
            try {
                Socket socket = serverSocket.accept();
                this.executors.submit(new TCPClientHandler(socket));
                System.out.println("New client accepted");
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void close() {
        this.closeConnection = true;
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.executors.shutdown();
    }
}
