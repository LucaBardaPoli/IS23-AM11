package it.polimi.ingsw.model.goals;

import it.polimi.ingsw.model.*;

import java.io.Serializable;
import java.util.function.Predicate;

public class SameKindGroupsGoal implements Predicate<Bookshelf>, Serializable {
    // checks if there are at least min_groups of at least min_size tiles
    private final int min_groups;
    private final int min_size;

    public SameKindGroupsGoal(int min_groups, int min_size) {
        this.min_groups = min_groups;
        this.min_size = min_size;
    }

    /**
     * Recursive method that checks for adjacent tiles (tile group)
     * @param bookshelf bookshelf to execute the check on
     * @param visited flag that needs to keep track of the visited tiles
     * @param i x coordinate
     * @param j y coordinate
     * @return group size
     */
    private int floodFill(Bookshelf bookshelf, boolean[][] visited, int i, int j) {

        if (i < 0 || i >= GameSettings.ROWS || j < 0 || j >= GameSettings.COLUMNS) {
            return 0;
        }

        if (visited[i][j]) {
            return 0;
        }

        visited[i][j] = true;

        // empty cell
        if(bookshelf.getTile(new Position(i, j)).equals(Tile.EMPTY)){
            return 0;
        }
        Tile referenceColor = bookshelf.getTile(new Position(i, j));

        int groupSize = 1;

        Tile currentColor;

        currentColor = bookshelf.getTile(new Position(i - 1, j));
        if(!currentColor.equals(Tile.EMPTY) && currentColor == referenceColor){
            groupSize += floodFill(bookshelf, visited, i - 1, j);
        }

        currentColor = bookshelf.getTile(new Position(i + 1, j));
        if(!currentColor.equals(Tile.EMPTY) && currentColor == referenceColor){
            groupSize += floodFill(bookshelf, visited, i + 1, j);
        }

        currentColor = bookshelf.getTile(new Position(i, j - 1));
        if(!currentColor.equals(Tile.EMPTY) && currentColor == referenceColor){
            groupSize += floodFill(bookshelf, visited, i, j - 1);
        }

        currentColor = bookshelf.getTile(new Position(i, j + 1));
        if(!currentColor.equals(Tile.EMPTY) && currentColor == referenceColor){
            groupSize += floodFill(bookshelf, visited, i, j + 1);
        }

        return groupSize;
    }

    /**
     * Test method of the predicate
     * @param bookshelf the input argument
     * @return true whether the goal is achieved
     */
    @Override
    public boolean test(Bookshelf bookshelf) {
        int count = 0;
        int nrows, ncols, groupSize;
        nrows = GameSettings.ROWS;
        ncols = GameSettings.COLUMNS;
        boolean[][] visited = new boolean[nrows][ncols];

        for (int i = 0; i < nrows; i++) {
            for (int j = 0; j < ncols; j++) {
                if (!visited[i][j]) {
                    groupSize = floodFill(bookshelf, visited, i, j);
                    if (groupSize >= min_size) {
                        count++;
                    }
                }
            }
        }

        return (count >= min_groups);
    }
}
