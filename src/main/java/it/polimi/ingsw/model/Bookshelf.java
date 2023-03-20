package it.polimi.ingsw.model;
import java.util.ArrayList;
import java.util.Optional;

public class Bookshelf {

    private Cell bookshelf[][];


    //costruttore
    public Bookshelf (){
        this.bookshelf = new Cell[6][5];
    }

    //aggiunge le massimo 3 carte passate da input alla bookshelf
    // !!!!! è importante che sia stato effettuato prima un controllo sul numero di celle disponibili nella colonna
    public void addCells(ArrayList<Card> cards, Integer column){

        int i; // i scorre le righe di bookshelf
        int k = getFreeCells(); // k sono le caselle libere della colonna considerata
        int iteratoreCards = 0; // iteratoreCards itera su Cards per tre volte

        //nelle tre posizioni libere aggiunge fino a massimo 3 carte,
        // si interrompe prima se le carte sono meno di 3 mettendo l'indice a -1
        for(i = k-1; i >= k-3; i--){
            Optional<Card> obtainedCard = Optional.ofNullable(cards.get(iteratoreCards));
            if (obtainedCard.isPresent()){
                bookshelf[i][column].setCard(Optional.ofNullable(cards.get(iteratoreCards)));
                iteratoreCards++;
            } else {
                i = -1;
            }

        }
    }


    //restituisce la cella della bookshelf relativa alla position data
    public Cell getCell(Position position){
        int row =  position.getRow();
        int column = position.getColumn();
        return bookshelf[row][column];
    }

    //restituisce un ArrayList con tutte le carte contenute nella colonna indicata in input
    public ArrayList<Card> getColumn(Integer columnNumber){
        ArrayList<Card> colonnaDiCarte = new ArrayList(6);
        Position position = new Position();
        position.setColumn(columnNumber);
        for(int i=0; i<6; i++){
            position.setRow(i);
            Optional<Card> cardOptional = getCell(position).getCard();
            cardOptional.ifPresent(colonnaDiCarte::add);
        }
        return colonnaDiCarte;
    }

    //restituisce un ArrayList con tutte le carte contenute nella riga indicata in input
    public ArrayList<Card> getRow(Integer rowNumber){
        ArrayList<Card> rigaDiCarte = new ArrayList(6);
        Position position = new Position();
        position.setRow(rowNumber);
        for(int i=0; i<5; i++){
            position.setColumn(i);
            Optional<Card> cardOptional = getCell(position).getCard();
            cardOptional.ifPresent(rigaDiCarte::add);
        }
        return rigaDiCarte;
    }

    //restituisce il numero totale di casella null nella bookshelf
    public Integer getFreeCells() {

        //per usare la funzione ifPresent() ho deciso di contare al
        // contrario scalando il contatore se una cella contiene una carta
        int contatore = 30;

        Position position = new Position();
        for (int i=0 ; i<5; i++){
            for(int j=0; j<6; j++){

                //salvo la posizone ed estraggo la carta
                position.setRow(j);
                position.setColumn(i);

                Optional<Card> cardOptional = getCell(position).getCard();

                //sotraggo se la cella NON contiene NULL
                if (cardOptional.isPresent()) {
                    contatore--;
                }
            }
        }
        return contatore;
    }

    //restituisce le celle libere data una determinata colonna
    public Integer getFreeCells(Integer columnNumber){
        return (6 - getColumn(columnNumber).size());
    }


}
