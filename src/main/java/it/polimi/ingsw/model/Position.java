package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class that represents the position either of a bookshelf cell or of a board cell
 */
public class Position implements Serializable {

    private int row;
    private int column;

    /**
     * Class constructor
     * @param row indicates the row of the position
     * @param column indicates the column of the position
     */
    public Position (int row, int column){
        this.row = row;
        this.column = column;
    }

    /**
     * Class constructor
     */
    public Position() {
        row = 0;
        column = 0;
    }

    /**
     * Getter of row
     * @return returns the number of the row
     */
    public int getRow() {
        return row;
    }

    /**
     * Getter of column
     * @return return the number of the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * Setter of row
     * @param row sets the row to this value
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Setter of column
     * @param column sets the column to this value
     */
    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public String toString() {
        return "(" + row + ", " + column + ')';
    }

    /**
     * Equals method
     * @param o object to compare
     * @return true if the two object are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return row == position.row && column == position.column;
    }

    /**
     * Hash code method
     * @return hash code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(row, column);
    }
}
