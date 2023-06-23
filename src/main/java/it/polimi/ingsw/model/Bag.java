package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.concurrent.ThreadLocalRandom;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class that handles the tiles of the game
 */
public class Bag implements Serializable {
    private final LinkedHashMap<Tile, Integer> bag;
    private int currentNumberOfTiles;

    /**
     * Class constructor
     */
    public Bag() {
        bag = new LinkedHashMap<>();
        bag.put(Tile.GREEN, GameSettings.MAX_NUM_CARDS_PER_TYPE);
        bag.put(Tile.WHITE, GameSettings.MAX_NUM_CARDS_PER_TYPE);
        bag.put(Tile.YELLOW, GameSettings.MAX_NUM_CARDS_PER_TYPE);
        bag.put(Tile.BLUE, GameSettings.MAX_NUM_CARDS_PER_TYPE);
        bag.put(Tile.LBLUE, GameSettings.MAX_NUM_CARDS_PER_TYPE);
        bag.put(Tile.PINK, GameSettings.MAX_NUM_CARDS_PER_TYPE);

        this.currentNumberOfTiles = GameSettings.MAX_NUM_CARDS_PER_TYPE * (Tile.numColors);
    }

    /**
     * Picks a new tile from the bag
     * Generates a random number from 0 to the current number of tiles left to pick
     * Based on which interval, given by the number of tiles left for each type, the number belongs it generates the new tile
     * @return new picked tile
     */
    public Tile pickTile() {
        //keeps track of the interval of cards we're currently checking
        Integer count = 0;
        boolean foundInterval = false;

        Tile newTile = null;
        int randomNumber = ThreadLocalRandom.current().nextInt(0, this.currentNumberOfTiles + 1);

        for (Map.Entry<Tile, Integer> entry : bag.entrySet()) {
            //update the counter value
            count += entry.getValue();

            if(randomNumber <= count && !foundInterval) {
                //generate the new tile
                newTile = entry.getKey();

                //decreases the total number of cards left
                this.currentNumberOfTiles--;

                //modifies the map element with the specified key
                bag.put(entry.getKey(), bag.get(entry.getKey()) - 1);

                //raises the flag to communicate that the new tile has been generated
                foundInterval = true;
            }
        }
        return newTile;
    }

    /**
     * Getter of the bag attribute
     * @return returns the bag
     */
    public LinkedHashMap<Tile, Integer> getBag() {
        return bag;
    }
}






