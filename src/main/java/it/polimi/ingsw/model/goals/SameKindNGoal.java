package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.function.Predicate;

public class SameKindNGoal implements Predicate<Bookshelf>, Serializable {
    private final int n;
    private final int[] count_types;

    public SameKindNGoal(int n) {
        this.n = n;
        count_types = new int[Tile.numColors];
        // initialize the counters
        Arrays.fill(count_types, 0);
    }

    /**
     * Test method of the predicate
     * @param bookshelf the input argument
     * @return true whether the goal is achieved
     */
    @Override
    public boolean test(Bookshelf bookshelf) {
        int nrows = GameSettings.ROWS;
        int ncolumns = GameSettings.COLUMNS;
        Tile tile;
        Position position = new Position();
        Arrays.fill(count_types, 0);
        int row_idx, col_idx;

        for(row_idx = 0; row_idx < nrows; row_idx++){
            position.setRow(row_idx);
            for(col_idx = 0; col_idx < ncolumns; col_idx++){
                position.setColumn(col_idx);
                tile = bookshelf.getTile(position);
                // tile.ifPresent(cardType -> count_types[cardType.ordinal()]++);
                if(tile != Tile.EMPTY){
                    if(++count_types[tile.ordinal()] >= n){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
