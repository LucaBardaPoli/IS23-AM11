package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.server.ClientHandler;

public class LoginRequest implements ClientMessage {
    private final String nickname;

    public LoginRequest(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return this.nickname;
    }

    @Override
    public void handle(ClientHandler clientHandler) {
        clientHandler.handle(this);
    }
}
