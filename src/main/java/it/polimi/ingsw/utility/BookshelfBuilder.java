package it.polimi.ingsw.utility;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.GameSettings;
import it.polimi.ingsw.model.Tile;

import java.util.List;
import java.util.Random;

/**
 * Utility methods used for testing
 */
public class BookshelfBuilder{
    public static Bookshelf randomBookshelf(){
        Bookshelf bookshelf = new Bookshelf();
        Random rand = new Random();
        Tile tile;

        for(int j = 0; j < GameSettings.COLUMNS; j++){
            // for each column fill the rows from the bottom
            for(int i = GameSettings.ROWS - 1; i >= 0 ; i--){
                tile = Tile.values()[Math.abs(rand.nextInt()) % Tile.values().length];
                // no colored tile can be above an empty tile (each column is a stack)
                if(tile == Tile.EMPTY){
                    break;
                }
                // the column will be full only if no empty tile is randomly selected
                bookshelf.addTiles(List.of(tile), j);
            }
        }
        return  bookshelf;
    }

    public static Bookshelf randomFullBookshelf(){
        Bookshelf bookshelf = new Bookshelf();
        Random rand = new Random();
        Tile tile;

        for(int j = 0; j < GameSettings.COLUMNS; j++){
            // for each column fill the rows from the bottom
            for(int i = GameSettings.ROWS - 1; i >= 0 ; i--){
                tile = Tile.values()[Math.abs(rand.nextInt()) % Tile.numColors];
                // this ensures that the tile cannot be empty
                bookshelf.addTiles(List.of(tile), j);
            }
        }
        return  bookshelf;
    }
}
