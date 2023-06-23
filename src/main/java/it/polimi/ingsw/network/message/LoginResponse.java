package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class LoginResponse implements ServerMessage {
    private final String nickname;

    /**
     * Class constructor
     * @param nickname null if it's already taken
     */
    public LoginResponse(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Nickname getter
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Handles the server message
     * @param clientController that handles the client server-side
     */
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
