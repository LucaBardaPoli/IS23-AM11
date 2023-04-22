package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.server.ClientHandler;

public class ConfirmColumnRequest implements ClientMessage {
    private final int columnNumber;

    public ConfirmColumnRequest(int column) {
        this.columnNumber = column;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    @Override
    public void handle(ClientHandler clientHandler) {
        clientHandler.handle(this);
    }
}
