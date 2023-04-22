package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.network.client.ClientController;

import java.util.List;

public class RemoveTileResponse implements ServerMessage {
    private final List<Tile> pickedTiles;

    public RemoveTileResponse(List<Tile> pickedTiles) {
        this.pickedTiles = pickedTiles;
    }

    public List<Tile> getPickedTiles() {
        return this.pickedTiles;
    }

    public void handle(ClientController clientController) {
        clientController.handle(this);
    }

}
