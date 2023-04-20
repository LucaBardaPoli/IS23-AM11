package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.ServerMessage;

import java.io.*;
import java.net.Socket;

public class ClientHandlerTCP extends ClientHandler implements Runnable {
    private final Socket socket;
    private final ObjectInputStream inputStream;
    private final ObjectOutputStream outputStream;

    public ClientHandlerTCP(Socket socket) throws IOException {
        super();
        this.socket = socket;
        this.inputStream = new ObjectInputStream(socket.getInputStream());
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.outputStream.flush();
    }

    public void run() {
        try {
            System.out.println("OK");
            while(!stopConnection) {
                ClientMessage clientMessage = (ClientMessage) this.inputStream.readObject();
                clientMessage.handle(this);
            }
            this.inputStream.close();
            this.outputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(ServerMessage serverMessage) {
        try {
            this.outputStream.writeObject(serverMessage);
            this.outputStream.flush();
            this.outputStream.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        this.stopConnection = true;
        this.outputStream.close();
        this.inputStream.close();
        this.socket.close();
    }
}
