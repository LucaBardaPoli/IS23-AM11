package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Position;
import it.polimi.ingsw.network.server.ClientHandler;

import java.rmi.RemoteException;

public class PickTaleRequest implements ClientMessage {
    Position position;

    public PickTaleRequest(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public void handle(ClientHandler clientHandler) throws RemoteException {
        clientHandler.handle(this);
    }
}
