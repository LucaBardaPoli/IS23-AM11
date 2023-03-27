package it.polimi.ingsw.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
public class Bookshelf {

    private Integer rows;
    private Integer columns;
    private Optional<CardType> bookshelf [][];

    /**
     * constructor
     * @param rows number of rows wanted in the bookshelf
     * @param columns number of columns wanted in the bookshelf
     */
    public Bookshelf(Integer rows, Integer columns) {
        this.rows = rows;
        this.columns = columns;
        this.bookshelf = new Optional[6][5];
        for(int i = 0; i < this.rows; i++){
            for(int j = 0; j < this.columns; j++){
                this.bookshelf[i][j] = Optional.empty();
            }
        }
    }

    /**
     * add the maximum 3 cards passed from input to the bookshelf
     * @param cards cards that need to be added to the bookshelf
     * @param column number of the column of the bookshelf where tha card must be added
     */
    public void addCells(List<CardType> cards, Integer column){

        int k = getFreeCells(column); // k are the free cells of the considered column
        int i = k - 1; // i scrolls through bookshelf rows

        for(CardType obtainedCard : cards){
            bookshelf[i][column] = Optional.ofNullable(obtainedCard);
            i--;
        }
    }

    /**
     * returns the bookshelf cell related to the position data
     * @param position position of the card that needs to be returned
     * @return
     */
    public Optional<CardType> getCell(Position position){
        if (bookshelf[position.getRow()][position.getColumn()].isPresent()){
            return Optional.of(bookshelf[position.getRow()][position.getColumn()].get());
        }
        else return Optional.empty();
    }

    /**
     * returns an ArrayList with all the cards contained in the column indicated in input
     *
     * @param columnNumber indicates the column the cards must be taken from
     * @return
     */
    public ArrayList<Optional<CardType>> getColumn(Integer columnNumber){

        ArrayList<Optional<CardType>> cardColumn = new ArrayList();
        Position position = new Position();
        position.setColumn(columnNumber);

        for(int i = 0; i < getRows(); i++){
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
     *
     * @param rowNumber indicates the row the cards must be taken from
     * @return
     */
    public ArrayList<Optional<CardType>> getRow(Integer rowNumber){
        ArrayList<Optional<CardType>> rigaDiCarte = new ArrayList();
        Position position = new Position();
        position.setRow(rowNumber);
        for(int i = 0 ; i < getColumns(); i++){
            position.setColumn(i);
            Optional<CardType> cardOptional = getCell(position);
            rigaDiCarte.add(cardOptional);
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
        for (int i = 0 ; i < getColumns(); i++){

            position.setColumn(i);

            for(int j = 0; j < getRows(); j++){

                //save the position and extract the card
                position.setRow(j);

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
     * @param columnNumber number that indicates which column needs to be examined
     * @return
     */
    public Integer getFreeCells(Integer columnNumber){
        return (getRows() - getColumn(columnNumber).size());
    }

    /**
     * getter of attribute rows
     * @return
     */
    public Integer getRows() {
        return rows;
    }

    /**
     * getter of attribute columns
     * @return
     */
    public Integer getColumns() {
        return columns;
    }
}
