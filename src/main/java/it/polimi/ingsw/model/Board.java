package it.polimi.ingsw.model;

import java.util.HashMap;
import java.util.Optional;

public class Board {
    private HashMap<Position,Optional<Card>> board;
    private Integer flag; //valore che determina se una casella è utilizzabile o meno in base al numero di giocatori

    /*
    Cellflag potential values:  valori dipendono dal numero giocatori
    -5: celle non valide: esclusivamente a scopo grafico
    -2: celle usate in ogni partita
    -3: celle usate se e solo se i giocatori sono un numero minore o uguale a 3
    -4: celle usate se e solo se i giocatori sono un numero minore o uguale a 4
     */

    public Board(Integer numPlayers) {
        board = new HashMap<Position,Optional<Card>>();
        createBoardCells(numPlayers);
    }

    /*
    riempe la board tenendo conto del fatto che ci saranno gia delle carte al suo interno residue,
        del numero dei giocatori e del numero di carte residue nel sacchetto per ogni colore
     */
    public void fillBoard (Integer numPlayers, CountCards countCards){

        /* SVOLGE LE SEGUENTI OPERAZIONI:
        - itera su tutte le BoardCell della board
        - controlla se nella BoardCell sia gia presente una carta o meno e se la Board Cell è utilizzabile in questa partita

         */
        for (HashMap.Entry<Position, Optional<Card>> entry : board.entrySet()) {
            if(entry.getValue().isEmpty() && validPick(entry.getKey())){
                board.put(entry.getKey(), Optional.ofNullable(countCards.PickCard()));
            }
        }
    }

    //crea un optional con la carta presente nella board nella posizione passata in input
    // se e solo se la carta è in una posizone VALIDA!!!!!!
    public Optional<Card> getCard(Position position){

        if(validPick(position)){
            return board.get(position);
        }
        else return Optional.empty();
    }

    // controlla se la board va re-fillata
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

    //restituisce TRUE se la carta è selezionabile controllando che ci sia almeno una cella libera intorno alla cella
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


    //controlla che la cella considerata non sia nulla e sia modificabile e non solo estetica
    public boolean checkAllConditions(Position position){
        if( checkValidCell(position) && board.get(position).isPresent()){
            return true;
        }
        else return false;
    }


    //restituisce TRUE se la posizione position è una cella dove si possono mettere carte
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
            board.put(new Position(0,0), Optional.empty());
            board.put(new Position(0,1), Optional.empty());

            //RIGA 1:
            board.put(new Position(1,0), Optional.empty());
            board.put(new Position(1,1), Optional.empty());
            board.put(new Position(1,2), Optional.empty());

            //RIGA 2:
            board.put(new Position(2,-1), Optional.empty());
            board.put(new Position(2,0), Optional.empty());
            board.put(new Position(2,2), Optional.empty());
            board.put(new Position(2,3), Optional.empty());

            //RIGA 3:
            board.put(new Position(3,-2), Optional.empty());
            board.put(new Position(3,-1), Optional.empty());
            board.put(new Position(3,1), Optional.empty());
            board.put(new Position(3,2), Optional.empty());
            board.put(new Position(3,3), Optional.empty());
            board.put(new Position(3,5), Optional.empty());

            //RIGA 4:
            board.put(new Position(4,-3), Optional.empty());
            board.put(new Position(4,-2), Optional.empty());
            board.put(new Position(4,-1), Optional.empty());
            board.put(new Position(4,0), Optional.empty());
            board.put(new Position(4,1), Optional.empty());
            board.put(new Position(4,3), Optional.empty());
            board.put(new Position(4,4), Optional.empty());
            board.put(new Position(4,5), Optional.empty());

            //RIGA 5:
            board.put(new Position(5,-3), Optional.empty());
            board.put(new Position(5,-2), Optional.empty());
            board.put(new Position(5,-1), Optional.empty());
            board.put(new Position(5,0), Optional.empty());
            board.put(new Position(5,1), Optional.empty());
            board.put(new Position(5,2), Optional.empty());
            board.put(new Position(5,3), Optional.empty());
            board.put(new Position(5,4), Optional.empty());

            //RIGA 6:
            board.put(new Position(6,-1), Optional.empty());
            board.put(new Position(6,0), Optional.empty());
            board.put(new Position(6,2), Optional.empty());
            board.put(new Position(6,3), Optional.empty());

            //RIGA 7:
            board.put(new Position(7,0), Optional.empty());
            board.put(new Position(7,1), Optional.empty());
            board.put(new Position(7,2), Optional.empty());

            //RIGA 8:
            board.put(new Position(8,1), Optional.empty());
            board.put(new Position(8,2), Optional.empty());
        }


        if(numPlayers == 3){
            board.put(new Position(0,0), Optional.empty());

            //RIGA 1:
            board.put(new Position(1,0), Optional.empty());
            board.put(new Position(1,1), Optional.empty());

            //RIGA 2:
            board.put(new Position(2,-1), Optional.empty());
            board.put(new Position(2,0), Optional.empty());
            board.put(new Position(2,2), Optional.empty());
            board.put(new Position(2,3), Optional.empty());

            //RIGA 3:
            board.put(new Position(3,-1), Optional.empty());
            board.put(new Position(3,1), Optional.empty());
            board.put(new Position(3,2), Optional.empty());
            board.put(new Position(3,3), Optional.empty());
            board.put(new Position(3,5), Optional.empty());

            //RIGA 4:
            board.put(new Position(4,-2), Optional.empty());
            board.put(new Position(4,-1), Optional.empty());
            board.put(new Position(4,0), Optional.empty());
            board.put(new Position(4,1), Optional.empty());
            board.put(new Position(4,3), Optional.empty());
            board.put(new Position(4,4), Optional.empty());

            //RIGA 5:
            board.put(new Position(5,-3), Optional.empty());
            board.put(new Position(5,-2), Optional.empty());
            board.put(new Position(5,-1), Optional.empty());
            board.put(new Position(5,0), Optional.empty());
            board.put(new Position(5,1), Optional.empty());
            board.put(new Position(5,2), Optional.empty());
            board.put(new Position(5,3), Optional.empty());

            //RIGA 6:
            board.put(new Position(6,-1), Optional.empty());
            board.put(new Position(6,0), Optional.empty());
            board.put(new Position(6,2), Optional.empty());
            board.put(new Position(6,3), Optional.empty());

            //RIGA 7:
            board.put(new Position(7,1), Optional.empty());
            board.put(new Position(7,2), Optional.empty());

            //RIGA 8:
            board.put(new Position(8,2), Optional.empty());
        }

        if(numPlayers == 2){

            //RIGA 1:
            board.put(new Position(1,0), Optional.empty());
            board.put(new Position(1,1), Optional.empty());

            //RIGA 2:
            board.put(new Position(2,0), Optional.empty());
            board.put(new Position(2,2), Optional.empty());

            //RIGA 3:
            board.put(new Position(3,-1), Optional.empty());
            board.put(new Position(3,1), Optional.empty());
            board.put(new Position(3,2), Optional.empty());
            board.put(new Position(3,3), Optional.empty());

            //RIGA 4:
            board.put(new Position(4,-2), Optional.empty());
            board.put(new Position(4,-1), Optional.empty());
            board.put(new Position(4,0), Optional.empty());
            board.put(new Position(4,1), Optional.empty());
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
            board.put(new Position(6,2), Optional.empty());

            //RIGA 7:
            board.put(new Position(7,1), Optional.empty());
            board.put(new Position(7,2), Optional.empty());

        }

    }
}
