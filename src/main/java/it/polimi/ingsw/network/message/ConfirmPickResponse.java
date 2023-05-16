package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.network.client.ClientController;

public class ConfirmPickResponse implements ServerMessage {

    private final boolean valid;
    private final Board board;

    public ConfirmPickResponse(boolean valid, Board board) {
        this.valid = valid;
        this.board = board;
    }

    public boolean isValid() {
        return valid;
    }

    public Board getBoard() {
        return board;
    }

    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
