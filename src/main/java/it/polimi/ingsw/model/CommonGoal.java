package it.polimi.ingsw.model;

import java.util.LinkedList;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.List;

public class CommonGoal {
    private final String name;
    private List<Token> tokens;
    private Predicate<Bookshelf> predicate;

    public CommonGoal(String name, List<Token> tokens, Predicate<Bookshelf> predicate) {
        this.name = name;
        this.tokens = new LinkedList<Token>(tokens);
        this.predicate = predicate;
    }
    // assuming the tokens' value is in descending order
    private Optional<Token> removeToken(){
        Optional<Token> token;
        if(tokens.isEmpty()){
            token = Optional.empty();
        }else{
            token = Optional.of(tokens.remove(0));
        }
        return token;
    }

    public Optional<Token> checkGoal(Bookshelf bookshelf){
        Optional<Token> token;
        if(predicate.test(bookshelf)){
            token = removeToken();
        }else{
            token = Optional.empty();
        }
        return token;
    }
}
