package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.server.ClientHandler;

public class ConfirmColumnRequest implements ClientMessage {
    private final int columnNumber;

    /**
     * Class constructor
     * @param column selected column from the current player
     */
    public ConfirmColumnRequest(int column) {
        this.columnNumber = column;
    }

    /**
     * Selected column getter
     * @return selected column
     */
    public int getColumnNumber() {
        return columnNumber;
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
