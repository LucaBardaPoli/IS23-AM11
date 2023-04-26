package it.polimi.ingsw.network.client;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.goals.LadderGoal;
import it.polimi.ingsw.model.goals.SameKindXGoal;
import it.polimi.ingsw.network.message.*;
import it.polimi.ingsw.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (serverMessage.getNickname() == null) {
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
        // For testing since Board, CommonGoals and PersonalGoals are not Serializable
        Board b = new Board(2, new Bag());
        b.fillBoard();

        List<Position> positions = new ArrayList<>();
        List<Tile> tiles = new ArrayList<>();
        Map<Integer, Integer> rewards = new HashMap<>();

        rewards.put(1, 1);
        rewards.put(2, 2);
        rewards.put(3, 4);
        rewards.put(4, 6);
        rewards.put(5, 9);
        rewards.put(6, 12);

        //Personal Goal x1
        positions.add(new Position(0, 0));
        tiles.add(Tile.PINK);

        positions.add(new Position(0, 2));
        tiles.add(Tile.BLUE);

        positions.add(new Position(1, 4));
        tiles.add(Tile.GREEN);

        positions.add(new Position(2, 3));
        tiles.add(Tile.WHITE);

        positions.add(new Position(3, 1));
        tiles.add(Tile.YELLOW);

        positions.add(new Position(5, 2));
        tiles.add(Tile.LBLUE);

        this.view.startGame(b, new ArrayList<>(List.of(new CommonGoal("Primo", new LadderGoal()), new CommonGoal("Secondo", new SameKindXGoal()))), new PersonalGoal(positions, tiles, rewards), serverMessage.getIsYourTurn());
        //this.view.startGame(serverMessage.getBoard(), serverMessage.getCommonGoals(), serverMessage.getPersonalGoal(), serverMessage.getIsYourTurn());
    }

    public void handle(PickTileResponse serverMessage) {
        if(serverMessage.getValid()) {
            this.view.showValidPick();
        } else {
            this.view.showInvalidPick();
        }
        this.view.showPickATile();
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

    public void handle(GameResultNotify serverMessage) {
        //handle gameResultNotify
    }

    public void handle(ChatMessage serverMessage) {
        if(!this.client.getNickname().equals(serverMessage.getPlayer())) {
            //show on chat box
        }
    }
}

