package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.CardType;
import it.polimi.ingsw.model.Position;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public class DifferentTypesGoal implements Predicate<Bookshelf> {
    // the minimum numbers of different types that have to be on a row/column for it to be counted
    private final int min_types;
    // the maximum numbers of different types that have to be on a row/column for it to be counted
    private final int max_types;
    // the minimum numbers of counted rows/columns required for the goal to be fulfilled
    private final int min_num;
    // CheckMode.HORIZONTAL checks rows strategy and CheckMode.VERTICAL is checks columns strategy
    private final CheckMode mode;

    public DifferentTypesGoal(int min_types, int max_types, int min_num, CheckMode mode) {
        this.min_types = min_types;
        this.max_types = max_types;
        this.min_num = min_num;
        this.mode = mode;
    }

    private boolean testRows(Bookshelf bookshelf){
        int nrows = Bookshelf.getRows();
        int ncolumns = Bookshelf.getColumns();
        int ntypes = CardType.values().length;
        int i, j;
        int count_types = 0, count_rows = 0;
        Optional<CardType> cardType;
        boolean fullRow;
        boolean[] typeFound = new boolean[ntypes];

        for(i = 0; i < nrows; i++){
            // at the start of each row no types have been found, yet
            Arrays.fill(typeFound, false);
            count_types = 0;
            fullRow = true;
            for(j = 0; j < ncolumns && fullRow; j++){
                cardType = bookshelf.getCell(new Position(i, j));
                if(cardType.isPresent()) {
                    if (!typeFound[cardType.get().ordinal()]) {
                        count_types++;
                        typeFound[cardType.get().ordinal()] = true;
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

    private boolean testColumns(Bookshelf bookshelf){
        int nrows = Bookshelf.getRows();
        int ncolumns = Bookshelf.getColumns();
        int ntypes = CardType.values().length;
        int i, j;
        int count_types = 0, count_columns = 0;
        Optional<CardType> cardType;
        boolean fullColumn;
        boolean[] typeFound = new boolean[ntypes];

        for(j = 0; j < ncolumns; j++){
            Arrays.fill(typeFound, false);
            count_types = 0;
            fullColumn = true;
            for(i = 0; i < nrows && fullColumn; i++){
                cardType = bookshelf.getCell(new Position(i, j));
                if(cardType.isPresent()) {
                    if (!typeFound[cardType.get().ordinal()]) {
                        count_types++;
                        typeFound[cardType.get().ordinal()] = true;
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
