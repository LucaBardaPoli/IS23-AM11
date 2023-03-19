package it.polimi.ingsw;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class CountCards {

    private LinkedHashMap<CardType, Integer> countCardType;
    private static Integer MAX_NUM_CARDS_TYPE = 132;


    public CountCards() {
        countCardType = new LinkedHashMap<CardType,Integer>();

        //istanzio staticamente tutte le carte con i rispettivi colori
        countCardType.put(CardType.GREEN,22);
        countCardType.put(CardType.WHITE,22);
        countCardType.put(CardType.YELLOW,22);
        countCardType.put(CardType.BLUE,22);
        countCardType.put(CardType.LBLUE,22);
        countCardType.put(CardType.PINK,22);
    }

    //funzione pubblica per chiamare generateCard
    public Card PickCard(){
        Card newCard = GenerateCard();
        return newCard;
    }

    //genera una carta scalando tutti gli indici del caso dalla hashMap:
    /* FUNZIONAMENTO:
    -itera su le chiave della hashMap (che sono i valori della enum CardType)
    -aggiorna man mano il valore dell'intero count per capire in che intervallo sia caduto
        randomNumber
    -ogni volta che viene creata una carta il valore di MAX_NUM_CARDS_TYPE viene aggiornato
        di modo tale che il numero casuale venga generato nell'intervallo logicamente valido
    -una volta trovato l'intervallo giusto crea la carta del colore corrispondente e diminuisce il
        valore Integer di countCardType
     */
    private Card GenerateCard() {
        Random random = new Random();
        Integer count = 0, flag = 0;

        //genero un numero tra 1 e MAX_NUM_CARDS_TYPE
        int randomNumber = random.nextInt(getMaxNumCardsType()) + 1;

        //creo la carta che poi istanzierò dinamicamente nell'if
        Card newCard = null;
        for (Map.Entry<CardType, Integer> entry : countCardType.entrySet()) {
            //aggiorna il valore del contatore
            count = count + entry.getValue();

            //se dobbiamo procedere con la lista dei colori
            if (count <= randomNumber && flag == 0) {
                //genera la nuova carta
                newCard = new Card(entry.getKey());

                //diminiusce il numero totale di carte rimaste
                int nuovoValore = getMaxNumCardsType() - 1;
                setMaxNumCardsType(nuovoValore);

                //modifica l'elemento della mappa con la chiave specificata
                int nuovoValore2 = countCardType.get(entry.getKey()) - 1;
                countCardType.put(entry.getKey(), nuovoValore2);

                //alza il flag per comunicare che la carta nuova è stata generata
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






