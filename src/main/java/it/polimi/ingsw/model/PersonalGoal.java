package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.*;

public class PersonalGoal implements Serializable {
    private final List<Position> positions;
    private final List<Tile> tiles;
    private final Map<Integer, Integer> rewards;

    /**
     * Class constructor
     * @param positions positions to check
     * @param tiles tiles to check
     * @param rewards number of points to assign
     */
    public PersonalGoal(List<Position> positions, List<Tile> tiles, Map<Integer, Integer> rewards) {
        this.positions = new LinkedList<>(positions);
        this.tiles = new LinkedList<>(tiles);
        this.rewards = new HashMap<>(rewards);
    }

    public List<Position> getPositions() {
        return positions;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public Map<Integer, Integer> getRewards() {
        return rewards;
    }

    /**
     * Checks whether the goal is fulfilled or not
     * @param bookshelf bookshelf to check
     * @return the number of points won
     */
    public int checkGoal(Bookshelf bookshelf){
        int n_tiles = 0;
        Tile tile;
        for(int index = 0; index < positions.size(); index++){
            tile = bookshelf.getTile(positions.get(index));
            // CardType is an enumeration so equality corresponds with identity
            if(!tile.equals(Tile.EMPTY) && tile == tiles.get(index)){
                n_tiles++;
            }
        }

        return n_tiles > 0 ? rewards.get(n_tiles): 0;
    }
}
