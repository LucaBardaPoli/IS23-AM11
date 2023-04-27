package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Class that represents the common goal that searches for 2 2x2 squares, each one made of the same tail types
 */
public class SameKindSquareGoal implements Predicate<Bookshelf>, Serializable {
    /**
     * Checks if exists a 2x2 square starting from the given position towards the bottom-right corner
     * @param startRow row of the starting point
     * @param startColumn column of the starting point
     * @param bookshelf bookshelf to execute the check on
     * @return true whether the 2x2 square exists
     */
    private boolean checkSquare(int startRow, int startColumn, Bookshelf bookshelf) {
        Tile tile;
        Tile currentType;

        tile = bookshelf.getTile(new Position(startRow, startColumn));
        if(!tile.equals(Tile.EMPTY)) {
            currentType = tile;

            tile = bookshelf.getTile(new Position(startRow + 1, startColumn));
            if (!tile.equals(Tile.EMPTY)) {
                if (!tile.equals(currentType)) {
                    return false;
                }
            } else {
                return false;
            }

            tile = bookshelf.getTile(new Position(startRow + 1, startColumn + 1));
            if (!tile.equals(Tile.EMPTY)) {
                if (!tile.equals(currentType)) {
                    return false;
                }
            } else {
                return false;
            }

            tile = bookshelf.getTile(new Position(startRow, startColumn + 1));
            if (tile == Tile.EMPTY){
                return false;
            } else {
                return tile == currentType;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if exists 2 different 2x2 squares in the bookshelf
     * @param bookshelf bookshelf to execute the check on
     * @return true whether the 2 squares exists
     */
    public boolean test(Bookshelf bookshelf) {
        Optional<Integer> startRowLastSquare = Optional.empty();
        Optional<Integer> startColumnLastSquare = Optional.empty();

        for(int r = 0; r < GameSettings.ROWS-1; r++) {
            for(int c = 0; c < GameSettings.COLUMNS-1; c++) {
                if(checkSquare(r, c, bookshelf)) {
                    if(startRowLastSquare.isEmpty()) {
                        startRowLastSquare = Optional.of(r);
                        startColumnLastSquare = Optional.of(c);
                    } else {
                        if(Math.abs(r - startRowLastSquare.get()) >= 2 || Math.abs(c - startColumnLastSquare.get()) >= 2) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
