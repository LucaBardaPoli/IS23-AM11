package it.polimi.ingsw.network.client;

import it.polimi.ingsw.model.Game;
import it.polimi.ingsw.network.message.*;

import java.rmi.RemoteException;

public class ClientController {
    private Client client;
    // private View view;

    public ClientController(Client client) {
        this.client = client;
        this.client.setController(this);
    }

    public void run() {
        // Test message
        try {
            client.sendMessage(new LoginRequest("simone"));
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        client.startListening();
        // view.chooseUsername();
    }

    // Handles all kind of Server messages
    public void handle(LoginResponse message) {
        System.out.println("OK");
    }

    public void handle(PickTaleResponse pickTaleResponse) {
        //handle pickTaleResponse
    }

    public void handle(NumPlayersRequest numPlayersRequest) {
        //handle numPlayersResponse
    }

    public void handle(GameStartNotify gameStartNotify) {
        //handle gameStartNotify
    }

    public void handle(NewBoardNotify newBoardNotify) {
        //handle newBoardNotify
    }

    public void handle(ConfirmColumnResponse confirmColumnResponse) {
        //handle confirmColumnResponse
    }

    public void handle(SwapTilesOrderResponse swapTilesOrderResponse) {
        //handle swapTilesOrderResponse
    }

    public void handle(EndTurnNotify endTurnNotify) {
        //handle endTurnNotify
    }

    public void handle(GameResultNotify gameResultNotify){
        //handle gameResultNotify
    }

}

