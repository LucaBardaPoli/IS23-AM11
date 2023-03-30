package it.polimi.ingsw.model;

import java.util.concurrent.ThreadLocalRandom;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class CountCards {
    private final LinkedHashMap<CardType, Integer> countCardType;
    private Integer currentNumberOfCards;
    private static final Integer MAX_NUM_CARDS_PER_TYPE = 22;

    /**
     * Class constructor
     */
    public CountCards() {
        countCardType = new LinkedHashMap<CardType,Integer>();
        countCardType.put(CardType.GREEN, MAX_NUM_CARDS_PER_TYPE);
        countCardType.put(CardType.WHITE, MAX_NUM_CARDS_PER_TYPE);
        countCardType.put(CardType.YELLOW, MAX_NUM_CARDS_PER_TYPE);
        countCardType.put(CardType.BLUE, MAX_NUM_CARDS_PER_TYPE);
        countCardType.put(CardType.LBLUE, MAX_NUM_CARDS_PER_TYPE);
        countCardType.put(CardType.PINK, MAX_NUM_CARDS_PER_TYPE);

        this.currentNumberOfCards = MAX_NUM_CARDS_PER_TYPE * CardType.values().length;
    }

    /**
     * Picks a new card from the bag
     *
     * Generates a random number from 0 to the current number of cards left to pick
     * Based on which interval, given by the number of cards left for each type, the number belongs it generates the new card
     * @return new picked card
     */
    public CardType pickCard() {
        //keeps track of the interval of cards we're currently checking
        Integer count = 0;
        boolean foundInterval = false;

        Optional<CardType> newCard = Optional.empty();
        int randomNumber = ThreadLocalRandom.current().nextInt(0, this.currentNumberOfCards + 1);

        for (Map.Entry<CardType, Integer> entry : countCardType.entrySet()) {
            //update the counter value
            count += entry.getValue();

            if(randomNumber <= count && !foundInterval) {
                //generate the new card
                newCard = Optional.of(entry.getKey());

                //decreases the total number of cards left
                this.currentNumberOfCards--;

                //modifies the map element with the specified key
                countCardType.put(entry.getKey(), countCardType.get(entry.getKey()) - 1);

                //raises the flag to communicate that the new card has been generated
                foundInterval = true;
            }
        }
        return newCard.get();
    }

    /**
     * Getter of CountCardType attribute
     * @return returns the attribute
     */
    public LinkedHashMap<CardType, Integer> getCountCardType() {
        return countCardType;
    }
}






