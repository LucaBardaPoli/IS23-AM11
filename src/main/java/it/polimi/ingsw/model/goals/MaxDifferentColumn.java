package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;

import java.util.Optional;
import java.util.function.Predicate;

public class MaxDifferentColumn implements Predicate<Bookshelf> {
    private boolean checkColumn(Integer column, Bookshelf bookshelf) {
        Integer rows = bookshelf.getRows();
        Optional<Card> card1, card2;

        if (bookshelf.getRow(column).size() < rows) {
            return false;
        }
        for (int r1 = 0; r1 < rows - 1; r1++) {
            card1 = bookshelf.getCell(new Position(r1, column)).getCard();
            if (card1.isEmpty()) {
                return false;
            } else {
                for (int r2 = r1 + 1; r2 < rows; r2++) {
                    card2 = bookshelf.getCell(new Position(r2, column)).getCard();
                    if (card2.isEmpty()) {
                        return false;
                    } else {
                        if (card2.get().getType().equals(card1.get().getType())) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean test(Bookshelf bookshelf) {
        Integer columns = bookshelf.getColumns();
        boolean foundOne = false;

        for(int c = 0; c < columns; c++) {
            if(checkColumn(c, bookshelf)) {
                if(foundOne) {
                    return true;
                } else {
                    foundOne = true;
                }
            }
        }
        return false;
    }
}