package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.CardType;
import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.Position;

import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.List;

public class GivenPositionsGoal implements Predicate<Bookshelf> {
    private final List<Position> positions;

    public GivenPositionsGoal(List<Position> positions) {
        this.positions = new ArrayList<>(positions);
    }

    @Override
    public boolean test(Bookshelf bookshelf) {
        Optional<CardType> card;
        CardType cardTypeReference, cardType;

        // if there are no positions to check the goal is fulfilled for any board
        if(positions.isEmpty()){
            return  true;
        }

        card = bookshelf.getCell(positions.get(0));
        if(card.isEmpty()){
            return false;
        }
        cardTypeReference = card.get();

        for(Position position: positions){

            card = bookshelf.getCell(position);
            if(card.isEmpty()){
                return false;
            }
            cardType = card.get();
            if(cardType != cardTypeReference){
                return false;
            }
        }
        return true;
    }
}
