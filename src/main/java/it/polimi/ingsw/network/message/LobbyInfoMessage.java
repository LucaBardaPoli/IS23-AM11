package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

import java.util.ArrayList;
import java.util.List;

public class LobbyInfoMessage implements ServerMessage {
    private final int sizeLobby;
    private final List<String> lobby;
    private final boolean newPlayerConnected;
    private final String playerName;

    /**
     * Class constructor
     * @param sizeLobby size of the lobby
     * @param lobby lobby of the game
     * @param newPlayerConnected true if connected, false if disconnected
     * @param playerName name of the player
     */
    public LobbyInfoMessage(int sizeLobby, List<String> lobby, boolean newPlayerConnected, String playerName){
        this.sizeLobby = sizeLobby;
        this.lobby = new ArrayList<>(lobby);
        this.newPlayerConnected = newPlayerConnected;
        this.playerName = playerName;
    }

    /**
     * Lobby size getter
     * @return lobby size
     */
    public int getSizeLobby() {
        return sizeLobby;
    }

    /**
     * Lobby getter
     * @return lobby
     */
    public List<String> getLobby() {
        return lobby;
    }

    /**
     * New player connected getter
     * @return new player connected
     */
    public boolean isNewPlayerConnected() {
        return newPlayerConnected;
    }

    /**
     * Player name getter
     * @return player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Handles the server message
     * @param clientController that handles the client server-side
     */
    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
