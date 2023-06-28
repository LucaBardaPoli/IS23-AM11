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
    private boolean isLastTurn;
    private boolean endGame;

    /* Utils */
    private boolean changeTurn;
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

    /**
     * Class constructor
     */
    public TUIView() {
        this.scanner = new Scanner(System.in);
        this.showChooseTypeOfConnection();
    }

    /**
     * Players getter
     * @return players
     */
    public List<String> getPlayers() {
        return new ArrayList<>(this.points.keySet());
    }

    /**
     * Current player getter
     * @return current player
     */
    public String getCurrentPlayer() {
        return this.currentPlayer;
    }

    /**
     * Picked tiles getter
     * @return picked tiles
     */
    public List<Tile> getPickedTiles() {
        return this.pickedTiles;
    }

    /* Methods to read/write on console */
    /**
     * Reads a line from console
     * @return read line
     */
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

    /**
     * Reads a word from console
     * @return read word
     */
    private String readWord() {
        do {
            try {
                String line = this.scanner.nextLine();
                if(line.contains(" ")) {
                    return line.substring(0, line.indexOf(" "));
                }
                return line;
            } catch(InputMismatchException | IndexOutOfBoundsException ignored) {
            }
        } while(!this.clientController.getClient().getStopConnection());
        return "";
    }

    /**
     * Reads an integer value from console
     * @return read integer value
     */
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
    /**
     * Client controller setter
     * @param clientController new client controller
     */
    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }

    /**
     * Methods that saves the information about the new game which is about to start
     * @param board new board
     * @param commonGoals new common goals
     * @param commonGoalsTokens new common goals tokens
     * @param personalGoal new personal goals
     * @param nextPlayer next player to play
     */
    public void startGame(Board board, List<CommonGoal> commonGoals, List<Integer> commonGoalsTokens, PersonalGoal personalGoal, String nextPlayer) {
        this.setTable(board, commonGoals, commonGoalsTokens, personalGoal);
        this.showBoard();
        this.showCommonGoals();
        this.showPersonalGoal();
        this.currentPlayer = nextPlayer;
        this.isLastTurn = false;
        this.endGame = false;
        System.out.println(this.currentPlayer + "'s turn");
        this.showPickATile();
    }

    /**
     * Players setter
     * @param players new list of players
     */
    public void setPlayers(List<String> players) {
        this.bookshelves = new HashMap<>();
        this.points = new HashMap<>();
        for(String player : players) {
            this.bookshelves.put(player, new Bookshelf());
            this.points.put(player, 0);
        }
    }

    /**
     * Handles the lobby before the start of the game
     * @param lobbySize size of the lobby
     * @param lobby lobby of the game
     * @param newPlayerConnected true if connected, false if disconnected
     * @param playerName name of the player
     */
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

    /**
     * Table setter
     * @param board new board
     * @param commonGoals new common goals
     * @param commonGoalsTokens new common goals tokens
     * @param personalGoal new personal goal
     */
    private void setTable(Board board, List<CommonGoal> commonGoals, List<Integer> commonGoalsTokens, PersonalGoal personalGoal) {
        this.board = board;
        this.commonGoals = commonGoals;
        this.commonGoalsTokens = commonGoalsTokens;
        this.personalGoal = personalGoal;
        this.pickedTiles = new ArrayList<>();
    }


    /* Methods to display the items of the game */
    /**
     * Prints the given tile on console
     * @param tile to print
     */
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

    /**
     * Shows the board
     */
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

    /**
     * Shows the bookshelf
     */
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

    /**
     * Shows the personal goal
     */
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

    /**
     * Shows the common goals
     */
    private void showCommonGoals() {
        System.out.println("\nCommon goals: ");
        for(int i=0; i<this.commonGoals.size(); i++) {
            System.out.println(this.commonGoals.get(i) + " -> Points: " + this.commonGoalsTokens.get(i));
        }
        System.out.println();
    }

    /**
     * Shows the picked tiles
     */
    private void showPickedTiles() {
        System.out.println("Picked tiles: ");
        for(int i=this.pickedTiles.size()-1; i>=0; i--) {
            System.out.print(i + " ");
            this.printTile(this.pickedTiles.get(i));
            System.out.print("\n");
        }

        System.out.println();
    }

    /**
     * Shows the standings
     */
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

    /**
     * Shows the main items of the game
     */
    private void showTable() {
        this.showBoard();
        this.showCommonGoals();
        this.showPersonalGoal();
        this.showBookshelf(this.currentPlayer);
    }


    /* Methods to update the items */
    /**
     * Updates the current board
     * @param board new board
     */
    public void updateBoard(Board board) {
        this.board = board;
        this.showBoard();
    }

    /**
     * Updates a player's bookshelf
     * @param player name of the player
     * @param bookshelf new bookshelf
     */
    public void updateBookshelf(String player, Bookshelf bookshelf) {
        this.bookshelves.replace(player, bookshelf);
    }

    /**
     * Updates the currently picked tiles
     * @param pickedTiles picked tiles
     */
    public void updatePickedTiles(List<Tile> pickedTiles) {
        if(this.currentPlayer.equals(this.clientController.getClient().getNickname())) {
            this.pickedTiles = pickedTiles;
        }
    }

    /**
     * Updates a player's points
     * @param player name of the player
     * @param points new points
     */
    public void updatePoints(String player, int points) {
        this.points.replace(player, points);
    }

    /**
     * Updates the common goals tokens
     * @param commonGoalsTokens new common goals tokens
     */
    public void updateCommonGoals(List<Integer> commonGoalsTokens) {
        this.commonGoalsTokens = commonGoalsTokens;
    }

    /**
     * Updates the end game value
     * @param endGame new end game value
     */
    public void updateEndGame(boolean endGame) {
        if(!this.isLastTurn && endGame && this.clientController.getClient().getNickname().equals(this.currentPlayer)) {
            this.isLastTurn = true;
            System.out.println("Won EndGame token!");
        }
    }

    /**
     * Rearrange the picked tiles
     * @param index of the picked tile to move to the end
     * @return result of the action
     */
    public boolean rearrangeTiles(int index) {
        if(index >= 0 && index < this.pickedTiles.size()) {
            Tile tmp = this.pickedTiles.get(index);
            this.pickedTiles.set(index, this.pickedTiles.get(this.pickedTiles.size() - 1));
            this.pickedTiles.set(this.pickedTiles.size() - 1, tmp);
            return true;
        }
        return false;
    }


    /* Methods to ask for a player's move */
    /**
     * Shows the game logo in a fancy way
     */
    private void showLogo() {
        System.out.println(ANSI_RED + "\n" +
                "███╗   ███╗██╗   ██╗ ██████╗██╗  ██╗███████╗██╗     ███████╗██╗███████╗\n" +
                "████╗ ████║╚██╗ ██╔╝██╔════╝██║  ██║██╔════╝██║     ██╔════╝██║██╔════╝\n" +
                "██╔████╔██║ ╚████╔╝ ╚█████╗ ███████║█████╗  ██║     █████╗  ██║█████╗  \n" +
                "██║╚██╔╝██║  ╚██╔╝   ╚═══██╗██╔══██║██╔══╝  ██║     ██╔══╝  ██║██╔══╝  \n" +
                "██║ ╚═╝ ██║   ██║   ██████╔╝██║  ██║███████╗███████╗██║     ██║███████╗\n" +
                "╚═╝     ╚═╝   ╚═╝   ╚═════╝ ╚═╝  ╚═╝╚══════╝╚══════╝╚═╝     ╚═╝╚══════╝" + ANSI_RESET);
    }

    /**
     * Asks for the type of connection
     */
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

    /**
     * Asks for the nickname
     */
    public void showChooseNickname() {
        System.out.println("Insert a nickname: ");
        String nickname = this.readWord();
        this.clientController.getClient().sendMessage(new LoginRequest(nickname));
    }

    /**
     * Asks for the number of players
     */
    public void showChooseNumPlayers() {
        System.out.println("Insert the number of players of the game (from 2 to 4 players allowed): ");
        int numPlayers = this.readInt();
        this.clientController.getClient().sendMessage(new NumPlayersResponse(numPlayers));
    }

    /**
     * Handles the pick of a tile
     * @return result
     */
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

    /**
     * Handles the unpick of a tile
     * @return result
     */
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

    /**
     * Handles the sending of a message
     */
    private void handleChatMessage() {
        try {
            System.out.println("Type the message:");
            String message = this.readText();
            System.out.print("Type the receiver ( ");
            for(Map.Entry<String, Integer> entry : this.points.entrySet()) {
                if(!entry.getKey().equals(this.clientController.getClient().getNickname())) {
                    System.out.print(entry.getKey() + " | ");
                }
            }
            System.out.print("Everyone ):\n");
            String receiver = this.readWord();
            this.clientController.sendMessage(new ChatMessage(this.clientController.getClient().getNickname(), receiver, message));
        } catch(Exception e) {
            System.out.println("Error!");
        }
    }

    /**
     * Asks to pick a tile
     */
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

    /**
     * Asks to choose the column where to insert the picked tiles
     */
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

    /**
     * Asks to swap the picked tiles order
     */
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
                    this.clientController.sendMessage(new ConfirmOrderNotify(this.pickedTiles));
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
                        if(this.rearrangeTiles(Integer.parseInt(s))) {
                            this.showValidSwap();
                        } else {
                            this.showInvalidSwap();
                        }
                        this.showSwapTilesOrder();
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
    /**
     * Shows that the nickname is not valid
     */
    public void showInvalidNickname() {
        System.out.println("Nickname already taken!");
    }

    /**
     * Shows that the pick is valid
     */
    public void showValidPick() {
        System.out.println("Tile picked successfully!");
    }

    /**
     * Shows that the pick is not valid
     */
    public void showInvalidPick() {
        System.out.println("Tile not pickable!");
    }

    /**
     * Shows that the tile unpick is valid
     */
    public void showValidUnpick() {
        System.out.println("Tile unpicked successfully!");
    }

    /**
     * Shows that the tile unpick is not valid
     */
    public void showInvalidUnpick() {
        System.out.println("Tile not unpickable!");
    }

    /**
     * Shows that no tiles are picked
     */
    public void showNoPickedTiles() {
        System.out.println("Pick at least a tile!");
    }

    /**
     * Shows that the selected column is valid
     */
    public void showValidColumn() {
        System.out.println("Column selected!");
    }

    /**
     * Shows that the selected column is not valid
     */
    public void showInvalidColumn() {
        System.out.println("Invalid column!");
    }

    /**
     * Shows that the swap is valid
     */
    public void showValidSwap() {
        System.out.println("Tiles swapped!");
    }

    /**
     * Shows that the swap is not valid
     */
    public void showInvalidSwap() {
        System.out.println("Invalid index!");
    }


    /* Methods to handle the change of a turn */
    /**
     * Handles the start of a new turn
     * @param player name of the player who is now playing
     */
    public void startTurn(String player) {
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
                this.changeTurn = true;
            } else {
                System.out.println("Type:");
                System.out.println(MESSAGE + " to send a message:");
            }
        }
    }

    /**
     * Handles the end of a player's turn
     */
    public void endTurn() {
        System.out.println(this.currentPlayer + "'s turn is over!");
        System.out.println("New Board:");
        showBoard();
        System.out.println("New " + this.currentPlayer + "'s bookshelf:");
        showBookshelf(this.currentPlayer);
        showPoints();
        showCommonGoals();
    }

    /**
     * Shows a recap of the game
     */
    public void showEndGame() {
        this.endGame = true;
        System.out.println("\n\n\n" +
                "╭━━━╮╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╭╮\n" +
                "┃╭━╮┃╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱╱┃┃\n" +
                "┃┃╱╰╋━━┳╮╭┳━━╮╭━━┳╮╭┳━━┳━┫┃\n" +
                "┃┃╭━┫╭╮┃╰╯┃┃━┫┃╭╮┃╰╯┃┃━┫╭┻╯\n" +
                "┃╰┻━┃╭╮┃┃┃┃┃━┫┃╰╯┣╮╭┫┃━┫┃╭╮\n" +
                "╰━━━┻╯╰┻┻┻┻━━╯╰━━╯╰╯╰━━┻╯╰╯\n");

        System.out.println("Results:");
        List<String> sortedPlayers = this.points
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        for(String sortedPlayer : sortedPlayers) {
            System.out.println(sortedPlayer + "\t: " + this.points.get(sortedPlayer));
        }
        System.exit(0);
    }


    /* Methods to handle chat messages */
    /**
     * Shows an unicast message
     * @param sender sender of the message
     * @param message message sent
     */
    public void showNewChatMessageUnicast(String sender, String message) {
        System.out.println("\nChat:");
        System.out.println("Message from " + sender + " to " + ANSI_GREEN + this.clientController.getClient().getNickname() + ANSI_RESET + ": " + message);
    }

    /**
     * Shows a broadcast message
     * @param sender sender of the message
     * @param message message sent
     */
    public void showNewChatMessageBroadcast(String sender, String message) {
        System.out.println("\nChat:");
        System.out.println("Message from " + sender + " to " + ANSI_RED + "Everyone" + ANSI_RESET + ": " + message);
    }


    /* Methods to show disconnection phase */
    /**
     * Shows a message when a player has disconnected
     * @param disconnectedPlayer name of the player
     */
    public void showPlayerDisconnected(String disconnectedPlayer) {
        if(!this.endGame) {
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
    }

    /**
     * Shows a message when there's a connection issue
     */
    public void showDisconnection() {
        System.out.println("Closing the connection with the server...");
        System.exit(0);
    }
}
