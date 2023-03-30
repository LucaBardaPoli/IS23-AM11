package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.CardType;
import it.polimi.ingsw.model.Position;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public class DifferentTypesRow implements Predicate<Bookshelf> {
    // the minimum numbers of different types that have to be on a row for it to be counted
    private final int min_types;
    // the maximum numbers of different types that have to be on a row for it to be counted
    private final int max_types;
    // the minimum numbers of counted rows required for the goal to be fulfilled
    private final int min_rows;

    public DifferentTypesRow(int minTypes, int maxTypes, int minRows) {
        min_types = minTypes;
        max_types = maxTypes;
        min_rows = minRows;
    }

    @Override
    public boolean test(Bookshelf bookshelf) {
        int nrows = Bookshelf.getRows();
        int ncolumns = Bookshelf.getColumns();
        int ntypes = CardType.values().length;
        int i, j;
        int count_types = 0, count_rows = 0;
        Optional<CardType> cardType;
        // the correspondence between card types and array index is given by the ordinal() method of the enum CardType
        boolean[] typeFound = new boolean[ntypes];

        for(i = 0; i < nrows; i++){
            // at the start of each row no types have been found, yet
            Arrays.fill(typeFound, false);
            for(j = 0; j < ncolumns; j++){
                cardType = bookshelf.getCell(new Position(i, j));
                if(cardType.isPresent() && !typeFound[cardType.get().ordinal()]){
                    count_types++;
                }
            }
            if(count_types >= min_types && count_types <= max_types){
                count_rows++;
            }
        }

        return count_rows >= min_rows;
    }
}
