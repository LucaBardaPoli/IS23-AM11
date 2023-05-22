package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.message.ConfirmColumnRequest;
import it.polimi.ingsw.network.message.PickTileRequest;
import it.polimi.ingsw.network.message.SwapTilesOrderRequest;
import it.polimi.ingsw.network.message.UnpickTileRequest;
import it.polimi.ingsw.view.controller.GameController;
import it.polimi.ingsw.view.controller.LoginController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class GUIView extends Application {
    private ClientController clientController;

    /* Gui's items */
    private GUIViewAdapter adapter;
    private Stage mainWindow;
    private Scene scene;
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
    private final int tileSizeBookshelf = 38;
    private final int tileSizeBoard = 64;
    private final int rectangleSizeBoard = 66;

    /* Controllers */
    private GameController gameController;

    /* Game's items */
    private Board board;
    private Map<String, Bookshelf> bookshelves;
    private Map<CommonGoal, Integer> commonGoals;
    private PersonalGoal personalGoal;
    private List<Tile> pickedTiles;
    private Map<String, Integer> points;
    private String currentPlayer;
    private Position lastSelectedTile;
    private int selectedColumn;

    /* Utils */
    private boolean endGame;


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        this.adapter = new GUIViewAdapter(this);
        this.mainWindow = primaryStage;
        this.mainWindow.setHeight(1050);
        this.mainWindow.setWidth(1680);

        this.mainWindow.setOnCloseRequest(event -> this.closeWindow());
        this.showChooseTypeOfConnection();
    }

    public GUIViewAdapter getAdapter() {
        return this.adapter;
    }

    public ClientController getClientController() {
        return this.clientController;
    }

    public List<String> getPlayers() {
        return new ArrayList<>(this.bookshelves.keySet());
    }

    public Stage getMainWindow() {
        return mainWindow;
    }


    /* Initialization methods */
    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }

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
            this.pickedTilesLayout = (HBox) vBox.getChildren().get(0);
            StackPane stackPane = (StackPane) vBox.getChildren().get(1);
            this.bookshelfMaskLayout = (GridPane) stackPane.getChildren().get(1);
            this.bookshelfLayout = (GridPane) stackPane.getChildren().get(0);
            this.tokensLayout = (HBox) vBox.getChildren().get(2);

            // Top
            this.topLayout = (HBox) this.rootNode.getTop();
            this.bookshelvesLayout = (HBox) this.topLayout.getChildren().get(1);
            this.rankingLayout = (VBox) this.topLayout.getChildren().get(2);

            // Bottom
            this.bottomLayout = (HBox) this.rootNode.getBottom();

            // Center
            this.centerLayout = (StackPane) this.rootNode.getCenter();
            this.boardLayout = (GridPane) this.centerLayout.getChildren().get(0);

            this.showBookshelves();
            this.showBoard();
            this.showCommonGoals();
            this.showPoints();

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

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public void startGame(Board board, Map<CommonGoal, Integer> commonGoals, PersonalGoal personalGoal, String nextPlayer) {
        setTable(board, commonGoals, personalGoal);
        this.currentPlayer = nextPlayer;
        this.loadTable();

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "It's " + nextPlayer + "'s turn!");
        alert.initOwner(this.mainWindow);
        alert.show();
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
    private String getTilePath(Tile tile) {
        Random rand = new Random();
        //int tileNumber = rand.nextInt(3) + 1;
        int tileNumber = 1;
        String tilePath = "/item_tiles";
        switch(tile) {
            case BLUE:
                tilePath += "/Cornici1." + tileNumber + ".png";
                break;
            case PINK:
                tilePath += "/Piante1." + tileNumber + ".png";
                break;
            case WHITE:
                tilePath += "/Libri1." + tileNumber + ".png";
                break;
            case YELLOW:
                tilePath += "/Giochi1." + tileNumber + ".png";
                break;
            case LBLUE:
                tilePath += "/Trofei1." + tileNumber + ".png";
                break;
            case GREEN:
                tilePath += "/Gatti1." + tileNumber + ".png";
                break;
        }
        return tilePath;
    }

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

    private void showCommonGoals() {
        int index = 0;
        for(Map.Entry<CommonGoal, Integer> entry : this.commonGoals.entrySet()) {
            StackPane stackPane = (StackPane) this.commonGoalsLayout.getChildren().get(index);

            // Change common goal
            ImageView imageView = (ImageView) stackPane.getChildren().get(0);
            imageView.setImage(new Image("/common_goals/Common_Goals" + entry.getKey().getId() + ".jpg"));

            // Remove not used tokens
            switch (this.bookshelves.size()) {
                case 2:
                    imageView = (ImageView) stackPane.getChildren().get(1);
                    imageView.setVisible(false);
                    imageView = (ImageView) stackPane.getChildren().get(3);
                    imageView.setVisible(false);
                    break;
                case 3:
                    imageView = (ImageView) stackPane.getChildren().get(1);
                    imageView.setVisible(false);
                    break;
                default:
                    break;
            }

            index++;
        }
    }

    private void showBookshelf() {
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

    private void showBookshelves() {
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

    public void showPickedTiles() {
        this.pickedTilesLayout.getChildren().clear();
        for(Tile t : this.pickedTiles) {
            ImageView imgV = new ImageView(new Image(getTilePath(t)));
            imgV.setFitWidth(this.tileSizeBoard);
            imgV.setFitHeight(this.tileSizeBoard);
            if(this.selectedColumn != -1) {
                imgV.setOnMouseClicked(event -> this.clientController.sendMessage(new SwapTilesOrderRequest(this.pickedTiles.indexOf(t))));
            }
            this.pickedTilesLayout.getChildren().add(imgV);
        }
    }

    public void showPoints() {
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


    /* Methods to update the items */
    public void updateBoard(Board board) {
        this.board = board;
    }

    public void updateBookshelf(String player, Bookshelf bookshelf) {
        this.bookshelves.replace(player, bookshelf);
    }

    public void updatePickedTiles(List<Tile> pickedTiles) {
        this.pickedTiles = pickedTiles;
    }

    public void updatePoints(String player, int points) {
        this.points.replace(player, points);
    }

    public void updateCommonGoals(Map<CommonGoal, Integer> commonGoals) {
        this.commonGoals = commonGoals;
    }


    /* Methods to ask for a player's move */
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

    public void showPickATile() {
    }

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

    public void showSwapTilesOrder() {
        this.showPickedTiles();
    }


    /* Methods to show a move's result */
    public void showInvalidNickname() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Nickname already taken!");
        alert.initOwner(this.mainWindow);
        alert.show();
    }

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

    public void showInvalidPick() {
    }

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

    public void showInvalidUnpick() {
    }

    public void showNoPickedTiles() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "No picked tiles!");
        alert.initOwner(this.mainWindow);
        alert.show();
    }

    public void showValidColumn() {
        for(Node node : this.bookshelfMaskLayout.getChildren()) {
            if(GridPane.getColumnIndex(node) > 0 && GridPane.getColumnIndex(node) < 6) {
                node.setOnMouseEntered(event -> {});
                node.setOnMouseExited(event -> {});
                node.setOnMouseClicked(event -> {});
            }
        }
    }

    public void showInvalidColumn() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Not a valid column!");
        alert.initOwner(this.mainWindow);
        alert.show();
    }

    public void showValidSwap() {
    }

    public void showInvalidSwap() {
    }


    /* Methods to handle the change of a turn */
    public void startTurn(String player) {
        if(!this.endGame) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "It's " + player + "'s turn!");
            alert.initOwner(this.mainWindow);
            alert.show();
            if(this.currentPlayer.equals(this.clientController.getClient().getNickname())) {
                this.currentPlayer = player;
                this.showPickATile();
                Button b = (Button) this.bottomLayout.getChildren().get(0);
                b.setDisable(true);
                b = (Button) this.bottomLayout.getChildren().get(1);
                b.setDisable(true);
            } else {
                this.currentPlayer = player;
                Button b = (Button) this.bottomLayout.getChildren().get(0);
                b.setDisable(false);
                b = (Button) this.bottomLayout.getChildren().get(1);
                b.setDisable(false);
            }
        } else {
            this.showEndGame();
        }
    }

    public void endTurn() {
        this.pickedTilesLayout.getChildren().clear();
        this.showBoard();
        this.showCommonGoals();
        this.showPoints();

        // Highlight bookshelf columns
        for(Node node : this.bookshelfMaskLayout.getChildren()) {
            if(GridPane.getColumnIndex(node) > 0 && GridPane.getColumnIndex(node) < 6) {
                node.getStyleClass().remove("column_selected");
                node.setOnMouseEntered(event -> {});
                node.setOnMouseExited(event -> {});
                node.setOnMouseClicked(event -> {});
            }
        }

        this.showBookshelf();
        //this.showBookshelves();
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
        this.clientController.getClient().close();
    }


    /* Methods to handle chat messages */
    public void showNewChatMessageUnicast(String sender, String message) {
        this.gameController.printChatMessageUnicast(sender, message);
    }

    public void showNewChatMessageBroadcast(String sender, String message) {
        this.gameController.printChatMessageBroadcast(sender, message);
    }


    /* Methods to show disconnection phase */
    public void showPlayerDisconnected(String disconnectedPlayer) {

    }

    public void showDisconnection() {

    }

    public void closeWindow() {

    }
}
