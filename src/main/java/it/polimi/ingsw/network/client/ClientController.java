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

    // Handles all kind of Server messages
    public void handle(LoginResponse serverMessage) {
        System.out.println("LoginResponse -> " + serverMessage.getValid());
    }

    public void handle(NumPlayersRequest serverMessage) {
        this.view.chooseNumPlayers();
    }

    public void handle(GameStartNotify serverMessage) {
        //handle gameStartNotify
    }

    public void handle(PickTileResponse serverMessage) {
        //handle pickTaleResponse
    }

    public void handle(RemoveTileResponse serverMessage) {
        //handle removeTileResponse
    }

    public void handle(NewBoardNotify serverMessage) {
        //handle newBoardNotify
    }

    public void handle(ConfirmColumnResponse serverMessage) {
        //handle confirmColumnResponse
    }

    public void handle(SwapTilesOrderResponse serverMessage) {
        //handle swapTilesOrderResponse
    }

    public void handle(EndTurnNotify serverMessage) {
        //handle endTurnNotify
    }

    public void handle(GameResultNotify serverMessage){
        //handle gameResultNotify
    }

    public void handle(ChatMessage serverMessage) {
        if(!this.client.getNickname().equals(serverMessage.getPlayer())) {
            //show on chat box
        }
    }
}

