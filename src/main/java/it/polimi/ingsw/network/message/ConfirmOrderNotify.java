package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.network.server.ClientHandler;

import java.util.List;

public class ConfirmOrderNotify implements ClientMessage {
    private final List<Tile> pickedTiles;

    /**
     * Class constructor
     * @param pickedTiles picked tiles from the current player
     */
    public ConfirmOrderNotify(List<Tile> pickedTiles) {
        this.pickedTiles = pickedTiles;
    }

    /**
     * Picked tiles getter
     * @return picked tiles
     */
    public List<Tile> getPickedTiles() {
        return pickedTiles;
    }

    /**
     * Handles the Client message
     * @param clientHandler that handles the client client-side
     */
    @Override
    public void handle(ClientHandler clientHandler) {
        clientHandler.handle(this);
    }
}
