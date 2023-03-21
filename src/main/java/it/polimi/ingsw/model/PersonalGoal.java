package it.polimi.ingsw.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;
import java.util.Optional;

public class PersonalGoal {
    // positions and cardTypes must have the same size
    private final List<Position> positions;
    private final List<CardType> cardTypes;
    // the keys must start from 1 up to position.size()
    private final Map<Integer, Integer> rewards;
    // maps the nubmer of correct cards into the corresponding points reward

    public PersonalGoal(List<Position> positions, List<CardType> cardTypes, Map<Integer, Integer> rewards) {
        this.positions = new LinkedList<Position>(positions);
        this.cardTypes = new LinkedList<CardType>(cardTypes);
        this.rewards = new HashMap<Integer, Integer>(rewards);
    }

    public int checkGoal(Bookshelf bookshelf){
        int n_cards = 0;
        Cell cell;
        for(int index = 0; index < positions.size(); index++){
            cell = bookshelf.getCell(positions.get(index));
            // CardType is an enumeration so equality corresponds with identity
            if(!cell.getCard().isPresent() && cell.getCard().get().getType() == cardTypes.get(index)){
                n_cards++;
            }
        }

        return n_cards > 0? rewards.get(n_cards): 0;
    }
}
