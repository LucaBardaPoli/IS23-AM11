package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.message.ServerMessage;

/**
 * Server-side handler for a generic client (used for testing)
 */
public class ClientHandlerStub extends ClientHandler {
    /**
     * Class constructor
     * @param nickname name of the player
     * @param numPlayers number of players playing the current game
     */
    public ClientHandlerStub(String nickname, int numPlayers) {
        super(new PingPongHandler());
        this.nickname = nickname;
        this.numPlayers = numPlayers;
    }

    /**
     * Method that send a server message
     * @param serverMessage is the server message sent
     */
    public void sendMessage(ServerMessage serverMessage) {
    }
}
