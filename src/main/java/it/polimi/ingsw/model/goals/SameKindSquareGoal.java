package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Class that represents the common goal that searches for 2 2x2 squares, each one made of the same tail types
 */
public class SameKindSquareGoal implements Predicate<Bookshelf> {
    /**
     * Checks if exists a 2x2 square starting from the given position towards the bottom-right corner
     * @param startRow row of the starting point
     * @param startColumn column of the starting point
     * @param bookshelf bookshelf to execute the check on
     * @return true whether the 2x2 square exists
     */
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
            return card.map(cardType -> cardType.equals(currentType)).orElse(false);
        } else {
            return false;
        }
    }

    /**
     * Checks if exists 2 different 2x2 squares in the bookshelf
     * @param bookshelf bookshelf to execute the check on
     * @return true whether the 2 squares exists
     */
    public boolean test(Bookshelf bookshelf) {
        Optional<Integer> startRowLastSquare = Optional.empty();
        Optional<Integer> startColumnLastSquare = Optional.empty();

        for(int r = 0; r < Bookshelf.getRows()-1; r++) {
            for(int c = 0; c < Bookshelf.getColumns()-1; c++) {
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
