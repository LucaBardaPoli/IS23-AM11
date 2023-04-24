package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.network.client.ClientController;

public class NewBoardNotify implements ServerMessage {
    private final Board board;

    public NewBoardNotify(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
