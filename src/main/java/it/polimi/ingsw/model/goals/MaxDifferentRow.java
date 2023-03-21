package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;

import java.util.Optional;
import java.util.function.Predicate;

public class MaxDifferentRow implements Predicate<Bookshelf> {
    private boolean checkRow(Integer row, Bookshelf bookshelf) {
        Integer columns = bookshelf.getColumns();
        Optional<CardType> card1, card2;

        if (bookshelf.getRow(row).size() < columns) {
            return false;
        }
        for (int c1 = 0; c1 < columns - 1; c1++) {
            card1 = bookshelf.getCell(new Position(row, c1));
            if (card1.isEmpty()) {
                return false;
            } else {
                for (int c2 = c1 + 1; c2 < columns; c2++) {
                    card2 = bookshelf.getCell(new Position(row, c2));
                    if (card2.isEmpty()) {
                        return false;
                    } else {
                        if (card2.get().equals(card1.get())) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public boolean test(Bookshelf bookshelf) {
        Integer rows = bookshelf.getRows();
        boolean foundOne = false;

        for(int r = 0; r < rows; r++) {
            if(checkRow(r, bookshelf)) {
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
