package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.client.LaunchClient;
import it.polimi.ingsw.network.message.*;

import java.util.*;
import java.util.stream.Collectors;

public class TUIView implements View {
    private ClientController clientController;
    private Board board;
    private Map<String, Bookshelf> bookshelves;
    private Map<CommonGoal, Integer> commonGoals;
    private PersonalGoal personalGoal;
    private List<Tile> pickedTiles;
    private Map<String, Integer> points;
    private String currentPlayer;
    private boolean changeTurn;
    private final Scanner scanner;

    /* Colors */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public TUIView() {
        this.scanner = new Scanner(System.in);
        this.showChooseTypeOfConnection();
    }

    public List<String> getPlayers() {
        return new ArrayList<>(this.points.keySet());
    }


    /* Methods to read/write on console */
    private String readText() {
        do {
            try {
                return this.scanner.nextLine();
            } catch(InputMismatchException | IndexOutOfBoundsException e) {
                if(!this.clientController.getClient().getStopConnection()) {
                    System.out.println("Invalid input! Try again:");
                }
            }
        } while(!this.clientController.getClient().getStopConnection());
        return "";
    }

    private String readWord() {
        do {
            try {
                String line = this.scanner.nextLine();
                if(line.contains(" ")) {
                    return line.substring(0, line.indexOf(" "));
                }
                return line;
            } catch(InputMismatchException | IndexOutOfBoundsException e) {
                ;
            }
        } while(!this.clientController.getClient().getStopConnection());
        return "";
    }

    private int readInt() {
        do {
            try {
                return Integer.parseInt(this.readWord());
            } catch(InputMismatchException | IndexOutOfBoundsException | NumberFormatException e) {
                if(!this.clientController.getClient().getStopConnection()) {
                    System.out.println("Invalid input! Try again:");
                }
            }
        } while(!this.clientController.getClient().getStopConnection());
        return -1;
    }


    /* Initialization methods */
    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }

    public void startGame(Board board, Map<CommonGoal, Integer> commonGoals, PersonalGoal personalGoal, String nextPlayer) {
        System.out.println("\n\nGame started\n");
        this.setTable(board, commonGoals, personalGoal);
        this.showBoard();
        this.showCommonGoals();
        this.showPersonalGoal();
        this.currentPlayer = nextPlayer;
        System.out.println(this.currentPlayer + "'s turn");
        this.showPickATile();
    }

    public void setPlayers(List<String> players) {
        this.bookshelves = new HashMap<>();
        this.points = new HashMap<>();
        for(String player : players) {
            this.bookshelves.put(player, new Bookshelf());
            this.points.put(player, 0);
        }
    }

    private void setTable(Board board, Map<CommonGoal, Integer> commonGoals, PersonalGoal personalGoal) {
        this.board = board;
        this.commonGoals = commonGoals;
        this.personalGoal = personalGoal;
        this.pickedTiles = new ArrayList<>();
    }


    /* Methods to display the items of the game */
    private void printTile(Tile tile) {
        switch(tile) {
            case BLUE:
                System.out.print(ANSI_BLUE + "\u25A0" + ANSI_RESET);
                break;
            case PINK:
                System.out.print(ANSI_PURPLE + "\u25A0" + ANSI_RESET);
                break;
            case WHITE:
                System.out.print("\u25A0");
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
        System.out.println("Board:");

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

    public void showBookshelf(String player) {
        System.out.println(player + "'s bookshelf: ");

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
        System.out.println();
    }

    private void showPersonalGoal() {
        System.out.println("Your personal goal: ");
        Position position;
        for(int i=0; i<=5; i++) {
            for(int j=0; j<=4; j++) {
                System.out.print(" ");
                position = new Position(i, j);
                if(this.personalGoal.getPositions().contains(position)) {
                    printTile(this.personalGoal.getTiles().get(this.personalGoal.getPositions().indexOf(position)));
                } else {
                    printTile(Tile.EMPTY);
                }
                System.out.print(" ");
            }
            System.out.print("\n");
        }
        System.out.println("Rewards:");
        System.out.println("Tiles | Points");
        for(Map.Entry<Integer, Integer> entry : this.personalGoal.getRewards().entrySet()) {
            System.out.println("  " + entry.getKey() + "   |   " + entry.getValue());
        }
        System.out.println();
    }

    private void showCommonGoals() {
        System.out.println("\nCommon goals: ");
        for(Map.Entry<CommonGoal, Integer> entry : this.commonGoals.entrySet()) {
            System.out.println(entry.getKey() + " -> Points: " + entry.getValue());
        }
        System.out.println();
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
        System.out.println();
    }

    private void showPoints() {
        System.out.println("Points: ");
        for(String player : this.points.keySet()) {
            System.out.println(player + ": " + this.points.get(player));
        }
        System.out.println();
    }

    private void showTable() {
        this.showBoard();
        this.showCommonGoals();
        this.showPersonalGoal();
        this.showBookshelf(this.currentPlayer);
    }


    /* Methods to update the items */
    public void updateBoard(Board board) {
        this.board = board;
    }

    public void updateBookshelf(String player, Bookshelf bookshelf) {
        this.bookshelves.replace(player, bookshelf);
    }

    public void updatePickedTiles(List<Tile> pickedTiles) {
        if(this.currentPlayer.equals(this.clientController.getClient().getNickname())) {
            this.pickedTiles = pickedTiles;
        }
    }

    public void updatePoints(String player, int points) {
        this.points.replace(player, points);
        List<String> sortedPlayers = this.points
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        Map<String, Integer> sortedPoints = new HashMap<>();
        for(String sortedPlayer : sortedPlayers) {
            sortedPoints.put(sortedPlayer, this.points.get(sortedPlayer));
        }
        this.points = sortedPoints;
    }

    public void updateCommonGoals(Map<CommonGoal, Integer> commonGoals) {
        this.commonGoals = commonGoals;
    }


    /* Methods to ask for a player's move */
    public void showChooseTypeOfConnection() {
        boolean connected = false;
        String connection;
        String serverIP;
        while(!connected) {
            System.out.println("Insert the type of connection (RMI or TCP): ");
            connection = this.readWord();
            System.out.println("Insert the server IP (Type Enter for localhost): ");
            serverIP = this.readWord();
            connected = LaunchClient.openConnection(connection, serverIP, this);
            if(!connected) {
                System.out.println("Error during connection. Try again.\n");
            }
        }
    }

    public void showChooseNickname() {
        System.out.println("Insert a nickname: ");
        String nickname = this.readWord();
        this.clientController.getClient().sendMessage(new LoginRequest(nickname));
    }

    public void showChooseNumPlayers() {
        System.out.println("Insert the number of players of the game (from 2 to 4 players allowed): ");
        int numPlayers = this.readInt();
        this.clientController.getClient().sendMessage(new NumPlayersResponse(numPlayers));
    }

    private boolean handlePick() {
        Position position = new Position();
        try {
            System.out.println("Insert the row:");
            position.setRow(this.readInt());
            System.out.println("Insert the column:");
            position.setColumn(this.readInt());
            this.clientController.sendMessage(new PickTileRequest(position));
            return true;
        } catch(NumberFormatException | InputMismatchException e) {
            System.out.println("Not a number!");
            return false;
        }
    }

    private boolean handleUnpick() {
        if(!this.pickedTiles.isEmpty()) {
            Position position = new Position();
            try {
                System.out.println("Insert the row:");
                position.setRow(this.readInt());
                System.out.println("Insert the column:");
                position.setColumn(this.readInt());
                this.clientController.sendMessage(new UnpickTileRequest(position));
                return true;
            } catch (NumberFormatException e) {
                System.out.println("Not a number!");
                return false;
            }
        } else {
            System.out.println("No tiles picked!");
            return false;
        }
    }

    private void handleChatMessage() {
        try {
            System.out.println("Type the message:");
            String message = this.readText();
            System.out.println("Type the receiver (enter to send it to everyone): ");
            String receiver = this.readWord();
            this.clientController.sendMessage(new ChatMessage(this.clientController.getClient().getNickname(), receiver, message));
        } catch(Exception e) {
            System.out.println("Error!");
        }
    }

    public void showPickATile() {
        boolean tilesPicked = false;
        boolean showError = false;

        do {
            System.out.println("Type:");
            if(this.currentPlayer.equals(this.clientController.getClient().getNickname())) {
                System.out.println("P to pick a tile.");
                if (!this.pickedTiles.isEmpty()) {
                    System.out.println("U to unpick a tile.");
                }
                System.out.println("C to confirm the picked tiles.");
                System.out.println("S to show the table.");
            }
            System.out.println("M to send a message:");
            String s = this.readWord().toUpperCase();
            if(this.currentPlayer.equals(this.clientController.getClient().getNickname())) {
                switch(s) {
                    case "P":
                        tilesPicked = this.handlePick();
                        break;
                    case "U":
                        tilesPicked = this.handleUnpick();
                        break;
                    case "C":
                        this.clientController.sendMessage(new ConfirmPickRequest());
                        tilesPicked = true;
                        break;
                    case "S":
                        this.showTable();
                        break;
                    default:
                        showError = true;
                        break;
                }
            }
            if(s.equals("M")) {
                this.handleChatMessage();
            } else if(showError && !this.changeTurn) {
                System.out.println("Not a valid command!");
            }
            showError = false;
            if(this.changeTurn) {
                this.changeTurn = false;
            }
        } while(!tilesPicked && !this.clientController.getClient().getStopConnection());
    }

    public void showChooseColumn() {
        if(this.currentPlayer.equals(this.clientController.getClient().getNickname())) {
            boolean confirmedColumn = false;

            do {
                System.out.println("Type:");
                System.out.println("The column index where to insert the picked tiles.");
                System.out.println("S to show the table.");
                System.out.println("M to send a message:");
                String s = this.readWord().toUpperCase();
                switch(s) {
                    case "S":
                        this.showPersonalGoal();
                        this.showCommonGoals();
                        this.showBookshelf(this.currentPlayer);
                        this.showPickedTiles();
                        break;
                    case "M":
                        this.handleChatMessage();
                        break;
                    default:
                        try {
                            this.clientController.sendMessage(new ConfirmColumnRequest(Integer.parseInt(s)));
                            confirmedColumn = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Not a valid command!");
                        }
                        break;
                }
            } while(!confirmedColumn && !this.clientController.getClient().getStopConnection());
        }
    }

    public void showSwapTilesOrder() {
        boolean confirmedTiles = false;

        do {
            this.showPickedTiles();
            System.out.println("Type:");
            System.out.println("The index of the picked tile that will be set as last in the list.");
            System.out.println("C to confirm the order of the picked tiles.");
            System.out.println("S to show table.");
            System.out.println("M to send a message:");
            String s = this.readWord().toUpperCase();
            switch(s) {
                case "C":
                    this.clientController.sendMessage(new ConfirmOrderNotify());
                    confirmedTiles = true;
                    break;
                case "S":
                    this.showTable();
                    break;
                case "M":
                    this.handleChatMessage();
                    break;
                default:
                    try {
                        this.clientController.sendMessage(new SwapTilesOrderRequest(Integer.parseInt(s)));
                        confirmedTiles = true;
                    } catch(NumberFormatException e) {
                        System.out.println("Not a valid command!");
                    }
                    break;
            }
        } while(!confirmedTiles && !this.clientController.getClient().getStopConnection());
    }


    /* Methods to show a move's result */
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

    public void showNoPickedTiles() {
        System.out.println("Pick at least a tile!");
    }

    public void showValidColumn() {
        System.out.println("Column selected!");
    }

    public void showInvalidColumn() {
        System.out.println("Invalid column!");
    }

    public void showValidSwap() {
        System.out.println("Tiles swapped!");
    }

    public void showInvalidSwap() {
        System.out.println("Invalid index!");
    }


    /* Methods to handle the change of a turn */
    public void startTurn(String player) {
        System.out.println(player + "'s turn");
        if(this.currentPlayer.equals(this.clientController.getClient().getNickname())) {
            this.currentPlayer = player;
            this.showPickATile();
        } else {
            this.currentPlayer = player;
            if(this.currentPlayer.equals(this.clientController.getClient().getNickname())) {
                System.out.println("Type Enter to play:");
                this.changeTurn = true;
            } else {
                System.out.println("Type:");
                System.out.println("M to send a message:");
            }
        }
    }

    public void endTurn() {
        System.out.println(this.currentPlayer + "'s turn is over!");
        System.out.println("New Board:");
        showBoard();
        System.out.println("New " + this.currentPlayer + "'s bookshelf:");
        showBookshelf(this.currentPlayer);
        showPoints();
        showCommonGoals();
    }

    public void showEndGame() {
        System.out.println("Game ended");
        System.out.println("Results:");
        for(Map.Entry<String, Integer> entry : this.points.entrySet()) {
            System.out.println(entry.getKey() + "\t: " + entry.getValue());
        }
        this.clientController.getClient().close();
    }


    /* Methods to handle chat messages */
    public void showNewChatMessageUnicast(String sender, String message) {
        System.out.println("Chat:");
        System.out.println("Message from " + sender + " to " + this.clientController.getClient().getNickname() + ": " + message);
    }

    public void showNewChatMessageBroadcast(String sender, String message) {
        System.out.println("Chat:");
        System.out.println("Message from " + sender + " to everyone: " + message);
    }


    /* Methods to show disconnection phase */
    public void showPlayerDisconnected(String disconnectedPlayer) {
        System.out.println(disconnectedPlayer + " has disconnected!");
        showDisconnection();
    }

    public void showDisconnection() {
        System.out.println("Closing the connection with the server...");
        System.out.println("Type something to close the game:");
    }
}
