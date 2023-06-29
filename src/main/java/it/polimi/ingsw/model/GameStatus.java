package it.polimi.ingsw.model;

import java.io.Serializable;

/**
 * Class that keeps track of the game status
 */
public enum GameStatus implements Serializable {
    /**
     * The current player is choosing the tiles to pick from the board
     */
    PICK_CARDS,

    /**
     * The current player is choosing the column where to insert the picked tiles
     */
    SELECT_COLUMN,

    /**
     * The current player is choosing whether to change the order of the picked tiles or not
     */
    SELECT_ORDER,

    /**
     * The current player's turn is over
     */
    UPDATE_POINTS
}
