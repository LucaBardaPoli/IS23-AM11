package it.polimi.ingsw.model;

public class Position {

    private Integer row;
    private Integer column;

    public Position (Integer row, Integer column){
        this.row = row;
        this.column = column;
    }

    public Position(){

    }

    public Integer getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

}
