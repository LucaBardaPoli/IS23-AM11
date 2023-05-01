package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.NetworkSettings;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.client.LaunchClient;
import it.polimi.ingsw.network.message.*;

import java.util.*;

public class TUIView implements View {
    private ClientController clientController;
    private Board board;
    private Map<String, Bookshelf> bookshelves;
    private List<CommonGoal> commonGoals;
    private PersonalGoal personalGoal;
    private List<Tile> pickedTiles;
    private Map<String, Integer> points;
    private String currentPlayer;
    private final Scanner scanner;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

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
        this.pickedTiles = new ArrayList<>();
    }

    private void printTile(Tile tile) {
        switch(tile) {
            case BLUE:
                System.out.print(ANSI_BLUE + "\u25A0" + ANSI_RESET);
                break;
            case PINK:
                System.out.print(ANSI_PURPLE + "\u25A0" + ANSI_RESET);
                break;
            case WHITE:
                System.out.print(ANSI_WHITE + "\u25A0" + ANSI_RESET);
                break;
            case YELLOW:
                System.out.print(ANSI_YELLOW + "\u25A0" + ANSI_RESET);
                break;
            case LBLUE:
                System.out.print(ANSI_CYAN + "\u25A0" + ANSI_RESET);
                break;
            case GREEN:
                System.out.print(ANSI_GREEN + "\u25A0" + ANSI_RESET);
                break;
            case EMPTY:
                System.out.print(ANSI_BLACK + "\u25A0" + ANSI_RESET);
                break;
        }
    }

    private void showBoard() {
        System.out.println("\nBoard:");

        // Fist row with indexes
        System.out.print("  ");
        for(int i=-3; i<=5; i++) {
            if(i >= 0) {
                System.out.print(" " + i + " ");
            } else {
                System.out.print(i + " ");
            }
        }
        System.out.print("\n");

        // Board
        for(int i=0; i<=8; i++) {
            System.out.print(i + " ");
            for(int j=-3; j<=5; j++) {
                Position p = new Position(i, j);
                if(this.board.getBoard().containsKey(p)) {
                    System.out.print(" ");
                    printTile(this.board.getBoard().get(p));
                    System.out.print(" ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.print("\n");
        }
    }

    public void updateBoard(Board board) {
        this.board = board;
        showBoard();
    }

    public void updateBookshelf(String player, Bookshelf bookshelf) {
        this.bookshelves.replace(player, bookshelf);
        showBookshelf(player);
    }

    public void chooseTypeOfConnection() {
        System.out.println("Insert the type of connection: ");
        String connection = this.scanner.nextLine();
        LaunchClient.openConnection(connection, NetworkSettings.SERVER_NAME, this);
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
        showBoard();
        showBookshelf(this.clientController.getClient().getNickname());

        System.out.println("\nCommon goals: ");
        for(CommonGoal c : this.commonGoals) {
            System.out.println(c);
        }

        System.out.println("\nYour personal goal: ");
        System.out.println(this.personalGoal);
    }

    public void startGame(Board board, List<CommonGoal> commonGoals, PersonalGoal personalGoal, String nextPlayer) {
        System.out.println("Game started...");
        setTable(board, commonGoals, personalGoal);
        showTable();
        this.currentPlayer = nextPlayer;
        startTurn(nextPlayer);
    }

    public void setPlayers(List<String> players) {
        this.bookshelves = new HashMap<>();
        this.points = new HashMap<>();
        for(String player : players) {
            this.bookshelves.put(player, new Bookshelf());
            this.points.put(player, 0);
        }
    }

    public void showPickATile() {
        //System.out.println("Pick a tile. Type P (x, y) to pick a tile, U (x, y to unpick a tile, C to confirm the picked tiles, S to show table: ");
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

    public void showValidUnpick() {
        System.out.println("Tile unpicked successfully!");
    }

    public void showInvalidUnpick() {
        System.out.println("Tile not unpickable!");
    }

    private void showPickedTiles() {
        System.out.println("Picked tiles: ");
        for(int i=0; i<this.pickedTiles.size(); i++) {
            System.out.print(i + " ");
        }
        System.out.print("\n");
        for(Tile t : this.pickedTiles) {
            printTile(t);
            System.out.print(" ");
        }
        System.out.print("\n");
    }

    public void showBookshelf(String player) {
        System.out.println("\n" + player + "'s bookshelf: ");

        // Fist row with indexes
        for(int i=0; i<=4; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.print("\n");

        // Bookshelf
        for(int i=0; i<=5; i++) {
            for(int j=0; j<=4; j++) {
                System.out.print(" ");
                printTile(this.bookshelves.get(player).getTile(new Position(i, j)));
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    public void updatePickedTiles(List<Tile> pickedTiles) {
        if(this.currentPlayer.equals(this.clientController.getClient().getNickname())) {
            this.pickedTiles = pickedTiles;
            showPickedTiles();
        }
    }

    public void showChooseColumn() {
        if(this.currentPlayer.equals(this.clientController.getClient().getNickname())) {
            System.out.println("Choose the column where to insert the picked tiles: ");
            int column = this.scanner.nextInt();
            this.clientController.getClient().sendMessage(new ConfirmColumnRequest(column));
        }
    }

    public void showValidColumn() {
        System.out.println("Column selected!");
    }

    public void showInvalidColumn() {
        System.out.println("Invalid column!");
    }

    public void showSwapTilesOrder() {
        System.out.println("Swap the tiles (Type C to confirm the order, S to show table): ");
        String s = this.scanner.next();
        if(s.equals("C")) {
            this.clientController.sendMessage(new ConfirmOrderNotify());
        } else if(s.equals("S")) {
            showTable();
            showSwapTilesOrder();
        } else {
            this.clientController.sendMessage(new SwapTilesOrderRequest(Integer.parseInt(s)));
        }
    }

    public void showValidSwap() {
        System.out.println("Tiles swapped!");
    }

    public void showInvalidSwap() {
        System.out.println("Invalid index!");
    }

    private void showPoints() {
        System.out.println("Points: ");
        for(String player : this.points.keySet()) {
            System.out.println(player + ": " + this.points.get(player));
        }
    }

    public void updatePoints(String player, int points) {
        this.points.replace(player, points);
        showPoints();
    }

    public void startTurn(String player) {
        System.out.println(player + "'s turn");
        if(player.equals(this.clientController.getClient().getNickname())) {
            showPickATile();
        }
        this.currentPlayer = player;
    }

    public void endTurn() {
        System.out.println(this.currentPlayer + "'s turn is over!");
    }
}
