package it.polimi.ingsw.model.goals;

import java.util.Optional;
import java.util.function.Predicate;
import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.CardType;
import it.polimi.ingsw.model.Position;

public class LadderGoal implements Predicate<Bookshelf> {

    @Override
    public boolean test(Bookshelf bookshelf) {
        return testDescendingLadder(0, 0, bookshelf) ||
                testDescendingLadder(1, 0, bookshelf) ||
                testAscendingLadder(5, 0, bookshelf) ||
                testAscendingLadder(4, 0, bookshelf);
    }

    /**
     * Checks if exists a descending ladder starting from the given position towards the bottom-right corner
     * @param startRow row of the starting point
     * @param startColumn column of the starting point
     * @param bookshelf bookshelf to execute the check on
     * @return true whether the descending ladder exists
     */
    private boolean testDescendingLadder(int startRow, int startColumn, Bookshelf bookshelf) {
        int limit = Math.min(Bookshelf.getColumns(), Bookshelf.getRows());
        Optional<CardType> card;

        card = bookshelf.getCell(new Position(startRow, startColumn));
        if(card.isPresent() && bookshelf.getCell(new Position(startRow-1, startColumn)).isEmpty()) {
            for (int i = 1; i < limit; i++) {
                card = bookshelf.getCell(new Position(startRow+i, startColumn+i));
                if (card.isEmpty() || bookshelf.getCell(new Position(startRow+i-1, startColumn+i)).isPresent()) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }

    /**
     * Checks if exists a ascending ladder starting from the given position towards the top-right corner
     * @param startRow row of the starting point
     * @param startColumn column of the starting point
     * @param bookshelf bookshelf to execute the check on
     * @return true whether the ascending ladder exists
     */
    private boolean testAscendingLadder(int startRow, int startColumn, Bookshelf bookshelf) {
        int limit = Math.min(Bookshelf.getColumns(), Bookshelf.getRows());
        Optional<CardType> card;

        card = bookshelf.getCell(new Position(startRow, startColumn));
        if(card.isPresent() && bookshelf.getCell(new Position(startRow-1, startColumn)).isEmpty()) {
            for (int i = 1; i < limit; i++) {
                card = bookshelf.getCell(new Position(startRow-i, startColumn+i));
                if (card.isEmpty() || bookshelf.getCell(new Position(startRow-i-1, startColumn+i)).isPresent()) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }
}
