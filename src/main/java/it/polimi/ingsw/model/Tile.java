package it.polimi.ingsw.model;

import java.io.Serializable;

/**
 * Class that represents all possible tile types
 */
public enum Tile implements Serializable {
    BLUE,
    GREEN,
    LBLUE,
    PINK,
    WHITE,
    YELLOW,
    EMPTY;
    public static final int numColors = (Tile.values().length - 1);
}
