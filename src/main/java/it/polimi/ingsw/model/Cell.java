package it.polimi.ingsw.model;
import java.util.Optional;

public class Cell {

    private Position position;
    private Optional<Card> card;

    //al costruttore passo la poszione e il flag della cella di modo tale che
    // la carta possa essere istanziata in un secondo momento
    public Cell(Position position, Optional<Card> card) {
        this.position = position;
        this.card = card;
    }

    //getter e setter degli attributi
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
