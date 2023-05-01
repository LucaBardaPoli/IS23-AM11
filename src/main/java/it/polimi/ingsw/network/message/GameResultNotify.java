package it.polimi.ingsw.network.message;

import it.polimi.ingsw.network.client.ClientController;

import java.util.Map;

public class GameResultNotify implements ServerMessage {
    private final Map<String, Integer> points;
    private final String winner;

    public GameResultNotify(Map<String, Integer> points, String winner) {
        this.points = points;
        this.winner = winner;
    }

    public Map<String, Integer> getPoints() {
        return points;
    }

    public String getWinner() {
        return winner;
    }

    @Override
    public void handle(ClientController clientController) {
        clientController.handle(this);
    }
}
