package it.polimi.ingsw.network.server;

import it.polimi.ingsw.controller.LobbyManager;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.network.message.ServerMessage;

public abstract class ClientHandler {
    private String nickname;
    private int numPlayers;
    protected LobbyManager lobbyManager;
    protected Game model;
    protected boolean stopConnection;

    public ClientHandler() {
        this.lobbyManager = LobbyManager.getInstance();
        this.stopConnection = false;
    }

    public abstract void sendMessage(ServerMessage serverMessage);

    public String getNickname() {
        return this.nickname;
    }

    public int getNumPlayers() {
        return this.numPlayers;
    }

    public void setModel(Game model) {
        this.model = model;
    }
    
    /*
    All methods to notify something to this specific player
    Each method will call sendMessage with the right subclass of Message
     */
}
