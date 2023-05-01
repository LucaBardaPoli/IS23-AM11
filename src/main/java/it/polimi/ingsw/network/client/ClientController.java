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

    public void run() {
        this.client.startListening();
        this.view.chooseUsername();
    }

    public void sendMessage(ClientMessage clientMessage) {
        this.client.sendMessage(clientMessage);
    }

    public void handle(PingMessage message) {
        this.client.sendMessage(new PongMessage());
        System.out.println("Ping received");
    }

    // Handles all kind of Server messages
    public void handle(LoginResponse serverMessage) {
        if(serverMessage.getNickname() == null) {
            this.view.chooseUsername();
        } else {
            this.client.setNickname(serverMessage.getNickname());
        }
    }

    public void handle(NumPlayersRequest serverMessage) {
        //new Thread(this.view::chooseNumPlayers).start();
        this.view.chooseNumPlayers();
    }

    public void handle(GameStartNotify serverMessage) {
        this.view.setPlayers(serverMessage.getPlayers());
        this.view.startGame(serverMessage.getBoard(), serverMessage.getCommonGoals(), serverMessage.getPersonalGoal(), serverMessage.getNextPlayer());
    }

    public void handle(PickTileResponse serverMessage) {
        if(serverMessage.getValid()) {
            this.view.showValidPick();
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

    public void handle(NewBoardNotify serverMessage) {
        this.view.updateBoard(serverMessage.getBoard());
        this.view.updatePickedTiles(serverMessage.getPickedTiles());
        this.view.showBookshelf(serverMessage.getPlayer());
        this.view.showChooseColumn();
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
        //handle endTurnNotify
        this.view.endTurn();
        this.view.updateBookshelf(serverMessage.getPlayer(), serverMessage.getBookshelf());
        this.view.updatePoints(serverMessage.getPlayer(), serverMessage.getPoints());
        this.view.startTurn(serverMessage.getNextPlayer());
    }

    public void handle(GameResultNotify serverMessage) {
        //handle gameResultNotify
    }

    public void handle(ChatMessage serverMessage) {
        if(!this.client.getNickname().equals(serverMessage.getPlayer())) {
            //show on chat box
        }
    }
}

