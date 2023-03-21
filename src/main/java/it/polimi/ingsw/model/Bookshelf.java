package it.polimi.ingsw.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bookshelf {

    private Cell bookshelf[][];


    //costruttore
    public Bookshelf (){
        this.bookshelf = new Cell[6][5];
    }

    //add the maximum 3 cards passed from input to the bookshelf
    // !!!!! it is important that a check has been made first on the number of cells available in the column
    public void addCells(ArrayList<Card> cards, Integer column){

        int i; // i scorre le righe di bookshelf
        int k = getFreeCells(); // k sono le caselle libere della colonna considerata
        int iteratoreCards = 0; // iteratoreCards itera su Cards per tre volte

        //in the three free positions it adds up to a maximum of 3 cards,
        // stops early if the cards are less than 3 by setting the index to -1
        for(i = k-1; i >= k-3; i--){
            Optional<Card> obtainedCard = Optional.ofNullable(cards.get(iteratoreCards));
            if (obtainedCard.isPresent()){
                bookshelf[i][column].setCard(Optional.ofNullable(cards.get(iteratoreCards)));
                iteratoreCards++;
            }
            else {
                i = -1;
            }

        }
    }


    //returns the bookshelf cell related to the position data
    public Cell getCell(Position position){
        int row =  position.getRow();
        int column = position.getColumn();
        return bookshelf[row][column];
    }

    //returns an ArrayList with all the cards contained in the column indicated in input
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

    //returns an ArrayList with all the cards contained in the row indicated in input
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

    //returns the total number of null boxes in the bookshelf
    public Integer getFreeCells() {

        //to use the ifPresent() function I decided to count al
        // reverse scaling the counter if a cell contains a card
        int contatore = 30;

        Position position = new Position();
        for (int i=0 ; i<5; i++){
            for(int j=0; j<6; j++){

                //save the position and extract the card
                position.setRow(j);
                position.setColumn(i);

                Optional<Card> cardOptional = getCell(position).getCard();

                //subtract if the cell does NOT contain NULL
                if (cardOptional.isPresent()) {
                    contatore--;
                }
            }
        }
        return contatore;
    }

    //return the free cells given a given column
    public Integer getFreeCells(Integer columnNumber){
        return (6 - getColumn(columnNumber).size());
    }


}
