package it.polimi.ingsw.network.message;

import it.polimi.ingsw.model.Player;

import java.sql.Timestamp;

public class PlayerMessage {
    private final Player player;
    private final String message;
    private final Timestamp timestamp;

    public PlayerMessage(Player player, String message, Timestamp timestamp) {
        this.player = player;
        this.message = message;
        this.timestamp = timestamp;
    }

    public Player getPlayer() {
        return player;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
