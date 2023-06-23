package it.polimi.ingsw.model;

import java.io.Serializable;

/**
 * Class that represents a common goal token
 */
public class Token implements Serializable {
    private final int value;

    /**
     * Class constructor
     * @param value token value
     */
    public Token(Integer value) {
        this.value = value;
    }

    /**
     * Token value getter
     * @return token value
     */
    public Integer getValue() {
        return value;
    }
}
