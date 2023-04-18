package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.ServerMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.rmi.RemoteException;

public class TCPClient extends Client implements ClientMessageHandler {
    private final int port;
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public TCPClient(String serverIp, int port) {
        super(serverIp);
        this.port = port;
    }

    public void openConnection() {
        try {
            this.socket = new Socket(this.serverIp, this.port);
            this.inputStream = new ObjectInputStream(this.socket.getInputStream());
            this.outputStream = new ObjectOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startListening() {
        try {
            while(!stopConnection) {
                ServerMessage serverMessage = (ServerMessage) this.inputStream.readObject();
                serverMessage.handle(this);
            }
            this.inputStream.close();
            this.outputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void receiveMessage(ServerMessage serverMessage) throws RemoteException {

    }

    public void sendMessage(ClientMessage clientMessage) {

    }

    // Handles all kind of Server messages
    public void handle(ServerMessage serverMessage) {

    }
}
