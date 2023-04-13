package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;

import java.util.Optional;
import java.util.function.Predicate;

/**
 * Class that represents the common goal that searches for a group of tails shaped as an x, each one of the same type
 */
public class SameKindXGoal implements Predicate<Bookshelf> {
    /**
     * Checks if exists a group of tails shaped as an x starting from the given position towards the bottom-right corner
     * @param startRow of the starting point
     * @param startColumn column of the starting point
     * @param bookshelf bookshelf to execute the check on
     * @return true whether the x exists
     */
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

    /**
     * Checks if exists a group of tails shaped as an x in every cell of the bookshelf
     * @param bookshelf the input argument
     * @return true whether the gruop exists
     */
    public boolean test(Bookshelf bookshelf) {
        for(int r = 1; r < Bookshelf.getRows()-1; r++){
            for(int c = 1; c < Bookshelf.getColumns()-1; c++) {
                if(checkX(r, c, bookshelf)) {
                    return true;
                }
            }
        }
        return false;
    }
}
