package it.polimi.ingsw.network;

/**
 * Class that defines the selection of a column where to insert the cards
 */
public class SelectColumn {
    private Integer column;

    /**
     * Class constructor
     * @param column column where to insert the cards
     */
    public SelectColumn(Integer column) {
        this.column = column;
    }

    /**
     * Getter of the column
     * @return column
     */
    public Integer getColumn() {
        return this.column;
    }
}
