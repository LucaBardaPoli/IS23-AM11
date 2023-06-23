package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.server.ClientHandler;

public class LoginRequest implements ClientMessage {
    private final String nickname;

    /**
     * Class constructor
     * @param nickname name that will be used during the game
     */
    public LoginRequest(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Nickname getter
     * @return nickname
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * Handles the Client message
     * @param clientHandler that handles the client client-side
     */
    @Override
    public void handle(ClientHandler clientHandler) {
        clientHandler.handle(this);
    }
}
