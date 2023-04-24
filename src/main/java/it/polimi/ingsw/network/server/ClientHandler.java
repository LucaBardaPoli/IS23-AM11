package it.polimi.ingsw.network.server;

import it.polimi.ingsw.controller.LobbyManager;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.network.message.LoginRequest;
import it.polimi.ingsw.network.message.LoginResponse;
import it.polimi.ingsw.network.message.PongMessage;
import it.polimi.ingsw.network.message.ServerMessage;

import java.rmi.RemoteException;

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

    public abstract void sendMessage(ServerMessage serverMessage) throws RemoteException;

    public String getNickname() {
        return this.nickname;
    }

    public int getNumPlayers() {
        return this.numPlayers;
    }

    public void setModel(Game model) {
        this.model = model;
    }

    public void handle(LoginRequest clientMessage) throws RemoteException {
        /*if(this.lobbyManager.isNicknameTaken(clientMessage.getNickname())) {
            sendMessage(new LoginResponse(false));
        } else {
            sendMessage(new LoginResponse(true));
        }*/
        sendMessage(new LoginResponse(true));
    }

    public abstract void handle(PongMessage message)throws RemoteException;
}
