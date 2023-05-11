package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.client.ClientController;
import it.polimi.ingsw.network.client.LaunchClient;
import it.polimi.ingsw.network.message.LoginRequest;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.List;
import java.util.Map;

public class GUIView extends Application implements View {
    private ClientController clientController;
    private Stage mainWindow;

    public void runGUI() {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.mainWindow = primaryStage;
        this.mainWindow.show();
        this.showChooseTypeOfConnection();
    }

    @Override
    public void showChooseTypeOfConnection() {
        GridPane layout = new GridPane();

        Scene scene = new Scene(layout,500,300);

        Label text = new Label("Connection info");
        text.setMaxWidth(Double.MAX_VALUE);
        text.setAlignment(Pos.CENTER);

        TextField serverIpTextField = new TextField();
        serverIpTextField.setText("127.0.0.1");

        Label serverIPLabel = new Label("Server IP");

        Button b1 = new Button();
        b1.setText("TCP");
        b1.setOnAction(event -> LaunchClient.openConnection(b1.getText(), serverIpTextField.getText(), this));

        Button b2 = new Button();
        b2.setText("RMI");
        b2.setOnAction(event -> LaunchClient.openConnection(b2.getText(), serverIpTextField.getText(), this));

        layout.setAlignment(Pos.CENTER);
        layout.setHgap(8);
        layout.setVgap(8);
        layout.setPadding(new Insets(5, 5, 5, 5));

        layout.add(text,0,0,3,1);
        layout.add(serverIPLabel,0,1);
        layout.add(serverIpTextField,1,1,2,1);
        layout.add(b1,1,2);
        layout.add(b2,2,2);

        this.mainWindow.setScene(scene);
        this.mainWindow.setTitle("MyShelfie");
        this.mainWindow.show();
    }

    @Override
    public void showChooseNickname() {
        GridPane layout = new GridPane();

        Scene scene = new Scene(layout,500,300);

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

        this.mainWindow.setScene(scene);
        this.mainWindow.show();
    }

    @Override
    public void showChooseNumPlayers() {

    }

    @Override
    public void showPickATile() {

    }

    @Override
    public void showChooseColumn() {

    }

    @Override
    public void showSwapTilesOrder() {

    }

    @Override
    public List<String> getPlayers() {
        return null;
    }

    @Override
    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }

    @Override
    public void startGame(Board board, Map<CommonGoal, Integer> commonGoals, PersonalGoal personalGoal, String nextPlayer) {

    }

    @Override
    public void showDisconnection() {

    }

    @Override
    public void setPlayers(List<String> players) {

    }

    @Override
    public void showBookshelf(String player) {

    }

    @Override
    public void updateBoard(Board board) {

    }

    @Override
    public void updateBookshelf(String player, Bookshelf bookshelf) {

    }

    @Override
    public void updatePickedTiles(List<Tile> pickedTiles) {

    }

    @Override
    public void updatePoints(String player, int points) {

    }

    @Override
    public void updateCommonGoals(Map<CommonGoal, Integer> commonGoals) {

    }

    @Override
    public void showValidPick() {

    }

    @Override
    public void showInvalidPick() {

    }

    @Override
    public void showValidUnpick() {

    }

    @Override
    public void showInvalidUnpick() {

    }

    @Override
    public void showNoPickedTiles() {

    }

    @Override
    public void showValidColumn() {

    }

    @Override
    public void showInvalidColumn() {

    }

    @Override
    public void showValidSwap() {

    }

    @Override
    public void showInvalidSwap() {

    }

    @Override
    public void startTurn(String player) {

    }

    @Override
    public void endTurn() {

    }

    @Override
    public void showEndGame() {

    }

    @Override
    public void showNewChatMessageUnicast(String sender, String message) {

    }

    @Override
    public void showNewChatMessageBroadcast(String sender, String message) {

    }

    @Override
    public void showPlayerDisconnected(String disconnectedPlayer) {

    }
}
