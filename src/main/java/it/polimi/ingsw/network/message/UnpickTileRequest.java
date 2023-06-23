package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Position;
import it.polimi.ingsw.network.server.ClientHandler;

public class UnpickTileRequest implements ClientMessage {
    private final Position position;

    /**
     * Class constructor
     * @param position of the tile to unpick
     */
    public UnpickTileRequest(Position position) {
        this.position = position;
    }

    /**
     * Tile position getter
     * @return tile position
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * Handles the Client message
     * @param clientHandler that handles the client client-side
     */
    public void handle(ClientHandler clientHandler) {
        clientHandler.handle(this);
    }
}
