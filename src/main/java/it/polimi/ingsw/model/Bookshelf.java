package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Bookshelf implements Serializable {
    private final Optional<Tile>[][] bookshelf;

    /**
     * Class constructor
     */
    public Bookshelf() {
        this.bookshelf = new Optional[GameSettings.ROWS][GameSettings.COLUMNS];
        for(int i = 0; i < GameSettings.ROWS; i++) {
            for(int j = 0; j < GameSettings.COLUMNS; j++) {
                this.bookshelf[i][j] = Optional.empty();
            }
        }
    }

    /**
     * Add the maximum 3 tiles passed from input to the bookshelf
     * @param tiles tiles that need to be added to the bookshelf
     * @param column number of the column of the bookshelf where tha tiles must be added
     */
    public void addTiles(List<Tile> tiles, int column) {

        int freeCells = getFreeCells(column); // k are the free cells of the considered column
        int i = freeCells - 1; // i scrolls through bookshelf rows from the bottom

        if(freeCells >= tiles.size()){
            for(Tile obtainedTile : tiles){
                bookshelf[i][column] = Optional.ofNullable(obtainedTile);
                i--;
            }
        }
    }

    /**
     * Returns the tile at the given position
     * @param position position of the tile that needs to be returned
     * @return returns an Optional.empty() if the position contains no tile, otherwise it returns the tile
     */
    public Optional<Tile> getTile(Position position){
        int row = position.getRow();
        int column = position.getColumn();
        if(row >= 0 && row < GameSettings.ROWS && column >= 0 && column < GameSettings.COLUMNS){
            return bookshelf[position.getRow()][position.getColumn()];
        }
        return Optional.empty();
    }

    /**
     * Returns an ArrayList with all the tiles contained in the column indicated in input
     * @param columnNumber indicates the column the tiles must be taken from
     * @return returns all the tiles inside the given column
     */
    public ArrayList<Optional<Tile>> getColumn(int columnNumber) {

        ArrayList<Optional<Tile>> tileColumn = new ArrayList<>();
        Position position = new Position();
        position.setColumn(columnNumber);

        for(int i = 0; i < GameSettings.ROWS; i++){
            position.setRow(i);
            Optional<Tile> cardOptional = getTile(position);
            if(cardOptional.isPresent()){
                tileColumn.add(cardOptional);
            }
        }
        return tileColumn;
    }

    /**
     * Returns an ArrayList with all the tiles contained in the row indicated in input
     * @param rowNumber indicates the row the tiles must be taken from
     * @return returns all the tiles that are present in the given row
     */
    public ArrayList<Optional<Tile>> getRow(int rowNumber) {

        ArrayList<Optional<Tile>> tileRow = new ArrayList<>();
        Position position = new Position();
        position.setRow(rowNumber);
        for(int i = 0; i < GameSettings.COLUMNS; i++){
            position.setColumn(i);
            Optional<Tile> cardOptional = getTile(position);
            tileRow.add(cardOptional);
        }
        return tileRow;
    }

    /**
     * Returns the number of free cells
     * @return returns the total number of empty cells in the bookshelf
     */
    public int getFreeCells() {
        int counter = GameSettings.ROWS * GameSettings.COLUMNS;

        Position position = new Position();
        for (int i = 0; i < GameSettings.COLUMNS; i++){

            position.setColumn(i);

            for(int j = 0; j < GameSettings.ROWS; j++){

                //save the position and extract the card
                position.setRow(j);

                Optional<Tile> tileOptional = getTile(position);

                //subtract if the cell does NOT contain NULL
                if (tileOptional.isPresent()) {
                    counter--;
                }
            }
        }
        return counter;
    }

    /**
     * Returns the number of free cells inside the given column
     * @param columnNumber number that indicates which column needs to be examined
     * @return return the free cells given a given column
     */
    public int getFreeCells(Integer columnNumber){
        return (GameSettings.ROWS - getColumn(columnNumber).size());
    }
}
