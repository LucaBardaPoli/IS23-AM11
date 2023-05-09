package it.polimi.ingsw.model;

import org.junit.Test;

/**
 * Testing of Board class
 */
public class BoardTest {

    private Board board;
    private Bag bag;

    /**
     * Tests fill process of the board
     */
    @Test
    public void testFillBoard() {
        this.bag = new Bag();
        this.board = new Board(3, bag);
        this.board.fillBoard();

        // Checks if cards are where they should
        assert(this.board.validPick(new Position(1, 0)));
        assert(this.board.validPick(new Position(6, 0)));
        assert(this.board.validPick(new Position(5, -2)));
        assert(this.board.validPick(new Position(3, 4)));

        // Checks if cards are where they should not
        assert(!this.board.validPick(new Position(0, 1)));
        assert(!this.board.validPick(new Position(3, -2)));
        assert(!this.board.validPick(new Position(1, 2)));
        assert(!this.board.validPick(new Position(4, 5)));
    }

    /**
     * Tests fill process of the board
     */
    @Test
    public void testRefillBoard() {
        this.bag = new Bag();
        this.board = new Board(2, bag);
        this.board.fillBoard();

        // Picks up a lot of cards until there is no valid pick
        assert(board.pickTile(new Position(1,0)) != Tile.EMPTY);
        assert(!this.board.hasToBeRefilled());
        assert(board.pickTile(new Position(2,0)) != Tile.EMPTY);
        assert(!this.board.hasToBeRefilled());
        assert(board.pickTile(new Position(2,1)) != Tile.EMPTY);
        assert(!this.board.hasToBeRefilled());
        assert(board.pickTile(new Position(3,-1)) != Tile.EMPTY);
        assert(!this.board.hasToBeRefilled());
        assert(board.pickTile(new Position(3,0)) != Tile.EMPTY);
        assert(!this.board.hasToBeRefilled());
        assert(board.pickTile(new Position(3,1)) != Tile.EMPTY);
        assert(!this.board.hasToBeRefilled());
        assert(board.pickTile(new Position(3,2)) != Tile.EMPTY);
        assert(!this.board.hasToBeRefilled());
        assert(board.pickTile(new Position(3,3)) != Tile.EMPTY);
        assert(!this.board.hasToBeRefilled());
        assert(board.pickTile(new Position(4,-2)) != Tile.EMPTY);
        assert(!this.board.hasToBeRefilled());
        assert(board.pickTile(new Position(4,-1)) != Tile.EMPTY);
        assert(!this.board.hasToBeRefilled());
        assert(board.pickTile(new Position(4,0)) != Tile.EMPTY);
        assert(!this.board.hasToBeRefilled());
        assert(board.pickTile(new Position(4,4)) != Tile.EMPTY);
        assert(!this.board.hasToBeRefilled());
        assert(board.pickTile(new Position(4,3)) != Tile.EMPTY);
        assert(!this.board.hasToBeRefilled());
        assert(board.pickTile(new Position(4,2)) != Tile.EMPTY);
        assert(!this.board.hasToBeRefilled());
        assert(board.pickTile(new Position(5,-2)) != Tile.EMPTY);
        assert(!this.board.hasToBeRefilled());
        assert(board.pickTile(new Position(5,-1)) != Tile.EMPTY);
        assert(!this.board.hasToBeRefilled());
        assert(board.pickTile(new Position(5,0)) != Tile.EMPTY);
        assert(!this.board.hasToBeRefilled());
        assert(board.pickTile(new Position(5,1)) != Tile.EMPTY);
        assert(!this.board.hasToBeRefilled());
        assert(board.pickTile(new Position(5,2)) != Tile.EMPTY);
        assert(!this.board.hasToBeRefilled());
        assert(board.pickTile(new Position(6,2)) != Tile.EMPTY);
        assert(!this.board.hasToBeRefilled());
        assert(board.pickTile(new Position(6,1)) != Tile.EMPTY);
        assert(!this.board.hasToBeRefilled());
        assert(board.pickTile(new Position(7,2)) != Tile.EMPTY);

        // Checks that there is no valid pick on the board, so it has to be refilled
        assert(this.board.hasToBeRefilled());
        this.board.fillBoard();

        // Checks that the board is now full
        assert(!this.board.hasToBeRefilled());
    }

    /**
     * Tests getCard method
     */
    @Test
    public void testGetCard() {
        this.bag = new Bag();
        this.board = new Board(3, bag);
        this.board.fillBoard();

        assert(this.board.setTile(new Position(2, 3), Tile.WHITE));
        assert(this.board.getTile(new Position(2, 3)) == Tile.WHITE);
        assert(this.board.getTile(new Position(10, 10)) == Tile.EMPTY);
        assert(this.board.getTile(new Position(0, 1)) == Tile.EMPTY);
    }

    /**
     * Tests validPick method
     */
    @Test
    public void testValidPick() {
        this.bag = new Bag();
        this.board = new Board(2, bag);
        this.board.fillBoard();

        // Checks if cards that should be possible to pick can actually be picked
        assert(this.board.validPick(new Position(2,0)));
        assert(this.board.validPick(new Position(3,-1)));
        assert(this.board.validPick(new Position(3,3)));
        assert(this.board.validPick(new Position(3,4)));

        // Checks if cards that should not be possible to pick can be picked
        assert(!this.board.validPick(new Position(2,1)));
        assert(!this.board.validPick(new Position(3,0)));
        assert(!this.board.validPick(new Position(3,1)));
        assert(!this.board.validPick(new Position(4,-1)));
        assert(!this.board.validPick(new Position(5,2)));
    }

    /**
     * Tests validBoard method
     */
    @Test
    public void testValidBoard() {
        this.bag = new Bag();
        this.board = new Board(2, bag);

        assert(this.board.hasToBeRefilled());

        this.board.setTile(new Position(1, 0), Tile.BLUE);
        assert(this.board.hasToBeRefilled());

        this.board.setTile(new Position(2, 1), Tile.WHITE);
        assert(this.board.hasToBeRefilled());

        this.board.setTile(new Position(1, 1), Tile.BLUE);
        assert(!this.board.hasToBeRefilled());
    }
}