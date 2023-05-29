package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.network.server.ClientHandler;

import java.util.List;

public class ConfirmOrderNotify implements ClientMessage {
    private final List<Tile> pickedTiles;

    public ConfirmOrderNotify(List<Tile> pickedTiles) {
        this.pickedTiles = pickedTiles;
    }

    public List<Tile> getPickedTiles() {
        return pickedTiles;
    }

    @Override
    public void handle(ClientHandler clientHandler) {
        clientHandler.handle(this);
    }
}
