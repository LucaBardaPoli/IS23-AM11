package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class PlayerDisconnectedNotify implements ServerMessage {

    private final String disconnectedPlayer;

    public PlayerDisconnectedNotify(String disconnectedPlayer) {
        this.disconnectedPlayer = disconnectedPlayer;
    }

    public String getDisconnectedPlayer() {
        return disconnectedPlayer;
    }

    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
