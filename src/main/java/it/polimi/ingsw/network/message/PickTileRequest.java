package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Position;
import it.polimi.ingsw.network.server.ClientHandler;

public class PickTileRequest implements ClientMessage {
    private final Position position;

    public PickTileRequest(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public void handle(ClientHandler clientHandler) {
        clientHandler.handle(this);
    }
}
