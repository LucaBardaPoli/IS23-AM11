package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.network.client.ClientController;

import java.util.List;

public class UnpickTileResponse implements ServerMessage {
    private final List<Tile> pickedTiles;
    private final boolean successful;

    /**
     * Class constructor
     * @param pickedTiles new picked tiles
     * @param successful result of the action
     */
    public UnpickTileResponse(List<Tile> pickedTiles, boolean successful) {
        this.pickedTiles = pickedTiles;
        this.successful = successful;
    }

    /**
     * New picked tiles getter
     * @return picked tiles
     */
    public List<Tile> getPickedTiles() {
        return this.pickedTiles;
    }

    /**
     * Result getter
     * @return result
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * Handles the server message
     * @param clientController that handles the client server-side
     */
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
