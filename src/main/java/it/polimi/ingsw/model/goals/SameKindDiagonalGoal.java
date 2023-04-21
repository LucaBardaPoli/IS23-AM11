package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;

import java.util.Optional;
import java.util.function.Predicate;

public class SameKindDiagonalGoal implements Predicate<Bookshelf> {
    /**
     * Checks if exists a diagonal starting from the given position towards the bottom-right corner
     * @param startRow row of the starting point
     * @param startColumn column of the starting point
     * @param bookshelf bookshelf to execute the check on
     * @return true whether the diagonal exists
     */
    private boolean checkDiagonal(Integer startRow, Integer startColumn, Bookshelf bookshelf) {
        Integer limit = Math.min(GameSettings.COLUMNS, GameSettings.ROWS);
        Optional<Tile> tile;
        Tile currentType;

        tile = bookshelf.getTile(new Position(startRow, startColumn));
        if(tile.isPresent()) {
            currentType = tile.get();
            for (int i = 1; i < limit; i++) {
                tile = bookshelf.getTile(new Position(startRow+i, startColumn+i));
                if (tile.isPresent()) {
                    if (!tile.get().equals(currentType)) {
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
    private boolean checkAntidiagonal(Integer startRow, Integer startColumn, Bookshelf bookshelf) {
        Integer limit = Math.min(GameSettings.COLUMNS, GameSettings.ROWS);
        Optional<Tile> tile;
        Tile currentType;

        tile = bookshelf.getTile(new Position(startRow, startColumn));
        if(tile.isPresent()) {
            currentType = tile.get();
            for (int i = 1; i < limit; i++) {
                tile = bookshelf.getTile(new Position(startRow-i, startColumn+i));
                if (tile.isPresent()) {
                    if (!tile.get().equals(currentType)) {
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

    public boolean test(Bookshelf bookshelf) {
        return checkDiagonal(0, 0, bookshelf) || checkDiagonal(1, 0, bookshelf) ||
                checkAntidiagonal(5, 0, bookshelf) || checkDiagonal(4, 0, bookshelf);
    }
}
