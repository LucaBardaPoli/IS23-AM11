package it.polimi.ingsw.model;
import java.util.Optional;

public class Cell {

    private Position position;
    private Optional<Card> card;

    // I pass the cell position and flag to the constructor so that
    // the card can be instantiated later


    public Cell(Position position, Optional<Card> card) {
        this.position = position;
        this.card = card;
    }

    //getter e setter
    public void setPosition(Position position) {
        this.position = position;
    }


    public Position getPosition() {
        return position;
    }

    public Optional<Card> getCard() {
        return card;
    }

    public void setCard(Optional<Card> card) {
        this.card = card;
    }
}
