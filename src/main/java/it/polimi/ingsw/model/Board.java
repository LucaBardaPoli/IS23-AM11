package it.polimi.ingsw.model;

import java.util.HashMap;
import java.util.Optional;

public class Board {
    private HashMap<Position,Optional<Card>> board;
    private Integer flag; //value that determines if a square is usable or not based on the number of players

    /*
    Cellflag potential values: values depend on the number of players
    -5: Invalid Cells: For graphics purposes only
    -2: cells used in each game
    -3: cells used if and only if the number of players is less than or equal to 3
    -4: cells used if and only if the number of players is less than or equal to 4
     */
    public Board(Integer numPlayers) {
        board = new HashMap<Position,Optional<Card>>();
        createBoardCells(numPlayers);
    }

    /*
     fills the board taking into account the fact that there will already be cards left inside it,
         the number of players and the number of remaining cards in the bag for each color
      */
    public void fillBoard (Integer numPlayers, CountCards countCards){

        /* CARRY OUT THE FOLLOWING OPERATIONS:
        - iterates on all the BoardCells of the board
        - checks whether or not a card is already present in the BoardCell and whether the BoardCell is usable in this game
         */
        for (HashMap.Entry<Position, Optional<Card>> entry : board.entrySet()) {
            if(entry.getValue().isEmpty() && validPick(entry.getKey())){
                board.put(entry.getKey(), Optional.ofNullable(countCards.PickCard()));
            }
        }
    }

    //creates an optional with the card present on the board in the position passed in input
    // if and only if the card is in a VALID position!!!!!!
    public Optional<Card> getCard(Position position){

        if(validPick(position)){
            return board.get(position);
        }
        else return Optional.empty();
    }

    // check if the board needs to be re-filled
    public boolean validBoard (){

        int flag = 0;

        for (HashMap.Entry<Position, Optional<Card>> entry : board.entrySet()) {
            if(entry.getValue().isPresent() && validPick(entry.getKey())){
                flag = 1;
            }
        }
        if(flag == 1){
            return true;
        }

        else return false;

    }

    //returns TRUE if the card is selectable by checking that there is at least one free cell around the cell
    public boolean validPick(Position position){
        int flag = 0;
        int row = position.getRow();;
        int column = position.getColumn();

        position.setRow(row + 1);
        if(checkValidCell(position) && board.get(position).isEmpty()){
            flag = 1;
        }

        position.setRow(row -1);
        if(checkValidCell(position) && board.get(position).isEmpty()){
            flag = 1;
        }

        position.setRow(row);
        position.setColumn(column + 1);
        if(checkValidCell(position) && board.get(position).isEmpty()){
            flag = 1;
        }

        position.setColumn(column - 1);
        if(checkValidCell(position) && board.get(position).isEmpty()){
            flag = 1;
        }

        if (flag == 1 ) {
            return true;
        }

        else return false;
    }


    // check that the considered cell is not null and is modifiable and not just aesthetic
    public boolean checkAllConditions(Position position){
        if( checkValidCell(position) && board.get(position).isPresent()){
            return true;
        }
        else return false;
    }


    //returns TRUE if the position position is a cell where cards can be placed
    public boolean checkValidCell (Position position){
        int row = position.getRow();;
        int column = position.getColumn();

        if( (row == 2 && column == 1) || (row == 3 && column == 0) || (row == 3 && column == 4) || (row == 6 && column == 1) || (row == 4 && column == 2)){
            return false;
        }
        else return true;
    }


    public void createBoardCells(Integer numPlayers){

        if(numPlayers == 4){
            //RIGA 0:
            board.put(new Position(0,0),null);
            board.put(new Position(0,1),null);

            //RIGA 1:
            board.put(new Position(1,0),null);
            board.put(new Position(1,1),null);
            board.put(new Position(1,2),null);

            //RIGA 2:
            board.put(new Position(2,-1),null);
            board.put(new Position(2,0),null);
            board.put(new Position(2,2),null);
            board.put(new Position(2,3),null);

            //RIGA 3:
            board.put(new Position(3,-2),null);
            board.put(new Position(3,-1),null);
            board.put(new Position(3,1),null);
            board.put(new Position(3,2),null);
            board.put(new Position(3,3),null);
            board.put(new Position(3,5),null);

            //RIGA 4:
            board.put(new Position(4,-3),null);
            board.put(new Position(4,-2),null);
            board.put(new Position(4,-1),null);
            board.put(new Position(4,0),null);
            board.put(new Position(4,1),null);
            board.put(new Position(4,3),null);
            board.put(new Position(4,4),null);
            board.put(new Position(4,5),null);

            //RIGA 5:
            board.put(new Position(5,-3),null);
            board.put(new Position(5,-2),null);
            board.put(new Position(5,-1),null);
            board.put(new Position(5,0),null);
            board.put(new Position(5,1),null);
            board.put(new Position(5,2),null);
            board.put(new Position(5,3),null);
            board.put(new Position(5,4),null);

            //RIGA 6:
            board.put(new Position(6,-1),null);
            board.put(new Position(6,0),null);
            board.put(new Position(6,2),null);
            board.put(new Position(6,3),null);

            //RIGA 7:
            board.put(new Position(7,0),null);
            board.put(new Position(7,1),null);
            board.put(new Position(7,2),null);

            //RIGA 8:
            board.put(new Position(8,1),null);
            board.put(new Position(8,2),null);
        }


        if(numPlayers == 3){
            board.put(new Position(0,0),null);

            //RIGA 1:
            board.put(new Position(1,0),null);
            board.put(new Position(1,1),null);

            //RIGA 2:
            board.put(new Position(2,-1),null);
            board.put(new Position(2,0),null);
            board.put(new Position(2,2),null);
            board.put(new Position(2,3),null);

            //RIGA 3:
            board.put(new Position(3,-1),null);
            board.put(new Position(3,1),null);
            board.put(new Position(3,2),null);
            board.put(new Position(3,3),null);
            board.put(new Position(3,5),null);

            //RIGA 4:
            board.put(new Position(4,-2),null);
            board.put(new Position(4,-1),null);
            board.put(new Position(4,0),null);
            board.put(new Position(4,1),null);
            board.put(new Position(4,3),null);
            board.put(new Position(4,4),null);

            //RIGA 5:
            board.put(new Position(5,-3),null);
            board.put(new Position(5,-2),null);
            board.put(new Position(5,-1),null);
            board.put(new Position(5,0),null);
            board.put(new Position(5,1),null);
            board.put(new Position(5,2),null);
            board.put(new Position(5,3),null);

            //RIGA 6:
            board.put(new Position(6,-1),null);
            board.put(new Position(6,0),null);
            board.put(new Position(6,2),null);
            board.put(new Position(6,3),null);

            //RIGA 7:
            board.put(new Position(7,1),null);
            board.put(new Position(7,2),null);

            //RIGA 8:
            board.put(new Position(8,2),null);
        }

        if(numPlayers == 2){

            //RIGA 1:
            board.put(new Position(1,0),null);
            board.put(new Position(1,1),null);

            //RIGA 2:
            board.put(new Position(2,0),null);
            board.put(new Position(2,2),null);

            //RIGA 3:
            board.put(new Position(3,-1),null);
            board.put(new Position(3,1),null);
            board.put(new Position(3,2),null);
            board.put(new Position(3,3),null);

            //RIGA 4:
            board.put(new Position(4,-2),null);
            board.put(new Position(4,-1),null);
            board.put(new Position(4,0),null);
            board.put(new Position(4,1),null);
            board.put(new Position(4,3),null);
            board.put(new Position(4,4),null);

            //RIGA 5:
            board.put(new Position(5,-2),null);
            board.put(new Position(5,-1),null);
            board.put(new Position(5,0),null);
            board.put(new Position(5,1),null);
            board.put(new Position(5,2),null);
            board.put(new Position(5,3),null);

            //RIGA 6:
            board.put(new Position(6,0),null);
            board.put(new Position(6,2),null);

            //RIGA 7:
            board.put(new Position(7,1),null);
            board.put(new Position(7,2),null);

        }

    }
}
