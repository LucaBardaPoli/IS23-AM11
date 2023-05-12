package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.client.ClientController;
import java.util.List;
import java.util.Map;

public interface View {

    List<String> getPlayers();


    /* Initialization methods */
    void setClientController(ClientController clientController);
    void setEndGame(boolean endGame);
    void startGame(Board board, Map<CommonGoal, Integer> commonGoals, PersonalGoal personalGoal, String nextPlayer);
    void setPlayers(List<String> players);


    /* Methods to display the items of the game */
    void showBookshelf(String player);


    /* Methods to update the items */
    void updateBoard(Board board);
    void updateBookshelf(String player, Bookshelf bookshelf);
    void updatePickedTiles(List<Tile> pickedTiles);
    void updatePoints(String player, int points);
    void updateCommonGoals(Map<CommonGoal, Integer> commonGoals);


    /* Methods to ask for a player's move */
    void showChooseTypeOfConnection();
    void showChooseNickname();
    void showChooseNumPlayers();
    void showPickATile();
    void showChooseColumn();
    void showSwapTilesOrder();


    /* Methods to show a move's result */
    void showInvalidNickname();
    void showValidPick();
    void showInvalidPick();
    void showValidUnpick();
    void showInvalidUnpick();
    void showNoPickedTiles();
    void showValidColumn();
    void showInvalidColumn();
    void showValidSwap();
    void showInvalidSwap();


    /* Methods to handle the change of a turn */
    void startTurn(String player);
    void endTurn();


    /* Methods to handle chat messages */
    void showNewChatMessageUnicast(String sender, String message);
    void showNewChatMessageBroadcast(String sender, String message);


    /* Methods to show disconnection issues */
    void showPlayerDisconnected(String disconnectedPlayer);
    void showDisconnection();
}
