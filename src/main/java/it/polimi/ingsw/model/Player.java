package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Player {
    private final String nickname;
    private PersonalGoal personalGoal;
    private Game game;
    private Bookshelf bookshelf;
    private Integer personalGoalPoints;
    private Integer commonGoalPoints;
    private Integer adjacentPoints;
    private  List<Boolean> commonGoalFullfilled;


    public Player(String nickname, PersonalGoal personalGoal, Game game) {
        this.nickname = nickname;
        this.personalGoal = personalGoal;
        this.game = game;
        this.personalGoalPoints = 0;
        this.commonGoalPoints = 0;
        this.adjacentPoints = 0;
        this.bookshelf = new Bookshelf(6,5);
        this.commonGoalFullfilled = new ArrayList<>(List.of(false, false));
    }

    public String getNickname() {
        return nickname;
    }

    public PersonalGoal getPersonalGoal() {
        return personalGoal;
    }

    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    private boolean isFullBookshelf(){
        if(this.bookshelf.getFreeCells() == 0){
            return false;
        }
        else return true;
    }

    public void insertCards(Integer column, List<CardType> cardList){
        this.bookshelf.addCells(cardList, column);
    }

    public Integer getFreeCells(Integer column){
        return this.bookshelf.getFreeCells(column);
    }

    private Optional<Integer> checkPersonalGoal(){
        Integer calculatedPoints = this.personalGoal.checkGoal(this.bookshelf);

        if(calculatedPoints == 0) return Optional.empty();
        else return Optional.of(calculatedPoints);
    }

    public boolean checkBookshelf(List<CommonGoal> commonGoals) {
        /* Checking Personal Goal Points */
        Optional<Integer> points = Optional.of(this.personalGoal.checkGoal(this.bookshelf));
        points.ifPresent(integer -> this.personalGoalPoints = integer);

        // Checking Common Goal Points
        for(int i = 0; i < 2; i++) {
            if(!this.commonGoalFullfilled.get(i)) {
                if(commonGoals.get(i).checkGoal(this.bookshelf)) {
                    this.commonGoalPoints += this.game.winToken(i);
                    this.commonGoalFullfilled.set(i, true);
                }
            }
        }
        // Checking Adjacency
        this.adjacentPoints = checkAdjacency(this.bookshelf);


        // Checking if Bookshelf is full
        return this.isFullBookshelf();

    }

    public Integer getPoints() {
        return this.adjacentPoints + this.commonGoalPoints + this.personalGoalPoints;
    }

    /**
     Static method that checks if the player has in his bookshelf adjacency Goals
     @param bshelf is the bookshelf of the player
     @return the score based on the number of adjacencies present in the player's bookshelf
     */
    public static int checkAdjacency(Bookshelf bshelf) {
        //int numGroup = 0;
        int adjacencyPoints = 0;

        int numRow = bshelf.getRows();
        int numColumn = bshelf.getColumns();

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
     @param visited is a matrix of bool which takes into account whether  a Card has been visited or not
     @param row is the index of the row of the card that should be checked
     @param column is the index of the column of the Card that should be checked
     @param type is the type of the card that should be checked
     @return the size of the group with cards of the same type
     */
    private static int findAdjacentGroupCards(Bookshelf bshelf, boolean[][] visited, int row, int column, Optional<CardType> type) {

        Position posToCheck = new Position(row, column);

        // Check out of bounds + Alredy Visited
        if ( (row < 0 || row >= 6 || column < 0 || column >= 5) || visited[row][column]) {
            return 0;
        } else if( bshelf.getCell(posToCheck).isEmpty() || type.isEmpty()){
            return 0;
        } else if( bshelf.getCell(posToCheck).get() != type.get() ){
            return 0;
        }

        visited[row][column] = true;

        int groupSize = 1;


        // Visit locations adjacent to the position provided - ( it doesn't check for the diagonal e.g. row +-1, column +-1 && row+-1, column -+ 1)
        groupSize += findAdjacentGroupCards(bshelf, visited, row - 1, column, type);
        groupSize += findAdjacentGroupCards(bshelf, visited, row + 1, column, type);
        groupSize += findAdjacentGroupCards(bshelf, visited, row, column - 1, type);
        groupSize += findAdjacentGroupCards(bshelf, visited, row, column + 1, type);

        return groupSize;
    }

}

