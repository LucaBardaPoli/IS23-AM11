package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.CommonGoal;
import it.polimi.ingsw.model.PersonalGoal;
import it.polimi.ingsw.network.client.ClientController;

import java.util.List;
import java.util.Map;

public class GameStartNotify implements ServerMessage {
    private final Board board;
    private final Map<CommonGoal, Integer> commonGoals;
    private final PersonalGoal personalGoal;
    private final List<String> players;
    private final String nextPlayer;

    public GameStartNotify(Board board, Map<CommonGoal, Integer> commonGoals, PersonalGoal personalGoal, List<String> players, String nextPlayer) {
        this.board = board;
        this.commonGoals = commonGoals;
        this.personalGoal = personalGoal;
        this.players = players;
        this.nextPlayer = nextPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public Map<CommonGoal, Integer> getCommonGoals() {
        return commonGoals;
    }

    public PersonalGoal getPersonalGoal() {
        return personalGoal;
    }

    public List<String> getPlayers() {
        return players;
    }

    public String getNextPlayer() {
        return nextPlayer;
    }

    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
