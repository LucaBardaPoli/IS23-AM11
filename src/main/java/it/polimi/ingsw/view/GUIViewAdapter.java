package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.client.ClientController;
import javafx.application.Platform;

import java.util.List;

public class GUIViewAdapter implements View {
    private final GUIView view;

    public GUIViewAdapter(GUIView view) {
        this.view = view;
    }

    public List<String> getPlayers() {
        return this.view.getPlayers();
    }

    public String getCurrentPlayer() {
        return this.view.getCurrentPlayer();
    }


    /* Initialization methods */
    public void setClientController(ClientController clientController) {
        this.view.setClientController(clientController);
    }

    public void setEndGame(boolean endGame) {
        Platform.runLater(() -> this.view.setEndGame(endGame));
    }

    public void startGame(Board board, List<CommonGoal> commonGoals, List<Integer> commonGoalsTokens, PersonalGoal personalGoal, String nextPlayer) {
        Platform.runLater(() -> this.view.startGame(board, commonGoals, commonGoalsTokens, personalGoal, nextPlayer));
    }

    public void setPlayers(List<String> players) {
        Platform.runLater(() -> this.view.setPlayers(players));
    }

    public void updateLobbyInfo(int lobbySize, List<String> lobby, boolean newPlayerConnected, String playerName) {
        Platform.runLater(() -> this.view.updateLobbyInfo(lobbySize, lobby, newPlayerConnected, playerName));
    }


    /* Methods to update the items */
    public void updateBoard(Board board) {
        Platform.runLater(() -> this.view.updateBoard(board));
    }

    public void updateBookshelf(String player, Bookshelf bookshelf) {
        Platform.runLater(() -> this.view.updateBookshelf(player, bookshelf));
    }

    public void updatePickedTiles(List<Tile> pickedTiles) {
        Platform.runLater(() -> this.view.updatePickedTiles(pickedTiles));
    }

    public void updatePoints(String player, int points) {
        Platform.runLater(() -> this.view.updatePoints(player, points));
    }

    public void updateCommonGoals(List<Integer> commonGoalsTokens) {
        Platform.runLater(() -> this.view.updateCommonGoals(commonGoalsTokens));
    }

    public void updateEndGame(boolean endGame) {
        Platform.runLater(() -> this.view.updateEndGame(endGame));
    }


    /* Methods to ask for a player's move */
    public void showChooseTypeOfConnection() {
        Platform.runLater(this.view::showChooseTypeOfConnection);
    }

    public void showChooseNickname() {
        Platform.setImplicitExit(false);
        Platform.runLater(this.view::showChooseNickname);
    }

    public void showChooseNumPlayers() {
        Platform.runLater(this.view::showChooseNumPlayers);
    }

    public void showPickATile() {
        Platform.runLater(this.view::showPickATile);
    }

    public void showChooseColumn() {
        Platform.runLater(this.view::showChooseColumn);
    }

    public void showSwapTilesOrder() {
        Platform.runLater(this.view::showSwapTilesOrder);
    }


    /* Methods to show a move's result */
    public void showInvalidNickname() {
        Platform.runLater(this.view::showInvalidNickname);
    }

    public void showValidPick() {
        Platform.runLater(this.view::showValidPick);
    }

    public void showInvalidPick() {
        Platform.runLater(this.view::showInvalidPick);
    }

    public void showValidUnpick() {
        Platform.runLater(this.view::showValidUnpick);
    }

    public void showInvalidUnpick() {
        Platform.runLater(this.view::showInvalidUnpick);
    }

    public void showNoPickedTiles() {
        Platform.runLater(this.view::showNoPickedTiles);
    }

    public void showValidColumn() {
        Platform.runLater(this.view::showValidColumn);
    }

    public void showInvalidColumn() {
        Platform.runLater(this.view::showInvalidColumn);
    }

    public void showValidSwap() {
        Platform.runLater(this.view::showValidSwap);
    }

    public void showInvalidSwap() {
        Platform.runLater(this.view::showInvalidSwap);
    }


    /* Methods to handle the change of a turn */
    public void startTurn(String player) {
        Platform.runLater(() -> this.view.startTurn(player));
    }

    public void endTurn() {
        Platform.runLater(this.view::endTurn);
    }


    /* Methods to handle chat messages */
    public void showNewChatMessageUnicast(String sender, String message) {
        Platform.runLater(() -> this.view.showNewChatMessageUnicast(sender, message));
    }

    public void showNewChatMessageBroadcast(String sender, String message) {
        Platform.runLater(() -> this.view.showNewChatMessageBroadcast(sender, message));
    }


    /* Methods to show disconnection issues */
    public void showPlayerDisconnected(String disconnectedPlayer) {
        Platform.runLater(() -> this.view.showPlayerDisconnected(disconnectedPlayer));
    }

    public void showDisconnection() {
        Platform.runLater(this.view::showDisconnection);
    }
}
