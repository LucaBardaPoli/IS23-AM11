package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.Settings;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerController {
    private int port;

    public ServerController() {
        this.port = Settings.SERVER_PORT_TCP;
    }

    public void startServer() {
        // TCP
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return;
        }

        // RMI
        InitClientHandler obj = new InitClientHandler();
        InitClientHandlerInterface stub = null;
        try {
            stub = (InitClientHandlerInterface) UnicastRemoteObject.exportObject(obj, Settings.SERVER_PORT_RMI);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        try {
            Registry registry = LocateRegistry.createRegistry(Settings.SERVER_PORT_RMI);
            registry.bind("InitHandler", stub);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }

        System.out.println("Server ready...");
        handleTCPConnections(serverSocket);
    }

    public void handleTCPConnections(ServerSocket serverSocket) {
        ExecutorService executor = Executors.newCachedThreadPool();
        while(true) {
            try {
                Socket socket = serverSocket.accept();
                executor.submit(new TCPInitClientHandler(socket));
                System.out.println("New client accepted");
            } catch(IOException e) {
                break;
            }
        }
        executor.shutdown();
    }
}
