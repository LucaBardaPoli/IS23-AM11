package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;

import java.util.Optional;
import java.util.function.Predicate;

public class SameKindDiagonal implements Predicate<Bookshelf> {

    private boolean checkDiagonal(Integer startRow, Integer startColumn, Bookshelf bookshelf) {
        Integer limit = Math.min(bookshelf.getColumns(), bookshelf.getRows());
        Optional<Card> card;
        CardType currentType;

        card = bookshelf.getCell(new Position(startRow, startColumn)).getCard();
        if(card.isPresent()) {
            currentType = card.get().getType();
            for (int i = 0; i < limit; i++) {
                card = bookshelf.getCell(new Position(startRow+i, startColumn+i)).getCard();
                if (card.isPresent()) {
                    if (!card.get().getType().equals(currentType)) {
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
