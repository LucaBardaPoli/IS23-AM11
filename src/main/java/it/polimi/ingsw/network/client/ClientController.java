package it.polimi.ingsw.network.client;

import it.polimi.ingsw.network.message.*;

public class ClientController {
    private final Client client;
    // private View view;

    public ClientController(Client client) {
        this.client = client;
        this.client.setController(this);
    }

    public void run() {
        // testing
        this.client.sendMessage(new LoginRequest("simone"));

        client.startListening();
        // view.chooseUsername();
    }

    // Handles all kind of Server messages
    public void handle(LoginResponse serverMessage) {
        //handle pickTaleResponse
    }

    public void handle(PickTileResponse serverMessage) {
        //handle pickTaleResponse
    }

    public void handle(NumPlayersRequest serverMessage) {
        //handle numPlayersResponse
    }

    public void handle(GameStartNotify serverMessage) {
        //handle gameStartNotify
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

}

