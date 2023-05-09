package it.polimi.ingsw.model;

import java.io.Serializable;

public enum GameStatus implements Serializable {
    PICK_CARDS,
    SELECT_COLUMN,
    SELECT_ORDER,
    UPDATE_POINTS
}
