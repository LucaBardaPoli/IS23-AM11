package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.server.ClientHandler;

import java.rmi.RemoteException;

public class ConfirmColumnRequest implements ClientMessage {
    private final int columnNumber;

    public ConfirmColumnRequest(int column) {
        this.columnNumber = column;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    @Override
    public void handle(ClientHandler clientHandler) throws RemoteException {
        clientHandler.handle(this);
    }
}
