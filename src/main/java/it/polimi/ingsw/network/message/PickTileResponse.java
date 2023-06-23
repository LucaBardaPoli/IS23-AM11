package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.network.client.ClientController;

import java.util.List;

public class PickTileResponse implements ServerMessage {
    private final boolean valid;
    private final List<Tile> pickedTiles;

    /**
     * Class constructor
     * @param response result of the action
     * @param pickedTiles picked tiles from the player
     */
    public PickTileResponse(boolean response, List<Tile> pickedTiles) {
        this.valid = response;
        this.pickedTiles = pickedTiles;
    }

    /**
     * Result getter
     * @return result
     */
    public boolean getValid() {
        return valid;
    }

    /**
     * Picked tiles getter
     * @return picked tiles
     */
    public List<Tile> getPickedTiles() {
        return this.pickedTiles;
    }

    /**
     * Handles the server message
     * @param clientController that handles the client server-side
     */
    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
