package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.ClientMessage;

/**
 * Server-side class that represents the client information.
 */

public abstract class Client {
    protected String serverIp;
    protected String nickname;
    protected ClientController controller;
    protected boolean stopConnection;

    /**
     * Class constructor
     * @param serverIp server ip to connect to
     */
    public Client(String serverIp) {
        this.serverIp = serverIp;
        this.stopConnection = false;
    }

    /**
     * Getter method.
     * @return the nickname of the player
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * Getter method
     * @return true if the connection is closing
     */
    public boolean getStopConnection() {
        return stopConnection;
    }

    /**
     * Setter method
     * @param clientController is the class which controls the main functions of the client.
     */
    public void setController(ClientController clientController) {
        this.controller = clientController;
    }

    /**
     * Setter method
     * @param nickname is the nickname chosen by the player.
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Open the connection with server.
     * @return true if it's connected to the server
     */
    public abstract boolean openConnection();

    /**
     * Wait for messages coming from the server until the connection holds.
     */
    public abstract void start();

    /**
     * Send a client Message
     * @param clientMessage is the message that will be sent to the server.
     */
    public abstract void sendMessage(ClientMessage clientMessage);

    /**
     * Closes the connection opened before with the server.
     * @param messageFromServer flag to know who discovered the disconnection
     */
    public abstract void close(boolean messageFromServer);
}
