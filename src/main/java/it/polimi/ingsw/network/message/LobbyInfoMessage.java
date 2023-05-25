package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

import java.util.ArrayList;
import java.util.List;

public class LobbyInfoMessage implements ServerMessage {
    private final int sizeLobby;

    private final List<String> lobby;

    private final boolean newPlayerConnected;

    // True -> connected, False -> disconnected
    private final String playerName;

    public LobbyInfoMessage(int sizeLobby, List<String> lobby, boolean newPlayerConnected, String playerName){
        this.sizeLobby = sizeLobby;
        this.lobby = new ArrayList<>(lobby);
        this.newPlayerConnected = newPlayerConnected;
        this.playerName = playerName;
    }

    public int getSizeLobby() {
        return sizeLobby;
    }

    public List<String> getLobby() {
        return lobby;
    }

    public boolean isNewPlayerConnected() {
        return newPlayerConnected;
    }

    public String getPlayerName() {
        return playerName;
    }


    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
