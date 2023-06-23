package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.function.Predicate;

/**
 * Class that represents a specific common goal of the game
 */
public class CommonGoal implements Serializable {
    private final int id;
    private final String name;
    private final Predicate<Bookshelf> predicate;

    /**
     * Class constructor
     * @param id used to link the common goal to its image
     * @param name name of the goal
     * @param predicate tests the goal on a given bookshelf
     */
    public CommonGoal(int id, String name, Predicate<Bookshelf> predicate) {
        this.id = id;
        this.name = name;
        this.predicate = predicate;
    }

    /**
     * Id getter
     * @return id attribute
     */
    public int getId() {
        return id;
    }

    /**
     * Method that tests the goal with the given bookshelf
     * @param bookshelf bookshelf to test
     * @return result of the test
     */
    public boolean checkGoal(Bookshelf bookshelf){
        return predicate.test(bookshelf);
    }

    @Override
    public String toString() {
        return name;
    }
}
