package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.client.ClientController;

import java.util.List;

public class GUIView implements View {
    private ClientController clientController;

    public void setClientController(ClientController clientController) {
        this.clientController = clientController;
    }

    public void updateBoard(Board board) {
    }

    public void updateBookshelf(String player, Bookshelf bookshelf) {
    }

    public void chooseTypeOfConnection() {
    }

    public void chooseUsername() {
    }

    public void chooseNumPlayers() {
    }

    public void startGame(Board board, List<CommonGoal> commonGoals, PersonalGoal personalGoal, String nextPlayer) {
    }

    public void setPlayers(List<String> players) {
    }

    public void showPickATile() {
    }

    public void showValidPick() {
    }

    public void showInvalidPick() {
    }

    public void showValidUnpick() {
    }

    public void showInvalidUnpick() {
    }

    public void updatePickedTiles(List<Tile> pickedTiles) {
    }

    public void showBookshelf(String player) {
    }

    public void showChooseColumn() {
    }

    public void showValidColumn() {
    }

    public void showInvalidColumn() {
    }

    public void showSwapTilesOrder() {
    }

    public void showValidSwap() {
    }

    public void showInvalidSwap() {
    }

    public void updatePoints(String player, int points) {
    }

    public void startTurn(String player) {
    }

    public void endTurn() {
    }
}
