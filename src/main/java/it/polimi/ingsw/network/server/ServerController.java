package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.NetworkSettings;
import it.polimi.ingsw.network.RMIListener;
import it.polimi.ingsw.network.RMIListenerInterface;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerController {
    private ServerSocket serverSocket;
    private final ExecutorService executors;
    private boolean closeConnection;

    /**
     * Class Constructor
     */
    public ServerController() {
        this.executors = Executors.newCachedThreadPool();
        this.closeConnection = false;
    }

    /**
     * Starts the server
     * @param port This is the port the server listens on.
     */
    public void startServer(int port) {
        // TCP
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("Listening...");
        } catch (IOException e) {
            this.close();
            return;
        }

        // RMI
        try {
            DatagramSocket socket = new DatagramSocket();
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            String ip = socket.getLocalAddress().getHostAddress();
            System.setProperty("java.rmi.server.hostname", ip);
        } catch(SocketException | UnknownHostException | UncheckedIOException e) {
            System.out.println("Connecting to localhost...");
        } finally {
            try {
                Registry registry = LocateRegistry.createRegistry(NetworkSettings.SERVER_PORT_RMI);
                RMIListenerInterface rmiListener = (RMIListenerInterface) UnicastRemoteObject.exportObject(new RMIListener(), NetworkSettings.SERVER_PORT_RMI);
                registry.rebind(NetworkSettings.RMI_REMOTE_OBJECT, rmiListener);
                System.out.println("Exposed remote obj...");
            } catch(RemoteException | UncheckedIOException e) {
                this.close();
            }
        }

        System.out.println("Server ready...");
    }

    /**
     * Waits for players to connect
     */
    public void run() {
        while(!this.closeConnection) {
            try {
                System.out.println("Waiting...");
                Socket socket = this.serverSocket.accept();
                this.executors.submit(new ClientHandlerTCP(socket, new PingPongHandler()));
                System.out.println("New TCP client accepted");
            } catch(IOException e) {
                this.close();
            }
        }
    }

    /**
     * Closes the server
     */
    public void close() {
        this.closeConnection = true;
        try {
            this.serverSocket.close();
        } catch(Exception e) {
            System.out.println("Port already in use...");
        }
        this.executors.shutdownNow();
    }
}
