package it.polimi.ingsw.model;

import java.util.List;
import java.util.Optional;

public interface GameInterface {
    public Player getCurrentPlayer();
    public Optional<Card> pickCard(Position position);
    public boolean confirmChoice();
    public Optional<List<Card>> confirmColumn(Integer column);

    public List<Card> rearrangeCards(Integer position);

    public void confirmOrderSelectedCards();

}

