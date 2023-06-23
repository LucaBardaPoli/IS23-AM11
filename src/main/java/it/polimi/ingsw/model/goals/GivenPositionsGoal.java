package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.Position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.List;

public class GivenPositionsGoal implements Predicate<Bookshelf>, Serializable {
    private final List<Position> positions;

    public GivenPositionsGoal(List<Position> positions) {
        this.positions = new ArrayList<>(positions);
    }

    /**
     * Test method of the predicate
     * @param bookshelf the input argument
     * @return true whether the goal is achieved
     */
    @Override
    public boolean test(Bookshelf bookshelf) {
        Tile optionalTile;
        Tile tileReference, tile;

        // if there are no positions to check the goal is fulfilled for any board
        if(positions.isEmpty()){
            return  true;
        }

        optionalTile = bookshelf.getTile(positions.get(0));
        if(optionalTile.equals(Tile.EMPTY)){
            return false;
        }
        tileReference = optionalTile;

        for(Position position: positions){

            optionalTile = bookshelf.getTile(position);
            if(optionalTile.equals(Tile.EMPTY)){
                return false;
            }
            tile = optionalTile;
            if(tile != tileReference){
                return false;
            }
        }
        return true;
    }
}
