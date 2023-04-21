package it.polimi.ingsw.network.server;

import it.polimi.ingsw.controller.LobbyManager;
import it.polimi.ingsw.model.CardType;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.network.message.*;

import java.util.List;
import java.util.Optional;

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

    public String getNickname() {
        return this.nickname;
    }

    public int getNumPlayers() {
        return this.numPlayers;
    }

    public void setModel(Game model) {
        this.model = model;
        if(this.model.checkPlayer(this.nickname)) {
            sendMessage(new GameStartNotify(this.model.getBoard(), this.model.getCommonGoals(), this.model.getPersonalGoal(this.nickname).get(), true));
        } else {
            sendMessage(new GameStartNotify(this.model.getBoard(), this.model.getCommonGoals(), this.model.getPersonalGoal(this.nickname).get(), false));
        }
    }

    public abstract void sendMessage(ServerMessage serverMessage);

    //connecting a new player checking if the username is either valid or not
    public void handle(LoginRequest clientMessage) {
        if(this.lobbyManager.isNicknameTaken(clientMessage.getNickname())) {
            sendMessage(new LoginResponse(false));
        } else {
            this.nickname = clientMessage.getNickname();
            sendMessage(new LoginResponse(true));
            if(this.lobbyManager.getLobby().isEmpty()) {
                sendMessage(new NumPlayersRequest());
            } else {
                this.lobbyManager.addPlayer(this);
            }
        }
    }

    //receives from the client the number of players we wats to play with
    public void handle(NumPlayersResponse clientMessage) {
        if(this.lobbyManager.isNumPlayersValid(clientMessage.getNumPlayers())) {
            this.numPlayers = clientMessage.getNumPlayers();
            this.lobbyManager.addPlayer(this);
        } else {
            sendMessage(new NumPlayersRequest());
        }
    }

    //checks if the picks made by the player are available
    public void handle(PickTileRequest clientMessage) {
        if(this.model.pickCard(clientMessage.getPosition()).isPresent()) {
            sendMessage(new PickTileResponse(true));
        } else {
            sendMessage(new PickTileResponse(false));
        }
    }

    //confirms the set of cards picked previously by the player and goes on to get them from the board
    public void handle(ConfirmPickNotify clientMessage) {
        this.model.confirmPick();
        // We need to send this message to each player!!!!!!!!!!!!!!!!!!! Add observer to other players
        sendMessage(new NewBoardNotify(this.model.getBoard()));
    }

    //confirms the column selected by the player
    public void handle(ConfirmColumnRequest clientMessage) {
        if(this.model.confirmColumn(clientMessage.getColumnNumber())) {
            sendMessage(new ConfirmColumnResponse(true));
        } else {
            sendMessage(new ConfirmColumnResponse(false));
        }
    }

    //rearrange the order of the cards before adding them to the bookshelf
    public void handle(SwapTilesOrderRequest clientMessage) {
        Optional<List<CardType>> result = this.model.rearrangeCards(clientMessage.getIndex());
        if(result.isPresent()) {
            sendMessage(new SwapTilesOrderResponse(result.get(), true));
        } else {
            sendMessage(new SwapTilesOrderResponse(this.model.getPickedCards(), false));
        }
    }

    //confirms the order of the cards that was previously selected
    public void handle(ConfirmOrderNotify clientMessage) {
        this.model.confirmOrderSelectedCards();
        // We need to send this message to each player!!!!!!!!!!!!!!!!!!! Add observer to other players
        sendMessage(new EndTurnNotify(this.model.getBookshelf(this.model.getLastPlayer().getNickname()).get(), this.model.getPlayerPoints(this.model.getLastPlayer().getNickname()).get(), this.model.checkPlayer(this.nickname)));
    }
}