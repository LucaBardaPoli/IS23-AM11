package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.GameSettings;
import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.model.Position;

import java.io.Serializable;
import java.util.Arrays;
import java.util.function.Predicate;

public class DifferentTypesGoal implements Predicate<Bookshelf>, Serializable {
    // the minimum numbers of different types that have to be on a row/column for it to be counted
    private final int min_types;

    // the maximum numbers of different types that have to be on a row/column for it to be counted
    private final int max_types;

    // the minimum numbers of counted rows/columns required for the goal to be fulfilled
    private final int min_num;

    // CheckMode.HORIZONTAL is for rows and CheckMode.VERTICAL is for columns
    private final CheckMode mode;

    public DifferentTypesGoal(int min_types, int max_types, int min_num, CheckMode mode) {
        this.min_types = min_types;
        this.max_types = max_types;
        this.min_num = min_num;
        this.mode = mode;
    }

    /**
     * Tests the common goal's properties on each row
     * @param bookshelf the bookshelf to check
     * @return true whether the goal is achieved
     */
    private boolean testRows(Bookshelf bookshelf){
        int nrows = GameSettings.ROWS;
        int ncolumns = GameSettings.COLUMNS;
        int ntypes = Tile.numColors;
        int i, j;
        int count_types, count_rows = 0;
        boolean fullRow;
        Tile tileType;

        boolean[] typeFound = new boolean[ntypes];

        for(i = 0; i < nrows; i++){
            // at the start of each row no types have been found, yet
            Arrays.fill(typeFound, false);
            fullRow = true;
            count_types = 0;
            for(j = 0; j < ncolumns && fullRow; j++){
                tileType = bookshelf.getTile(new Position(i, j));
                if(tileType != Tile.EMPTY){
                    if(!typeFound[tileType.ordinal()]){
                        count_types++;
                        typeFound[tileType.ordinal()] = true;
                    }
                } else {
                    fullRow = false;
                }
            }
            if(fullRow && count_types >= min_types && count_types <= max_types){
                count_rows++;
            }
        }

        return count_rows >= min_num;
    }

    /**
     * Tests the common goal's properties on each column
     * @param bookshelf the bookshelf to check
     * @return true whether the goal is achieved
     */
    private boolean testColumns(Bookshelf bookshelf){
        int nrows = GameSettings.ROWS;
        int ncolumns = GameSettings.COLUMNS;
        int ntypes = Tile.numColors;
        int i, j;
        int count_types, count_columns = 0;
        boolean fullColumn;
        Tile tileType;

        boolean[] typeFound = new boolean[ntypes];

        for(j = 0; j < ncolumns; j++){
            // at the start of each column no types have been found, yet
            Arrays.fill(typeFound, false);
            fullColumn = true;
            count_types = 0;
            for(i = 0; i < nrows && fullColumn; i++){
                tileType = bookshelf.getTile(new Position(i, j));
                if(tileType != Tile.EMPTY){
                    if(!typeFound[tileType.ordinal()]){
                        count_types++;
                        typeFound[tileType.ordinal()] = true;
                    }
                } else {
                    fullColumn = false;
                }
            }
            if(fullColumn && count_types >= min_types && count_types <= max_types){
                count_columns++;
            }
        }

        return count_columns >= min_num;
    }

    /**
     * Test method of the predicate
     * @param bookshelf the input argument
     * @return true whether the goal is achieved
     */
    @Override
    public boolean test(Bookshelf bookshelf) {
        if(mode == CheckMode.HORIZONTAL){
            return testRows(bookshelf);
        } else if(mode == CheckMode.VERTICAL) {
            return testColumns(bookshelf);
        }
        return false;  // defensive programming
    }
}
