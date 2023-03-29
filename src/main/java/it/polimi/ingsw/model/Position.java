package it.polimi.ingsw.model;

import java.util.Objects;

public class Position {

    private Integer row;
    private Integer column;

    /**
     * constructor
     * @param row indicates the row of the position
     * @param column indicates the column of the position
     */
    public Position (Integer row, Integer column){
        this.row = row;
        this.column = column;
    }

    /**
     * constructor
     */
    public Position(){
        row = 0;
        column = 0;
    }

    /**
     * getter of row
     * @return returns the number of the row
     */
    public Integer getRow() {
        return row;
    }

    /**
     * getter of column
     * @return return the number of the column
     */
    public Integer getColumn() {
        return column;
    }

    /**
     * setter of row
     * @param row sets the row to this value
     */
    public void setRow(Integer row) {
        this.row = row;
    }

    /**
     * setter of column
     * @param column sets the column to this value
     */
    public void setColumn(Integer column) {
        this.column = column;
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
