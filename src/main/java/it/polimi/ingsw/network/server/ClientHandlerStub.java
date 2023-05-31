package it.polimi.ingsw.network.server;

import it.polimi.ingsw.network.message.ServerMessage;

public class ClientHandlerStub extends ClientHandler {

    public ClientHandlerStub(String nickname, int numPlayers) {
        super(new PingPongHandler());
        this.nickname = nickname;
        this.numPlayers = numPlayers;
    }

    public void sendMessage(ServerMessage serverMessage) {
    }
}
