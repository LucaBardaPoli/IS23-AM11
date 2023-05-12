package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.client.ClientController;
import javafx.application.Platform;

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
    public void showChooseTypeOfConnection() {
        Platform.setImplicitExit(false);
        Platform.runLater(this.view::showChooseTypeOfConnection);
    }

    @Override
    public void showChooseNickname() {
        //Platform.setImplicitExit(false);
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

    }

    @Override
    public List<String> getPlayers() {
        return null;
    }

    @Override
    public void setPlayers(List<String> players) {

    }

    @Override
    public void setEndGame(boolean endGame) {

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
    public void showPickATile() {

    }

    @Override
    public void showChooseColumn() {

    }

    @Override
    public void showSwapTilesOrder() {

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
    public void showNewChatMessageUnicast(String sender, String message) {

    }

    @Override
    public void showNewChatMessageBroadcast(String sender, String message) {

    }

    @Override
    public void showPlayerDisconnected(String disconnectedPlayer) {

    }

    @Override
    public void showDisconnection() {

    }
}
