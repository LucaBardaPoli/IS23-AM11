package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Player {
    private String nickname;
    private PersonalGoal personalGoal;
    private Game game;
    private Bookshelf bookshelf;
    private Integer points;
    private List<Boolean> commonGoalFullfilled;

    public Player(String nickname, PersonalGoal personalGoal, Game game) {
        this.nickname = nickname;
        this.personalGoal = personalGoal;
        this.game = game;
        this.points = 0;
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

    private void addPoints(Integer points){
        this.points += points;
    }

    private boolean isFullBookshelf(){
        if(this.bookshelf.getFreeCells() == 0) return false;
        return true;
    }

    public void insertCards(Integer column, List<Card> cardList){
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

    public boolean checkBookshelf(List<CommonGoal> commonGoals){

        Optional<Integer> points = Optional.of(this.personalGoal.checkGoal(this.bookshelf));

        if(!points.isEmpty()) {
            this.addPoints(points.get());
        }

        for(int i = 0; i < 2; i++) {
            if(!this.commonGoalFullfilled.get(i)) {
                Optional<Token> pointsCommon = commonGoals.get(i).checkGoal(this.bookshelf);
                if (pointsCommon.isPresent()) {
                    this.addPoints(pointsCommon.get().getValue());
                    this.commonGoalFullfilled.set(i, true);
                }
            }
        }

        if(this.isFullBookshelf()) return true;

        return false;
    }
}
