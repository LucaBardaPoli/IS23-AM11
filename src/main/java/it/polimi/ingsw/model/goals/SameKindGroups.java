package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;

import java.util.Optional;
import java.util.function.Predicate;
public class SameKindGroups implements Predicate<Bookshelf>{
    // checks if there are at least min_groups of at least min_size size
    private final int min_groups;
    private final int min_size;

    public SameKindGroups(int min_groups, int min_size) {
        this.min_groups = min_groups;
        this.min_size = min_size;
    }

    private int floodFill(Bookshelf bookshelf, boolean[][] visited, int i, int j) {
        int nrows, ncols;

        nrows = Bookshelf.getRows();
        ncols = Bookshelf.getColumns();

        if (i < 0 || i >= nrows || j < 0 || j >= ncols) {
            return 0;
        }

        if (visited[i][j]) {
            return 0;
        }

        visited[i][j] = true;

        // empty cell
        if(bookshelf.getCell(new Position(i, j)).isEmpty()){
            return 0;
        }
        CardType referenceColor = bookshelf.getCell(new Position(i, j)).get();

        int groupSize = 1;

        Optional<CardType> currentColor;

        currentColor = bookshelf.getCell(new Position(i - 1, j));
        if(currentColor.isPresent() && currentColor.get() == referenceColor){
            groupSize += floodFill(bookshelf, visited, i - 1, j);
        }

        currentColor = bookshelf.getCell(new Position(i + 1, j));
        if(currentColor.isPresent() && currentColor.get() == referenceColor){
            groupSize += floodFill(bookshelf, visited, i + 1, j);
        }

        currentColor = bookshelf.getCell(new Position(i, j - 1));
        if(currentColor.isPresent() && currentColor.get() == referenceColor){
            groupSize += floodFill(bookshelf, visited, i, j - 1);
        }

        currentColor = bookshelf.getCell(new Position(i, j + 1));
        if(currentColor.isPresent() && currentColor.get() == referenceColor){
            groupSize += floodFill(bookshelf, visited, i, j + 1);
        }

        return groupSize;
    }

    @Override
    public boolean test(Bookshelf bookshelf) {
        int count = 0;
        int nrows, ncols;
        nrows = bookshelf.getRows();
        ncols = bookshelf.getColumns();
        boolean[][] visited = new boolean[nrows][ncols];

        for (int i = 0; i < nrows; i++) {
            for (int j = 0; j < ncols; j++) {
                if (!visited[i][j]) {
                    int groupSize = floodFill(bookshelf, visited, i, j);
                    if (groupSize >= min_size) {
                        count++;
                    }
                }
            }
        }

        return (count >= min_groups);
    }
}
