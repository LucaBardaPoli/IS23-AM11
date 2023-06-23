package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class PlayerDisconnectedNotify implements ServerMessage {
    private final String disconnectedPlayer;

    /**
     * Class constructor
     * @param disconnectedPlayer name of the player who has disconnected from the game
     */
    public PlayerDisconnectedNotify(String disconnectedPlayer) {
        this.disconnectedPlayer = disconnectedPlayer;
    }

    /**
     * Player's name getter
     * @return player's name
     */
    public String getDisconnectedPlayer() {
        return disconnectedPlayer;
    }

    /**
     * Handles the server message
     * @param clientController that handles the client server-side
     */
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
