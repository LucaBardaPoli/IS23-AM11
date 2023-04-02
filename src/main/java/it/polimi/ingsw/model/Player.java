package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Player {
    private final String nickname;
    private final PersonalGoal personalGoal;
    private final Game game;
    private Bookshelf bookshelf;
    private Integer personalGoalPoints;
    private Integer commonGoalPoints;
    private Integer adjacentPoints;
    private boolean endGamePoint;
    private final  List<Boolean> commonGoalFulfilled;

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
     * Insert the selected Cards in the bookshelf
     * @param column where the card should be placed
     * @param cardList of the selected cards
     */
    public void insertCards(Integer column, List<CardType> cardList){
        this.bookshelf.addCells(cardList, column);
    }

    /**
     * Getter of the free cells in a certain column of the bookshelf
     * @param column where to check
     * @return number of free cells in a certain column of the bookshelf
     */
    public Integer getFreeCells(Integer column){
        return this.bookshelf.getFreeCells(column);
    }

    /**
     * Check the progress of a player's goals & adjacency and update their score based on this.
     * @param commonGoals List of common goals that should be completed
     * @return  boolean depending on whether the player has or not filled his bookshelf
     */
    public boolean checkBookshelf(List<CommonGoal> commonGoals) {
        /* Checking Personal Goal Points */
        Optional<Integer> points = Optional.of(this.personalGoal.checkGoal(this.bookshelf));
        points.ifPresent(integer -> this.personalGoalPoints = integer);

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
    public Integer getPoints() {
        Integer points = 0;
        if(this.endGamePoint) {
            points += 1;
        }
        return points + this.adjacentPoints + this.commonGoalPoints + this.personalGoalPoints;
    }

    /**
     Static method that checks if the player has in his bookshelf adjacency Goals
     @param bshelf is the bookshelf of the player
     @return the score based on the number of adjacency present in the player's bookshelf
     */
    public int checkAdjacency(Bookshelf bshelf) {
        //int numGroup = 0;
        int adjacencyPoints = 0;

        int numRow = Bookshelf.getRows();
        int numColumn = Bookshelf.getColumns();

        boolean[][] visited = new boolean[numRow][numColumn];

        for (int i = 0; i < numRow; i++) {
            for (int j = 0; j < numColumn; j++) {
                if (!visited[i][j]) {
                    int groupSize = findAdjacentGroupCards(bshelf, visited, i, j, bshelf.getCell(new Position(i,j)));
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
     Finds the Adjacent Cards present in the bookshelf
     @param bshelf is the bookshelf of the player
     @param visited is a matrix of bool which takes into account whether a Card has been visited or not
     @param row is the index of the row of the card that should be checked
     @param column is the index of the column of the Card that should be checked
     @param type is the type of the card that should be checked
     @return the size of the group with cards of the same type
     */
    private int findAdjacentGroupCards(Bookshelf bshelf, boolean[][] visited, int row, int column, Optional<CardType> type) {

        Position posToCheck = new Position(row, column);

        // Check out of bounds + Already Visited
        if ( (row < 0 || row >= Bookshelf.getRows() || column < 0 || column >= Bookshelf.getColumns()) || visited[row][column]) {
            return 0;
        } else if( bshelf.getCell(posToCheck).isEmpty() || type.isEmpty()){
            return 0;
        } else if( bshelf.getCell(posToCheck).get() != type.get() ){
            return 0;
        }

        visited[row][column] = true;

        int groupSize = 1;


        // Visit locations adjacent to the position provided -
        // (it doesn't check for the diagonal e.g. row +-1, column +-1 && row+-1, column -+ 1)
        groupSize += findAdjacentGroupCards(bshelf, visited, row - 1, column, type);
        groupSize += findAdjacentGroupCards(bshelf, visited, row + 1, column, type);
        groupSize += findAdjacentGroupCards(bshelf, visited, row, column - 1, type);
        groupSize += findAdjacentGroupCards(bshelf, visited, row, column + 1, type);

        return groupSize;
    }

    public Integer getPersonalGoalPoints() {
        return personalGoalPoints;
    }

    public Integer getCommonGoalPoints() {
        return commonGoalPoints;
    }

    public Integer getAdjacentPoints() {
        return adjacentPoints;
    }
}

