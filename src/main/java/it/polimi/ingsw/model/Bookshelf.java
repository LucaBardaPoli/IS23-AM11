package it.polimi.ingsw.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bookshelf {

    private final Integer rows;
    private final Integer columns;
    private Optional<CardType>[][] bookshelf;

    public Bookshelf(Integer rows, Integer columns) {
        this.rows = rows;
        this.columns = columns;
    }

    //add the maximum 3 cards passed from input to the bookshelf
    //it is important that a check has been made first on the number of cells available in the column
    public void addCells(List<CardType> cards, Integer column){

        int i; // i scrolls through bookshelf rows
        int k = getFreeCells(); // k are the free cells of the considered column
        int iteratorCards = 0; // iteratoreCards iterates over Cards three times

        //in the three free positions it adds up to a maximum of 3 cards,
        // stops early if the cards are less than 3 by setting the index to -1
        for(i = k-1; i >= k-3; i--){
            Optional<CardType> obtainedCard = Optional.ofNullable(cards.get(iteratorCards));
            if (obtainedCard.isPresent()){
                bookshelf[i][column] = Optional.ofNullable(cards.get(iteratorCards));
                iteratorCards++;
            }
            else {
                i = -1;
            }

        }
    }

    //returns the bookshelf cell related to the position data
    public Optional<CardType> getCell(Position position){
        int row =  position.getRow();
        int column = position.getColumn();
        if(row < 0 || row >= rows || column < 0 || column >= columns){
            return Optional.empty();
        }
        return bookshelf[row][column];
    }

    //returns an ArrayList with all the cards contained in the column indicated in input
    public ArrayList<CardType> getColumn(Integer columnNumber){
        ArrayList<CardType> colonnaDiCarte = new ArrayList(getColumns());
        Position position = new Position();
        position.setColumn(columnNumber);
        for(int i=0; i < getColumns(); i++){
            position.setRow(i);
            Optional<CardType> cardOptional = getCell(position);
            cardOptional.ifPresent(colonnaDiCarte::add);
        }
        return colonnaDiCarte;
    }

    //returns an ArrayList with all the cards contained in the row indicated in input
    public ArrayList<CardType> getRow(Integer rowNumber){
        ArrayList<CardType> rigaDiCarte = new ArrayList(getRows());
        Position position = new Position();
        position.setRow(rowNumber);
        for(int i = 0 ; i < getRows(); i++){
            position.setColumn(i);
            Optional<CardType> cardOptional = getCell(position);
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
        for (int i=0 ; i < getColumns(); i++){
            for(int j=0; j < getRows(); j++){

                //save the position and extract the card
                position.setRow(j);
                position.setColumn(i);

                Optional<CardType> cardOptional = getCell(position);

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
        return (getRows() - getColumn(columnNumber).size());
    }

    public Integer getRows() {
        return rows;
    }

    public Integer getColumns() {
        return columns;
    }
}
