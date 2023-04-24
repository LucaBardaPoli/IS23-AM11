package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.CommonGoal;
import it.polimi.ingsw.model.PersonalGoal;
import it.polimi.ingsw.network.client.ClientController;

import java.util.List;

public class GameStartNotify implements ServerMessage{
    private final Board board;
    private final List<CommonGoal> commonGoals;
    private final PersonalGoal personalGoal;
    private final boolean isYourTurn;

    public GameStartNotify(Board board, List<CommonGoal> commonGoals, PersonalGoal personalGoal, boolean isYourTurn) {
        this.board = board;
        this.commonGoals = commonGoals;
        this.personalGoal = personalGoal;
        this.isYourTurn = isYourTurn;
    }

    public Board getBoard() {
        return board;
    }

    public List<CommonGoal> getCommonGoals() {
        return commonGoals;
    }

    public PersonalGoal getPersonalGoal() {
        return personalGoal;
    }

    public boolean getIsYourTurn() {
        return isYourTurn;
    }

    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
