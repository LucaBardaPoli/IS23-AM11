package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Position;
import it.polimi.ingsw.network.server.ClientHandler;

public class UnpickTileRequest implements ClientMessage {
    private final Position position;

    public UnpickTileRequest(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return this.position;
    }

    public void handle(ClientHandler clientHandler) {
        clientHandler.handle(this);
    }
}
