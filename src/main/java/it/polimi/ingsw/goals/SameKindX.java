package it.polimi.ingsw.goals;

import it.polimi.ingsw.model.*;

import java.util.Optional;
import java.util.function.Predicate;

public class SameKindX implements Predicate<Bookshelf> {
    private boolean checkX(Integer startRow, Integer startColumn, Bookshelf bookshelf) {
        Optional<Card> card;
        CardType currentType;

        card = bookshelf.getCell(new Position(startRow, startColumn)).getCard();
        if(card.isPresent()) {
            currentType = card.get().getType();

            card = bookshelf.getCell(new Position(startRow - 1, startColumn - 1)).getCard();
            if (card.isPresent()) {
                if (!card.get().getType().equals(currentType)) {
                    return false;
                }
            } else {
                return false;
            }

            card = bookshelf.getCell(new Position(startRow - 1, startColumn + 1)).getCard();
            if (card.isPresent()) {
                if (!card.get().getType().equals(currentType)) {
                    return false;
                }
            } else {
                return false;
            }

            card = bookshelf.getCell(new Position(startRow + 1, startColumn - 1)).getCard();
            if (card.isPresent()) {
                if (!card.get().getType().equals(currentType)) {
                    return false;
                }
            } else {
                return false;
            }

            card = bookshelf.getCell(new Position(startRow + 1, startColumn + 1)).getCard();
            if (card.isPresent()) {
                if (!card.get().getType().equals(currentType)) {
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
        for(int r = 1; r < bookshelf.getColumns()-1; r++){
            for(int c = 1; c < bookshelf.getRows()-1; c++) {
                if(checkX(r, c, bookshelf)) {
                    return true;
                }
            }
        }
        return false;
    }
}
