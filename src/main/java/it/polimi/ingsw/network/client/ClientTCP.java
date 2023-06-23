package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.ServerMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Client Class which implements the TCP connection type
 */
public class ClientTCP extends Client {
    private final int port;
    private Socket socket;
    private final ExecutorService executors;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    /**
     * Class Constructor
     * @param serverIp ip of the server to connect
     * @param port of the server
     */
    public ClientTCP(String serverIp, int port) {
        super(serverIp);
        this.port = port;
        this.executors = Executors.newCachedThreadPool();
    }

    /**
     * Opens the connection by opening a socket and the input/output streams.
     */
    public boolean openConnection() {
        try {
            this.socket = new Socket(this.serverIp, this.port);
            this.outputStream = new ObjectOutputStream(this.socket.getOutputStream());
            this.inputStream = new ObjectInputStream(this.socket.getInputStream());
            return true;
        } catch (IOException ignored) {

        }
        return false;
    }

    /**
     * Waits for messages coming from the server until the connection holds.
     */
    public void start() {
        try {
            while (!stopConnection) {
                ServerMessage serverMessage = (ServerMessage) this.inputStream.readObject();
                this.executors.submit(() -> serverMessage.handle(this.controller));
            }
        } catch (IOException | ClassNotFoundException ignored) {

        } finally {
            this.close(false);
        }
    }

    /**
     * Sends a client Message to the server
     * @param clientMessage is the message that will be sent to the server.
     */
    public void sendMessage(ClientMessage clientMessage) {
        try {
            this.outputStream.reset();
            this.outputStream.flush();
            this.outputStream.writeObject(clientMessage);
        } catch(IOException e) {
            this.close(false);
        }
    }

    /**
     * Closes the connection previously opened with the server
     */
    public void close(boolean messageFromServer) {
        if(this.stopConnection) {
            return;
        }
        this.stopConnection = true;
        if(!messageFromServer) {
            this.controller.getView().showDisconnection();
        }
        try {
            this.executors.shutdownNow();
            this.inputStream.close();
            this.outputStream.close();
            this.socket.close();
        } catch(Exception ignored) {

        }
    }
}
