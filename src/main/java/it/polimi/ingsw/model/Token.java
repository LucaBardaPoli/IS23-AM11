package it.polimi.ingsw.model;

import java.io.Serializable;

public class Token implements Serializable {
    private final int value;

    public Token(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
