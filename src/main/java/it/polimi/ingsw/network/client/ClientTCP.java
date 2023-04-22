package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.ServerMessage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientTCP extends Client {
    private final int port;
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public ClientTCP(String serverIp, int port) {
        super(serverIp);
        this.port = port;
    }

    public void openConnection() {
        try {
            this.socket = new Socket(this.serverIp, this.port);
            this.outputStream = new ObjectOutputStream(this.socket.getOutputStream());
            this.inputStream = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startListening() {
        Thread t = new Thread(() -> {
            try {
                while (!stopConnection) {
                    ServerMessage serverMessage = (ServerMessage) this.inputStream.readObject();
                    serverMessage.handle(this.controller);
                }
                this.inputStream.close();
                this.outputStream.close();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        t.start();
    }

    public void sendMessage(ClientMessage clientMessage) {
        try {
            this.outputStream.writeObject(clientMessage);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
