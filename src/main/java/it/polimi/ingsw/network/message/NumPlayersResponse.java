package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.server.ClientHandler;

public class NumPlayersResponse implements ClientMessage {
    private final int numPlayers;

    /**
     * Class constructor
     * @param numPlayers number of players of the game
     */
    public NumPlayersResponse(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    /**
     * Number of player getter
     * @return number of player
     */
    public int getNumPlayers() {
        return numPlayers;
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
