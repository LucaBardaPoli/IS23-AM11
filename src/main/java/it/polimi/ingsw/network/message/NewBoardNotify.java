package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.network.client.ClientController;

import java.util.List;

public class NewBoardNotify implements ServerMessage {
    private final Board board;
    private final List<Tile> pickedTiles;
    private final String player;

    public NewBoardNotify(Board board, List<Tile> pickedTiles, String player) {
        this.board = board;
        this.pickedTiles = pickedTiles;
        this.player = player;
    }

    public Board getBoard() {
        return board;
    }

    public List<Tile> getPickedTiles() {
        return pickedTiles;
    }

    public String getPlayer() {
        return player;
    }

    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
