package it.polimi.ingsw.model;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import java.util.Optional;

/**
 * Testing of Board class
 */
public class BoardTest extends TestCase {

    Board board;
    CountCards countCards;

    public BoardTest( String testName ) {
        super( testName );
    }

    public static Test suite() {
        return new TestSuite( BoardTest.class );
    }

    /**
     * Tests fill process of the board
     */
    public void testFillBoard() {
        this.board = new Board(3);
        this.countCards = new CountCards();
        this.board.fillBoard(countCards);

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
     * Tests getCard method
     */
    public void testGetCard() {
        this.board = new Board(3);
        this.countCards = new CountCards();
        this.board.fillBoard(countCards);

        assert(this.board.setCard(new Position(2, 3), CardType.WHITE));
        assert(this.board.getCard(new Position(2, 3)).equals(Optional.of(CardType.WHITE)));
        assert(this.board.getCard(new Position(10, 10)).equals(Optional.empty()));
        assert(this.board.getCard(new Position(0, 1)).equals(Optional.empty()));
    }

    /**
     * Tests validPick method
     */
    public void testValidPick() {
        this.board = new Board(2);
        this.countCards = new CountCards();
        this.board.fillBoard(countCards);

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
    public void testValidBoard() {
        this.board = new Board(2);

        assert(this.board.hasToBeRefilled());

        this.board.setCard(new Position(1, 0), CardType.BLUE);
        assert(this.board.hasToBeRefilled());

        this.board.setCard(new Position(2, 1), CardType.WHITE);
        assert(this.board.hasToBeRefilled());

        this.board.setCard(new Position(1, 1), CardType.BLUE);
        assert(!this.board.hasToBeRefilled());
    }
}