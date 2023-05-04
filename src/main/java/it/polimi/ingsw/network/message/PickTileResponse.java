package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.network.client.ClientController;

import java.util.List;

public class PickTileResponse implements ServerMessage {
    private final boolean valid;
    private final List<Tile> pickedTiles;

    public PickTileResponse(boolean response, List<Tile> pickedTiles) {
        this.valid = response;
        this.pickedTiles = pickedTiles;
    }

    public boolean getValid() {
        return valid;
    }

    public List<Tile> getPickedTiles() {
        return this.pickedTiles;
    }

    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
