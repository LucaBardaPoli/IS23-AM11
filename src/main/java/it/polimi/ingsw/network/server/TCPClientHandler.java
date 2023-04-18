package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.LoginRequest;
import it.polimi.ingsw.network.message.ServerMessage;

import java.io.*;
import java.net.Socket;

public class TCPClientHandler extends ClientHandler implements Runnable, ServerMessageHandler {
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public TCPClientHandler(Socket socket) throws IOException {
        super();
        this.socket = socket;
        this.inputStream = new ObjectInputStream(socket.getInputStream());
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
    }

    public void run() {
        try {
            while(!stopConnection) {
                ClientMessage clientMessage = (ClientMessage) this.inputStream.readObject();
                clientMessage.handle(this);
            }
            this.inputStream.close();
            this.outputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // Handles all kind of Client messages
    public void handle(ClientMessage clientMessage) {

    }

    // Send all kind of Server messages
    public void sendMessage(ServerMessage serverMessage) {
        try {
            this.outputStream.writeObject(new LoginRequest());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
