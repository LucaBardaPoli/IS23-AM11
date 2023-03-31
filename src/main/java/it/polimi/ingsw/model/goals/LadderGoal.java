package it.polimi.ingsw.model.goals;

import java.util.Optional;
import java.util.function.Predicate;
import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.CardType;
import it.polimi.ingsw.model.Position;

public class LadderGoal implements Predicate<Bookshelf> {
    @Override
    public boolean test(Bookshelf bookshelf) {
        Integer columns = bookshelf.getColumns();
        Integer rows = bookshelf.getRows();

        int row_idx, col_idx;

        // from left to right
        for(col_idx = 0; col_idx < columns; col_idx++){
            // from top to bottom
            for(row_idx = rows-1; row_idx >= rows-col_idx && row_idx >= 0; row_idx--){
                // check the empty cells
                if(bookshelf.getCell(new Position(row_idx, col_idx)).isPresent()){
                    return false;
                }
            }
            // there must be a card below the empty cells
            if(bookshelf.getCell(new Position(row_idx, col_idx)).isEmpty()){
                return false;
            }
        }

        return true;
    }
}
