package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;

import java.io.Serializable;
import java.util.function.Predicate;

/**
 * Class that represents the common goal that searches for a group of tails shaped as an x, each one of the same type
 */
public class SameKindXGoal implements Predicate<Bookshelf>, Serializable {
    /**
     * Checks if exists a group of tails shaped as an x starting from the given position towards the bottom-right corner
     * @param startRow of the starting point
     * @param startColumn column of the starting point
     * @param bookshelf bookshelf to execute the check on
     * @return true whether the x exists
     */
    private boolean checkX(int startRow, int startColumn, Bookshelf bookshelf) {
        Tile tile;
        Tile currentType;

        tile = bookshelf.getTile(new Position(startRow, startColumn));
        if(!tile.equals(Tile.EMPTY)) {
            currentType = tile;

            tile = bookshelf.getTile(new Position(startRow - 1, startColumn - 1));
            if (!tile.equals(Tile.EMPTY)) {
                if (!tile.equals(currentType)) {
                    return false;
                }
            } else {
                return false;
            }

            tile = bookshelf.getTile(new Position(startRow - 1, startColumn + 1));
            if (!tile.equals(Tile.EMPTY)) {
                if (!tile.equals(currentType)) {
                    return false;
                }
            } else {
                return false;
            }

            tile = bookshelf.getTile(new Position(startRow + 1, startColumn - 1));
            if (!tile.equals(Tile.EMPTY)) {
                if (!tile.equals(currentType)) {
                    return false;
                }
            } else {
                return false;
            }

            tile = bookshelf.getTile(new Position(startRow + 1, startColumn + 1));
            if (!tile.equals(Tile.EMPTY)) {
                return tile.equals(currentType);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if exists a group of tails shaped as an x in every cell of the bookshelf
     * @param bookshelf the input argument
     * @return true whether the group exists
     */
    public boolean test(Bookshelf bookshelf) {
        for(int r = 1; r < GameSettings.ROWS-1; r++){
            for(int c = 1; c < GameSettings.COLUMNS-1; c++) {
                if(checkX(r, c, bookshelf)) {
                    return true;
                }
            }
        }
        return false;
    }
}
