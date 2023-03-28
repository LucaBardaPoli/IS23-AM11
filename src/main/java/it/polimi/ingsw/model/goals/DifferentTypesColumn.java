package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.CardType;
import it.polimi.ingsw.model.Position;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public class DifferentTypesColumn implements Predicate<Bookshelf> {
    // the minimum numbers of different types that have to be on a column for it to be counted
    private final int min_types;
    // the maximum numbers of different types that have to be on a column for it to be counted
    private final int max_types;
    // the minimum numbers of counted columns required for the goal to be fulfilled
    private final int min_columns;

    public DifferentTypesColumn(int minTypes, int maxTypes, int minColumns) {
        min_types = minTypes;
        max_types = maxTypes;
        min_columns = minColumns;
    }

    @Override
    public boolean test(Bookshelf bookshelf) {
        int nrows = bookshelf.getRows();
        int ncolumns = bookshelf.getColumns();
        int ntypes = CardType.values().length;
        int i, j;
        int count_types = 0, count_columns = 0;
        Optional<CardType> cardType;

        boolean[] typeFound = new boolean[ntypes];

        for(j = 0; j < ncolumns; j++){
            Arrays.fill(typeFound, false);
            for(i = 0; i < nrows; i++){
                cardType = bookshelf.getCell(new Position(i, j));
                if(cardType.isPresent() && !typeFound[cardType.get().ordinal()]){
                    count_types++;
                }
            }
            if(count_types >= min_types && count_types <= max_types){
                count_columns++;
            }
        }

        return count_columns >= min_columns;
    }
}
