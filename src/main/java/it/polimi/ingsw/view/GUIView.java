package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.message.PickTileRequest;
import it.polimi.ingsw.view.controller.GameController;
import it.polimi.ingsw.view.controller.LoginController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class GUIView extends Application {
    private ClientController clientController;

    /* Gui's items */
    private GUIViewAdapter adapter;
    private Stage mainWindow;
    private BorderPane rootNode;
    private final double screenWidth = Screen.getScreens().get(0).getBounds().getWidth();
    private final double screenHeight = Screen.getScreens().get(0).getBounds().getHeight();
    private Scene scene;
    private GameController gameController;

    /* Game's items */
    private Board board;
    private Map<String, Bookshelf> bookshelves;
    private Map<CommonGoal, Integer> commonGoals;
    private PersonalGoal personalGoal;
    private List<Tile> pickedTiles;
    private Map<String, Integer> points;
    private String currentPlayer;

    /* Utils */
    private boolean changeTurn;
    private boolean endGame;


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) {
        this.adapter = new GUIViewAdapter(this);
        this.mainWindow = primaryStage;
        this.mainWindow.setHeight(screenHeight);
        this.mainWindow.setWidth(screenWidth);
        this.mainWindow.setMaximized(true);
        this.mainWindow.show();
        this.showChooseTypeOfConnection();
    }

    public GUIViewAdapter getAdapter() {
        return adapter;
    }

    public ClientController getClientController() {
        return clientController;
    }

    public List<String> getPlayers() {
        return new ArrayList<>(bookshelves.keySet());
    }


    /* Initialization methods */
    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }

    public void setEndGame(boolean endGame) {
        this.endGame = endGame;
    }

    public void startGame(Board board, Map<CommonGoal, Integer> commonGoals, PersonalGoal personalGoal, String nextPlayer) {
        setTable(board, commonGoals, personalGoal);
        this.showBoard();
        this.currentPlayer = nextPlayer;
        //this.showPickATile();
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
        int tileNumber = rand.nextInt(3) + 1;
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/boardLayout.fxml"));
            this.gameController = new GameController(this);
            loader.setController(this.gameController);
            this.rootNode = loader.load();

            // Show other players' bookshelves
            HBox h = (HBox) this.rootNode.getTop();
            for(Map.Entry<String, Bookshelf> entry : this.bookshelves.entrySet()) {
                if(!entry.getKey().equals(this.clientController.getClient().getNickname())) {
                    loader = new FXMLLoader(getClass().getResource("/fxml/bookshelf.fxml"));
                    StackPane s = (StackPane) loader.load();
                    VBox v = (VBox) s.getChildren().get(0);
                    Label l = (Label) v.getChildren().get(0);
                    l.setText(entry.getKey());

                    h.getChildren().add(s);
                }
            }

            // Show current player's bookshelf
            VBox v = (VBox) this.rootNode.getRight();

            loader = new FXMLLoader(getClass().getResource("/fxml/bookshelf.fxml"));
            StackPane s = (StackPane) loader.load();
            VBox vB = (VBox) s.getChildren().get(0);

            Label l = (Label) vB.getChildren().get(0);
            l.setText(this.clientController.getClient().getNickname());

            GridPane gp = (GridPane) vB.getChildren().get(1);
            gp.setPrefSize(200, 200);

            ImageView img = new ImageView(new Image("/personal_goals/Personal_Goals" +  this.personalGoal.getId() + ".png"));
            img.setFitWidth(200);
            img.setFitHeight(300);

            v.getChildren().add(1, s);
            v.getChildren().add(2, img);
            v.setSpacing(50);

            // Show Board
            GridPane g = (GridPane) this.rootNode.getCenter();

            for(int i=0; i<=8; i++) {
                for(int j=-3; j<=5; j++) {
                    Position p = new Position(i, j);
                    if(this.board.getBoard().containsKey(p)) {
                        ImageView imgV = new ImageView(new Image(getTilePath(this.board.getTile(p))));
                        imgV.setOnMouseClicked(event -> this.clientController.sendMessage(new PickTileRequest(p)));
                        imgV.setFitHeight(75);
                        imgV.setFitWidth(75);
                        g.add(imgV, j+3, i);
                    }
                }
            }

            // Show chat players
            VBox vC = (VBox) this.rootNode.getLeft();

            HBox hC = (HBox) vC.getChildren().get(2);
            ChoiceBox cB = (ChoiceBox) hC.getChildren().get(0);
            ObservableList<String> list = cB.getItems();

            for(Map.Entry<String, Bookshelf> entry : this.bookshelves.entrySet()) {
                if (!entry.getKey().equals(this.clientController.getClient().getNickname())) {
                    list.add(entry.getKey());
                }
            }

            this.scene = new Scene(this.rootNode);
            this.mainWindow.setScene(scene);
            this.mainWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /* Methods to update the items */
    public void updateBoard(Board board) {

    }

    public void updateBookshelf(String player, Bookshelf bookshelf) {

    }

    public void updatePickedTiles(List<Tile> pickedTiles) {

    }

    public void updatePoints(String player, int points) {

    }

    public void updateCommonGoals(Map<CommonGoal, Integer> commonGoals) {

    }


    /* Methods to ask for a player's move */
    public void showChooseTypeOfConnection() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/connectionLayout.fxml"));
            loader.setController(new LoginController(this));
            StackPane node = loader.load();

            Scene scene = new Scene(node);
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
            StackPane node = loader.load();

            Scene scene = new Scene(node);
            this.mainWindow.setScene(scene);
            this.mainWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showChooseNumPlayers() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/numPlayersLayout.fxml"));
            loader.setController(new LoginController(this));
            StackPane node = loader.load();

            Scene scene = new Scene(node);
            this.mainWindow.setScene(scene);
            this.mainWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPickATile() {

    }

    public void showChooseColumn() {

    }

    public void showSwapTilesOrder() {

    }


    /* Methods to show a move's result */
    public void showInvalidNickname() {
        new Alert(Alert.AlertType.ERROR, "Nickname already taken!").show();
    }

    public void showValidPick() {

    }

    public void showInvalidPick() {

    }

    public void showValidUnpick() {

    }

    public void showInvalidUnpick() {

    }

    public void showNoPickedTiles() {

    }

    public void showValidColumn() {

    }

    public void showInvalidColumn() {

    }

    public void showValidSwap() {

    }

    public void showInvalidSwap() {

    }


    /* Methods to handle the change of a turn */
    public void startTurn(String player) {

    }

    public void endTurn() {
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
}
