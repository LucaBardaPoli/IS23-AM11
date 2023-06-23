package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Board;
import it.polimi.ingsw.model.CommonGoal;
import it.polimi.ingsw.model.PersonalGoal;
import it.polimi.ingsw.network.client.ClientController;

import java.util.List;

public class GameStartNotify implements ServerMessage {
    private final Board board;
    private final List<CommonGoal> commonGoals;
    private final List<Integer> commonGoalsTokens;
    private final PersonalGoal personalGoal;
    private final List<String> players;
    private final String nextPlayer;

    /**
     * Class constructor
     * @param board board of the game
     * @param commonGoals common goals of the game
     * @param commonGoalsTokens common goals tokens of the game
     * @param personalGoal personal goal of the game
     * @param players players of the game
     * @param nextPlayer player who's now playing
     */
    public GameStartNotify(Board board, List<CommonGoal> commonGoals, List<Integer> commonGoalsTokens, PersonalGoal personalGoal, List<String> players, String nextPlayer) {
        this.board = board;
        this.commonGoals = commonGoals;
        this.commonGoalsTokens = commonGoalsTokens;
        this.personalGoal = personalGoal;
        this.players = players;
        this.nextPlayer = nextPlayer;
    }

    /**
     * Board getter
     * @return board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Common goals getter
     * @return common goals
     */
    public List<CommonGoal> getCommonGoals() {
        return commonGoals;
    }

    /**
     * Common goals tokens getter
     * @return common goals tokens
     */
    public List<Integer> getCommonGoalsTokens() {
        return commonGoalsTokens;
    }

    /**
     * Personal goal getter
     * @return personal goal
     */
    public PersonalGoal getPersonalGoal() {
        return personalGoal;
    }

    /**
     * Players getter
     * @return players
     */
    public List<String> getPlayers() {
        return players;
    }

    /**
     * Next player getter
     * @return next player
     */
    public String getNextPlayer() {
        return nextPlayer;
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
