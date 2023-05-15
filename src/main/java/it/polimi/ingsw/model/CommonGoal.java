package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.function.Predicate;

public class CommonGoal implements Serializable {
    private final int id;
    private final String name;
    private final Predicate<Bookshelf> predicate;

    public CommonGoal(int id, String name, Predicate<Bookshelf> predicate) {
        this.id = id;
        this.name = name;
        this.predicate = predicate;
    }

    public int getId() {
        return id;
    }

    public boolean checkGoal(Bookshelf bookshelf){
        return predicate.test(bookshelf);
    }

    @Override
    public String toString() {
        return name;
    }
}
