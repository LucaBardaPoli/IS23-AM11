package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.message.ClientMessage;
import it.polimi.ingsw.network.message.ServerMessage;

import java.io.*;
import java.net.Socket;

/**
 * Server-side handler for a TCP client
 */
public class ClientHandlerTCP extends ClientHandler implements Runnable {
    private final Socket socket;
    private final ObjectInputStream inputStream;
    private final ObjectOutputStream outputStream;

    /**
     * Class Constructor: TCP Client Handler
     * @param socket is the assigned socket for the connection
     * @param pingPongHandler is a thread which is used to handle the PingPong
     * @throws IOException TCP error
     */
    public ClientHandlerTCP(Socket socket, PingPongHandler pingPongHandler) throws IOException {
        super(pingPongHandler);
        this.socket = socket;
        this.inputStream = new ObjectInputStream(socket.getInputStream());
        this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        this.outputStream.flush();
    }

    /**
     * Waits for messages coming from the server until the connection holds.
     * Once the connection is down, the PingPong stops and the server-shutdown procedure is started
     */
    public void run() {
        try {
            while(!stopConnection) {
                ClientMessage clientMessage = (ClientMessage) this.inputStream.readObject();
                clientMessage.handle(this);
            }
        } catch(IOException | ClassNotFoundException e) {
            if(!this.stopConnection) {
                this.initClose();
            }
        }
    }

    /**
     * Sends a server message
     * @param serverMessage is the message sent by the ClientHandler
     */
    public void sendMessage(ServerMessage serverMessage) {
        try {
            this.outputStream.reset();
            this.outputStream.flush();
            this.outputStream.writeObject(serverMessage);
        } catch (IOException e) {
            if(!this.stopConnection) {
                this.initClose();
            }
        }
    }

    /**
     * Closes the connection
     */
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
        } catch(IOException ignored) {

        }
    }

    /**
     * Initializes server shutdown
     */
    public void initClose() {
        super.initClose();
        this.close();
    }
}
