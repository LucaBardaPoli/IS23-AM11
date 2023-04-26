package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.function.Predicate;

public class CommonGoal implements Serializable {
    private final String name;
    private final Predicate<Bookshelf> predicate;

    public CommonGoal(String name, Predicate<Bookshelf> predicate) {
        this.name = name;
        this.predicate = predicate;
    }

    public boolean checkGoal(Bookshelf bookshelf){
        return predicate.test(bookshelf);
    }

    @Override
    public String toString() {
        return name;
    }
}
