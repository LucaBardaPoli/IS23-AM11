package it.polimi.ingsw.network.server;

import it.polimi.ingsw.controller.LobbyManager;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.network.message.*;

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

    public void setNumPlayers(int numPlayers) {
        this.numPlayers = numPlayers;
    }

    public Game getModel() {
        return model;
    }

    //connecting a new player checking if the username is either valid or not
    public void handle(LoginRequest clientMessage) throws RemoteException {
        if(this.lobbyManager.isNicknameTaken(clientMessage.getNickname())) {
            sendMessage(new LoginResponse(false));
        } else {
            sendMessage(new LoginResponse(true));
        }
        sendMessage(new LoginResponse(true));
    }

    //confirms the column selected by the player
    public void handle(ConfirmColumnRequest clientMessage) {
        model.confirmColumn(clientMessage.getColumnNumber());
    }

    //confirms the order of the cards that was previously selected
    public void handle(ConfirmOrderNotify clientMessage) {
        model.confirmOrderSelectedCards();
    }

    //confirms the set of cards picked previously by the player and goes on to get them from the board
    public void handle(ConfirmPickNotify clientMessage) {
        model.confirmChoice();
    }

    //checks if the picks made by the player are available
    public void handle(PickTaleRequest clientMessage) throws RemoteException {
        if(model.pickCard(clientMessage.getPosition()).isPresent()){
            sendMessage(new PickTaleResponse(false));
            model.getPickedCardsPositions().add(clientMessage.getPosition());
        }
        else {
            sendMessage(new PickTaleResponse(true));
        }
    }

    //rearrange the order of the cards before adding them to the bookshelf
    public void handle(SwapTilesOrderRequest clientMessage) {
        model.rearrangeCards(clientMessage.getPosition());
    }

    //receives from the client the number of players we wats to play with
    public void handle(NumPlayersResponse numPlayersRequest){
        this.setNumPlayers(numPlayersRequest.getNumPlayers());
    }

}
