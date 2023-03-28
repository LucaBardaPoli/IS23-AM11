package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;

import java.util.Optional;
import java.util.function.Predicate;

public class SameKindX implements Predicate<Bookshelf> {
    private boolean checkX(Integer startRow, Integer startColumn, Bookshelf bookshelf) {
        Optional<CardType> card;
        CardType currentType;

        card = bookshelf.getCell(new Position(startRow, startColumn));
        if(card.isPresent()) {
            currentType = card.get();

            card = bookshelf.getCell(new Position(startRow - 1, startColumn - 1));
            if (card.isPresent()) {
                if (!card.get().equals(currentType)) {
                    return false;
                }
            } else {
                return false;
            }

            card = bookshelf.getCell(new Position(startRow - 1, startColumn + 1));
            if (card.isPresent()) {
                if (!card.get().equals(currentType)) {
                    return false;
                }
            } else {
                return false;
            }

            card = bookshelf.getCell(new Position(startRow + 1, startColumn - 1));
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
        } else {
            return false;
        }

        return true;
    }

    public boolean test(Bookshelf bookshelf) {
        for(int r = 1; r < bookshelf.getRows()-1; r++){
            for(int c = 1; c < bookshelf.getColumns()-1; c++) {
                if(checkX(r, c, bookshelf)) {
                    return true;
                }
            }
        }
        return false;
    }
}
