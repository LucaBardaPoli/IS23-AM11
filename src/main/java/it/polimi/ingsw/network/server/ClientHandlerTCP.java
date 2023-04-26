package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.PongMessage;
import it.polimi.ingsw.network.message.ServerMessage;

import java.io.*;
import java.net.Socket;

public class ClientHandlerTCP extends ClientHandler implements Runnable {
    private final Socket socket;
    private final ObjectInputStream inputStream;
    private final ObjectOutputStream outputStream;
    private final PingPongHandler pingPongHandler;

    public ClientHandlerTCP(Socket socket, PingPongHandler pingPongHandler) throws IOException {
        super();
        this.socket = socket;
        this.pingPongHandler = pingPongHandler;
        this.pingPongHandler.setClientHandlerTCP(this);
        this.inputStream = new ObjectInputStream(socket.getInputStream());
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.outputStream.flush();
    }

    public void run() {
        try {
            while (!stopConnection) {
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
            this.outputStream.reset();
            this.outputStream.flush();
            this.outputStream.writeObject(serverMessage);
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

    @Override
    public void handle(PongMessage message) {
        this.pingPongHandler.notifyReceivedMessage();
        System.out.println("Pong received");
    }
}
