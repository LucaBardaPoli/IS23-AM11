package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Class that handles all possible actions that can be performed on the board
 */
public class Board implements Serializable {
    private final HashMap<Position, Tile> board;
    private final Bag bag;

    /**
     * Class constructor
     * @param numPlayers creates a different board depending on the number of players
     * @param bag tiles to use during the game
     */
    public Board(Integer numPlayers, Bag bag) {
        this.board = new HashMap<>();
        this.bag = bag;
        this.createBoardCells(numPlayers);
    }

    /**
     * Getter of the board
     * @return board attribute
     */
    public HashMap<Position, Tile> getBoard() {
        return board;
    }

    /**
     * Creates the correct cells of the board
     * @param numPlayers creates a different board based on the number of players
     */
    private void createBoardCells(Integer numPlayers) {
        if(numPlayers >= GameSettings.MIN_NUM_PLAYERS) {
            //ROW 1:
            board.put(new Position(1,0), Tile.EMPTY);
            board.put(new Position(1,1), Tile.EMPTY);

            //ROW 2:
            board.put(new Position(2,0), Tile.EMPTY);
            board.put(new Position(2,1), Tile.EMPTY);
            board.put(new Position(2,2), Tile.EMPTY);

            //ROW 3:
            board.put(new Position(3,-1),Tile.EMPTY);
            board.put(new Position(3,0), Tile.EMPTY);
            board.put(new Position(3,1), Tile.EMPTY);
            board.put(new Position(3,2), Tile.EMPTY);
            board.put(new Position(3,3), Tile.EMPTY);
            board.put(new Position(3,4), Tile.EMPTY);

            //ROW 4:
            board.put(new Position(4,-2), Tile.EMPTY);
            board.put(new Position(4,-1), Tile.EMPTY);
            board.put(new Position(4,0), Tile.EMPTY);
            board.put(new Position(4,1), Tile.EMPTY);
            board.put(new Position(4,2), Tile.EMPTY);
            board.put(new Position(4,3), Tile.EMPTY);
            board.put(new Position(4,4), Tile.EMPTY);

            //ROW 5:
            board.put(new Position(5,-2), Tile.EMPTY);
            board.put(new Position(5,-1), Tile.EMPTY);
            board.put(new Position(5,0), Tile.EMPTY);
            board.put(new Position(5,1), Tile.EMPTY);
            board.put(new Position(5,2), Tile.EMPTY);
            board.put(new Position(5,3), Tile.EMPTY);

            //ROW 6:
            board.put(new Position(6,0), Tile.EMPTY);
            board.put(new Position(6,1), Tile.EMPTY);
            board.put(new Position(6,2), Tile.EMPTY);

            //ROW 7:
            board.put(new Position(7,1), Tile.EMPTY);
            board.put(new Position(7,2),Tile.EMPTY);
        }

        if(numPlayers >= GameSettings.MIN_NUM_PLAYERS +1) {
            // ROW 0:
            board.put(new Position(0,0), Tile.EMPTY);

            //ROW 2:
            board.put(new Position(2,-1), Tile.EMPTY);
            board.put(new Position(2,3), Tile.EMPTY);

            //ROW 3:
            board.put(new Position(3,5), Tile.EMPTY);

            //ROW 5:
            board.put(new Position(5,-3), Tile.EMPTY);

            //ROW 6:
            board.put(new Position(6,-1), Tile.EMPTY);
            board.put(new Position(6,3), Tile.EMPTY);

            //ROW 8:
            board.put(new Position(8,2), Tile.EMPTY);
        }

        if(numPlayers >= GameSettings.MAX_NUM_PLAYERS) {
            //ROW 0:
            board.put(new Position(0,1), Tile.EMPTY);

            //ROW 1:
            board.put(new Position(1,2), Tile.EMPTY);

            //ROW 3:
            board.put(new Position(3,-2), Tile.EMPTY);

            //ROW 4:
            board.put(new Position(4,-3), Tile.EMPTY);
            board.put(new Position(4,5), Tile.EMPTY);

            //ROW 5:
            board.put(new Position(5,4), Tile.EMPTY);

            //ROW 7:
            board.put(new Position(7,0), Tile.EMPTY);

            //ROW 8:
            board.put(new Position(8,1), Tile.EMPTY);
        }
    }

    /**
     * Fills all the empty cells of the board
     */
    public void fillBoard() {
        for (HashMap.Entry<Position, Tile> entry : this.board.entrySet()) {
            if(entry.getValue() == Tile.EMPTY) {
                entry.setValue(this.bag.pickTile());
            }
        }
    }

    /**
     * Returns the tile of the board in the given position
     * @param position indicates the position where the tile is supposed to be
     * @return return an optional of tile
     */
    public Tile getTile(Position position) {
        if(this.board.get(position) != null) {
            return this.board.get(position);
        }
        return Tile.EMPTY;
    }

    /**
     * Returns the tile of the board in the given position removing it from the board
     * @param position indicates the position where the tile is supposed to be
     * @return return an optional of tile
     */
    public Tile pickTile(Position position) {
        Tile rtn = Tile.EMPTY;
        if(this.board.get(position) != Tile.EMPTY) {
            rtn = this.board.get(position);
            this.board.put(position, Tile.EMPTY);
        }
        return rtn;
    }

    /**
     * Sets a tile in a given cell of the board (used for testing)
     * @param position position where to place the tile
     * @param tile tile to place on the board
     * @return true if the given position belongs to the board
     */
    public boolean setTile(Position position, Tile tile) {
        if(this.board.containsKey(position)) {
            board.put(position, tile);
            return true;
        }
        return false;
    }

    /**
     * Checks if the board needs to be refilled
     * @return true if exists (at least) two adjacent tiles
     */
    public boolean hasToBeRefilled() {
        for(HashMap.Entry<Position, Tile> entry : board.entrySet()) {
            if(entry.getValue() != Tile.EMPTY && hasAdjacentTiles(entry.getKey())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a given position has at least an adjacent tile on the board
     * @param position indicates the position of the current tile
     * @return true if the tile has at least an adjacent tile
     */
    private boolean hasAdjacentTiles(Position position) {
        Position p = new Position(position.getRow(), position.getColumn());
        int row = position.getRow();
        int column = position.getColumn();

        p.setRow(row + 1);
        if(this.board.containsKey(p) && board.get(p) != Tile.EMPTY) {
            return true;
        }

        p.setRow(row - 1);
        if(this.board.containsKey(p) && board.get(p) != Tile.EMPTY) {
            return true;
        }

        p.setRow(row);
        p.setColumn(column + 1);
        if(this.board.containsKey(p) && board.get(p) != Tile.EMPTY) {
            return true;
        }

        p.setColumn(column - 1);
        if(this.board.containsKey(p) && board.get(p) != Tile.EMPTY) {
            return true;
        }

        return false;
    }

    /**
     * Returns TRUE if the tile is selectable by checking that there is at least one free cell around the position indicated
     * @param position indicates the position where the picked tile is
     * @return return true if the tile is selectable
     */
    public boolean validPick(Position position) {
        Position p = new Position(position.getRow(), position.getColumn());
        int row = position.getRow();
        int column = position.getColumn();

        if(!this.board.containsKey(p) || this.board.get(p) == Tile.EMPTY) {
            return false;
        }

        p.setRow(row + 1);
        if(!this.board.containsKey(p) || (this.board.containsKey(p) && this.board.get(p) == Tile.EMPTY)) {
            return true;
        }

        p.setRow(row - 1);
        if(!this.board.containsKey(p) || (this.board.containsKey(p) && this.board.get(p) == Tile.EMPTY)) {
            return true;
        }

        p.setRow(row);
        p.setColumn(column + 1);
        if(!this.board.containsKey(p) || (this.board.containsKey(p) && board.get(p) == Tile.EMPTY)) {
            return true;
        }

        p.setColumn(column - 1);
        if(!this.board.containsKey(p) || (this.board.containsKey(p) && board.get(p) == Tile.EMPTY)) {
            return true;
        }

        return false;
    }
}