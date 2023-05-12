package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.client.LaunchClient;
import it.polimi.ingsw.network.message.LoginRequest;
import it.polimi.ingsw.network.message.NumPlayersResponse;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
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
        this.mainWindow.show();
        this.showChooseTypeOfConnection();
    }

    public void showChooseTypeOfConnection() {
        /*try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/fxml/rootLayout.fxml"));
            this.rootLayout = loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(this.rootLayout);
            mainWindow.setScene(scene);

            tcpButton.setOnAction(actionEvent -> LaunchClient.openConnection("TCP", serverIpTextField.getText(), this.adapter));
            tcpButton.setOnAction(actionEvent -> LaunchClient.openConnection("RMI", serverIpTextField.getText(), this.adapter));

            mainWindow.show();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        StackPane mainLayout = new StackPane();
        mainLayout.setPrefHeight(400.0);
        mainLayout.setPrefWidth(700.0);

        VBox mainVBox = new VBox();
        mainVBox.setAlignment(Pos.CENTER);

        VBox titleVBox = new VBox();
        titleVBox.setAlignment(Pos.CENTER);
        titleVBox.setSpacing(10.0);

        Label connectionLabel = new Label();
        connectionLabel.setText("Connection info");

        titleVBox.getChildren().add(connectionLabel);

        HBox ipHBox = new HBox();
        ipHBox.setAlignment(Pos.CENTER);
        ipHBox.setPrefHeight(10.0);
        ipHBox.setPrefWidth(200.0);
        ipHBox.setSpacing(10);
        ipHBox.setPadding(new Insets(20.0, 0, 20.0, 0));

        Label serverIpLabel = new Label();
        serverIpLabel.setText("Server IP");

        TextField serverIpTextField = new TextField();
        serverIpTextField.setText("127.0.0.1");

        ipHBox.getChildren().addAll(serverIpLabel, serverIpTextField);

        HBox buttonHBox = new HBox();
        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.setPrefWidth(200.0);
        buttonHBox.setSpacing(10);

        Button tcpButton = new Button();
        tcpButton.setText("TCP");
        tcpButton.setOnAction(event -> LaunchClient.openConnection(tcpButton.getText(), serverIpTextField.getText(), this.adapter));

        Button rmiButton = new Button();
        rmiButton.setText("RMI");
        rmiButton.setOnAction(event -> LaunchClient.openConnection(rmiButton.getText(), serverIpTextField.getText(), this.adapter));

        buttonHBox.getChildren().addAll(tcpButton, rmiButton);

        mainVBox.getChildren().addAll(titleVBox, ipHBox, buttonHBox);

        mainLayout.getChildren().add(mainVBox);

        this.scene = new Scene(mainLayout);
        this.mainWindow.setScene(this.scene);
        this.mainWindow.show();
    }

    public void showChooseNickname() {
        GridPane layout = new GridPane();

        this.scene = new Scene(layout, 500, 300);

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
        this.mainWindow.show();
    }

    public void showChooseNumPlayers() {
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
        this.mainWindow.show();
    }

    public void showInvalidNickname() {
        Alert alert = new Alert(Alert.AlertType.ERROR, "Nickname already taken!");
        alert.show();
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
        setTable(board, commonGoals, personalGoal);
        BorderPane layout = new BorderPane();

        Image boardImage = new Image("livingroom.png", 500, 300, true, true);
        ImageView boardImageView = new ImageView(boardImage);
        layout.setCenter(boardImageView);

        this.scene = new Scene(layout, 500, 300);
        this.mainWindow.setScene(this.scene);
        this.mainWindow.show();

        /*this.showBoard();
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
