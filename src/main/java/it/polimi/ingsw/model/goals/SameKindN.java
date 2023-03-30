package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;

import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Predicate;

public class SameKindN implements Predicate<Bookshelf> {
    private final int n;
    private final Map<CardType, Integer> count_card;

    public SameKindN(int n) {
        this.n = n;
        count_card = new HashMap<>();
        // initialize the counters
        for(CardType ct: CardType.values()){
            count_card.put(ct, 0);
        }
    }

    @Override
    public boolean test(Bookshelf bookshelf) {
        Integer rows = Bookshelf.getRows();
        Integer columns = Bookshelf.getColumns();
        Optional<CardType> card;
        Position position = new Position();
        int row_idx, col_idx;

        for(row_idx = 0; row_idx < rows; row_idx++){
            for(col_idx = 0; col_idx < columns; col_idx++){
                position.setRow(row_idx);
                position.setColumn(col_idx);
                card = bookshelf.getCell(position);
                card.ifPresent(value -> count_card.computeIfPresent(value, (k, v) -> v + 1));
                if(card.isPresent()){
                    if(count_card.get(card.get()) >= n){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
