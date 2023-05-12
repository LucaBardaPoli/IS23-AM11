package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.client.ClientController;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GUIViewAdapter implements View {
    private final GUIView view;

    public GUIViewAdapter(GUIView view) {
        this.view = view;
    }


    @Override
    public void setClientController(ClientController clientController) {
        this.view.setClientController(clientController);
    }

    @Override
    public void showChooseNickname() {
        Platform.setImplicitExit(false);
        Platform.runLater(this.view::showChooseNickname);
    }

    @Override
    public void showChooseNumPlayers() {
        //Platform.setImplicitExit(false);
        Platform.runLater(this.view::showChooseNumPlayers);
    }

    @Override
    public void showInvalidNickname() {
        //Platform.setImplicitExit(false);
        Platform.runLater(this.view::showInvalidNickname);
    }

    @Override
    public void startGame(Board board, Map<CommonGoal, Integer> commonGoals, PersonalGoal personalGoal, String nextPlayer) {
        Platform.runLater(() -> this.view.startGame(board, commonGoals, personalGoal, nextPlayer));
    }

    @Override
    public List<String> getPlayers() {
        return this.view.getPlayers();
    }

    @Override
    public void setPlayers(List<String> players) {
        Platform.runLater(() -> this.view.setPlayers(players));
    }

    @Override
    public void setEndGame(boolean endGame) {
        Platform.runLater(() -> this.view.setEndGame(endGame));
    }

    @Override
    public void showBookshelf(String player) {
        Platform.runLater(() -> this.view.showBookshelf(player));
    }

    @Override
    public void updateBoard(Board board) {
        Platform.runLater(() -> this.view.updateBoard(board));
    }

    @Override
    public void updateBookshelf(String player, Bookshelf bookshelf) {
        Platform.runLater(() -> this.view.updateBookshelf(player, bookshelf));
    }

    @Override
    public void updatePickedTiles(List<Tile> pickedTiles) {
        Platform.runLater(() -> this.view.updatePickedTiles(pickedTiles));
    }

    @Override
    public void updatePoints(String player, int points) {
        Platform.runLater(() -> this.view.updatePoints(player, points));
    }

    @Override
    public void updateCommonGoals(Map<CommonGoal, Integer> commonGoals) {
        Platform.runLater(() -> this.view.updateCommonGoals(commonGoals));
    }

    @Override
    public void showChooseTypeOfConnection() {
        Platform.runLater(this.view::showChooseTypeOfConnection);
    }

    @Override
    public void showPickATile() {
        Platform.runLater(this.view::showPickATile);
    }

    @Override
    public void showChooseColumn() {
        Platform.runLater(this.view::showChooseColumn);
    }

    @Override
    public void showSwapTilesOrder() {
        Platform.runLater(this.view::showSwapTilesOrder);
    }

    @Override
    public void showValidPick() {
        Platform.runLater(this.view::showValidPick);
    }

    @Override
    public void showInvalidPick() {
        Platform.runLater(this.view::showInvalidPick);
    }

    @Override
    public void showValidUnpick() {
        Platform.runLater(this.view::showValidUnpick);
    }

    @Override
    public void showInvalidUnpick() {
        Platform.runLater(this.view::showInvalidUnpick);
    }

    @Override
    public void showNoPickedTiles() {
        Platform.runLater(this.view::showNoPickedTiles);
    }

    @Override
    public void showValidColumn() {
        Platform.runLater(this.view::showValidColumn);
    }

    @Override
    public void showInvalidColumn() {
        Platform.runLater(this.view::showInvalidColumn);
    }

    @Override
    public void showValidSwap() {
        Platform.runLater(this.view::showValidSwap);
    }

    @Override
    public void showInvalidSwap() {
        Platform.runLater(this.view::showInvalidSwap);
    }

    @Override
    public void startTurn(String player) {
        Platform.runLater(() -> this.view.startTurn(player));
    }

    @Override
    public void endTurn() {
        Platform.runLater(this.view::endTurn);
    }

    @Override
    public void showNewChatMessageUnicast(String sender, String message) {
        Platform.runLater(() -> this.view.showNewChatMessageUnicast(sender, message));
    }

    @Override
    public void showNewChatMessageBroadcast(String sender, String message) {
        Platform.runLater(() -> this.view.showNewChatMessageBroadcast(sender, message));
    }

    @Override
    public void showPlayerDisconnected(String disconnectedPlayer) {
        Platform.runLater(() -> this.view.showPlayerDisconnected(disconnectedPlayer));
    }

    @Override
    public void showDisconnection() {
        Platform.runLater(this.view::showDisconnection);
    }
}
