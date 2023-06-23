package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;

import java.io.Serializable;
import java.util.function.Predicate;

public class SameKindDiagonalGoal implements Predicate<Bookshelf>, Serializable {
    /**
     * Checks if exists a diagonal starting from the given position towards the bottom-right corner
     * @param startRow row of the starting point
     * @param startColumn column of the starting point
     * @param bookshelf bookshelf to execute the check on
     * @return true whether the diagonal exists
     */
    private boolean checkDiagonal(Integer startRow, Integer startColumn, Bookshelf bookshelf) {
        int limit = Math.min(GameSettings.COLUMNS, GameSettings.ROWS);
        Tile tile;
        Tile currentType;

        tile = bookshelf.getTile(new Position(startRow, startColumn));
        if(!tile.equals(Tile.EMPTY)) {
            currentType = tile;
            for (int i = 1; i < limit; i++) {
                tile = bookshelf.getTile(new Position(startRow+i, startColumn+i));
                if (!tile.equals(Tile.EMPTY)) {
                    if (!tile.equals(currentType)) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Checks if exists an anti-diagonal starting from the given position towards the top-right corner
     * @param startRow row of the starting point
     * @param startColumn column of the starting point
     * @param bookshelf bookshelf to execute the check on
     * @return true whether the anti-diagonal exists
     */
    private boolean checkAntiDiagonal(Integer startRow, Integer startColumn, Bookshelf bookshelf) {
        int limit = Math.min(GameSettings.COLUMNS, GameSettings.ROWS);
        Tile tile;
        Tile currentType;

        tile = bookshelf.getTile(new Position(startRow, startColumn));
        if(!tile.equals(Tile.EMPTY)) {
            currentType = tile;
            for (int i = 1; i < limit; i++) {
                tile = bookshelf.getTile(new Position(startRow-i, startColumn+i));
                if (!tile.equals(Tile.EMPTY)) {
                    if (!tile.equals(currentType)) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Test method of the predicate
     * @param bookshelf the input argument
     * @return true whether the goal is achieved
     */
    public boolean test(Bookshelf bookshelf) {
        return checkDiagonal(0, 0, bookshelf) || checkDiagonal(1, 0, bookshelf) ||
                checkAntiDiagonal(5, 0, bookshelf) || checkDiagonal(4, 0, bookshelf);
    }
}
