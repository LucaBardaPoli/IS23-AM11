package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;

import java.util.Optional;
import java.util.function.Predicate;

public class SameKindSquare implements Predicate<Bookshelf> {
    private boolean checkSquare(Integer startRow, Integer startColumn, Bookshelf bookshelf) {
        Optional<CardType> card;
        CardType currentType;

        card = bookshelf.getCell(new Position(startRow, startColumn));
        if(card.isPresent()) {
            currentType = card.get();

            card = bookshelf.getCell(new Position(startRow + 1, startColumn));
            if (card.isPresent()) {
                if (!card.get().equals(currentType)) {
                    return false;
                }
            } else {
                return false;
            }

            card = bookshelf.getCell(new Position(startRow + 1, startColumn + 1));
            if (card.isPresent()) {
                if (!card.get().equals(currentType)) {
                    return false;
                }
            } else {
                return false;
            }

            card = bookshelf.getCell(new Position(startRow, startColumn + 1));
            if (card.isPresent()) {
                if (!card.get().equals(currentType)) {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    public boolean test(Bookshelf bookshelf) {
        Optional<Integer> startRowLastSquare = Optional.empty();
        Optional<Integer> startColumnLastSquare = Optional.empty();

        for(int r = 0; r < bookshelf.getRows()-1; r++) {
            for(int c = 0; c < bookshelf.getColumns()-1; c++) {
                if(checkSquare(r, c, bookshelf)) {
                    if(startRowLastSquare.isEmpty()) {
                        startRowLastSquare = Optional.of(r);
                        startColumnLastSquare = Optional.of(c);
                    } else {
                        if(Math.abs(r - startRowLastSquare.get()) >= 2 || Math.abs(c - startColumnLastSquare.get()) >= 2) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
