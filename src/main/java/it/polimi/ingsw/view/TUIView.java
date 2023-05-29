package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.client.LaunchClient;
import it.polimi.ingsw.network.message.*;

import java.util.*;
import java.util.stream.Collectors;

public class TUIView implements View {
    private ClientController clientController;

    /* Game's items */
    private Board board;
    private Map<String, Bookshelf> bookshelves;
    private List<CommonGoal> commonGoals;
    private List<Integer> commonGoalsTokens;
    private PersonalGoal personalGoal;
    private List<Tile> pickedTiles;
    private Map<String, Integer> points;
    private String currentPlayer;

    /* Utils */
    private boolean changeTurn;
    private boolean endGame;
    private final Scanner scanner;

    /* Colors */
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_BACKGROUND_BLACK = "\033[40m";
    private final String tile_char = "██";

    /* Commands' codes */
    private static final String PICK = "P";
    private static final String UNPICK = "U";
    private static final String SHOW = "S";
    private static final String CONFIRM = "C";
    private static final String MESSAGE = "M";


    public TUIView() {
        this.scanner = new Scanner(System.in);
        this.showChooseTypeOfConnection();
    }

    public List<String> getPlayers() {
        return new ArrayList<>(this.points.keySet());
    }

    public String getCurrentPlayer() {
        return this.currentPlayer;
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

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public void startGame(Board board, List<CommonGoal> commonGoals, List<Integer> commonGoalsTokens, PersonalGoal personalGoal, String nextPlayer) {
        this.setTable(board, commonGoals, commonGoalsTokens, personalGoal);
        this.showBoard();
        this.showCommonGoals();
        this.showPersonalGoal();
        this.currentPlayer = nextPlayer;
        this.endGame = false;
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

    public void updateLobbyInfo(int lobbySize, List<String> lobby, boolean newPlayerConnected, String playerName) {
        int currLobbySize = lobby.size();
        if(newPlayerConnected) {
            System.out.println(playerName +" joined the lobby!");
        } else {
            System.out.println(playerName +" exited the lobby!");
        }
        System.out.println("Current lobby:");
        for(String p: lobby){
            System.out.println("\t- " + p);
        }
        System.out.println("Waiting for other players... (" + currLobbySize + "/" + lobbySize + ")\n");
    }

    private void setTable(Board board, List<CommonGoal> commonGoals, List<Integer> commonGoalsTokens, PersonalGoal personalGoal) {
        this.board = board;
        this.commonGoals = commonGoals;
        this.commonGoalsTokens = commonGoalsTokens;
        this.personalGoal = personalGoal;
        this.pickedTiles = new ArrayList<>();
    }


    /* Methods to display the items of the game */
    private void printTile(Tile tile) {
        switch(tile) {
            case BLUE:
                System.out.print(ANSI_BLUE + this.tile_char + ANSI_RESET);
                break;
            case PINK:
                System.out.print(ANSI_PURPLE + this.tile_char + ANSI_RESET);
                break;
            case WHITE:
                System.out.print(this.tile_char);
                break;
            case YELLOW:
                System.out.print(ANSI_YELLOW + this.tile_char + ANSI_RESET);
                break;
            case LBLUE:
                System.out.print(ANSI_CYAN + this.tile_char + ANSI_RESET);
                break;
            case GREEN:
                System.out.print(ANSI_GREEN + this.tile_char + ANSI_RESET);
                break;
            case EMPTY:
                System.out.print(ANSI_BLACK + this.tile_char + ANSI_RESET);
                break;
        }
    }

    private void showBoard() {
        System.out.println("Board:");

        // Fist row with indexes
        System.out.print(ANSI_BACKGROUND_BLACK + "  ");
        for(int i=0; i<=8; i++) {
            System.out.print(ANSI_BACKGROUND_BLACK + " " + i + " " + ANSI_RESET);
        }
        System.out.print("\n");

        // Board
        for(int i=0; i<=8; i++) {
            System.out.print(ANSI_BACKGROUND_BLACK + i + " " + ANSI_RESET);
            for(int j=0; j<=8; j++) {
                Position p = new Position(i, j-3);
                if(this.board.getBoard().containsKey(p)) {
                    printTile(this.board.getBoard().get(p));
                    System.out.print(ANSI_BACKGROUND_BLACK + " " + ANSI_RESET);
                } else {
                    System.out.print(ANSI_BACKGROUND_BLACK + "   " + ANSI_RESET);
                }
            }
            System.out.print("\n");
        }

        // Adjacency points
        System.out.println("Adjacency points:");
        System.out.println("Group of tiles | Points");
        System.out.println("       3       |   2");
        System.out.println("       4       |   3");
        System.out.println("       5       |   5");
        System.out.println("       6+      |   8");
    }

    private void showBookshelf(String player) {
        System.out.println(player + "'s bookshelf: ");

        // Fist row with indexes
        System.out.print(" ");
        for(int i=0; i<=4; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.print("\n");

        // Bookshelf
        for(int i=0; i<=5; i++) {
            System.out.print(" ");
            for(int j=0; j<=4; j++) {
                printTile(this.bookshelves.get(player).getTile(new Position(i, j)));
                System.out.print(" ");
            }
            System.out.print("\n");
        }
        System.out.println();
    }

    private void showPersonalGoal() {
        System.out.println("Your personal goal: ");

        // Fist row with indexes
        System.out.print(" ");
        for(int i=0; i<=4; i++) {
            System.out.print(" " + i + " ");
        }
        System.out.print("\n");

        Position position;
        for(int i=0; i<=5; i++) {
            System.out.print(" ");
            for(int j=0; j<=4; j++) {
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
        for(int i=0; i<this.commonGoals.size(); i++) {
            System.out.println(this.commonGoals.get(i) + " -> Points: " + this.commonGoalsTokens.get(i));
        }
        System.out.println();
    }

    private void showPickedTiles() {
        System.out.println("Picked tiles: ");
        for(int i=this.pickedTiles.size()-1; i>=0; i--) {
            System.out.print(i + " ");
            this.printTile(this.pickedTiles.get(i));
            System.out.print("\n");
        }

        System.out.println();
    }

    private void showPoints() {
        System.out.println("Points: ");

        List<String> sortedPlayers = this.points
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        for(String sortedPlayer : sortedPlayers) {
            System.out.println(sortedPlayer + ": " + this.points.get(sortedPlayer));
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
        this.showBoard();
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
    }

    public void updateCommonGoals(List<Integer> commonGoalsTokens) {
        this.commonGoalsTokens = commonGoalsTokens;
    }

    public void updateEndGame(boolean endGame) {
        /*


            FIX THIS


         */
        if(endGame && this.clientController.getClient().getNickname().equals(this.currentPlayer)) {
            System.out.println("Won EndGame token!");
        }
    }


    /* Methods to ask for a player's move */
    private void showLogo() {
        System.out.println(ANSI_RED + "\n" +
                "███╗   ███╗██╗   ██╗ ██████╗██╗  ██╗███████╗██╗     ███████╗██╗███████╗\n" +
                "████╗ ████║╚██╗ ██╔╝██╔════╝██║  ██║██╔════╝██║     ██╔════╝██║██╔════╝\n" +
                "██╔████╔██║ ╚████╔╝ ╚█████╗ ███████║█████╗  ██║     █████╗  ██║█████╗  \n" +
                "██║╚██╔╝██║  ╚██╔╝   ╚═══██╗██╔══██║██╔══╝  ██║     ██╔══╝  ██║██╔══╝  \n" +
                "██║ ╚═╝ ██║   ██║   ██████╔╝██║  ██║███████╗███████╗██║     ██║███████╗\n" +
                "╚═╝     ╚═╝   ╚═╝   ╚═════╝ ╚═╝  ╚═╝╚══════╝╚══════╝╚═╝     ╚═╝╚══════╝" + ANSI_RESET);
    }

    public void showChooseTypeOfConnection() {
        this.showLogo();
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
            position.setColumn(this.readInt() - 3);
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
                position.setColumn(this.readInt() - 3);
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
                System.out.println(PICK + " to pick a tile.");
                if (!this.pickedTiles.isEmpty()) {
                    System.out.println(UNPICK + " to unpick a tile.");
                }
                System.out.println(CONFIRM + " to confirm the picked tiles.");
                System.out.println(SHOW + " to show the table.");
            }
            System.out.println(MESSAGE + " to send a message:");
            String s = this.readWord().toUpperCase();
            if(this.currentPlayer.equals(this.clientController.getClient().getNickname())) {
                switch(s) {
                    case PICK:
                        tilesPicked = this.handlePick();
                        break;
                    case UNPICK:
                        tilesPicked = this.handleUnpick();
                        break;
                    case CONFIRM:
                        this.clientController.sendMessage(new ConfirmPickRequest());
                        tilesPicked = true;
                        break;
                    case SHOW:
                        this.showTable();
                        break;
                    default:
                        showError = true;
                        break;
                }
            }
            if(s.equals(MESSAGE)) {
                this.handleChatMessage();
            } else if(showError && !this.changeTurn && !this.clientController.getClient().getStopConnection()) {
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
                System.out.println(SHOW + " to show the table.");
                System.out.println(MESSAGE + " to send a message:");
                String s = this.readWord().toUpperCase();
                switch(s) {
                    case SHOW:
                        this.showPersonalGoal();
                        this.showCommonGoals();
                        this.showBookshelf(this.currentPlayer);
                        this.showPickedTiles();
                        break;
                    case MESSAGE:
                        this.handleChatMessage();
                        break;
                    default:
                        try {
                            this.clientController.sendMessage(new ConfirmColumnRequest(Integer.parseInt(s)));
                            confirmedColumn = true;
                        } catch(NumberFormatException e) {
                            if(!this.clientController.getClient().getStopConnection()) {
                                System.out.println("Not a valid command!");
                            }
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
            System.out.println(CONFIRM + " to confirm the order of the picked tiles.");
            System.out.println(SHOW + " to show table.");
            System.out.println(MESSAGE + " to send a message:");
            String s = this.readWord().toUpperCase();
            switch(s) {
                case CONFIRM:
                    this.clientController.sendMessage(new ConfirmOrderNotify());
                    confirmedTiles = true;
                    break;
                case SHOW:
                    this.showTable();
                    break;
                case MESSAGE:
                    this.handleChatMessage();
                    break;
                default:
                    try {
                        this.clientController.sendMessage(new SwapTilesOrderRequest(Integer.parseInt(s)));
                        confirmedTiles = true;
                    } catch(NumberFormatException e) {
                        if(!this.clientController.getClient().getStopConnection()) {
                            System.out.println("Not a valid command!");
                        }
                    }
                    break;
            }
        } while(!confirmedTiles && !this.clientController.getClient().getStopConnection());
    }


    /* Methods to show a move's result */
    public void showInvalidNickname() {
        System.out.println("Nickname already taken!");
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
        if(!this.endGame) {
            System.out.println(player + "'s turn");
            if(this.currentPlayer.equals(this.clientController.getClient().getNickname())) {
                this.currentPlayer = player;
                this.showPickATile();
            } else {
                this.currentPlayer = player;
                if (this.currentPlayer.equals(this.clientController.getClient().getNickname())) {
                    System.out.println("Type:");
                    System.out.println(PICK + " to pick a tile.");
                    System.out.println(CONFIRM + " to confirm the picked tiles.");
                    System.out.println(SHOW + " to show the table.");
                    System.out.println(MESSAGE + " to send a message:");
                    //System.out.println("Type Enter to play:");
                    this.changeTurn = true;
                } else {
                    System.out.println("Type:");
                    System.out.println(MESSAGE + " to send a message:");
                }
            }
        } else {
            this.changeTurn = true;
            this.showEndGame();
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

    private void showEndGame() {
        System.out.println("\n\n\n" +
                "╭━━━╮╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╭╮\n" +
                "┃╭━╮┃╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱┃┃\n" +
                "┃┃╱╰╋━━┳╮╭┳━━╮╭━━┳╮╭┳━━┳━┫┃\n" +
                "┃┃╭━┫╭╮┃╰╯┃┃━┫┃╭╮┃╰╯┃┃━┫╭┻╯\n" +
                "┃╰┻━┃╭╮┃┃┃┃┃━┫┃╰╯┣╮╭┫┃━┫┃╭╮\n" +
                "╰━━━┻╯╰┻┻┻┻━━╯╰━━╯╰╯╰━━┻╯╰╯\n");
        System.out.println("Results:");
        for(Map.Entry<String, Integer> entry : this.points.entrySet()) {
            System.out.println(entry.getKey() + "\t: " + entry.getValue());
        }
        this.clientController.getClient().close(false);
    }


    /* Methods to handle chat messages */
    public void showNewChatMessageUnicast(String sender, String message) {
        System.out.println("\nChat:");
        System.out.println("Message from " + sender + " to " + ANSI_GREEN + this.clientController.getClient().getNickname() + ANSI_RESET + ": " + message);
    }

    public void showNewChatMessageBroadcast(String sender, String message) {
        System.out.println("\nChat:");
        System.out.println("Message from " + sender + " to " + ANSI_RED + "Everyone" + ANSI_RESET + ": " + message);
    }


    /* Methods to show disconnection phase */
    public void showPlayerDisconnected(String disconnectedPlayer) {
        System.out.println("\n\n\n\n\n");
        System.out.println(ANSI_RED + "\n" +
                " █████╗ ██╗  ██╗  ███╗  ██╗ █████╗  ██╗\n" +
                "██╔══██╗██║  ██║  ████╗ ██║██╔══██╗ ██║\n" +
                "██║  ██║███████║  ██╔██╗██║██║  ██║ ██║\n" +
                "██║  ██║██╔══██║  ██║╚████║██║  ██║ ╚═╝\n" +
                "╚█████╔╝██║  ██║  ██║ ╚███║╚█████╔╝ ██╗\n" +
                " ╚════╝ ╚═╝  ╚═╝  ╚═╝  ╚══╝ ╚════╝  ╚═╝" + ANSI_RESET);
        System.out.println("\n" + disconnectedPlayer + " has disconnected!");
        this.showDisconnection();
    }

    public void showDisconnection() {
        System.out.println("Closing the connection with the server...");
        System.out.println("Type something to close the game:");
    }
}
