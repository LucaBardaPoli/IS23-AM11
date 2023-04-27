package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.client.LaunchClient;
import it.polimi.ingsw.network.message.ConfirmPickNotify;
import it.polimi.ingsw.network.message.LoginRequest;
import it.polimi.ingsw.network.message.NumPlayersResponse;
import it.polimi.ingsw.network.message.PickTileRequest;

import java.util.*;
import java.util.stream.Collectors;

public class TUIView implements View {
    private ClientController clientController;
    private Board board;
    private List<CommonGoal> commonGoals;
    private PersonalGoal personalGoal;
    private final Scanner scanner;

    public TUIView() {
        super();
        this.scanner = new Scanner(System.in);
    }

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }

    public void setTable(Board board, List<CommonGoal> commonGoals, PersonalGoal personalGoal) {
        this.board = board;
        this.commonGoals = commonGoals;
        this.personalGoal = personalGoal;
    }
    public void chooseTypeOfConnection() {
        System.out.println("Insert the type of connection: ");
        String connection = this.scanner.nextLine();
        LaunchClient.openConnection(connection, "127.0.0.1", this);
    }

    public void chooseUsername() {
        System.out.println("Insert a nickname: ");
        String nickname = this.scanner.nextLine();
        this.clientController.getClient().sendMessage(new LoginRequest(nickname));
    }

    public void chooseNumPlayers() {
        System.out.println("Insert the number of players of the game: ");
        int numPlayers = this.scanner.nextInt();
        this.clientController.getClient().sendMessage(new NumPlayersResponse(numPlayers));
    }

    private void showTable() {
        System.out.println("\nBoard:");
        List<Position> sortedKeySet = this.board.getBoard().keySet().stream().sorted((p1, p2) -> ((p1.getRow() < p2.getRow()) ? -1 : ((p1.getRow() > p2.getRow()) ? 1 : (Integer.compare(p1.getColumn(), p2.getColumn()))))).collect(Collectors.toList());
        for(Position p : sortedKeySet) {
            System.out.println(p + " -> " + (this.board.getBoard().get(p) != Tile.EMPTY ? this.board.getBoard().get(p) : "empty"));
        }

        System.out.println("\nCommon goals: ");
        for(CommonGoal c : this.commonGoals) {
            System.out.println(c);
        }

        System.out.println("\nYour personal goal: ");
        System.out.println(this.personalGoal);
    }

    public void startGame(Board board, List<CommonGoal> commonGoals, PersonalGoal personalGoal, boolean isYourTurn) {
        System.out.println("Game started...");
        setTable(board, commonGoals, personalGoal);
        showTable();
        if(isYourTurn) {
            System.out.println("Your turn!");
            showPickATile();
        } else {
            System.out.println("Waiting for the first player to start playing...");
        }
    }

    public void showPickATile() {
        System.out.println("Pick a tile (Type C to confirm the picked tiles, S to show table): ");
        String s = this.scanner.next();
        if(s.equals("C")) {
            this.clientController.sendMessage(new ConfirmPickNotify());
        } else if(s.equals("S")) {
            showTable();
            showPickATile();
        } else {
            Position position = new Position();
            position.setRow(Integer.parseInt(s));
            position.setColumn(scanner.nextInt());
            this.clientController.sendMessage(new PickTileRequest(position));
        }
    }

    public void showValidPick() {
        System.out.println("Tile picked successfully!");
    }
    public void showInvalidPick() {
        System.out.println("Tile not pickable!");
    }
}
