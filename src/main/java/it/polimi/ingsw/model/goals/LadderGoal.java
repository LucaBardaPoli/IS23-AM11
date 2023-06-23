package it.polimi.ingsw.model.goals;

import java.io.Serializable;
import java.util.function.Predicate;
import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.GameSettings;
import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.model.Position;

public class LadderGoal implements Predicate<Bookshelf>, Serializable {

    /**
     * Test method of the predicate
     * @param bookshelf the input argument
     * @return true whether the goal is achieved
     */
    @Override
    public boolean test(Bookshelf bookshelf) {
        return testDescendingLadder(0, 0, bookshelf) ||
                testDescendingLadder(1, 0, bookshelf) ||
                testAscendingLadder(5, 0, bookshelf) ||
                testAscendingLadder(4, 0, bookshelf);
    }

    /**
     * Checks if exists a descending ladder starting from the given position towards the bottom-right corner
     * @param startRow row of the starting point
     * @param startColumn column of the starting point
     * @param bookshelf bookshelf to execute the check on
     * @return true whether the descending ladder exists
     */
    private boolean testDescendingLadder(int startRow, int startColumn, Bookshelf bookshelf) {
        int limit = Math.min(GameSettings.COLUMNS, GameSettings.ROWS);
        Tile tile;

        tile = bookshelf.getTile(new Position(startRow, startColumn));
        if(!tile.equals(Tile.EMPTY) && bookshelf.getTile(new Position(startRow-1, startColumn)).equals(Tile.EMPTY)) {
            for (int i = 1; i < limit; i++) {
                tile = bookshelf.getTile(new Position(startRow+i, startColumn+i));
                if (tile.equals(Tile.EMPTY) || !bookshelf.getTile(new Position(startRow+i-1, startColumn+i)).equals(Tile.EMPTY)) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Checks if exists an ascending ladder starting from the given position towards the top-right corner
     * @param startRow row of the starting point
     * @param startColumn column of the starting point
     * @param bookshelf bookshelf to execute the check on
     * @return true whether the ascending ladder exists
     */
    private boolean testAscendingLadder(int startRow, int startColumn, Bookshelf bookshelf) {
        int limit = Math.min(GameSettings.COLUMNS, GameSettings.ROWS);
        Tile card;

        card = bookshelf.getTile(new Position(startRow, startColumn));
        if(!card.equals(Tile.EMPTY) && bookshelf.getTile(new Position(startRow-1, startColumn)).equals(Tile.EMPTY)) {
            for (int i = 1; i < limit; i++) {
                card = bookshelf.getTile(new Position(startRow-i, startColumn+i));
                if (card.equals(Tile.EMPTY) || !bookshelf.getTile(new Position(startRow-i-1, startColumn+i)).equals(Tile.EMPTY)) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
