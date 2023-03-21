package it.polimi.ingsw.model;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class CountCards {

    private LinkedHashMap<CardType, Integer> countCardType;
    private static Integer MAX_NUM_CARDS_TYPE = 132;


    public CountCards() {
        countCardType = new LinkedHashMap<CardType,Integer>();

        // statically instantiate all cards with their respective colors
        countCardType.put(CardType.GREEN,22);
        countCardType.put(CardType.WHITE,22);
        countCardType.put(CardType.YELLOW,22);
        countCardType.put(CardType.BLUE,22);
        countCardType.put(CardType.LBLUE,22);
        countCardType.put(CardType.PINK,22);
    }

    //public function to call generateCard
    public Card PickCard(){
        Card newCard = GenerateCard();
        return newCard;
    }

    //generate a card scaling all the indexes of the case from the hashMap:
    /* OPERATION:
    -iterate over the keys of the hashMap (which are the values ​​of the CardType enum)
    - gradually updates the value of the entire count to understand in which interval it fell
        randomNumber
    - every time a card is created the value of MAX_NUM_CARDS_TYPE is updated
        so that the random number is generated in the logically valid range
    -once you find the right interval create the card of the corresponding color and decrease the
        Integer value of countCardType
     */
    private Card GenerateCard() {
        Random random = new Random();
        Integer count = 0, flag = 0;

        //generate a number between 1 and MAX_NUM_CARDS_TYPE
        int randomNumber = random.nextInt(getMaxNumCardsType()) + 1;

        // I create the card which I will then dynamically instantiate in the if
        Card newCard = null;
        for (Map.Entry<CardType, Integer> entry : countCardType.entrySet()) {
            //update the counter value
            count = count + entry.getValue();

            //if we have to proceed with the color list
            if (count <= randomNumber && flag == 0) {
                //genera la nuova carta
                newCard = new Card(entry.getKey());

                //decreases the total number of cards left
                int newValue1 = getMaxNumCardsType() - 1;
                setMaxNumCardsType(newValue1);

                //modify the map element with the specified key
                int newValue2 = countCardType.get(entry.getKey()) - 1;
                countCardType.put(entry.getKey(), newValue2);

                //raise the flag to communicate that the new card has been generated
                flag = 1;
            }
        }
        return newCard;
    }

    public static Integer getMaxNumCardsType() {
        return MAX_NUM_CARDS_TYPE;
    }

    public static void setMaxNumCardsType(Integer maxNumCardsType) {
        MAX_NUM_CARDS_TYPE = maxNumCardsType;
    }
}






