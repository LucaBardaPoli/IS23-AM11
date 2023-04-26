package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.Tile;
import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.Position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.List;

public class GivenPositionsGoal implements Predicate<Bookshelf>, Serializable {
    private final List<Position> positions;

    public GivenPositionsGoal(List<Position> positions) {
        this.positions = new ArrayList<>(positions);
    }

    @Override
    public boolean test(Bookshelf bookshelf) {
        Optional<Tile> optionalTile;
        Tile tileReference, tile;

        // if there are no positions to check the goal is fulfilled for any board
        if(positions.isEmpty()){
            return  true;
        }

        optionalTile = bookshelf.getTile(positions.get(0));
        if(optionalTile.isEmpty()){
            return false;
        }
        tileReference = optionalTile.get();

        for(Position position: positions){

            optionalTile = bookshelf.getTile(position);
            if(optionalTile.isEmpty()){
                return false;
            }
            tile = optionalTile.get();
            if(tile != tileReference){
                return false;
            }
        }
        return true;
    }
}
