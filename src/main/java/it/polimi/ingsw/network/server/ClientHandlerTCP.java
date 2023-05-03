package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.ServerMessage;

import java.io.*;
import java.net.Socket;

public class ClientHandlerTCP extends ClientHandler implements Runnable {
    private final Socket socket;
    private final ObjectInputStream inputStream;
    private final ObjectOutputStream outputStream;

    public ClientHandlerTCP(Socket socket, PingPongHandler pingPongHandler) throws IOException {
        super(pingPongHandler);
        this.socket = socket;
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
        } catch (IOException | ClassNotFoundException e) {
            this.pingPongHandler.stopPing();
            this.initClose();
        }
    }

    public void sendMessage(ServerMessage serverMessage) {
        try {
            this.outputStream.reset();
            this.outputStream.flush();
            this.outputStream.writeObject(serverMessage);
        } catch (IOException e) {
            initClose();
        }
    }

    public void close() {
        super.close();
        try {
            if(this.outputStream != null) {
                this.outputStream.close();
            }
            if(this.inputStream != null) {
                this.inputStream.close();
            }
            this.socket.close();
        } catch(IOException e) {
            ;
        }
    }

    public void initClose() {
        super.initClose();
        this.stopConnection = true;
        this.close();
    }
}
