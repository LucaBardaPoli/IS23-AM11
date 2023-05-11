package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.client.LaunchClient;
import it.polimi.ingsw.network.message.LoginRequest;
import it.polimi.ingsw.network.message.NumPlayersResponse;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class GUIView extends Application implements View {
    private GUIViewAdapter adapter;
    private ClientController clientController;
    private Stage mainWindow;
    private Scene scene;

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage primaryStage) {
        this.mainWindow = primaryStage;
        this.adapter = new GUIViewAdapter(this);
        this.showChooseTypeOfConnection();
    }

    public void showChooseTypeOfConnection() {
        GridPane layout = new GridPane();

        this.scene = new Scene(layout,500,300);

        Label text = new Label("Connection info");
        text.setMaxWidth(Double.MAX_VALUE);
        text.setAlignment(Pos.CENTER);

        TextField serverIpTextField = new TextField();
        serverIpTextField.setText("127.0.0.1");

        Label serverIPLabel = new Label("Server IP");

        Button b1 = new Button();
        b1.setText("TCP");
        b1.setOnAction(event -> LaunchClient.openConnection(b1.getText(), serverIpTextField.getText(), this.adapter));

        Button b2 = new Button();
        b2.setText("RMI");
        b2.setOnAction(event -> LaunchClient.openConnection(b2.getText(), serverIpTextField.getText(), this.adapter));

        layout.setAlignment(Pos.CENTER);
        layout.setHgap(8);
        layout.setVgap(8);
        layout.setPadding(new Insets(5, 5, 5, 5));

        layout.add(text,0,0,3,1);
        layout.add(serverIPLabel,0,1);
        layout.add(serverIpTextField,1,1,2,1);
        layout.add(b1,1,2);
        layout.add(b2,2,2);

        this.mainWindow.setTitle("MyShelfie");
        this.mainWindow.setScene(this.scene);
        this.mainWindow.show();
    }

    public void showChooseNickname() {
        GridPane layout = new GridPane();

        this.scene = new Scene(layout,500,300);

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
        layout.add(text,0,0,3,1);
        layout.add(nicknameLabel,0,1);
        layout.add(nicknameTextField,1,1,2,1);
        layout.add(b1,2,2);

        this.mainWindow.setScene(this.scene);
        this.mainWindow.show();
    }

    
    public void showChooseNumPlayers() {
        GridPane layout = new GridPane();

        Scene scene = new Scene(layout,500,300);

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
        layout.add(text,0,0,3,1);
        layout.add(nicknameLabel,0,1);
        layout.add(numPlayersTextField,1,1,2,1);
        layout.add(b1,2,2);

        this.mainWindow.setScene(scene);
        this.mainWindow.show();
    }

    
    public void showInvalidNickname() {
        /*Alert alert = new Alert(Alert.AlertType.ERROR, "Nickname already taken!");
        alert.show();*/
    }

    
    public void showPickATile() {

    }

    
    public void showChooseColumn() {

    }

    
    public void showSwapTilesOrder() {

    }

    
    public List<String> getPlayers() {
        return null;
    }

    
    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }

    
    public void startGame(Board board, Map<CommonGoal, Integer> commonGoals, PersonalGoal personalGoal, String nextPlayer) {

    }

    
    public void showDisconnection() {

    }

    
    public void setPlayers(List<String> players) {

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

    
    public void showEndGame() {

    }

    
    public void showNewChatMessageUnicast(String sender, String message) {

    }

    
    public void showNewChatMessageBroadcast(String sender, String message) {

    }

    
    public void showPlayerDisconnected(String disconnectedPlayer) {

    }
}
