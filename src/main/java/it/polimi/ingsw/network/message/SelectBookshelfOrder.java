package it.polimi.ingsw.network.message;

/**
 * Class that defines the selection of a new order of the picked cards
 */
public class SelectBookshelfOrder {
    private Integer card;

    /**
     * Class constructor
     * @param card selected card to change order
     */
    public SelectBookshelfOrder(Integer card) {
        this.card = card;
    }

    /**
     * Getter of the card
     * @return card
     */
    public Integer getCard() {
        return this.card;
    }
}
