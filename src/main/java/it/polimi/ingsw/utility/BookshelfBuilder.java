package it.polimi.ingsw.utility;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.GameSettings;
import it.polimi.ingsw.model.Tile;

import java.util.List;
import java.util.Random;

/**
 * method used exclusively for testing
 */
public class BookshelfBuilder{
    public static Bookshelf randomFullBookshelf(){
        Bookshelf bookshelf = new Bookshelf();
        Random rand = new Random();

        for(int i = 0; i < GameSettings.COLUMNS; i++){
            for(int j = 0; j < GameSettings.ROWS; j++){
                bookshelf.addTiles(List.of(Tile.values()[Math.abs(rand.nextInt()) % Tile.values().length]), i);
            }
        }
        return  bookshelf;
    }
}
