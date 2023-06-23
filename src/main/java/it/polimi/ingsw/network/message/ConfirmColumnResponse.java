package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

public class ConfirmColumnResponse implements ServerMessage {
    private final boolean valid;
    private final boolean needForSwap;

    /**
     * Class constructor
     * @param valid result of the action
     * @param needForSwap true whether it could make sense to swap the picked tiles
     */
    public ConfirmColumnResponse(boolean valid, boolean needForSwap) {
        this.valid = valid;
        this.needForSwap = needForSwap;
    }

    /**
     * Result getter
     * @return result
     */
    public boolean getValid() {
        return valid;
    }

    /**
     * Need for swap getter
     * @return whether the swap is needed
     */
    public boolean getNeedForSwap() {
        return this.needForSwap;
    }

    /**
     * Handles the server message
     * @param clientController that handles the client server-side
     */
    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
