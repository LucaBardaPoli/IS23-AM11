package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.CommonGoal;
import it.polimi.ingsw.model.PersonalGoal;
import it.polimi.ingsw.network.client.ClientController;

import java.util.List;

public class GUIView implements View {
    private ClientController clientController;

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }

    public void showUpdatedBoard(Board board) {
    }

    public void chooseTypeOfConnection() {
    }

    public void chooseUsername() {
    }

    public void chooseNumPlayers() {
    }

    public void startGame(Board board, List<CommonGoal> commonGoals, PersonalGoal personalGoal, String nextPlayer) {
    }

    public void showPickATile() {
    }

    public void showValidPick() {
    }
    public void showInvalidPick() {
    }
}
