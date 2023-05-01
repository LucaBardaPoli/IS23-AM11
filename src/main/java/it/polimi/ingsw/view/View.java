package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.client.ClientController;

import java.util.List;

public interface View {
    void setClientController(ClientController clientController);
    void updateBookshelf(String player, Bookshelf bookshelf);
    void updateBoard(Board board);
    void chooseTypeOfConnection();
    void chooseUsername();
    void chooseNumPlayers();
    void startGame(Board board, List<CommonGoal> commonGoals, PersonalGoal personalGoal, String nextPlayer);
    void setPlayers(List<String> players);
    void showPickATile();
    void showValidPick();
    void showInvalidPick();
    void showValidUnpick();
    void showInvalidUnpick();
    void updatePickedTiles(List<Tile> pickedTiles);
    void showBookshelf(String player);
    void showChooseColumn();
    void showValidColumn();
    void showInvalidColumn();
    void showSwapTilesOrder();
    void showValidSwap();
    void showInvalidSwap();
    void updatePoints(String player, int points);
    void startTurn(String player);
    void endTurn();
}
