package it.polimi.ingsw.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles all possible actions that can be performed by a single player
 */
public class Player implements Serializable {
    private final String nickname;
    private final PersonalGoal personalGoal;
    private final Game game;
    private Bookshelf bookshelf;
    private int personalGoalPoints;
    private int commonGoalPoints;
    private int adjacentPoints;
    private boolean endGamePoint;
    private final List<Boolean> commonGoalFulfilled;

    /**
     * Class Constructor
     * @param nickname of the player
     * @param personalGoal of the player that should fulfill
     * @param game in which the player will play
     */
    public Player(String nickname, PersonalGoal personalGoal, Game game) {
        this.nickname = nickname;
        this.personalGoal = personalGoal;
        this.game = game;
        this.personalGoalPoints = 0;
        this.commonGoalPoints = 0;
        this.adjacentPoints = 0;
        this.endGamePoint = false;
        this.bookshelf = new Bookshelf();
        this.commonGoalFulfilled = new ArrayList<>(List.of(false, false));
    }

    /**
     * Bookshelf setter (used for testing)
     * @param bookshelf new bookshelf
     */
    public void setBookshelf(Bookshelf bookshelf){
        this.bookshelf = bookshelf;
    }

    /**
     * Getter of Player's nickname
     * @return Player's nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Getter of Player's Personal Goal
     * @return Player's Personal Goal
     */
    public PersonalGoal getPersonalGoal() {
        return personalGoal;
    }

    /**
     * Player's Bookshelf
     * @return Player's Bookshelf
     */
    public Bookshelf getBookshelf() {
        return this.bookshelf;
    }

    /**
     * Check if the Bookshelf full or not
     * @return a Boolean that depends on the state of the library
     */
    private boolean isFullBookshelf(){
        return this.bookshelf.getFreeCells() == 0;
    }

    /**
     * Insert the selected tiles in the bookshelf
     * @param column where the tile should be placed
     * @param tiles of the selected tiles
     */
    public void insertTiles(int column, List<Tile> tiles){
        this.bookshelf.addTiles(tiles, column);
    }

    /**
     * Getter of the free cells in a certain column of the bookshelf
     * @param column where to check
     * @return number of free cells in a certain column of the bookshelf
     */
    public int getFreeCells(int column){
        return this.bookshelf.getFreeCells(column);
    }

    /**
     * Check the progress of a player's goals and adjacency and update their score based on this
     * @param commonGoals list of common goals that should be completed
     * @return boolean depending on whether the player has or not filled his bookshelf
     */
    public boolean checkBookshelf(List<CommonGoal> commonGoals) {
        /* Checking Personal Goal Points */
        this.personalGoalPoints = this.personalGoal.checkGoal(this.bookshelf);

        // Checking Common Goal Points
        for(int i = 0; i < commonGoals.size(); i++) {
            if(!this.commonGoalFulfilled.get(i)) {
                if(commonGoals.get(i).checkGoal(this.bookshelf)) {
                    this.commonGoalPoints += this.game.winToken(i);
                    this.commonGoalFulfilled.set(i, true);
                }
            }
        }

        // Checking Adjacency
        this.adjacentPoints = checkAdjacency(this.bookshelf);

        // Checking if Bookshelf is full
        if(this.isFullBookshelf()) {
            this.endGamePoint = true;
            return true;
        }
        return false;
    }

    /**
     * Getter of the points
     * @return points scored by the player (based on Common, Personal Goals and on the Adjacency)
     */
    public int getPoints() {
        int points = 0;
        if(this.endGamePoint) {
            points += 1;
        }
        return points + this.adjacentPoints + this.commonGoalPoints + this.personalGoalPoints;
    }

    /**
     * Checks if the player has in his bookshelf adjacency Goals
     * @param bookshelf is the bookshelf of the player
     * @return the score based on the number of adjacency present in the player's bookshelf
     */
    public int checkAdjacency(Bookshelf bookshelf) {
        //int numGroup = 0;
        int adjacencyPoints = 0;

        int numRow = GameSettings.ROWS;
        int numColumn = GameSettings.COLUMNS;

        boolean[][] visited = new boolean[numRow][numColumn];

        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numColumn; j++) {
                if (!visited[i][j]) {
                    int groupSize = findAdjacentGroupTiles(bookshelf, visited, i, j, bookshelf.getTile(new Position(i,j)));
                    if (groupSize >= 3) {
                        //numGroup++;

                        // Based on the size of the adjacent card group, returns the score (minimum size to earn points is 3)
                        if(groupSize == 3){
                            adjacencyPoints += 2;
                        } else if(groupSize == 4){
                            adjacencyPoints += 3;
                        } else if(groupSize == 5){
                            adjacencyPoints += 5;
                        } else adjacencyPoints += 8;

                    }
                }
            }
        }

        return adjacencyPoints;
    }

    /**
     * Finds the Adjacent tiles present in the bookshelf
     * @param bookshelf is the bookshelf of the player
     * @param visited is a matrix of bool which takes into account whether a Card has been visited or not
     * @param row is the index of the row of the card that should be checked
     * @param column is the index of the column of the Card that should be checked
     * @param type is the type of the tile that should be checked
     * @return the size of the group with tiles of the same type
     */
    private int findAdjacentGroupTiles(Bookshelf bookshelf, boolean[][] visited, int row, int column, Tile type) {

        Position posToCheck = new Position(row, column);

        // Check out of bounds + Already Visited
        if ( (row < 0 || row >= GameSettings.ROWS || column < 0 || column >= GameSettings.COLUMNS) || visited[row][column]) {
            return 0;
        } else if( bookshelf.getTile(posToCheck) == Tile.EMPTY || type.equals(Tile.EMPTY)){
            return 0;
        } else if(bookshelf.getTile(posToCheck) != type) {
            return 0;
        }

        visited[row][column] = true;

        int groupSize = 1;

        // Visit locations adjacent to the position provided -
        // (it doesn't check for the diagonal e.g. row +-1, column +-1 && row+-1, column -+ 1)
        groupSize += findAdjacentGroupTiles(bookshelf, visited, row - 1, column, type);
        groupSize += findAdjacentGroupTiles(bookshelf, visited, row + 1, column, type);
        groupSize += findAdjacentGroupTiles(bookshelf, visited, row, column - 1, type);
        groupSize += findAdjacentGroupTiles(bookshelf, visited, row, column + 1, type);

        return groupSize;
    }

    /**
     * Personal goals points getter
     * @return personal goals points
     */
    public int getPersonalGoalPoints() {
        return personalGoalPoints;
    }

    /**
     * Common goals points getter
     * @return common goals points
     */
    public int getCommonGoalPoints() {
        return commonGoalPoints;
    }

    /**
     * Adjacent points getter
     * @return adjacent points
     */
    public int getAdjacentPoints() {
        return adjacentPoints;
    }

    /**
     * End game point getter
     * @return end game point
     */
    public boolean getEndGamePoint() {
        return endGamePoint;
    }
}