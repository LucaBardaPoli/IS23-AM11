package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class LoginResponse implements ServerMessage {
    private final String nickname;

    public LoginResponse(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
