package it.polimi.ingsw.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bookshelf {

    private Integer rows;
    private Integer columns;
    private Optional<CardType> bookshelf [][];

    /**
     * costructor
     * @param rows number of rows wanted in the bookshelf
     * @param columns number of columns wanted in the bookshelf
     */
    public Bookshelf(Integer rows, Integer columns) {
        this.rows = rows;
        this.columns = columns;
    }

    /**
     * add the maximum 3 cards passed from input to the bookshelf
     * @param cards cards that need to be added to the bookshelf
     * @param column number of the column of the bookshelf where tha card must be added
     */
    public void addCells(List<Optional<CardType>> cards, Integer column){

        int i; // i scrolls through bookshelf rows
        int k = getFreeCells(); // k are the free cells of the considered column
        int iteratorCards = 0; // iteratoreCards iterates over Cards three times

        //in the three free positions it adds up to a maximum of 3 cards,
        // stops early if the cards are less than 3 by setting the index to -1
        for(i = k-1; i >= k-3; i--){
            Optional<CardType> obtainedCard =  cards.get(iteratorCards);
            if (obtainedCard.isPresent()){
                bookshelf[i][column] = cards.get(iteratorCards);
                iteratorCards++;
            }
            else {
                i = -1;
            }
        }
    }

    /**
     * returns the bookshelf cell related to the position data
     * @param position position of the card that needs to be returned
     * @return
     */
    public Optional<CardType> getCell(Position position){
        return bookshelf[position.getRow()][position.getColumn()];
    }

    /**
     * returns an ArrayList with all the cards contained in the column indicated in input
     * @param columnNumber indicates the column the cards must be taken from
     * @return
     */
    public ArrayList<CardType> getColumn(Integer columnNumber){
        ArrayList<CardType> colonnaDiCarte = new ArrayList(getColumns());
        Position position = new Position();
        position.setColumn(columnNumber);
        for(int i = 0; i < getColumns(); i++){
            position.setRow(i);
            Optional<CardType> cardOptional = getCell(position);
            cardOptional.ifPresent(colonnaDiCarte::add);
        }
        return colonnaDiCarte;
    }


    /**
     * returns an ArrayList with all the cards contained in the row indicated in input
     * @param rowNumber indicates the row the cards must be taken from
     * @return
     */
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

    /**
     * //returns the total number of null boxes in the bookshelf
     * @return
     */
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

    /**
     * return the free cells given a given column
     * @param columnNumber number that indicates which colomn needs to be examined
     * @return
     */
    public Integer getFreeCells(Integer columnNumber){
        return (getRows() - getColumn(columnNumber).size());
    }

    /**
     * getter dell'attributo rows
     * @return
     */
    public Integer getRows() {
        return rows;
    }

    /**
     * getter dell'attributo columns
     * @return
     */
    public Integer getColumns() {
        return columns;
    }
}
