package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Position;
import it.polimi.ingsw.network.server.ClientHandler;

public class PickTileRequest implements ClientMessage {
    private final Position position;

    /**
     * Class constructor
     * @param position of the picked tile
     */
    public PickTileRequest(Position position) {
        this.position = position;
    }

    /**
     * Position getter
     * @return position
     */
    public Position getPosition() {
        return position;
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
