package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.CommonGoal;
import it.polimi.ingsw.model.PersonalGoal;
import it.polimi.ingsw.network.client.ClientController;

import java.util.List;

public interface View {
    void setClientController(ClientController clientController);
    void chooseTypeOfConnection();
    void chooseUsername();
    void chooseNumPlayers();
    void startGame(Board board, List<CommonGoal> commonGoals, PersonalGoal personalGoal, boolean isYourTurn);
    void showPickATile();
    void showValidPick();
    void showInvalidPick();
}
