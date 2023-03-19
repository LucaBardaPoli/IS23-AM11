package it.polimi.ingsw;

public class Card {
    private CardType type ;

    public Card(CardType type) {
        this.type = type;
    }

    public CardType getType() {
        return type;
    }
}
