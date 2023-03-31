package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bookshelf {
    private final static Integer ROWS = 6;
    private final static Integer COLUMNS = 5;
    private final Optional<CardType>[][] bookshelf;

    /**
     * Class constructor
     */
    public Bookshelf() {
        this.bookshelf = new Optional[Bookshelf.ROWS][Bookshelf.COLUMNS];
        for(int i = 0; i < Bookshelf.ROWS; i++) {
            for(int j = 0; j < Bookshelf.COLUMNS; j++) {
                this.bookshelf[i][j] = Optional.empty();
            }
        }
    }

    public static Integer getRows() {
        return ROWS;
    }

    public static Integer getColumns() {
        return COLUMNS;
    }

    /**
     * add the maximum 3 cards passed from input to the bookshelf
     * @param cards cards that need to be added to the bookshelf
     * @param column number of the column of the bookshelf where tha card must be added
     */
    public void addCells(List<CardType> cards, Integer column){

        int freeCells = getFreeCells(column); // k are the free cells of the considered column
        int i = freeCells - 1; // i scrolls through bookshelf rows from the bottom

        if(freeCells >= cards.size()){
            for(CardType obtainedCard : cards){
                bookshelf[i][column] = Optional.ofNullable(obtainedCard);
                i--;
            }
        }
        //else throw new InsufficientFreeCellsException();
    }

    /**
     * returns the bookshelf cell related to the position data
     * @param position position of the card that needs to be returned
     * @return returns an Optional.empty() if the position contains no card, otherwise it returns the card
     */
    public Optional<CardType> getCell(Position position){
        /*if (bookshelf[position.getRow()][position.getColumn()].isPresent()){
            return Optional.of(bookshelf[position.getRow()][position.getColumn()].get());
        }
        else return Optional.empty();*/
        return bookshelf[position.getRow()][position.getColumn()];
    }

    /**
     * returns an ArrayList with all the cards contained in the column indicated in input
     * @param columnNumber indicates the column the cards must be taken from
     * @return returns all of the cards that are present in the given column
     */
    public ArrayList<Optional<CardType>> getColumn(Integer columnNumber){

        ArrayList<Optional<CardType>> cardColumn = new ArrayList<>();
        Position position = new Position();
        position.setColumn(columnNumber);

        for(int i = 0; i < Bookshelf.ROWS; i++){
            position.setRow(i);
            Optional<CardType> cardOptional = getCell(position);
            if(cardOptional.isPresent()){
                cardColumn.add(cardOptional);
            }
        }
        return cardColumn;
    }

    /**
     * returns an ArrayList with all the cards contained in the row indicated in input
     * @param rowNumber indicates the row the cards must be taken from
     * @return returns all of the cards that are present in the given row
     */
    public ArrayList<Optional<CardType>> getRow(Integer rowNumber){
        ArrayList<Optional<CardType>> cardRow = new ArrayList<>();
        Position position = new Position();
        position.setRow(rowNumber);
        for(int i = 0; i < Bookshelf.COLUMNS; i++){
            position.setColumn(i);
            Optional<CardType> cardOptional = getCell(position);
            cardRow.add(cardOptional);
        }
        return cardRow;
    }

    /**
     *
     * @return returns the total number of empty boxes in the bookshelf
     */
    public Integer getFreeCells() {

        //to use the ifPresent() function I decided to count al
        // reverse scaling the counter if a cell contains a card
        int counter = Bookshelf.ROWS * Bookshelf.COLUMNS;

        Position position = new Position();
        for (int i = 0; i < Bookshelf.COLUMNS; i++){

            position.setColumn(i);

            for(int j = 0; j < Bookshelf.ROWS; j++){

                //save the position and extract the card
                position.setRow(j);

                Optional<CardType> cardOptional = getCell(position);

                //subtract if the cell does NOT contain NULL
                if (cardOptional.isPresent()) {
                    counter--;
                }
            }
        }
        return counter;
    }

    /**
     * @param columnNumber number that indicates which column needs to be examined
     * @return return the free cells given a given column
     */
    public Integer getFreeCells(Integer columnNumber){
        return (Bookshelf.ROWS - getColumn(columnNumber).size());
    }
}
