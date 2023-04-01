package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

public class SameKindNGoal implements Predicate<Bookshelf> {
    private final int n;
    private final int[] count_types;

    public SameKindNGoal(int n) {
        this.n = n;
        count_types = new int[CardType.values().length];
        // initialize the counters
        Arrays.fill(count_types, 0);
    }

    @Override
    public boolean test(Bookshelf bookshelf) {
        int nrows = bookshelf.getRows();
        int ncolumns = bookshelf.getColumns();
        Optional<CardType> card;
        Position position = new Position();
        int row_idx, col_idx;

        for(row_idx = 0; row_idx < nrows; row_idx++){
            for(col_idx = 0; col_idx < ncolumns; col_idx++){
                position.setRow(row_idx);
                position.setColumn(col_idx);
                card = bookshelf.getCell(position);
                // card.ifPresent(cardType -> count_types[cardType.ordinal()]++);
                if(card.isPresent()){
                    if(++count_types[card.get().ordinal()] >= n){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
