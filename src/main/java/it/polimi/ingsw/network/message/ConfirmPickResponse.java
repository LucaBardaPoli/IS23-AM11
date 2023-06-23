package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.network.client.ClientController;

public class ConfirmPickResponse implements ServerMessage {
    private final boolean valid;
    private final Board board;

    /**
     * Class constructor
     * @param valid result of the action
     * @param board new board
     */
    public ConfirmPickResponse(boolean valid, Board board) {
        this.valid = valid;
        this.board = board;
    }

    /**
     * Result getter
     * @return result
     */
    public boolean isValid() {
        return valid;
    }

    /**
     * Board getter
     * @return board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Handles the server message
     * @param clientController that handles the client server-side
     */
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
