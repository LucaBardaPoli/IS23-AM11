package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.server.ClientHandler;

public class SwapTilesOrderRequest implements ClientMessage {
    private final int index;

    public SwapTilesOrderRequest(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public void handle(ClientHandler clientHandler) {
        clientHandler.handle(this);
    }
}
