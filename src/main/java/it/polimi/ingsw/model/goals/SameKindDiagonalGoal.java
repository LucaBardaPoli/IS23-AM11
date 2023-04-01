package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;

import java.util.Optional;
import java.util.function.Predicate;

public class SameKindDiagonalGoal implements Predicate<Bookshelf> {

    private boolean checkDiagonal(Integer startRow, Integer startColumn, Bookshelf bookshelf) {
        Integer limit = Math.min(Bookshelf.getColumns(), Bookshelf.getRows());
        Optional<CardType> card;
        CardType currentType;

        card = bookshelf.getCell(new Position(startRow, startColumn));
        if(card.isPresent()) {
            currentType = card.get();
            for (int i = 0; i < limit; i++) {
                card = bookshelf.getCell(new Position(startRow+i, startColumn+i));
                if (card.isPresent()) {
                    if (!card.get().equals(currentType)) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    public boolean test(Bookshelf bookshelf) {
        return checkDiagonal(0, 0, bookshelf) || checkDiagonal(1, 0, bookshelf);
    }
}
