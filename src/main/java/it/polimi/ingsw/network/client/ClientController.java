package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.*;
import it.polimi.ingsw.view.View;

/**
 * Client's Controller class
 */
public class ClientController {
    private final Client client;
    private final View view;

    /**
     * Class Constructor
     * @param client that will be controlled by the ClientController
     * @param view of the ClientController
     */
    public ClientController(Client client, View view) {
        this.client = client;
        this.view = view;
        this.view.setClientController(this);
        this.client.setController(this);
    }

    /**
     * Getter method
     * @return client
     */
    public Client getClient() {
        return this.client;
    }

    /**
     * Initializes the Controller
     */
    public void initController() {
        this.view.chooseNickname();
        this.client.start();
    }

    /**
     * Sends a client message
     * @param clientMessage is the message that will be sent
     */
    public void sendMessage(ClientMessage clientMessage) {
        this.client.sendMessage(clientMessage);
    }

    /**
     * Handles the specific message
     * @param message is a Ping Message
     */
    public void handle(PingMessage message) {
        this.client.sendMessage(new PongMessage());
        //System.out.println("Ping received");
    }

    /**
     * Handles the specific message
     * @param serverMessage is a login response message
     */
    public void handle(LoginResponse serverMessage) {
        if(serverMessage.getNickname() == null) {
            this.view.chooseNickname();
        } else {
            this.client.setNickname(serverMessage.getNickname());
        }
    }

    /**
     * Handles the specific message
     * @param serverMessage contains the number of players chosen for a specified game.
     */
    public void handle(NumPlayersRequest serverMessage) {
        this.view.chooseNumPlayers();
    }

    /**
     * Handles the specific message
     * @param serverMessage is a message that notifies the beginning of a new game.
     */
    public void handle(GameStartNotify serverMessage) {
        this.view.setPlayers(serverMessage.getPlayers());
        this.view.startGame(serverMessage.getBoard(), serverMessage.getCommonGoals(), serverMessage.getPersonalGoal(), serverMessage.getNextPlayer());
        this.view.startTurn(serverMessage.getNextPlayer());
    }

    /**
     * Handles the specific message
     * @param serverMessage  is a message sent after the selection of a card.
     */
    public void handle(PickTileResponse serverMessage) {
        if(serverMessage.getValid()) {
            this.view.showValidPick();
            this.view.updatePickedTiles(serverMessage.getPickedTiles());
        } else {
            this.view.showInvalidPick();
        }
        this.view.showPickATile();
    }

    /**
     * Handles the specific message
     * @param serverMessage is a message sent after unpicking a card.
     */
    public void handle(UnpickTileResponse serverMessage) {
        if(serverMessage.isSuccessful()) {
            this.view.showValidUnpick();
        } else {
            this.view.showInvalidUnpick();
        }
        this.view.showPickATile();
    }

    /**
     * Handles the specific message
     * @param serverMessage is a message sent after confirming the selection of a card.
     */
    public void handle(ConfirmPickResponse serverMessage) {
        if(serverMessage.isValid()) {
            this.view.showChooseColumn();
        } else {
            this.view.showInvalidPick();
            this.view.showPickATile();
        }
    }

    /**
     * Handles the specific message
     * @param serverMessage  is a message sent after the confirmation of a column.
     */
    public void handle(ConfirmColumnResponse serverMessage) {
        if(serverMessage.getValid()) {
            this.view.showValidColumn();
            this.view.showSwapTilesOrder();
        } else {
            this.view.showInvalidColumn();
            this.view.showChooseColumn();
        }
    }

    /**
     * Handles the specific message
     * @param serverMessage  is a message sent to notify the status of the tiles previously swapped.
     */
    public void handle(SwapTilesOrderResponse serverMessage) {
        if(serverMessage.getSuccessful()) {
            this.view.showValidSwap();
        } else {
            this.view.showInvalidSwap();
        }
        this.view.updatePickedTiles(serverMessage.getPickedCards());
        this.view.showSwapTilesOrder();
    }

    /**
     * Handles the specific message
     * @param serverMessage is a message sent to notify the end of a turn
     */
    public void handle(EndTurnNotify serverMessage) {
        this.view.updateBoard(serverMessage.getBoard());
        this.view.updateBookshelf(serverMessage.getPlayer(), serverMessage.getBookshelf());
        this.view.updatePoints(serverMessage.getPlayer(), serverMessage.getPoints());
        this.view.endTurn();
        this.view.startTurn(serverMessage.getNextPlayer());
    }

    /**
     * Handles the specific message
     * @param serverMessage is a message sent in order to display game results
     */
    public void handle(GameResultNotify serverMessage) {
        //handle gameResultNotify
    }

    /**
     * Handles the specific message
     * @param serverMessage is a message sent in the chat
     */
    public void handle(ChatMessage serverMessage) {
        if(!this.client.getNickname().equals(serverMessage.getPlayer())) {
            if(this.view.getPlayers().contains(serverMessage.getReceiver())) {
                this.view.showNewChatMessageUnicast(serverMessage.getPlayer(), serverMessage.getTextMessage());
            } else {
                this.view.showNewChatMessageBroadcast(serverMessage.getPlayer(), serverMessage.getTextMessage());
            }
        }
    }

    /**
     * Handles the specific message
     * @param serverMessage is a message sent to notify the disconnection of a player
     */
    public void handle(PlayerDisconnectedNotify serverMessage) {
        this.view.showPlayerDisconnected(serverMessage.getDisconnectedPlayer());
        this.client.close();
    }
}

