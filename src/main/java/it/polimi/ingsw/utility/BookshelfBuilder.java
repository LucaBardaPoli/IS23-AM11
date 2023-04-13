package it.polimi.ingsw.utility;

import it.polimi.ingsw.model.Bookshelf;
import it.polimi.ingsw.model.CardType;

import java.util.List;
import java.util.Random;

public class BookshelfBuilder{
    public static Bookshelf randomFullBookshelf(){
        Bookshelf bookshelf = new Bookshelf();
        Random rand = new Random();

        for(int i = 0; i < Bookshelf.getColumns(); i++){
            for(int j = 0; j < Bookshelf.getRows(); j++){
                bookshelf.addCells(List.of(CardType.values()[Math.abs(rand.nextInt()) % CardType.values().length]), i);
            }
        }
        return  bookshelf;
    }
}
