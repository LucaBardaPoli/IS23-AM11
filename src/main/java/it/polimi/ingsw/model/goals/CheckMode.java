package it.polimi.ingsw.model.goals;

import java.io.Serializable;

/**
 * Class used when evaluating common goals
 */
public enum CheckMode implements Serializable {
    /**
     * Refers to a group of tiles placed vertically
     */
    VERTICAL,

    /**
     * Refers to a group of tiles placed horizontally
     */
    HORIZONTAL,
}
