package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.message.ConfirmColumnRequest;
import it.polimi.ingsw.network.message.PickTileRequest;
import it.polimi.ingsw.network.message.UnpickTileRequest;
import it.polimi.ingsw.view.controller.GameController;
import it.polimi.ingsw.view.controller.LoginController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class GUIView extends Application {
    private ClientController clientController;

    /* Gui's items */
    private GUIViewAdapter adapter;
    private Stage mainWindow;
    private StackPane root;
    private BorderPane rootNode;
    private StackPane centerLayout;
    private HBox rightLayout;
    private HBox topLayout;
    private HBox leftLayout;
    private HBox bottomLayout;
    private GridPane boardLayout;
    private VBox commonGoalsLayout;
    private VBox rankingLayout;
    private GridPane bookshelfLayout;
    private GridPane bookshelfMaskLayout;
    private HBox bookshelvesLayout;
    private HBox pickedTilesLayout;
    private HBox tokensLayout;
    private Label turnLayout;
    private List<Tile> tilesLobby;
    private final int tileSizeBookshelf = 38;
    private final int tileSizeBookshelves = 18;
    private final int tileSizeBoard = 64;
    private final int rectangleSizeBoard = 66;
    private int tileNumber;

    /* Controllers */
    private GameController gameController;

    /* Game's items */
    private Board board;
    private Map<String, Bookshelf> bookshelves;
    private List<CommonGoal> commonGoals;
    private List<Integer> commonGoalsTokens;
    private PersonalGoal personalGoal;
    private List<Tile> pickedTiles;
    private Map<String, Integer> points;
    private String currentPlayer;
    private Position lastSelectedTile;
    private int selectedColumn;
    private boolean isLastTurn;
    private boolean endGame;


    public static void main(String[] args) {
        launch();
    }

    /**
     * Sets up the graphical environment
     * @param primaryStage main stage of the application
     */
    @Override
    public void start(Stage primaryStage) {
        this.adapter = new GUIViewAdapter(this);
        this.mainWindow = primaryStage;
        this.mainWindow.setHeight(1050);
        this.mainWindow.setWidth(1680);

        this.mainWindow.setOnCloseRequest(event -> this.closeWindow());
        this.showChooseTypeOfConnection();

        this.tileNumber = new Random().nextInt(3) + 1;
    }

    /**
     * Adapter getter
     * @return adapter
     */
    public GUIViewAdapter getAdapter() {
        return this.adapter;
    }

    /**
     * Client controller getter
     * @return client controller
     */
    public ClientController getClientController() {
        return this.clientController;
    }

    /**
     * Players getter
     * @return players
     */
    public List<String> getPlayers() {
        return new ArrayList<>(this.bookshelves.keySet());
    }

    /**
     * Main window getter
     * @return main window
     */
    public Stage getMainWindow() {
        return mainWindow;
    }

    /**
     * Selected column getter
     * @return selected column
     */
    public int getSelectedColumn() {
        return selectedColumn;
    }

    /**
     * Current player getter
     * @return current player
     */
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Picked tiles getter
     * @return picked tiles
     */
    public List<Tile> getPickedTiles() {
        return this.pickedTiles;
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
     * Loads the main window items from the fxml file
     */
    private void loadTable() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/boardLayout.fxml"));
            this.gameController = new GameController(this);
            loader.setController(this.gameController);
            this.rootNode = loader.load();
            this.root.getChildren().set(0, this.rootNode);

            // Left
            this.leftLayout = (HBox) this.rootNode.getLeft();
            this.commonGoalsLayout = (VBox) this.leftLayout.getChildren().get(1);

            // Right
            this.rightLayout = (HBox) this.rootNode.getRight();
            VBox vBox = (VBox) this.rightLayout.getChildren().get(1);
            this.pickedTilesLayout = (HBox) vBox.getChildren().get(1);
            StackPane stackPane = (StackPane) vBox.getChildren().get(2);
            this.bookshelfMaskLayout = (GridPane) stackPane.getChildren().get(1);
            this.bookshelfLayout = (GridPane) stackPane.getChildren().get(0);
            this.tokensLayout = (HBox) vBox.getChildren().get(4);

            // Top
            this.topLayout = (HBox) this.rootNode.getTop();
            vBox = (VBox) this.topLayout.getChildren().get(1);
            this.bookshelvesLayout = (HBox) vBox.getChildren().get(0);
            this.turnLayout = (Label) vBox.getChildren().get(1);
            this.rankingLayout = (VBox) this.topLayout.getChildren().get(2);

            // Bottom
            this.bottomLayout = (HBox) this.rootNode.getBottom();

            // Center
            this.centerLayout = (StackPane) this.rootNode.getCenter();
            this.boardLayout = (GridPane) this.centerLayout.getChildren().get(0);

            this.initBookshelves();
            this.showBoard();
            this.showCommonGoals();
            this.initPoints();

            // Show chat players
            vBox = (VBox) this.leftLayout.getChildren().get(0);
            TextArea textArea = (TextArea) vBox.getChildren().get(0);
            textArea.setWrapText(true);
            HBox hC = (HBox) vBox.getChildren().get(2);
            ChoiceBox cB = (ChoiceBox) hC.getChildren().get(0);
            ObservableList<String> list = cB.getItems();
            list.add("Everyone");
            for(Map.Entry<String, Bookshelf> entry : this.bookshelves.entrySet()) {
                if (!entry.getKey().equals(this.clientController.getClient().getNickname())) {
                    list.add(entry.getKey());
                }
            }

            // Show personal goals
            ImageView imgV = (ImageView) this.rightLayout.getChildren().get(0);
            imgV.setImage(new Image("/personal_goals/Personal_Goals" + this.personalGoal.getId() + ".png"));

            this.selectedColumn = -1;

            if(!this.clientController.getClient().getNickname().equals(this.currentPlayer)) {
                Button b = (Button) this.bottomLayout.getChildren().get(0);
                b.setDisable(true);
                b = (Button) this.bottomLayout.getChildren().get(1);
                b.setDisable(true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        setTable(board, commonGoals, commonGoalsTokens, personalGoal);
        this.currentPlayer = nextPlayer;
        this.isLastTurn = false;
        this.endGame = false;
        this.loadTable();

        this.turnLayout.setText("Current player: " + nextPlayer);
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
        try {
            if (playerName.equals(this.clientController.getClient().getNickname())) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/lobbyLayout.fxml"));
                this.rootNode = loader.load();
                this.root.getChildren().set(0, this.rootNode);

                this.tilesLobby = new ArrayList<>(List.of(Tile.values()));
                this.tilesLobby.remove(Tile.EMPTY);
                Collections.shuffle(this.tilesLobby);
            }

            VBox vBox = (VBox) this.rootNode.getCenter();
            ProgressBar progressBar = (ProgressBar) vBox.getChildren().get(2);
            progressBar.setProgress(((double) lobby.size()) / lobbySize);
            HBox hBox = (HBox) vBox.getChildren().get(3);
            hBox.getChildren().clear();

            for(int i=0; i<lobby.size(); i++) {
                ImageView imageView = new ImageView(new Image(getTilePath(this.tilesLobby.get(i))));
                imageView.setFitHeight(140);
                imageView.setFitWidth(140);
                imageView.getStyleClass().add("background_shadow");
                Label label = new Label();
                label.setText(lobby.get(i));
                label.setTextFill(Paint.valueOf("WHITE"));
                label.setFont(Font.font(24));
                label.setTextAlignment(TextAlignment.CENTER);
                vBox = new VBox(imageView, label);
                vBox.setSpacing(20);
                vBox.setAlignment(Pos.CENTER);
                hBox.getChildren().add(i, vBox);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
     * Returns the file path of the image related to the given tile
     * @param tile which we need the image path of
     * @return image path
     */
    private String getTilePath(Tile tile) {
        String tilePath = "/item_tiles";
        switch(tile) {
            case BLUE:
                tilePath += "/Cornici1." + this.tileNumber + ".png";
                break;
            case PINK:
                tilePath += "/Piante1." + this.tileNumber + ".png";
                break;
            case WHITE:
                tilePath += "/Libri1." + this.tileNumber + ".png";
                break;
            case YELLOW:
                tilePath += "/Giochi1." + this.tileNumber + ".png";
                break;
            case LBLUE:
                tilePath += "/Trofei1." + this.tileNumber + ".png";
                break;
            case GREEN:
                tilePath += "/Gatti1." + this.tileNumber + ".png";
                break;
        }
        return tilePath;
    }

    /**
     * Shows the board
     */
    private void showBoard() {
        this.boardLayout.getChildren().clear();
        for(int i=0; i<=8; i++) {
            for(int j=-3; j<=5; j++) {
                Position p = new Position(i, j);
                if(!this.board.getTile(p).equals(Tile.EMPTY)) {

                    StackPane st = new StackPane();

                    Rectangle r = new Rectangle();
                    r.getStyleClass().add("tile_not_selected");
                    r.setHeight(this.rectangleSizeBoard);
                    r.setWidth(this.rectangleSizeBoard);

                    ImageView imgV = new ImageView(new Image(getTilePath(this.board.getTile(p))));
                    imgV.setFitWidth(this.tileSizeBoard);
                    imgV.setFitHeight(this.tileSizeBoard);

                    st.getChildren().addAll(r, imgV);

                    st.setOnMouseClicked(event -> {
                        if(this.clientController.getClient().getNickname().equals(this.currentPlayer)) {
                            this.lastSelectedTile = p;
                            if(st.getChildren().get(0).getStyleClass().contains("tile_not_selected")) {
                                this.clientController.sendMessage(new PickTileRequest(p));
                            } else {
                                this.clientController.sendMessage(new UnpickTileRequest(p));
                            }
                        }
                    });

                    this.boardLayout.add(st, j+4, i+1);
                }
            }
        }
    }

    /**
     * Shows the common goals
     */
    private void showCommonGoals() {
        for(int i=0; i<this.commonGoals.size(); i++) {
            StackPane stackPane = (StackPane) this.commonGoalsLayout.getChildren().get(i+1);

            // Change common goal
            ImageView imageView = (ImageView) stackPane.getChildren().get(0);
            imageView.setImage(new Image("/common_goals/Common_Goals" + this.commonGoals.get(i).getId() + ".jpg"));

            // Change token
            try {
                imageView = (ImageView) stackPane.getChildren().get(1);
                imageView.setImage(new Image("/scoring_tokens/scoring_" + this.commonGoalsTokens.get(i) + ".jpg"));
            } catch(IllegalArgumentException e) {
                imageView = (ImageView) stackPane.getChildren().get(1);
                imageView.setVisible(false);
            }
        }
    }

    /**
     * Shows the bookshelf
     */
    private void showBookshelf() {
        this.bookshelfLayout.getChildren().clear();
        for(int i = 0; i < 6; i++) {
            for(int j = 0; j < 5; j++) {
                Position position = new Position(i, j);
                ImageView imgV = new ImageView(new Image(getTilePath(this.bookshelves.get(this.clientController.getClient().getNickname()).getTile(position))));
                imgV.setFitWidth(this.tileSizeBookshelf);
                imgV.setFitHeight(this.tileSizeBookshelf);
                this.bookshelfLayout.add(imgV, j+1, i+1);
            }
        }
    }

    /**
     * Displays only the used bookshelves
     */
    private void initBookshelves() {
        VBox vBox;
        switch(this.bookshelves.size()) {
            case 2:
                vBox = (VBox) this.bookshelvesLayout.getChildren().get(0);
                vBox.setVisible(false);
                vBox = (VBox) this.bookshelvesLayout.getChildren().get(2);
                vBox.setVisible(false);
                vBox = (VBox) this.bookshelvesLayout.getChildren().get(1);
                Label l = (Label) vBox.getChildren().get(0);
                for(Map.Entry<String, Bookshelf> entry : this.bookshelves.entrySet()) {
                    if(!entry.getKey().equals(this.clientController.getClient().getNickname())) {
                        l.setText(entry.getKey());
                    }
                }
                break;
            case 3:
                vBox = (VBox) this.bookshelvesLayout.getChildren().get(1);
                vBox.setVisible(false);
                int index = 0;
                for(Map.Entry<String, Bookshelf> entry : this.bookshelves.entrySet()) {
                    if(!entry.getKey().equals(this.clientController.getClient().getNickname())) {
                        vBox = (VBox) this.bookshelvesLayout.getChildren().get(index);
                        l = (Label) vBox.getChildren().get(0);
                        l.setText(entry.getKey());
                        index += 2;
                    }
                }
                break;
            default:
                index = 0;
                for(Map.Entry<String, Bookshelf> entry : this.bookshelves.entrySet()) {
                    if(!entry.getKey().equals(this.clientController.getClient().getNickname())) {
                        vBox = (VBox) this.bookshelvesLayout.getChildren().get(index);
                        l = (Label) vBox.getChildren().get(0);
                        l.setText(entry.getKey());
                        index++;
                    }
                }
                break;
        }
    }

    /**
     * Shows all the bookshelves
     */
    private void showBookshelves() {
        switch(this.bookshelves.size()) {
            case 2:
                int index = 1;
                VBox vBox = (VBox) this.bookshelvesLayout.getChildren().get(index);
                Label label = (Label) vBox.getChildren().get(0);
                GridPane gridPane = (GridPane) vBox.getChildren().get(1);
                gridPane.getChildren().clear();
                Bookshelf bookshelf = this.bookshelves.get(label.getText());
                for(int i = 0; i < 6; i++) {
                    for(int j = 0; j < 5; j++) {
                        Position position = new Position(i, j);
                        ImageView imgV = new ImageView(new Image(getTilePath(bookshelf.getTile(position))));
                        imgV.setFitWidth(this.tileSizeBookshelves);
                        imgV.setFitHeight(this.tileSizeBookshelves);
                        gridPane.add(imgV, j+1, i+1);
                    }
                }
                break;
            case 3:
                for(index=0; index<3; index+=2) {
                    vBox = (VBox) this.bookshelvesLayout.getChildren().get(index);
                    label = (Label) vBox.getChildren().get(0);
                    gridPane = (GridPane) vBox.getChildren().get(1);
                    gridPane.getChildren().clear();
                    bookshelf = this.bookshelves.get(label.getText());
                    for(int i = 0; i < 6; i++) {
                        for(int j = 0; j < 5; j++) {
                            Position position = new Position(i, j);
                            ImageView imgV = new ImageView(new Image(getTilePath(bookshelf.getTile(position))));
                            imgV.setFitWidth(this.tileSizeBookshelves);
                            imgV.setFitHeight(this.tileSizeBookshelves);
                            gridPane.add(imgV, j+1, i+1);
                        }
                    }
                }
                break;
            case 4:
                for(index=0; index<3; index++) {
                    vBox = (VBox) this.bookshelvesLayout.getChildren().get(index);
                    label = (Label) vBox.getChildren().get(0);
                    gridPane = (GridPane) vBox.getChildren().get(1);
                    gridPane.getChildren().clear();
                    bookshelf = this.bookshelves.get(label.getText());
                    for(int i = 0; i < 6; i++) {
                        for(int j = 0; j < 5; j++) {
                            Position position = new Position(i, j);
                            ImageView imgV = new ImageView(new Image(getTilePath(bookshelf.getTile(position))));
                            imgV.setFitWidth(this.tileSizeBookshelves);
                            imgV.setFitHeight(this.tileSizeBookshelves);
                            gridPane.add(imgV, j+1, i+1);
                        }
                    }
                }
                break;
        }
    }

    /**
     * Shows the picked tiles
     */
    private void showPickedTiles() {
        this.pickedTilesLayout.getChildren().clear();
        for(int index=0; index<this.pickedTiles.size(); index++) {
            ImageView imgV = new ImageView(new Image(getTilePath(this.pickedTiles.get(index))));
            imgV.setFitWidth(this.tileSizeBoard);
            imgV.setFitHeight(this.tileSizeBoard);
            if(this.selectedColumn != -1) {
                int i = index;
                imgV.setOnMouseClicked(event -> {
                    this.rearrangeTiles(i);
                    this.showSwapTilesOrder();
                });
            }
            this.pickedTilesLayout.getChildren().add(imgV);
        }
    }

    /**
     * Displays only the current players in the standings
     */
    private void initPoints() {
        int index = 1;
        for(Map.Entry<String, Integer> entry : this.points.entrySet()) {
            HBox hBox = (HBox) this.rankingLayout.getChildren().get(index);
            Label l = (Label) hBox.getChildren().get(0);
            l.setText(entry.getKey());
            l = (Label) hBox.getChildren().get(1);
            l.setText(entry.getValue().toString());
            index++;
        }
        for(int j=index; j<5; j++) {
            HBox hBox = (HBox) this.rankingLayout.getChildren().get(j);
            hBox.setVisible(false);
        }
    }

    /**
     * Shows the standings
     */
    private void showPoints() {
        List<String> sortedPlayers = this.points
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        int index = 1;
        for(String sortedPlayer : sortedPlayers) {
            HBox hBox = (HBox) this.rankingLayout.getChildren().get(index);
            Label l = (Label) hBox.getChildren().get(0);
            l.setText(sortedPlayer);
            l = (Label) hBox.getChildren().get(1);
            l.setText(this.points.get(sortedPlayer).toString());
            index++;
        }
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
        this.pickedTiles = pickedTiles;
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
        for(int i=0; i<this.commonGoalsTokens.size(); i++) {
            if(!this.commonGoalsTokens.get(i).equals(commonGoalsTokens.get(i)) && this.clientController.getClient().getNickname().equals(this.currentPlayer)) {
                ImageView imgV = new ImageView(new Image("/scoring_tokens/scoring_" + this.commonGoalsTokens.get(i) + ".jpg"));
                imgV.setFitWidth(this.tileSizeBoard);
                imgV.setFitHeight(this.tileSizeBoard);
                this.tokensLayout.getChildren().add(imgV);
            }
        }
        this.commonGoalsTokens = commonGoalsTokens;
    }

    /**
     * Updates the end game value
     * @param endGame new end game value
     */
    public void updateEndGame(boolean endGame) {
        if(!this.isLastTurn && endGame) {
            if(this.clientController.getClient().getNickname().equals(this.currentPlayer)) {
                ImageView imgV = new ImageView(new Image("/scoring_tokens/end_game.jpg"));
                imgV.setFitWidth(this.tileSizeBoard);
                imgV.setFitHeight(this.tileSizeBoard);
                this.tokensLayout.getChildren().add(imgV);
            }
            this.isLastTurn = true;
            try {
                StackPane stackPane = (StackPane) this.rootNode.getCenter();
                stackPane.getChildren().remove(1);
            } catch(IndexOutOfBoundsException ignored) {

            }
        }
    }

    /**
     * Rearrange the picked tiles
     * @param index of the picked tile to move to the end
     * @return result of the action
     */
    private boolean rearrangeTiles(int index) {
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
     * Asks for the type of connection
     */
    public void showChooseTypeOfConnection() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/connectionLayout.fxml"));
            loader.setController(new LoginController(this));
            this.rootNode = loader.load();
            this.root = new StackPane();
            this.root.getChildren().add(this.rootNode);

            Scene scene = new Scene(this.root);
            this.mainWindow.setScene(scene);
            this.mainWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Asks for the nickname
     */
    public void showChooseNickname() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/nicknameLayout.fxml"));
            loader.setController(new LoginController(this));
            this.rootNode = loader.load();
            this.root.getChildren().set(0, this.rootNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Asks for the number of players
     */
    public void showChooseNumPlayers() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/numPlayersLayout.fxml"));
            loader.setController(new LoginController(this));
            this.rootNode = loader.load();
            this.root.getChildren().set(0, this.rootNode);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Asks to pick a tile
     */
    public void showPickATile() {
    }

    /**
     * Asks to choose the column where to insert the picked tiles
     */
    public void showChooseColumn() {
        Button pick = (Button) this.bottomLayout.getChildren().get(0);
        pick.setDisable(true);

        this.showBoard();

        this.showPickedTiles();

        // Highlight bookshelf columns
        for(Node node : this.bookshelfMaskLayout.getChildren()) {
            if(GridPane.getColumnIndex(node) > 0 && GridPane.getColumnIndex(node) < 6) {
                node.setOnMouseEntered(event -> node.getStyleClass().add("column_selected"));
                node.setOnMouseExited(event -> node.getStyleClass().remove("column_selected"));
                node.setOnMouseClicked(event -> {
                    this.selectedColumn = GridPane.getColumnIndex(node) - 1;
                    this.clientController.sendMessage(new ConfirmColumnRequest(this.selectedColumn));
                    this.showPickedTiles();
                });
            }
        }
    }

    /**
     * Asks to swap the picked tiles order
     */
    public void showSwapTilesOrder() {
        this.showPickedTiles();
    }


    /* Methods to show a move's result */
    /**
     * Shows that the nickname is not valid
     */
    public void showInvalidNickname() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Nickname already taken!");
        alert.initOwner(this.mainWindow);
        alert.show();
    }

    /**
     * Shows that the pick is valid
     */
    public void showValidPick() {
        ObservableList<Node> list = this.boardLayout.getChildren();
        for(Node node : list) {
            if(node != null) {
                if ((GridPane.getRowIndex(node) - 1) == this.lastSelectedTile.getRow() && (GridPane.getColumnIndex(node) - 4) == this.lastSelectedTile.getColumn()) {
                    StackPane st = (StackPane) node;
                    st.getChildren().get(0).getStyleClass().remove("tile_not_selected");
                    st.getChildren().get(0).getStyleClass().add("tile_selected");
                    break;
                }
            }
        }
    }

    /**
     * Shows that the pick is not valid
     */
    public void showInvalidPick() {
    }

    /**
     * Shows that the tile unpick is valid
     */
    public void showValidUnpick() {
        ObservableList<Node> list = this.boardLayout.getChildren();

        for(Node node : list) {
            if(node != null) {
                if ((GridPane.getRowIndex(node) - 1) == this.lastSelectedTile.getRow() && (GridPane.getColumnIndex(node) - 4) == this.lastSelectedTile.getColumn()) {
                    StackPane st = (StackPane) node;
                    st.getChildren().get(0).getStyleClass().remove("tile_selected");
                    st.getChildren().get(0).getStyleClass().add("tile_not_selected");
                    break;
                }
            }
        }
    }

    /**
     * Shows that the tile unpick is not valid
     */
    public void showInvalidUnpick() {
    }

    /**
     * Shows that no tiles are picked
     */
    public void showNoPickedTiles() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "No picked tiles!");
        alert.initOwner(this.mainWindow);
        alert.show();
    }

    /**
     * Shows that the selected column is valid
     */
    public void showValidColumn() {
        for(Node node : this.bookshelfMaskLayout.getChildren()) {
            if(GridPane.getColumnIndex(node) > 0 && GridPane.getColumnIndex(node) < 6) {
                node.setOnMouseEntered(event -> {});
                node.setOnMouseExited(event -> {});
                node.setOnMouseClicked(event -> {});
            }
        }
    }

    /**
     * Shows that the selected column is not valid
     */
    public void showInvalidColumn() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid column!");
        alert.initOwner(this.mainWindow);
        alert.show();
    }

    /**
     * Shows that the swap is valid
     */
    public void showValidSwap() {
    }

    /**
     * Shows that the swap is not valid
     */
    public void showInvalidSwap() {
    }


    /* Methods to handle the change of a turn */
    /**
     * Handles the start of a new turn
     * @param player name of the player who is now playing
     */
    public void startTurn(String player) {
        this.turnLayout.setText("Current player: " + player);
        this.currentPlayer = player;
        if(this.currentPlayer.equals(this.clientController.getClient().getNickname())) {
                Button b = (Button) this.bottomLayout.getChildren().get(0);
                b.setDisable(false);
                b = (Button) this.bottomLayout.getChildren().get(1);
                b.setDisable(false);
            } else {
                this.currentPlayer = player;
                Button b = (Button) this.bottomLayout.getChildren().get(0);
                b.setDisable(true);
                b = (Button) this.bottomLayout.getChildren().get(1);
                b.setDisable(true);
            }
    }

    /**
     * Handles the end of a player's turn
     */
    public void endTurn() {
        this.pickedTilesLayout.getChildren().clear();
        this.showBoard();
        this.showCommonGoals();
        this.showPoints();

        // Remove highlight bookshelf columns
        for(Node node : this.bookshelfMaskLayout.getChildren()) {
            if(GridPane.getColumnIndex(node) > 0 && GridPane.getColumnIndex(node) < 6) {
                node.getStyleClass().remove("column_selected");
                node.setOnMouseEntered(event -> {});
                node.setOnMouseExited(event -> {});
                node.setOnMouseClicked(event -> {});
            }
        }

        this.showBookshelf();
        this.showBookshelves();
        this.selectedColumn = -1;
    }

    /**
     * Shows a recap of the game
     */
    public void showEndGame() {
        this.endGame = true;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/resultsLayout.fxml"));
            this.rootNode = loader.load();
            this.root.getChildren().set(0, this.rootNode);

            List<String> sortedPlayers = this.points
                    .entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            Button button = new Button();
            button.setText("Close game");
            button.setAlignment(Pos.CENTER);
            button.setContentDisplay(ContentDisplay.CENTER);
            button.setPrefHeight(35);
            button.setPrefWidth(100);
            button.getStyleClass().add("background_shadow");
            button.setTextAlignment(TextAlignment.CENTER);
            button.setOnMouseClicked(event -> this.closeWindow());

            VBox vBox = (VBox) this.rootNode.getCenter();
            vBox.getChildren().add(button);

            HBox hBox = (HBox) vBox.getChildren().get(2);

            for(int i=0; i<sortedPlayers.size(); i++) {
                Label labelNumber = new Label();
                labelNumber.setText(i + 1 + "° (" + this.points.get(sortedPlayers.get(i)) + " pt)");
                labelNumber.setTextFill(Paint.valueOf("WHITE"));
                labelNumber.setFont(Font.font(24));
                labelNumber.setTextAlignment(TextAlignment.CENTER);
                ImageView imageView = new ImageView(new Image(getTilePath(this.tilesLobby.get(i))));
                imageView.setFitHeight(125);
                imageView.setFitWidth(125);
                imageView.getStyleClass().add("background_shadow");
                Label label = new Label();
                label.setText(sortedPlayers.get(i));
                label.setTextFill(Paint.valueOf("WHITE"));
                label.setFont(Font.font(24));
                label.setTextAlignment(TextAlignment.CENTER);
                vBox = new VBox(labelNumber, imageView, label);
                vBox.setSpacing(20);
                vBox.setAlignment(Pos.CENTER);
                hBox.getChildren().add(i, vBox);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    /* Methods to handle chat messages */
    /**
     * Shows an unicast message
     * @param sender sender of the message
     * @param message message sent
     */
    public void showNewChatMessageUnicast(String sender, String message) {
        this.gameController.printChatMessageUnicast(sender, message);
    }

    /**
     * Shows a broadcast message
     * @param sender sender of the message
     * @param message message sent
     */
    public void showNewChatMessageBroadcast(String sender, String message) {
        this.gameController.printChatMessageBroadcast(sender, message);
    }


    /* Methods to show disconnection phase */
    /**
     * Shows a message when a player has disconnected
     * @param disconnectedPlayer name of the player
     */
    public void showPlayerDisconnected(String disconnectedPlayer) {
        if(!this.endGame) {
            Alert alert = new Alert(Alert.AlertType.ERROR, disconnectedPlayer + " has disconnected!");
            alert.initOwner(this.mainWindow);
            alert.show();
            alert.setOnCloseRequest(event -> this.closeWindow());
        }
    }

    /**
     * Shows a message when there's a connection issue
     */
    public void showDisconnection() {
        if(!this.endGame) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Closing the connection with the server!");
            alert.initOwner(this.mainWindow);
            alert.show();
            alert.setOnCloseRequest(event -> this.closeWindow());
        }
    }

    /**
     * Closes the game
     */
    public void closeWindow() {
        this.mainWindow.close();
        System.exit(0);
    }
}
