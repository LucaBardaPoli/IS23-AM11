package it.polimi.ingsw.model;

import java.util.List;
import java.util.Optional;

public interface GameInterface {
    public Player getCurrentPlayer();
    public Optional<CardType> pickCard(Position position);
    public boolean confirmChoice();
    public Optional<List<CardType>> confirmColumn(Integer column);
    public List<CardType> rearrangeCards(Integer position);
    public void confirmOrderSelectedCards();

}

