package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.message.NumPlayersResponse;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUIView extends Application {
    private GUIViewAdapter adapter;
    private ClientController clientController;
    private Stage mainWindow;
    private Parent rootNode;
    private final double screenWidth = Screen.getScreens().get(0).getBounds().getWidth();
    private final double screenHeight = Screen.getScreens().get(0).getBounds().getHeight();
    private Scene scene;

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

    public void showChooseTypeOfConnection() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/connectionLayout.fxml"));
            loader.setController(new LoginController(this));
            this.rootNode = loader.load();

            Scene scene = new Scene(this.rootNode);
            this.mainWindow.setScene(scene);
            this.mainWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        StackPane mainLayout = new StackPane();

        VBox mainVBox = new VBox();
        mainVBox.setAlignment(Pos.CENTER);

        VBox titleVBox = new VBox();
        titleVBox.setAlignment(Pos.CENTER);
        titleVBox.setSpacing(10.0);

        Label connectionLabel = new Label();
        connectionLabel.setText("Connection info");
        connectionLabel.setFont(new Font("System Bold", 16.0));

        titleVBox.getChildren().add(connectionLabel);

        HBox ipHBox = new HBox();
        ipHBox.setAlignment(Pos.CENTER);
        ipHBox.setPrefHeight(10.0);
        ipHBox.setPrefWidth(200.0);
        ipHBox.setSpacing(10);
        ipHBox.setPadding(new Insets(30.0, 0, 20.0, 0));

        Label serverIpLabel = new Label();
        serverIpLabel.setText("Server IP");

        TextField serverIpTextField = new TextField();
        serverIpTextField.setText("127.0.0.1");
        serverIpTextField.setAlignment(Pos.CENTER);
        serverIpTextField.setPrefWidth(110.0);

        ipHBox.getChildren().addAll(serverIpLabel, serverIpTextField);

        HBox buttonHBox = new HBox();
        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.setPrefWidth(200.0);
        buttonHBox.setSpacing(10);

        Button tcpButton = new Button();
        tcpButton.setText("TCP");
        tcpButton.setPrefWidth(80.0);
        tcpButton.setOnAction(event -> {
            if(!LaunchClient.openConnection(tcpButton.getText(), serverIpTextField.getText(), this.adapter)) {
                this.showConnectionError();
            }
        });

        Button rmiButton = new Button();
        rmiButton.setText("RMI");
        rmiButton.setPrefWidth(80.0);
        rmiButton.setOnAction(event -> {
            if(!LaunchClient.openConnection(rmiButton.getText(), serverIpTextField.getText(), this.adapter)) {
                this.showConnectionError();
            }
        });

        buttonHBox.getChildren().addAll(tcpButton, rmiButton);

        mainVBox.getChildren().addAll(titleVBox, ipHBox, buttonHBox);

        mainLayout.getChildren().add(mainVBox);

        this.scene = new Scene(mainLayout);
        this.mainWindow.setScene(this.scene);
        this.mainWindow.show();*/
    }

    public void showChooseNickname() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/nicknameLayout.fxml"));
            loader.setController(new LoginController(this));
            this.rootNode = loader.load();

            Scene scene = new Scene(this.rootNode);
            this.mainWindow.setScene(scene);
            this.mainWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        GridPane layout = new GridPane();

        this.scene = new Scene(layout);

        Label text = new Label("Choose a nickname");
        text.setMaxWidth(Double.MAX_VALUE);
        text.setAlignment(Pos.CENTER);

        TextField nicknameTextField = new TextField();
        nicknameTextField.setText("");

        Label nicknameLabel = new Label("Nickname");

        Button b1 = new Button("Login");
        b1.setOnAction(event -> this.clientController.sendMessage(new LoginRequest(nicknameTextField.getText())));

        layout.setAlignment(Pos.CENTER);
        layout.setHgap(8);
        layout.setVgap(8);
        layout.setPadding(new Insets(5, 5, 5, 5));
        layout.add(text, 0, 0, 3, 1);
        layout.add(nicknameLabel, 0, 1);
        layout.add(nicknameTextField, 1, 1, 2, 1);
        layout.add(b1, 2, 2);

        this.mainWindow.setScene(this.scene);
        this.mainWindow.show();*/
    }

    public void showChooseNumPlayers() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/numPlayersLayout.fxml"));
            loader.setController(new LoginController(this));
            this.rootNode = loader.load();

            Scene scene = new Scene(this.rootNode);
            this.mainWindow.setScene(scene);
            this.mainWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        GridPane layout = new GridPane();

        Scene scene = new Scene(layout, 500, 300);

        Label text = new Label("Choose the number of players");
        text.setMaxWidth(Double.MAX_VALUE);
        text.setAlignment(Pos.CENTER);

        TextField numPlayersTextField = new TextField();
        numPlayersTextField.setText("" + GameSettings.MIN_NUM_PLAYERS + "");

        Label nicknameLabel = new Label("Number");

        Button b1 = new Button("Login");
        b1.setOnAction(event -> this.clientController.sendMessage(new NumPlayersResponse(Integer.parseInt(numPlayersTextField.getText()))));

        layout.setAlignment(Pos.CENTER);
        layout.setHgap(8);
        layout.setVgap(8);
        layout.setPadding(new Insets(5, 5, 5, 5));
        layout.add(text, 0, 0, 3, 1);
        layout.add(nicknameLabel, 0, 1);
        layout.add(numPlayersTextField, 1, 1, 2, 1);
        layout.add(b1, 2, 2);

        this.mainWindow.setScene(scene);
        this.mainWindow.show();*/
    }

    public void showInvalidNickname() {
        new Alert(Alert.AlertType.ERROR, "Nickname already taken!").show();
    }

    public void showPickATile() {

    }

    public void showChooseColumn() {

    }

    public void showSwapTilesOrder() {

    }

    public List<String> getPlayers() {
        return new ArrayList<>(bookshelves.keySet());
    }

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }

    public void setEndGame(boolean endGame) {

    }

    private void setTable(Board board, Map<CommonGoal, Integer> commonGoals, PersonalGoal personalGoal) {
        this.board = board;
        this.commonGoals = commonGoals;
        this.personalGoal = personalGoal;
        this.pickedTiles = new ArrayList<>();
    }

    public void startGame(Board board, Map<CommonGoal, Integer> commonGoals, PersonalGoal personalGoal, String nextPlayer) {
        /*setTable(board, commonGoals, personalGoal);
        BorderPane layout = new BorderPane();

        Image boardImage = new Image("livingroom.png", 500, 300, true, true);
        ImageView boardImageView = new ImageView(boardImage);
        layout.setCenter(boardImageView);

        this.scene = new Scene(layout, 500, 300);
        this.mainWindow.setScene(this.scene);
        this.mainWindow.show();

        this.showBoard();
        this.showCommonGoals();
        this.showPersonalGoal();
        this.currentPlayer = nextPlayer;
        this.showPickATile();*/
    }

    public void showDisconnection() {

    }

    public void setPlayers(List<String> players) {
        this.bookshelves = new HashMap<>();
        this.points = new HashMap<>();
        for(String player : players) {
            this.bookshelves.put(player, new Bookshelf());
            this.points.put(player, 0);
        }
    }

    public void showBookshelf(String player) {

    }

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

    public void startTurn(String player) {

    }

    public void endTurn() {

    }

    public void showNewChatMessageUnicast(String sender, String message) {

    }

    public void showNewChatMessageBroadcast(String sender, String message) {

    }

    public void showPlayerDisconnected(String disconnectedPlayer) {

    }
}
