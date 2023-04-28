package it.polimi.ingsw.network.server;

import it.polimi.ingsw.controller.LobbyManager;
import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.network.message.*;

import java.util.ArrayList;
import java.util.List;

public abstract class ClientHandler implements Listener {
    private String nickname;
    private int numPlayers;
    protected LobbyManager lobbyManager;
    private List<ClientHandler> lobby;
    protected Game model;
    private EventListener eventListener;
    protected PingPongHandler pingPongHandler;
    protected boolean stopConnection;

    public ClientHandler(PingPongHandler pingPongHandler) {
        this.lobbyManager = LobbyManager.getInstance();
        this.stopConnection = false;
        this.pingPongHandler = pingPongHandler;
        this.pingPongHandler.setClientHandler(this);
        this.pingPongHandler.run();
    }

    public String getNickname() {
        return this.nickname;
    }

    public int getNumPlayers() {
        return this.numPlayers;
    }

    public void initGame(Game model, List<ClientHandler> lobby) {
        this.model = model;
        this.lobby = lobby;
        sendMessage(new GameStartNotify(this.model.getBoard(), new ArrayList<>(this.model.getCommonGoals()), this.model.getPersonalGoal(this.nickname).get(), this.model.getCurrentPlayer().getNickname()));
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public abstract void sendMessage(ServerMessage serverMessage);

    public abstract void handle(PongMessage message);

    //connecting a new player checking if the username is either valid or not
    public void handle(LoginRequest clientMessage) {
        if(this.lobbyManager.isNicknameTaken(clientMessage.getNickname())) {
            sendMessage(new LoginResponse(null));
        } else {
            this.nickname = clientMessage.getNickname();
            sendMessage(new LoginResponse(clientMessage.getNickname()));
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
        if(!this.model.pickTile(clientMessage.getPosition()).equals(Tile.EMPTY)) {
            sendMessage(new PickTileResponse(true));
        } else {
            sendMessage(new PickTileResponse(false));
        }
    }

    public void handle(RemoveTileRequest clientMessage) {
        if(this.model.removeTile(clientMessage.getPosition())) {
            sendMessage(new RemoveTileResponse(this.model.getPickedTiles(), true));
        } else {
            sendMessage(new RemoveTileResponse(this.model.getPickedTiles(), false));
        }
    }

    //confirms the set of cards picked previously by the player and goes on to get them from the board
    public void handle(ConfirmPickNotify clientMessage) {
        this.model.confirmPick();
        this.eventListener.notifyListeners(new NewBoardNotify(this.model.getBoard()));
    }

    //confirms the column selected by the player
    public void handle(ConfirmColumnRequest clientMessage) {
        sendMessage(new ConfirmColumnResponse(this.model.confirmColumn(clientMessage.getColumnNumber())));
    }

    //rearrange the order of the cards before adding them to the bookshelf
    public void handle(SwapTilesOrderRequest clientMessage) {
        sendMessage(new SwapTilesOrderResponse(this.model.getPickedTiles(), this.model.rearrangeTiles(clientMessage.getIndex())));
    }

    //confirms the order of the cards that was previously selected
    public void handle(ConfirmOrderNotify clientMessage) {
        this.model.confirmOrderSelectedTiles();
        this.eventListener.notifyListeners(new EndTurnNotify(this.model.getBookshelf(this.model.getLastPlayer().getNickname()).get(), this.model.getPlayerPoints(this.model.getLastPlayer().getNickname()).get(), this.model.getCurrentPlayer().getNickname()));
    }

    public void handle(ChatMessage clientMessage) {
        if(clientMessage.getReceiver() != null) {
            for(ClientHandler c : this.lobby) {
                if(c.getNickname().equals(clientMessage.getReceiver())) {
                    this.eventListener.notifyListeners(clientMessage, c);
                    return;
                }
            }
        }
        this.eventListener.notifyListeners(clientMessage);
    }

    public void notify(ServerMessage serverMessage) {
        sendMessage(serverMessage);
    }

    public abstract void close();
}