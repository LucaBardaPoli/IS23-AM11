package it.polimi.ingsw.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

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

        this.currentNumberOfCards = MAX_NUM_CARDS_PER_TYPE * 6;
    }

    /**
     * Picks a new card from the bag
     * @return the new card
     */
    public CardType pickCard(){
        return generateCard();
    }

    /**
     * Randomly picks a new card from the bag
     * Generates a random number from 0 to the current number of cards left to pick
     * Based on which interval, given by the number of cards left for each type, the number belongs it generates the new card
     * @return new picked card
     */
    private CardType generateCard() {
        Random random = new Random();
        Integer count = 0, flag = 0;

        //Random default value
        CardType newCard = CardType.GREEN;

        int randomNumber = random.nextInt(this.currentNumberOfCards) + 1;

        for (Map.Entry<CardType, Integer> entry : countCardType.entrySet()) {
            //update the counter value
            count += entry.getValue();

            if (count <= randomNumber && flag == 0) {
                //generate the new card
                newCard = entry.getKey();

                //decreases the total number of cards left
                this.currentNumberOfCards--;

                //modifies the map element with the specified key
                int newValue2 = countCardType.get(entry.getKey()) - 1;
                countCardType.put(entry.getKey(), newValue2);

                //raises the flag to communicate that the new card has been generated
                flag = 1;
            }
        }
        return newCard;
    }

    /**
     * getter of CountCardType attribute
     * @return returns the attribute
     */
    public LinkedHashMap<CardType, Integer> getCountCardType() {
        return countCardType;
    }
}






