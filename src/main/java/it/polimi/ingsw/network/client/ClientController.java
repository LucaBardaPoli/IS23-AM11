package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.*;
import it.polimi.ingsw.view.View;

public class ClientController {
    private final Client client;
    private final View view;

    public ClientController(Client client, View view) {
        this.client = client;
        this.view = view;
        this.view.setClientController(this);
        this.client.setController(this);
    }

    public Client getClient() {
        return this.client;
    }

    public void initController() {
        this.view.chooseNickname();
        this.client.start();
    }

    public void sendMessage(ClientMessage clientMessage) {
        this.client.sendMessage(clientMessage);
    }

    public void handle(PingMessage message) {
        this.client.sendMessage(new PongMessage());
        //System.out.println("Ping received");
    }

    // Handles all kind of Server messages
    public void handle(LoginResponse serverMessage) {
        if(serverMessage.getNickname() == null) {
            this.view.chooseNickname();
        } else {
            this.client.setNickname(serverMessage.getNickname());
        }
    }

    public void handle(NumPlayersRequest serverMessage) {
        this.view.chooseNumPlayers();
    }

    public void handle(GameStartNotify serverMessage) {
        this.view.setPlayers(serverMessage.getPlayers());
        this.view.startGame(serverMessage.getBoard(), serverMessage.getCommonGoals(), serverMessage.getPersonalGoal(), serverMessage.getNextPlayer());
        this.view.startTurn(serverMessage.getNextPlayer());
    }

    public void handle(PickTileResponse serverMessage) {
        if(serverMessage.getValid()) {
            this.view.showValidPick();
            this.view.updatePickedTiles(serverMessage.getPickedTiles());
        } else {
            this.view.showInvalidPick();
        }
        this.view.showPickATile();
    }

    public void handle(UnpickTileResponse serverMessage) {
        if(serverMessage.isSuccessful()) {
            this.view.showValidUnpick();
        } else {
            this.view.showInvalidUnpick();
        }
        this.view.showPickATile();
    }

    public void handle(ConfirmPickResponse serverMessage) {
        if(serverMessage.isValid()) {
            this.view.showChooseColumn();
        } else {
            this.view.showInvalidPick();
            this.view.showPickATile();
        }
    }

    public void handle(ConfirmColumnResponse serverMessage) {
        if(serverMessage.getValid()) {
            this.view.showValidColumn();
            this.view.showSwapTilesOrder();
        } else {
            this.view.showInvalidColumn();
            this.view.showChooseColumn();
        }
    }

    public void handle(SwapTilesOrderResponse serverMessage) {
        if(serverMessage.getSuccessful()) {
            this.view.showValidSwap();
        } else {
            this.view.showInvalidSwap();
        }
        this.view.updatePickedTiles(serverMessage.getPickedCards());
        this.view.showSwapTilesOrder();
    }

    public void handle(EndTurnNotify serverMessage) {
        this.view.updateBoard(serverMessage.getBoard());
        this.view.updateBookshelf(serverMessage.getPlayer(), serverMessage.getBookshelf());
        this.view.updatePoints(serverMessage.getPlayer(), serverMessage.getPoints());
        this.view.endTurn();
        this.view.startTurn(serverMessage.getNextPlayer());
    }

    public void handle(GameResultNotify serverMessage) {
        //handle gameResultNotify
    }

    public void handle(ChatMessage serverMessage) {
        if(!this.client.getNickname().equals(serverMessage.getPlayer())) {
            if(this.view.getPlayers().contains(serverMessage.getReceiver())) {
                this.view.showNewChatMessageUnicast(serverMessage.getPlayer(), serverMessage.getTextMessage());
            } else {
                this.view.showNewChatMessageBroadcast(serverMessage.getPlayer(), serverMessage.getTextMessage());
            }
        }
    }

    public void handle(PlayerDisconnectedNotify serverMessage) {
        this.view.showPlayerDisconnected(serverMessage.getDisconnectedPlayer());
        this.client.close();
    }
}

