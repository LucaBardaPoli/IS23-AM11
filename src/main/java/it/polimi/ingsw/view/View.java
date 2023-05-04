package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.network.client.ClientController;
import java.util.List;

public interface View {
    List<String> getPlayers();


    /* Initialization methods */
    void setClientController(ClientController clientController);
    void startGame(Board board, List<CommonGoal> commonGoals, PersonalGoal personalGoal, String nextPlayer);
    void setPlayers(List<String> players);
    void setTable(Board board, List<CommonGoal> commonGoals, PersonalGoal personalGoal);


    /* Methods to display the items of the game */
    void showBookshelf(String player);


    /* Methods to update the items */
    void updateBoard(Board board);
    void updateBookshelf(String player, Bookshelf bookshelf);
    void updatePickedTiles(List<Tile> pickedTiles);
    void updatePoints(String player, int points);


    /* Methods to ask for a player's move */
    void showChooseTypeOfConnection();
    void showChooseNickname();
    void showChooseNumPlayers();
    void showPickATile();
    void showChooseColumn();
    void showSwapTilesOrder();


    /* Methods to show a move's result */
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
    void showEndGame();


    /* Methods to handle chat messages */
    void showNewChatMessageUnicast(String sender, String message);
    void showNewChatMessageBroadcast(String sender, String message);


    /* Methods to show disconnection issues */
    void showPlayerDisconnected(String disconnectedPlayer);
}
