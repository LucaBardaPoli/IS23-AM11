package it.polimi.ingsw.network.server;

import it.polimi.ingsw.controller.LobbyManager;
import it.polimi.ingsw.model.CommonGoal;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.network.message.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Server-side handler for a generic client
 */
public abstract class ClientHandler implements Listener {
    protected String nickname;
    protected int numPlayers;
    private final LobbyManager lobbyManager;
    protected List<ClientHandler> lobby;
    protected Game model;
    protected EventListener eventListener;
    protected PingPongHandler pingPongHandler;
    protected boolean stopConnection;

    /**
     * Class Constructor
     * @param pingPongHandler is a thread that handles the PingPong
     */
    public ClientHandler(PingPongHandler pingPongHandler) {
        this.lobbyManager = LobbyManager.getInstance();
        this.stopConnection = false;
        this.pingPongHandler = pingPongHandler;
        this.pingPongHandler.setClientHandler(this);
        new Thread(this.pingPongHandler).start();
    }

    /**
     * Getter method:
     * @return player's nickname.
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * Getter method:
     * @return the number of players in the current game.
     */
    public int getNumPlayers() {
        return this.numPlayers;
    }

    /**
     * Initializes a new game
     * @param model of the game
     * @param lobby of the players
     */
    public void initGame(Game model, List<ClientHandler> lobby) {
        this.model = model;
        this.lobby = lobby;

        List<Integer> commonGoalsTokens = new ArrayList<>();
        for(CommonGoal commonGoal : this.model.getCommonGoals()) {
            if(this.model.getTopToken(commonGoal).isPresent()) {
                commonGoalsTokens.add(this.model.getTopToken(commonGoal).get());
            } else {
                return;
            }
        }
        if(this.model.getPersonalGoal(this.nickname).isPresent()) {
            this.sendMessage(new GameStartNotify(this.model.getBoard(), this.model.getCommonGoals(), commonGoalsTokens, this.model.getPersonalGoal(this.nickname).get(), this.model.getPlayers().stream().map(Player::getNickname).collect(Collectors.toList()), this.model.getCurrentPlayer().getNickname()));
        }
    }

    /**
     * Setter method
     * Sets an Event Listener
     * @param eventListener observable class
     */
    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    /**
     * Sends a server Message
     * @param serverMessage is the server Message sent.
     */
    public abstract void sendMessage(ServerMessage serverMessage);

    /**
     * Handles the specific message
     * @param clientMessage is a request of login
     */
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

    /**
     * Handles the specific message
     * @param clientMessage is a message for info abut the number of players.
     */
    public void handle(NumPlayersResponse clientMessage) {
        if(this.lobbyManager.isNumPlayersValid(clientMessage.getNumPlayers())) {
            this.numPlayers = clientMessage.getNumPlayers();
            this.lobbyManager.addPlayer(this);
        } else {
            sendMessage(new NumPlayersRequest());
        }
    }

    /**
     * Handles the specific message
     * @param clientMessage is a message for the choosing of a tile.
     */
    public void handle(PickTileRequest clientMessage) {
        if(!this.model.pickTile(clientMessage.getPosition()).equals(Tile.EMPTY)) {
            sendMessage(new PickTileResponse(true, this.model.getPickedTiles()));
        } else {
            sendMessage(new PickTileResponse(false, this.model.getPickedTiles()));
        }
    }

    /**
     * Handles the specific message
     * @param clientMessage is a message for the deselection of a tile.
     */
    public void handle(UnpickTileRequest clientMessage) {
        if(this.model.unpickTile(clientMessage.getPosition())) {
            sendMessage(new UnpickTileResponse(this.model.getPickedTiles(), true));
        } else {
            sendMessage(new UnpickTileResponse(this.model.getPickedTiles(), false));
        }
    }

    /**
     * Handles the specific message
     * @param clientMessage is a message for the confirmation of a selected tile.
     */
    public void handle(ConfirmPickRequest clientMessage) {
        this.eventListener.notifyListeners(new ConfirmPickResponse(this.model.confirmPick(), this.model.getBoard()));
    }

    /**
     * Handles the specific message
     * @param clientMessage is a message for the confirmation of a column.
     */
    public void handle(ConfirmColumnRequest clientMessage) {
        sendMessage(new ConfirmColumnResponse(this.model.confirmColumn(clientMessage.getColumnNumber()), this.model.checkNeedForSwap()));
    }

    /**
     * Handles the specific message
     * @param clientMessage is a message for the confirmation of the selected cards order.
     */
    public void handle(ConfirmOrderNotify clientMessage) {
        this.model.confirmOrderSelectedTiles(clientMessage.getPickedTiles());

        List<Integer> commonGoalsTokens = new ArrayList<>();
        for(CommonGoal commonGoal : this.model.getCommonGoals()) {
            if(this.model.getTopToken(commonGoal).isPresent()) {
                commonGoalsTokens.add(this.model.getTopToken(commonGoal).get());
            } else {
                return;
            }
        }
        if(this.model.getEndGame()) {
            if(this.model.getPlayerPoints(this.model.getCurrentPlayer().getNickname()).isPresent() && this.model.getBookshelf(this.model.getCurrentPlayer().getNickname()).isPresent()) {
                this.eventListener.notifyListeners(new EndTurnNotify(this.model.getBoard(), this.model.getBookshelf(this.model.getCurrentPlayer().getNickname()).get(), commonGoalsTokens, this.model.getPlayerPoints(this.model.getCurrentPlayer().getNickname()).get(), this.model.getIsLastTurn(), this.model.getCurrentPlayer().getNickname(), this.model.getCurrentPlayer().getNickname()));
                this.eventListener.notifyListeners(new GameResultNotify());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
                this.initClose();
            }
        } else {
            if(this.model.getPlayerPoints(this.model.getLastPlayer().getNickname()).isPresent() && this.model.getBookshelf(this.model.getLastPlayer().getNickname()).isPresent()) {
                this.eventListener.notifyListeners(new EndTurnNotify(this.model.getBoard(), this.model.getBookshelf(this.model.getLastPlayer().getNickname()).get(), commonGoalsTokens, this.model.getPlayerPoints(this.model.getLastPlayer().getNickname()).get(), this.model.getIsLastTurn(), this.model.getLastPlayer().getNickname(), this.model.getCurrentPlayer().getNickname()));
            }
        }
    }

    /**
     * Handles the specific message
     * @param clientMessage is a chat message.
     */
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

    /**
     * Handles the specific message
     * @param clientMessage is a Pong message
     */
    public void handle(PongMessage clientMessage) {
        this.pingPongHandler.notifyReceivedMessage();
    }

    /**
     * Sends a server message to notify something
     * @param serverMessage is a generic server Message
     */
    public void notify(ServerMessage serverMessage) {
        sendMessage(serverMessage);
    }

    /**
     * Closes the connection of the client.
     */
    public void close() {
        this.stopConnection = true;
        this.pingPongHandler.stopPing();
    }

    /**
     * Initializes the client disconnection.
     */
    public void initClose() {
        System.out.println("Closing connection with " + this.nickname + "...");
        this.close();
        try {
            this.lobbyManager.removePlayer(this);
            this.lobbyManager.endGame(this.model);
            this.eventListener.removeListener(this);
            if(this.nickname != null) {
                this.eventListener.notifyListeners(new PlayerDisconnectedNotify(this.nickname));
            }
        } catch(Exception ignored) {
        }
    }

}