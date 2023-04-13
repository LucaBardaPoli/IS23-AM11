package it.polimi.ingsw.network.message;

/**
 * Class that defines the selection of a card from the board
 */
public class SelectBoard {
    private Integer row;
    private Integer column;

    /**
     * Class constructor
     * @param row row of the board
     * @param column column of the board
     */
    public SelectBoard(Integer row, Integer column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Getter of the row
     * @return row
     */
    public Integer getRow() {
        return this.row;
    }

    /**
     * Getter of the column
     * @return column
     */
    public Integer getColumn() {
        return this.column;
    }
}
