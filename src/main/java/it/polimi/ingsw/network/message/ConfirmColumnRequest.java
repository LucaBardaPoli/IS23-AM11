package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.server.ClientHandler;

import java.rmi.RemoteException;

public class ConfirmColumnRequest implements ClientMessage {
    private Integer columnNumber;

    public ConfirmColumnRequest(Integer column) {
        this.columnNumber = column;
    }

    public Integer getColumnNumber() {
        return columnNumber;
    }

    @Override
    public void handle(ClientHandler clientHandler) throws RemoteException {
        clientHandler.handle(this);
    }
}
