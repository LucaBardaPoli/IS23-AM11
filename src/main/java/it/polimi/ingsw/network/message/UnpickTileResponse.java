package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.network.client.ClientController;

import java.util.List;

public class UnpickTileResponse implements ServerMessage {
    private final List<Tile> pickedTiles;
    private final boolean successful;

    public UnpickTileResponse(List<Tile> pickedTiles, boolean successful) {
        this.pickedTiles = pickedTiles;
        this.successful = successful;
    }

    public List<Tile> getPickedTiles() {
        return this.pickedTiles;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void handle(ClientController clientController) {
        clientController.handle(this);
    }

}
