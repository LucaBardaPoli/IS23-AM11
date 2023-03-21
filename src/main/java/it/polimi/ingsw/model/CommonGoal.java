package it.polimi.ingsw.model;

import java.util.function.Predicate;

public class CommonGoal {
    private final String name;
    private Predicate<Bookshelf> predicate;

    public CommonGoal(String name, Predicate<Bookshelf> predicate) {
        this.name = name;
        this.predicate = predicate;
    }

    public boolean checkGoal(Bookshelf bookshelf){
        return predicate.test(bookshelf);
    }
}
