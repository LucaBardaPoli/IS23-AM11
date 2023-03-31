package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;

public class Board {
    private HashMap<Position, Optional<CardType>> board;

    /**
     * Class constructor
     * @param numPlayers creates a different board depending on the number of players
     */
    public Board(Integer numPlayers) {
        board = new HashMap<Position, Optional<CardType>>();
        createBoardCells(numPlayers);
    }

    /**
     * creates the board
     * @param numPlayers it creates a different board depending on the number of players
     */
    private void createBoardCells(Integer numPlayers){

        if(numPlayers >= 2) {
            //RIGA 1:
            board.put(new Position(1,0), Optional.empty());
            board.put(new Position(1,1), Optional.empty());

            //RIGA 2:
            board.put(new Position(2,0), Optional.empty());
            board.put(new Position(2,1), Optional.empty());
            board.put(new Position(2,2), Optional.empty());

            //RIGA 3:
            board.put(new Position(3,-1), Optional.empty());
            board.put(new Position(3,0), Optional.empty());
            board.put(new Position(3,1), Optional.empty());
            board.put(new Position(3,2), Optional.empty());
            board.put(new Position(3,3), Optional.empty());
            board.put(new Position(3,4), Optional.empty());

            //RIGA 4:
            board.put(new Position(4,-2), Optional.empty());
            board.put(new Position(4,-1), Optional.empty());
            board.put(new Position(4,0), Optional.empty());
            board.put(new Position(4,1), Optional.empty());
            board.put(new Position(4,2), Optional.empty());
            board.put(new Position(4,3), Optional.empty());
            board.put(new Position(4,4), Optional.empty());

            //RIGA 5:
            board.put(new Position(5,-2), Optional.empty());
            board.put(new Position(5,-1), Optional.empty());
            board.put(new Position(5,0), Optional.empty());
            board.put(new Position(5,1), Optional.empty());
            board.put(new Position(5,2), Optional.empty());
            board.put(new Position(5,3), Optional.empty());

            //RIGA 6:
            board.put(new Position(6,0), Optional.empty());
            board.put(new Position(6,1), Optional.empty());
            board.put(new Position(6,2), Optional.empty());

            //RIGA 7:
            board.put(new Position(7,1), Optional.empty());
            board.put(new Position(7,2), Optional.empty());
        }

        if(numPlayers >= 3) {
            // RIGA 0:
            board.put(new Position(0,0), Optional.empty());

            //RIGA 2:
            board.put(new Position(2,-1), Optional.empty());
            board.put(new Position(2,3), Optional.empty());

            //RIGA 3:
            board.put(new Position(3,5), Optional.empty());

            //RIGA 5:
            board.put(new Position(5,-3), Optional.empty());

            //RIGA 6:
            board.put(new Position(6,-1), Optional.empty());
            board.put(new Position(6,3), Optional.empty());

            //RIGA 8:
            board.put(new Position(8,2), Optional.empty());
        }

        if(numPlayers >= 4) {
            //RIGA 0:
            board.put(new Position(0,1), Optional.empty());

            //RIGA 1:
            board.put(new Position(1,2), Optional.empty());

            //RIGA 3:
            board.put(new Position(3,-2), Optional.empty());

            //RIGA 4:
            board.put(new Position(4,-3), Optional.empty());
            board.put(new Position(4,5), Optional.empty());

            //RIGA 5:
            board.put(new Position(5,4), Optional.empty());

            //RIGA 7:
            board.put(new Position(7,0), Optional.empty());

            //RIGA 8:
            board.put(new Position(8,1), Optional.empty());
        }
    }

    /**
     * fills the board in all the free spots
     * @param countCards counts how many cards of a given color have been created up until the moment the method is called
     */
    public void fillBoard (CountCards countCards){

        /* CARRY OUT THE FOLLOWING OPERATIONS:
        - iterates on all the BoardCells of the board
        - checks whether a card is already present in the BoardCell and whether the BoardCell is usable in this game
         */
        for (HashMap.Entry<Position, Optional<CardType>> entry : board.entrySet()) {
            if(entry.getValue().isEmpty()) {
                board.put(entry.getKey(), Optional.ofNullable(countCards.pickCard()));
            }
        }
    }

    /**
     * Returns the card of the board in the given position
     * @param position indicates the position where the card is supposed to be
     * @return return an optional of card
     */
    public Optional<CardType> getCard(Position position) {
        ArrayList<Position> keyList = new ArrayList<>(this.board.keySet());
        Optional<CardType> rtn = Optional.empty();
        for(Position p : keyList) {
            if(p.equals(position)) {
                rtn = this.board.get(p);
                break;
            }
        }
        return rtn;
    }

    /**
     * Returns the card of the board in the given position removing it from the board
     * @param position indicates the position where the card is supposed to be
     * @return return an optional of card
     */
    public Optional<CardType> pickCard(Position position) {
        if(this.board.containsKey(position) && validPick(position)) {
            ArrayList<Position> keyList = new ArrayList<>(this.board.keySet());
            Optional<CardType> rtn = Optional.empty();
            for(Position p : keyList) {
                if(p.equals(position)) {
                    rtn = this.board.get(p);
                    this.board.put(p, Optional.empty());
                    break;
                }
            }
            return rtn;
        }
        return Optional.empty();
    }

    /**
     * Sets a card in a given cell of the board (used for testing)
     * @param position postion where to place the card
     * @param card card to place on the board
     * @return true if the given position belongs to the board
     */
    public boolean setCard(Position position, CardType card) {
        if(this.board.containsKey(position)) {
            board.put(position, Optional.ofNullable(card));
            return true;
        }
        return false;
    }

    /**
     * Checks if the board needs to be refilled
     * @return true if exists (at least) two adjacent cards
     */
    public boolean hasToBeRefilled() {
        for(HashMap.Entry<Position, Optional<CardType>> entry : board.entrySet()) {
            if(entry.getValue().isPresent() && hasAdjacentCards(entry.getKey())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a given position has at least an adjacent cards on the board
     * @param position indicates the position of the current card
     * @return true if the card has at least an adjacent card
     */
    private boolean hasAdjacentCards(Position position) {
        Position p = new Position(position.getRow(), position.getColumn());
        int row = position.getRow();;
        int column = position.getColumn();

        p.setRow(row + 1);
        if(this.board.containsKey(p) && board.get(p).isPresent()) {
            return true;
        }

        p.setRow(row - 1);
        if(this.board.containsKey(p) && board.get(p).isPresent()) {
            return true;
        }

        p.setRow(row);
        p.setColumn(column + 1);
        if(this.board.containsKey(p) && board.get(p).isPresent()) {
            return true;
        }

        p.setColumn(column - 1);
        if(this.board.containsKey(p) && board.get(p).isPresent()) {
            return true;
        }

        return false;
    }

    /**
     * returns TRUE if the card is selectable by checking that there is at least one free cell around the position indicated
     * @param position indicates the position where the picked card is
     * @return return true if the card is selectable
     */
    public boolean validPick(Position position) {
        Position p = new Position(position.getRow(), position.getColumn());
        int row = position.getRow();
        int column = position.getColumn();

        if(!this.board.containsKey(p)) {
            return false;
        }

        p.setRow(row + 1);
        if(!this.board.containsKey(p) || (this.board.containsKey(p) && board.get(p).isEmpty())) {
            return true;
        }

        p.setRow(row - 1);
        if(!this.board.containsKey(p) || (this.board.containsKey(p) && board.get(p).isEmpty())) {
            return true;
        }

        p.setRow(row);
        p.setColumn(column + 1);
        if(!this.board.containsKey(p) || (this.board.containsKey(p) && board.get(p).isEmpty())) {
            return true;
        }

        p.setColumn(column - 1);
        if(!this.board.containsKey(p) || (this.board.containsKey(p) && board.get(p).isEmpty())) {
            return true;
        }

        return false;
    }
}
